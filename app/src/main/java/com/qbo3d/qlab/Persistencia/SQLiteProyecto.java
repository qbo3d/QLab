package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_Proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteProyecto extends SQLiteUd_Lab{

	public SQLiteProyecto(Context contexto) {
		super(contexto);
	}
	
	public void addProyecto(Datos_Proyecto pro) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_PROYECTO + " (" + 
						KEY_PRO_NOMBRE + ", " +
						KEY_PRO_LOCALIZACION + ", " +
						KEY_PRO_FECHA + ", " +
						KEY_PRO_USU_CEDULA_FK + ")" + 
					"VALUES ('" + 
						pro.getProNombre() + "', '" + 
						pro.getProLocalizacion() + "', '" + 
						pro.getProFecha() + "', " + 
						pro.getProUsuCedulaFk() + ");");
	}

	public List<String> getProyectoNombre() {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT " + KEY_PRO_NOMBRE + " FROM " + KEY_PROYECTO + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getProyectoNombre(String cedula) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + KEY_PROYECTO + " WHERE " + KEY_PRO_USU_CEDULA_FK + " = " + cedula + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_Proyecto getProyecto(String nombre) {

		String selectQuery = "SELECT * FROM " + KEY_PROYECTO + " WHERE " + KEY_PRO_NOMBRE + " = '" + nombre + "';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_Proyecto pro = new Datos_Proyecto( 
				cursor.getString(0), 
				cursor.getString(1), 
				cursor.getString(2),
				Integer.parseInt(cursor.getString(3)));
		return pro;
	}

	public int updateProyecto(Datos_Proyecto pro, String nombre) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PRO_NOMBRE, pro.getProNombre());
		values.put(KEY_PRO_LOCALIZACION, pro.getProLocalizacion());
		values.put(KEY_PRO_FECHA, pro.getProFecha());
		values.put(KEY_PRO_USU_CEDULA_FK, pro.getProUsuCedulaFk());

		return db.update(KEY_PROYECTO, values, KEY_PRO_NOMBRE + " = '" + nombre + "'", null);
	}

	public void deleteProyect(String nombre) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_PROYECTO, KEY_PRO_NOMBRE + " = '" + nombre + "'", null);
		db.close();
	}

}
