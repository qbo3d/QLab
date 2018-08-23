package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_Ensayo;
import com.qbo3d.qlab.Persistencia.SQLiteEnsayo;
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

public class Lab_Ensayo_Consultar extends Activity {

	TextView ens_super;
	TextView ens_numero;
	TextView ens_descripcion;
	TextView ens_fecha;
	TextView ens_norma;
	String ens_fecha_str;
	String ens_norma_str;
	Datos_Ensayo ens;
	Button bt_ens_con;
	Button bt_ens_ano;
	Button bt_ens_cap;
	String pro_nombre;
	String ens_numero_str;
	Bundle extra;
	SQLiteEnsayo ensSQLite;
	Intent data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_ensayo_consultar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero_str = extra.getString("ensayo");
		ens_super = (TextView) findViewById(R.id.tv_ens_super_con);
		ens_super.setText("Proyecto: " + pro_nombre);
		ens_numero = (TextView) findViewById(R.id.et_ens_numero_con);
		ens_descripcion = (TextView) findViewById(R.id.et_ens_descripcion_con);
		ens_fecha = (TextView) findViewById(R.id.dp_ens_fecha_con);
		ens_norma = (TextView) findViewById(R.id.dp_ens_norma_con);
		ensSQLite = new SQLiteEnsayo(this);
		data = new Intent();

		callLlenarET(ens_numero_str, pro_nombre);

		bt_ens_con = (Button) findViewById(R.id.bt_ens_consultar);
		bt_ens_con.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callPuentePerforacion();
			}
		});
		bt_ens_ano = (Button) findViewById(R.id.bt_ens_anotar);
		bt_ens_ano.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callPuenteAnotacion();
			}
		});
		bt_ens_cap = (Button) findViewById(R.id.bt_ens_capturar);
		bt_ens_cap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callPuenteCaptura();
			}
		});
		ensSQLite.close();
	}
	
	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
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

		ens_fecha.setText(Integer.parseInt(ens_fecha_str.substring(0, i1))
				+ " / "
				+ (Integer.parseInt(ens_fecha_str.substring(i1 + 1, i2)) + 1)
				+ " / " + Integer.parseInt(ens_fecha_str.substring(i2 + 1)));
		ens_norma_str = ens.getEnsNorma();
		ens_norma.setText(ens_norma_str);
	}
	
	private void callPuentePerforacion() {
		Intent intent = new Intent();
		intent.setClass(this, Puente_Per.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero_str);
		intent.putExtra("ens_norma", ens_norma_str);
		startActivity(intent);
	}
	
	private void callPuenteAnotacion() {
		Intent intent = new Intent();
		intent.setClass(this, Puente_Ano.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero_str);
		startActivity(intent);
	}
	
	private void callPuenteCaptura() {
		Intent intent = new Intent();
		intent.setClass(this, Puente_Cap.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero_str);
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
			if (ens_numero_str != null) {
				AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
				dialogo.setTitle("Eliminar");
				dialogo.setMessage("¿Desea eliminar el ensayo número "
						+ ens_numero_str + " perteneciente al proyecto " + pro_nombre + "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								ensSQLite.deleteEnsayo(ens_numero_str, pro_nombre);
								Toast.makeText(getApplicationContext(),
										"Ensayo eliminado", Toast.LENGTH_SHORT)
										.show();
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
						"No hay ensayos almacenados", Toast.LENGTH_LONG)
						.show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}