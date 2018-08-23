package com.qbo3d.qlab.Interface;

import android.app.Activity;
import android.os.Bundle;

import com.qbo3d.qlab.R;

public class Herr_Ayuda extends Activity {

	Bundle extra;
	int item;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_fondo_ayuda_00);

		extra = getIntent().getExtras();
		item = Integer.parseInt(extra.getString("item"));

		switch (item) {
		case 0:
			setContentView(R.layout.tab_fondo_ayuda_00);
			break;
		case 1:
			setContentView(R.layout.tab_fondo_ayuda_01);
			break;
		case 2:
			setContentView(R.layout.tab_fondo_ayuda_02);
			break;
		case 3:
			setContentView(R.layout.tab_fondo_ayuda_03);
			break;
		case 4:
			setContentView(R.layout.tab_fondo_ayuda_04);
			break;
		case 5:
			setContentView(R.layout.tab_fondo_ayuda_05);
			break;
		case 6:
			setContentView(R.layout.tab_fondo_ayuda_06);
			break;
		case 7:
			setContentView(R.layout.tab_fondo_ayuda_07);
			break;
		case 8:
			setContentView(R.layout.tab_fondo_ayuda_08);
			break;
		case 9:
			setContentView(R.layout.tab_fondo_ayuda_09);
			break;
		case 10:
			setContentView(R.layout.tab_fondo_ayuda_10);
			break;
		case 11:
			setContentView(R.layout.tab_fondo_ayuda_11);
			break;
		}
	}
}
