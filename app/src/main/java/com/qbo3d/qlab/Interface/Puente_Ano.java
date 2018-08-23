package com.qbo3d.qlab.Interface;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.qbo3d.qlab.R;

public class Puente_Ano extends Activity {

	TextView tipoObjeto;
	TextView tipoSuper;
	String pro_nombre;
	String ens_numero;
	String ens_norma;
	Bundle extra;
	ListView lv;
	String[] datos = { "Crear", "Consultar / Eliminar" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puente_obj);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		ens_norma = extra.getString("ens_norma");
		tipoObjeto = (TextView) findViewById(R.id.tv_tipo_objeto);
		tipoObjeto.setText("Anotación");
		tipoSuper = (TextView) findViewById(R.id.tv_tipo_super);
		tipoSuper.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);
		lv = (ListView) findViewById(R.id.list);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_puente,
				datos));

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					callCrearAnotacion();
					break;
				case 1:
					callAnotacionLista();
					break;
				}
				;
			}
		});
	}

	private void callCrearAnotacion() {
		Intent intent = new Intent();
		intent.setClass(this, Lab_Ano_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivity(intent);
	}

	private void callAnotacionLista() {
		Intent intent = new Intent();
		intent.setClass(this, Lista_Anotacion.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivity(intent);
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