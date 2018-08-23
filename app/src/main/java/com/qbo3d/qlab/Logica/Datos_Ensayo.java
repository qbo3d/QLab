package com.qbo3d.qlab.Logica;

public class Datos_Ensayo {

	int ens_Id;
	String ens_Numero;
	String ens_Descripcion_Suelo;
	String ens_Fecha;
	String ens_Norma;
	String ens_pro_Nombre_Fk;

	public Datos_Ensayo() {

	}

	public Datos_Ensayo(int ens_Id, String ens_Numero,
			String ens_Descripcion_Suelo, String ens_Fecha, String ens_Norma,
			String ens_pro_Nombre_Fk) {
		this.ens_Id = ens_Id;
		this.ens_Numero = ens_Numero;
		this.ens_Descripcion_Suelo = ens_Descripcion_Suelo;
		this.ens_Fecha = ens_Fecha;
		this.ens_Norma = ens_Norma;
		this.ens_pro_Nombre_Fk = ens_pro_Nombre_Fk;
	}

	public Datos_Ensayo(String ens_Numero, String ens_Descripcion_Suelo,
			String ens_Fecha, String ens_Norma, String ens_pro_Nombre_Fk) {
		this.ens_Numero = ens_Numero;
		this.ens_Descripcion_Suelo = ens_Descripcion_Suelo;
		this.ens_Fecha = ens_Fecha;
		this.ens_Norma = ens_Norma;
		this.ens_pro_Nombre_Fk = ens_pro_Nombre_Fk;
	}

	public int getEnsId() {
		return this.ens_Id;
	}

	public void setEnsId(int ens_Id) {
		this.ens_Id = ens_Id;
	}

	public String getEnsNumero() {
		return this.ens_Numero;
	}

	public void setEnsNumero(String ens_Numero) {
		this.ens_Numero = ens_Numero;
	}

	public String getEnsDescripcionSuelo() {
		return this.ens_Descripcion_Suelo;
	}

	public void setEnsDescripcionSuelo(String ens_Descripcion_Suelo) {
		this.ens_Descripcion_Suelo = ens_Descripcion_Suelo;
	}

	public String getEnsFecha() {
		return this.ens_Fecha;
	}

	public void setEnsFecha(String ens_Fecha) {
		this.ens_Fecha = ens_Fecha;
	}

	public String getEnsNorma() {
		return this.ens_Norma;
	}

	public void setEnsNorma(String ens_Norma) {
		this.ens_Norma = ens_Norma;
	}

	public String getEnsProNombreFk() {
		return this.ens_pro_Nombre_Fk;
	}

	public void setEnsProNombreFk(String ens_pro_Nombre_Fk) {
		this.ens_pro_Nombre_Fk = ens_pro_Nombre_Fk;
	}
}