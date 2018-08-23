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
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Usuario_Modificar extends Activity {

	String usu_cedula_str;
	EditText usu_nombre;
	EditText usu_apellido;
	EditText usu_codigo;
	DatePicker usu_fechanacimiento;
	String usu_fechanacimiento_str;
	ImageButton usu_foto;
	String usu_foto_str;
	EditText usu_email;
	EditText usu_cel;
	TextView usu_id;
	TextView usu_tipo_pos;
	Spinner usu_tipo;
	String usu_tipo_str;
	ArrayAdapter<?> adapter;
	SQLiteUsuario usuSQLite;
	private static final int REQUEST_CODE = 10;
	Datos_Usuario usu;
	Button bt_usu_modificar;
	Bundle extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_usuario_modificar);

		extra = getIntent().getExtras();
		usu_cedula_str = extra.getString("usuario");
		usu_nombre = (EditText) findViewById(R.id.et_usu_nombre_mod);
		usu_apellido = (EditText) findViewById(R.id.et_usu_apellido_mod);
		usu_codigo = (EditText) findViewById(R.id.et_usu_codigo_mod);
		usu_fechanacimiento = (DatePicker) findViewById(R.id.dp_usu_fechanacimiento_mod);
		usu_foto = (ImageButton) findViewById(R.id.bt_usu_foto_mod);
		usu_foto.setOnClickListener(listener);
		usu_email = (EditText) findViewById(R.id.et_usu_email_mod);
		usu_cel = (EditText) findViewById(R.id.et_usu_cel_mod);
		usu_id = (TextView) findViewById(R.id.tv_usu_id_mod);
		usu_tipo_pos = (TextView) findViewById(R.id.tv_usu_tipo_pos_mod);
		usu_tipo = (Spinner) findViewById(R.id.sp_usu_tipo_mod);
		usuSQLite = new SQLiteUsuario(this);

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
		
		callLlenarET(usu_cedula_str);
		
		
		usu_tipo.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				switch (position) {
					case 0:
						usu_tipo_str = "Estudiante";
						break;
					case 1:
						usu_tipo_str = "Profesor";
						break;
					case 2:
						usu_tipo_str = "Administrativo";
						break;
					case 3:
						usu_tipo_str = "Otro";
						break;
				}
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

		bt_usu_modificar = (Button) findViewById(R.id.bt_usu_modificar);
		bt_usu_modificar.setOnClickListener(new View.OnClickListener() {
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
					usuSQLite.updateUsuario(
							new Datos_Usuario(
									Integer.parseInt(usu_cedula_str), 
									usu_nombre.getText().toString(),
									usu_apellido.getText().toString(), 
									usu_codigo.getText().toString(),
									usu_fechanacimiento_str, 
									usu_foto_str,
									usu_email.getText().toString(), 
									usu_cel.getText().toString(), 
									usu_tipo_str),
							Integer.parseInt(usu_cedula_str));
					finish();
				} else {
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
		intent.putExtra("Id", usu_cedula_str);
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

	private void callLlenarET(String usu_cedula_str) {
		
		usu_id.setText(usu_cedula_str);
		usu = usuSQLite.getUsuario(Integer.parseInt(usu_cedula_str));
		usu_cedula_str = String.valueOf(usu.getUsuCedula());
		usu_nombre.setText(usu.getUsuNombre());
		usu_apellido.setText(usu.getUsuApellido());
		usu_codigo.setText(usu.getUsuCodigo());

		usu_fechanacimiento_str = usu.getUsuFechaNacimiento();
		int i1 = 0, i2 = 0;
		boolean b1 = true;

		for (int i = 0; i < usu_fechanacimiento_str.length(); i++) {
			if (usu_fechanacimiento_str.charAt(i) == '-') {
				if (b1 == true) {
					i1 = i;
					b1 = false;
				} else {
					i2 = i;
				}
			}
		}
		;

		usu_fechanacimiento.updateDate(
				Integer.parseInt(usu_fechanacimiento_str.substring(0, i1)),
				Integer.parseInt(usu_fechanacimiento_str.substring(i1 + 1, i2)),
				Integer.parseInt(usu_fechanacimiento_str.substring(i2 + 1)));

		if (usu.getUsuFoto() != null) {
			usu_foto_str = usu.getUsuFoto();
			usu_foto.setImageBitmap(Herr_Capturar.strImg(this, usu_foto_str));
		}

		usu_email.setText(usu.getUsuEmail());
		usu_cel.setText(usu.getUsuCel());
		usu_tipo_pos.setText(usu.getUsuTipo());
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