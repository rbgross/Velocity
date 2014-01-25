package edu.ncsu.csc563.velocity;

import edu.ncsu.csc563.velocity.rendering.GLES20Renderer;
import android.content.Context;
import android.hardware.SensorEvent;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class GLES20InteractiveSurfaceView extends GLSurfaceView {

    public GLES20InteractiveSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new GLES20Renderer());
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	
    	switch(e.getAction()) {
    	
    	}
    	e.getX();
    	e.getY();
    	return true;
    	//TODO Add touch input
    }
    
    public void onSensorChanged(SensorEvent e) {
    	//TODO Add accelerometer input
    }
}

