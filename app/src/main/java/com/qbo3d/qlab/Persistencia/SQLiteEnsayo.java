package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_Ensayo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteEnsayo extends SQLiteUd_Lab{

	public SQLiteEnsayo(Context contexto) {
		super(contexto);
	}
	
	public void addEnsayo(Datos_Ensayo ens) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_ENSAYO + " (" + 
						KEY_ENS_NUMERO + ", " +
						KEY_ENS_DESCRIPCION_SUELO + ", " +
						KEY_ENS_FECHA + ", " +
						KEY_ENS_NORMA + ", " +
						KEY_ENS_PRO_NOMBRE_FK + ")" + 
					"VALUES ('" + 
						ens.getEnsNumero() + "', '" + 
						ens.getEnsDescripcionSuelo() + "', '" + 
						ens.getEnsFecha() + "', '" + 
						ens.getEnsNorma() + "', '" + 
						ens.getEnsProNombreFk() + "');");
	}

	public int getEnsayoId(String ensayo, String nombre) {
		int id;
		String selectQuery = "SELECT " + KEY_ENS_ID + " FROM " + KEY_ENSAYO + 
				" WHERE " + KEY_ENS_NUMERO + " = '" + ensayo + 
				"' AND " + KEY_ENS_PRO_NOMBRE_FK + " = '" + nombre + "';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
			
		return id;
	}

	public Datos_Ensayo getEnsayo(String ensayo, String nombre) {
		String selectQuery = "SELECT * FROM " + KEY_ENSAYO + 
				" WHERE " + KEY_ENS_NUMERO + " = '" + ensayo + 
				"' AND " + KEY_ENS_PRO_NOMBRE_FK + " = '" + nombre + "';";
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_Ensayo ens = new Datos_Ensayo(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1), 
				cursor.getString(2), 
				cursor.getString(3), 
				cursor.getString(4), 
				cursor.getString(5));
		return ens;
	}

	public List<String> getEnsayoNumero(String nombre) {
		List<String> list = new ArrayList<String>();
		String selectQuery = "SELECT * FROM " + KEY_ENSAYO + " WHERE " + KEY_ENS_PRO_NOMBRE_FK + " = '" + nombre + "';";
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}

		return list;
	}

	public int updateEnsayo(Datos_Ensayo ens, String numero) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ENS_NUMERO, ens.getEnsNumero());
		values.put(KEY_ENS_DESCRIPCION_SUELO, ens.getEnsDescripcionSuelo());
		values.put(KEY_ENS_FECHA, ens.getEnsFecha());
		values.put(KEY_ENS_NORMA, ens.getEnsNorma());
		values.put(KEY_ENS_PRO_NOMBRE_FK, ens.getEnsProNombreFk());

		return db.update(KEY_ENSAYO, values, KEY_ENS_NUMERO + " = '" + numero + "'", null);
	}

	public void deleteEnsayo(String ensayo, String nombre) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_ENSAYO, KEY_ENS_NUMERO + " = '" + ensayo + 
				"' AND " + KEY_ENS_PRO_NOMBRE_FK + " = '" + nombre + "'", null);
		db.close();
	}

}
