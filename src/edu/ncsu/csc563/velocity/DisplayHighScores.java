package edu.ncsu.csc563.velocity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.TextView;

public class DisplayHighScores extends Activity{

	TextView scr1,scr2,scr3,hstitle;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);
        
        hstitle = (TextView) this.findViewById(R.id.hstitle);
        scr1 = (TextView) this.findViewById(R.id.scr1);
        scr2 = (TextView) this.findViewById(R.id.scr2);
        scr3 = (TextView) this.findViewById(R.id.scr3); 

        //set resource string values and update text views here
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		int hs1 = sharedPref.getInt("hs1", 0);
		int hs2 = sharedPref.getInt("hs2", 0);
		int hs3 = sharedPref.getInt("hs3", 0);
		
		hstitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		scr1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		scr2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		scr3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        // Set the font
        Typeface font = Typeface.createFromAsset(getAssets(), "xirod.ttf");
        hstitle.setTypeface(font);
        
		scr1.setText(hs1 + "");
		scr2.setText(hs2 + "");
		scr3.setText(hs3 + "");
    }
	
	@Override
    public boolean onTouchEvent(MotionEvent e) {
    	switch (e.getAction()) {
        	case MotionEvent.ACTION_DOWN:
        		Intent intent = new Intent(this, MainActivity.class);
				this.startActivity(intent);
        		break;
    	}  	
    	
    	return true;
    }
}
