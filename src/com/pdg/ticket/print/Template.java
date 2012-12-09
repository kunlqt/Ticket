package com.pdg.ticket.print;

import com.pdg.ticket.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * make a activity to print in template mode
 *
 * @author  Brother Industries, Ltd.
 * @version 1.0
 */
public class Template extends Activity {
	private static final int FILE_LIST = 1;
	private TextView selectedFile;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.template);
	}
	//template select
	public void templateSelectButton_click(View v){
		Intent fileList = new Intent(this, FileList.class);
		selectedFile = (TextView)findViewById(R.id.templateFile_id);
		CharSequence fileList_label = selectedFile.getText();
		String path = fileList_label.toString();
		if(path.equals(getString(R.string.selectedFile_label))){
			fileList.putExtra("fileName", "");
		}else{
			fileList.putExtra("fileName", path);
		}
		startActivityForResult(fileList, FILE_LIST);
	}

	public void printButton_click1(View v){
		Intent mainMenu = new Intent(this, StartMenu.class);
		mainMenu.putExtra("transfer", false );
		System.out.println("Buuuuuuu   1111");
		//get template key
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		String templateNum = editText1.getText().toString();
		if(templateNum != ""){
			mainMenu.putExtra("templateNum", templateNum);
		}
		//get text 1
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		String text1 = editText2.getText().toString();
		if(text1 != ""){
			mainMenu.putExtra("templateText1", text1);
		}
		//get text 2
		EditText editText3 = (EditText) findViewById(R.id.editText3);
		String text2 = editText3.getText().toString();
		if(text2 != ""){
			mainMenu.putExtra("templateText2", text2);
		}
		mainMenu.putExtra("mode", 1 );
		setResult(RESULT_OK, mainMenu);
		finish();
	}
	public void printButton_click2(View v){
		Intent mainMenu = new Intent(this, StartMenu.class);
		mainMenu.putExtra("transfer", false );
		System.out.println("Buuuuuuu   222222");

		//get template key
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		String templateNum = editText1.getText().toString();
		if(templateNum != ""){
			mainMenu.putExtra("templateNum", templateNum);
		}
		//get text 1
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		String text1 = editText2.getText().toString();
		if(text1 != ""){
			mainMenu.putExtra("templateText1", text1);
		}
		//get text 2
		EditText editText3 = (EditText) findViewById(R.id.editText3);
		String text2 = editText3.getText().toString();
		if(text2 != ""){
			mainMenu.putExtra("templateText2", text2);
		}
		mainMenu.putExtra("mode", 2 );
		setResult(RESULT_OK, mainMenu);
		finish();
	}
	public void printButton_click3(View v){
		Intent mainMenu = new Intent(this, StartMenu.class);
		mainMenu.putExtra("transfer", false );
		System.out.println("Buuuuuuu   33333");

		//get template key
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		String templateNum = editText1.getText().toString();
		if(templateNum != ""){
			mainMenu.putExtra("templateNum", templateNum);
		}
		//get text 1
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		String text1 = editText2.getText().toString();
		if(text1 != ""){
			mainMenu.putExtra("templateText1", text1);
		}
		//get text 2
		EditText editText3 = (EditText) findViewById(R.id.editText3);
		String text2 = editText3.getText().toString();
		if(text2 != ""){
			mainMenu.putExtra("templateText2", text2);
		}
		mainMenu.putExtra("mode", 3 );
		setResult(RESULT_OK, mainMenu);
		finish();
	}
	public void transferButton_click(View v){
		Intent mainMenu = new Intent(this, StartMenu.class);
		mainMenu.putExtra("transfer", true );
		//get template file
		
		TextView textView = (TextView) findViewById(R.id.templateFile_id);
		String template = textView.getText().toString();
		if(template != ""){
			mainMenu.putExtra("template", template);
		}
		setResult(RESULT_OK, mainMenu);
		finish();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == FILE_LIST){
			if(resultCode == RESULT_OK){
				setContentView(R.layout.template);
				selectedFile = (TextView)findViewById(R.id.templateFile_id);
				selectedFile.setText(data.getStringExtra("fileName"));
			}
		}
	}

}
