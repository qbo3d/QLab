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

public class Lab_Ensayo_Modificar extends Activity {

	TextView ens_super;
	TextView ens_numero;
	EditText ens_descripcion;
	DatePicker ens_fecha;
	TextView ens_norma_tv;
	Spinner ens_norma;
	String ens_fecha_str;
	String ens_norma_str;
	Datos_Ensayo ens;
	ArrayAdapter<?> adapterNorma;
	Button bt_ens_mod;
	String pro_nombre;
	String ensayo;
	Bundle extra;
	SQLiteEnsayo ensSQLite;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_ensayo_modificar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ensayo = extra.getString("ensayo");
		ens_super = (TextView) findViewById(R.id.tv_ens_super_mod);
		ens_super.setText("Proyecto: " + pro_nombre);
		ens_numero = (TextView) findViewById(R.id.et_ens_numero_mod);
		ens_descripcion = (EditText) findViewById(R.id.et_ens_descripcion_mod);
		ens_fecha = (DatePicker) findViewById(R.id.dp_ens_fecha_mod);
		ens_norma_tv = (TextView) findViewById(R.id.tv_ens_norma_str_mod);
		ens_norma = (Spinner) findViewById(R.id.sp_ens_norma_mod);
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

		callLlenarET(ensayo, pro_nombre);

		bt_ens_mod = (Button) findViewById(R.id.bt_ens_modificar);
		bt_ens_mod.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				int dia = ens_fecha.getDayOfMonth();
				int mes = ens_fecha.getMonth();
				int anho = ens_fecha.getYear();
				ens_fecha_str = anho + "-" + mes + "-" + dia;
				if (ens_descripcion.getText().toString().equals("") == false) {
					ensSQLite.updateEnsayo(
							new Datos_Ensayo(
								ensayo, 
								ens_descripcion.getText().toString(), 
								ens_fecha_str,
								ens_norma_str,
								pro_nombre), 
							ensayo);
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

	private void callLlenarET(String ens_Numero, String ens_nombre_str) {
		ens = ensSQLite.getEnsayo(ens_Numero, ens_nombre_str);
		ens_numero.setText(ens.getEnsNumero());
		ens_descripcion.setText(ens.getEnsDescripcionSuelo());
		
		ens_fecha_str = ens.getEnsFecha();
		int i1 = 0, i2 = 0;
		boolean b1 = true;

		for (int i = 0; i < ens_fecha_str.length(); i++) {
			if (ens_fecha_str.charAt(i) == '-') {
				if (b1 == true) {
					i1 = i;
					b1 = false;
				} else {
					i2 = i;
				}
			}
		}
		;

		ens_fecha.updateDate(
				Integer.parseInt(ens_fecha_str.substring(0, i1)),
				Integer.parseInt(ens_fecha_str.substring(i1 + 1, i2)),
				Integer.parseInt(ens_fecha_str.substring(i2 + 1)));
		ens_norma_str = ens.getEnsNorma();
		ens_norma_tv.setText(ens_norma_str);
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