package com.qbo3d.qlab.Logica;

public class Datos_Usuario {

	int usu_Cedula;
	String usu_Nombre;
	String usu_Apellido;
	String usu_Codigo;
	String usu_FechaNacimiento;
	String usu_Foto;
	String usu_Email;
	String usu_Cel;
	String usu_Tipo;
	
	public Datos_Usuario(){
		
	}

	public Datos_Usuario(int usu_Cedula, String usu_Nombre,
			String usu_Apellido, String usu_Codigo, String usu_FechaNacimiento,
			String usu_Foto, String usu_Email, String usu_Cel, String usu_Tipo) {
		this.usu_Cedula = usu_Cedula;
		this.usu_Nombre = usu_Nombre;
		this.usu_Apellido = usu_Apellido;
		this.usu_Codigo = usu_Codigo;
		this.usu_FechaNacimiento = usu_FechaNacimiento;
		this.usu_Foto = usu_Foto;
		this.usu_Email = usu_Email;
		this.usu_Cel = usu_Cel;
		this.usu_Tipo = usu_Tipo;
	}

	public int getUsuCedula() {
		return this.usu_Cedula;
	}

	public void setUsuCedula(int usu_Cedula) {
		this.usu_Cedula = usu_Cedula;
	}

	public String getUsuNombre() {
		return this.usu_Nombre;
	}

	public void setUsuNombre(String usu_Nombre) {
		this.usu_Nombre = usu_Nombre;
	}

	public String getUsuApellido() {
		return this.usu_Apellido;
	}

	public void setUsuApellido(String usu_Apellido) {
		this.usu_Apellido = usu_Apellido;
	}

	public String getUsuCodigo() {
		return this.usu_Codigo;
	}

	public void setUsuCodigo(String usu_Codigo) {
		this.usu_Codigo = usu_Codigo;
	}

	public String getUsuFechaNacimiento() {
		return this.usu_FechaNacimiento;
	}

	public void setUsuFechaNacimiento(String usu_FechaNacimiento) {
		this.usu_FechaNacimiento = usu_FechaNacimiento;
	}

	public String getUsuFoto() {
		return this.usu_Foto;
	}

	public void setUsuFoto(String usu_Foto) {
		this.usu_Foto = usu_Foto;
	}

	public String getUsuEmail() {
		return this.usu_Email;
	}

	public void setUsuEmail(String usu_Email) {
		this.usu_Email = usu_Email;
	}

	public String getUsuCel() {
		return this.usu_Cel;
	}

	public void setUsuCel(String usu_Cel) {
		this.usu_Cel = usu_Cel;
	}

	public String getUsuTipo() {
		return this.usu_Tipo;
	}

	public void setUsuTipo(String usu_Tipo) {
		this.usu_Tipo = usu_Tipo;
	}
}