package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M127;
import com.qbo3d.qlab.Persistencia.SQLiteM127;
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

public class N_INV_127_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;
	int m127_id;
	String mue_numero;

	Double w;
	Double W1;
	Double W2;
	Double W3;
	Double LC;
	Double V;
	Double Vo;
	Double Wo;
	Double miw;
	Double R_var;
	Double Gs;
	Double CV;
	Double Wi;
	Double CL;

	TextView tv_m127_super_con;
	TextView et_m127_numero_con;
	
	TextView et_m127_w1_con;
	TextView et_m127_w2_con;
	TextView et_m127_w3_con;
	
	TextView tv_m127_w_con;
	TextView et_m127_v_con;
	TextView et_m127_vo_con;
	TextView et_m127_wo_con;
	TextView et_m127_miw_con;
	
	TextView et_m127_gs_con;
	
	TextView et_m127_wi_con;
	TextView tv_m127_lc_con;
	TextView tv_m127_r_con;
	
	TextView tv_m127_cv_con;
	TextView tv_m127_cl_con;

	SQLiteM127 mueSQLite;
	Bundle extra;
	Intent data;
	
	Datos_M127 m127;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_127_consultar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));
		mue_numero = extra.getString("mue_numero");

		tv_m127_super_con = (TextView) findViewById(R.id.tv_m127_super_con);
		tv_m127_super_con.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);
		et_m127_numero_con = (TextView) findViewById(R.id.et_m127_numero_con);
		
		et_m127_w1_con = (TextView) findViewById(R.id.et_m127_w1_con);
		et_m127_w2_con = (TextView) findViewById(R.id.et_m127_w2_con);
		et_m127_w3_con = (TextView) findViewById(R.id.et_m127_w3_con);
		tv_m127_w_con = (TextView) findViewById(R.id.tv_m127_w_con);
		et_m127_v_con = (TextView) findViewById(R.id.et_m127_v_con);
		et_m127_vo_con = (TextView) findViewById(R.id.et_m127_vo_con);
		et_m127_wo_con = (TextView) findViewById(R.id.et_m127_wo_con);
		et_m127_miw_con = (TextView) findViewById(R.id.et_m127_miw_con);
		et_m127_gs_con = (TextView) findViewById(R.id.et_m127_gs_con);
		et_m127_wi_con = (TextView) findViewById(R.id.et_m127_wi_con);
		tv_m127_lc_con = (TextView) findViewById(R.id.tv_m127_lc_con);
		tv_m127_r_con = (TextView) findViewById(R.id.tv_m127_r_con);
		tv_m127_cv_con = (TextView) findViewById(R.id.tv_m127_cv_con);
		tv_m127_cl_con = (TextView) findViewById(R.id.tv_m127_cl_con);
		
		mueSQLite = new SQLiteM127(this);
		
		m127_id = mueSQLite.getM127Id(mue_numero, per_id);
		
		callLlenarTV(m127_id);
		
		mueSQLite.close();
	}

	private void callLlenarTV(int m127_id) {
		m127 = mueSQLite.getM127(m127_id);
		et_m127_numero_con.setText(String.valueOf(m127.getM127Numero()));
		et_m127_w1_con.setText(String.format("%.2f",m127.getM127W1()));
		et_m127_w2_con.setText(String.format("%.2f",m127.getM127W2()));
		et_m127_w3_con.setText(String.format("%.2f",m127.getM127W3()));
		tv_m127_w_con.setText(String.format("%.2f",m127.getM127w()));
		et_m127_v_con.setText(String.format("%.2f",m127.getM127V()));
		et_m127_vo_con.setText(String.format("%.2f",m127.getM127Vo()));
		et_m127_wo_con.setText(String.format("%.2f",m127.getM127Wo()));
		et_m127_miw_con.setText(String.format("%.2f",m127.getM127miw()));
		et_m127_gs_con.setText(String.format("%.2f",m127.getM127Gs()));
		et_m127_wi_con.setText(String.format("%.2f",m127.getM127Wi()));
		tv_m127_lc_con.setText(String.format("%.2f",m127.getM127LC()));
		tv_m127_r_con.setText(String.format("%.2f",m127.getM127R()));
		tv_m127_cv_con.setText(String.format("%.2f",m127.getM127CV()));
		tv_m127_cl_con.setText(String.format("%.2f",m127.getM127CL()));
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
						+ m127_id + " perteneciente a la Perforación número "
						+ per_numero + ", al proyecto número "
						+ pro_nombre + " y al ensayo número " + ens_numero
						+ "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								mueSQLite.deleteM127(m127_id);
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