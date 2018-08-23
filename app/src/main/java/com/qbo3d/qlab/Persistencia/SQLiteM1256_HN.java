package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M1256_HN;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteM1256_HN extends SQLiteUd_Lab{

	public SQLiteM1256_HN(Context contexto) {
		super(contexto);
	}
	
	public void addM1256_HN(Datos_M1256_HN M1256_HN) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_M1256_HN + " (" + 
						KEY_M1256_HN_RN + ", " +
						KEY_M1256_HN_PSHR + ", " +
						KEY_M1256_HN_PSSR + ", " +
						KEY_M1256_HN_PR + ", " +
						KEY_M1256_HN_PP200 + ", " +
						KEY_M1256_HN_AASHTO + ", " +
						KEY_M1256_HN_USC + ", " +
						KEY_M1256_HN_PER_ID_FK + ")" + 
					"VALUES ('" + 
						M1256_HN.getM1256_HN_RN() + "', '" +
						M1256_HN.getM1256_HN_PSHR() + "', " + 
						M1256_HN.getM1256_HN_PSSR() + ", " + 
						M1256_HN.getM1256_HN_PR() + ", " + 
						M1256_HN.getM1256_HN_PP200() + ", '" + 
						M1256_HN.getM1256_HN_ASSHTO() + "', '" +
						M1256_HN.getM1256_HN_USC() + "', " +
						M1256_HN.getM1256_HNPerIdFk() + ");");
	}

	public int getM1256_HNId(String M1256_HN_RN, int per_id) {
		int id;

		String selectQuery = 
				"SELECT " + KEY_M1256_HN_ID + " FROM " + KEY_M1256_HN + 
				" WHERE " + KEY_M1256_HN_RN + " = '" + M1256_HN_RN + 
				"' AND " + KEY_M1256_HN_PER_ID_FK + " = " + per_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
		
		return id;
	}

	public List<String> getM1256_HNNumero(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + KEY_M1256_HN + " WHERE " + KEY_M1256_HN_PER_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_M1256_HN getM1256_HN(int m1256_HN_id) {

		String selectQuery = 
				"SELECT * FROM " + KEY_M1256_HN + 
				" WHERE " + KEY_M1256_HN_ID + " = " + m1256_HN_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_M1256_HN m1256_HN = new Datos_M1256_HN(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1), 
				Double.parseDouble(cursor.getString(2)), 
				Double.parseDouble(cursor.getString(3)), 
				Double.parseDouble(cursor.getString(4)), 
				Double.parseDouble(cursor.getString(5)), 
				cursor.getString(6), 
				cursor.getString(7), 
				Integer.parseInt(cursor.getString(8)));
		return m1256_HN;
	}

	public int updateM1256_HN(Datos_M1256_HN M1256_HN, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_M1256_HN_RN, M1256_HN.getM1256_HN_RN());
		values.put(KEY_M1256_HN_PSHR, M1256_HN.getM1256_HN_PSHR());
		values.put(KEY_M1256_HN_PSSR, M1256_HN.getM1256_HN_PSSR());
		values.put(KEY_M1256_HN_PR, M1256_HN.getM1256_HN_PR());
		values.put(KEY_M1256_HN_AASHTO, M1256_HN.getM1256_HN_ASSHTO());
		values.put(KEY_M1256_HN_USC, M1256_HN.getM1256_HN_USC());
		values.put(KEY_M1256_HN_PER_ID_FK, M1256_HN.getM1256_HNPerIdFk());

		return db.update(KEY_M1256_HN, values, KEY_M1256_HN_ID + " = " + id, null);
	}

	public void deleteM1256_HN(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_M1256_HN, KEY_M1256_HN_ID + " = " + id, null);
		db.close();
	}

}
