package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qbo3d.qlab.Logica.Datos_Captura;

public class SQLiteCaptura extends SQLiteUd_Lab{

	public SQLiteCaptura(Context contexto) {
		super(contexto);
	}
	
	public void addCaptura(Datos_Captura cap) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_CAPTURA + " (" + 
						KEY_CAP_IMAGEN + ", " +
						KEY_CAP_NOTA + ", " +
						KEY_CAP_ENS_ID_FK + ")" + 
					"VALUES ('" + 
						cap.getCapImagen() + "', '" + 
						cap.getCapNota() + "', " + 
						cap.getCapEnsIdFk() + ");");
	}

	public int getCapId(String ensayo, String nombre) {
		int id;
		String selectQuery = "SELECT " + KEY_CAP_ID + " FROM " + KEY_CAPTURA + 
				" WHERE " + KEY_ANO_ENS_ID_FK + " = (SELECT " + KEY_ENS_ID + " FROM " + KEY_ENSAYO + 
				" WHERE " + KEY_ENS_NUMERO + " = '" + ensayo + 
				"' AND " + KEY_ENS_PRO_NOMBRE_FK + " = '" + nombre + "');";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
			
		return id;
	}

	public List<String> getCapturaId(String ensayo, String nombre) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT " + KEY_CAP_ID + " FROM " + KEY_CAPTURA + 
				" WHERE " + KEY_CAP_ENS_ID_FK + " = (SELECT " + KEY_ENS_ID + " FROM " + KEY_ENSAYO + 
				" WHERE " + KEY_ENS_NUMERO + " = '" + ensayo + 
				"' AND " + KEY_ENS_PRO_NOMBRE_FK + " = '" + nombre + "');";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getCapturaImagen(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT " + KEY_CAP_IMAGEN + " FROM " + KEY_CAPTURA + " WHERE " + KEY_CAP_ENS_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getCapturaNota(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT " + KEY_CAP_NOTA + " FROM " + KEY_CAPTURA + " WHERE " + KEY_CAP_ENS_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_Captura getCaptura(int id) {

		String selectQuery = "SELECT * FROM " + KEY_CAPTURA + " WHERE " + KEY_CAP_ID + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_Captura cap = new Datos_Captura(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1),
				cursor.getString(2),
				Integer.parseInt(cursor.getString(3)));
		return cap;
	}

	public int updateCaptura(Datos_Captura cap, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CAP_IMAGEN, cap.getCapImagen());
		values.put(KEY_CAP_NOTA, cap.getCapNota());
		values.put(KEY_CAP_ENS_ID_FK, cap.getCapEnsIdFk());

		return db.update(KEY_CAPTURA	, values, KEY_CAP_ID + " = " + id, null);
	}

	public void deleteCaptura(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_CAPTURA, KEY_CAP_ID + " = " + id, null);
		db.close();
	}

}
