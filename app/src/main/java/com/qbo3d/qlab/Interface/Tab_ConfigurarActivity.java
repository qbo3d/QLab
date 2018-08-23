package com.qbo3d.qlab.Interface;

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

import com.qbo3d.qlab.R;

public class Tab_ConfigurarActivity extends Fragment {

	Activity activity;
	Button bt_administracion;

	// newInstance constructor for creating fragment with arguments
	public static Tab_ConfigurarActivity newInstance(int page, String title) {
		Tab_ConfigurarActivity fragmentFirst = new Tab_ConfigurarActivity();
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
		View view = inflater.inflate(R.layout.tab_fondo_configuracion, container, false);

		bt_administracion = (Button) view.findViewById(R.id.bt_administracion);
		bt_administracion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callUsuario();
			}
		});

		return view;
	}

	private void callUsuario() {
		Intent intent = new Intent();
		intent.setClass(activity, Puente_Usu.class);
		intent.putExtra("objeto", "Usuario");
		startActivity(intent);
	}
}
