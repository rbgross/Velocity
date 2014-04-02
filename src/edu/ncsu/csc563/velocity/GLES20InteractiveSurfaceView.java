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
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * SurfaceView that manages an OpenGL ES 2.0 Renderer and can handle
 * touch and accelerometer events
 */
public class GLES20InteractiveSurfaceView extends GLSurfaceView implements SensorEventListener {
	private SensorManager mSensorManager;
    private Sensor mAccelerometer;    
    private float[] mGravity;
    
    private MediaPlayer mp;
    
    private float baseRoll;
    private float baseTilt;
	public SoundPool sp;
	public int endSound;
    
    public static float roll;
    public static float tilt;
	
    public GLES20InteractiveSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new GLES20Renderer(context));
        
        this.mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        endSound = sp.load(context, R.raw.turrible, 1);
        
        this.mp = MediaPlayer.create(context, R.raw.nights);
        this.mp.setLooping(true);
        
        Scene.pause();        
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	switch (e.getAction()) {
        	case MotionEvent.ACTION_DOWN:
        		if (Scene.isGameOver()) {
        			Scene.resetGame();
        			this.mp.pause();
        			this.mp.seekTo(0);
        			sp.play(endSound, 1, 1, 0, 0, 1);
        		} else {	        		
	        		if (!Scene.isPaused()) {
	            		Scene.pause();
	                    this.mp.pause();
	            	} else {
	            		this.mGravity = null;
	            		Scene.unPause();
	            		this.mp.start();
	            	}
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
		
		this.mSensorManager.unregisterListener(this);
		Scene.pause();
		this.mp.pause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		this.mSensorManager.registerListener(this, this.mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
		this.mGravity = null;
	}	
}