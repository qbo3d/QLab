package com.qbo3d.qlab.Logica;

import java.util.List;

public class M1256 {
	
	public static int lab1256LL_X(int num){
		int factor;
		
		factor = (int) Math.round((num-5) * 3.61 + 105);
		
		return factor;
	}
	
	public static int lab1256LL_Y(double num){
		int factor;
		
		factor = (int) Math.round((num-10) * 4.4 + 140);
		
		return factor;
	}
	
	public static int lab1256CP_X(double num){
		int factor;
		
		factor = (int) Math.round(num * 1.7 + 318);
		
		return factor;
	}
	
	public static int lab1256CP_Y(double num){
		int factor;
		
		factor = (int) Math.round(num * 2.6 + 140);
		
		return factor;
	}
	
	public static double lab1256calc01(double pshr, double pssr){
		return pshr - pssr;
	}

	public static double lab1256calc02(double pssr, double pr){
		return pssr - pr;
	}

	public static double lab1256calc03(double pa, double pss){
		Double ch;
		
		if(pss != 0)
			ch = (pa/pss) * 100;
		else
			ch = 0.0;
		return ch;
	}

	public static double lab1256calc04(List<String> listLLch, List<String> listLLng){
		Double ll;
		Double SCH = 0.0;
		Double SlogNG = 0.0;
		Double SCHxSlogNG = 0.0;
		Double SlogNGp2 = 0.0;
		Double m;
		Double b;
		
		for (int i = 0; i < listLLch.size(); i++) {
			SCH += Double.parseDouble(listLLch.get(i));
			SlogNG += Math.log10(Double.parseDouble(listLLng.get(i)));
			SCHxSlogNG += Double.parseDouble(listLLch.get(i)) * Math.log10(Double.parseDouble(listLLng.get(i)));
			SlogNGp2 += Math.pow(Math.log10(Double.parseDouble(listLLng.get(i))), 2);
		}
		
		m = (3 * SCHxSlogNG - SlogNG * SCH) / (3 * SlogNGp2 - Math.pow(SlogNG, 2));
		b = (SCH - m * SlogNG) / 3;
		ll = Math.log10(25) * m + b;
		
		return ll;
	}

	public static double lab1256calc05(List<String> listLLch){
		Double ch = 0.0;
		
		for (int i = 0; i < listLLch.size(); i++) {
			ch += Double.parseDouble(listLLch.get(i));
		}
		
		ch = ch / listLLch.size();
		
		return ch;
	}

}
