package com.qbo3d.qlab.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUd_Lab extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "UD-Lab";
	
//	Usuario

	protected static final String KEY_USUARIO = "Usuario";
	protected static final String KEY_USU_CEDULA = "usu_Cedula";
	protected static final String KEY_USU_NOMBRE = "usu_Nombre";
	protected static final String KEY_USU_APELLIDO = "usu_Apellido";
	protected static final String KEY_USU_CODIGO = "usu_Codigo";
	protected static final String KEY_USU_FECHANACIMIENTO = "usu_FechaNacimiento";
	protected static final String KEY_USU_FOTO = "usu_Foto";
	protected static final String KEY_USU_EMAIL = "usu_Email";
	protected static final String KEY_USU_CEL = "usu_Cel";
	protected static final String KEY_USU_TIPO = "usu_Tipo";

	String sqlCreateTableUsuario = 
			"CREATE TABLE " + KEY_USUARIO + " (" + 
				KEY_USU_CEDULA + " INTEGER PRIMARY KEY NOT NULL," + 
				KEY_USU_NOMBRE + " TEXT NOT NULL," + 
				KEY_USU_APELLIDO + " TEXT NOT NULL," + 
				KEY_USU_CODIGO + " TEXT NOT NULL," + 
				KEY_USU_FECHANACIMIENTO + " DATETIME NULL," + 
				KEY_USU_FOTO + " TEXT NULL," + 
				KEY_USU_EMAIL + " TEXT NULL," + 
				KEY_USU_CEL + " TEXT NULL," + 
				KEY_USU_TIPO + " TEXT NULL);";
	
//	Proyecto
	
	protected static final String KEY_PROYECTO = "Proyecto";
	protected static final String KEY_PRO_NOMBRE = "pro_Nombre";
	protected static final String KEY_PRO_LOCALIZACION = "pro_Localizacion";
	protected static final String KEY_PRO_FECHA = "pro_Fecha";
	protected static final String KEY_PRO_USU_CEDULA_FK = "pro_usu_Cedula_Fk";
	
	String sqlCreateTableProyecto = 
			"CREATE TABLE " + KEY_PROYECTO + " (" + 
				KEY_PRO_NOMBRE + " TEXT PRIMARY KEY NOT NULL," + 
				KEY_PRO_LOCALIZACION + " TEXT NOT NULL," +
				KEY_PRO_FECHA + " DATETIME NOT NULL," +
				KEY_PRO_USU_CEDULA_FK + " INTEGER NOT NULL, " +
				"FOREIGN KEY(" + KEY_PRO_USU_CEDULA_FK + ") REFERENCES " + KEY_USUARIO + "(" + KEY_USU_CEDULA + ") ON DELETE CASCADE);";
	
//	Ensayo

	protected static final String KEY_ENSAYO = "Ensayo";
	protected static final String KEY_ENS_ID = "ens_Id";
	protected static final String KEY_ENS_NUMERO = "ens_Numero";
	protected static final String KEY_ENS_DESCRIPCION_SUELO = "ens_Descripcion_Suelo";
	protected static final String KEY_ENS_FECHA = "ens_Fecha";
	protected static final String KEY_ENS_NORMA = "ens_Norma";
	protected static final String KEY_ENS_PRO_NOMBRE_FK = "ens_pro_Nombre_Fk";
	
	String sqlCreateTableEnsayo = 
			"CREATE TABLE " + KEY_ENSAYO + " (" + 
				KEY_ENS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_ENS_NUMERO + " TEXT NOT NULL," + 
				KEY_ENS_DESCRIPCION_SUELO + " TEXT NOT NULL," + 
				KEY_ENS_FECHA + " DATETIME NOT NULL," + 
				KEY_ENS_NORMA + " TEXT NOT NULL," + 
				KEY_ENS_PRO_NOMBRE_FK + " TEXT NOT NULL , " +
				"FOREIGN KEY(" + KEY_ENS_PRO_NOMBRE_FK + ") REFERENCES " + KEY_PROYECTO + "(" + KEY_PRO_NOMBRE + ") ON DELETE CASCADE);";
	
//	Perforacion
	
	protected static final String KEY_PERFORACION = "Perforacion";
	protected static final String KEY_PER_ID = "per_Id";
	protected static final String KEY_PER_NUMERO = "per_Numero";
	protected static final String KEY_PER_PROFUNDIDAD = "per_Profundidad";
	protected static final String KEY_PER_OBSERVACION = "per_Observacion";
	protected static final String KEY_PER_ENS_ID_FK = "per_ens_Id_Fk";
	
	String sqlCreateTablePerforacion = 
			"CREATE TABLE " + KEY_PERFORACION + " (" + 
				KEY_PER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_PER_NUMERO + " TEXT NOT NULL," + 
				KEY_PER_PROFUNDIDAD + " DOUBLE NOT NULL," + 
				KEY_PER_OBSERVACION + " TEXT NOT NULL," + 
				KEY_PER_ENS_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_PER_ENS_ID_FK + ") REFERENCES " + KEY_ENSAYO + "(" + KEY_ENS_ID + ") ON DELETE CASCADE);";
	
