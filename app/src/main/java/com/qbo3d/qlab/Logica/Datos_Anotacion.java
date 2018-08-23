package com.qbo3d.qlab.Logica;

public class Datos_Anotacion {

	int ano_Id;
	String ano_Nota;
	int ano_ens_Id_Fk;

	public Datos_Anotacion() {

	}

	public Datos_Anotacion(int ano_Id, String ano_Nota, int ano_ens_Id_Fk) {
		this.ano_Id = ano_Id;
		this.ano_Nota = ano_Nota;
		this.ano_ens_Id_Fk = ano_ens_Id_Fk;
	}

	public Datos_Anotacion(String ano_Nota, int ano_ens_Id_Fk) {
		this.ano_Nota = ano_Nota;
		this.ano_ens_Id_Fk = ano_ens_Id_Fk;
	}

	public int getAnoId() {
		return this.ano_Id;
	}

	public void setAnoId(int ano_Id) {
		this.ano_Id = ano_Id;
	}

	public String getAnoNota() {
		return this.ano_Nota;
	}

	public void setAnoNota(String ano_Nota) {
		this.ano_Nota = ano_Nota;
	}

	public int getAnoEnsIdFk() {
		return this.ano_ens_Id_Fk;
	}

	public void setAnoEnsIdFk(int ano_ens_Id_Fk) {
		this.ano_ens_Id_Fk = ano_ens_Id_Fk;
	}
}