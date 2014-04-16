package edu.ncsu.csc563.velocity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class DisplayHighScores extends Activity{

	TextView scr1,scr2,scr3;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);
        
        scr1 = (TextView) this.findViewById(R.id.scr1);
        scr2 = (TextView) this.findViewById(R.id.scr2);
        scr3 = (TextView) this.findViewById(R.id.scr3); 

        //set resource string values and update text views here
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		int hs1 = sharedPref.getInt("hs1", 0);
		int hs2 = sharedPref.getInt("hs2", 0);
		int hs3 = sharedPref.getInt("hs3", 0);
		
		scr1.setText(hs1 + "");
		scr2.setText(hs2 + "");
		scr3.setText(hs3 + "");
    }
}