//	Anotacion
	
	protected static final String KEY_ANOTACION = "Anotacion";
	protected static final String KEY_ANO_ID = "ano_Id";
	protected static final String KEY_ANO_NOTA = "ano_Nota";
	protected static final String KEY_ANO_ENS_ID_FK = "ano_ens_Id_Fk";
	
	String sqlCreateTableAnotacion = 
			"CREATE TABLE " + KEY_ANOTACION + " (" + 
				KEY_ANO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_ANO_NOTA + " TEXT NOT NULL," + 
				KEY_ANO_ENS_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_ANO_ENS_ID_FK + ") REFERENCES " + KEY_ENSAYO + "(" + KEY_ENS_ID + ") ON DELETE CASCADE);";
	
//	Captura
	
	protected static final String KEY_CAPTURA = "Captura";
	protected static final String KEY_CAP_ID = "cap_Id";
	protected static final String KEY_CAP_IMAGEN = "cap_Imagen";
	protected static final String KEY_CAP_NOTA = "cap_Nota";
	protected static final String KEY_CAP_ENS_ID_FK = "cap_ens_Id_Fk";
	
	String sqlCreateTableCaptura = 
			"CREATE TABLE " + KEY_CAPTURA + " (" + 
				KEY_CAP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_CAP_IMAGEN + " TEXT NOT NULL," + 
				KEY_CAP_NOTA + " TEXT NOT NULL," + 
				KEY_CAP_ENS_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_CAP_ENS_ID_FK + ") REFERENCES " + KEY_ENSAYO + "(" + KEY_ENS_ID + ") ON DELETE CASCADE);";
	
//	Muestra Norma INV E 122 07
	
	protected static final String KEY_M122 = "M122";
	protected static final String KEY_M122_ID = "m122_Id";
	protected static final String KEY_M122_NUMERO = "m122_Numero";
	protected static final String KEY_M122_TIPO = "m122_Tipo";
	protected static final String KEY_M122_W = "m122_w";
	protected static final String KEY_M122_W1 = "m122_W1";
	protected static final String KEY_M122_W2 = "m122_W2";
	protected static final String KEY_M122_WC = "m122_Wc";
	protected static final String KEY_M122_WW = "m122_Ww";
	protected static final String KEY_M122_WS = "m122_Ws";
	protected static final String KEY_M122_PER_ID_FK = "m122_per_Id_Fk";
	
	String sqlCreateTableM122 = 
			"CREATE TABLE " + KEY_M122 + " (" + 
				KEY_M122_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_M122_NUMERO + " TEXT NOT NULL," + 
				KEY_M122_TIPO + " TEXT NOT NULL," + 
				KEY_M122_W + " DOUBLE NOT NULL," + 
				KEY_M122_W1 + " DOUBLE NOT NULL," + 
				KEY_M122_W2 + " DOUBLE NOT NULL," + 
				KEY_M122_WC + " DOUBLE NOT NULL," + 
				KEY_M122_WW + " DOUBLE NOT NULL," + 
				KEY_M122_WS + " DOUBLE NOT NULL," + 
				KEY_M122_PER_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_M122_PER_ID_FK + ") REFERENCES " + KEY_PERFORACION + "(" + KEY_PER_ID + ") ON DELETE CASCADE);";
	
