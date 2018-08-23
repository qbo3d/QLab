package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;
import com.qbo3d.qlab.Persistencia.SQLiteProyecto;
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

public class Lista_Proyecto extends Activity {

	TextView pro_titulo;
	TextView list_pro_super;
	ListView lv;
	ArrayAdapter<String> adapterListEns;
	SQLiteProyecto proSQLite;
	List<String> list;
	String usuario;
	String accion;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_obj);

		extra = getIntent().getExtras();
		accion = extra.getString("accion");
		usuario = extra.getString("usuario");
		pro_titulo = (TextView) findViewById(R.id.tv_list_titulo_obj);
		pro_titulo.setText(accion + " proyecto");
		list_pro_super = (TextView) findViewById(R.id.tv_list_super_obj);
		list_pro_super.setText("Usuario: " + usuario);
		lv = (ListView) findViewById(R.id.list_obj);
		proSQLite = new SQLiteProyecto(this);
		
		cargarLV();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(accion.equals("Modificar")){
					callProyectoMod(usuario, position);
				}else if(accion.equals("Consultar")){
					callProyectoCon(usuario, position);
				}

			}
		});
	}

	private void cargarLV() {
		list = new ArrayList<String>(proSQLite.getProyectoNombre(usuario));
		adapterListEns = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>(
						list));
		adapterListEns
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lv.setAdapter(adapterListEns);
	}

	public void callProyectoMod(String usuario, int position) {
		String pro_nombre = list.get(position);
			Intent intent = new Intent();
			intent.setClass(this, Lab_Proyecto_Modificar.class);
			intent.putExtra("usuario", usuario);
			intent.putExtra("pro_nombre", pro_nombre);
			startActivityForResult(intent, REQUEST_CODE);
	}

	public void callProyectoCon(String usuario, int position) {
		String pro_nombre = list.get(position);
			Intent intent = new Intent();
			intent.setClass(this, Lab_Proyecto_Consultar.class);
			intent.putExtra("usuario", usuario);
			intent.putExtra("pro_nombre", pro_nombre);
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