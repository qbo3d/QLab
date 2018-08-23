package com.qbo3d.qlab.Interface;

import android.os.Bundle;
import android.app.Activity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.qbo3d.qlab.Logica.Datos_Captura;
import com.qbo3d.qlab.Persistencia.SQLiteCaptura;
import com.qbo3d.qlab.Persistencia.SQLiteEnsayo;
import com.qbo3d.qlab.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Cap_Crear extends Activity {

	String pro_nombre;
	String ens_numero;
	int ens_id;
	TextView tv_cap_super;
	EditText et_cap_nota;
	Button bt_cap_guardar;
	ImageButton imageView;
	Uri d_path;
	String imagen;
	File destination;
	File UD_Lab;
	Bundle extra;
	final String path = "/UD-Lab/Avatar/";
	private static final int SELECT_PICTURE = 2;
	SQLiteEnsayo ensSQLite;
	SQLiteCaptura capSQLite;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_cap_crear);

		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		imagen = extra.getString("Id");
		imagen = imagen + ".jpg";
		
		tv_cap_super = (TextView) findViewById(R.id.tv_cap_super_cre);
		tv_cap_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: "
				+ ens_numero);
		et_cap_nota = (EditText) findViewById(R.id.et_cap_nota_cre);
		
		ensSQLite = new SQLiteEnsayo(this);
		capSQLite = new SQLiteCaptura(this);
		
		ens_id = ensSQLite.getEnsayoId(ens_numero, pro_nombre);
		
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			UD_Lab = new File(Environment.getExternalStorageDirectory(), path);
			if (!UD_Lab.exists()) {
				UD_Lab.mkdirs();
			}
		}
		
		imageView = (ImageButton) findViewById(R.id.iv_cap_imagen_cre);
		imageView.setOnClickListener(capturar);

		bt_cap_guardar = (Button) findViewById(R.id.bt_cap_guardar);
		bt_cap_guardar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (et_cap_nota.getText().toString().equals("") == false) {
					capSQLite.addCaptura(new Datos_Captura(
							d_path.toString(),
							et_cap_nota.getText().toString(),
							ens_id));
					regresar();
					finish();					
				} 
				else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Uri selectedImage = data.getData();
		d_path = selectedImage;

		if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
			imageView.setImageURI(selectedImage);
		}
	}

	private View.OnClickListener capturar = new View.OnClickListener() {
		public void onClick(View v) {
			destination = new File(Environment.getExternalStorageDirectory()
					+ path, imagen);
			d_path = Uri.fromFile(destination);
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			int code = SELECT_PICTURE;
			intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
			startActivityForResult(intent, code);
		}
	};

	public void regresar() {
		Intent data = new Intent();
		data.putExtra("path", d_path.toString());
		setResult(RESULT_OK, data);
	}

	public static Bitmap strImg(Context cont, String path) {
		Bitmap bitmap = null;

		try {
			if (path != null) {
				bitmap = MediaStore.Images.Media.getBitmap(
						cont.getContentResolver(), Uri.parse(path));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bitmap;

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