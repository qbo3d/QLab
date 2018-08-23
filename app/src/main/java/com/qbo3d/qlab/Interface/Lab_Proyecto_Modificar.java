package com.qbo3d.qlab.Interface;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Proyecto_Modificar extends Activity {

	String usu_cedula;
	TextView pro_nombre;
	String pro_nombre_str;
	EditText pro_localizacion;
	DatePicker pro_fecha;
	String pro_fecha_str;
	int pro_usu_cedula_fk_int;
	TextView pro_usu_cedula_fk_pos;
	SQLiteUsuario usuSQLite;
	SQLiteProyecto proSQLite;
	Datos_Proyecto pro;
	Button bt_pro_modificar;
	Bundle extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_proyecto_modificar);

		extra = getIntent().getExtras();
		usu_cedula = extra.getString("usuario");
		pro_nombre_str = extra.getString("pro_nombre");
		pro_nombre = (TextView) findViewById(R.id.tv_pro_nombre_pos_mod);
		pro_localizacion = (EditText) findViewById(R.id.et_pro_localizacion_mod);
		pro_fecha = (DatePicker) findViewById(R.id.dp_pro_fecha_mod);
		pro_usu_cedula_fk_pos = (TextView) findViewById(R.id.tv_pro_usu_cedula_fk_pos_mod);
		usuSQLite = new SQLiteUsuario(this);
		proSQLite = new SQLiteProyecto(this);

		callLlenarET();
		
		bt_pro_modificar = (Button) findViewById(R.id.bt_pro_modificar);
		bt_pro_modificar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				int dia = pro_fecha.getDayOfMonth();
				int mes = pro_fecha.getMonth();
				int anho = pro_fecha.getYear();
				pro_fecha_str = anho + "-" + mes + "-" + dia;
				if (pro_localizacion.getText().toString().equals("") == false) {
					proSQLite.updateProyecto(	
							new Datos_Proyecto(
								pro_nombre_str, 
								pro_localizacion.getText().toString(), 
								pro_fecha_str, 
								pro_usu_cedula_fk_int), 
							pro_nombre_str);
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

	private void callLlenarET() {
		
		pro = proSQLite.getProyecto(pro_nombre_str);
		pro_nombre.setText(usu_cedula);
		pro_usu_cedula_fk_pos.setText(pro_nombre_str);
		pro_localizacion.setText(pro.getProLocalizacion());

		pro_fecha_str = pro.getProFecha();
		int i1 = 0, i2 = 0;
		boolean b1 = true;

		for (int i = 0; i < pro_fecha_str.length(); i++) {
			if (pro_fecha_str.charAt(i) == '-') {
				if (b1 == true) {
					i1 = i;
					b1 = false;
				} else {
					i2 = i;
				}
			}
		}
		;

		pro_fecha.updateDate(
				Integer.parseInt(pro_fecha_str.substring(0, i1)),
				Integer.parseInt(pro_fecha_str.substring(i1 + 1, i2)),
				Integer.parseInt(pro_fecha_str.substring(i2 + 1)));
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