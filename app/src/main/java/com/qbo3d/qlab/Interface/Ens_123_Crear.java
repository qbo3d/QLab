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

public class Ens_123_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	int ens_id;
	int per_id;
	String per_numero;

	TextView tv_m123_super;
	Button bt_m123_crear_perforacion;
	Spinner sp_m123_perforacion;
	Button bt_m123_crear_muestra;

	SQLiteEnsayo ensSQLite;
	SQLitePerforacion perSQLite;

	List<String> listMuestra;
	ArrayAdapter<String> adapterPerforacion;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_123_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");

		tv_m123_super = (TextView) findViewById(R.id.tv_m123_super_cre);
		tv_m123_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero);
		bt_m123_crear_perforacion = (Button) findViewById(R.id.bt_m123_crear_perforacion_cre);
		sp_m123_perforacion = (Spinner) findViewById(R.id.sp_m123_perforacion_cre);
		bt_m123_crear_muestra = (Button) findViewById(R.id.bt_m123_crear_muestra_cre);
		
		ensSQLite = new SQLiteEnsayo(this);
		perSQLite = new SQLitePerforacion(this);
		ens_id = ensSQLite.getEnsayoId(ens_numero, pro_nombre);

		bt_m123_crear_perforacion
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						callCrearPerforacion();
						bt_m123_crear_perforacion.setEnabled(false);
					}
				});

		cargarSp();

		bt_m123_crear_muestra.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callCrearMuestra();
				bt_m123_crear_muestra.setEnabled(false);
			}
		});

		ensSQLite.close();
	}

	private void cargarSp() {
		listMuestra = new ArrayList<String>(perSQLite.getPerforacionNumero(ens_id));
		adapterPerforacion = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listMuestra);
		adapterPerforacion
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m123_perforacion.setAdapter(adapterPerforacion);

		sp_m123_perforacion
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						per_numero = listMuestra.get(position);
						per_id = perSQLite.getPerforacionId(ens_numero, pro_nombre,
								per_numero);
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

	private void callCrearMuestra() {
		Intent intent = new Intent();
		intent.setClass(this, N_INV_123_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("per_numero", per_numero);
		intent.putExtra("per_id", String.valueOf(per_id));
		startActivityForResult(intent, REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			cargarSp();
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