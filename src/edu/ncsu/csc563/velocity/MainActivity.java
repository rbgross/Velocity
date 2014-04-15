package edu.ncsu.csc563.velocity;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.opengl.GLSurfaceView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Activity class from which the Android app is launched
 */
public class MainActivity extends Activity {

	private GLSurfaceView mView;
	public static TextView score;
	public static int mscore = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Request a full screen window with no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create a GLES20InteractiveSurfaceView instance and set it as the ContentView for this Activity
        this.mView = new GLES20InteractiveSurfaceView(this);
        setContentView(this.mView);
        
        LinearLayout mLayout = new LinearLayout(this);
        score = new TextView(this);
        mLayout.addView(score);
        score.setText("Score: "+mscore);
        score.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        // Set the font
        Typeface font = Typeface.createFromAsset(getAssets(), "xirod.ttf");
        score.setTypeface(font);
        score.setTextColor(Color.RED);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        this.addContentView(mLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
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
