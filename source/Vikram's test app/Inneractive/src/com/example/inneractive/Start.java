package com.example.inneractive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);
				} catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openmainactivity = new Intent("com.example.inneractive.MAINACTIVITY");
					startActivity(openmainactivity);
				}
			}
		};
		timer.start();
		
	}

}
