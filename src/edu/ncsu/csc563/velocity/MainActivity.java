package edu.ncsu.csc563.velocity;

import android.os.Bundle;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.Window;
import android.view.WindowManager;

/**
 * Activity class from which the Android app is launched
 */
public class MainActivity extends Activity {

	private GLSurfaceView mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Request a full screen window with no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create a GLES20InteractiveSurfaceView instance and set it as the ContentView for this Activity
        this.mView = new GLES20InteractiveSurfaceView(this);
        setContentView(this.mView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mView.onResume();
    }
}
