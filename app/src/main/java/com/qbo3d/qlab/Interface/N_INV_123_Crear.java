package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M123;
import com.qbo3d.qlab.Persistencia.SQLiteM123;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class N_INV_123_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;

	TextView tv_m123_super;
	EditText et_m123_numero_cre;
	
	EditText  et_m123_t2p_cre;
	EditText  et_m123_t1e1s2p_cre;
	EditText  et_m123_t1p_cre;
	EditText  et_m123_t1s2p_cre;
	EditText  et_m123_t3s8p_cre;
	EditText  et_m123_tn4p_cre;
	EditText  et_m123_tn10p_cre;
	EditText  et_m123_tn40p_cre;
	EditText  et_m123_tn200p_cre;
	EditText  et_m123_tfondo_cre;
	
	EditText  et_m123_d60_cre;
	EditText  et_m123_d30_cre;
	EditText  et_m123_d10_cre;
	
	EditText  et_m123_ll_cre;
	EditText  et_m123_lp_cre;
	EditText  et_m123_ip_cre;
	
	EditText  et_m123_aashto_cre;
	EditText  et_m123_usc_cre;
	
	SQLiteM123 mueSQLite;
	Button bt_m123_crear;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_123_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));

		tv_m123_super = (TextView) findViewById(R.id.tv_m123_super_cre);
		tv_m123_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);

		et_m123_numero_cre = (EditText) findViewById(R.id.et_m123_numero_cre);
		
		et_m123_t2p_cre  = (EditText) findViewById(R.id.et_m123_t2p_cre);
		et_m123_t1e1s2p_cre  = (EditText) findViewById(R.id.et_m123_t1e1s2p_cre);
		et_m123_t1p_cre  = (EditText) findViewById(R.id.et_m123_t1p_cre);
		et_m123_t1s2p_cre  = (EditText) findViewById(R.id.et_m123_t1s2p_cre);
		et_m123_t3s8p_cre  = (EditText) findViewById(R.id.et_m123_t3s8p_cre);
		et_m123_tn4p_cre  = (EditText) findViewById(R.id.et_m123_tn4p_cre);
		et_m123_tn10p_cre  = (EditText) findViewById(R.id.et_m123_tn10p_cre);
		et_m123_tn40p_cre  = (EditText) findViewById(R.id.et_m123_tn40p_cre);
		et_m123_tn200p_cre  = (EditText) findViewById(R.id.et_m123_tn200p_cre);
		et_m123_tfondo_cre  = (EditText) findViewById(R.id.et_m123_tfondo_cre);
		et_m123_d60_cre  = (EditText) findViewById(R.id.et_m123_d60_cre);
		et_m123_d30_cre  = (EditText) findViewById(R.id.et_m123_d30_cre);
		et_m123_d10_cre  = (EditText) findViewById(R.id.et_m123_d10_cre);
		et_m123_ll_cre  = (EditText) findViewById(R.id.et_m123_ll_cre);
		et_m123_lp_cre  = (EditText) findViewById(R.id.et_m123_lp_cre);
		et_m123_ip_cre  = (EditText) findViewById(R.id.et_m123_ip_cre);
		et_m123_aashto_cre  = (EditText) findViewById(R.id.et_m123_aashto_cre);
		et_m123_usc_cre  = (EditText) findViewById(R.id.et_m123_usc_cre);
		
		mueSQLite = new SQLiteM123(this);

		bt_m123_crear = (Button) findViewById(R.id.bt_m123_crear);
		bt_m123_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (et_m123_t2p_cre.getText().toString().equals("") == false
						&& et_m123_t1e1s2p_cre.getText().toString().equals("") == false
						&& et_m123_t1p_cre.getText().toString().equals("") == false
						&& et_m123_t1s2p_cre.getText().toString().equals("") == false
						&& et_m123_t3s8p_cre.getText().toString().equals("") == false
						&& et_m123_tn4p_cre.getText().toString().equals("") == false
						&& et_m123_tn10p_cre.getText().toString().equals("") == false
						&& et_m123_tn40p_cre.getText().toString().equals("") == false
						&& et_m123_tn200p_cre.getText().toString().equals("") == false
						&& et_m123_tfondo_cre.getText().toString().equals("") == false) {
					mueSQLite.addM123(new Datos_M123(
						et_m123_numero_cre.getText().toString(),
						Double.parseDouble(et_m123_t2p_cre.getText().toString()),
						Double.parseDouble(et_m123_t1e1s2p_cre.getText().toString()),
						Double.parseDouble(et_m123_t1p_cre.getText().toString()),
						Double.parseDouble(et_m123_t1s2p_cre.getText().toString()),
						Double.parseDouble(et_m123_t3s8p_cre.getText().toString()),
						Double.parseDouble(et_m123_tn4p_cre.getText().toString()),
						Double.parseDouble(et_m123_tn10p_cre.getText().toString()),
						Double.parseDouble(et_m123_tn40p_cre.getText().toString()),
						Double.parseDouble(et_m123_tn200p_cre.getText().toString()),
						Double.parseDouble(et_m123_tfondo_cre.getText().toString()),
						Double.parseDouble(et_m123_d60_cre.getText().toString()),
						Double.parseDouble(et_m123_d30_cre.getText().toString()),
						Double.parseDouble(et_m123_d10_cre.getText().toString()),
						et_m123_ll_cre.getText().toString(),
						et_m123_lp_cre.getText().toString(),
						et_m123_ip_cre.getText().toString(),
						et_m123_aashto_cre.getText().toString(),
						et_m123_usc_cre.getText().toString(),
						per_id));
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