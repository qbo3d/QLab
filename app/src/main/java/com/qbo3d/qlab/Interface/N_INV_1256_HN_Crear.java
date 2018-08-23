package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M1256_HN;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_HN;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class N_INV_1256_HN_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;

	Double pshr;
	Double pssr;
	Double pr;
	Double pp200;
	String aashto;
	String usc;
	
	String[] aashtoAR = { "A-1-a", "A-1-b", "A-3", "A-2-4", "A-2-5", "A-2-6", "A-2-7", "A-4", "A-5", "A-6", "A-7-5", "A-7-6" };

	String[] uscAR = { "CL", "ML", "CL-ML", "MH-CH", "MH", "CH", "SW-SC", "SW-SM", "SP-SC", "SP-SM", "SC", "SMd", "SMu", "SM-SC", "SP", "SW", "GW-GC", "GW-GM", "GP-GC", "GP-GM", "GC", "GMd", "GMu", "GM-GC", "GP", "GW" };

	TextView tv_m1256_hn_super;
	EditText et_m1256_hn_numero;
	EditText et_m1256_hn_pshr_cre;
	EditText et_m1256_hn_pssr_cre;
	EditText et_m1256_hn_pr_cre;
	EditText et_m1256_hn_pp200_cre;

	Spinner sp_m1256_hn_aashto_cre;
	Spinner sp_m1256_hn_usc_cre;

	ArrayAdapter<String> adapterAASHTO;
	ArrayAdapter<String> adapterUSC;
	SQLiteM1256_HN mueSQLite;
	Button bt_m1256_hn_crear;
	Bundle extra;
	Intent data;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_1256_hn_crear);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));
		
		tv_m1256_hn_super = (TextView) findViewById(R.id.tv_m1256_hn_super_cre);
		tv_m1256_hn_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero + "\nPerforación: " + per_numero);
		et_m1256_hn_numero = (EditText) findViewById(R.id.et_m1256_hn_rn_cre);
		et_m1256_hn_pshr_cre = (EditText) findViewById(R.id.et_m1256_hn_pshr_cre);
		et_m1256_hn_pssr_cre = (EditText) findViewById(R.id.et_m1256_hn_pssr_cre);
		et_m1256_hn_pr_cre = (EditText) findViewById(R.id.et_m1256_hn_pr_cre);
		et_m1256_hn_pp200_cre = (EditText) findViewById(R.id.et_m1256_hn_pp200_cre);
		sp_m1256_hn_aashto_cre = (Spinner) findViewById(R.id.sp_m1256_hn_aashto_cre);
		sp_m1256_hn_usc_cre = (Spinner) findViewById(R.id.sp_m1256_hn_usc_cre);

		adapterAASHTO = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, aashtoAR);
		adapterAASHTO
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m1256_hn_aashto_cre.setAdapter(adapterAASHTO);

		sp_m1256_hn_aashto_cre
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						aashto = aashtoAR[position];
					}
			public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
		
		adapterUSC = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, uscAR);
		adapterUSC
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m1256_hn_usc_cre.setAdapter(adapterUSC);

		sp_m1256_hn_usc_cre
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						usc = uscAR[position];
					}

					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
		
		mueSQLite = new SQLiteM1256_HN(this);
		
		bt_m1256_hn_crear = (Button) findViewById(R.id.bt_m1256_hn_crear);
		bt_m1256_hn_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m1256_hn_numero.getText().toString().equals("") == false
						&& et_m1256_hn_pshr_cre.getText().toString().equals("") == false
						&& et_m1256_hn_pssr_cre.getText().toString().equals("") == false
						&& et_m1256_hn_pr_cre.getText().toString().equals("") == false
						&& et_m1256_hn_pp200_cre.getText().toString().equals("") == false) {
					
					pshr = Double.parseDouble(et_m1256_hn_pshr_cre.getText().toString());
					pssr = Double.parseDouble(et_m1256_hn_pssr_cre.getText().toString());
					pr = Double.parseDouble(et_m1256_hn_pr_cre.getText().toString());
					pp200 = Double.parseDouble(et_m1256_hn_pp200_cre.getText().toString());
					mueSQLite.addM1256_HN(new Datos_M1256_HN(
						et_m1256_hn_numero.getText().toString(),
						pshr,
						pssr,
						pr,
						pp200,
						aashto,
						usc,
						per_id));
					setResult(RESULT_OK, data);
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		mueSQLite.close();
	}
	
	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_cari, menu);
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
		case R.id.men_anotar:
			callAnotar();
			return true;
		case R.id.men_capturar:
			callCapturar();
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