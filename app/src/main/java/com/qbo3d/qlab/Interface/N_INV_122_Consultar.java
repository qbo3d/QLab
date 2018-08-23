package com.qbo3d.qlab.Interface;

import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M122;
import com.qbo3d.qlab.Persistencia.SQLiteM122;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class N_INV_122_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;
	int m122_id;
	String mue_numero;

	Double w;
	Double Ww;
	Double Ws;

	TextView tv_m122_super;
	TextView et_m122_numero;
	TextView tv_m122_tipo;
	TextView et_m122_w1;
	TextView et_m122_w2;
	TextView et_m122_wc;

	ArrayAdapter<String> adapterTipo;
	List<String> listTipo;
	SQLiteM122 mueSQLite;
	Bundle extra;
	Intent data;
	
	Datos_M122 m122;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_122_consultar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));
		mue_numero = extra.getString("mue_numero");

		tv_m122_super = (TextView) findViewById(R.id.tv_m122_super_con);
		tv_m122_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);
		et_m122_numero = (TextView) findViewById(R.id.et_m122_numero_con);
		tv_m122_tipo = (TextView) findViewById(R.id.tv_m122_tipo2_con);
		et_m122_w1 = (TextView) findViewById(R.id.et_m122_w1_con);
		et_m122_w2 = (TextView) findViewById(R.id.et_m122_w2_con);
		et_m122_wc = (TextView) findViewById(R.id.et_m122_wc_con);

		mueSQLite = new SQLiteM122(this);
		
		m122_id = mueSQLite.getM122Id(mue_numero, per_id);
		
		callLlenarTV(m122_id);
		
		mueSQLite.close();
	}

	private void callLlenarTV(int m122_id) {
		m122 = mueSQLite.getM122(m122_id);
		et_m122_numero.setText(m122.getM122Numero());
		tv_m122_tipo.setText(m122.getM122Tipo());
		et_m122_w1.setText(String.valueOf(m122.getM122W1()));
		et_m122_w2.setText(String.valueOf(m122.getM122W2()));
		et_m122_wc.setText(String.valueOf(m122.getM122Wc()));
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
			if (per_numero != null) {
				AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
				dialogo.setTitle("Eliminar");
				dialogo.setMessage("¿Desea eliminar la muestra número "
						+ m122_id + " perteneciente a la Perforación número "
						+ per_numero + ", al proyecto número "
						+ pro_nombre + " y al ensayo número " + ens_numero
						+ "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								mueSQLite.deleteM122(m122_id);
								Toast.makeText(getApplicationContext(),
										"Muestra eliminada",
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