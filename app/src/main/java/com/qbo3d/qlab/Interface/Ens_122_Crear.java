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

public class Ens_122_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	int ens_id;
	int per_id_grava;
	String per_numero_grava;
	int per_id_arena;
	String per_numero_arena;
	String[] num = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	int cant_grava, cant_arena;
	TextView tv_m122_super;
	Button bt_m122_crear_perforacion;
	Spinner sp_m122_perforacion_grava;
	Spinner sp_m122_cant_grava;
	Button bt_m122_crear_muestra_grava;
	Spinner sp_m122_perforacion_arena;
	Spinner sp_m122_cant_arena;
	Button bt_m122_crear_muestra_arena;
	SQLiteEnsayo ensSQLite;
	SQLitePerforacion perSQLite;
	List<String> listGrava;
	List<String> listArena;
	ArrayAdapter<String> adapterPerforacionGrava;
	ArrayAdapter<String> adapterPerforacionArena;
	ArrayAdapter<String> adapterCantGrava;
	ArrayAdapter<String> adapterCantArena;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_122_crear);

		cant_grava = 1;
		cant_arena = 1;

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");

		tv_m122_super = (TextView) findViewById(R.id.tv_m122_super);
		tv_m122_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero);
		bt_m122_crear_perforacion = (Button) findViewById(R.id.bt_m122_crear_perforacion);
		sp_m122_perforacion_grava = (Spinner) findViewById(R.id.sp_m122_perforacion_grava);
		sp_m122_cant_grava = (Spinner) findViewById(R.id.sp_m122_cant_grava);
		bt_m122_crear_muestra_grava = (Button) findViewById(R.id.bt_m122_crear_muestra_grava);
		sp_m122_perforacion_arena = (Spinner) findViewById(R.id.sp_m122_perforacion_arena);
		sp_m122_cant_arena = (Spinner) findViewById(R.id.sp_m122_cant_arena);
		bt_m122_crear_muestra_arena = (Button) findViewById(R.id.bt_m122_crear_muestra_arena);
		
		ensSQLite = new SQLiteEnsayo(this);
		perSQLite = new SQLitePerforacion(this);
		ens_id = ensSQLite.getEnsayoId(ens_numero, pro_nombre);

		bt_m122_crear_perforacion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callCrearPerforacion();
				bt_m122_crear_perforacion.setEnabled(false);
			}
		});

		cargarSpGrava();

		adapterCantGrava = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, num);
		adapterCantGrava
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m122_cant_grava.setAdapter(adapterCantGrava);

		sp_m122_cant_grava
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						cant_grava = position + 1;
					}
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});

		bt_m122_crear_muestra_grava
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						for (int i = 0; i < cant_grava; i++) {
							callCrearMuestraGrava();
						}
						bt_m122_crear_muestra_grava.setEnabled(false);
					}
				});

		cargarSpArena();

		adapterCantArena = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, num);
		adapterCantArena
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m122_cant_arena.setAdapter(adapterCantArena);

		sp_m122_cant_arena
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						cant_arena = position + 1;
					}
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});

		bt_m122_crear_muestra_arena
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						for (int i = 0; i < cant_arena; i++) {
							callCrearMuestraArena();
						}
						bt_m122_crear_muestra_arena.setEnabled(false);
					}
				});

		ensSQLite.close();
	}
	
	private void cargarSpGrava() {
		listGrava = new ArrayList<String>(perSQLite.getPerforacionNumero(ens_id));
		adapterPerforacionGrava = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listGrava);
		adapterPerforacionGrava
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m122_perforacion_grava.setAdapter(adapterPerforacionGrava);

		sp_m122_perforacion_grava
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						per_numero_grava = listGrava.get(position);
						per_id_grava = perSQLite.getPerforacionId(ens_numero,
								pro_nombre, per_numero_grava);
					}

					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
	}

	private void cargarSpArena() {
		listArena = new ArrayList<String>(perSQLite.getPerforacionNumero(ens_id));
		adapterPerforacionArena = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listArena);
		adapterPerforacionArena
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_m122_perforacion_arena.setAdapter(adapterPerforacionArena);

		sp_m122_perforacion_arena
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						per_numero_arena = listGrava.get(position);
						per_id_arena = perSQLite.getPerforacionId(ens_numero,
								pro_nombre, per_numero_arena);
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

	private void callCrearMuestraGrava() {
		Intent intent = new Intent();
		intent.setClass(this, N_INV_122_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("per_numero", per_numero_grava);
		intent.putExtra("per_id", String.valueOf(per_id_grava));
		intent.putExtra("tipo", "Grava");
		startActivityForResult(intent, REQUEST_CODE);
	}

	private void callCrearMuestraArena() {
		Intent intent = new Intent();
		intent.setClass(this, N_INV_122_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		intent.putExtra("per_numero", per_numero_arena);
		intent.putExtra("per_id", String.valueOf(per_id_arena));
		intent.putExtra("tipo", "Arena");
		startActivityForResult(intent, REQUEST_CODE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			cargarSpGrava();
			cargarSpArena();
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