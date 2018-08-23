package com.qbo3d.qlab.Interface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qbo3d.qlab.R;

public class Tab_AyudaActivity extends Fragment {

	Activity activity;
	ListView lv;
	String[] datos = { "Pestaña Proyectos", "Listado de Normas",
			"Visor de normas", "Herramientas de Visualización",
			"Pestaña Configurar", "Crear", "Modificar", "Consultar",
			"Eliminar", "Ingresar Anotación", "Ingresar Imagen",
			"Generar Informe", "Listado de Laboratorios" };

	public static Tab_AyudaActivity newInstance(int page, String title) {
		Tab_AyudaActivity fragmentFirst = new Tab_AyudaActivity();
		Bundle args = new Bundle();
		args.putInt("someInt", page);
		args.putString("someTitle", title);
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab_fondo_ayuda, container, false);

		lv = view.findViewById(R.id.list_ayuda);
		lv.setAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, datos));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				if(position == 12)
					listadoLabs();
				else
					callAyuda(position);
			}
		});

		return view;
	}

	private void listadoLabs() {
		Intent intent = new Intent();
		intent.setClass(activity, Elv_Norma.class);
		startActivity(intent);
	}

	private void callAyuda(int item) {
		Intent intent = new Intent();
		intent.setClass(activity, Herr_Ayuda.class);
		intent.putExtra("item", String.valueOf(item));
		startActivity(intent);
	}
}
