package com.qbo3d.qlab.Logica;

public class M125 {
	
	public static double lab125calc01(double masa_agua, double masa_suelo_secado_horno){
		return (masa_agua / masa_suelo_secado_horno) * 100;
	}
	
	public static double lab125calc02(double WN, double N){
		return WN * Math.pow((N / 25), 0.121);
	}
	
	public static double lab125calc03(double K, double WN){
		return K * WN;
	}

}
