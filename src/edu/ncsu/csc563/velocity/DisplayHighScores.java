package edu.ncsu.csc563.velocity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayHighScores extends Activity{

	TextView scr1,scr2,scr3;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);
        
        scr1 = (TextView)this.findViewById(R.id.scr1);
        scr2 = (TextView)this.findViewById(R.id.scr2);
        scr3 = (TextView)this.findViewById(R.id.scr3); 

        //set resource string values and update text views here
    }
}
