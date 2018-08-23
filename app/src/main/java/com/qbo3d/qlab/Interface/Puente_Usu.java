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
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.qbo3d.qlab.R;

public class Puente_Usu extends Activity {

	TextView tipoObjeto;
	TextView tipoSuper;
	String objeto;
	Bundle extra;
	ListView lv;
	String[] datos = { "Crear", "Modificar", "Consultar / Eliminar" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puente_obj);

		extra = getIntent().getExtras();
		objeto = extra.getString("objeto");
		tipoObjeto = (TextView) findViewById(R.id.tv_tipo_objeto);
		tipoObjeto.setText(objeto);
		tipoSuper = (TextView) findViewById(R.id.tv_tipo_super);
		tipoSuper.setText("");
		lv = (ListView) findViewById(R.id.list);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_puente,
				datos));

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					callCrearUsuario();
					break;
				case 1:
					callUsuarioLista("Modificar");
					break;
				case 2:
					callUsuarioLista("Consultar");
					break;
				}
				;
			}
		});
	}

	private void callCrearUsuario() {
		Intent intent = new Intent();
		intent.setClass(this, Lab_Usuario_Crear.class);
		startActivity(intent);
	}

	private void callUsuarioLista(String accion) {
		Intent intent = new Intent();
		intent.setClass(this, Lista_Usuario.class);
		intent.putExtra("accion", accion);
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