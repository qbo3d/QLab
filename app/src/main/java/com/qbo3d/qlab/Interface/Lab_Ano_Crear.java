package com.qbo3d.qlab.Interface;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import com.qbo3d.qlab.Logica.Datos_Anotacion;
import com.qbo3d.qlab.Persistencia.SQLiteAnotacion;
import com.qbo3d.qlab.Persistencia.SQLiteEnsayo;
import com.qbo3d.qlab.R;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Ano_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	int ens_id;
	TextView tv_ano_super;
	EditText et_ano_nota;
	Button bt_ano_guardar;
	Bundle extra;
	SQLiteEnsayo ensSQLite;
	SQLiteAnotacion anoSQLite;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_ano_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		
		tv_ano_super = (TextView) findViewById(R.id.tv_ano_super_cre);
		tv_ano_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero);
		et_ano_nota = (EditText) findViewById(R.id.et_ano_nota_cre);
		
		ensSQLite = new SQLiteEnsayo(this);
		anoSQLite = new SQLiteAnotacion(this);
		
		ens_id = ensSQLite.getEnsayoId(ens_numero, pro_nombre);
		
		bt_ano_guardar = (Button) findViewById(R.id.bt_ano_guardar);
		bt_ano_guardar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (et_ano_nota.getText().toString().equals("") == false) {
					anoSQLite.addAnotacion(new Datos_Anotacion(
							et_ano_nota.getText().toString(),
							ens_id));
					finish();					
				} 
				else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
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
		inflater.inflate(R.menu.menu_ri, menu);
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
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}