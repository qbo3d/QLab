package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_Proyecto;
import com.qbo3d.qlab.Persistencia.SQLiteProyecto;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Proyecto_Consultar extends Activity {

	String usu_cedula;
	TextView pro_nombre;
	String pro_nombre_str;
	TextView pro_localizacion;
	TextView pro_fecha;
	String pro_fecha_str;
	int pro_usu_cedula_fk_int;
	TextView pro_usu_cedula_fk_pos;
	SQLiteProyecto proSQLite;
	Datos_Proyecto pro;
	Button bt_puente_ens;
	Bundle extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_proyecto_consultar);
		
		extra = getIntent().getExtras();
		usu_cedula = extra.getString("usuario");
		pro_nombre_str = extra.getString("pro_nombre");
		pro_nombre = (TextView) findViewById(R.id.tv_pro_nombre_pos_con);
		pro_localizacion = (TextView) findViewById(R.id.et_pro_localizacion_con);
		pro_fecha = (TextView) findViewById(R.id.dp_pro_fecha_con);
		pro_usu_cedula_fk_pos = (TextView) findViewById(R.id.tv_pro_usu_cedula_fk_pos_con);
		proSQLite = new SQLiteProyecto(this);

		callLlenarET();
		
		bt_puente_ens = (Button) findViewById(R.id.bt_puente_ensayo);
		bt_puente_ens.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callPuenteEnsayo();
			}
		});

		proSQLite.close();
	}
	
	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
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

		pro_fecha.setText(Integer.parseInt(pro_fecha_str.substring(0, i1))
				+ " / "
				+ (Integer.parseInt(pro_fecha_str.substring(i1 + 1, i2)) + 1)
				+ " / " + Integer.parseInt(pro_fecha_str.substring(i2 + 1)));
	}
	
	private void callPuenteEnsayo() {
		Intent intent = new Intent();
		intent.setClass(this, Puente_Ens.class);
		intent.putExtra("pro_nombre", pro_nombre_str);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_eri, menu);
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
		case R.id.men_eliminar:
			if (pro_nombre_str != null) {
				AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
				dialogo.setTitle("Eliminar");
				dialogo.setMessage("¿Desea eliminar el proyecto "
						+ pro_nombre_str + "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								proSQLite.deleteProyect(pro_nombre_str);
								Toast.makeText(getApplicationContext(),
										"Proyecto eliminado", Toast.LENGTH_SHORT)
										.show();
								finish();
							}
						});
				dialogo.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								Toast.makeText(getApplicationContext(),
										"Se ha cancelado la operación",
										Toast.LENGTH_SHORT).show();
							}
						});
				dialogo.show();
			} else {
				Toast.makeText(getApplicationContext(),
						"No hay usuarios almacenados", Toast.LENGTH_LONG)
						.show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}