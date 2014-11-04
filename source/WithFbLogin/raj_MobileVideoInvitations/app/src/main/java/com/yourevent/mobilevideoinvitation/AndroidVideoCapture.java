package com.yourevent.mobilevideoinvitation;

/**
 * Created by raj on 14/10/14.
 */

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import android.R.string;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidVideoCapture extends Activity{
    public String filename;
    public String User;
    public static String FILENAME = "";
    private Camera myCamera;
    private MyCameraSurfaceView myCameraSurfaceView;
    private MediaRecorder mediaRecorder;
    TextView rec;
    boolean visible = true;
    Button myButton;
    SurfaceHolder surfaceHolder;
    boolean recording;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        recording = false;

        setContentView(R.layout.main);
        TextView rec=(TextView)findViewById(R.id.RecButton);

        Bundle b = getIntent().getExtras();
        User=b.getString("user");




        Toast.makeText(AndroidVideoCapture.this,
                "Hello " + User, Toast.LENGTH_LONG).show();

        myCamera = getCameraInstance();

        myCameraSurfaceView = new MyCameraSurfaceView(this, myCamera);
        FrameLayout myCameraPreview = (FrameLayout)findViewById(R.id.videoview);
        myCameraPreview.addView(myCameraSurfaceView);

        myButton = (Button)findViewById(R.id.mybutton);
        myButton.setOnClickListener(myButtonOnClickListener);
    }

    Button.OnClickListener myButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            TextView rec=(TextView)findViewById(R.id.RecButton);
            rec.setVisibility(View.VISIBLE);



            if(recording){
                // stop recording and release camera
                mediaRecorder.stop();  // stop the recording
                releaseMediaRecorder(); // release the MediaRecorder object
                Intent openinvite = new Intent("android.intent.action.BACKGROUNDSCORE");
                openinvite.putExtra(FILENAME,filename);
                startActivity(openinvite);
            }else{
                releaseCamera();
                if(!prepareMediaRecorder()){
                    Toast.makeText(AndroidVideoCapture.this,
                            "Fail in prepareMediaRecorder()!\n - Ended -",
                            Toast.LENGTH_LONG).show();
                    finish();
                }

                mediaRecorder.start();
                recording = true;
                myButton.setText("STOP");
                myButton.setBackgroundResource(R.drawable.round_button_stop);
            }
        }};

    private Camera getCameraInstance(){
        int cameraCount = 0;
        int waste=0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cam = Camera.open(camIdx);
                cam.setDisplayOrientation(90);
            }
        }
        return cam;

    }

    private boolean prepareMediaRecorder(){
        myCamera = getCameraInstance();
        mediaRecorder = new MediaRecorder();
        myCamera.unlock();
        mediaRecorder.setCamera(myCamera);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOrientationHint(270);
        mediaRecorder.setProfile(CamcorderProfile.get(1,CamcorderProfile.QUALITY_HIGH));
        String s;
        Calendar c = Calendar.getInstance();
        filename = getAppropriateString(c.get(Calendar.YEAR))+getAppropriateString(c.get(Calendar.MONTH)+1)+getAppropriateString(c.get(Calendar.DATE))+"_"+getAppropriateString(c.get(Calendar.HOUR))+getAppropriateString(c.get(Calendar.MINUTE))+getAppropriateString(c.get(Calendar.SECOND));//Integer.toString(c.get(Calendar.YEAR))+Integer.toString(c.get(Calendar.MONTH)+1)+Integer.toString(c.get(Calendar.DATE))+Integer.toString(c.get(Calendar.HOUR))+Integer.toString(c.get(Calendar.MINUTE))+Integer.toString(c.get(Calendar.SECOND));
        s=Environment.getExternalStorageDirectory() + "/YourEvents/" + User+ "/UnSaved/"+ filename + ".mp4" ;
        mediaRecorder.setOutputFile(s);
        mediaRecorder.setMaxDuration(30000); // Set max duration 30 sec.
        mediaRecorder.setMaxFileSize(16000000); // Set max file size 16M

        try {
            mediaRecorder.setPreviewDisplay(myCameraSurfaceView.getHolder().getSurface());
            mediaRecorder.prepare();
            //mediaRecorder.start();
        } catch (IllegalStateException e) {
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            releaseMediaRecorder();
            return false;
        }
        return true;

    }

    // Converts a single digit integer to a double digit integer by appending 0 in front
    // and then converting this number to a string
    public String getAppropriateString(int input){
        String tmp = "0";
        String str = Integer.toString(input);
        if (str.length() == 1)
            tmp += str;
        else
            tmp = str;
        return tmp;
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
        releaseCamera();              // release the camera immediately on pause event
    }

    private void releaseMediaRecorder(){
        if (mediaRecorder != null) {
            mediaRecorder.reset();   // clear recorder configuration
            mediaRecorder.release(); // release the recorder object
            mediaRecorder = null;
            myCamera.lock();           // lock camera for later use
        }
    }

    private void releaseCamera(){
        if (myCamera != null){
            myCamera.release();        // release the camera for other applications
            myCamera = null;
        }
    }

    public class MyCameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

        private SurfaceHolder mHolder;
        private Camera mCamera;

        public MyCameraSurfaceView(Context context, Camera camera) {
            super(context);
            mCamera = camera;

            // Install a SurfaceHolder.Callback so we get notified when the
            // underlying surface is created and destroyed.
            mHolder = getHolder();
            mHolder.addCallback(this);
            // deprecated setting, but required on Android versions prior to 3.0
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int weight,
                                   int height) {
            // If your preview can change or rotate, take care of those events here.
            // Make sure to stop the preview before resizing or reformatting it.

            if (mHolder.getSurface() == null){
                // preview surface does not exist
                return;
            }

            // stop preview before making changes
            try {
                mCamera.stopPreview();
            } catch (Exception e){
                // ignore: tried to stop a non-existent preview
            }

            // make any resize, rotate or reformatting changes here

            // start preview with new settings
            try {
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();

            } catch (Exception e){
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            // The Surface has been created, now tell the camera where to draw the preview.
            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            } catch (IOException e) {
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO Auto-generated method stub

        }

 }
}