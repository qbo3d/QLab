package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M127;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteM127 extends SQLiteUd_Lab{

	public SQLiteM127(Context contexto) {
		super(contexto);
	}
	
	public void addM127(Datos_M127 M127) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_M127 + " (" + 
						KEY_M127_NUMERO + ", " +
						KEY_M127_w + ", " +
						KEY_M127_W1 + ", " +
						KEY_M127_W2 + ", " +
						KEY_M127_W3 + ", " +
						KEY_M127_LC + ", " +
						KEY_M127_V + ", " +
						KEY_M127_Vo + ", " +
						KEY_M127_Wo + ", " +
						KEY_M127_miw + ", " +
						KEY_M127_R + ", " +
						KEY_M127_Gs + ", " +
						KEY_M127_CV + ", " +
						KEY_M127_Wi + ", " +
						KEY_M127_CL + ", " +
						KEY_M127_PER_ID_FK + ")" + 
					"VALUES ('" + 
						M127.getM127Numero() + "', " +
						M127.getM127w() + ", " + 
						M127.getM127W1() + ", " + 
						M127.getM127W2() + ", " + 
						M127.getM127W3() + ", " + 
						M127.getM127LC() + ", " + 
						M127.getM127V() + ", " + 
						M127.getM127Vo() + ", " + 
						M127.getM127Wo() + ", " + 
						M127.getM127miw() + ", " + 
						M127.getM127R() + ", " + 
						M127.getM127Gs() + ", " + 
						M127.getM127CV() + ", " + 
						M127.getM127Wi() + ", " + 
						M127.getM127CL() + ", " + 
						M127.getM127PerIdFk() + ");");
	}

	public int getM127Id(String M127_numero, int per_id) {
		int id;

		String selectQuery = 
				"SELECT " + KEY_M127_ID + " FROM " + KEY_M127 + 
				" WHERE " + KEY_M127_NUMERO + " = '" + M127_numero + 
				"' AND " + KEY_M127_PER_ID_FK + " = " + per_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
		
		return id;
	}

	public List<String> getM127Numero(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + KEY_M127 + " WHERE " + KEY_M127_PER_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_M127 getM127(String M127_numero, int per_id) {

		String selectQuery = 
				"SELECT * FROM " + KEY_M127 + 
				" WHERE " + KEY_M127_NUMERO + " = '" + M127_numero + 
				"' AND " + KEY_M127_PER_ID_FK + " = " + per_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_M127 m127 = new Datos_M127(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1), 
				Double.parseDouble(cursor.getString(2)), 
				Double.parseDouble(cursor.getString(3)), 
				Double.parseDouble(cursor.getString(4)), 
				Double.parseDouble(cursor.getString(5)), 
				Double.parseDouble(cursor.getString(6)), 
				Double.parseDouble(cursor.getString(7)), 
				Double.parseDouble(cursor.getString(8)), 
				Double.parseDouble(cursor.getString(9)), 
				Double.parseDouble(cursor.getString(10)), 
				Double.parseDouble(cursor.getString(11)), 
				Double.parseDouble(cursor.getString(12)), 
				Double.parseDouble(cursor.getString(13)), 
				Double.parseDouble(cursor.getString(14)),
				Double.parseDouble(cursor.getString(15)),  
				Integer.parseInt(cursor.getString(16)));
		return m127;
	}

	public Datos_M127 getM127(int m127_id) {

		String selectQuery = 
				"SELECT * FROM " + KEY_M127 + 
				" WHERE " + KEY_M127_ID + " = " + m127_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_M127 m127 = new Datos_M127(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1), 
				Double.parseDouble(cursor.getString(2)), 
				Double.parseDouble(cursor.getString(3)), 
				Double.parseDouble(cursor.getString(4)), 
				Double.parseDouble(cursor.getString(5)), 
				Double.parseDouble(cursor.getString(6)), 
				Double.parseDouble(cursor.getString(7)), 
				Double.parseDouble(cursor.getString(8)), 
				Double.parseDouble(cursor.getString(9)), 
				Double.parseDouble(cursor.getString(10)), 
				Double.parseDouble(cursor.getString(11)), 
				Double.parseDouble(cursor.getString(12)), 
				Double.parseDouble(cursor.getString(13)), 
				Double.parseDouble(cursor.getString(14)),
				Double.parseDouble(cursor.getString(15)),  
				Integer.parseInt(cursor.getString(16)));
		return m127;
	}
	
	public Cursor getAllM127(String ens_Numero) {
		String selectQuery = 
				"SELECT * FROM 'VlistM127' " +
				"WHERE ens_Numero = '" + ens_Numero + "';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		return cursor;
	}

	public int updateM127(Datos_M127 M127, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_M127_NUMERO, M127.getM127Numero());
		values.put(KEY_M127_w, M127.getM127w());
		values.put(KEY_M127_W1, M127.getM127W1());
		values.put(KEY_M127_W2, M127.getM127W2());
		values.put(KEY_M127_W3, M127.getM127W3());
		values.put(KEY_M127_LC, M127.getM127LC());
		values.put(KEY_M127_V, M127.getM127V());
		values.put(KEY_M127_Vo, M127.getM127Vo());
		values.put(KEY_M127_Wo, M127.getM127Wo());
		values.put(KEY_M127_miw, M127.getM127miw());
		values.put(KEY_M127_R, M127.getM127R());
		values.put(KEY_M127_Gs, M127.getM127Gs());
		values.put(KEY_M127_CV, M127.getM127CV());
		values.put(KEY_M127_Wi, M127.getM127Wi());
		values.put(KEY_M127_CL, M127.getM127CL());
		values.put(KEY_M127_PER_ID_FK, M127.getM127PerIdFk());

		return db.update(KEY_M127, values, KEY_M127_ID + " = " + id, null);
	}

	public void deleteM127(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_M127, KEY_M127_ID + " = " + id, null);
		db.close();
	}

	public String getM127Fecha(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT ens_Fecha FROM VlistM127 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

	public String getM127DesSuelo(String ens_Numero) {
		String desSuelo;

		String selectQuery = 
				"SELECT ens_Descripcion_Suelo FROM VlistM127 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		desSuelo = cursor.getString(0);
		
		return desSuelo;
	}

	public String getM127proN(String ens_Numero) {
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

	public String getM127Localizacion(String ens_Numero) {
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

	public String getM127Usuario(String ens_Numero) {

		String selectQuery = 
				"SELECT usu_Nombre, usu_Apellido FROM Usuario WHERE usu_Cedula = (SELECT pro_usu_Cedula_Fk FROM Proyecto WHERE pro_Nombre = (SELECT ens_pro_Nombre_Fk FROM Ensayo WHERE ens_Numero = '" + ens_Numero + "'));";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		return cursor.getString(0) + " " + cursor.getString(1);
	}

	public String getM127Perforacion(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT per_Numero FROM VlistM127 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

	public String getM127Profundidad(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT per_Profundidad FROM VlistM127 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

}
