package com.paz.memorama;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	MediaPlayer mp;
	private ImageButton bActivado,bCompara,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,
				b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,
				b21,b22,b23,b24,b25,b26,b27,b28,b29,b30;
	private Button reiniciar;//btnPausar, 
	private final Handler handler= new Handler();
	private TextView tiempo, intentos, noIntentos, tvTiempo;
	private int[] imagenes= {R.drawable.img01,R.drawable.img02,R.drawable.img03,
					 R.drawable.img04,R.drawable.img05,R.drawable.img06,
					 R.drawable.img07,R.drawable.img08,R.drawable.img09,
					 R.drawable.img10,R.drawable.img11,R.drawable.img12,
					 R.drawable.img13,R.drawable.img14,R.drawable.img15};
	private int contador=0, imagenID, imagenIDCompara, parejas=0;
	private int nDigitos[]= new int[30];
	private boolean seleccion=false,bandera=false;// primera=true;
	//private ImageButton[] botones, yaEmparejados= new ImageButton[30];
	private Resources recursos;
	private Hilo hilo;
	private AyudanteDB aDB;
	private SQLiteDatabase db;
	private Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);	
		mp= MediaPlayer.create(getApplicationContext(), R.raw.pop);
		if(Acerca.bandera)
			finish();
		/*Obteniendo los recursos*/
		recursos= getResources();
		/*Instacia de los ImageButton*/
		b1= (ImageButton) findViewById(R.id.imageButton1);
		b2= (ImageButton) findViewById(R.id.imageButton2);
		b3= (ImageButton) findViewById(R.id.imageButton3);
		b4= (ImageButton) findViewById(R.id.imageButton4);
		b5= (ImageButton) findViewById(R.id.imageButton5);
		b6= (ImageButton) findViewById(R.id.imageButton6);
		b7= (ImageButton) findViewById(R.id.imageButton7);
		b8= (ImageButton) findViewById(R.id.imageButton8);
		b9= (ImageButton) findViewById(R.id.imageButton9);
		b10= (ImageButton) findViewById(R.id.imageButton10);
		b11= (ImageButton) findViewById(R.id.imageButton11);
		b12= (ImageButton) findViewById(R.id.imageButton12);
		b13= (ImageButton) findViewById(R.id.imageButton13);
		b14= (ImageButton) findViewById(R.id.imageButton14);
		b15= (ImageButton) findViewById(R.id.imageButton15);
		b16= (ImageButton) findViewById(R.id.imageButton16);
		b17= (ImageButton) findViewById(R.id.imageButton17);
		b18= (ImageButton) findViewById(R.id.imageButton18);
		b19= (ImageButton) findViewById(R.id.imageButton19);
		b20= (ImageButton) findViewById(R.id.imageButton20);
		b21= (ImageButton) findViewById(R.id.imageButton21);
		b22= (ImageButton) findViewById(R.id.imageButton22);
		b23= (ImageButton) findViewById(R.id.imageButton23);
		b24= (ImageButton) findViewById(R.id.imageButton24);
		b25= (ImageButton) findViewById(R.id.imageButton25);
		b26= (ImageButton) findViewById(R.id.imageButton26);
		b27= (ImageButton) findViewById(R.id.imageButton27);
		b28= (ImageButton) findViewById(R.id.imageButton28);
		b29= (ImageButton) findViewById(R.id.imageButton29);
		b30= (ImageButton) findViewById(R.id.imageButton30);
		
		//botones=new ImageButton[]{b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15,
			//					  b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30};
	//	btnPausar= (Button) findViewById(R.id.pausar);
		reiniciar= (Button) findViewById(R.id.reiniciar);
		
		
		intentos= (TextView) findViewById(R.id.textView1);
		tiempo= (TextView) findViewById(R.id.textView2);
		noIntentos= (TextView) findViewById(R.id.textView3);
		tvTiempo= (TextView) findViewById(R.id.textView4);
		
		hilo= new Hilo(tvTiempo);
		
		Typeface fuente = Typeface.createFromAsset(getAssets(), "Fuentes/snow_for_santa.ttf");
		
		/*Poner fuente a los elementos*/
		//btnPausar.setTypeface(fuente);
		reiniciar.setTypeface(fuente);
		intentos.setTypeface(fuente);
		tiempo.setTypeface(fuente);
		tvTiempo.setTypeface(fuente);
		noIntentos.setTypeface(fuente);
		
		/*Agregar escuchador a los ImageButton*/
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		b10.setOnClickListener(this);
		b11.setOnClickListener(this);
		b12.setOnClickListener(this);
		b13.setOnClickListener(this);
		b14.setOnClickListener(this);
		b15.setOnClickListener(this);
		b16.setOnClickListener(this);
		b17.setOnClickListener(this);
		b18.setOnClickListener(this);
		b19.setOnClickListener(this);
		b20.setOnClickListener(this);
		b21.setOnClickListener(this);
		b22.setOnClickListener(this);
		b23.setOnClickListener(this);
		b24.setOnClickListener(this);
		b25.setOnClickListener(this);
		b26.setOnClickListener(this);
		b27.setOnClickListener(this);
		b28.setOnClickListener(this);
		b29.setOnClickListener(this);
		b30.setOnClickListener(this);
		
		//btnPausar.setOnClickListener(this);
		reiniciar.setOnClickListener(this);
	
		ordenar(nDigitos);
		
		pausar();
		
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.salir:
				Acerca.bandera=false;
				finish();
				break;
			case R.id.acerca:
				i= new Intent(this, Acerca.class);
				startActivity(i);
				onPause();
				break;
			case R.id.scores:
				i= new Intent(this, Scores.class);
				startActivity(i);
				onPause();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause() {
		hilo.setPausado(true);
		super.onPause();
	}

	@Override
	protected void onResume() {
		hilo.setPausado(false);
		super.onResume();
	}


	@Override
	protected void onDestroy() {
		finish();
		super.onDestroy();
	}

	/*Método que llena un arreglo aleatorio para el llamado de las imágenes*/
	public void ordenar(int nDigitos[]){
		int max=0, nuevo=0, aux=0;
		Random r= new Random();
				
		do{
			nuevo = r.nextInt(30);		
		     for(int i = 0; i < max; i++){
	            if(nuevo == nDigitos[i])
		          aux = 1;
			}		
			if(aux == 0){		
				nDigitos[max] = nuevo;
				max++;
			}		
			aux = 0;
		}while(max < 30); 
		
		for(int i=0; i< max; i++ ){
			switch(nDigitos[i]){
				case 15:
					nDigitos[i]=0;
				break;
				case 16:
					nDigitos[i]=1;
				break;
				case 17:
					nDigitos[i]=2;
				break;
				case 18:
					nDigitos[i]=3;
				break;
				case 19:
					nDigitos[i]=4;
				break;
				case 20:
					nDigitos[i]=5;
				break;
				case 21:
					nDigitos[i]=6;
				break;
				case 22:
					nDigitos[i]=7;
				break;
				case 23:
					nDigitos[i]=8;
				break;
				case 24:
					nDigitos[i]=9;
				break;
				case 25:
					nDigitos[i]=10;
				break;
				case 26:
					nDigitos[i]=11;
				break;
				case 27:
					nDigitos[i]=12;
				break;
				case 28:
					nDigitos[i]=13;
				break;
				case 29:
					nDigitos[i]=14;
				break;				
			}
		}
				
			
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.imageButton1:
				if(!seleccion){
					b1.setImageResource(imagenes[nDigitos[0]]);
					bActivado=b1;
					imagenID=nDigitos[0];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b1.setImageResource(imagenes[nDigitos[0]]);
					bCompara=b1;
					imagenIDCompara=nDigitos[0];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton2:
				if(!seleccion){
					b2.setImageResource(imagenes[nDigitos[1]]);
					bActivado=b2;
					imagenID=nDigitos[1];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b2.setImageResource(imagenes[nDigitos[1]]);
					bCompara=b2;
					imagenIDCompara=nDigitos[1];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton3:
				if(!seleccion){
					b3.setImageResource(imagenes[nDigitos[2]]);
					bActivado=b3;
					imagenID=nDigitos[2];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b3.setImageResource(imagenes[nDigitos[2]]);
					bCompara=b3;
					imagenIDCompara=nDigitos[2];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton4:
				if(!seleccion){
					b4.setImageResource(imagenes[nDigitos[3]]);
					bActivado=b4;
					imagenID=nDigitos[3];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b4.setImageResource(imagenes[nDigitos[3]]);
					bCompara=b4;
					imagenIDCompara=nDigitos[3];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton5:
				if(!seleccion){
					b5.setImageResource(imagenes[nDigitos[4]]);
					bActivado=b5;
					imagenID=nDigitos[4];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b5.setImageResource(imagenes[nDigitos[4]]);
					bCompara=b5;
					imagenIDCompara=nDigitos[4];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton6:
				if(!seleccion){
					b6.setImageResource(imagenes[nDigitos[5]]);
					bActivado=b6;
					imagenID=nDigitos[5];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b6.setImageResource(imagenes[nDigitos[5]]);
					bCompara=b6;
					imagenIDCompara=nDigitos[5];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton7:
				if(!seleccion){
					b7.setImageResource(imagenes[nDigitos[6]]);
					bActivado=b7;
					imagenID=nDigitos[6];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b7.setImageResource(imagenes[nDigitos[6]]);
					bCompara=b7;
					imagenIDCompara=nDigitos[6];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton8:
				if(!seleccion){
					b8.setImageResource(imagenes[nDigitos[7]]);
					bActivado=b8;
					imagenID=nDigitos[7];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b8.setImageResource(imagenes[nDigitos[7]]);
					bCompara=b8;
					imagenIDCompara=nDigitos[7];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton9:
				if(!seleccion){
					b9.setImageResource(imagenes[nDigitos[8]]);
					bActivado=b9;
					imagenID=nDigitos[8];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b9.setImageResource(imagenes[nDigitos[8]]);
					bCompara=b9;
					imagenIDCompara=nDigitos[8];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton10:
				if(!seleccion){
					b10.setImageResource(imagenes[nDigitos[9]]);
					bActivado=b10;
					imagenID=nDigitos[9];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b10.setImageResource(imagenes[nDigitos[9]]);
					bCompara=b10;
					imagenIDCompara=nDigitos[9];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton11:
				if(!seleccion){
					b11.setImageResource(imagenes[nDigitos[10]]);
					bActivado=b11;
					imagenID=nDigitos[10];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b11.setImageResource(imagenes[nDigitos[10]]);
					bCompara=b11;
					imagenIDCompara=nDigitos[10];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton12:
				if(!seleccion){
					b12.setImageResource(imagenes[nDigitos[11]]);
					bActivado=b12;
					imagenID=nDigitos[11];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b12.setImageResource(imagenes[nDigitos[11]]);
					bCompara=b12;
					imagenIDCompara=nDigitos[11];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton13:
				if(!seleccion){
					b13.setImageResource(imagenes[nDigitos[12]]);
					bActivado=b13;
					imagenID=nDigitos[12];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b13.setImageResource(imagenes[nDigitos[12]]);
					bCompara=b13;
					imagenIDCompara=nDigitos[12];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton14:
				if(!seleccion){
					b14.setImageResource(imagenes[nDigitos[13]]);
					bActivado=b14;
					imagenID=nDigitos[13];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b14.setImageResource(imagenes[nDigitos[13]]);
					bCompara=b14;
					imagenIDCompara=nDigitos[13];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton15:
				if(!seleccion){
					b15.setImageResource(imagenes[nDigitos[14]]);
					bActivado=b15;
					imagenID=nDigitos[14];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b15.setImageResource(imagenes[nDigitos[14]]);
					bCompara=b15;
					imagenIDCompara=nDigitos[14];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton16:
				if(!seleccion){
					b16.setImageResource(imagenes[nDigitos[15]]);
					bActivado=b16;
					imagenID=nDigitos[15];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b16.setImageResource(imagenes[nDigitos[15]]);
					bCompara=b16;
					imagenIDCompara=nDigitos[15];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton17:
				if(!seleccion){
					b17.setImageResource(imagenes[nDigitos[16]]);
					bActivado=b17;
					imagenID=nDigitos[16];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b17.setImageResource(imagenes[nDigitos[16]]);
					bCompara=b17;
					imagenIDCompara=nDigitos[16];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton18:
				if(!seleccion){
					b18.setImageResource(imagenes[nDigitos[17]]);
					bActivado=b18;
					imagenID=nDigitos[17];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b18.setImageResource(imagenes[nDigitos[17]]);
					bCompara=b18;
					imagenIDCompara=nDigitos[17];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton19:
				if(!seleccion){
					b19.setImageResource(imagenes[nDigitos[18]]);
					bActivado=b19;
					imagenID=nDigitos[18];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b19.setImageResource(imagenes[nDigitos[18]]);
					bCompara=b19;
					imagenIDCompara=nDigitos[18];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton20:
				if(!seleccion){
					b20.setImageResource(imagenes[nDigitos[19]]);
					bActivado=b20;
					imagenID=nDigitos[19];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b20.setImageResource(imagenes[nDigitos[19]]);
					bCompara=b20;
					imagenIDCompara=nDigitos[19];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton21:
				if(!seleccion){
					b21.setImageResource(imagenes[nDigitos[20]]);
					bActivado=b21;
					imagenID=nDigitos[20];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b21.setImageResource(imagenes[nDigitos[20]]);
					bCompara=b21;
					imagenIDCompara=nDigitos[20];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton22:
				if(!seleccion){
					b22.setImageResource(imagenes[nDigitos[21]]);
					bActivado=b22;
					imagenID=nDigitos[21];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b22.setImageResource(imagenes[nDigitos[21]]);
					bCompara=b22;
					imagenIDCompara=nDigitos[21];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton23:
				if(!seleccion){
					b23.setImageResource(imagenes[nDigitos[22]]);
					bActivado=b23;
					imagenID=nDigitos[22];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b23.setImageResource(imagenes[nDigitos[22]]);
					bCompara=b23;
					imagenIDCompara=nDigitos[22];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton24:
				if(!seleccion){
					b24.setImageResource(imagenes[nDigitos[23]]);
					bActivado=b24;
					imagenID=nDigitos[23];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b24.setImageResource(imagenes[nDigitos[23]]);
					bCompara=b24;
					imagenIDCompara=nDigitos[23];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton25:
				if(!seleccion){
					b25.setImageResource(imagenes[nDigitos[24]]);
					bActivado=b25;
					imagenID=nDigitos[24];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b25.setImageResource(imagenes[nDigitos[24]]);
					bCompara=b25;
					imagenIDCompara=nDigitos[24];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton26:
				if(!seleccion){
					b26.setImageResource(imagenes[nDigitos[25]]);
					bActivado=b26;
					imagenID=nDigitos[5];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b26.setImageResource(imagenes[nDigitos[25]]);
					bCompara=b26;
					imagenIDCompara=nDigitos[25];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton27:
				if(!seleccion){
					b27.setImageResource(imagenes[nDigitos[26]]);
					bActivado=b27;
					imagenID=nDigitos[26];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b27.setImageResource(imagenes[nDigitos[26]]);
					bCompara=b27;
					imagenIDCompara=nDigitos[26];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton28:
				if(!seleccion){
					b28.setImageResource(imagenes[nDigitos[27]]);
					bActivado=b28;
					imagenID=nDigitos[27];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b28.setImageResource(imagenes[nDigitos[27]]);
					bCompara=b28;
					imagenIDCompara=nDigitos[27];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			case R.id.imageButton29:
				if(!seleccion){
					b29.setImageResource(imagenes[nDigitos[28]]);
					bActivado=b29;
					imagenID=nDigitos[28];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b29.setImageResource(imagenes[nDigitos[28]]);
					bCompara=b29;
					imagenIDCompara=nDigitos[28];
					bCompara.setEnabled(false);
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
					
				}
			break;
			case R.id.imageButton30:
				if(!seleccion){
					b30.setImageResource(imagenes[nDigitos[29]]);
					bActivado=b30;
					imagenID=nDigitos[29];
					seleccion=true;
					bActivado.setEnabled(false);
				}else{
					b30.setImageResource(imagenes[nDigitos[29]]);					
					bCompara=b30;
					bCompara.setEnabled(false);
					imagenIDCompara=nDigitos[29];
					empareja(imagenID, imagenIDCompara);
					noIntentos.setText(contador+ "");
				}
			break;
			/*case R.id.pausar:
				if(btnPausar.getText().equals(recursos.getString(R.string.jugar))){
					bandera=true;
					continuar();
					btnPausar.setText(recursos.getString(R.string.pausar));
				}else if(btnPausar.getText().equals(recursos.getString(R.string.pausar))){
					pausar();
					btnPausar.setText(recursos.getString(R.string.continuar));
				}else{
					continuar();
					btnPausar.setText(recursos.getString(R.string.pausar));
				}
					
			break;*/
			case R.id.reiniciar:
				if(reiniciar.getText().equals(recursos.getString(R.string.jugar))){
					bandera=true;
					continuar();
					reiniciar.setEnabled(false);
					reiniciar.setText(recursos.getString(R.string.reiniciar));
				}else{
					reiniciarJuego();
					hilo.setCentesimas(0);
					hilo.setSegundos(0);
					hilo.setMinutos(0);
					tvTiempo.setText("00:00");
					noIntentos.setText("0");	
					hilo.setPausado(false);
					reiniciar.setEnabled(false);
				}
			break;
		}
	}
	public void habilitaBotones(ImageButton bA, ImageButton bC){
		bActivado.setEnabled(true);
		bCompara.setEnabled(true);
	}
	public void empareja(final int imagenUno, final int imagenDos){
				if(imagenUno == imagenDos){
					mp.start();
					parejas++;
					seleccion=false;
					bActivado.setEnabled(false);
					bCompara.setEnabled(false);
					if(parejas==15){
						hilo.setPausado(true);
						reiniciar.setEnabled(true);
						/*Inserción a la BD*/
						insertaScore(tvTiempo.getText().toString(),noIntentos.getText().toString());
						Toast.makeText(getApplicationContext(), "Felicidades lo haz logrado :)", Toast.LENGTH_SHORT).show();
						pausar();
					}
				}else{	
					contador++;
					seleccion= false;
					handler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							bActivado.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
							bCompara.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));		
							habilitaBotones(bActivado, bCompara);
						}
					}, 500);
					//habilitaBotones();
				}
	}

	public void insertaScore(String tiempo, String intentos){
		String querySQL="INSERT INTO scores(tiempo, intentos) values('"+tiempo+"', '"+intentos+"')";
		try{
			aDB= new AyudanteDB(this, "memorama", null, 1);
			db= aDB.getWritableDatabase();
			if(db != null)
				db.execSQL(querySQL);
		}catch(SQLException ex){
			Toast.makeText(getApplicationContext(), "Error: "+ex.getMessage(), Toast.LENGTH_LONG).show();
			System.out.println("Erro: "+ex.getMessage());
		}finally{
			if(db!=null)						
				db.close();
		}
	}
	public void reiniciarJuego(){
		contador=0;
		parejas=0;
		seleccion=false;
		/*Reiniciar botones*/
		b1.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b2.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b3.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b4.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b5.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b6.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b7.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b8.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b9.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b10.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b11.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b12.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b13.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b14.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b15.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b16.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b17.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b18.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b19.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b20.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b21.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b22.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b23.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b24.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b25.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b26.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b27.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b28.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b29.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		b30.setImageDrawable(recursos.getDrawable(android.R.drawable.btn_star));
		
		b1.setEnabled(true);
		b2.setEnabled(true);
		b3.setEnabled(true);
		b4.setEnabled(true);
		b5.setEnabled(true);
		b6.setEnabled(true);
		b7.setEnabled(true);
		b8.setEnabled(true);
		b9.setEnabled(true);
		b10.setEnabled(true);
		b11.setEnabled(true);
		b12.setEnabled(true);
		b13.setEnabled(true);
		b14.setEnabled(true);
		b15.setEnabled(true);
		b16.setEnabled(true);
		b17.setEnabled(true);
		b18.setEnabled(true);
		b19.setEnabled(true);
		b20.setEnabled(true);
		b21.setEnabled(true);
		b22.setEnabled(true);
		b23.setEnabled(true);
		b24.setEnabled(true);
		b25.setEnabled(true);
		b26.setEnabled(true);
		b27.setEnabled(true);
		b28.setEnabled(true);
		b29.setEnabled(true);
		b30.setEnabled(true);
		
		ordenar(nDigitos);
		
	}
	public void pausar(){
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		b5.setEnabled(false);
		b6.setEnabled(false);
		b7.setEnabled(false);
		b8.setEnabled(false);
		b9.setEnabled(false);
		b10.setEnabled(false);
		b11.setEnabled(false);
		b12.setEnabled(false);
		b13.setEnabled(false);
		b14.setEnabled(false);
		b15.setEnabled(false);
		b16.setEnabled(false);
		b17.setEnabled(false);
		b18.setEnabled(false);
		b19.setEnabled(false);
		b20.setEnabled(false);
		b21.setEnabled(false);
		b22.setEnabled(false);
		b23.setEnabled(false);
		b24.setEnabled(false);
		b25.setEnabled(false);
		b26.setEnabled(false);
		b27.setEnabled(false);
		b28.setEnabled(false);
		b29.setEnabled(false);
		b30.setEnabled(false);
		
		hilo.setPausado(true);
	}
	public void continuar(){
	//	int iguales=0;
		if(bandera)
			hilo.start();
		/*for(int i=0; i < botones.length; i++){
			for(int j=0; j < yaEmparejados.length; j++){
				if(botones[i] == yaEmparejados[j])
					iguales++;
				else
					botones[i].setEnabled(true);
			}
		}
		System.out.println("MSG-->"+iguales+"\n");*/
		
		b1.setEnabled(true);
		b2.setEnabled(true);
		b3.setEnabled(true);
		b4.setEnabled(true);
		b5.setEnabled(true);
		b6.setEnabled(true);
		b7.setEnabled(true);
		b8.setEnabled(true);
		b9.setEnabled(true);
		b10.setEnabled(true);
		b11.setEnabled(true);
		b12.setEnabled(true);
		b13.setEnabled(true);
		b14.setEnabled(true);
		b15.setEnabled(true);
		b16.setEnabled(true);
		b17.setEnabled(true);
		b18.setEnabled(true);
		b19.setEnabled(true);
		b20.setEnabled(true);
		b21.setEnabled(true);
		b22.setEnabled(true);
		b23.setEnabled(true);
		b24.setEnabled(true);
		b25.setEnabled(true);
		b26.setEnabled(true);
		b27.setEnabled(true);
		b28.setEnabled(true);
		b29.setEnabled(true);
		b30.setEnabled(true);
		
		hilo.setPausado(false);
		bandera=false;
	}
}
