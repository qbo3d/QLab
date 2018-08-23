package com.qbo3d.qlab.Logica;

public class M126 {
	
	public static double lab126calc01(double masa_agua, double masa_suelo_secado_horno){
		return (masa_agua / masa_suelo_secado_horno) * 100;
	}

	public static double lab126calc02(double limite_liquido, double limite_plastico){
		return limite_liquido - limite_plastico;
	}

}