//	Muestra Norma INV E 123 07
	
	protected static final String KEY_M123 = "M123";
	protected static final String KEY_M123_ID = "m123_Id";
	protected static final String KEY_M123_NUMERO = "m123_Numero";
	protected static final String KEY_M123_T2P = "m123_T2P";
	protected static final String KEY_M123_T1E1S2P = "m123_T1E1S2P";
	protected static final String KEY_M123_T1P = "m123_T1P";
	protected static final String KEY_M123_T1S2P = "m123_T1S2P";
	protected static final String KEY_M123_T3S8P = "m123_T3S8P";
	protected static final String KEY_M123_TN4P = "m123_TN4P";
	protected static final String KEY_M123_TN10P = "m123_TN10P";
	protected static final String KEY_M123_TN40P = "m123_TN40P";
	protected static final String KEY_M123_TN200P = "m123_TN200P";
	protected static final String KEY_M123_FONDO = "m123_Fondo";
	protected static final String KEY_M123_D60 = "m123_D60";
	protected static final String KEY_M123_D30 = "m123_D30";
	protected static final String KEY_M123_D10 = "m123_D10";
	protected static final String KEY_M123_LL = "m123_LL";
	protected static final String KEY_M123_LP = "m123_LP";
	protected static final String KEY_M123_IP = "m123_IP";
	protected static final String KEY_M123_AASHTO = "m123_AASHTO";
	protected static final String KEY_M123_USC = "m123_USC";
	protected static final String KEY_M123_PER_ID_FK = "m123_per_Id_Fk";
	
	String sqlCreateTableM123 = 
			"CREATE TABLE " + KEY_M123 + " (" + 
				KEY_M123_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_M123_NUMERO + " TEXT NOT NULL," + 
				KEY_M123_T2P + " DOUBLE NOT NULL," + 
				KEY_M123_T1E1S2P + " DOUBLE NOT NULL," + 
				KEY_M123_T1P + " DOUBLE NOT NULL," + 
				KEY_M123_T1S2P + " DOUBLE NOT NULL," + 
				KEY_M123_T3S8P + " DOUBLE NOT NULL," +
				KEY_M123_TN4P + " DOUBLE NOT NULL," + 
				KEY_M123_TN10P + " DOUBLE NOT NULL," + 
				KEY_M123_TN40P + " DOUBLE NOT NULL," + 
				KEY_M123_TN200P + " DOUBLE NOT NULL," + 
				KEY_M123_FONDO + " DOUBLE NOT NULL," + 
				KEY_M123_D60 + " DOUBLE NOT NULL," + 
				KEY_M123_D30 + " DOUBLE NOT NULL," + 
				KEY_M123_D10 + " DOUBLE NOT NULL," + 
				KEY_M123_LL + " TEXT NOT NULL," + 
				KEY_M123_LP + " TEXT NOT NULL," + 
				KEY_M123_IP + " TEXT NOT NULL," + 
				KEY_M123_AASHTO + " TEXT NOT NULL," + 
				KEY_M123_USC + " TEXT NOT NULL," + 
				KEY_M123_PER_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_M123_PER_ID_FK + ") REFERENCES " + KEY_PERFORACION + "(" + KEY_PER_ID + ") ON DELETE CASCADE);";
	
//	Muestra Norma INV E 125 07 Y Norma INV E 126 07 PARA LIMITE LIQUIDO
	
	protected static final String KEY_M1256_LL = "M1256_LL";
	protected static final String KEY_M1256_LL_ID = "m1256_LL_Id";
	protected static final String KEY_M1256_LL_RN = "m1256_LL_RN";
	protected static final String KEY_M1256_LL_PSHR = "m1256_LL_PSHR";
	protected static final String KEY_M1256_LL_PSSR = "m1256_LL_PSSR";
	protected static final String KEY_M1256_LL_PR = "m1256_LL_PR";
	protected static final String KEY_M1256_LL_NG = "m1256_LL_NG";
	protected static final String KEY_M1256_LL_PER_ID_FK = "m1256_LL_per_Id_Fk";
	
	String sqlCreateTableM1256_LL = 
			"CREATE TABLE " + KEY_M1256_LL + " (" + 
				KEY_M1256_LL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_M1256_LL_RN + " TEXT NOT NULL," + 
				KEY_M1256_LL_PSHR + " DOUBLE NOT NULL," + 
				KEY_M1256_LL_PSSR + " DOUBLE NOT NULL," + 
				KEY_M1256_LL_PR + " DOUBLE NOT NULL," + 
				KEY_M1256_LL_NG + " DOUBLE NOT NULL," + 
				KEY_M1256_LL_PER_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_M1256_LL_PER_ID_FK + ") REFERENCES " + KEY_PERFORACION + "(" + KEY_PER_ID + ") ON DELETE CASCADE);";
	
//	Muestra Norma INV E 125 07 Y Norma INV E 126 07 PARA HUMEDAD NATURAL
	
	protected static final String KEY_M1256_HN = "M1256_HN";
	protected static final String KEY_M1256_HN_ID = "m1256_HN_Id";
	protected static final String KEY_M1256_HN_RN = "m1256_HN_RN";
	protected static final String KEY_M1256_HN_PSHR = "m1256_HN_PSHR";
	protected static final String KEY_M1256_HN_PSSR = "m1256_HN_PSSR";
	protected static final String KEY_M1256_HN_PR = "m1256_HN_PR";
	protected static final String KEY_M1256_HN_PP200 = "m1256_HN_PP200";
	protected static final String KEY_M1256_HN_AASHTO = "m1256_HN_AASHTO";
	protected static final String KEY_M1256_HN_USC = "m1256_HN_USC";
	protected static final String KEY_M1256_HN_PER_ID_FK = "m1256_HN_per_Id_Fk";
	
	String sqlCreateTableM1256_HN = 
			"CREATE TABLE " + KEY_M1256_HN + " (" + 
				KEY_M1256_HN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_M1256_HN_RN + " TEXT NOT NULL," + 
				KEY_M1256_HN_PSHR + " DOUBLE NOT NULL," + 
				KEY_M1256_HN_PSSR + " DOUBLE NOT NULL," + 
				KEY_M1256_HN_PR + " DOUBLE NOT NULL," + 
				KEY_M1256_HN_PP200 + " DOUBLE NOT NULL," + 
				KEY_M1256_HN_AASHTO + " TEXT NOT NULL," + 
				KEY_M1256_HN_USC + " TEXT NOT NULL," + 
				KEY_M1256_HN_PER_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_M1256_HN_PER_ID_FK + ") REFERENCES " + KEY_PERFORACION + "(" + KEY_PER_ID + ") ON DELETE CASCADE);";
	
