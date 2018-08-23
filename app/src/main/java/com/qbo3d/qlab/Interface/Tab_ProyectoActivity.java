package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;
import com.qbo3d.qlab.Persistencia.SQLiteProyecto;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Tab_ProyectoActivity extends Fragment {

	Activity activity;
	ListView lv;
	ArrayAdapter<String> adapterListPro;
	SQLiteProyecto proSQLite;
	Button bt_list_pro;
	List<String> list;

	// newInstance constructor for creating fragment with arguments
	public static Tab_ProyectoActivity newInstance(int page, String title) {
		Tab_ProyectoActivity fragmentFirst = new Tab_ProyectoActivity();
		Bundle args = new Bundle();
		args.putInt("someInt", page);
		args.putString("someTitle", title);
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = getActivity();
	}

	// Inflate the view for the fragment based on layout XML
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab_fondo_proyecto, container, false);

		lv = (ListView) view.findViewById(R.id.list);
		proSQLite = new SQLiteProyecto(activity);

		cargarLV();

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				if (list != null) {
					callIniciarProyecto(position);
				}
			}
		});

		return view;
	}

	private void cargarLV() {
		list = new ArrayList<String>(proSQLite.getProyectoNombre());
		adapterListPro = new ArrayAdapter<String>(activity,
				android.R.layout.simple_list_item_1,
				new ArrayList<String>(list));
		adapterListPro
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lv.setAdapter(adapterListPro);
	}

	public void callIniciarProyecto(int position) {
		String proyecto = list.get(position);
		try {
			Intent intent = new Intent();
			intent.setClass(activity, Ini_Proyecto.class);
			intent.putExtra("proyecto", proyecto);
			startActivity(intent);
		} catch (IndexOutOfBoundsException ex) {
			Toast.makeText(activity,
					"este es el error" + ex.toString(), Toast.LENGTH_SHORT)
					.show();
		}
	}
}