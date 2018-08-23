package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_Ensayo;
import com.qbo3d.qlab.Logica.Datos_Proyecto;
import com.qbo3d.qlab.Persistencia.SQLiteEnsayo;
import com.qbo3d.qlab.Persistencia.SQLiteProyecto;
import com.qbo3d.qlab.Persistencia.SQLiteUsuario;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Ini_Proyecto extends Activity {

	TextView pro_nombre;
	String pro_nombre_str;
	int usu_cedula;
	TextView pro_localizacion;
	TextView pro_fecha;
	String pro_fecha_str;
	int pro_usu_cedula_fk_int;
	TextView pro_usu_cedula_fk_pos;
	ListView lv;
	ArrayAdapter<String> adapterListEns;
	List<String> list;
	Datos_Proyecto pro;
	Datos_Ensayo ens;
	String ens_norma;
	String ens_numero;
	Bundle extra;
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iniciar_proyecto);

		pro_nombre = (TextView) findViewById(R.id.sp_pro_nombre);
		pro_localizacion = (TextView) findViewById(R.id.et_pro_localizacion);
		pro_fecha = (TextView) findViewById(R.id.dp_pro_fecha);
		pro_usu_cedula_fk_pos = (TextView) findViewById(R.id.tv_pro_usu_cedula_fk_pos);
		
		extra = getIntent().getExtras();
		pro_nombre_str = extra.getString("proyecto");
		callLlenarET();

		lv = (ListView) findViewById(R.id.ini_list);

		cargarLV();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				llenarEns(position);
				callIniciarLab(position);
			}
		});
	}
	
	public void llenarEns(int position){
		SQLiteEnsayo ensSQLite = new SQLiteEnsayo(this);
		ens_numero = list.get(position);
		ens = ensSQLite.getEnsayo(ens_numero, pro_nombre_str);
		ens_norma = ens.getEnsNorma();
		ensSQLite.close();
	}

	public void callIniciarLab(int position) {
		Intent intent = new Intent();
		intent.setClass(this, Elv_Laboratorio.class);
		intent.putExtra("normaIntent", ens_norma );
		intent.putExtra("usu_cedula", String.valueOf(usu_cedula));
		intent.putExtra("pro_nombre", pro_nombre_str);
		intent.putExtra("ens_numero", ens_numero);
		startActivityForResult(intent, REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			cargarLV();
		}
	}

	private void cargarLV() {
		SQLiteEnsayo ensSQLite = new SQLiteEnsayo(this);
		list = new ArrayList<String>(ensSQLite.getEnsayoNumero(pro_nombre_str));
		adapterListEns = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>(
						list));
		adapterListEns
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lv.setAdapter(adapterListEns);
		ensSQLite.close();
	}

	private void callLlenarET() {
		SQLiteUsuario usuSQLite = new SQLiteUsuario(this);
		SQLiteProyecto proSQLite = new SQLiteProyecto(this);
		pro = proSQLite.getProyecto(pro_nombre_str);
		usu_cedula = pro.getProUsuCedulaFk();
		pro_nombre.setText(" " + pro.getProNombre());
		pro_usu_cedula_fk_pos.setText(" " + usuSQLite.getUsuarioNC(usu_cedula));
		pro_localizacion.setText(" " + pro.getProLocalizacion());

		pro_fecha_str = pro.getProFecha();
		int i1 = 0, i2 = 0;
		boolean b1 = true;

		for (int i = 0; i < pro_fecha_str.length(); i++) {
			if (pro_fecha_str.charAt(i) == '-') {
				if (b1 == true) {
					i1 = i;
					b1 = false;
				} else {
					i2 = i;
				}
			}
		}
		;

		pro_fecha.setText(" " + Integer.parseInt(pro_fecha_str.substring(0, i1))
				+ " / "
				+ (Integer.parseInt(pro_fecha_str.substring(i1 + 1, i2)) + 1)
				+ " / " + Integer.parseInt(pro_fecha_str.substring(i2 + 1)));
		usuSQLite.close();
	}
}