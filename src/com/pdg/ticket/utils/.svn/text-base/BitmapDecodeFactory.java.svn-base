package com.pdg.ticket.utils;

import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class BitmapDecodeFactory 
{
	
	public static int getImageWidth(String path)
	{
		Bitmap bm = BitmapFactory.decodeFile(path);
		return bm.getWidth();
		
	}
	
	public static int getImageHeight(String path)
	{
		Bitmap bm = BitmapFactory.decodeFile(path);
		return bm.getHeight();
	}
	
	public static byte[] ImageToByteArray(String path)
	{
		Bitmap bm = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);    
        byte[] b = baos.toByteArray(); 
        return b;
	}
	
	public static byte[] ImageToByteArray(Bitmap bitmap)
	{
		//Bitmap bm = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);    
        byte[] b = baos.toByteArray(); 
        return b;
	}
	
	public static byte[] CropImage(byte[] image,int x, int y, int width, int height)
	{
		Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
		
		Bitmap cropBitmap = Bitmap.createBitmap(bm, x, y, width, height);
		
		// recycle de giai thoat bo nho
		bm.recycle();
		byte[] tmp = BitmapDecodeFactory.ImageToByteArray(cropBitmap);
		cropBitmap.recycle();
		
		return tmp;
	}
	
	public static String getImagePath(String ImageFile)
	{
		return Environment.getExternalStorageDirectory() + "/" + ImageFile + ".jpg";
	}

}
