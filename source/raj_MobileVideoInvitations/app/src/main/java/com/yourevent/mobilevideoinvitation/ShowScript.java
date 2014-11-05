package com.yourevent.mobilevideoinvitation;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by imjalpreet on 13-10-2014.
 */

public class ShowScript extends Activity {
    TextView ScriptType;
    EditText Script;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_script);
        ScriptType = (TextView) findViewById(R.id.ScriptType);
        Bundle extras = getIntent().getExtras();
        String eventName = extras.getString(StaggeredGridActivity.EVENT_NAME);
        String[] Data = eventName.split("_");
        setTitle(Data[1]);
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.hide();
        ScriptType.setText(Data[0]);
        Script = (EditText) findViewById(R.id.tvFinalScript);
        String script="";
        final String[] line={"Come and join us for some Birthday fun. \n", "It’s "+Data[2].split(" ")[0]+"’s Birthday!\n", "We will be waiting for you at "+Data[3]+" on "+Data[4]+" and "+Data[5]+". \n", "Do Join us for some family time!!"};
        for (String tmpline: line){
            script+=tmpline+"\n";
        }
        Script.setText(script);
        Button contBut = (Button)findViewById(R.id.continueFinalScript);
        contBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.intent.action.ANDROIDVIDEOCAPTURE");
                Bundle b=new Bundle();
                b.putStringArray("EXTRA_SCRIPT", line);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
