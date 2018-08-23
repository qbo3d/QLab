package com.qbo3d.qlab.Interface;

import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M1256_LP;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_LP;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class N_INV_1256_LP_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;

	Double pshr;
	Double pssr;
	Double pr;
	Double ng;

	TextView tv_m1256_lp_super;
	EditText et_m1256_lp_numero;
	EditText et_m1256_lp_pshr_cre;
	EditText et_m1256_lp_pssr_cre;
	EditText et_m1256_lp_pr_cre;

	ArrayAdapter<String> adapterTipo;
	List<String> listTipo;
	SQLiteM1256_LP mueSQLite;
	Button bt_m1256_lp_crear;
	Bundle extra;
	Intent data;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_1256_lp_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));

		tv_m1256_lp_super = (TextView) findViewById(R.id.tv_m1256_lp_super_cre);
		tv_m1256_lp_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);
		et_m1256_lp_numero = (EditText) findViewById(R.id.et_m1256_lp_rn_cre);
		et_m1256_lp_pshr_cre = (EditText) findViewById(R.id.et_m1256_lp_pshr_cre);
		et_m1256_lp_pssr_cre = (EditText) findViewById(R.id.et_m1256_lp_pssr_cre);
		et_m1256_lp_pr_cre = (EditText) findViewById(R.id.et_m1256_lp_pr_cre);

		mueSQLite = new SQLiteM1256_LP(this);
		
		bt_m1256_lp_crear = (Button) findViewById(R.id.bt_m1256_lp_crear);
		bt_m1256_lp_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m1256_lp_numero.getText().toString().equals("") == false
						&& et_m1256_lp_pshr_cre.getText().toString().equals("") == false
						&& et_m1256_lp_pssr_cre.getText().toString().equals("") == false
						&& et_m1256_lp_pr_cre.getText().toString().equals("") == false) {
					
					pshr = Double.parseDouble(et_m1256_lp_pshr_cre.getText().toString());
					pssr = Double.parseDouble(et_m1256_lp_pssr_cre.getText().toString());
					pr = Double.parseDouble(et_m1256_lp_pr_cre.getText().toString());

					mueSQLite.addM1256_LP(new Datos_M1256_LP(
						et_m1256_lp_numero.getText().toString(),
						pshr,
						pssr,
						pr,
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