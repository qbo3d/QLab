package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_Perforacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLitePerforacion extends SQLiteUd_Lab{

	public SQLitePerforacion(Context contexto) {
		super(contexto);
	}
	
	public void addPerforacion(Datos_Perforacion per) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_PERFORACION + " (" + 
						KEY_PER_NUMERO + ", " +
						KEY_PER_PROFUNDIDAD + ", " +
						KEY_PER_OBSERVACION + ", " +
						KEY_PER_ENS_ID_FK + ")" + 
					"VALUES ('" + 
						per.getPerNumero() + "', " + 
						per.getPerProfundidad() + ", '" + 
						per.getPerObservacion() + "', " + 
						per.getPerEnsIdFk() + ");");
	}

	public int getPerforacionId(String ensayo, String nombre, String perforacion) {
		int id;
		String selectQuery = "SELECT " + KEY_PER_ID + " FROM " + KEY_PERFORACION + 
				" WHERE " + KEY_PER_NUMERO + " = '" + perforacion + 
				"' AND " + KEY_PER_ENS_ID_FK + " = (SELECT " + KEY_ENS_ID + " FROM " + KEY_ENSAYO + 
				" WHERE " + KEY_ENS_NUMERO + " = '" + ensayo + 
				"' AND " + KEY_ENS_PRO_NOMBRE_FK + " = '" + nombre + "');";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
			
		return id;
	}

	public List<String> getPerforacionNumero(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + KEY_PERFORACION + " WHERE " + KEY_PER_ENS_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_Perforacion getPerforacion(int id) {

		String selectQuery = "SELECT * FROM " + KEY_PERFORACION + " WHERE " + KEY_PER_ID + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_Perforacion per = new Datos_Perforacion(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1),
				Double.parseDouble(cursor.getString(2)),
				cursor.getString(3),
				Integer.parseInt(cursor.getString(4)));
		return per;
	}

	public int updatePerforacion(Datos_Perforacion per, String numero) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PER_PROFUNDIDAD, per.getPerProfundidad());
		values.put(KEY_PER_OBSERVACION, per.getPerObservacion());
		values.put(KEY_PER_ENS_ID_FK, per.getPerEnsIdFk());

		return db.update(KEY_PERFORACION, values, KEY_PER_NUMERO + " = '" + numero + "'", null);
	}

	public void deletePerforacion(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_PERFORACION, KEY_PER_ID + " = " + id, null);
		db.close();
	}

}
