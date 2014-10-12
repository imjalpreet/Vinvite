package com.exercise.AndroidVideoCapture;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class FormRead extends Activity {
    String[] events = {"Birthday", "Wedding", "Engagement", "Reception", "House Warming", "Anniversary"};
    Button ContinueButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_read);
        setTitle("                   Event Details");
  //      Intent intent = getIntent();
    //    String message = intent.getStringExtra(StaggeredGridActivityFragment.EXTRA_MESSAGE);
        TextView t = (TextView)findViewById(R.id.eventName);
        ContinueButton = (Button)findViewById(R.id.continueEventDetails);
        ContinueButton.setOnClickListener(ContinueButtonOnClickListener);
      //  t.setText(events[Integer.parseInt(message)]);
    }

    Button.OnClickListener ContinueButtonOnClickListener
    = new Button.OnClickListener(){
    
    	public void onClick(View v) {
    		
    		
    		Intent openvideocapture = new Intent("com.exercise.AndroidVideoCapture.ANDROIDVIDEOCAPTURE");
    		
			startActivity(openvideocapture);
    		
 
    	      
    	}
    };
    
    
    
    
}