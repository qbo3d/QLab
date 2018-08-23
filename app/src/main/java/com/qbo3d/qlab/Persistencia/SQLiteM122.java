package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M122;
import com.qbo3d.qlab.Logica.Datos_Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteM122 extends SQLiteUd_Lab{

	public SQLiteM122(Context contexto) {
		super(contexto);
	}
	
	public void addM122(Datos_M122 M122) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_M122 + " (" + 
						KEY_M122_NUMERO + ", " +
						KEY_M122_TIPO + ", " +
						KEY_M122_W + ", " +
						KEY_M122_W1 + ", " +
						KEY_M122_W2 + ", " +
						KEY_M122_WC + ", " +
						KEY_M122_WW + ", " +
						KEY_M122_WS + ", " +
						KEY_M122_PER_ID_FK + ")" + 
					"VALUES ('" + 
						M122.getM122Numero() + "', '" +
						M122.getM122Tipo() + "', " + 
						M122.getM122w() + ", " + 
						M122.getM122W1() + ", " + 
						M122.getM122W2() + ", " + 
						M122.getM122Wc() + ", " + 
						M122.getM122Ww() + ", " + 
						M122.getM122Ws() + ", " + 
						M122.getM122PerIdFk() + ");");
	}

	public int getM122Id(String M122_numero, int per_id) {
		int id;

		String selectQuery = 
				"SELECT " + KEY_M122_ID + " FROM " + KEY_M122 + 
				" WHERE " + KEY_M122_NUMERO + " = '" + M122_numero + 
				"' AND " + KEY_M122_PER_ID_FK + " = " + per_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
		
		return id;
	}

	public List<String> getM122Numero(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + KEY_M122 + " WHERE " + KEY_M122_PER_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_M122 getM122(int m122_id) {

		String selectQuery = 
				"SELECT * FROM " + KEY_M122 + 
				" WHERE " + KEY_M122_ID + " = " + m122_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_M122 m122 = new Datos_M122(
				Integer.parseInt(cursor.getString(0)), 
				cursor.getString(1), 
				cursor.getString(2), 
				Double.parseDouble(cursor.getString(3)), 
				Double.parseDouble(cursor.getString(4)), 
				Double.parseDouble(cursor.getString(5)), 
				Double.parseDouble(cursor.getString(6)), 
				Double.parseDouble(cursor.getString(7)), 
				Double.parseDouble(cursor.getString(8)), 
				Integer.parseInt(cursor.getString(9)));
		return m122;
	}
	
	public Cursor getAllM122(String ens_Numero) {
		String selectQuery = 
				"SELECT * FROM 'VlistM122' " +
				"WHERE ens_Numero = '" + ens_Numero + "';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		return cursor;
	}

	public int updateM122(Datos_M122 M122, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_M122_NUMERO, M122.getM122Numero());
		values.put(KEY_M122_TIPO, M122.getM122Tipo());
		values.put(KEY_M122_W, M122.getM122w());
		values.put(KEY_M122_W1, M122.getM122W1());
		values.put(KEY_M122_W2, M122.getM122W2());
		values.put(KEY_M122_WC, M122.getM122Wc());
		values.put(KEY_M122_WW, M122.getM122Ww());
		values.put(KEY_M122_WS, M122.getM122Ws());
		values.put(KEY_M122_PER_ID_FK, M122.getM122PerIdFk());

		return db.update(KEY_M122, values, KEY_M122_ID + " = " + id, null);
	}

	public void deleteM122(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_M122, KEY_M122_ID + " = " + id, null);
		db.close();
	}
	
	public Cursor getAVGw(String ens_Numero) {
		String selectQuery = 
				"SELECT AVG(M122_W) FROM 'VlistM122' " +
				"WHERE ens_Numero = '" + ens_Numero + "' " +
				"GROUP BY per_Id ORDER BY m122_Tipo DESC;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		return cursor;
	}

	public Datos_Usuario getUsuarioV(String ens_Numero) {

		String selectQuery = 
				"SELECT * " +
				"FROM Usuario " +
				"WHERE usu_Cedula = " +
					"(SELECT pro_usu_Cedula_Fk  " +
					"FROM Proyecto " +
					"WHERE pro_Nombre = " +
						"(SELECT ens_pro_Nombre_Fk " +
						"FROM Ensayo " +
						"WHERE ens_Numero = '" + ens_Numero + "'));";

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

	public String getM122Fecha(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT ens_Fecha FROM VlistM122 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

	public String getM122DesSuelo(String ens_Numero) {
		String desSuelo;

		String selectQuery = 
				"SELECT ens_Descripcion_Suelo FROM VlistM122 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		desSuelo = cursor.getString(0);
		
		return desSuelo;
	}

	public String getM122proN(String ens_Numero) {
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

	public String getM122Localizacion(String ens_Numero) {
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

	public String getM122Usuario(String ens_Numero) {

		String selectQuery = 
				"SELECT usu_Nombre, usu_Apellido FROM Usuario WHERE usu_Cedula = (SELECT pro_usu_Cedula_Fk FROM Proyecto WHERE pro_Nombre = (SELECT ens_pro_Nombre_Fk FROM Ensayo WHERE ens_Numero = '" + ens_Numero + "'));";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		return cursor.getString(0) + " " + cursor.getString(1);
	}

}
