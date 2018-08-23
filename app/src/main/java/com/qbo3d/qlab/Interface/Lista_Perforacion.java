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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Lista_Perforacion extends Activity {

	String accion;
	String pro_nombre;
	String ens_numero;
	String ens_norma;
	int ens_id;
	TextView per_titulo;
	TextView list_per_super;
	ListView lv;
	ArrayAdapter<String> adapterListPer;
	SQLiteEnsayo ensSQLite;
	SQLitePerforacion perSQLite;
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
		per_titulo = (TextView) findViewById(R.id.tv_list_titulo_obj);
		per_titulo.setText(accion + " perforacion");
		list_per_super = (TextView) findViewById(R.id.tv_list_super_obj);
		list_per_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);

		lv = (ListView) findViewById(R.id.list_obj);
		ensSQLite = new SQLiteEnsayo(this);
		perSQLite = new SQLitePerforacion(this);
		ens_id = ensSQLite.getEnsayoId(ens_numero, pro_nombre);

		cargarLV();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(accion.equals("Modificar")){
					callPerforacionMod(position);
				}else if(accion.equals("Consultar")){
					callPerforacionCon(position);
				}

			}
		});
	}

	private void cargarLV() {
		list = new ArrayList<String>(perSQLite.getPerforacionNumero(ens_id));
		adapterListPer = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>(
						list));
		adapterListPer
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lv.setAdapter(adapterListPer);
	}

	public void callPerforacionMod(int position) {
		String per_numero = list.get(position);
			Intent intent = new Intent();
			intent.setClass(this, Lab_Perforacion_Modificar.class);
			intent.putExtra("accion", accion);
			intent.putExtra("pro_nombre", pro_nombre);
			intent.putExtra("ens_numero", ens_numero);
			intent.putExtra("per_numero", per_numero);
			startActivityForResult(intent, REQUEST_CODE);
	}

	public void callPerforacionCon(int position) {
		String per_numero = list.get(position);
			Intent intent = new Intent();
			if(ens_norma.equals("Norma INV E 125 07 Y Norma INV E 126 07"))
				intent.setClass(this, Lab_Perforacion_M1256_Consultar.class);
			else
				intent.setClass(this, Lab_Perforacion_Consultar.class);
			intent.putExtra("accion", accion);
			intent.putExtra("pro_nombre", pro_nombre);
			intent.putExtra("ens_numero", ens_numero);
			intent.putExtra("ens_norma", ens_norma);
			intent.putExtra("per_numero", per_numero);
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