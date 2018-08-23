package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_Perforacion;
import com.qbo3d.qlab.Persistencia.SQLitePerforacion;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Perforacion_M1256_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;
	String ens_norma;
	String per_numero_str;
	int per_id;
	int cant_n;

	ArrayAdapter<?> an;
	TextView ens_super;
	TextView per_numero;
	TextView per_profundidad;
	TextView per_observacion;

	SQLitePerforacion perSQLite;
	Button bt_per_hn_consultar;
	Button bt_per_ll_consultar;
	Button bt_per_lp_consultar;
	Bundle extra;
	Datos_Perforacion per;
	Intent data;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_perforacion_m1256_consultar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		ens_norma = extra.getString("ens_norma");
		per_numero_str = extra.getString("per_numero");
		ens_super = (TextView) findViewById(R.id.tv_per_super_con);
		ens_super
				.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);
		per_numero = (TextView) findViewById(R.id.et_per_numero_con);
		per_profundidad = (TextView) findViewById(R.id.et_per_profundidad_con);
		per_observacion = (TextView) findViewById(R.id.et_per_observacion_con);
		perSQLite = new SQLitePerforacion(this);
		per_id = perSQLite.getPerforacionId(ens_numero, pro_nombre, per_numero_str);

		callLlenarET(per_id);

		bt_per_hn_consultar = (Button) findViewById(R.id.bt_per_hn_consultar);
		bt_per_hn_consultar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callListaMuestraHN();
			}
		});

		bt_per_ll_consultar = (Button) findViewById(R.id.bt_per_ll_consultar);
		bt_per_ll_consultar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callListaMuestraLL();
			}
		});

		bt_per_lp_consultar = (Button) findViewById(R.id.bt_per_lp_consultar);
		bt_per_lp_consultar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callListaMuestraLP();
			}
		});
		
		an = ArrayAdapter.createFromResource(this, R.array.n_item,
				android.R.layout.simple_spinner_item);

		for (int i = 0; i < an.getCount(); i++) {
			if (an.getItem(i).toString().equals(ens_norma)) {
				cant_n = i;
			}
		}

		perSQLite.close();
	}

	private void callLlenarET(int per_Id) {
		per = perSQLite.getPerforacion(per_Id);
		per_numero.setText(per.getPerNumero());
		per_profundidad.setText(String.valueOf(per.getPerProfundidad()));
		per_observacion.setText(per.getPerObservacion());
	}

	private void callListaMuestraHN() {
		Intent intent = new Intent();
		intent.setClass(this, Lista_M1256_HN.class);
		intent.putExtra("accion", "Consultar");
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("ens_norma", ens_norma);
		intent.putExtra("per_numero", per_numero_str);
		intent.putExtra("per_id", String.valueOf(per_id));
		startActivity(intent);
	}

	private void callListaMuestraLL() {
		Intent intent = new Intent();
		intent.setClass(this, Lista_M1256_LL.class);
		intent.putExtra("accion", "Consultar");
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("ens_norma", ens_norma);
		intent.putExtra("per_numero", per_numero_str);
		intent.putExtra("per_id", String.valueOf(per_id));
		startActivity(intent);
	}

	private void callListaMuestraLP() {
		Intent intent = new Intent();
		intent.setClass(this, Lista_M1256_LP.class);
		intent.putExtra("accion", "Consultar");
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("ens_norma", ens_norma);
		intent.putExtra("per_numero", per_numero_str);
		intent.putExtra("per_id", String.valueOf(per_id));
		startActivity(intent);
	}

	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_caeri, menu);
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
		case R.id.men_anotar:
			callAnotar();
			return true;
		case R.id.men_capturar:
			callCapturar();
			return true;
		case R.id.men_eliminar:
			if (per_numero_str != null) {
				AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
				dialogo.setTitle("Eliminar");
				dialogo.setMessage("¿Desea eliminar la perforacion número "
						+ per_numero_str + " perteneciente al proyecto "
						+ pro_nombre + " y al ensayo número " + ens_numero
						+ "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								perSQLite.deletePerforacion(per_id);
								Toast.makeText(getApplicationContext(),
										"Perforacion eliminada",
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
						"No hay perforaciones almacenadas", Toast.LENGTH_LONG)
						.show();
			}
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