package com.qbo3d.qlab.Interface;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qbo3d.qlab.Persistencia.SQLiteUd_Lab;
import com.qbo3d.qlab.R;

import android.os.Bundle;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class Elv_Laboratorio extends ExpandableListActivity {

	private static final String NOMBRE = "NOMBRE";
	private static final String IS_EVEN = "IS_EVEN";
	private ExpandableListAdapter elAdapter;
	List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
	InputStream is;
	boolean addDescripcion = true;
	boolean addTitulo;
	HashMap<String, String> tituloMap = new HashMap<String, String>();
	HashMap<String, String> descMap = new HashMap<String, String>();
	int count = 0;
	int count1 = -1;
	int count2 = 0;
	List<String> descCount = new ArrayList<String>();
	String normaIntent;
	String imagenIntent;
	int usu_cedula;
	String pro_nombre;
	String ens_numero;
	Bundle datosMain;
	private static final int REQUEST_CODE = 10;
	SQLiteUd_Lab db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elv_laboratorio_main);

		datosMain = getIntent().getExtras();
		usu_cedula = Integer.parseInt(datosMain.getString("usu_cedula"));
		pro_nombre = datosMain.getString("pro_nombre");
		ens_numero = datosMain.getString("ens_numero");
		normaIntent = datosMain.getString("normaIntent");
		setTitle(normaIntent);

		switch (Integer.parseInt(normaIntent.substring(12, 15))) {
			case 122:
				is = this.getResources().openRawResource(R.raw.norma122);
				break;
			case 123:
				is = this.getResources().openRawResource(R.raw.norma123);
				break;
			case 125:
				is = this.getResources().openRawResource(R.raw.norma125);
				break;
			case 127:
				is = this.getResources().openRawResource(R.raw.norma127);
				break;
			case 142:
				is = this.getResources().openRawResource(R.raw.norma142);
				break;
			case 161:
				is = this.getResources().openRawResource(R.raw.norma161);
				break;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String line = "";

			while ((line = reader.readLine()) != null) {
				String[] strings = line.split("&");
				String ttl = strings[0].trim();
				String dsc = strings[1].trim();

				if (tituloMap.containsValue(ttl)) {
					addTitulo = false;
					descMap.put("descripcion" + count1 + count2, dsc);
					count2++;
				} else {
					count1++;
					descCount.add("" + count2);
					count2 = 0;
					tituloMap.put("titulo" + count, ttl);
					descMap.put("descripcion" + count1 + count2, dsc);
					count2++;
					count++;
				}
			}
		} catch (Exception e) {
		}

		descCount.add("" + count2);
		descCount.remove(0);

		for (int i = 0; i < tituloMap.size(); i++) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			String ctry = tituloMap.get("titulo" + i);
			curGroupMap.put(NOMBRE, ctry);
			curGroupMap.put(IS_EVEN, "Titulo " + i);
			List<Map<String, String>> children = new ArrayList<Map<String, String>>();
			int k = Integer.parseInt(descCount.get(i));
			for (int j = 0; j < k; j++) {
				Map<String, String> curChildMap = new HashMap<String, String>();
				children.add(curChildMap);
				curChildMap.put(NOMBRE, descMap.get("descripcion" + i + j));
				curChildMap.put(IS_EVEN, "Lab " + j);
			}
			childData.add(children);
		}

		elAdapter = new SimpleExpandableListAdapter(this, groupData,
				R.layout.elv_laboratorio_group,
				new String[] { NOMBRE, IS_EVEN }, new int[] { R.id.groupLab },
				childData, R.layout.elv_laboratorio_child, new String[] {
						NOMBRE, IS_EVEN }, new int[] { R.id.childLab });
		setListAdapter(elAdapter);
	}

	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {

		String op = descMap.get("descripcion" + groupPosition + childPosition);

		if ("Cálculo ".equals(op.substring(0, op.length() - 2))) {
			imagenIntent = "calc" + op.substring(op.length() - 2, op.length());
			imagen();
		}

		else if ("Cuadro ".equals(op.substring(0, op.length() - 2))) {
			imagenIntent = "cuad" + op.substring(op.length() - 2, op.length());
			imagen();
		}

		else if ("Figura ".equals(op.substring(0, op.length() - 2))) {
			imagenIntent = "figu" + op.substring(op.length() - 2, op.length());
			imagen();
		}

		else if ("Tabla ".equals(op.substring(0, op.length() - 2))) {
			imagenIntent = "tabl" + op.substring(op.length() - 2, op.length());
			imagen();
		} else if ("Desarrollar laboratorio".equals(op)) {
			desLab();
		} else if (op.length() < 53 && op.substring(0) != "1"
				&& op.substring(0) != "2" && op.substring(0) != "3"
				&& op.substring(0) != "4" && op.substring(0) != "5"
				&& op.substring(0) != "6" && op.substring(0) != "7") {
			imagenIntent = op;
			equipo();
		}
		return false;
	}

//	private void calculo() {
//		Intent intent = new Intent();
//		intent.setClass(this, Herr_Calculos.class);
//		intent.putExtra("imagenIntent", "lab" + normaIntent.substring(12, 15) + imagenIntent);
//		startActivity(intent);
//	}

	private void imagen() {
		Intent intent = new Intent();
		intent.setClass(this, Herr_Imagenes.class);
		intent.putExtra("imagenIntent", "lab" + normaIntent.substring(12, 15) + imagenIntent);
		startActivity(intent);
	}

	private void equipo() {
		Intent intent = new Intent();
		intent.setClass(this, Herr_Equipos.class);
		intent.putExtra("imagenIntent", imagenIntent);
		startActivity(intent);
	}

	private void desLab() {
		Intent intent = new Intent();
		switch (Integer.parseInt(normaIntent.substring(12, 15))) {
			case 122:
				intent.setClass(this, Puente_Ens_M122.class);
				break;
			case 123:
				intent.setClass(this, Puente_Ens_M123.class);
				break;
			case 125:
				intent.setClass(this, Puente_Ens_M1256.class);
				break;
			case 127:
				intent.setClass(this, Puente_Ens_M127.class);
				break;
		}
		intent.putExtra("usu_cedula", String.valueOf(usu_cedula));
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
