package com.qbo3d.qlab.Logica;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Herr {

	public static void msgOK(String str, String str2, Context act) {
		// Put up the Yes/No message box
		AlertDialog.Builder builder = new AlertDialog.Builder(act);
		builder.setTitle(str).setMessage(str2)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				})
				.show();
	}

	public static void msgYN(String str, String str2, Context act) {
		// Put up the Yes/No message box
		AlertDialog.Builder builder = new AlertDialog.Builder(act);
		builder.setTitle(str).setMessage(str2)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Yes button clicked, do something
					}
				})
				 .setNegativeButton("No", null) //Do nothing on no
				.show();
	}
	
	public static String cambiarAnho(String fechaIni){
		String fechaFin;
		int i1 = 0, i2 = 0;
		boolean b1 = true;

		for (int i = 0; i < fechaIni.length(); i++) {
			if (fechaIni.charAt(i) == '-') {
				if (b1 == true) {
					i1 = i;
					b1 = false;
				} else {
					i2 = i;
				}
			}
		}

		fechaFin = (Integer.parseInt(fechaIni.substring(0, i1))
				+ " / "
				+ (Integer.parseInt(fechaIni.substring(i1 + 1, i2)) + 1)
				+ " / " + Integer.parseInt(fechaIni.substring(i2 + 1)));
		
		return fechaFin;
	}

}
