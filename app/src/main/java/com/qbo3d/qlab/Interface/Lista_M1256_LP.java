package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_LP;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Lista_M1256_LP extends Activity {

	String accion;
	String pro_nombre;
	String ens_numero;
	String ens_norma;
	String per_numero;
	int per_id;
	TextView mue_titulo;
	TextView list_mue_super;
	ListView lv;
	ArrayAdapter<String> adapterListMue;
	SQLiteM1256_LP mueSQLite;
	List<String> list;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_obj);

		extra = getIntent().getExtras();
		accion = extra.getString("accion");
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		ens_norma = extra.getString("ens_norma");
		per_numero = extra.getString("per_numero");
		per_id = Integer.parseInt(extra.getString("per_id"));
		mue_titulo = (TextView) findViewById(R.id.tv_list_titulo_obj);
		mue_titulo.setText("Consultar Muestra");
		list_mue_super = (TextView) findViewById(R.id.tv_list_super_obj);
		list_mue_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero
				+ "\nPerforación: " + per_numero);

		lv = (ListView) findViewById(R.id.list_obj);
		mueSQLite = new SQLiteM1256_LP(this);
		
		cargarLV();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(accion.equals("Consultar")){
					callMuestraCon(position);
				}
			}
		});
	}

	private void cargarLV() {
		list = new ArrayList<String>(mueSQLite.getM1256_LPNumero(per_id));
		adapterListMue = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>(
						list));
		adapterListMue
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lv.setAdapter(adapterListMue);
	}

	public void callMuestraCon(int position) {
		String mue_numero = list.get(position);
			Intent intent = new Intent();
			intent.setClass(this, N_INV_1256_LP_Consultar.class);
			intent.putExtra("pro_nombre", pro_nombre);
			intent.putExtra("ens_numero", ens_numero);
			intent.putExtra("per_numero", per_numero);
			intent.putExtra("per_id", String.valueOf(per_id));
			intent.putExtra("mue_numero", mue_numero);
			startActivityForResult(intent, REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			cargarLV();
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
		inflater.inflate(R.menu.menu_ri, menu);
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
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}