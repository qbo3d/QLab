package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_Usuario;
import com.qbo3d.qlab.Persistencia.SQLiteUsuario;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Lab_Usuario_Crear extends Activity {

	EditText usu_cedula;
	EditText usu_nombre;
	EditText usu_apellido;
	EditText usu_codigo;
	DatePicker usu_fechanacimiento;
	String usu_fechanacimiento_str;
	ImageButton usu_foto;
	String usu_foto_str;
	EditText usu_email;
	EditText usu_cel;
	Spinner usu_tipo;
	String usu_tipo_str;
	ArrayAdapter<?> adapter;
	SQLiteUsuario usuSQLite;
	private static final int REQUEST_CODE = 10;
	Button bt_usu_crear;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_usuario_crear);

		usu_cedula = (EditText) findViewById(R.id.et_usu_cedula_cre);
		usu_nombre = (EditText) findViewById(R.id.et_usu_nombre_cre);
		usu_apellido = (EditText) findViewById(R.id.et_usu_apellido_cre);
		usu_codigo = (EditText) findViewById(R.id.et_usu_codigo_cre);
		usu_fechanacimiento = (DatePicker) findViewById(R.id.dp_usu_fechanacimiento_cre);
		usu_foto = (ImageButton) findViewById(R.id.bt_usu_foto_cre);
		usu_foto.setOnClickListener(listener);
		usu_email = (EditText) findViewById(R.id.et_usu_email_cre);
		usu_cel = (EditText) findViewById(R.id.et_usu_cel_cre);
		usu_tipo = (Spinner) findViewById(R.id.sp_usu_tipo_cre);

		adapter = ArrayAdapter.createFromResource(this,
				R.array.usu_tipo_item, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		usu_tipo.setAdapter(adapter);

		usu_tipo.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
						usu_tipo_str = adapter.getItem(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

		usuSQLite = new SQLiteUsuario(this);
		bt_usu_crear = (Button) findViewById(R.id.bt_usu_crear);
		bt_usu_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				int dia = usu_fechanacimiento.getDayOfMonth();
				int mes = usu_fechanacimiento.getMonth();
				int anho = usu_fechanacimiento.getYear();
				usu_fechanacimiento_str = anho + "-" + mes + "-" + dia;
				if (usu_nombre.getText().toString().equals("") == false
						&& usu_apellido.getText().toString().equals("") == false
						&& usu_codigo.getText().toString().equals("") == false
						&& usu_email.getText().toString().equals("") == false
						&& usu_cel.getText().toString().equals("") == false) {
					usuSQLite.addUsuario(new Datos_Usuario(
							Integer.parseInt(usu_cedula.getText().toString()),
							usu_nombre.getText().toString(), 
							usu_apellido.getText().toString(), 
							usu_codigo.getText().toString(),
							usu_fechanacimiento_str, 
							usu_foto_str, 
							usu_email.getText().toString(), 
							usu_cel.getText().toString(), 
							usu_tipo_str));
					finish();	
				} 
				else {
					Toast.makeText(getApplicationContext(),
							"Todos los campos deben ser diligenciados",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		usuSQLite.close();
	}

	private View.OnClickListener listener = new View.OnClickListener() {
		public void onClick(View v) {
			callCapturar();
		}
	};

	private void callCapturar() {
		Intent intent = new Intent();
		intent.setClass(this, Herr_Capturar.class);
		intent.putExtra("Id", usu_cedula.getText().toString());
		startActivityForResult(intent, REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			if (data.hasExtra("path")) {
				usu_foto_str = data.getExtras().getString("path");
				usu_foto.setImageBitmap(Herr_Capturar.strImg(this, usu_foto_str));
			}
		}
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