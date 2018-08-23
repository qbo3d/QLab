package com.qbo3d.qlab.Interface;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import com.qbo3d.qlab.Logica.Datos_Captura;
import com.qbo3d.qlab.Persistencia.SQLiteCaptura;
import com.qbo3d.qlab.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Cap_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;
	int ens_id;
	TextView tv_cap_super;
	TextView et_cap_nota;
	ImageView imageView;
	Bundle extra;
	SQLiteCaptura capSQLite;
	Intent data;
	Datos_Captura cap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_cap_consultar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		ens_id = Integer.parseInt(extra.getString("ens_id"));
		
		tv_cap_super = (TextView) findViewById(R.id.tv_cap_super_con);
		tv_cap_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero);
		imageView = (ImageView) findViewById(R.id.iv_cap_imagen_con);
		et_cap_nota = (TextView) findViewById(R.id.et_cap_nota_con);
		
		capSQLite = new SQLiteCaptura(this);
		
		cap = capSQLite.getCaptura(ens_id);
		if (cap.getCapImagen() != null) {
			imageView.setImageBitmap(Herr_Capturar.strImg(this, cap.getCapImagen()));
		}
		et_cap_nota.setText(cap.getCapNota());
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