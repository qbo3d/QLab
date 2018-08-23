package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_M127;
import com.qbo3d.qlab.Logica.M127;
import com.qbo3d.qlab.Persistencia.SQLiteM127;
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

public class N_INV_127_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;

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

	TextView tv_m127_super_cre;
	EditText et_m127_numero_cre;
	
	EditText et_m127_w1_cre;
	EditText et_m127_w2_cre;
	EditText et_m127_w3_cre;
	Button bt_m127_w_cre;
	
	TextView tv_m127_w_cre;
	EditText et_m127_v_cre;
	EditText et_m127_vo_cre;
	EditText et_m127_wo_cre;
	EditText et_m127_miw_cre;
	Button bt_m127_lc_cre;

	TextView tv_m127_wo_r_cre;
	TextView tv_m127_vo_r_cre;
	Button bt_m127_r_cre;
	
	TextView tv_m127_r_op_cre;
	TextView tv_m127_op_miw_cre;
	EditText et_m127_gs_cre;
	Button bt_m127_lc_op_cre;
	
	EditText et_m127_wi_cre;
	TextView tv_m127_lc_cre;
	TextView tv_m127_r_cre;
	Button bt_m127_cv_cre;
	
	TextView tv_m127_cv_cre;
	Button bt_m127_cl_cre;
	TextView tv_m127_cl_cre;
	Button bt_m127_crear;

	SQLiteM127 mueSQLite;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_127_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));
		
		tv_m127_super_cre = (TextView) findViewById(R.id.tv_m127_super_cre);
		tv_m127_super_cre.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);
		et_m127_numero_cre = (EditText) findViewById(R.id.et_m127_numero_cre);
		
		et_m127_w1_cre = (EditText) findViewById(R.id.et_m127_w1_cre);
		et_m127_w2_cre = (EditText) findViewById(R.id.et_m127_w2_cre);
		et_m127_w3_cre = (EditText) findViewById(R.id.et_m127_w3_cre);
		bt_m127_w_cre = (Button) findViewById(R.id.bt_m127_w_cre);
		tv_m127_w_cre = (TextView) findViewById(R.id.tv_m127_w_cre);
		et_m127_v_cre = (EditText) findViewById(R.id.et_m127_v_cre);
		et_m127_vo_cre = (EditText) findViewById(R.id.et_m127_vo_cre);
		et_m127_wo_cre = (EditText) findViewById(R.id.et_m127_wo_cre);
		et_m127_miw_cre = (EditText) findViewById(R.id.et_m127_miw_cre);
		bt_m127_lc_cre = (Button) findViewById(R.id.bt_m127_lc_cre);
		tv_m127_wo_r_cre = (TextView) findViewById(R.id.tv_m127_wo_r_cre);
		tv_m127_vo_r_cre = (TextView) findViewById(R.id.tv_m127_vo_r_cre);
		bt_m127_r_cre = (Button) findViewById(R.id.bt_m127_r_cre);
		tv_m127_r_op_cre = (TextView) findViewById(R.id.tv_m127_r_op_cre);
		tv_m127_op_miw_cre = (TextView) findViewById(R.id.tv_m127_op_miw_cre);
		et_m127_gs_cre = (EditText) findViewById(R.id.et_m127_gs_cre);
		bt_m127_lc_op_cre = (Button) findViewById(R.id.bt_m127_lc_op_cre);
		et_m127_wi_cre = (EditText) findViewById(R.id.et_m127_wi_cre);
		tv_m127_lc_cre = (TextView) findViewById(R.id.tv_m127_lc_cre);
		tv_m127_r_cre = (TextView) findViewById(R.id.tv_m127_r_cre);
		bt_m127_cv_cre = (Button) findViewById(R.id.bt_m127_cv_cre);
		tv_m127_cv_cre = (TextView) findViewById(R.id.tv_m127_cv_cre);
		bt_m127_cl_cre = (Button) findViewById(R.id.bt_m127_cl_cre);
		tv_m127_cl_cre = (TextView) findViewById(R.id.tv_m127_cl_cre);
		bt_m127_crear = (Button) findViewById(R.id.bt_m127_crear);
		
		mueSQLite = new SQLiteM127(this);

		bt_m127_w_cre.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (et_m127_w1_cre.getText().toString().equals("") == false
						&& et_m127_w2_cre.getText().toString().equals("") == false
						&& et_m127_w3_cre.getText().toString().equals("") == false) {
					W1 = Double.parseDouble(et_m127_w1_cre.getText().toString());
					W2 = Double.parseDouble(et_m127_w2_cre.getText().toString());
					W3 = Double.parseDouble(et_m127_w3_cre.getText().toString());
					w = M127.lab127calc01(W1, W2, W3);
					tv_m127_w_cre.setText(String.format("%.2f", w));
				} else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		bt_m127_lc_cre.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m127_v_cre.getText().toString().equals("") == false
						&& et_m127_vo_cre.getText().toString().equals("") == false
						&& et_m127_wo_cre.getText().toString().equals("") == false
						&& et_m127_miw_cre.getText().toString().equals("") == false) {
					V = Double.parseDouble(et_m127_v_cre.getText().toString());
					Vo = Double.parseDouble(et_m127_vo_cre.getText().toString());
					Wo = Double.parseDouble(et_m127_wo_cre.getText().toString());
					miw = Double.parseDouble(et_m127_miw_cre.getText().toString());
					LC = M127.lab127calc03(w, V, Vo, miw, Wo);
					tv_m127_op_miw_cre.setText(String.format("%.2f", miw));
					tv_m127_wo_r_cre.setText(String.format("%.2f", Wo));
					tv_m127_vo_r_cre.setText(String.format("%.2f", Vo));
					tv_m127_lc_cre.setText(String.format("%.2f", LC));
				} else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		bt_m127_r_cre.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m127_vo_cre.getText().toString().equals("") == false
						&& et_m127_wo_cre.getText().toString().equals("") == false) {
					R_var = M127.lab127calc05(Wo, Vo);
					tv_m127_r_op_cre.setText(String.format("%.2f", R_var));
					tv_m127_r_cre.setText(String.format("%.2f", R_var));
				} else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		bt_m127_lc_op_cre.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m127_gs_cre.getText().toString().equals("") == false) {
					Gs = Double.parseDouble(et_m127_gs_cre.getText().toString());
					LC = M127.lab127calc04(R_var, Gs);
					tv_m127_lc_cre.setText(String.format("%.2f", LC));
				} else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		bt_m127_cv_cre.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m127_wi_cre.getText().toString().equals("") == false) {
					Wi = Double.parseDouble(et_m127_wi_cre.getText().toString());
					CV = M127.lab127calc06(Wi, LC, R_var);
					tv_m127_cv_cre.setText(String.format("%.2f", CV));
				} else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		bt_m127_cl_cre.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CL = M127.lab127calc07(CV);
				tv_m127_cl_cre.setText(String.format("%.2f", CL));
			}
		});
		
		bt_m127_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m127_numero_cre.getText().toString().equals("") == false) {
					
					mueSQLite.addM127(new Datos_M127(
						et_m127_numero_cre.getText().toString(),
						w,
						W1,
						W2,
						W3,
						LC,
						V,
						Vo,
						Wo,
						miw,
						R_var,
						Gs,
						CV,
						Wi,
						CL,
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