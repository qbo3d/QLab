package com.qbo3d.qlab.Persistencia;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M123;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteM123 extends SQLiteUd_Lab{

	public SQLiteM123(Context contexto) {
		super(contexto);
	}
	
	public void addM123(Datos_M123 M123) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"INSERT INTO " + KEY_M123 + " (" + 
						KEY_M123_NUMERO + ", " +
						KEY_M123_T2P + ", " +
						KEY_M123_T1E1S2P + ", " +
						KEY_M123_T1P + ", " +
						KEY_M123_T1S2P + ", " +
						KEY_M123_T3S8P + ", " +
						KEY_M123_TN4P + ", " +
						KEY_M123_TN10P + ", " +
						KEY_M123_TN40P + ", " +
						KEY_M123_TN200P + ", " +
						KEY_M123_FONDO + ", " +
						KEY_M123_D60 + ", " +
						KEY_M123_D30 + ", " +
						KEY_M123_D10 + ", " +
						KEY_M123_LL + ", " +
						KEY_M123_LP + ", " +
						KEY_M123_IP + ", " +
						KEY_M123_AASHTO + ", " +
						KEY_M123_USC + ", " +
						KEY_M123_PER_ID_FK + ")" + 
					"VALUES ('" + 
						M123.getM123Numero() + "', " +
						M123.getM123T2P() + ", " + 
						M123.getM123T1E1S2P() + ", " + 
						M123.getM123T1P() + ", " + 
						M123.getM123T1S2P() + ", " + 
						M123.getM123T3S8P() + ", " + 
						M123.getM123TN4P() + ", " + 
						M123.getM123TN10P() + ", " + 
						M123.getM123TN40P() + ", " + 
						M123.getM123TN200P() + ", " + 
						M123.getM123Fondo() + ", " +  
						M123.getM123D60() + ", " + 
						M123.getM123D30() + ", " + 
						M123.getM123D10() + ", '" + 
						M123.getM123LL() + "', '" + 
						M123.getM123LP() + "', '" + 
						M123.getM123IP() + "', '" + 
						M123.getM123AASHTO() + "', '" + 
						M123.getM123USC() + "', " + 
						M123.getM123PerIdFk() + ");");
	}

	public int getM123Id(String M123_numero, int per_id) {
		int id;

		String selectQuery = 
				"SELECT " + KEY_M123_ID + " FROM " + KEY_M123 + 
				" WHERE " + KEY_M123_NUMERO + " = '" + M123_numero + 
				"' AND " + KEY_M123_PER_ID_FK + " = " + per_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		id = Integer.parseInt(cursor.getString(0));
		
		return id;
	}

	public List<String> getM123Numero(int id) {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + KEY_M123 + " WHERE " + KEY_M123_PER_ID_FK + " = " + id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public Datos_M123 getM123(String M123_numero, int per_id) {

		String selectQuery = 
				"SELECT * FROM " + KEY_M122 + 
				" WHERE " + KEY_M122_NUMERO + " = '" + M123_numero + 
				"' AND " + KEY_M122_PER_ID_FK + " = " + per_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_M123 m123 = new Datos_M123(
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
				cursor.getString(15), 
				cursor.getString(16), 
				cursor.getString(17), 
				cursor.getString(18), 
				cursor.getString(19), 
				Integer.parseInt(cursor.getString(20)));
		return m123;
	}

	public Datos_M123 getM123(int m123_id) {

		String selectQuery = 
				"SELECT * FROM " + KEY_M123 + 
				" WHERE " + KEY_M123_ID + " = " + m123_id + ";";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Datos_M123 m123 = new Datos_M123(
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
				cursor.getString(15), 
				cursor.getString(16), 
				cursor.getString(17), 
				cursor.getString(18), 
				cursor.getString(19), 
				Integer.parseInt(cursor.getString(20)));
		return m123;
	}
	
	public Cursor getAllM123(String ens_Numero) {
		String selectQuery = 
				"SELECT * FROM 'VlistM123' " +
				"WHERE ens_Numero = '" + ens_Numero + "';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		return cursor;
	}

	public int updateM123(Datos_M123 M123, int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_M123_NUMERO, M123.getM123Numero());
		values.put(KEY_M123_T2P, M123.getM123T2P());
		values.put(KEY_M123_T1E1S2P, M123.getM123T1E1S2P());
		values.put(KEY_M123_T1P, M123.getM123T1P());
		values.put(KEY_M123_T1S2P, M123.getM123T1S2P());
		values.put(KEY_M123_T3S8P, M123.getM123T3S8P());
		values.put(KEY_M123_TN4P, M123.getM123TN4P());
		values.put(KEY_M123_TN10P, M123.getM123TN10P());
		values.put(KEY_M123_TN40P, M123.getM123TN40P());
		values.put(KEY_M123_TN200P, M123.getM123TN200P());
		values.put(KEY_M123_FONDO, M123.getM123Fondo());
		values.put(KEY_M123_D60, M123.getM123D60());
		values.put(KEY_M123_D30, M123.getM123D30());
		values.put(KEY_M123_D10, M123.getM123D10());
		values.put(KEY_M123_LL, M123.getM123LL());
		values.put(KEY_M123_LP, M123.getM123LP());
		values.put(KEY_M123_IP, M123.getM123IP());
		values.put(KEY_M123_AASHTO, M123.getM123AASHTO());
		values.put(KEY_M123_USC, M123.getM123USC());
		values.put(KEY_M123_PER_ID_FK, M123.getM123PerIdFk());

		return db.update(KEY_M123, values, KEY_M123_ID + " = " + id, null);
	}

	public void deleteM123(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KEY_M123, KEY_M123_ID + " = " + id, null);
		db.close();
	}

	public String getM123Fecha(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT ens_Fecha FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

	public String getM123DesSuelo(String ens_Numero) {
		String desSuelo;

		String selectQuery = 
				"SELECT ens_Descripcion_Suelo FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		desSuelo = cursor.getString(0);
		
		return desSuelo;
	}

	public String getM123proN(String ens_Numero) {
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

	public String getM123Localizacion(String ens_Numero) {
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

	public String getM123Usuario(String ens_Numero) {

		String selectQuery = 
				"SELECT usu_Nombre, usu_Apellido FROM Usuario WHERE usu_Cedula = (SELECT pro_usu_Cedula_Fk FROM Proyecto WHERE pro_Nombre = (SELECT ens_pro_Nombre_Fk FROM Ensayo WHERE ens_Numero = '" + ens_Numero + "'));";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		return cursor.getString(0) + " " + cursor.getString(1);
	}

	public String getM123Perforacion(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT per_Numero FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}

	public String getM123Profundidad(String ens_Numero) {
		String fecha;

		String selectQuery = 
				"SELECT per_Profundidad FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		fecha = cursor.getString(0);
		
		return fecha;
	}
	
	public Double getM123T2P(String ens_Numero) {
		Double T2P;

		String selectQuery = 
				"SELECT m123_T2P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		T2P = Double.parseDouble(cursor.getString(0));

		return T2P;
	}
	
	public Double getM123T1E1S2P(String ens_Numero) {
		Double T1E1S2P;

		String selectQuery = 
				"SELECT m123_T1E1S2P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		T1E1S2P = Double.parseDouble(cursor.getString(0));

		return T1E1S2P;
	}
	
	public Double getM123T1P(String ens_Numero) {
		Double T1P;

		String selectQuery = 
				"SELECT m123_T1P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		T1P = Double.parseDouble(cursor.getString(0));

		return T1P;
	}
	
	public Double getM123T1S2P(String ens_Numero) {
		Double T1S2P;

		String selectQuery = 
				"SELECT m123_T1S2P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		T1S2P = Double.parseDouble(cursor.getString(0));

		return T1S2P;
	}
	
	public Double getM123T3S8P(String ens_Numero) {
		Double T3S8P;

		String selectQuery = 
				"SELECT m123_T3S8P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		T3S8P = Double.parseDouble(cursor.getString(0));

		return T3S8P;
	}
	
	public Double getM123TN4P(String ens_Numero) {
		Double TN4P;

		String selectQuery = 
				"SELECT m123_TN4P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		TN4P = Double.parseDouble(cursor.getString(0));

		return TN4P;
	}
	
	public Double getM123TN10P(String ens_Numero) {
		Double TN10P;

		String selectQuery = 
				"SELECT m123_TN10P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		TN10P = Double.parseDouble(cursor.getString(0));

		return TN10P;
	}
	
	public Double getM123TN40P(String ens_Numero) {
		Double TN40P;

		String selectQuery = 
				"SELECT m123_TN40P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		TN40P = Double.parseDouble(cursor.getString(0));

		return TN40P;
	}
	
	public Double getM123TN200P(String ens_Numero) {
		Double TN200P;

		String selectQuery = 
				"SELECT m123_TN200P FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		TN200P = Double.parseDouble(cursor.getString(0));

		return TN200P;
	}
	
	public Double getM123Fondo(String ens_Numero) {
		Double Fondo;

		String selectQuery = 
				"SELECT m123_Fondo FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		Fondo = Double.parseDouble(cursor.getString(0));

		return Fondo;
	}
	
	public Double getM123Total(String ens_Numero) {
		Double Total;

		String selectQuery = 
				"SELECT m123_T2P + m123_T1E1S2P + m123_T1P + m123_T1S2P + m123_T3S8P + m123_TN4P + m123_TN10P + m123_TN40P + m123_TN200P + m123_Fondo FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		Total = Double.parseDouble(cursor.getString(0));

		return Total;
	}
	
	public Double getM123D60(String ens_Numero) {
		Double D60;

		String selectQuery = 
				"SELECT m123_D60 FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		D60 = Double.parseDouble(cursor.getString(0));
		
		return D60;
	}
	
	public Double getM123D30(String ens_Numero) {
		Double D30;

		String selectQuery = 
				"SELECT m123_D30 FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		D30 = Double.parseDouble(cursor.getString(0));

		return D30;
	}
	
	public Double getM123D10(String ens_Numero) {
		Double D10;

		String selectQuery = 
				"SELECT m123_D10 FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		D10 = Double.parseDouble(cursor.getString(0));

		return D10;
	}
	
	public String getM123LL(String ens_Numero) {
		String LL;

		String selectQuery = 
				"SELECT m123_LL FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		LL = cursor.getString(0);

		return LL;
	}
	
	public String getM123LP(String ens_Numero) {
		String LP;

		String selectQuery = 
				"SELECT m123_LP FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		LP = cursor.getString(0);

		return LP;
	}
	
	public String getM123IP(String ens_Numero) {
		String IP;

		String selectQuery = 
				"SELECT m123_IP FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		IP = cursor.getString(0);

		return IP;
	}
	
	public String getM123AASHTO(String ens_Numero) {
		String AASHTO;

		String selectQuery = 
				"SELECT m123_AASHTO FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		AASHTO = cursor.getString(0);

		return AASHTO;
	}
	
	public String getM123USC(String ens_Numero) {
		String USC;

		String selectQuery = 
				"SELECT m123_USC FROM VlistM123 WHERE ens_Numero = '" + ens_Numero + "' GROUP BY ens_Id;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();
		
		USC = cursor.getString(0);

		return USC;
	}

}
