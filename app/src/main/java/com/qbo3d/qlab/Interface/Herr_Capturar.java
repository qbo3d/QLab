package com.qbo3d.qlab.Interface;

import android.os.Bundle;
import android.app.Activity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.qbo3d.qlab.R;

public class Herr_Capturar extends Activity {

	Button backButton;
	ImageButton imageView;
	Uri d_path;
	String imagen;
	File destination;
	File UD_Lab;
	Bundle extras;
	final String path = "/UD-Lab/Avatar/";
	private static final int SELECT_PICTURE = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.herr_capturar);

		extras = getIntent().getExtras();

		if (extras == null) {
			return;
		}

		imagen = extras.getString("Id");

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			UD_Lab = new File(Environment.getExternalStorageDirectory(), path);
			if (!UD_Lab.exists()) {
				UD_Lab.mkdirs();
			}
		}
		imageView = (ImageButton) findViewById(R.id.image);
		imageView.setOnClickListener(capturar);

		backButton = (Button) findViewById(R.id.buttonBack);
		backButton.setOnClickListener(regresar);
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

	private View.OnClickListener regresar = new View.OnClickListener() {
		public void onClick(View v) {
			regresar();
		}
	};

	public void regresar() {
//		Toast.makeText(this, d_path.toString(), Toast.LENGTH_LONG).show();
		Intent data = new Intent();
		data.putExtra("path", d_path.toString());
		setResult(RESULT_OK, data);
		super.finish();
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
}