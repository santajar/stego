package com.example.stegano;

import java.io.File;
import com.example.explorer.Filechoice1;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class decode extends ListActivity {
	String file;
	String image2;
	private static final int REQUEST_PATH = 1;
    String curFileName;    
    EditText editimage2;
    EditText hasil;
	EditText password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decode);
		editimage2 = (EditText) findViewById(R.id.etload);
		password = (EditText) findViewById(R.id.password);
		//hasil =(EditText)findViewById(R.id.hasil);
		Button gambar = (Button) findViewById(R.id.btbrowseimage2);
		
		gambar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getfile();
				
			}
		});
		
		Button decode = (Button) findViewById(R.id.btdecode);
		decode.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("ShowToast") @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SteganoInformation steg;
				String gambar = editimage2.getText().toString();
				File loadto = new File(gambar);
				String pass = password.getText().toString();
				steg = new SteganoInformation(loadto);
				
	               steganografi.decodeFile(steg, false, pass);
	               String data = steganografi.getMessage();
	               Toast.makeText(decode.this, data, Toast.LENGTH_LONG).show();
					//hasil.setText(pesan);
				editimage2.setText("");
			}
		});
		
		Button close = (Button) findViewById(R.id.btclose2);
    	close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getclose();
			}
		});
       	

	}
	
	public void getfile(){
        Intent intent1 = new Intent(this, Filechoice1.class);
        startActivityForResult(intent1,REQUEST_PATH);
    }
	
    public void getclose(){
    	Intent intent2 = new Intent (this,menu.class);
    	startActivityForResult(intent2, R.layout.menu);
    }
    
 // Listen for results.
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // See which child activity is calling us back.
        if (requestCode == REQUEST_PATH){
                if (resultCode == RESULT_OK) {
                        curFileName = data.getStringExtra("GetFileName1");
                        editimage2.setText(curFileName);
                }
         }
    }
    
	}