//	Muestra Norma INV E 125 07 Y Norma INV E 126 07 PARA LIMITE PLASTICO
	
	protected static final String KEY_M1256_LP = "M1256_LP";
	protected static final String KEY_M1256_LP_ID = "m1256_LP_Id";
	protected static final String KEY_M1256_LP_RN = "m1256_LP_RN";
	protected static final String KEY_M1256_LP_PSHR = "m1256_LP_PSHR";
	protected static final String KEY_M1256_LP_PSSR = "m1256_LP_PSSR";
	protected static final String KEY_M1256_LP_PR = "m1256_LP_PR";
	protected static final String KEY_M1256_LP_PER_ID_FK = "m1256_LP_per_Id_Fk";
	
	String sqlCreateTableM1256_LP = 
			"CREATE TABLE " + KEY_M1256_LP + " (" + 
				KEY_M1256_LP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_M1256_LP_RN + " TEXT NOT NULL," + 
				KEY_M1256_LP_PSHR + " DOUBLE NOT NULL," + 
				KEY_M1256_LP_PSSR + " DOUBLE NOT NULL," + 
				KEY_M1256_LP_PR + " DOUBLE NOT NULL," + 
				KEY_M1256_LP_PER_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_M1256_LP_PER_ID_FK + ") REFERENCES " + KEY_PERFORACION + "(" + KEY_PER_ID + ") ON DELETE CASCADE);";
	
//	Muestra Norma INV E 127 07
	
	protected static final String KEY_M127 = "M127";
	protected static final String KEY_M127_ID = "m127_Id";
	protected static final String KEY_M127_NUMERO = "m127_Numero";
	protected static final String KEY_M127_w = "m127_w";
	protected static final String KEY_M127_W1 = "m127_W1";
	protected static final String KEY_M127_W2 = "m127_W2";
	protected static final String KEY_M127_W3 = "m127_W3";
	protected static final String KEY_M127_LC = "m127_LC";
	protected static final String KEY_M127_V = "m127_V";
	protected static final String KEY_M127_Vo = "m127_Vo";
	protected static final String KEY_M127_Wo = "m127_Wo";
	protected static final String KEY_M127_miw = "m127_miw";
	protected static final String KEY_M127_R = "m127_R";
	protected static final String KEY_M127_Gs = "m127_Gs";
	protected static final String KEY_M127_CV = "m127_CV";
	protected static final String KEY_M127_Wi = "m127_Wi";
	protected static final String KEY_M127_CL = "m127_CL";
	protected static final String KEY_M127_PER_ID_FK = "m127_per_Id_Fk";
	
	String sqlCreateTableM127 = 
			"CREATE TABLE " + KEY_M127 + " (" + 
				KEY_M127_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				KEY_M127_NUMERO + " TEXT NOT NULL," + 
				KEY_M127_w + " DOUBLE NOT NULL," + 
				KEY_M127_W1 + " DOUBLE NOT NULL," + 
				KEY_M127_W2 + " DOUBLE NOT NULL," + 
				KEY_M127_W3 + " DOUBLE NOT NULL," + 
				KEY_M127_LC + " DOUBLE NOT NULL," +
				KEY_M127_V + " DOUBLE NOT NULL," + 
				KEY_M127_Vo + " DOUBLE NOT NULL," + 
				KEY_M127_Wo + " DOUBLE NOT NULL," + 
				KEY_M127_miw + " DOUBLE NOT NULL," + 
				KEY_M127_R + " DOUBLE NOT NULL," + 
				KEY_M127_Gs + " DOUBLE NOT NULL," + 
				KEY_M127_CV + " DOUBLE NOT NULL," + 
				KEY_M127_Wi + " DOUBLE NOT NULL," + 
				KEY_M127_CL + " DOUBLE NOT NULL," +  
				KEY_M127_PER_ID_FK + " INTEGER NOT NULL , " +
				"FOREIGN KEY(" + KEY_M127_PER_ID_FK + ") REFERENCES " + KEY_PERFORACION + "(" + KEY_PER_ID + ") ON DELETE CASCADE);";

