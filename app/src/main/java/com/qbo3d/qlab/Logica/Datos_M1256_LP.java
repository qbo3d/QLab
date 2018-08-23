package com.qbo3d.qlab.Logica;

public class Datos_M1256_LP {

	int M1256_LP_Id;
	String M1256_LP_RN;
	Double M1256_LP_PSHR;
	Double M1256_LP_PSSR;
	Double M1256_LP_PR;
	int M1256_LP_per_Id_Fk;

	public Datos_M1256_LP() {

	}

	public Datos_M1256_LP(int M1256_LP_Id, String M1256_LP_RN,
			Double M1256_LP_PSHR, Double M1256_LP_PSSR, Double M1256_LP_PR,
			int M1256_LP_per_Id_Fk) {
		this.M1256_LP_Id = M1256_LP_Id;
		this.M1256_LP_RN = M1256_LP_RN;
		this.M1256_LP_PSHR = M1256_LP_PSHR;
		this.M1256_LP_PSSR = M1256_LP_PSSR;
		this.M1256_LP_PR = M1256_LP_PR;
		this.M1256_LP_per_Id_Fk = M1256_LP_per_Id_Fk;
	}

	public Datos_M1256_LP(String M1256_LP_RN, Double M1256_LP_PSHR,
			Double M1256_LP_PSSR, Double M1256_LP_PR, int M1256_LP_per_Id_Fk) {
		this.M1256_LP_RN = M1256_LP_RN;
		this.M1256_LP_PSHR = M1256_LP_PSHR;
		this.M1256_LP_PSSR = M1256_LP_PSSR;
		this.M1256_LP_PR = M1256_LP_PR;
		this.M1256_LP_per_Id_Fk = M1256_LP_per_Id_Fk;
	}

	public int getM1256_LPId() {
		return this.M1256_LP_Id;
	}

	public void setM1256_LPId(int M1256_LP_Id) {
		this.M1256_LP_Id = M1256_LP_Id;
	}

	public String getM1256_LP_RN() {
		return this.M1256_LP_RN;
	}

	public void setM1256_LP_RN(String M1256_LP_RN) {
		this.M1256_LP_RN = M1256_LP_RN;
	}

	public Double getM1256_LP_PSHR() {
		return this.M1256_LP_PSHR;
	}

	public void setM1256_LP_PSHR(Double M1256_LP_PSHR) {
		this.M1256_LP_PSHR = M1256_LP_PSHR;
	}

	public Double getM1256_LP_PSSR() {
		return this.M1256_LP_PSSR;
	}

	public void setM1256_LP_PSSR(Double M1256_LP_PSSR) {
		this.M1256_LP_PSSR = M1256_LP_PSSR;
	}

	public Double getM1256_LP_PR() {
		return this.M1256_LP_PR;
	}

	public void setM1256_LP_PR(Double M1256_LP_PR) {
		this.M1256_LP_PR = M1256_LP_PR;
	}

	public int getM1256_LPPerIdFk() {
		return this.M1256_LP_per_Id_Fk;
	}

	public void setM1256_LPPerIdFk(int M1256_LP_per_Id_Fk) {
		this.M1256_LP_per_Id_Fk = M1256_LP_per_Id_Fk;
	}
}