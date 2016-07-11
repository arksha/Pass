package com.example.pass;

import java.io.IOException;
import java.util.HashMap;



import android.R.integer;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import com.example.pass.CharView;
public class SoundManager {
 
private  SoundPool mSoundPool;
private MediaPlayer mediaPlayer;
private  HashMap<Integer, Integer> mSoundPoolMap;
private HashMap<Integer, MediaPlayer> mediamap;
private  AudioManager  mAudioManager;
private  Context mContext;
private CharView cv;
	
	public void initSounds(Context theContext) {
	    mContext = theContext;
	    mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
	    mSoundPoolMap = new HashMap<Integer, Integer>();
	    mediaPlayer = new MediaPlayer();
	    mediamap = new HashMap<Integer, MediaPlayer>() ;
	   
	    mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
	}
	
	public void addSound(int index, AssetFileDescriptor afd)//int SoundID)
	{
	    mSoundPoolMap.put(index, mSoundPool.load(afd, 1));//.load(mContext, SoundID, 1));
	   
	}
	public void addBgm(int index, AssetFileDescriptor afd){
		
		 try {
			mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 mediamap.put(index,mediaPlayer);
	}
	
	
	public void playSound(int index)
	{
		if(cv.soundebtstate)
		{
			
			//Log.v("rob", streamVolume+" "+mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
			float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//			streamVolume = streamVolume;//MyGameView.volume;//streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//			Log.v("rob", streamVolume+""+MyGameView.volume);
		    mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);
	    }
	}
	public void playBgm(int index) {
	if(cv.musicbtstate){
		try {
			mediamap.get(index).prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediamap.get(index).start();
	}
	}
	public void  pauseBgm(int index) {
		mediamap.get(index).pause();
	}
	public void stopBgm(int index) {
		mediamap.get(index).stop();
	}
	 
	public void playLoopedSound(int index)
	{
	    float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	    streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	    mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, -1, 1f);
}

}