//	Triggers
	
	String sqlCreateTriggerBUsC = 
			"CREATE TRIGGER 'borrarUsuC' " +
			"BEFORE DELETE " +
			"ON Usuario " +
			"FOR EACH ROW " +
			"BEGIN " +
			"DELETE FROM Proyecto WHERE pro_usu_Cedula_Fk = old.usu_Cedula; " +
			"END";
	
	String sqlCreateTriggerBPrC = 
			"CREATE TRIGGER 'borrarProC' " +
			"BEFORE DELETE " +
			"ON Proyecto " +
			"FOR EACH ROW " +
			"BEGIN " +
			"DELETE FROM Ensayo WHERE ens_pro_Nombre_Fk = old.pro_Nombre; " +
			"END";
	
	String sqlCreateTriggerBEnC =
			"CREATE TRIGGER 'borrarEnsC' " +
			"BEFORE DELETE " +
			"ON Ensayo " +
			"FOR EACH ROW " +
			"BEGIN " +
			"DELETE FROM Perforacion WHERE per_ens_Id_Fk = old.ens_Id; " +
			"DELETE FROM Anotacion WHERE ano_ens_Id_Fk = old.ens_Id; " +
			"DELETE FROM Captura WHERE cap_ens_Id_Fk = old.ens_Id; " +
			"END";
			
	String sqlCreateTriggerBPeC =
			"CREATE TRIGGER 'borrarPerC' " +
			"BEFORE DELETE " +
			"ON Perforacion " +
			"FOR EACH ROW " +
			"BEGIN " +
			"DELETE FROM M122 WHERE M122_per_Id_Fk = old.per_Id; " +
			"DELETE FROM M123 WHERE M123_per_Id_Fk = old.per_Id; " +
			"DELETE FROM M1256_HN WHERE M1256_HN_per_Id_Fk = old.per_Id; " +
			"DELETE FROM M1256_LL WHERE M1256_LL_per_Id_Fk = old.per_Id; " +
			"DELETE FROM M1256_LP WHERE M1256_LP_per_Id_Fk = old.per_Id; " +
			"DELETE FROM M127 WHERE M127_per_Id_Fk = old.per_Id; " +
			"END";

//	Vistas

	String sqlCreateViewVlistM122 =
			"CREATE VIEW 'VlistM122' " +
			"AS " +
			"SELECT " +
				"ens_Id, ens_Numero, ens_Descripcion_Suelo, ens_Fecha, ens_Norma, " +
				"per_Id, per_Numero, per_Profundidad, per_Observacion, " +
				"m122_Id, m122_Numero, m122_Tipo, m122_w, m122_W1, m122_W2, m122_Wc, m122_Ww, m122_Ws " +
			"FROM Ensayo " +
				"INNER " +
					"JOIN Perforacion " +
						"ON ens_Id = per_ens_Id_Fk " +
							"INNER " +
								"JOIN M122 " +
									"ON per_Id = m122_per_Id_Fk " +
										"ORDER BY M122_Tipo DESC";
	
	String sqlCreateViewVlistM123 =
			"CREATE VIEW 'VlistM123' " +
			"AS " +
			"SELECT " +
				"ens_Id, ens_Numero, ens_Descripcion_Suelo, ens_Fecha, ens_Norma, " +
				"per_Id, per_Numero, per_Profundidad, per_Observacion, " +
				"m123_Id, m123_Numero, m123_T2P, m123_T1E1S2P, m123_T1P, m123_T1S2P, m123_T3S8P, m123_TN4P, m123_TN10P, m123_TN40P, m123_TN200P, m123_Fondo, m123_D60, m123_D30, m123_D10, m123_LL, m123_LP, m123_IP, m123_AASHTO, m123_USC " +
			"FROM Ensayo " +
				"INNER " +
					"JOIN Perforacion " +
						"ON ens_Id = per_ens_Id_Fk " +
							"INNER " +
								"JOIN M123 " +
									"ON per_Id = m123_per_Id_Fk";
	
	String sqlCreateViewVlistM1256 =
			"CREATE VIEW 'VlistM1256' " +
			"AS " +
			"SELECT " +
				"ens_Id, ens_Numero, ens_Descripcion_Suelo, ens_Fecha, ens_Norma, " +
				"per_Id, per_Numero, per_Profundidad, per_Observacion, " +
				"m1256_LL_Id, m1256_LL_RN, m1256_LL_PSHR, m1256_LL_PSSR, m1256_LL_PR, m1256_LL_NG, " +
				"m1256_LP_Id, m1256_LP_RN, m1256_LP_PSHR, m1256_LP_PSSR, m1256_LP_PR, " +
				"m1256_HN_Id, m1256_HN_RN, m1256_HN_PSHR, m1256_HN_PSSR, m1256_HN_PR, m1256_HN_PP200, m1256_HN_AASHTO, m1256_HN_USC " +
			"FROM Ensayo " +
				"INNER " +
					"JOIN Perforacion " +
						"ON ens_Id = per_ens_Id_Fk " +
							"INNER " +
								"JOIN M1256_LL " +
									"ON per_Id = m1256_LL_per_Id_Fk " +
							"INNER " +
								"JOIN M1256_LP " +
									"ON per_Id = m1256_LP_per_Id_Fk " +
							"INNER " +
								"JOIN M1256_HN " +
									"ON per_Id = m1256_HN_per_Id_Fk";
	
	String sqlCreateViewVlistM127 =
			"CREATE VIEW 'VlistM127' " +
			"AS " +
			"SELECT " +
				"ens_Id, ens_Numero, ens_Descripcion_Suelo, ens_Fecha, ens_Norma, " +
				"per_Id, per_Numero, per_Profundidad, per_Observacion, " +
				"m127_Id, m127_Numero, m127_w, m127_W1, m127_W2, m127_W3, m127_LC, m127_V, m127_Vo, m127_Wo, m127_miw, m127_R, m127_Gs, m127_CV, m127_Wi, m127_CL " +
			"FROM Ensayo " +
				"INNER " +
					"JOIN Perforacion " +
						"ON ens_Id = per_ens_Id_Fk " +
							"INNER " +
								"JOIN M127 " +
									"ON per_Id = m127_per_Id_Fk";
	
