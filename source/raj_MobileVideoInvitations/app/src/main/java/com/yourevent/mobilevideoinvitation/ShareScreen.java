package com.yourevent.mobilevideoinvitation;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    private int count;
    private ImageButton img1, img2, img3, img4, img5, img6, img7;
    private String[] apps = {"com.facebook.katana", "com.whatsapp", "com.google.android.gm", "com.twitter.android","com.google.android.apps.plus", "com.instagram.android", "com.viber.voip", "com.dropbox.android", "com.google.android.youtube"};
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

        count=0;
        ImageButton tmpimgbut=null;
        int tmpid=0;
        for(int i=0; i<apps.length ; ++i){
            switch(count){
                case 0: tmpimgbut=img1;
                        tmpid=R.id.imageButton1;
                    break;
                case 1: tmpimgbut=img2;
                        tmpid=R.id.imageButton2;
                    break;
                case 2: tmpimgbut=img3;
                        tmpid=R.id.imageButton3;
                    break;
                case 3: tmpimgbut=img4;
                    tmpid=R.id.imageButton4;
                    break;
                case 4: tmpimgbut=img5;
                    tmpid=R.id.imageButton5;
                    break;
                case 5: tmpimgbut=img6;
                    tmpid=R.id.imageButton6;
                    break;
                case 6: tmpimgbut=img7;
                    tmpid=R.id.imageButton7;
                    break;
                default:
                    break;
            }
            if(appInstalledOrNot(apps[i])){
                count++;
                setShareActivity(tmpimgbut, tmpid, apps[i]);
            }
            if(count==7)
                break;
        }    }
    private void setShareActivity(ImageButton imgb, int imgbutid, final String uri){
        imgb = (ImageButton)findViewById(imgbutid);
        PackageManager pm = getPackageManager();
        try {
            imgb.setImageDrawable(pm.getApplicationIcon(uri));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                shareIntent.setType("video/mp4");
                shareIntent.setPackage(uri);
                startActivity(shareIntent);
            }
        });
    }
    private boolean appInstalledOrNot(String uri)
    {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try
        {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            app_installed = false;
        }
        return app_installed ;
    }
}
