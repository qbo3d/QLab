package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;
import com.qbo3d.qlab.Persistencia.SQLiteAnotacion;
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

public class Lista_Anotacion extends Activity {

	String pro_nombre;
	String ens_numero;
	
	TextView ano_titulo;
	TextView list_ano_super;
	ListView lv;
	ArrayAdapter<String> adapterListAno;
	SQLiteAnotacion anoSQLite;
	List<String> list;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_obj);

		extra = getIntent().getExtras();;
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		ano_titulo = (TextView) findViewById(R.id.tv_list_titulo_obj);
		ano_titulo.setText("Consultar anotación");
		list_ano_super = (TextView) findViewById(R.id.tv_list_super_obj);
		list_ano_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);

		lv = (ListView) findViewById(R.id.list_obj);
		anoSQLite = new SQLiteAnotacion(this);

		cargarLV();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				callAnotacion(position);
			}
		});
	}

	private void cargarLV() {
		list = new ArrayList<String>(anoSQLite.getAnotacionId(ens_numero, pro_nombre));
		adapterListAno = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>(
						list));
		adapterListAno
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lv.setAdapter(adapterListAno);
	}

	public void callAnotacion(int position) {
		String ens_id = list.get(position);
			Intent intent = new Intent();
			intent.setClass(this, Lab_Ano_Consultar.class);
			intent.putExtra("pro_nombre", pro_nombre);
			intent.putExtra("ens_numero", ens_numero);
			intent.putExtra("ens_id", ens_id);
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