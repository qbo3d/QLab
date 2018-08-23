package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_Proyecto;
import com.qbo3d.qlab.Persistencia.SQLiteProyecto;
import com.qbo3d.qlab.Persistencia.SQLiteUsuario;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Lab_Proyecto_Crear extends Activity {

	EditText pro_nombre;
	EditText pro_localizacion;
	DatePicker pro_fecha;
	String pro_fecha_str;
	Spinner pro_usu_cedula_fk;
	int pro_usu_cedula_fk_int;
	SQLiteUsuario usuSQLite;
	SQLiteProyecto proSQLite;
	Datos_Proyecto pro;
	ArrayAdapter<String> adapterId;
	Button bt_pro_crear;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_proyecto_crear);

		pro_nombre = (EditText) findViewById(R.id.et_pro_nombre_cre);
		pro_localizacion = (EditText) findViewById(R.id.et_pro_localizacion_cre);
		pro_fecha = (DatePicker) findViewById(R.id.dp_pro_fecha_cre);
		pro_usu_cedula_fk = (Spinner) findViewById(R.id.sp_pro_usu_cedula_fk_cre);
		usuSQLite = new SQLiteUsuario(this);
		proSQLite = new SQLiteProyecto(this);

		final List<String> list = new ArrayList<String>(usuSQLite.getUsuariosId());

		adapterId = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		adapterId
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		pro_usu_cedula_fk.setAdapter(adapterId);

		pro_usu_cedula_fk.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				pro_usu_cedula_fk_int = Integer.parseInt(list.get(position));
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

		bt_pro_crear = (Button) findViewById(R.id.bt_pro_crear);
		bt_pro_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				int dia = pro_fecha.getDayOfMonth();
				int mes = pro_fecha.getMonth();
				int anho = pro_fecha.getYear();
				pro_fecha_str = anho + "-" + mes + "-" + dia;
				if (pro_nombre.getText().toString().equals("") == false
						&& pro_localizacion.getText().toString().equals("") == false) {
					proSQLite.addProyecto(new Datos_Proyecto(
							pro_nombre.getText().toString(), 
							pro_localizacion.getText().toString(),
							pro_fecha_str, 
							pro_usu_cedula_fk_int));
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		usuSQLite.close();
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