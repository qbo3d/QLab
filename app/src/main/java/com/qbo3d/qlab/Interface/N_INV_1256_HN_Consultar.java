package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M1256_HN;
import com.qbo3d.qlab.Logica.M1256;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_HN;
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

public class N_INV_1256_HN_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;
	int m1256_hn_id;
	String mue_numero;

	Double pshr;
	Double pssr;
	Double pa;
	Double pr;
	Double pss;
	Double ch;

	TextView tv_m1256_hn_super;
	TextView et_m1256_hn_numero;
	TextView et_m1256_hn_pshr_con;
	TextView et_m1256_hn_pssr_con;
	TextView et_m1256_hn_pa_con;
	TextView et_m1256_hn_pr_con;
	TextView et_m1256_hn_pss_con;
	TextView et_m1256_hn_ch_con;

	SQLiteM1256_HN mueSQLite;
	Bundle extra;
	Intent data;
	
	Datos_M1256_HN m1256_HN;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_1256_hn_consultar);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));
		mue_numero = extra.getString("mue_numero");

		tv_m1256_hn_super = (TextView) findViewById(R.id.tv_m1256_hn_super_con);
		tv_m1256_hn_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);
		et_m1256_hn_numero = (TextView) findViewById(R.id.et_m1256_hn_rn_con);
		et_m1256_hn_pshr_con = (TextView) findViewById(R.id.et_m1256_hn_pshr_con);
		et_m1256_hn_pssr_con = (TextView) findViewById(R.id.et_m1256_hn_pssr_con);
		et_m1256_hn_pa_con = (TextView) findViewById(R.id.et_m1256_hn_pa_con);
		et_m1256_hn_pr_con = (TextView) findViewById(R.id.et_m1256_hn_pr_con);
		et_m1256_hn_pss_con = (TextView) findViewById(R.id.et_m1256_hn_pss_con);
		et_m1256_hn_ch_con = (TextView) findViewById(R.id.et_m1256_hn_ch_con);

		mueSQLite = new SQLiteM1256_HN(this);
		
		m1256_hn_id = mueSQLite.getM1256_HNId(mue_numero, per_id);
		
		callLlenarTV(m1256_hn_id);
		
		mueSQLite.close();
	}

	private void callLlenarTV(int m1256_hn_id) {
		m1256_HN = mueSQLite.getM1256_HN(m1256_hn_id);
		
		mue_numero = m1256_HN.getM1256_HN_RN();
		pshr = m1256_HN.getM1256_HN_PSHR();
		pssr = m1256_HN.getM1256_HN_PSSR();
		pa = M1256.lab1256calc01(pshr, pssr);
		pr = m1256_HN.getM1256_HN_PR();
		pss = M1256.lab1256calc02(pssr, pr);
		ch = M1256.lab1256calc03(pa, pss);
		
		et_m1256_hn_numero.setText(mue_numero);
		et_m1256_hn_pshr_con.setText(String.format("%.2f", pshr));
		et_m1256_hn_pssr_con.setText(String.format("%.2f", pssr));
		et_m1256_hn_pa_con.setText(String.format("%.2f", pa));
		et_m1256_hn_pr_con.setText(String.format("%.2f", pr));
		et_m1256_hn_pss_con.setText(String.format("%.2f", pss));
		et_m1256_hn_ch_con.setText(String.format("%.2f", ch));
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
						+ m1256_hn_id + " perteneciente a la Perforación número "
						+ per_numero + ", al proyecto número "
						+ pro_nombre + " y al ensayo número " + ens_numero
						+ "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								mueSQLite.deleteM1256_HN(m1256_hn_id);
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