//	Inserts
	
	String sqlInsertUsuario =
			"INSERT INTO 'Usuario' ('usu_Cedula','usu_Nombre','usu_Apellido','usu_Codigo','usu_FechaNacimiento','usu_Foto','usu_Email','usu_Cel','usu_Tipo') VALUES (1014261656, 'Tatiana', 'Perez', 10481816028, '1995-0-10', 'content://media/external/images/media/1', 'tperez56@uan.edu.co', 3182096997, 'Administrativo');";
	
	String sqlInsertProyecto =
			"INSERT INTO 'Proyecto' ('pro_Nombre','pro_Localizacion','pro_Fecha','pro_usu_Cedula_Fk') VALUES ( 'Proyecto 01', 'Bogotá', '2013-0-5', 1014261656);";
	
	String sqlInsertEnsayo122 =
			"INSERT INTO 'Ensayo' ( 'ens_Numero','ens_Descripcion_Suelo','ens_Fecha','ens_Norma','ens_pro_Nombre_Fk') VALUES ( 'ens122', 'Ninguna', '2013-0-5', 'Norma INV E 122 07', 'Proyecto 01');";
	
	String sqlInsertEnsayo123 =
			"INSERT INTO 'Ensayo' ( 'ens_Numero','ens_Descripcion_Suelo','ens_Fecha','ens_Norma','ens_pro_Nombre_Fk') VALUES ( 'ens123', 'Ninguna', '2013-0-5', 'Norma INV E 123 07', 'Proyecto 01');";
	
	String sqlInsertEnsayo1256 =
			"INSERT INTO 'Ensayo' ( 'ens_Numero','ens_Descripcion_Suelo','ens_Fecha','ens_Norma','ens_pro_Nombre_Fk') VALUES ( 'ens1256', 'Ninguna', '2013-0-5', 'Norma INV E 125 07 Y Norma INV E 126 07', 'Proyecto 01');";
	
	String sqlInsertEnsayo127 =
			"INSERT INTO 'Ensayo' ( 'ens_Numero','ens_Descripcion_Suelo','ens_Fecha','ens_Norma','ens_pro_Nombre_Fk') VALUES ( 'ens127', 'Ninguna', '2013-0-5', 'Norma INV E 127 07', 'Proyecto 01');";
	
	String sqlInsertPerforacion122_1 =
			"INSERT INTO 'Perforacion' ( 'per_Numero','per_Profundidad','per_Observacion','per_ens_Id_Fk') VALUES ( 'per122_1', 123, 'Ninguna', 1);";
	
	String sqlInsertPerforacion122_2 =
			"INSERT INTO 'Perforacion' ( 'per_Numero','per_Profundidad','per_Observacion','per_ens_Id_Fk') VALUES ( 'per122_2', 123, 'Ninguna', 1);";
	
	String sqlInsertPerforacion123_1 =
			"INSERT INTO 'Perforacion' ( 'per_Numero','per_Profundidad','per_Observacion','per_ens_Id_Fk') VALUES ( 'per123_1', 123, 'Ninguna', 2);";
	
	String sqlInsertPerforacion1256_1 =
			"INSERT INTO 'Perforacion' ( 'per_Numero','per_Profundidad','per_Observacion','per_ens_Id_Fk') VALUES ( 'per1256_1', 123, 'Ninguna', 3);";
	
	String sqlInsertPerforacion127_1 =
			"INSERT INTO 'Perforacion' ( 'per_Numero','per_Profundidad','per_Observacion','per_ens_Id_Fk') VALUES ( 'per127_1', 123, 'Ninguna', 4);";
	
	String sqlInsertM122_1 =
			"INSERT INTO 'M122' ( 'm122_Numero','m122_Tipo','m122_w','m122_W1','m122_W2','m122_Wc','m122_Ww','m122_Ws','m122_per_Id_Fk') VALUES ( '9', 'Grava', 0.1599744040953584, 82.54, 82.44, 19.93, 0.10000000000000853, 62.51, 1);";
	
	String sqlInsertM122_2 =
			"INSERT INTO 'M122' ( 'm122_Numero','m122_Tipo','m122_w','m122_W1','m122_W2','m122_Wc','m122_Ww','m122_Ws','m122_per_Id_Fk') VALUES ( '3', 'Grava', 0.2700675168792087, 86.83, 86.65, 20, 0.1799999999999926, 66.65, 1);";
	
	String sqlInsertM122_3 =
			"INSERT INTO 'M122' ( 'm122_Numero','m122_Tipo','m122_w','m122_W1','m122_W2','m122_Wc','m122_Ww','m122_Ws','m122_per_Id_Fk') VALUES ( '18', 'Arena', 0.3824343927205674, 94.87, 94.58, 18.75, 0.29000000000000625, 75.83, 2);";
	
	String sqlInsertM122_4 =
			"INSERT INTO 'M122' ( 'm122_Numero','m122_Tipo','m122_w','m122_W1','m122_W2','m122_Wc','m122_Ww','m122_Ws','m122_per_Id_Fk') VALUES ( '21', 'Arena', 0.4167286798630766, 86.72, 86.44, 19.25, 0.28000000000000114, 67.19, 2);";
	
	String sqlInsertM123_1 =
			"INSERT INTO 'M123' ('m123_Numero','m123_T2P','m123_T1E1S2P','m123_T1P','m123_T1S2P','m123_T3S8P','m123_TN4P','m123_TN10P','m123_TN40P','m123_TN200P','m123_Fondo','m123_D60','m123_D30','m123_D10','m123_LL','m123_LP','m123_IP','m123_AASHTO','m123_USC','m123_per_Id_Fk') VALUES ( 'mue123', 0, 0, 43.4, 369.2, 137.5, 341.2, 382.6, 419.4, 741.4, 163.4, 11.5, 1.4, 0.14, 'NO LL', 'NO LP', '-', 'A-1-a', 'GW', 3);";
	
	String sqlInsertM1256_HN_1 =
			"INSERT INTO 'M1256_HN' ('m1256_HN_RN','m1256_HN_PSHR','m1256_HN_PSSR','m1256_HN_PR','m1256_HN_PP200','m1256_HN_AASHTO','m1256_HN_USC','m1256_HN_per_Id_Fk') VALUES ('9', 82.54, 82.44, 19.93, 0, 'A-7-5', 'MH', 4);";
	
	String sqlInsertM1256_LL_1 =
			"INSERT INTO 'M1256_LL' ('m1256_LL_RN','m1256_LL_PSHR','m1256_LL_PSSR','m1256_LL_PR','m1256_LL_NG','m1256_LL_per_Id_Fk') VALUES ('21', 21.67, 18.17, 4.94, 15, 4);";
	
	String sqlInsertM1256_LL_2 =
			"INSERT INTO 'M1256_LL' ('m1256_LL_RN','m1256_LL_PSHR','m1256_LL_PSSR','m1256_LL_PR','m1256_LL_NG','m1256_LL_per_Id_Fk') VALUES ('20', 26.62, 22.55, 5.06, 29, 4);";
	
	String sqlInsertM1256_LL_3 =
			"INSERT INTO 'M1256_LL' ('m1256_LL_RN','m1256_LL_PSHR','m1256_LL_PSSR','m1256_LL_PR','m1256_LL_NG','m1256_LL_per_Id_Fk') VALUES ('42', 40.60, 34.21, 5.49, 37, 4);";
	
	String sqlInsertM1256_LP_1 =
			"INSERT INTO 'M1256_LP' ('m1256_LP_RN','m1256_LP_PSHR','m1256_LP_PSSR','m1256_LP_PR','m1256_LP_per_Id_Fk') VALUES ('4', 6.02, 5.76, 4.30, 4);";
	
	String sqlInsertM1256_LP_2 =
			"INSERT INTO 'M1256_LP' ('m1256_LP_RN','m1256_LP_PSHR','m1256_LP_PSSR','m1256_LP_PR','m1256_LP_per_Id_Fk') VALUES ('10', 9.34, 8.70, 5.04, 4);";
	
	String sqlInsertM127_1 =
			"INSERT INTO 'M127' ( 'm127_Numero','m127_w','m127_W1','m127_W2','m127_W3','m127_LC','m127_V','m127_Vo','m127_Wo','m127_miw','m127_R','m127_Gs','m127_CV','m127_Wi','m127_CL','m127_per_Id_Fk') VALUES ( 'mue127', 29.87012987012987, 123, 100, 23, 3, 5, 4, 100, 1, 25, 100, 650, 29, 48.912704507096464, 5);";
	
