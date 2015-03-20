package com.paz.memorama;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

public class SplashScreen extends ActionBarActivity{
	
	private static final long DELAY = 3000;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);	        
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.splash_screen);
	        
	        TimerTask timerTask= new TimerTask(){

				@Override
				public void run() {
					Intent intentPrincipal= new Intent(SplashScreen.this, MainActivity.class);
					startActivity(intentPrincipal);
					finish();					
				}	        	
	        };
	        Timer timer= new Timer();
	        timer.schedule(timerTask, DELAY);
	 }
	
}
