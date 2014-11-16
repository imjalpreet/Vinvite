package com.yourevent.mobilevideoinvitation;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.VideoView;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by raj on 29/10/14.
 */

public class ShareScreen2 extends Activity {

    ParseFile mVideo;
    String User;
    String s;
    String name;
    PopupWindow popupWindow;
    View popUpView;
    private File file;
    private VideoView videoView;
    private String videoFileName;
    private int count;
    private ImageButton img1;
    private ImageButton img2;
    private ImageButton img3;
    private ImageButton img4;
    private ImageButton img5;
    private ImageButton img6;
    private ImageButton img7;
    private ImageButton img8;
    private ImageButton img9;
    private ImageButton playVideo;
    private String[] apps = {"com.facebook.katana", "com.whatsapp", "com.google.android.gm", "com.twitter.android","com.google.android.apps.plus", "com.instagram.android", "com.viber.voip", "com.dropbox.android", "com.google.android.youtube"};
    public static int flag;

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

        Intent intent = getIntent();
        String message = intent.getStringExtra(PreviousInvitesFragment.EXTRA_MESSAGE);
        name = intent.getStringExtra(PreviousInvitesFragment.EXTRA_MESSAGE);
        Log.d("score", "Name :" + name + " s");


        ParseUser currentUser = ParseUser.getCurrentUser();
        User=currentUser.getObjectId();
        s = Environment.getExternalStorageDirectory() + "/YourEvents/" + User+ "/Saved/"+ name;

        // s = Environment.getExternalStorageDirectory() + "/YourEvents/" + User+ "/UnSaved/"+ videoFileName + ".mp4";
       /* String pathss = s;
        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(pathss,
                MediaStore.Images.Thumbnails.MINI_KIND);


        String rootss = Environment.getExternalStorageDirectory().toString();
        File myDirss = new File(rootss + "/YourEvents/" + User + "/Thumbnails/");
        if (!myDirss.exists()) {
            myDirss.mkdirs();
        }
        //  myDir.mkdirs();
        //  Random generator = new Random();
        //  int n = 10000;
        //  n = generator.nextInt(n);
        String fname = videoFileName +".jpg";
        File files = new File (myDirss, fname);
        if (files.exists ()) files.delete ();
        try {
            FileOutputStream outs = new FileOutputStream(files);
            thumb.compress(Bitmap.CompressFormat.JPEG, 90, outs);
            outs.flush();
            outs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
*/


        //    String s = Environment.getExternalStorageDirectory() + "/invitube/" + videoFileName + ".mp4";
        file = new File(s);
        videoView.setVideoPath(s); // setting the video path
        videoView.seekTo(100);     // setting the video thumbnail
        videoView.requestFocus();
        flag=0;
        playVideo = (ImageButton)findViewById(R.id.playVideoButton);
        playVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!videoView.isPlaying()) {
                    if(flag==0){
                        videoView.seekTo(0);
                    }
                    videoView.start();
                    playVideo.setVisibility(View.INVISIBLE);
                    videoView.setOnTouchListener(new View.OnTouchListener(){

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            videoView.pause();
                            flag=1;
                            playVideo.setImageResource(R.drawable.pause_video);
                            playVideo.setVisibility(View.VISIBLE);
                            return true;
                        }
                    });

                } else {
                    flag=1;
                    videoView.pause();
                    playVideo.setVisibility(View.VISIBLE);
                    playVideo.setImageResource(R.drawable.pause_video);
                }
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playVideo.setVisibility(View.VISIBLE);
                playVideo.setImageResource(R.drawable.play_video);
                flag=0;
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
                default:
                    break;
            }
            if(appInstalledOrNot(apps[i])){
                count++;
                if(count>4)
                    continue;
                setShareActivity(tmpimgbut, tmpid, apps[i], 0);
            }
        }
        if(count > 4){
            final ImageButton plus = (ImageButton) findViewById(R.id.ibPlus);
            plus.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plus.setVisibility(View.INVISIBLE);
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    popUpView = layoutInflater.inflate(R.layout.more, null);
                    popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ImageButton back = (ImageButton)popUpView.findViewById(R.id.ibBack);
                    back.setOnClickListener(new Button.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            plus.setVisibility(View.VISIBLE);
                        }
                    });

                    count = 4;
                    ImageButton temp = null;
                    int id = 0;
                    for (int i=4; i < apps.length; i++){
                        switch (count){
                            case 4: temp=img5;
                                id = R.id.imageButton5;
                                break;
                            case 5: temp=img6;
                                id=R.id.imageButton6;
                                break;
                            case 6: temp = img7;
                                id = R.id.imageButton7;
                                break;
                            case 7: temp = img8;
                                id = R.id.imageButton8;
                                break;
                            case 8: temp = img9;
                                id = R.id.imageButton9;
                                break;
                            default:
                                break;
                        }
                        if(appInstalledOrNot(apps[i])){
                            count++;
                            setShareActivity(temp, id, apps[i], 1);
                        }
                    }
                    popupWindow.showAsDropDown(plus,600,5);
                }
            });

        }
    }

    private void setShareActivity(ImageButton imgb, int imgbutid, final String uri, int choose){
        if(choose == 0) {
            imgb = (ImageButton) findViewById(imgbutid);
        }
        if (choose == 1){
            imgb = (ImageButton)popUpView.findViewById(imgbutid);
        }
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
              /*  ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] buf = new byte[1024];
                int n;
                assert fis != null;
                try {
                    while (-1 != (n = fis.read(buf)))
                        baos.write(buf, 0, n);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                File from = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/YourEvents/" + User+ "/UnSaved/" + videoFileName + ".mp4");
                File to = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/YourEvents/" + User+ "/Saved/" + videoFileName + ".mp4");
                from.renameTo(to);
                s = Environment.getExternalStorageDirectory() + "/YourEvents/" + User+ "/Saved/"+ videoFileName + ".mp4";
                videoView.setVideoPath(s);
                videoView.seekTo(100);


                byte[] videoBytes = baos.toByteArray(); //this is the video in bytes.
                mVideo = new ParseFile(videoFileName, videoBytes);
                mVideo.saveInBackground();
                ParseObject videoUpload = new ParseObject("Videos");
                ParseUser currentUser = ParseUser.getCurrentUser();
                videoUpload.put("VideoName", videoFileName);
                videoUpload.put("VideoFile", mVideo);
                videoUpload.put("created_by", currentUser);
                videoUpload.saveInBackground();*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Save) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buf = new byte[1024];
            int n;
            assert fis != null;
            try {
                while (-1 != (n = fis.read(buf)))
                    baos.write(buf, 0, n);
            } catch (IOException e) {
                e.printStackTrace();
            }
           /* File from = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/YourEvents/" + User+ "/UnSaved/" + videoFileName + ".mp4");
            File to = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/YourEvents/" + User+ "/Saved/" + videoFileName + ".mp4");
            from.renameTo(to);
            s = Environment.getExternalStorageDirectory() + "/YourEvents/" + User+ "/Saved/"+ videoFileName + ".mp4";
            videoView.setVideoPath(s);
            videoView.seekTo(100);
            byte[] videoBytes = baos.toByteArray(); //this is the video in bytes.
            mVideo = new ParseFile(videoFileName, videoBytes);
            mVideo.saveInBackground();
            ParseObject videoUpload = new ParseObject("Videos");
            videoUpload.put("VideoName", videoFileName);
            videoUpload.put("VideoFile", mVideo);
            videoUpload.saveInBackground();*/
        }
        return super.onOptionsItemSelected(item);
    }
}
