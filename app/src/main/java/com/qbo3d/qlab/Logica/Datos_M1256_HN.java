package com.qbo3d.qlab.Logica;

public class Datos_M1256_HN {

	int M1256_HN_Id;
	String M1256_HN_RN;
	Double M1256_HN_PSHR;
	Double M1256_HN_PSSR;
	Double M1256_HN_PR;
	Double M1256_HN_PP200;
	String M1256_HN_AASHTO;
	String M1256_HN_USC;
	int M1256_HN_per_Id_Fk;

	public Datos_M1256_HN() {

	}

	public Datos_M1256_HN(int M1256_HN_Id, String M1256_HN_RN,
			Double M1256_HN_PSHR, Double M1256_HN_PSSR, Double M1256_HN_PR,
			Double M1256_HN_PP200, String M1256_HN_AASHTO, String M1256_HN_USC,
			int M1256_HN_per_Id_Fk) {
		this.M1256_HN_Id = M1256_HN_Id;
		this.M1256_HN_RN = M1256_HN_RN;
		this.M1256_HN_PSHR = M1256_HN_PSHR;
		this.M1256_HN_PSSR = M1256_HN_PSSR;
		this.M1256_HN_PR = M1256_HN_PR;
		this.M1256_HN_PP200 = M1256_HN_PR;
		this.M1256_HN_AASHTO = M1256_HN_AASHTO;
		this.M1256_HN_USC = M1256_HN_USC;
		this.M1256_HN_per_Id_Fk = M1256_HN_per_Id_Fk;
	}

	public Datos_M1256_HN(String M1256_HN_RN, Double M1256_HN_PSHR,
			Double M1256_HN_PSSR, Double M1256_HN_PR, Double M1256_HN_PP200,
			String M1256_HN_AASHTO, String M1256_HN_USC, int M1256_HN_per_Id_Fk) {
		this.M1256_HN_RN = M1256_HN_RN;
		this.M1256_HN_PSHR = M1256_HN_PSHR;
		this.M1256_HN_PSSR = M1256_HN_PSSR;
		this.M1256_HN_PR = M1256_HN_PR;
		this.M1256_HN_PP200 = M1256_HN_PR;
		this.M1256_HN_AASHTO = M1256_HN_AASHTO;
		this.M1256_HN_USC = M1256_HN_USC;
		this.M1256_HN_per_Id_Fk = M1256_HN_per_Id_Fk;
	}

	public int getM1256_HNId() {
		return this.M1256_HN_Id;
	}

	public void setM1256_HNId(int M1256_HN_Id) {
		this.M1256_HN_Id = M1256_HN_Id;
	}

	public String getM1256_HN_RN() {
		return this.M1256_HN_RN;
	}

	public void setM1256_HN_RN(String M1256_HN_RN) {
		this.M1256_HN_RN = M1256_HN_RN;
	}

	public Double getM1256_HN_PSHR() {
		return this.M1256_HN_PSHR;
	}

	public void setM1256_HN_PSHR(Double M1256_HN_PSHR) {
		this.M1256_HN_PSHR = M1256_HN_PSHR;
	}

	public Double getM1256_HN_PSSR() {
		return this.M1256_HN_PSSR;
	}

	public void setM1256_HN_PSSR(Double M1256_HN_PSSR) {
		this.M1256_HN_PSSR = M1256_HN_PSSR;
	}

	public Double getM1256_HN_PR() {
		return this.M1256_HN_PR;
	}

	public void setM1256_HN_PR(Double M1256_HN_PR) {
		this.M1256_HN_PR = M1256_HN_PR;
	}

	public Double getM1256_HN_PP200() {
		return this.M1256_HN_PP200;
	}

	public void setM1256_HN_PP200(Double M1256_HN_PP200) {
		this.M1256_HN_PP200 = M1256_HN_PP200;
	}

	public String getM1256_HN_ASSHTO() {
		return this.M1256_HN_AASHTO;
	}

	public void setM1256_HN_AASHTO(String M1256_HN_AASHTO) {
		this.M1256_HN_AASHTO = M1256_HN_AASHTO;
	}

	public String getM1256_HN_USC() {
		return this.M1256_HN_USC;
	}

	public void setM1256_HN_USC(String M1256_HN_USC) {
		this.M1256_HN_USC = M1256_HN_USC;
	}

	public int getM1256_HNPerIdFk() {
		return this.M1256_HN_per_Id_Fk;
	}

	public void setM1256_HNPerIdFk(int M1256_HN_per_Id_Fk) {
		this.M1256_HN_per_Id_Fk = M1256_HN_per_Id_Fk;
	}
}