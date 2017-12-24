	package com.example.stegano;

import java.io.File;

import com.example.explorer.Filechoice;
import com.example.explorer.Filechoice1;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class encode extends ListActivity {
		
	String load;
	String image;
	private static final int REQUEST_PATH = 1;
	File filedoc;
	File gambar;
    String curFilename;
    String curFilename1;
    EditText editload;
    EditText editimage;
    EditText editsimpan;
	EditText password;
 
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.encode);
    	editload = (EditText) findViewById(R.id.etfile);
    	editimage = (EditText) findViewById(R.id.etcover);
    	editsimpan = (EditText) findViewById(R.id.etsaveto);
		password = (EditText) findViewById(R.id.password);
    	
    	Button load = (Button) findViewById(R.id.btbrowsefile1); 
    	load.setOnClickListener(new View.OnClickListener() {
    			
			@Override
			public void onClick(View v) {
				getfile();
				// TODO Auto-generated method stub
			
				
			}
		});
    	
    	Button image = (Button) findViewById(R.id.btbrowseimage1);
    	image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getimage();
				
			}
		});
    	
    	Button close = (Button) findViewById(R.id.btclose1);
    	close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getclose();
			}
		});
       	
    	Button encode = (Button) findViewById(R.id.btencode);
    	encode.setOnClickListener(new View.OnClickListener() {
    			
    			@SuppressLint("ShowToast") @Override
    			public void onClick(View v) {
					// TODO Auto-generated method stub
    				Integer compress = 9;
    				String coba = editload.getText().toString();
    				String coba1 = editimage.getText().toString();
    				String simpan = editsimpan.getText().toString();
					String pass = password.getText().toString();
    				filedoc = new File(coba);
    				gambar = new File(coba1);
    				File saveto = new File(simpan);
    					steganografi.encodeFile(gambar, saveto, filedoc, compress, pass);
					String data = steganografi.getMessage();
					Toast.makeText(encode.this, data, Toast.LENGTH_LONG).show();
					editload.setText("");
					editimage.setText("");
					editsimpan.setText("");
					password.setText("");
					
					
				}
			});
}


    public void getfile() {
    	Intent intent1 = new Intent (this, Filechoice.class);
    	startActivityForResult(intent1, REQUEST_PATH);
//		Intent intent2 = new Intent (this, Filechoice2.class); 
//		startActivityForResult(intent2, REQUEST_PATH);    
    }
    
    public void getimage() {
    	Intent intent2 = new Intent (this, Filechoice1.class);

    	
    	startActivityForResult(intent2, REQUEST_PATH);
    }
    
    public void getclose(){
    	Intent intent3 = new Intent (this,menu.class);
    	startActivityForResult(intent3, R.layout.menu);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		System.out.println(requestCode);
		if (requestCode == REQUEST_PATH){
			if(resultCode == RESULT_OK){
				
				if(data.getExtras().containsKey("GetFileName")){
					curFilename = data.getStringExtra("GetFileName");
					editload.setText(curFilename);
					
				}else if(data.getExtras().containsKey("GetFileName1")) {
					curFilename1 = data.getStringExtra("GetFileName1");
					editimage.setText(curFilename1);
					
				}
			}
		}
		}

		protected void onActivityResult1(int requestCode, int resultCode, Intent data) {//
			// TODO Auto-generated method stub
			if (requestCode == REQUEST_PATH){
				if (data.getExtras().containsKey("GetFileName")) {
					curFilename = data.getStringExtra("GetFileName");
					editload.setText(curFilename);
				}else if (data.getExtras().containsKey(" GetFileName1")) {
					curFilename1 = data.getStringExtra("GetFileName1");
					editimage.setText(curFilename1);
					}
					
				}
			}
	
	
} 