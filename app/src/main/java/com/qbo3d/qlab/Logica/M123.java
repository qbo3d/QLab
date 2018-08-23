package com.qbo3d.qlab.Logica;

public class M123 {
	
	public static double lab123pR(double mr, double mrTotal){
		Double pr;
		
		if(mrTotal != 0)
			pr = (mr / mrTotal) * 100;
		else
			pr = 0.0;
		
		return pr;
	}
	
	public static double lab123pP(double pr, double pa, double mrTotal){
		Double pp;
		
		if(mrTotal != 0)
			pp = pa - pr;
		else
			pp = 0.0;
		return pp;
	}
	
	public static double lab123pG(double ppTN4P){
		Double pg;
		
		pg = 100 - ppTN4P;
		
		return pg;
	}
	
	public static double lab123pA(double pg, double ppTN200P){
		Double pa;
		
		pa = 100 - pg - ppTN200P;
		
		return pa;
	}
	
	public static double lab123CU(double D60, double D10){
		Double CU;
		
		CU = D60 / D10;
		
		return CU;
	}
	
	public static double lab123CC(double D60, double D30, double D10){
		Double CC;
		
		CC = D30 * D30 / (D10 * D60);
		
		return CC;
	}
	
	public static int lab123E(double num){
		int factor = 524;
		
		if(num <= 0.1 && num >= 0.01)
			factor = (int) Math.round(524 - (Math.log10(num * 100) * 105));
		if(num <= 1 && num > 0.1)
			factor = (int) Math.round(524 - (Math.log10(num * 10) * 105 + 105));
		if(num <= 10 && num > 1)
			factor = (int) Math.round(524 - (Math.log10(num) * 105 + 105 +105));
		if(num <= 100 && num > 10)
			factor = (int) Math.round(524 - (Math.log10(num / 10) * 105 + 105 + 105 + 105));
		
		return factor;
	}
	
	public static int lab123F(double num){
		int factor;
		
		factor = (int) Math.round(num * 1.8 + 76);
		
		return factor;
	}
	
	public static double lab123calc01(double masa_total, double masa_retenida_tamiz_75){
		return (masa_total - masa_retenida_tamiz_75) / masa_total;
	}
	
	public static double lab123calc02(double masa_retenida_tamiz, double masa_total){
		return (masa_retenida_tamiz / masa_total) * 100;
	}
	
	public static double lab123calc03(double p_retenido_acumulado){
		return 100 - p_retenido_acumulado;
	}
	
	public static double lab123calc04(double W, double W1){
		return ((W - W1) / W1) * 100;
	}

}
