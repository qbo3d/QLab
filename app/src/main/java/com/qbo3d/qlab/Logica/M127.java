package com.qbo3d.qlab.Logica;

public class M127 {
	
	public static double lab127calc01(double W1, double W2, double W3){
		return ((W1 - W2) / (W2 - W3)) * 100;
	}
	
	public static double lab127calc02(double W2, double W3){
		return W2 - W3;
	}
	
	public static double lab127calc03(double w, double V, double Vo, double gw, double Wo){
		return w - (((V - Vo) * gw) / Wo) * 100;
	}
	
	public static double lab127calc04(double Rgw, double Gs){
		return ((1 / Rgw) - (1 / Gs)) * 100;
	}
	
	public static double lab127calc05(double Wo, double Vo){
		return Wo / Vo;
	}
	
	public static double lab127calc06(double Wi, double LC, double R){
		return (Wi - LC) * R;
	}
	
	public static double lab127calc07(double CV){
		return (1 - (Math.cbrt(100 / (CV + 100)))) * 100;
	}

}
