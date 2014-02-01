package edu.ncsu.csc563.velocity;

import edu.ncsu.csc563.velocity.rendering.GLES20Renderer;
import android.content.Context;
import android.hardware.SensorEvent;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * SurfaceView that manages an OpenGL ES 2.0 Renderer and can handle
 * touch and accelerometer events
 */
public class GLES20InteractiveSurfaceView extends GLSurfaceView {

    public GLES20InteractiveSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new GLES20Renderer(context));
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	//TODO Add touch input
    	return false;
    }
    
    public void onSensorChanged(SensorEvent e) {
    	//TODO Add accelerometer input
    }
}