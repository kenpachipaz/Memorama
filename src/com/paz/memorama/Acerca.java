package com.paz.memorama;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Acerca extends ActionBarActivity {
	TextView tv1, tv2, tv3, tv4, tv5, tv6;
	public static boolean bandera=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acerca);
		
		bandera=true;
		tv1= (TextView) findViewById(R.id.textView1);
		tv2= (TextView) findViewById(R.id.textView2);
		tv3= (TextView) findViewById(R.id.textView3);
		tv4= (TextView) findViewById(R.id.textView4);
		tv5= (TextView) findViewById(R.id.textView5);
		tv6= (TextView) findViewById(R.id.textView6);
		
		Typeface fuente = Typeface.createFromAsset(getAssets(), "Fuentes/snow_for_santa.ttf");
		
		tv1.setTypeface(fuente);
		tv2.setTypeface(fuente);
		tv3.setTypeface(fuente);
		tv4.setTypeface(fuente);
		tv5.setTypeface(fuente);
		tv6.setTypeface(fuente);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acerca, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent= new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
