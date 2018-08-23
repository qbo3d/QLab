package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M122;
import com.qbo3d.qlab.Logica.Herr;
import com.qbo3d.qlab.Persistencia.SQLiteM122;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ens_122_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;

	String m122_str = "";
	int m122_id;
	
	TextView tv_ens_122_super;
	TextView tv_m122;
	Button bt_122_con;
	
	SQLiteM122 mueSQLite;
	Button bt_ens_122_consultar;
	Bundle extra;
	Datos_M122 m122;
	Intent data;
	Cursor cursorM122, cursorM122_AVGw;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_122_consultar);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		
		tv_ens_122_super = (TextView) findViewById(R.id.tv_ens_122_super_con);
		tv_ens_122_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);
		tv_m122 = (TextView) findViewById(R.id.tv_ens_122_2_con);
		bt_122_con = (Button) findViewById(R.id.bt_m122_con);
		mueSQLite = new SQLiteM122(this);
		
		callLlenarTV();
		mueSQLite.close();
	}

	private void callLlenarTV() {
		cursorM122 = mueSQLite.getAllM122(ens_numero);
		cursorM122_AVGw = mueSQLite.getAVGw(ens_numero);
		cursorM122_AVGw.moveToFirst();
		int m122PerIdFk = 0, bkp;
			if (cursorM122.moveToFirst()) {
				m122_str = m122_str + 
					"Proyecto: " + mueSQLite.getM122proN(ens_numero) + "\n" +
					"Ensayo No.: " + ens_numero + "\n" +
					"Fecha de ensayo: " + Herr.cambiarAnho(mueSQLite.getM122Fecha(ens_numero)) + "\n" +
					"Descripción del suelo: " + mueSQLite.getM122DesSuelo(ens_numero) + "\n" +
					"Localización del proyecto: " + mueSQLite.getM122Localizacion(ens_numero) + "\n" +
					"Realizado por: " + mueSQLite.getM122Usuario(ens_numero) + "\n";
				do {
					bkp = m122PerIdFk;
					m122PerIdFk = Integer.parseInt(cursorM122.getString(5));
					
					if(m122PerIdFk != bkp){
						if(!cursorM122.isFirst()){
							m122_str = m122_str + 
									"\nHumedad promedio grava:" +
										"\n"  + String.format("%.2f", Double.parseDouble(cursorM122_AVGw.getString(0))) + " %\n";
							cursorM122_AVGw.moveToNext();
						}
						m122_str = m122_str + 
								"\nTipo: "  + cursorM122.getString(11) + "\n"; 
					}
					m122_str = m122_str + 
					"\n" +
					"Número: " + cursorM122.getString(10) + "\n" +
					"Peso recipiente + suelo húmedo W1:" +
						"\n"  + String.format("%.2f", Double.parseDouble(cursorM122.getString(13))) + " g\n" + 
					"Peso recipiente + suelo seco W2:" +
						"\n"  + String.format("%.2f", Double.parseDouble(cursorM122.getString(14))) + " g\n" + 
					"Peso recipiente WC:" +
						"\n"  + String.format("%.2f", Double.parseDouble(cursorM122.getString(15))) + " g\n" + 
					"Peso suelo seco Ws:" +
						"\n"  + String.format("%.2f", Double.parseDouble(cursorM122.getString(17))) + " g\n" + 
					"Peso de agua Ww:" +
						"\n"  + String.format("%.2f", Double.parseDouble(cursorM122.getString(16))) + " g\n" + 
					"Contenido de humedad w:" +
						"\n"  + String.format("%.2f", Double.parseDouble(cursorM122.getString(12))) + " %\n";
					
				} while (cursorM122.moveToNext());
				m122_str = m122_str + 
						"\nHumedad promedio Arena:" +
						"\n"  + String.format("%.2f", Double.parseDouble(cursorM122_AVGw.getString(0))) + " %\n";
			}
		tv_m122.setText(m122_str);
		
		bt_122_con.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callGenerarInforme();
			}
		});
	}
	
	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_cari, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.men_iniciar:
			iniciarProyecto();
			return true;
		case R.id.men_regresar:
			finish();
			return true;
		case R.id.men_anotar:
			callAnotar();
			return true;
		case R.id.men_capturar:
			callCapturar();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void callAnotar() {
		Intent intent = new Intent();
		intent.setClass(this, Lab_Ano_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivityForResult(intent, REQUEST_CODE);
	}

	private void callCapturar() {
		Intent intent = new Intent();
		intent.setClass(this, Lab_Cap_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivityForResult(intent, REQUEST_CODE);
	}

	private void callGenerarInforme() {
		Intent intent = new Intent();
		intent.setClass(this, Ens_122_Inf.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivity(intent);
	}

}