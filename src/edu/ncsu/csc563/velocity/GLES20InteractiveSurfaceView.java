package edu.ncsu.csc563.velocity;

import edu.ncsu.csc563.velocity.R;
import edu.ncsu.csc563.velocity.actors.Scene;
import edu.ncsu.csc563.velocity.rendering.GLES20Renderer;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * SurfaceView that manages an OpenGL ES 2.0 Renderer and can handle
 * touch and accelerometer events
 */
public class GLES20InteractiveSurfaceView extends GLSurfaceView implements SensorEventListener {
	//private SoundPool mSoundPool;
	private SensorManager mSensorManager;
	//private AudioManager mAudioManager;
    private Sensor mAccelerometer;    
    private float[] mGravity;
    //private int mSound = 0;
    //private float mVolume;
    
    private float baseRoll;
    private float baseTilt;
    
    public static float roll;
    public static float tilt;
	
    public GLES20InteractiveSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new GLES20Renderer(context));
        
        // Create a SoundPool and load sounds
        //this.mSoundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0);        
        //this.mSound = mSoundPool.load(context, R.raw.gamemusic,1);
        //this.mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        
        // Set volume and play sound
        //this.mVolume = (float) mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        this.mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        //mSoundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            //public void onLoadComplete(SoundPool mSoundPool, int sampleId,
                //int status) {
            	//if(mSound != 0)
            		//mSoundPool.play(mSound, mVolume, mVolume, 1, -1, 0);
            //}
          //});
        
        Scene.pause();        
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	switch (e.getAction()) {
        	case MotionEvent.ACTION_DOWN:
        		if (!Scene.isPaused()) {
            		Scene.pause();
            		//mSoundPool.autoPause();
            	} else {
            		this.mGravity = null;
            		Scene.unPause();
            		//mSoundPool.autoResume();
            	}
        		break;
    	}  	
    	
    	return true;
    }

	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	public void onSensorChanged(SensorEvent event) {		
		this.handleAccelerometer(event);				
	}
	
	private void handleAccelerometer(SensorEvent event) {		
		if (this.mGravity != null) {			
			roll = (float) Math.atan2(event.values[2], event.values[0]) - this.baseRoll;
			tilt = event.values[1] - this.baseTilt;
			
			if (Math.abs(roll) < 0.02f) {
				roll = 0;
			}
			
			if (Math.abs(tilt) < 0.14f) {
				tilt = 0;
			}
		} else {
			this.mGravity = event.values;
			
			this.baseRoll = (float) Math.atan2(this.mGravity[2], this.mGravity[0]);
			this.baseTilt = this.mGravity[1];
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		//mSoundPool.pause(mStream);
		this.mSensorManager.unregisterListener(this);
		Scene.pause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//mSoundPool.resume(mStream);
		this.mSensorManager.registerListener(this, this.mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
		this.mGravity = null;
	}	
}