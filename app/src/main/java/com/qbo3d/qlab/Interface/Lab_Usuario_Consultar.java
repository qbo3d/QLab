package com.qbo3d.qlab.Interface;

import com.qbo3d.qlab.Logica.Datos_Usuario;
import com.qbo3d.qlab.Persistencia.SQLiteUsuario;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Usuario_Consultar extends Activity {

	String usu_cedula_str;
	TextView usu_nombre;
	TextView usu_apellido;
	TextView usu_codigo;
	TextView usu_fechanacimiento;
	String usu_fechanacimiento_str;
	ImageView usu_foto;
	String usu_foto_str;
	TextView usu_email;
	TextView usu_cel;
	TextView usu_id;
	TextView usu_tipo_pos;
	Button bt_usu_pro;
	String usu_tipo_str;
	SQLiteUsuario usuSQLite;
	Datos_Usuario usu;
	ArrayAdapter<?> adapterTipo;
	Bundle extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lab_usuario_consultar);

		extra = getIntent().getExtras();
		usu_cedula_str = extra.getString("usuario");
		usu_nombre = (TextView) findViewById(R.id.et_usu_nombre_con);
		usu_apellido = (TextView) findViewById(R.id.et_usu_apellido_con);
		usu_codigo = (TextView) findViewById(R.id.et_usu_codigo_con);
		usu_fechanacimiento = (TextView) findViewById(R.id.dp_usu_fechanacimiento_con);
		usu_foto = (ImageView) findViewById(R.id.bt_usu_foto_con);
		usu_email = (TextView) findViewById(R.id.et_usu_email_con);
		usu_cel = (TextView) findViewById(R.id.et_usu_cel_con);
		usu_id = (TextView) findViewById(R.id.tv_usu_id_con);
		usu_tipo_pos = (TextView) findViewById(R.id.tv_usu_tipo_pos_con);

		usuSQLite = new SQLiteUsuario(this);

		callLlenarET(usu_cedula_str);

		bt_usu_pro = (Button) findViewById(R.id.bt_puente_proyecto);
		bt_usu_pro.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callPuenteProyecto(usu_cedula_str);
			}
		});
		
		usuSQLite.close();
	}
	
	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	private void callLlenarET(String usu_cedula_str) {

		usu = usuSQLite.getUsuario(Integer.parseInt(usu_cedula_str));
		usu_id.setText(usu_cedula_str);
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

		usu_fechanacimiento.setText(Integer.parseInt(usu_fechanacimiento_str
				.substring(0, i1))
				+ " / "
				+ (Integer.parseInt(usu_fechanacimiento_str.substring(i1 + 1,
						i2)) + 1)
				+ " / "
				+ Integer.parseInt(usu_fechanacimiento_str.substring(i2 + 1)));

		if (usu.getUsuFoto() != null) {
			usu_foto_str = usu.getUsuFoto();
			usu_foto.setImageBitmap(Herr_Capturar.strImg(this, usu_foto_str));
		}

		usu_email.setText(usu.getUsuEmail());
		usu_cel.setText(usu.getUsuCel());
		usu_tipo_pos.setText(usu.getUsuTipo());
	}
	
	private void callPuenteProyecto(String usuario) {
		Intent intent = new Intent();
		intent.setClass(this, Puente_Pro.class);
		intent.putExtra("usuario", usuario);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_eri, menu);
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
		case R.id.men_eliminar:
			if (usu_cedula_str != null) {
				AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
				dialogo.setTitle("Eliminar");
				dialogo.setMessage("¿Desea eliminar el usuario con numero de documento: "
						+ usu_cedula_str + "?");
				dialogo.setCancelable(false);
				dialogo.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								usuSQLite.deleteUsuario(Integer
										.parseInt(usu_cedula_str));
								Toast.makeText(getApplicationContext(),
										"Usuario eliminado", Toast.LENGTH_SHORT)
										.show();
								finish();
							}
						});
				dialogo.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								Toast.makeText(getApplicationContext(),
										"Se ha cancelado la operación",
										Toast.LENGTH_SHORT).show();
							}
						});
				dialogo.show();
			} else {
				Toast.makeText(getApplicationContext(),
						"No hay usuarios almacenados", Toast.LENGTH_LONG)
						.show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}