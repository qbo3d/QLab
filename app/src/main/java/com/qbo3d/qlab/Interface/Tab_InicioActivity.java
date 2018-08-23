package com.qbo3d.qlab.Interface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qbo3d.qlab.R;

public class Tab_InicioActivity extends Fragment {

	Activity activity;
	Button bt_creditos;

	// newInstance constructor for creating fragment with arguments
	public static Tab_InicioActivity newInstance(int page, String title) {
		Tab_InicioActivity fragmentFirst = new Tab_InicioActivity();
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
		View view = inflater.inflate(R.layout.tab_fondo_inicio, container, false);

		bt_creditos = (Button) view.findViewById(R.id.bt_creditos);
		bt_creditos.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callInicio();
			}
		});

		return view;
	}

	private void callInicio() {
		Intent intent = new Intent();
		intent.setClass(activity, Creditos.class);
		startActivity(intent);
	}
}
