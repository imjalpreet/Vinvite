package com.yourevent.mobilevideoinvitation;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by raj on 29/10/14.
 */
public class ShareScreen extends Activity {

    private File file;
    private String s;
    private VideoView videoView;
    private String videoFileName;
    private Button shareButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Send Invitation");
        setContentView(R.layout.share_video);
        videoView = (VideoView) this.findViewById(R.id.showInvitation);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        Bundle extras = getIntent().getExtras();
        videoFileName = extras.getString(BackgroundScore.VIDEOFILENAME);
        s = Environment.getExternalStorageDirectory() + "/" + videoFileName + ".mp4";
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
        shareButton = (Button) findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                shareIntent.setType("video/mp4");
                startActivity(Intent.createChooser(shareIntent, "Send Invitation"));
            }
        });
    }
}
