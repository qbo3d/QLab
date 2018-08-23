package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M123;
import com.qbo3d.qlab.Logica.Herr;
import com.qbo3d.qlab.Persistencia.SQLiteM123;
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

public class Ens_123_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;

	String m123_str = "";
	int m123_id;
	
	TextView tv_ens_123_super;
	TextView tv_m123;
	Button bt_123_con;
	
	SQLiteM123 mueSQLite;
	Button bt_ens_123_consultar;
	Bundle extra;
	Datos_M123 m123;
	Intent data;
	Cursor cursorM123;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_123_consultar);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		
		tv_ens_123_super = (TextView) findViewById(R.id.tv_ens_123_super_con);
		tv_ens_123_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);
		tv_m123 = (TextView) findViewById(R.id.tv_ens_123_2_con);
		bt_123_con = (Button) findViewById(R.id.bt_m123_con);
		mueSQLite = new SQLiteM123(this);
		
		callLlenarTV();
		mueSQLite.close();
	}

	private void callLlenarTV() {
		cursorM123 = mueSQLite.getAllM123(ens_numero);
		if (cursorM123.moveToFirst()) {
			m123_str = m123_str + 
				"Proyecto: " + mueSQLite.getM123proN(ens_numero) + "\n" +
				"Ensayo No.: " + ens_numero + "\n" +
				"Perforación No.: " + mueSQLite.getM123Perforacion(ens_numero) + "\n" +
				"Profundidad No.: " + mueSQLite.getM123Profundidad(ens_numero) + "\n" +
				"Fecha de ensayo: " + Herr.cambiarAnho(mueSQLite.getM123Fecha(ens_numero)) + "\n" +
				"Descripción del suelo: " + mueSQLite.getM123DesSuelo(ens_numero) + "\n" +
				"Localización del proyecto: " + mueSQLite.getM123Localizacion(ens_numero) + "\n" +
				"Realizado por: " + mueSQLite.getM123Usuario(ens_numero) + "\n\n" +
				"Número: " + cursorM123.getString(10) + "\n" +
				"Masa retenida en el tamiz de 50.8 mm (2 pul.):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(11))) + " g\n" + 
				"Masa retenida en el tamiz de 37.5 mm (1 ½ pul.):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(12))) + " g\n" + 
				"Masa retenida en el tamiz de 25.4 mm (1 pul.):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(13))) + " g\n" + 
				"Masa retenida en el tamiz de 12.7 mm (½ pul.):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(14))) + " g\n" + 
				"Masa retenida en el tamiz de 9.53 mm (⅜ pul.):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(15))) + " g\n" + 
				"Masa retenida en el tamiz de 4.75 mm (No.4):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(16))) + " g\n" + 
				"Masa retenida en el tamiz de 2.00 mm (No.10):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(17))) + " g\n" + 
				"Masa retenida en el tamiz de 425 μm (No.40):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(18))) + " g\n" + 
				"Masa retenida en el tamiz de 75 μm (No.200):" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(19))) + " g\n" + 
				"Masa que quedó en el fondo:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(20))) + " g\n" + 
				"D60:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(21))) + " g\n" + 
				"D30:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(22))) + " g\n" + 
				"D10:" +
					"\n"  + String.format("%.2f", Double.parseDouble(cursorM123.getString(23))) + " g\n" + 
				"% L. L.:" +
					"\n"  + String.valueOf(cursorM123.getString(24)) + "\n" + 
				"% L. P.:" +
					"\n"  + String.valueOf(cursorM123.getString(25)) + "\n" + 
				"% I. de P.:" +
					"\n"  + String.valueOf(cursorM123.getString(26)) + "\n" + 
				"A.A.S.H.T.O.:" +
					"\n"  + String.valueOf(cursorM123.getString(27)) + "\n" +
				"U.S.C.:" +
					"\n"  + String.valueOf(cursorM123.getString(28)) + "\n";
		}
		
		tv_m123.setText(m123_str);
		
		bt_123_con.setOnClickListener(new View.OnClickListener() {
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
		intent.setClass(this, Ens_123_Inf.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivity(intent);
	}

}