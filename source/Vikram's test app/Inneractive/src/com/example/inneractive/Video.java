package com.example.inneractive;




import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnTimedTextListener;
import android.media.MediaPlayer.TrackInfo;
import android.media.TimedText;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.VideoView;
import android.view.View.OnTouchListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.view.MotionEvent;
import android.view.View;


public class Video extends Activity implements OnTimedTextListener {
	private static final String TAG = "TimedTextTest";
	private TextView txtDisplay;
	private static Handler handler = new Handler();
	VideoView vid;
	MediaPlayer player;
	MediaController mc;
	SeekBar seekbar;
	AudioManager audioManager;
	int x,duration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		
		
		
		

		audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	     int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	     int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	     SeekBar volControl = (SeekBar)findViewById(R.id.seekbarvideo);
	     volControl.setMax(maxVolume);
	     volControl.setProgress(curVolume);
	     volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

	 @Override
	 public void onStopTrackingTouch(SeekBar arg0) {
	  // TODO Auto-generated method stub

	 }

	 @Override
	 public void onStartTrackingTouch(SeekBar arg0) {
	  // TODO Auto-generated method stub

	 }

	 @Override
	 public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
	  // TODO Auto-generated method stub
	  audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
	 }
	});
		
		
		
	//	seekbar=(SeekBar)findViewById(R.id.seekbarvideo);
		vid=(VideoView)findViewById(R.id.vidview);
		String urlpath= "android.resource://"  + getPackageName() +  "/" + R.raw.video ;
		vid.setVideoURI(Uri.parse(urlpath));
		

		
		
		  
		vid.requestFocus();
//		duration=vid.getDuration();
//		seekbar.setProgress(0);
 //       seekbar.setMax(100);
		vid.start();
		
		
		
		
		
		
	/*	vid.setOnPreparedListener(new OnPreparedListener() {

	        @Override
	        public void onPrepared(MediaPlayer mp) {

	            seekbar.setMax(vid.getDuration());
	            seekbar.postDelayed(onEverySecond, 1000);
	        }
	    });
		*/
		
	
		txtDisplay = (TextView) findViewById(R.id.txtDisplay);
		player = MediaPlayer.create(this, R.raw.video);
		try {
			player.addTimedTextSource(getSubtitleFile(R.raw.sub),
					MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP);
			int textTrackIndex = findTrackIndexFor(
					TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT, player.getTrackInfo());
			if (textTrackIndex >= 0) {
				player.selectTrack(textTrackIndex);
			} else {
				Log.w(TAG, "Cannot find text track!");
			}
			player.setOnTimedTextListener(this);
			player.setVolume(0,0);
			player.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
vid.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(vid.isPlaying())
                {
					vid.pause();
					player.pause();
					x=vid.getCurrentPosition();
                    
                }
                else
                {
                    vid.seekTo(x);
                    player.seekTo(x);
                    vid.start();
                    player.start();
                }
				return true;
			}
		});

		
	}

/*
	private Runnable onEverySecond=new Runnable() {

	    @Override
	    public void run() {

	        if(seekbar != null) {
	            seekbar.setProgress(vid.getCurrentPosition());
	        }

	        if(vid.isPlaying()) {
	            seekbar.postDelayed(onEverySecond, 1000);
	        }

	    }
	};
	*/
	public void onCheckboxClicked(View view) {
	    // Is the view now checked?
	    boolean checked = ((CheckBox) view).isChecked();
	    
	    // Check which checkbox was clicked
	    switch(view.getId()) {
	        case R.id.checkbox_meat:
	            if (checked)
	                // Put some meat on the sandwich
	            
	                // Remove the meat
	            break;
	        // TODO: Veggie sandwich
	    }
	}
	
	
	private int findTrackIndexFor(int mediaTrackType, TrackInfo[] trackInfo) {
		int index = -1;
		for (int i = 0; i < trackInfo.length; i++) {
			if (trackInfo[i].getTrackType() == mediaTrackType) {
				return i;
			}
		}
		return index;
	}

	private String getSubtitleFile(int resId) {
		String fileName = getResources().getResourceEntryName(resId);
		File subtitleFile = getFileStreamPath(fileName);
		if (subtitleFile.exists()) {
			Log.d(TAG, "Subtitle already exists");
			return subtitleFile.getAbsolutePath();
		}
		Log.d(TAG, "Subtitle does not exists, copy it from res/raw");

		// Copy the file from the res/raw folder to your app folder on the
		// device
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = getResources().openRawResource(resId);
			outputStream = new FileOutputStream(subtitleFile, false);
			copyFile(inputStream, outputStream);
			return subtitleFile.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStreams(inputStream, outputStream);
		}
		return "";
	}

	private void copyFile(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		final int BUFFER_SIZE = 1024;
		byte[] buffer = new byte[BUFFER_SIZE];
		int length = -1;
		while ((length = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, length);
		}
	}

	// A handy method I use to close all the streams
	private void closeStreams(Closeable... closeables) {
		if (closeables != null) {
			for (Closeable stream : closeables) {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void onTimedText(final MediaPlayer mp, final TimedText text) {
		if (text != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					int seconds = mp.getCurrentPosition() / 1000;

					txtDisplay.setText("[" + secondsToDuration(seconds) + "] "
							+ text.getText());
				}
			});
		}
	}

	// To display the seconds in the duration format 00:00:00
	public String secondsToDuration(int seconds) {
		return String.format("%02d:%02d:%02d", seconds / 3600,
				(seconds % 3600) / 60, (seconds % 60), Locale.US);
	}
}




