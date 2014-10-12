package com.exercise.AndroidVideoCapture;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.VideoView;

public class Invite extends Activity{
	
	   
    VideoView videoView;
    	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite);
        
        final VideoView videoView = (VideoView)this.findViewById(R.id.videoview);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        String s;
	    
 	   s=Environment.getExternalStorageDirectory()+"/Downloads/" ;
        videoView.setVideoPath(s);
        videoView.seekTo(100);           //
        
        videoView.requestFocus();
       // videoView.start();
        videoView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				videoView.seekTo(0);
				videoView.start();
				return true;
				
			}
		});
        
     }        
    
    
    
}
    
 