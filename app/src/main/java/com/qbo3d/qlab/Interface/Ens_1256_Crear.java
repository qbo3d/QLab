package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;
import com.qbo3d.qlab.Persistencia.SQLiteEnsayo;
import com.qbo3d.qlab.Persistencia.SQLitePerforacion;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Ens_1256_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	int ens_id;
	int per_id;
	String per_numero;
	String[] num = { "1", "2", "3" };
	int cant_ll, cant_lp;
	TextView tv_m1256_super;
	Button bt_m1256_crear_perforacion;
	Spinner sp_m1256_perforacion;
	Spinner sp_m1256_cant_ll;
	Button bt_m1256_crear_muestra_ll;
	Spinner sp_m1256_cant_lp;
	Button bt_m1256_crear_muestra_lp;
	Button bt_m1256_crear_muestra_hn;
	SQLiteEnsayo ensSQLite;
	SQLitePerforacion perSQLite;
	List<String> listPer;
	List<String> listLL;
	List<String> listLP;
	ArrayAdapter<String> adapterPerforacion;
	ArrayAdapter<String> adapterCantLL;
	ArrayAdapter<String> adapterCantLP;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_1256_crear);

		cant_ll = 1;
		cant_lp = 1;

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");

		tv_m1256_super = (TextView) findViewById(R.id.tv_m1256_super);
		tv_m1256_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero);
		bt_m1256_crear_perforacion = (Button) findViewById(R.id.bt_m1256_crear_perforacion);
		sp_m1256_perforacion = (Spinner) findViewById(R.id.sp_m1256_perforacion);
		sp_m1256_cant_ll = (Spinner) findViewById(R.id.sp_m1256_cant_ll);
		bt_m1256_crear_muestra_ll = (Button) findViewById(R.id.bt_m1256_crear_muestra_ll);
		sp_m1256_cant_lp = (Spinner) findViewById(R.id.sp_m1256_cant_lp);
		bt_m1256_crear_muestra_lp = (Button) findViewById(R.id.bt_m1256_crear_muestra_lp);
		bt_m1256_crear_muestra_hn = (Button) findViewById(R.id.bt_m1256_crear_muestra_hn);
		
		ensSQLite = new SQLiteEnsayo(this);
		perSQLite = new SQLitePerforacion(this);
		ens_id = ensSQLite.getEnsayoId(ens_numero, pro_nombre);

		bt_m1256_crear_perforacion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callCrearPerforacion();
				bt_m1256_crear_perforacion.setEnabled(false);
			}
		});

		cargarSpPerforacion();

		adapterCantLL = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, num);
		adapterCantLL
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m1256_cant_ll.setAdapter(adapterCantLL);

		sp_m1256_cant_ll
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						cant_ll = position + 1;
					}
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});

		bt_m1256_crear_muestra_ll
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						for (int i = 0; i < cant_ll; i++) {
							callCrearMuestraLL();
							bt_m1256_crear_muestra_ll.setEnabled(false);
						}
					}
				});

		adapterCantLP = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, num);
		adapterCantLP
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m1256_cant_lp.setAdapter(adapterCantLP);

		sp_m1256_cant_lp
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						cant_lp = position + 1;
					}
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});

		bt_m1256_crear_muestra_lp
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						for (int i = 0; i < cant_lp; i++) {
							callCrearMuestraLP();
							bt_m1256_crear_muestra_lp.setEnabled(false);
						}
					}
				});

		bt_m1256_crear_muestra_hn
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
							callCrearMuestraHN();
							bt_m1256_crear_muestra_hn.setEnabled(false);
					}
				});

		ensSQLite.close();
	}
	
	private void cargarSpPerforacion() {
		listPer = new ArrayList<String>(perSQLite.getPerforacionNumero(ens_id));
		adapterPerforacion = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listPer);
		adapterPerforacion
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m1256_perforacion.setAdapter(adapterPerforacion);

		sp_m1256_perforacion
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						per_numero = listPer.get(position);
						per_id = perSQLite.getPerforacionId(ens_numero,
								pro_nombre, per_numero);
					}

					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
	}

	private void callCrearPerforacion() {
		Intent intent = new Intent();
		intent.setClass(this, Lab_Perforacion_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivityForResult(intent, REQUEST_CODE);
	}

	private void callCrearMuestraLL() {
		Intent intent = new Intent();
		intent.setClass(this, N_INV_1256_LL_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("per_numero", per_numero);
		intent.putExtra("per_id", String.valueOf(per_id));
		intent.putExtra("tipo", "Grava");
		startActivityForResult(intent, REQUEST_CODE);
	}

	private void callCrearMuestraLP() {
		Intent intent = new Intent();
		intent.setClass(this, N_INV_1256_LP_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("per_numero", per_numero);
		intent.putExtra("per_id", String.valueOf(per_id));
		intent.putExtra("tipo", "Arena");
		startActivityForResult(intent, REQUEST_CODE);
	}

	private void callCrearMuestraHN() {
		Intent intent = new Intent();
		intent.setClass(this, N_INV_1256_HN_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("per_numero", per_numero);
		intent.putExtra("per_id", String.valueOf(per_id));
		intent.putExtra("tipo", "Arena");
		startActivityForResult(intent, REQUEST_CODE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			cargarSpPerforacion();
		}
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