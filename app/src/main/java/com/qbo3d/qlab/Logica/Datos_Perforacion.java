package com.qbo3d.qlab.Logica;

public class Datos_Perforacion {

	int per_Id;
	String per_Numero;
	double per_Profundidad;
	String per_Observacion;
	int per_ens_Id_Fk;

	public Datos_Perforacion() {

	}

	public Datos_Perforacion(int per_Id, String per_Numero,
			double per_Profundidad, String per_Observacion, 
			int per_ens_Id_Fk) {
		this.per_Id = per_Id;
		this.per_Numero = per_Numero;
		this.per_Profundidad = per_Profundidad;
		this.per_Observacion = per_Observacion;
		this.per_ens_Id_Fk = per_ens_Id_Fk;
	}

	public Datos_Perforacion(String per_Numero, double per_Profundidad,
			String per_Observacion, int per_ens_Id_Fk) {
		this.per_Numero = per_Numero;
		this.per_Profundidad = per_Profundidad;
		this.per_Observacion = per_Observacion;
		this.per_ens_Id_Fk = per_ens_Id_Fk;
	}

	public int getPerId() {
		return this.per_Id;
	}

	public void setPerId(int per_Id) {
		this.per_Id = per_Id;
	}

	public String getPerNumero() {
		return this.per_Numero;
	}

	public void setPerNumero(String per_Numero) {
		this.per_Numero = per_Numero;
	}

	public double getPerProfundidad() {
		return this.per_Profundidad;
	}

	public void setPerProfundidad(double per_Profundidad) {
		this.per_Profundidad = per_Profundidad;
	}

	public String getPerObservacion() {
		return this.per_Observacion;
	}

	public void setPerObservacion(String per_Observacion) {
		this.per_Observacion = per_Observacion;
	}

	public int getPerEnsIdFk() {
		return this.per_ens_Id_Fk;
	}

	public void setPerEnsIdFk(int per_ens_Id_Fk) {
		this.per_ens_Id_Fk = per_ens_Id_Fk;
	}
}