package com.qbo3d.qlab.Interface;

import java.io.File;
import com.qbo3d.qlab.Logica.Imagen;
import com.qbo3d.qlab.R;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class Herr_Imagenes extends Activity {

	String titulo;
	String prefijo = "";
	final String path = "/UD-Lab/Imagenes/";
	String pathImg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.herr_imagenes);

		Bundle datosMain = getIntent().getExtras();
		titulo = datosMain.getString("imagenIntent");

		if ("cuad".equals(titulo.substring(6, 10)))
			prefijo = "Cuadro ";
		if ("figu".equals(titulo.substring(6, 10)))
			prefijo = "Figura ";
		if ("tabl".equals(titulo.substring(6, 10)))
			prefijo = "Tabla ";

		setTitle(prefijo + titulo.substring(10, 12));
		ImageView imageView = (ImageView) findViewById(R.id.imageView);

		pathImg = Environment.getExternalStorageDirectory() + path + titulo;
		File imgFile = new  File(pathImg);
	    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
	    imageView.setImageBitmap(myBitmap);
	    imageView.setOnTouchListener(new Imagen());

	}

}
