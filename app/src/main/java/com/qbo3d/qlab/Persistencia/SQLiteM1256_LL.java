package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M1256_LL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteM1256_LL extends SQLiteUd_Lab{

	public SQLiteM1256_LL(Context contexto) {
		super(contexto);
	}
	
	public void addM1256_LL(Datos_M1256_LL M1256_LL) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_M1256_LL + " (" + 
						KEY_M1256_LL_RN + ", " +
						KEY_M1256_LL_PSHR + ", " +
						KEY_M1256_LL_PSSR + ", " +
						KEY_M1256_LL_PR + ", " +
						KEY_M1256_LL_NG + ", " +
						KEY_M1256_LL_PER_ID_FK + ")" + 
					"VALUES ('" + 
						M1256_LL.getM1256_LL_RN() + "', '" +
						M1256_LL.getM1256_LL_PSHR() + "', " + 
						M1256_LL.getM1256_LL_PSSR() + ", " + 
						M1256_LL.getM1256_LL_PR() + ", " + 
						M1256_LL.getM1256_LL_NG() + ", " + 
						M1256_LL.getM1256_LLPerIdFk() + ");");
	}

	public int getM1256_LLId(String M1256_LL_RN, int per_id) {
		int id;

		String selectQuery = 
				"SELECT " + KEY_M1256_LL_ID + " FROM " + KEY_M1256_LL + 
				" WHERE " + KEY_M1256_LL_RN + " = '" + M1256_LL_RN + 
				"' AND " + KEY_M1256_LL_PER_ID_FK + " = " + per_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
		
		return id;
	}

	public List<String> getM1256_LLNumero(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + KEY_M1256_LL + " WHERE " + KEY_M1256_LL_PER_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_M1256_LL getM1256_LL(int m1256_LL_id) {

		String selectQuery = 
				"SELECT * FROM " + KEY_M1256_LL + 
				" WHERE " + KEY_M1256_LL_ID + " = " + m1256_LL_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_M1256_LL m1256_LL = new Datos_M1256_LL(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1), 
				Double.parseDouble(cursor.getString(2)), 
				Double.parseDouble(cursor.getString(3)), 
				Double.parseDouble(cursor.getString(4)), 
				Double.parseDouble(cursor.getString(5)), 
				Integer.parseInt(cursor.getString(6)));
		return m1256_LL;
	}
	
	public Cursor getAllM1256(String ens_Numero) {
		String selectQuery = 
				"SELECT * FROM 'VlistM1256' " +
				"WHERE ens_Numero = '" + ens_Numero + "';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		return cursor;
	}

	public int updateM1256_LL(Datos_M1256_LL M1256_LL, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_M1256_LL_RN, M1256_LL.getM1256_LL_RN());
		values.put(KEY_M1256_LL_PSHR, M1256_LL.getM1256_LL_PSHR());
		values.put(KEY_M1256_LL_PSSR, M1256_LL.getM1256_LL_PSSR());
		values.put(KEY_M1256_LL_PR, M1256_LL.getM1256_LL_PR());
		values.put(KEY_M1256_LL_NG, M1256_LL.getM1256_LL_NG());
		values.put(KEY_M1256_LL_PER_ID_FK, M1256_LL.getM1256_LLPerIdFk());

		return db.update(KEY_M1256_LL, values, KEY_M1256_LL_ID + " = " + id, null);
	}

	public void deleteM1256_LL(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_M1256_LL, KEY_M1256_LL_ID + " = " + id, null);
		db.close();
	}

	public String getM1256_Fecha(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT ens_Fecha FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

	public String getM1256_DesSuelo(String ens_Numero) {
		String desSuelo;

		String selectQuery = 
				"SELECT ens_Descripcion_Suelo FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		desSuelo = cursor.getString(0);
		
		return desSuelo;
	}

	public String getM1256_proN(String ens_Numero) {
		String pro_Nombre;

		String selectQuery = 
				"SELECT ens_pro_Nombre_Fk  FROM Ensayo WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		pro_Nombre = cursor.getString(0);
		
		return pro_Nombre;
	}

	public String getM1256_Localizacion(String ens_Numero) {
		String pro_Localizacion;

		String selectQuery = 
				"SELECT pro_Localizacion FROM Proyecto WHERE pro_Nombre = (SELECT ens_pro_Nombre_Fk  FROM Ensayo WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id);";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		pro_Localizacion = cursor.getString(0);
		
		return pro_Localizacion;
	}

	public String getM1256_Usuario(String ens_Numero) {

		String selectQuery = 
				"SELECT usu_Nombre, usu_Apellido FROM Usuario WHERE usu_Cedula = (SELECT pro_usu_Cedula_Fk FROM Proyecto WHERE pro_Nombre = (SELECT ens_pro_Nombre_Fk FROM Ensayo WHERE ens_Numero = '" + ens_Numero + "'));";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		return cursor.getString(0) + " " + cursor.getString(1);
	}

	public String getM1256_Perforacion(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT per_Numero FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

	public String getM1256_Profundidad(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT per_Profundidad FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

	public List<String> getM1256LL_RN(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LL_RN FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LL_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getM1256LL_PSHR(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LL_PSHR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LL_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getM1256LL_PSSR(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LL_PSSR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LL_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getM1256LL_PR(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LL_PR FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LL_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getM1256LL_NG(String ens_Numero) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT m1256_LL_NG FROM VlistM1256 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY m1256_LL_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return list;
	}

}
