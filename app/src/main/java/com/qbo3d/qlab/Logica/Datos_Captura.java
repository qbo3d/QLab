package com.qbo3d.qlab.Logica;

public class Datos_Captura {

	int cap_Id;
	String cap_Imagen;
	String cap_Nota;
	int cap_ens_Id_Fk;

	public Datos_Captura() {

	}

	public Datos_Captura(int cap_Id, String cap_Imagen, String cap_Nota,
			int cap_ens_Id_Fk) {
		this.cap_Id = cap_Id;
		this.cap_Imagen = cap_Imagen;
		this.cap_Nota = cap_Nota;
		this.cap_ens_Id_Fk = cap_ens_Id_Fk;
	}

	public Datos_Captura(String cap_Imagen, String cap_Nota,
			int cap_ens_Id_Fk) {
		this.cap_Imagen = cap_Imagen;
		this.cap_Nota = cap_Nota;
		this.cap_ens_Id_Fk = cap_ens_Id_Fk;
	}

	public int getCapId() {
		return this.cap_Id;
	}

	public void setCapId(int cap_Id) {
		this.cap_Id = cap_Id;
	}

	public String getCapImagen() {
		return this.cap_Imagen;
	}

	public void setCapImagen(String cap_Imagen) {
		this.cap_Imagen = cap_Imagen;
	}

	public String getCapNota() {
		return this.cap_Nota;
	}

	public void setCapNota(String cap_Nota) {
		this.cap_Nota = cap_Nota;
	}

	public int getCapEnsIdFk() {
		return this.cap_ens_Id_Fk;
	}

	public void setCapEnsIdFk(int cap_ens_Id_Fk) {
		this.cap_ens_Id_Fk = cap_ens_Id_Fk;
	}
}