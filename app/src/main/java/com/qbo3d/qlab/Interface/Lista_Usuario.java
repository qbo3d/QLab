package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;

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

import com.qbo3d.qlab.Persistencia.SQLiteUsuario;
import com.qbo3d.qlab.R;

public class Lista_Usuario extends Activity {
	
	TextView usu_titulo;
	TextView list_usu_super;
	ListView lv;
	ArrayAdapter<String> adapterListUsu;
	SQLiteUsuario usuSQLite;
	List<String> list;
	String accion;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_obj);

		extra = getIntent().getExtras();
		accion = extra.getString("accion");
		usu_titulo = (TextView) findViewById(R.id.tv_list_titulo_obj);
		usu_titulo.setText(accion + " usuario");
		list_usu_super = (TextView) findViewById(R.id.tv_list_super_obj);
		list_usu_super.setText("Usuario");
		lv = (ListView) findViewById(R.id.list_obj);
		usuSQLite = new SQLiteUsuario(this);
		
		cargarLV();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(accion.equals("Modificar")){
					callUsuarioMod(position);
				}else if(accion.equals("Consultar")){
					callUsuarioCon(position);
				}

			}
		});
	}

	private void cargarLV() {
		list = new ArrayList<String>(usuSQLite.getUsuariosId());
		adapterListUsu = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>(
						list));
		adapterListUsu
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lv.setAdapter(adapterListUsu);
	}

	public void callUsuarioMod(int position) {
		String usuario = list.get(position);
			Intent intent = new Intent();
			intent.setClass(this, Lab_Usuario_Modificar.class);
			intent.putExtra("usuario", usuario);
			startActivityForResult(intent, REQUEST_CODE);
	}

	public void callUsuarioCon(int position) {
		String usuario = list.get(position);
			Intent intent = new Intent();
			intent.setClass(this, Lab_Usuario_Consultar.class);
			intent.putExtra("usuario", usuario);
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
