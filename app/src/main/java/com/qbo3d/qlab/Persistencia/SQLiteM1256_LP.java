package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M1256_LP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteM1256_LP extends SQLiteUd_Lab{

	public SQLiteM1256_LP(Context contexto) {
		super(contexto);
	}
	
	public void addM1256_LP(Datos_M1256_LP M1256_LP) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_M1256_LP + " (" + 
						KEY_M1256_LP_RN + ", " +
						KEY_M1256_LP_PSHR + ", " +
						KEY_M1256_LP_PSSR + ", " +
						KEY_M1256_LP_PR + ", " +
						KEY_M1256_LP_PER_ID_FK + ")" + 
					"VALUES ('" + 
						M1256_LP.getM1256_LP_RN() + "', '" +
						M1256_LP.getM1256_LP_PSHR() + "', " + 
						M1256_LP.getM1256_LP_PSSR() + ", " + 
						M1256_LP.getM1256_LP_PR() + ", " + 
						M1256_LP.getM1256_LPPerIdFk() + ");");
	}

	public int getM1256_LPId(String M1256_LP_RN, int per_id) {
		int id;

		String selectQuery = 
				"SELECT " + KEY_M1256_LP_ID + " FROM " + KEY_M1256_LP + 
				" WHERE " + KEY_M1256_LP_RN + " = '" + M1256_LP_RN + 
				"' AND " + KEY_M1256_LP_PER_ID_FK + " = " + per_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
		
		return id;
	}

	public List<String> getM1256_LPNumero(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + KEY_M1256_LP + " WHERE " + KEY_M1256_LP_PER_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_M1256_LP getM1256_LP(int m1256_LP_id) {

		String selectQuery = 
				"SELECT * FROM " + KEY_M1256_LP + 
				" WHERE " + KEY_M1256_LP_ID + " = " + m1256_LP_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_M1256_LP m1256_LP = new Datos_M1256_LP(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1), 
				Double.parseDouble(cursor.getString(2)), 
				Double.parseDouble(cursor.getString(3)), 
				Double.parseDouble(cursor.getString(4)), 
				Integer.parseInt(cursor.getString(5)));
		return m1256_LP;
	}

	public int updateM1256_LP(Datos_M1256_LP M1256_LP, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_M1256_LP_RN, M1256_LP.getM1256_LP_RN());
		values.put(KEY_M1256_LP_PSHR, M1256_LP.getM1256_LP_PSHR());
		values.put(KEY_M1256_LP_PSSR, M1256_LP.getM1256_LP_PSSR());
		values.put(KEY_M1256_LP_PR, M1256_LP.getM1256_LP_PR());
		values.put(KEY_M1256_LP_PER_ID_FK, M1256_LP.getM1256_LPPerIdFk());

		return db.update(KEY_M1256_LP, values, KEY_M1256_LP_ID + " = " + id, null);
	}

	public void deleteM1256_LP(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_M1256_LP, KEY_M1256_LP_ID + " = " + id, null);
		db.close();
	}

	public List<String> getM1256LP_RN(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LP_RN FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LP_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getM1256LP_PSHR(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LP_PSHR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LP_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getM1256LP_PSSR(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LP_PSSR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LP_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getM1256LP_PR(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LP_PR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LP_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public String getM1256HN_RN(String ens_Numero) {
		String RN;

		String selectQuery = "SELECT m1256_HN_RN FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_HN_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		RN = cursor.getString(0);
		
		return RN;
	}

	public Double getM1256HN_PSHR(String ens_Numero) {
		Double PSHR;

		String selectQuery = "SELECT m1256_HN_PSHR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_HN_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		PSHR = Double.parseDouble(cursor.getString(0));
		
		return PSHR;
	}

	public Double getM1256HN_PSSR(String ens_Numero) {
		Double PSSR;

		String selectQuery = "SELECT m1256_HN_PSSR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_HN_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		PSSR = Double.parseDouble(cursor.getString(0));
		
		return PSSR;
	}

	public Double getM1256HN_PR(String ens_Numero) {
		Double PR;

		String selectQuery = "SELECT m1256_HN_PR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_HN_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		PR = Double.parseDouble(cursor.getString(0));
		
		return PR;
	}

	public Double getM1256HN_PP200(String ens_Numero) {
		Double PP200;

		String selectQuery = "SELECT m1256_HN_PP200 FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_HN_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		PP200 = Double.parseDouble(cursor.getString(0));
		
		return PP200;
	}

	public String getM1256HN_AASHTO(String ens_Numero) {
		String AASHTO;

		String selectQuery = "SELECT m1256_HN_AASHTO FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_HN_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		AASHTO = cursor.getString(0);
		
		return AASHTO;
	}

	public String getM1256HN_USC(String ens_Numero) {
		String USC;

		String selectQuery = "SELECT m1256_HN_USC FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_HN_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		USC = cursor.getString(0);
		
		return USC;
	}

}
