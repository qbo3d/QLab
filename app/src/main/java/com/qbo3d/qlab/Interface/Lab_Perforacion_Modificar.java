package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_Perforacion;
import com.qbo3d.qlab.Persistencia.SQLitePerforacion;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Perforacion_Modificar extends Activity {

	String accion;
	String pro_nombre;
	String ens_numero;
	String per_numero_str;
	int per_Id;
	TextView ens_super;
	TextView per_numero;
	EditText per_profundidad;
	EditText per_observacion;
	SQLitePerforacion perSQLite;
	Button bt_per_modificar;
	Bundle extra;
	Datos_Perforacion per;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_perforacion_modificar);

		extra = getIntent().getExtras();
		accion = extra.getString("accion");
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero_str = extra.getString("per_numero");
		ens_super = (TextView) findViewById(R.id.tv_per_super_mod);
		ens_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);
		per_numero = (TextView) findViewById(R.id.et_per_numero_mod);
		per_profundidad = (EditText) findViewById(R.id.et_per_profundidad_mod);
		per_observacion = (EditText) findViewById(R.id.et_per_observacion_mod);
		perSQLite = new SQLitePerforacion(this);
		per_Id = perSQLite.getPerforacionId(ens_numero, pro_nombre, per_numero_str);

		callLlenarET(per_Id);

		bt_per_modificar = (Button) findViewById(R.id.bt_per_modificar);
		bt_per_modificar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (per_profundidad.getText().toString().equals("") == false
						&& per_observacion.getText().toString().equals("") == false) {
					perSQLite.updatePerforacion(
							new Datos_Perforacion(
								per_numero.getText().toString(), 
								Double.parseDouble(per_profundidad.getText().toString()), 
								per_observacion.getText().toString(),
								per_Id), 
							per_numero_str);
					finish();		
				} 
				else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
				
			}
		});
		perSQLite.close();
	}

	private void callLlenarET(int per_Id) {
		per = perSQLite.getPerforacion(per_Id);
		per_numero.setText(per.getPerNumero());
		per_profundidad.setText(String.valueOf(per.getPerProfundidad()));
		per_observacion.setText(per.getPerObservacion());
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
}