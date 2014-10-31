package com.yourevent.mobilevideoinvitation;


        import android.app.ActionBar;
        import android.app.Activity;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.media.MediaPlayer.OnCompletionListener;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.OnTouchListener;
        import android.widget.Button;
        import android.widget.MediaController;
        import android.widget.Toast;
        import android.widget.VideoView;

        import java.io.File;

public class Invite extends Activity{
    File file;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.invite);
        final VideoView videoView = (VideoView) this.findViewById(R.id.showVideo);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        String s;
        s = Environment.getExternalStorageDirectory() + "/newinvitation.mp4";
        file = new File(s);
        videoView.setVideoPath(s); // setting the video path
        videoView.seekTo(100);     // setting the video thumbnail
        videoView.requestFocus();
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoView.isPlaying()) {
                    videoView.seekTo(0);
                    videoView.start();
                } else {
                    videoView.pause();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.Save){
            /*Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            shareIntent.setType("video/mp4");
            startActivity(Intent.createChooser(shareIntent, "Send Invitation"));
        */
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
