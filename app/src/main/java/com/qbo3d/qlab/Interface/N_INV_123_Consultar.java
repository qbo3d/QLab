package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M123;
import com.qbo3d.qlab.Persistencia.SQLiteM123;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class N_INV_123_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;
	int m123_id;
	String mue_numero;

	TextView tv_m123_super;
	TextView et_m123_numero_con;
	
	TextView  et_m123_t2p_con;
	TextView  et_m123_t1e1s2p_con;
	TextView  et_m123_t1p_con;
	TextView  et_m123_t1s2p_con;
	TextView  et_m123_t3s8p_con;
	TextView  et_m123_tn4p_con;
	TextView  et_m123_tn10p_con;
	TextView  et_m123_tn40p_con;
	TextView  et_m123_tn200p_con;
	TextView  et_m123_tfondo_con;
	
	TextView  et_m123_d60_con;
	TextView  et_m123_d30_con;
	TextView  et_m123_d10_con;
	
	TextView  et_m123_ll_con;
	TextView  et_m123_lp_con;
	TextView  et_m123_ip_con;
	
	TextView  et_m123_aashto_con;
	TextView  et_m123_usc_con;
	
	SQLiteM123 mueSQLite;
	Bundle extra;
	Intent data;
	
	Datos_M123 m123;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_123_consultar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));
		mue_numero = extra.getString("mue_numero");
		
		tv_m123_super = (TextView) findViewById(R.id.tv_m123_super_con);
		tv_m123_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);

		et_m123_numero_con = (TextView) findViewById(R.id.et_m123_numero_con);
		
		et_m123_t2p_con  = (TextView) findViewById(R.id.et_m123_t2p_con);
		et_m123_t1e1s2p_con  = (TextView) findViewById(R.id.et_m123_t1e1s2p_con);
		et_m123_t1p_con  = (TextView) findViewById(R.id.et_m123_t1p_con);
		et_m123_t1s2p_con  = (TextView) findViewById(R.id.et_m123_t1s2p_con);
		et_m123_t3s8p_con  = (TextView) findViewById(R.id.et_m123_t3s8p_con);
		et_m123_tn4p_con  = (TextView) findViewById(R.id.et_m123_tn4p_con);
		et_m123_tn10p_con  = (TextView) findViewById(R.id.et_m123_tn10p_con);
		et_m123_tn40p_con  = (TextView) findViewById(R.id.et_m123_tn40p_con);
		et_m123_tn200p_con  = (TextView) findViewById(R.id.et_m123_tn200p_con);
		et_m123_tfondo_con  = (TextView) findViewById(R.id.et_m123_tfondo_con);
		et_m123_d60_con  = (TextView) findViewById(R.id.et_m123_d60_con);
		et_m123_d30_con  = (TextView) findViewById(R.id.et_m123_d30_con);
		et_m123_d10_con  = (TextView) findViewById(R.id.et_m123_d10_con);
		et_m123_ll_con  = (TextView) findViewById(R.id.et_m123_ll_con);
		et_m123_lp_con  = (TextView) findViewById(R.id.et_m123_lp_con);
		et_m123_ip_con  = (TextView) findViewById(R.id.et_m123_ip_con);
		et_m123_aashto_con  = (TextView) findViewById(R.id.et_m123_aashto_con);
		et_m123_usc_con  = (TextView) findViewById(R.id.et_m123_usc_con);
		
		mueSQLite = new SQLiteM123(this);
		
		m123_id = mueSQLite.getM123Id(mue_numero, per_id);
		
		callLlenarTV(m123_id);
		
		mueSQLite.close();
	}

	private void callLlenarTV(int m123_id) {
		m123 = mueSQLite.getM123(m123_id);
		et_m123_numero_con.setText(String.valueOf(m123.getM123Numero()));
		et_m123_t2p_con.setText(String.format("%.2f",m123.getM123T2P()));
		et_m123_t1e1s2p_con.setText(String.format("%.2f",m123.getM123T1E1S2P()));
		et_m123_t1p_con.setText(String.format("%.2f",m123.getM123T1P()));
		et_m123_t1s2p_con.setText(String.format("%.2f",m123.getM123T1S2P()));
		et_m123_t3s8p_con.setText(String.format("%.2f",m123.getM123T3S8P()));
		et_m123_tn4p_con.setText(String.format("%.2f",m123.getM123TN4P()));
		et_m123_tn10p_con.setText(String.format("%.2f",m123.getM123TN10P()));
		et_m123_tn40p_con.setText(String.format("%.2f",m123.getM123TN40P()));
		et_m123_tn200p_con.setText(String.format("%.2f",m123.getM123TN200P()));
		et_m123_d60_con.setText(String.format("%.2f",m123.getM123D60()));
		et_m123_d30_con.setText(String.format("%.2f",m123.getM123D30()));
		et_m123_d10_con.setText(String.format("%.2f",m123.getM123D10()));
		et_m123_ll_con.setText(String.valueOf(m123.getM123LL()));
		et_m123_lp_con.setText(String.valueOf(m123.getM123LP()));
		et_m123_ip_con.setText(String.valueOf(m123.getM123IP()));
		et_m123_aashto_con.setText(String.valueOf(m123.getM123AASHTO()));
		et_m123_usc_con.setText(String.valueOf(m123.getM123USC()));
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
						+ m123_id + " perteneciente a la Perforación número "
						+ per_numero + ", al proyecto número "
						+ pro_nombre + " y al ensayo número " + ens_numero
						+ "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								mueSQLite.deleteM123(m123_id);
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
		intent.setClass(this, Lab_Ano_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivityForResult(intent, REQUEST_CODE);
	}
}