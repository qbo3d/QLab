package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteUsuario extends SQLiteUd_Lab{

	public SQLiteUsuario(Context contexto) {
		super(contexto);
	}

	public void addUsuario(Datos_Usuario usu) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.execSQL(
				"INSERT INTO " + KEY_USUARIO + " (" + 
						KEY_USU_CEDULA + ", " + 
						KEY_USU_NOMBRE + ", " + 
						KEY_USU_APELLIDO + ", " + 
						KEY_USU_CODIGO + ", " + 
						KEY_USU_FECHANACIMIENTO + ", " + 
						KEY_USU_FOTO + ", " + 
						KEY_USU_EMAIL + ", " + 
						KEY_USU_CEL + ", " + 
						KEY_USU_TIPO + ")" + 
					"VALUES (" + 
						usu.getUsuCedula() + ", '" + 
						usu.getUsuNombre() + "', '" + 
						usu.getUsuApellido() + "', '" + 
						usu.getUsuCodigo() + "', '" + 
						usu.getUsuFechaNacimiento() + "', '" + 
						usu.getUsuFoto() + "', '" + 
						usu.getUsuEmail() + "', '" + 
						usu.getUsuCel() + "', '" + 
						usu.getUsuTipo() + "');");
	}

	public List<String> getUsuariosId() {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT " + KEY_USU_CEDULA + " FROM " + KEY_USUARIO + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public String getUsuarioNC(int id) {
		String selectQuery, nombre, apellido;
		SQLiteDatabase db;
		Cursor cursor;
		selectQuery = "SELECT " + KEY_USU_NOMBRE + " FROM " + KEY_USUARIO + 
				" WHERE " + KEY_USU_CEDULA + " = " + id + ";";

		db = this.getWritableDatabase();
		cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		nombre = cursor.getString(0);

		selectQuery = "SELECT " + KEY_USU_APELLIDO + " FROM " + KEY_USUARIO + 
				" WHERE " + KEY_USU_CEDULA + " = " + id + ";";

		db = this.getWritableDatabase();
		cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		apellido = cursor.getString(0);
		return nombre + " " + apellido;
	}

	public Datos_Usuario getUsuario(int id) {

		String selectQuery = "SELECT * FROM " + KEY_USUARIO + " WHERE " + KEY_USU_CEDULA + " = " + id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_Usuario usu = new Datos_Usuario(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1), 
				cursor.getString(2),
				cursor.getString(3), 
				cursor.getString(4), 
				cursor.getString(5),
				cursor.getString(6), 
				cursor.getString(7), 
				cursor.getString(8));
		return usu;
	}

	public int updateUsuario(Datos_Usuario usu, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USU_NOMBRE, usu.getUsuNombre());
		values.put(KEY_USU_APELLIDO, usu.getUsuApellido());
		values.put(KEY_USU_APELLIDO, usu.getUsuApellido());
		values.put(KEY_USU_CODIGO, usu.getUsuCodigo());
		values.put(KEY_USU_FECHANACIMIENTO, usu.getUsuFechaNacimiento());
		values.put(KEY_USU_FOTO, usu.getUsuFoto());
		values.put(KEY_USU_EMAIL, usu.getUsuEmail());
		values.put(KEY_USU_CEL, usu.getUsuCel());
		values.put(KEY_USU_TIPO, usu.getUsuTipo());

		return db.update(KEY_USUARIO, values, KEY_USU_CEDULA + " = " + id, null);
	}

	public void deleteUsuario(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_USUARIO, KEY_USU_CEDULA + " = " + id, null);
		db.close();
	}

	public List<Datos_Usuario> getAllUsuarios() {
		List<Datos_Usuario> contactList = new ArrayList<Datos_Usuario>();
		String selectQuery = "SELECT * FROM " + KEY_USUARIO + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Datos_Usuario usu = new Datos_Usuario();
				usu.setUsuCedula(Integer.parseInt(cursor.getString(0)));
				usu.setUsuNombre(cursor.getString(1));
				usu.setUsuApellido(cursor.getString(2));
				usu.setUsuCodigo(cursor.getString(3));
				usu.setUsuFechaNacimiento(cursor.getString(4));
				usu.setUsuFoto(cursor.getString(5));
				usu.setUsuEmail(cursor.getString(6));
				usu.setUsuCel(cursor.getString(7));
				usu.setUsuTipo(cursor.getString(8));

				contactList.add(usu);
			} while (cursor.moveToNext());
		}
		return contactList;
	}

	public int getUsuariosCount() {
		String countQuery = "SELECT * FROM " + KEY_USUARIO + ";";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		return cursor.getCount();
	}
	
}
