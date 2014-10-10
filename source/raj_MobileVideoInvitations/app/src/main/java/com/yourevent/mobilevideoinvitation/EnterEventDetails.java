package com.yourevent.mobilevideoinvitation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by raj on 9/10/14.
 */
public class EnterEventDetails extends Activity {
    String[] events = {"Birthday", "Wedding", "Engagement", "Reception", "House Warming", "Anniversary"};
    Button contButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        setTitle("                   Event Details");
        Intent intent = getIntent();
        String message = intent.getStringExtra(StaggeredGridActivityFragment.EXTRA_MESSAGE);
        TextView t = (TextView)findViewById(R.id.eventName);
        t.setText(events[Integer.parseInt(message)]);
        contButton = (Button)findViewById(R.id.continueEventDetails);
        //contButton.setOnClickListener();
        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.intent.action.STAGGEREDGRIDACTIVITY");
                startActivity(i);
            }
        });
    }

}
