package com.qbo3d.qlab.Logica;

public class Datos_Proyecto {

	String pro_Nombre;
	String pro_Localizacion;
	String pro_Fecha;
	int pro_usu_Cedula_Fk;

	public Datos_Proyecto() {

	}

	public Datos_Proyecto(String pro_Nombre, String pro_Localizacion,
			String pro_Fecha, int pro_usu_Cedula_Fk) {
		this.pro_Nombre = pro_Nombre;
		this.pro_Localizacion = pro_Localizacion;
		this.pro_Fecha = pro_Fecha;
		this.pro_usu_Cedula_Fk = pro_usu_Cedula_Fk;
	}

	public String getProNombre() {
		return this.pro_Nombre;
	}

	public void setProNombre(String pro_Nombre) {
		this.pro_Nombre = pro_Nombre;
	}

	public String getProLocalizacion() {
		return this.pro_Localizacion;
	}

	public void setProLocalizacion(String pro_Localizacion) {
		this.pro_Localizacion = pro_Localizacion;
	}

	public String getProFecha() {
		return this.pro_Fecha;
	}

	public void setProFecha(String pro_Fecha) {
		this.pro_Fecha = pro_Fecha;
	}

	public int getProUsuCedulaFk() {
		return this.pro_usu_Cedula_Fk;
	}

	public void setProUsuCedulaFk(int pro_usu_Cedula_Fk) {
		this.pro_usu_Cedula_Fk = pro_usu_Cedula_Fk;
	}
}