package edu.ncsu.csc563.velocity;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.opengl.GLSurfaceView;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
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

        this.mView = new GLES20InteractiveSurfaceView(this);
        
        RelativeLayout rl = new RelativeLayout(this);
        rl.addView(this.mView);        
        score = new TextView(this);
        score.setText("Score: "+mscore);
        score.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        // Set the font
        Typeface font = Typeface.createFromAsset(getAssets(), "xirod.ttf");
        score.setTypeface(font);
        score.setTextColor(Color.rgb(64, 192, 255));
        
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_TOP);
        score.setLayoutParams(lp);
		rl.addView(score);
        
        setContentView(rl);
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
