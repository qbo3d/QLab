package com.qbo3d.qlab.Interface;

import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M1256_LL;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_LL;
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

public class N_INV_1256_LL_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;

	Double pshr;
	Double pssr;
	Double pr;
	Double ng;

	TextView tv_m1256_ll_super;
	EditText et_m1256_ll_numero;
	EditText et_m1256_ll_pshr_cre;
	EditText et_m1256_ll_pssr_cre;
	EditText et_m1256_ll_pr_cre;
	EditText et_m1256_ll_ng_cre;

	ArrayAdapter<String> adapterTipo;
	List<String> listTipo;
	SQLiteM1256_LL mueSQLite;
	Button bt_m1256_ll_crear;
	Bundle extra;
	Intent data;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_1256_ll_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));

		tv_m1256_ll_super = (TextView) findViewById(R.id.tv_m1256_ll_super_cre);
		tv_m1256_ll_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);
		et_m1256_ll_numero = (EditText) findViewById(R.id.et_m1256_ll_rn_cre);
		et_m1256_ll_pshr_cre = (EditText) findViewById(R.id.et_m1256_ll_pshr_cre);
		et_m1256_ll_pssr_cre = (EditText) findViewById(R.id.et_m1256_ll_pssr_cre);
		et_m1256_ll_pr_cre = (EditText) findViewById(R.id.et_m1256_ll_pr_cre);
		et_m1256_ll_ng_cre = (EditText) findViewById(R.id.et_m1256_ll_ng_cre);

		mueSQLite = new SQLiteM1256_LL(this);
		
		bt_m1256_ll_crear = (Button) findViewById(R.id.bt_m1256_ll_crear);
		bt_m1256_ll_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m1256_ll_numero.getText().toString().equals("") == false
						&& et_m1256_ll_pshr_cre.getText().toString().equals("") == false
						&& et_m1256_ll_pssr_cre.getText().toString().equals("") == false
						&& et_m1256_ll_pr_cre.getText().toString().equals("") == false
						&& et_m1256_ll_ng_cre.getText().toString().equals("") == false) {
					
					pshr = Double.parseDouble(et_m1256_ll_pshr_cre.getText().toString());
					pssr = Double.parseDouble(et_m1256_ll_pssr_cre.getText().toString());
					pr = Double.parseDouble(et_m1256_ll_pr_cre.getText().toString());
					ng = Double.parseDouble(et_m1256_ll_ng_cre.getText().toString());

					mueSQLite.addM1256_LL(new Datos_M1256_LL(
						et_m1256_ll_numero.getText().toString(),
						pshr,
						pssr,
						pr,
						ng,
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