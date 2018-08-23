package com.qbo3d.qlab.Interface;

import java.io.File;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.qbo3d.qlab.R;

public class Herr_Equipos extends Activity {

	String titulo;
	final String path = "/UD-Lab/Imagenes/";
	String pathImg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.herr_equipo);

		Bundle datosMain = getIntent().getExtras();
		
		titulo = cambiarCatacteres(datosMain.getString("imagenIntent"));
		
		setTitle(titulo);
		ImageView imageView = (ImageView) findViewById(R.id.imageView);

		pathImg = Environment.getExternalStorageDirectory() + path + titulo;
		File imgFile = new  File(pathImg);
	    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
	    imageView.setImageBitmap(myBitmap);
	}

	private String cambiarCatacteres(String cadena) {
		cadena = cadena.replace("á", "a");
		cadena = cadena.replace("é", "e");
		cadena = cadena.replace("í", "i");
		cadena = cadena.replace("ó", "o");
		cadena = cadena.replace("ú", "u");
		cadena = cadena.replace("ñ", "ni");
		cadena = cadena.replace('"', ' ');
		cadena = cadena.replace("/", " ");
//		cadena = cadena.replace("?", "mi");
		return cadena;
	}

}
