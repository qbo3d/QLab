package com.qbo3d.qlab.Interface;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import com.qbo3d.qlab.Logica.Datos_Anotacion;
import com.qbo3d.qlab.Persistencia.SQLiteAnotacion;
import com.qbo3d.qlab.Persistencia.SQLiteCaptura;
import com.qbo3d.qlab.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Ano_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;
	int ens_id;
	TextView tv_ano_super;
	TextView et_ano_nota;
	Bundle extra;
	SQLiteAnotacion anoSQLite;
	SQLiteCaptura capSQLite;
	Intent data;
	Datos_Anotacion ano;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_ano_consultar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		ens_id = Integer.parseInt(extra.getString("ens_id"));
		
		tv_ano_super = (TextView) findViewById(R.id.tv_ano_super_con);
		tv_ano_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero);
		
		et_ano_nota = (TextView) findViewById(R.id.et_ano_nota_con);
		
		anoSQLite = new SQLiteAnotacion(this);
		capSQLite = new SQLiteCaptura(this);
		
		ano = anoSQLite.getAnotacion(ens_id);
		
		et_ano_nota.setText(ano.getAnoNota());
	}

	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
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
			setResult(RESULT_OK, data);
			finish();
			return true;
		case R.id.men_eliminar:
			if (ens_id != 0) {
				AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
				dialogo.setTitle("Eliminar");
				dialogo.setMessage("¿Desea eliminar la imagen número "
						+ ens_id + " perteneciente al proyecto "
						+ pro_nombre + " y al ensayo número " + ens_numero
						+ "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								capSQLite.deleteCaptura(ens_id);
								Toast.makeText(getApplicationContext(),
										"Imagen eliminada",
										Toast.LENGTH_SHORT).show();
								setResult(RESULT_OK, data);
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
						"No hay imagenes almacenadas", Toast.LENGTH_LONG)
						.show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}