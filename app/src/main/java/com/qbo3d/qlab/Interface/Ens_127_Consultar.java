package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M127;
import com.qbo3d.qlab.Logica.Herr;
import com.qbo3d.qlab.Persistencia.SQLiteM127;
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

public class Ens_127_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;

	String m127_str = "";
	int m127_id;
	
	TextView tv_ens_127_super;
	TextView tv_m127;
	Button bt_127_con;
	
	SQLiteM127 mueSQLite;
	Button bt_ens_127_consultar;
	Bundle extra;
	Datos_M127 m127;
	Intent data;
	Cursor cursorM127;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_127_consultar);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		
		tv_ens_127_super = (TextView) findViewById(R.id.tv_ens_127_super_con);
		tv_ens_127_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);
		tv_m127 = (TextView) findViewById(R.id.tv_ens_127_2_con);
		bt_127_con = (Button) findViewById(R.id.bt_m127_con);
		mueSQLite = new SQLiteM127(this);
		
		callLlenarTV();
		mueSQLite.close();
	}

	private void callLlenarTV() {
		cursorM127 = mueSQLite.getAllM127(ens_numero);
		if (cursorM127.moveToFirst()) {
			m127_str = m127_str + 
				"Proyecto: " + mueSQLite.getM127proN(ens_numero) + "\n" +
				"Ensayo No.: " + ens_numero + "\n" +
				"Perforación No.: " + mueSQLite.getM127Perforacion(ens_numero) + "\n" +
				"Profundidad No.: " + mueSQLite.getM127Profundidad(ens_numero) + "\n" +
				"Fecha de ensayo: " + Herr.cambiarAnho(mueSQLite.getM127Fecha(ens_numero)) + "\n" +
				"Descripción del suelo: " + mueSQLite.getM127DesSuelo(ens_numero) + "\n" +
				"Localización del proyecto: " + mueSQLite.getM127Localizacion(ens_numero) + "\n" +
				"Realizado por: " + mueSQLite.getM127Usuario(ens_numero) + "\n\n" +
				"Número: " + cursorM127.getString(10) + "\n" +
				"Masa del recipiente y del espécimen húmedo (g) W1:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(12))) + " g\n" + 
				"Masa del recipiente y del espécimen seco (g) W2:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(13))) + " g\n" + 
				"Masa del recipiente (g) W3:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(14))) + " g\n" + 
				"Contenido de agua (%) w:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(11))) + " %\n" + 
				"Volumen de la pastilla de suelo húmedo (cm³) V:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(16))) + " cm³\n" + 
				"Volumen de la pastilla de suelo secada al horno (cm³) Vo:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(17))) + " cm³\n" + 
				"Masa de la pastilla de suelo seco(g) Wo:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(18))) + " g\n" + 
				"Masa unitaria del agua (g/cm³) γw:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(19))) + " g/cm³\n" + 
				"Relación de contracción (g/cm³) R:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(20))) + " g/cm³\n" + 
				"Peso específico real de los sólidos Gs:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(21))) + " \n" + 
				"Límite de contracción (%) LC:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(15))) + " %\n" + 
				"Contenido de agua dado Wi:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(23))) + " \n" + 
				"Cambio volumétrico CV:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(22))) + " \n" + 
				"Contracción lineal CL:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM127.getString(24))) + " \n";
		}
		tv_m127.setText(m127_str);
		
		bt_127_con.setOnClickListener(new View.OnClickListener() {
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
		intent.setClass(this, Ens_127_Inf.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivity(intent);
	}

}