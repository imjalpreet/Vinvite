package com.yourevent.mobilevideoinvitation;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private int i;
    private int tmpi=0;
    String script="";

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
        final String[] line={"Come and join us for some Birthday fun. ", "It’s "+Data[2].split(" ")[0]+"’s Birthday!", "We will be waiting for you at "+Data[3]+" on "+Data[4]+" and "+Data[5]+". ", "Do Join us for some family time!!"};
        final String[] templine={"","","","","","","","","","","","",""};
        for (String tmpline: line){
            script+=tmpline+" \n\n ";
        }
        Script.setText(script);
        Button contBut = (Button)findViewById(R.id.continueFinalScript);
        contBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.ANDROIDVIDEOCAPTURE");
                script= String.valueOf(Script.getText());
                Log.d("script",script);
                final String[] words=script.split("\\s+");
                Bundle b=new Bundle();
                for(i = 0; i<words.length;i++){
                    if((i+1)%6==0){
                        tmpi++;
                        templine[tmpi]="";
                    }
                    templine[tmpi] += words[i] + " ";
                }

                b.putStringArray("EXTRA_SCRIPT", templine);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
