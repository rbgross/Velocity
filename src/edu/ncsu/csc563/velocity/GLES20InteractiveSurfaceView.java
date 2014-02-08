package edu.ncsu.csc563.velocity;

import java.util.Arrays;

import edu.ncsu.csc563.velocity.rendering.GLES20Renderer;
import edu.ncsu.csc563.velocity.scene.Scene;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * SurfaceView that manages an OpenGL ES 2.0 Renderer and can handle
 * touch and accelerometer events
 */
public class GLES20InteractiveSurfaceView extends GLSurfaceView implements SensorEventListener {
	private SensorManager mSensorManager;
    private Sensor mAccelerometer;    
    private Sensor mMagneticField;
    private float[] mGravity;
    private float[] mGeomagnetic;
    
    public static float xAngle;
    public static float yAngle;
    public static float zAngle;
    
    public static float[] baseOrientation = new float[3];
	
    public GLES20InteractiveSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new GLES20Renderer(context));
        
        this.mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.mMagneticField = this.mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        
        this.mGravity = new float[3];
        this.mGeomagnetic = new float[3];
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	switch (e.getAction()) {
        	case MotionEvent.ACTION_DOWN:
        		if (!Scene.isPaused()) {
            		Scene.pause();
            	} else {
            		Scene.unPause();
            	}
        		break;
    	}  	
    	
    	return true;
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	@Override
	public void onSensorChanged(SensorEvent event) {		
		switch(event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				this.handleAccelerometer(event);
				break;
				
			case Sensor.TYPE_MAGNETIC_FIELD:
				this.handleMagneticField(event);
				break;
		}
		if (mGravity != null && mGeomagnetic != null) {
			float R[] = new float[9];
			float I[] = new float[9];
			boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
			if (success) {
				SensorManager.getOrientation(R, baseOrientation);
				Log.d("Orientation", Arrays.toString(baseOrientation));
			}
		}
	}
	
	private void handleAccelerometer(SensorEvent event) {		
		this.mGravity[0] = event.values[0];
		this.mGravity[1] = event.values[1];
		this.mGravity[2] = event.values[2];
		xAngle = this.mGravity[0];
		yAngle = this.mGravity[1];
		zAngle = this.mGravity[2];
		
		//Log.d("Accelerometer", this.mGravity[0] + ", " + this.mGravity[1] + ", " + this.mGravity[2]);
	}
	
	private void handleMagneticField(SensorEvent event) {
		this.mGeomagnetic[0] = event.values[0];
		this.mGeomagnetic[1] = event.values[1];
		this.mGeomagnetic[2] = event.values[2];
		//Log.d("Magnetic Field", String.valueOf(this.mGeomagnetic[0]) + ", " + String.valueOf(this.mGeomagnetic[1]) + ", " + String.valueOf(this.mGeomagnetic[2]));
	}
	
	@Override
	public void onPause() {
		super.onPause();
		this.mSensorManager.unregisterListener(this);
		this.mGravity = null;
		this.mGeomagnetic = null;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		this.mSensorManager.registerListener(this, this.mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		this.mSensorManager.registerListener(this, this.mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
	}	
}