//	Constructor
	
	public SQLiteUd_Lab(Context contexto) {
		super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
	}

//	onCreate

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreateTableUsuario);
		db.execSQL(sqlCreateTableProyecto);
		db.execSQL(sqlCreateTableEnsayo);
		db.execSQL(sqlCreateTablePerforacion);
		db.execSQL(sqlCreateTableAnotacion);
		db.execSQL(sqlCreateTableCaptura);
		db.execSQL(sqlCreateTableM122);
		db.execSQL(sqlCreateTableM123);
		db.execSQL(sqlCreateTableM1256_HN);
		db.execSQL(sqlCreateTableM1256_LL);
		db.execSQL(sqlCreateTableM1256_LP);
		db.execSQL(sqlCreateTableM127);
		db.execSQL(sqlCreateTriggerBUsC);
		db.execSQL(sqlCreateTriggerBPrC);
		db.execSQL(sqlCreateTriggerBEnC);
		db.execSQL(sqlCreateTriggerBPeC);
		db.execSQL(sqlCreateViewVlistM122);
		db.execSQL(sqlCreateViewVlistM123);
		db.execSQL(sqlCreateViewVlistM1256);
		db.execSQL(sqlCreateViewVlistM127);
		
		db.execSQL(sqlInsertUsuario);
		db.execSQL(sqlInsertProyecto);
		db.execSQL(sqlInsertEnsayo122);
		db.execSQL(sqlInsertEnsayo123);
		db.execSQL(sqlInsertEnsayo1256);
		db.execSQL(sqlInsertEnsayo127);
		db.execSQL(sqlInsertPerforacion122_1);
		db.execSQL(sqlInsertPerforacion122_2);
		db.execSQL(sqlInsertPerforacion123_1);
		db.execSQL(sqlInsertPerforacion1256_1);
		db.execSQL(sqlInsertPerforacion127_1);
		db.execSQL(sqlInsertM122_1);
		db.execSQL(sqlInsertM122_2);
		db.execSQL(sqlInsertM122_3);
		db.execSQL(sqlInsertM122_4);
		db.execSQL(sqlInsertM123_1);
		db.execSQL(sqlInsertM1256_HN_1);
		db.execSQL(sqlInsertM1256_LL_1);
		db.execSQL(sqlInsertM1256_LL_2);
		db.execSQL(sqlInsertM1256_LL_3);
		db.execSQL(sqlInsertM1256_LP_1);
		db.execSQL(sqlInsertM1256_LP_2);
		db.execSQL(sqlInsertM127_1);
	}

	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			db.execSQL("PRAGMA foreign_keys = ON;");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {
		db.execSQL("DROP TABLE IF EXISTS " + KEY_USUARIO);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_PROYECTO);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_ENSAYO);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_PERFORACION);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_ANOTACION);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_CAPTURA);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_M122);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_M123);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_M1256_HN);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_M1256_LL);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_M1256_LP);
		db.execSQL("DROP TABLE IF EXISTS " + KEY_M127);
		db.execSQL(sqlCreateTableUsuario);
		db.execSQL(sqlCreateTableProyecto);
		db.execSQL(sqlCreateTableEnsayo);
		db.execSQL(sqlCreateTablePerforacion);
		db.execSQL(sqlCreateTableAnotacion);
		db.execSQL(sqlCreateTableCaptura);
		db.execSQL(sqlCreateTableM122);
		db.execSQL(sqlCreateTableM123);
		db.execSQL(sqlCreateTableM1256_HN);
		db.execSQL(sqlCreateTableM1256_LL);
		db.execSQL(sqlCreateTableM1256_LP);
		db.execSQL(sqlCreateTableM127);
		db.execSQL(sqlCreateTriggerBUsC);
		db.execSQL(sqlCreateTriggerBPrC);
		db.execSQL(sqlCreateTriggerBEnC);
		db.execSQL(sqlCreateTriggerBPeC);
		db.execSQL(sqlCreateViewVlistM122);
		db.execSQL(sqlCreateViewVlistM123);
		db.execSQL(sqlCreateViewVlistM1256);
		db.execSQL(sqlCreateViewVlistM127);
	}
}