package com.qbo3d.qlab.Logica;

public class Datos_M1256_LL {

	int M1256_LL_Id;
	String M1256_LL_RN;
	Double M1256_LL_PSHR;
	Double M1256_LL_PSSR;
	Double M1256_LL_PR;
	Double M1256_LL_NG;
	int M1256_LL_per_Id_Fk;

	public Datos_M1256_LL() {

	}

	public Datos_M1256_LL(int M1256_LL_Id, String M1256_LL_RN,
			Double M1256_LL_PSHR, Double M1256_LL_PSSR, Double M1256_LL_PR,
			Double M1256_LL_NG, int M1256_LL_per_Id_Fk) {
		this.M1256_LL_Id = M1256_LL_Id;
		this.M1256_LL_RN = M1256_LL_RN;
		this.M1256_LL_PSHR = M1256_LL_PSHR;
		this.M1256_LL_PSSR = M1256_LL_PSSR;
		this.M1256_LL_PR = M1256_LL_PR;
		this.M1256_LL_NG = M1256_LL_NG;
		this.M1256_LL_per_Id_Fk = M1256_LL_per_Id_Fk;
	}

	public Datos_M1256_LL(String M1256_LL_RN, Double M1256_LL_PSHR,
			Double M1256_LL_PSSR, Double M1256_LL_PR, Double M1256_LL_NG,
			int M1256_LL_per_Id_Fk) {
		this.M1256_LL_RN = M1256_LL_RN;
		this.M1256_LL_PSHR = M1256_LL_PSHR;
		this.M1256_LL_PSSR = M1256_LL_PSSR;
		this.M1256_LL_PR = M1256_LL_PR;
		this.M1256_LL_NG = M1256_LL_NG;
		this.M1256_LL_per_Id_Fk = M1256_LL_per_Id_Fk;
	}

	public int getM1256_LLId() {
		return this.M1256_LL_Id;
	}

	public void setM1256_LLId(int M1256_LL_Id) {
		this.M1256_LL_Id = M1256_LL_Id;
	}

	public String getM1256_LL_RN() {
		return this.M1256_LL_RN;
	}

	public void setM1256_LL_RP(String M1256_LL_RN) {
		this.M1256_LL_RN = M1256_LL_RN;
	}

	public Double getM1256_LL_PSHR() {
		return this.M1256_LL_PSHR;
	}

	public void setM1256_LL_PSHR(Double M1256_LL_PSHR) {
		this.M1256_LL_PSHR = M1256_LL_PSHR;
	}

	public Double getM1256_LL_PSSR() {
		return this.M1256_LL_PSSR;
	}

	public void setM1256_LL_PSSR(Double M1256_LL_PSSR) {
		this.M1256_LL_PSSR = M1256_LL_PSSR;
	}

	public Double getM1256_LL_PR() {
		return this.M1256_LL_PR;
	}

	public void setM1256_LL_PR(Double M1256_LL_PR) {
		this.M1256_LL_PR = M1256_LL_PR;
	}

	public Double getM1256_LL_NG() {
		return this.M1256_LL_NG;
	}

	public void setM1256_LL_NG(Double M1256_LL_NG) {
		this.M1256_LL_NG = M1256_LL_NG;
	}

	public int getM1256_LLPerIdFk() {
		return this.M1256_LL_per_Id_Fk;
	}

	public void setM1256_LLPerIdFk(int M1256_LL_per_Id_Fk) {
		this.M1256_LL_per_Id_Fk = M1256_LL_per_Id_Fk;
	}
}