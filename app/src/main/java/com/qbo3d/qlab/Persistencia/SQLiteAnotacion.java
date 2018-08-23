package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qbo3d.qlab.Logica.Datos_Anotacion;

public class SQLiteAnotacion extends SQLiteUd_Lab{

	public SQLiteAnotacion(Context contexto) {
		super(contexto);
	}
	
	public void addAnotacion(Datos_Anotacion ano) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_ANOTACION + " (" + 
						KEY_ANO_NOTA + ", " +
						KEY_ANO_ENS_ID_FK + ")" + 
					"VALUES ('" + 
						ano.getAnoNota() + "', " + 
						ano.getAnoEnsIdFk() + ");");
	}

	public int getAnoId(String ensayo, String nombre) {
		int id;
		String selectQuery = "SELECT " + KEY_ANO_ID + " FROM " + KEY_ANOTACION + 
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

	public List<String> getAnotacionId(String ensayo, String nombre) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT " + KEY_ANO_ID + " FROM " + KEY_ANOTACION + 
				" WHERE " + KEY_ANO_ENS_ID_FK + " = (SELECT " + KEY_ENS_ID + " FROM " + KEY_ENSAYO + 
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

	public List<String> getAnotacionNota(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT " + KEY_ANO_NOTA + " FROM " + KEY_ANOTACION + " WHERE " + KEY_ANO_ENS_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_Anotacion getAnotacion(int id) {

		String selectQuery = "SELECT * FROM " + KEY_ANOTACION + " WHERE " + KEY_ANO_ID + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_Anotacion ano = new Datos_Anotacion(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1),
				Integer.parseInt(cursor.getString(2)));
		return ano;
	}

	public int updateAnotacion(Datos_Anotacion ano, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ANO_NOTA, ano.getAnoNota());
		values.put(KEY_ANO_ENS_ID_FK, ano.getAnoEnsIdFk());

		return db.update(KEY_ANOTACION	, values, KEY_ANO_ID + " = " + id, null);
	}

	public void deleteAnotecion(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_ANOTACION, KEY_ANO_ID + " = " + id, null);
		db.close();
	}

}
