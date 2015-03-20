package com.paz.memorama;

import android.widget.TextView;


public class Hilo extends Thread{
	public boolean detenido;
	public boolean pausado;
	int minutos=00, segundos=00, centesimas=00;
	Hand handler;
	String cron;
	MainActivity p;
	
	public Hilo(TextView t)
	{
		detenido = false;
		pausado = true;
		handler = new Hand(t);
		p= new MainActivity();
	}


	@SuppressWarnings("static-access")
	public void run()
	{	
	  while(!detenido)
	  {
	    while(!pausado)
	    {
	      try {
	      	if(centesimas == 99){
	      		centesimas = 00;
	      		segundos++;
	      	}
	        if (segundos == 59) {
	          segundos = 00;
	          minutos++;
	        }
	        if (minutos == 59) {
	          minutos = 00;
	        }
	        centesimas++;
	        cron = minutos + " : " + segundos;
	        handler.setHcron(cron);
	        handler.act();
	        this.sleep(10);
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }
	    }
	  }
	}
	

	public void setDetenido(boolean detenido) {
		this.detenido = detenido;
	}

	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}
	public void setCentesimas(int centesimas) {
		this.centesimas = centesimas;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}


	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}


}
