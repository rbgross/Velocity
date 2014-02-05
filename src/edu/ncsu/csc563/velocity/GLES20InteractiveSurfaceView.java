package edu.ncsu.csc563.velocity;

import edu.ncsu.csc563.velocity.rendering.GLES20Renderer;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
    
    public static float angle;
	
	
    public GLES20InteractiveSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new GLES20Renderer(context));
        
        this.mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	//TODO Add touch input
    	return false;
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	@Override
	public void onSensorChanged(SensorEvent event) {		
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			this.mGravity = event.values;
			angle = this.mGravity[1];
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		this.mSensorManager.unregisterListener(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		this.mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}	
}