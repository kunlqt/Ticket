package com.pdg.ticket.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.R.drawable;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdg.ticket.R;
import com.pdg.ticket.adapter.ModelCorrectionTicket;


public class CreatePdfUtils {
	

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font whiteFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,
			Font.NORMAL, BaseColor.WHITE);
	
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);
	private static BaseColor orangeColor=new BaseColor(244,115,31);
	private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,
			Font.NORMAL, orangeColor);
	private List<ModelCorrectionTicket> listTicket;
	private Image img;
	private Context context;
	private File filePath;
	
	public CreatePdfUtils(List<ModelCorrectionTicket> list,Context context){
		this.listTicket=list;
		this.context=context;
	}
	
	
	private  void createTable(Section subCatPart,ModelCorrectionTicket model)
			throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Name/Number", whiteFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(orangeColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Review",whiteFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(orangeColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Confirmed",whiteFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(orangeColor);
		table.addCell(c1);
		table.setHeaderRows(1);

		//add row for review run ticket
		
		table.addCell(model.getNumber());
		c1 = new PdfPCell(new Phrase("Review",orangeFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Yes"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		//add row for review rail ticket
		if (model.isRail()) {
			table.addCell("Rail Ticket");
			c1 = new PdfPCell(new Phrase("Review",orangeFont));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Yes"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		//add row for review correction ticket
		if (model.isCorrection()) {
			table.addCell("Correction");
			c1 = new PdfPCell(new Phrase("Review",orangeFont));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Yes"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		subCatPart.add(table);	
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph,2);
		subCatPart.add(paragraph);

	}
	private  void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	public void CreatePdfFile(){
		try {
			Document document = new Document();
			filePath=getOutputMediaFile(1);
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(filePath));
			writer.setPageEvent(new HeaderAndFooter());
			document.open();
			addContent(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private  void addContent(Document document) throws DocumentException {

		// Second parameter is the number of the chapter
		Anchor anchor = new Anchor("Run Log Review", catFont);
		anchor.setName("Run Log Review");

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("Run Log Review", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph(""));

		// Add a list
		//createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 3);
		subCatPart.add(paragraph);

		// Add a table
		for (int i = 0; i < listTicket.size(); i++) {
			createTable(subCatPart, listTicket.get(i));
		}
		
		//add billing table
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Name/Number", whiteFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(orangeColor);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Review",whiteFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(orangeColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Confirmed",whiteFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(orangeColor);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		table.addCell("Billing Slip");
		c1 = new PdfPCell(new Phrase("Review",orangeFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Yes"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		subCatPart.add(table);
		// Now add all this to the document
		document.add(subCatPart);

		
//		// Next section
//		anchor = new Anchor("Second Chapter", catFont);
//		anchor.setName("Second Chapter");
//
//		// Second parameter is the number of the chapter
//		catPart = new Chapter(new Paragraph(anchor), 1);
//
//		subPara = new Paragraph("Subcategory", subFont);
//		subCatPart = catPart.addSection(subPara);
//		subCatPart.add(new Paragraph("This is a very important message"));
//
//		// Now add all this to the document
//		document.add(catPart);

	}
	
	private static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"MyPdf");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		if (type == 1) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "Review_" + timeStamp + ".pdf");
		} else if (type == 2) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}

		return mediaFile;
	}
	public class HeaderAndFooter extends PdfPageEventHelper {

		protected Phrase header;

		protected PdfPTable footer;

		 

		public HeaderAndFooter() {

		header = new Phrase("**** Header ****");

		footer = new PdfPTable(1);

		footer.setTotalWidth(150);

		footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		footer.addCell(new Phrase(new Chunk(

		"**** Footer summary Report generated by JavaGenious****")

		.setAction(new PdfAction(PdfAction.FIRSTPAGE))));

		}
	}
	public File getFilePath(){
		return filePath;
	}
}
