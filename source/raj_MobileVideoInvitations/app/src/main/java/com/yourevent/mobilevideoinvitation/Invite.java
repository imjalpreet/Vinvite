package com.yourevent.mobilevideoinvitation;


        import android.app.Activity;
        import android.media.MediaPlayer;
        import android.media.MediaPlayer.OnCompletionListener;
        import android.os.Bundle;
        import android.os.Environment;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.OnTouchListener;
        import android.widget.Button;
        import android.widget.MediaController;
        import android.widget.Toast;
        import android.widget.VideoView;

public class Invite extends Activity{
    VideoView videoView;
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
        videoView.setVideoPath(s); // setting the video path
        videoView.seekTo(100);     // setting the video thumbnail
        videoView.requestFocus();
        videoView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!videoView.isPlaying())
                {
                    videoView.seekTo(0);
                    videoView.start();

                }
                return true;

            }
        });
    }
}
