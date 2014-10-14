package com.yourevent.mobilevideoinvitation;

/**
 * Created by raj on 9/10/14.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterEventDetails extends Activity {
    String[] events = {"Birthday", "Wedding", "Engagement", "Reception", "House Warming", "Anniversary"};
    Button contButton;
    public String event;
    public final static String EXTRA = "";
    EditText name;
    EditText description;
    EditText venue;
    EditText date;
    EditText time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        setTitle("Event Details");
        Intent intent = getIntent();
        String message = intent.getStringExtra(StaggeredGridActivityFragment.EXTRA_MESSAGE);
        event = intent.getStringExtra(StaggeredGridActivityFragment.EXTRA_MESSAGE);
        TextView t = (TextView)findViewById(R.id.eventName);
        t.setText(events[Integer.parseInt(message)]);
        contButton = (Button)findViewById(R.id.continueEventDetails);
        name = (EditText) findViewById(R.id.etName);
        description = (EditText) findViewById(R.id.etDescription);
        venue = (EditText) findViewById(R.id.etVenue);
        date = (EditText) findViewById(R.id.etDate);
        time = (EditText) findViewById(R.id.etTime);
        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent("android.intent.action.STAGGEREDGRIDACTIVITY");
            if (name.getText().toString().trim().length() == 0||description.getText().toString().trim().length() == 0||venue.getText().toString().trim().length() == 0||date.getText().toString().trim().length() == 0||time.getText().toString().trim().length() == 0) {
                Toast.makeText(EnterEventDetails.this, "Field cannot be Empty", Toast.LENGTH_SHORT).show();
            }
            else {
                i.putExtra(EXTRA, events[Integer.parseInt(event)] + " " + name.getText() + " " + description.getText() + " " + venue.getText() + " " + date.getText() + " " + time.getText());
                startActivity(i);
            }
            }
        });
    }
}
