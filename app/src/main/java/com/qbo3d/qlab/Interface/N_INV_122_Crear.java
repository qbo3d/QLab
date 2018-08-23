package com.qbo3d.qlab.Interface;

import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M122;
import com.qbo3d.qlab.Logica.M122;
import com.qbo3d.qlab.Persistencia.SQLiteM122;
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

public class N_INV_122_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	String per_numero;
	int per_id;

	Double w;
	Double W1;
	Double W2;
	Double Wc;
	Double Ww;
	Double Ws;

	TextView tv_m122_super;
	EditText et_m122_numero;
	TextView tv_m122_tipo;
	String tv_m122_tipo_str;
	EditText et_m122_w1;
	EditText et_m122_w2;
	EditText et_m122_wc;

	ArrayAdapter<String> adapterTipo;
	List<String> listTipo;
	SQLiteM122 mueSQLite;
	Button bt_m122_crear;
	Bundle extra;
	Intent data;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.n_inv_122_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));
		tv_m122_tipo_str = extra.getString("tipo");

		tv_m122_super = (TextView) findViewById(R.id.tv_m122_super_cre);
		tv_m122_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero + "\nPerforación: " + per_numero);
		et_m122_numero = (EditText) findViewById(R.id.et_m122_numero_cre);
		tv_m122_tipo = (TextView) findViewById(R.id.tv_m122_tipo2_cre);
		tv_m122_tipo.setText(tv_m122_tipo_str);
		et_m122_w1 = (EditText) findViewById(R.id.et_m122_w1_cre);
		et_m122_w2 = (EditText) findViewById(R.id.et_m122_w2_cre);
		et_m122_wc = (EditText) findViewById(R.id.et_m122_wc_cre);

		mueSQLite = new SQLiteM122(this);
		
		bt_m122_crear = (Button) findViewById(R.id.bt_m122_crear);
		bt_m122_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (et_m122_numero.getText().toString().equals("") == false
						&& et_m122_w1.getText().toString().equals("") == false
						&& et_m122_w2.getText().toString().equals("") == false
						&& et_m122_wc.getText().toString().equals("") == false) {
					
					W1 = Double.parseDouble(et_m122_w1.getText().toString());
					W2 = Double.parseDouble(et_m122_w2.getText().toString());
					Wc = Double.parseDouble(et_m122_wc.getText().toString());
					Ww = M122.lab122calc04(W1, W2);
					Ws = M122.lab122calc05(W2, Wc);
					w = M122.lab122calc03(Ww,Ws);

					mueSQLite.addM122(new Datos_M122(
						et_m122_numero.getText().toString(),
						tv_m122_tipo_str,
						w,
						W1,
						W2,
						Wc,
						Ww, 
						Ws, 
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