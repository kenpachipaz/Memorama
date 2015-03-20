package com.paz.memorama;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Scores extends ActionBarActivity {
	AyudanteDB aDB;
	SQLiteDatabase db;
	TextView tvScores, tvIntentos, tvTituloScore, tvTituloIntentos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		Acerca.bandera=true;
		tvScores= (TextView) findViewById(R.id.tvTiempo);
		tvIntentos= (TextView) findViewById(R.id.tvIntentos);
		tvTituloIntentos= (TextView) findViewById(R.id.textView2);
		tvTituloScore= (TextView) findViewById(R.id.textView1);
		
		Typeface fuente= Typeface.createFromAsset(getAssets(), "Fuentes/snow_for_santa.ttf");
		
		tvScores.setTypeface(fuente);
		tvIntentos.setTypeface(fuente);
		tvTituloIntentos.setTypeface(fuente);
		tvTituloScore.setTypeface(fuente);
		
		consultar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scores, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i= new Intent(this, MainActivity.class);
			startActivity(i);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	public void consultar(){
		int cont=0;
		try{
			aDB= new AyudanteDB(this, "memorama", null, 1);
			db=aDB.getReadableDatabase();
			if(db != null){
				Cursor cursor=db.rawQuery("SELECT * FROM scores order by tiempo", null);
				while(cursor.moveToNext()){
					if(cont < 10){
						tvScores.append(cursor.getString(0)+"\n");
						tvIntentos.append(cursor.getString(1)+"\n");
					}
					cont++;
				}
			}
		}catch(SQLException ex){
			tvScores.setText("Error: "+ex.getMessage());
		}finally{
			if(db!=null)
				db.close();
		}
	}
}
