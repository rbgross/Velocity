package edu.ncsu.csc563.velocity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class WelcomeActivity extends Activity{
	
	private static final long DELAY = 3000;
    private boolean scheduled = false;
    private Timer splashTimer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        splashTimer = new Timer();
        splashTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
            	WelcomeActivity.this.finish();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
         }, DELAY);
       scheduled = true;

    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (scheduled)
            splashTimer.cancel();
        splashTimer.purge();
    }
}
