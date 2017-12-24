package com.example.stegano;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class splashscreen extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(7000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
							
						Intent i = new Intent(splashscreen.this, login.class);
						splashscreen.this.startActivity(i);
						splashscreen.this.finish();
					}
					//activity yang akan dijalankan setelah splashscreen selesai
					//Intent i = new Intent(splashscreen.this, menu.class);
					//splashscreen.this.startActivity(i);
					//splashscreen.this.finish();
					
				}
		};
		timer.start();
	}

}