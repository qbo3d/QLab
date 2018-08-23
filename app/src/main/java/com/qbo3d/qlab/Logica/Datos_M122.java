package com.qbo3d.qlab.Logica;

public class Datos_M122 {

	int M122_Id;
	String M122_Numero;
	String M122_Tipo;
	Double M122_w;
	Double M122_W1;
	Double M122_W2;
	Double M122_Wc;
	Double M122_Ww;
	Double M122_Ws;
	int M122_per_Id_Fk;

	public Datos_M122() {

	}

	public Datos_M122(int M122_Id, String M122_Numero, String M122_Tipo,
			Double M122_w, Double M122_W1, Double M122_W2, Double M122_Wc,
			Double M122_Ww, Double M122_Ws, int M122_per_Id_Fk) {
		this.M122_Id = M122_Id;
		this.M122_Numero = M122_Numero;
		this.M122_Tipo = M122_Tipo;
		this.M122_w = M122_w;
		this.M122_W1 = M122_W1;
		this.M122_W2 = M122_W2;
		this.M122_Wc = M122_Wc;
		this.M122_Ww = M122_Ww;
		this.M122_Ws = M122_Ws;
		this.M122_per_Id_Fk = M122_per_Id_Fk;
	}

	public Datos_M122(String M122_Numero, String M122_Tipo, Double M122_w,
			Double M122_W1, Double M122_W2, Double M122_Wc, Double M122_Ww,
			Double M122_Ws, int M122_per_Id_Fk) {
		this.M122_Numero = M122_Numero;
		this.M122_Tipo = M122_Tipo;
		this.M122_w = M122_w;
		this.M122_W1 = M122_W1;
		this.M122_W2 = M122_W2;
		this.M122_Wc = M122_Wc;
		this.M122_Ww = M122_Ww;
		this.M122_Ws = M122_Ws;
		this.M122_per_Id_Fk = M122_per_Id_Fk;
	}

	public int getM122Id() {
		return this.M122_Id;
	}

	public void setM122Id(int M122_Id) {
		this.M122_Id = M122_Id;
	}

	public String getM122Numero() {
		return this.M122_Numero;
	}

	public void setM122Numero(String M122_Numero) {
		this.M122_Numero = M122_Numero;
	}

	public String getM122Tipo() {
		return this.M122_Tipo;
	}

	public void setM122Tipo(String M122_Tipo) {
		this.M122_Tipo = M122_Tipo;
	}

	public Double getM122w() {
		return this.M122_w;
	}

	public void setM122w(Double M122_w) {
		this.M122_w = M122_w;
	}

	public Double getM122W1() {
		return this.M122_W1;
	}

	public void setM122W1(Double M122_W1) {
		this.M122_W1 = M122_W1;
	}

	public Double getM122W2() {
		return this.M122_W2;
	}

	public void setM122W2(Double M122_W2) {
		this.M122_W2 = M122_W2;
	}

	public Double getM122Wc() {
		return this.M122_Wc;
	}

	public void setM122Wc(Double M122_Wc) {
		this.M122_Wc = M122_Wc;
	}

	public Double getM122Ww() {
		return this.M122_Ww;
	}

	public void setM122Ww(Double M122_Ww) {
		this.M122_Ww = M122_Ww;
	}

	public Double getM122Ws() {
		return this.M122_Ws;
	}

	public void setM122Ws(Double M122_Ws) {
		this.M122_Ws = M122_Ws;
	}

	public int getM122PerIdFk() {
		return this.M122_per_Id_Fk;
	}

	public void setM122PerIdFk(int M122_per_Id_Fk) {
		this.M122_per_Id_Fk = M122_per_Id_Fk;
	}
}