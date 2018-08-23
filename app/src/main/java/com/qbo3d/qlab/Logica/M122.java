package com.qbo3d.qlab.Logica;

public class M122 {
	
	public static double lab122calc01(){
		return 0;
	}
	
	public static double lab122calc02(double W1, double W2, double Wc){
		return ((W1 - W2) / (W2 - Wc)) * 100;
	}
	
	public static double lab122calc03(double Ww, double Ws){
		return (Ww / Ws) * 100;
	}
	
	public static double lab122calc04(double W1, double W2){
		return W1 - W2;
	}
	
	public static double lab122calc05(double W2, double Wc){
		return W2 - Wc;
	}

}
