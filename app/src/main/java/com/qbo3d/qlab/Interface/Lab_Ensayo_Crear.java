package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_Ensayo;
import com.qbo3d.qlab.Persistencia.SQLiteEnsayo;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class Lab_Ensayo_Crear extends Activity {

	TextView ens_super;
	EditText ens_numero;
	EditText ens_descripcion;
	DatePicker ens_fecha;
	Spinner ens_norma;
	String ens_fecha_str;
	String ens_norma_str;
	SQLiteEnsayo ensSQLite;
	Datos_Ensayo ens;
	ArrayAdapter<?> adapterNorma;
	Button bt_ens_crear;
	String pro_nombre;
	Bundle extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_ensayo_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_super = (TextView) findViewById(R.id.tv_ens_super_cre);
		ens_super.setText("Proyecto: " + pro_nombre);
		ens_numero = (EditText) findViewById(R.id.et_ens_numero_cre);
		ens_descripcion = (EditText) findViewById(R.id.et_ens_descripcion_cre);
		ens_fecha = (DatePicker) findViewById(R.id.dp_ens_fecha_cre);
		ens_norma = (Spinner) findViewById(R.id.sp_ens_norma_cre);
		ensSQLite = new SQLiteEnsayo(this);
		
		adapterNorma = ArrayAdapter.createFromResource(this,
				R.array.n_item, android.R.layout.simple_spinner_item);
		adapterNorma
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ens_norma.setAdapter(adapterNorma);

		ens_norma.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
						ens_norma_str = adapterNorma.getItem(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

		bt_ens_crear = (Button) findViewById(R.id.bt_ens_crear);
		bt_ens_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				int dia = ens_fecha.getDayOfMonth();
				int mes = ens_fecha.getMonth();
				int anho = ens_fecha.getYear();
				ens_fecha_str = anho + "-" + mes + "-" + dia;
				if (ens_numero.getText().toString().equals("") == false
						&& ens_descripcion.getText().toString().equals("") == false) {
					ensSQLite.addEnsayo(new Datos_Ensayo(
							ens_numero.getText().toString(),
							ens_descripcion.getText().toString(), 
							ens_fecha_str,
							ens_norma_str,
							pro_nombre));
					finish();					
				} 
				else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		ensSQLite.close();
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