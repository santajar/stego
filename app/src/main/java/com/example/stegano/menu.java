package com.example.stegano;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
	}
    
	public void onclick(View view) {
		if (view.getId() == R.id.LayEncode)
			startActivity(new Intent(getApplicationContext(),encode.class));
		if (view.getId() == R.id.LayDecode)
			startActivity(new Intent(getApplicationContext(),decode.class));
		if (view.getId() == R.id.LayAbout)
			startActivity(new Intent(getApplicationContext(),about.class));
		if (view.getId() == R.id.LayHelp)
			startActivity(new Intent(getApplicationContext(),help.class));
		if (view.getId() == R.id.LayExit)
//			startActivity(new Intent(getApplicationContext(),login.class));
		{
			AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setMessage("Apakah Anda Benar-Benar ingin keluar?");
		ad.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// closeDialog.this.finish();
				Intent exit = new Intent(Intent.ACTION_MAIN);
				exit.addCategory(Intent.CATEGORY_HOME);
				exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(new Intent(getApplicationContext(),login.class));
			}
		});
		ad.setNegativeButton("Tidak",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		ad.show();
    }
}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        keluarDenganBackKey();
	        //moveTaskToBack(true);
	    }
	    return super.onKeyDown(keyCode, event);
	}

	protected void keluarDenganBackKey() {
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setMessage("Apakah Anda Benar-Benar ingin keluar?");
		ad.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// closeDialog.this.finish();
				Intent exit = new Intent(Intent.ACTION_MAIN);
				exit.addCategory(Intent.CATEGORY_HOME);
				exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(exit);
			}
		});
		ad.setNegativeButton("Tidak",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		ad.show();

	}
				
}
	