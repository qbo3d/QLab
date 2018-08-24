package com.qbo3d.qlab.Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbo3d.qlab.Logica.Datos_M122;
import com.qbo3d.qlab.Logica.Herr;
import com.qbo3d.qlab.Persistencia.SQLiteM122;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.qbo3d.qlab.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ens_122_Inf extends Activity {

	private File UD_Lab;

	String pro_nombre;
	String ens_numero;

	String m122_str = "";
	int m122_id;
	
	TextView tv_ens_122_super;
	TextView tv_m122;
	Button bt_122_con;
	
	int longC;
	SQLiteM122 mueSQLite;
	Button bt_ens_122_consultar;
	Bundle extra;
	Datos_M122 m122;
	Intent data;
	Cursor cursorM122, cursorM122_AVGw;
	boolean b1 = true,  b2 = true, b3 = true;
	private static final int REQUEST_CODE = 10;

	@SuppressLint("SdCardPath")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_122_inf);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		mueSQLite = new SQLiteM122(this);

		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			UD_Lab = new File(
					android.os.Environment.getExternalStorageDirectory(),
					"UD-Lab");
			if (!UD_Lab.exists()) {
				UD_Lab.mkdirs();
			}
			cursorM122 = mueSQLite.getAllM122(ens_numero);
			cursorM122_AVGw = mueSQLite.getAVGw(ens_numero);
			cursorM122_AVGw.moveToFirst();
			int m122PerIdFk = 0, bkp;
			
			Document document = new Document(PageSize.LETTER, 20, 20, 20, 20);
			@SuppressWarnings("unused")
			PdfWriter writer = null;
			try {
				String Nombre_Archivo;

				Date horaActual = new Date();

				Nombre_Archivo = horaActual.getYear() + 1900 + " "
						+ horaActual.getMonth() + " "
						+ horaActual.getDate() + " " + horaActual.getHours()
						+ " " + horaActual.getMinutes()
						+ " " + horaActual.getSeconds();
				writer = PdfWriter.getInstance(document, new FileOutputStream(UD_Lab
						+ "/" + ens_numero + " " + Nombre_Archivo + ".pdf"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			document.open();
			
			try {
				String escudo_str = "/sdcard/UD-Lab/Imagenes/Escudo-UD";
				Image escudo = Image.getInstance(escudo_str);
				float[] widths0 = { 12f, 100f };

				PdfPTable table0 = new PdfPTable(2);
					table0.setTotalWidth(widths0);
					PdfPCell cell0a = new PdfPCell();
					cell0a.setImage(escudo);
					cell0a.setBorder(0);
					cell0a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
					PdfPCell cell0b = new PdfPCell();
					cell0b = new PdfPCell(new Paragraph(
							"UNIVERSIDAD DISTRITAL FRANCISCO JOSE DE CALDAS\n"
									+ "FACULTAD TECNOLÍGICA\n"
									+ "LABORATORIO DE CONSTRUCCIONES CIVILES\n"
									+ "SUELOS - PAVIMENTOS - CONCRETOS"));
					cell0b.setBorder(0);
					cell0b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table0.addCell(cell0a);
					table0.addCell(cell0b);
				
				PdfPTable table1 = new PdfPTable(1);
					PdfPCell cell1a = new PdfPCell();
					cell1a.setNoWrap(false);
					cell1a.setBorder(0);
					PdfPCell cell1b = new PdfPCell();
					cell1b = new PdfPCell(
							new Paragraph(
									"DETERMINACION DEL CONTENIDO DE HUMEDAD INV E-122 - 07"));
					cell1b.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell1c = new PdfPCell();
					cell1c.setNoWrap(false);
					cell1c.setBorder(0);
					table1.addCell(cell1a);
					table1.addCell(cell1b);
					table1.addCell(cell1c);
				
				PdfPTable table2 = new PdfPTable(5);
					PdfPCell cell2a = new PdfPCell();
					cell2a.setNoWrap(false);
					cell2a.setBorder(0);
					cell2a.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2b = new PdfPCell();
					cell2b.setNoWrap(false);
					cell2b.setBorder(0);
					cell2b.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2c = new PdfPCell();
					cell2c.setNoWrap(false);
					cell2c.setBorder(0);
					cell2c.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2d = new PdfPCell();
					cell2d.setNoWrap(false);
					cell2d.setBorder(0);
					cell2d.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2e = new PdfPCell();
					cell2e = new PdfPCell(new Paragraph("Ensayo No.:"));
					cell2e.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2f = new PdfPCell();
					cell2f.setNoWrap(false);
					cell2f.setBorder(0);
					cell2f.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2g = new PdfPCell();
					cell2g.setNoWrap(false);
					cell2g.setBorder(0);
					cell2g.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2h = new PdfPCell();
					cell2h.setNoWrap(false);
					cell2h.setBorder(0);
					cell2h.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2i = new PdfPCell();
					cell2i.setNoWrap(false);
					cell2i.setBorder(0);
					cell2i.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2j = new PdfPCell();
					cell2j = new PdfPCell(new Paragraph(ens_numero));
					cell2j.setHorizontalAlignment(Element.ALIGN_CENTER);
					table2.addCell(cell2a);
					table2.addCell(cell2b);
					table2.addCell(cell2c);
					table2.addCell(cell2d);
					table2.addCell(cell2e);
					table2.addCell(cell2f);
					table2.addCell(cell2g);
					table2.addCell(cell2h);
					table2.addCell(cell2i);
					table2.addCell(cell2j);
					
				PdfPTable table3 = new PdfPTable(1);
					PdfPCell cell3a = new PdfPCell();
					cell3a.setNoWrap(false);
					cell3a.setBorder(0);
					
					table3.addCell(cell3a);
					
				float[] widths4 = { 35f, 20f, 22f, 23f };
				PdfPTable table4 = new PdfPTable(4);
					table4.setTotalWidth(widths4);
					PdfPCell cell4a = new PdfPCell();
					cell4a.setNoWrap(false);
					cell4a.setBorder(0);
					PdfPCell cell4b = new PdfPCell();
					cell4b.setNoWrap(false);
					cell4b.setBorder(0);
					PdfPCell cell4c = new PdfPCell();
					cell4c = new PdfPCell(
							new Paragraph(
									"Fecha de ensayo:"));
					PdfPCell cell4d = new PdfPCell();
					cell4d = new PdfPCell(
							new Paragraph(
									Herr.cambiarAnho(mueSQLite.getM122Fecha(ens_numero))));
					cell4d.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4e = new PdfPCell();
					cell4e = new PdfPCell(
							new Paragraph(
									"Descripción del suelo:"));
					PdfPCell cell4f = new PdfPCell();
					cell4f = new PdfPCell(
							new Paragraph(
									mueSQLite.getM122DesSuelo(ens_numero)));
					cell4f.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4g = new PdfPCell();
					cell4g = new PdfPCell(
							new Paragraph(
									"Proyecto:"));
					PdfPCell cell4h = new PdfPCell();
					cell4h = new PdfPCell(
							new Paragraph(
									mueSQLite.getM122proN(ens_numero)));
					cell4h.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4i = new PdfPCell();
					cell4i = new PdfPCell(
							new Paragraph(
									"Localización del proyecto:"));
					PdfPCell cell4j = new PdfPCell();
					cell4j = new PdfPCell(
							new Paragraph(
									mueSQLite.getM122Localizacion(ens_numero)));
					cell4j.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4k = new PdfPCell();
					cell4k = new PdfPCell(
							new Paragraph(
									"Realizado por:"));
					PdfPCell cell4l = new PdfPCell();
					cell4l = new PdfPCell(
							new Paragraph(
									mueSQLite.getM122Usuario(ens_numero)));
					cell4l.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4m = new PdfPCell();
					cell4m.setNoWrap(false);
					cell4m.setBorder(0);
					
					table4.addCell(cell4a);
					table4.addCell(cell4b);
					table4.addCell(cell4c);
					table4.addCell(cell4d);
					table4.addCell(cell4e);
					table4.addCell(cell4f);
					table4.addCell(cell4g);
					table4.addCell(cell4h);
					table4.addCell(cell4i);
					table4.addCell(cell4j);
					table4.addCell(cell4k);
					table4.addCell(cell4l);
					table4.addCell(cell4m);
					
				PdfPTable table5 = new PdfPTable(1);
					PdfPCell cell5a = new PdfPCell();
					cell5a.setNoWrap(false);
					cell5a.setBorder(0);

					table5.addCell(cell5a);

					List<String> listTipo = new ArrayList<String>();
					List<String> listEncabezado = new ArrayList<String>();
					List<String> listW1 = new ArrayList<String>();
					List<String> listW2 = new ArrayList<String>();
					List<String> listWc = new ArrayList<String>();
					List<String> listWs = new ArrayList<String>();
					List<String> listWw = new ArrayList<String>();
					List<String> listw = new ArrayList<String>();
					List<String> listProm = new ArrayList<String>();
					
					if (cursorM122.moveToFirst()) {
						do {
							bkp = m122PerIdFk;
							m122PerIdFk = Integer.parseInt(cursorM122.getString(5));
							
							if(m122PerIdFk != bkp){
								if(!cursorM122.isFirst()){
									if(b1){
										listProm.add("Humedad promedio grava:");
										listProm.add("%");
										b1 = false;
										b2 = true;
									}
									listProm.add(String.format("%.2f", Double.parseDouble(cursorM122_AVGw.getString(0))));
									cursorM122_AVGw.moveToNext();
								}
								listTipo.add(cursorM122.getString(11)); 
							}

							if(b2){
								listEncabezado.add("Descripción");
								listEncabezado.add("UN");
								listW1.add("Peso recipiente + suelo húmedo W1");
								listW1.add("g");
								listW2.add("Peso recipiente + suelo seco W2");
								listW2.add("g");
								listWc.add("Peso recipiente WC");
								listWc.add("g");
								listWs.add("Peso suelo seco Ws");
								listWs.add("g");
								listWw.add("Peso de agua Ww");
								listWw.add("g");
								listw.add("Contenido de humedad w");
								listw.add("%");
								b2 = false;
							}
							listEncabezado.add("Lata No. " + cursorM122.getString(10));
							listW1.add(String.format("%.2f", Double.parseDouble(cursorM122.getString(13)))); 
							listW2.add(String.format("%.2f", Double.parseDouble(cursorM122.getString(14)))); 
							listWc.add(String.format("%.2f", Double.parseDouble(cursorM122.getString(15)))); 
							listWs.add(String.format("%.2f", Double.parseDouble(cursorM122.getString(17)))); 
							listWw.add(String.format("%.2f", Double.parseDouble(cursorM122.getString(16)))); 
							listw.add(String.format("%.2f", Double.parseDouble(cursorM122.getString(12))));
						} while (cursorM122.moveToNext());
						if(b3){
							listProm.add("Humedad promedio Arena");
							listProm.add("%");
							b3 = false;
							b2 = true;
						}
						listProm.add(String.format("%.2f", Double.parseDouble(cursorM122_AVGw.getString(0))));
					}

				PdfPTable table6 = new PdfPTable(listTipo.size() / 2);
				PdfPTable table7 = new PdfPTable(listEncabezado.size() / 2);
				PdfPTable table10 = new PdfPTable(listW1.size() / 2);
				PdfPTable table12 = new PdfPTable(listW2.size() / 2);
				PdfPTable table14 = new PdfPTable(listWc.size() / 2);
				PdfPTable table16 = new PdfPTable(listWs.size() / 2);
				PdfPTable table18 = new PdfPTable(listWw.size() / 2);
				PdfPTable table20 = new PdfPTable(listw.size() / 2);
				PdfPTable table22 = new PdfPTable(listProm.size() / 2);
				PdfPTable table8 = new PdfPTable(listTipo.size() / 2);
				PdfPTable table9 = new PdfPTable(listEncabezado.size() / 2);
				PdfPTable table11 = new PdfPTable(listW1.size() / 2);
				PdfPTable table13 = new PdfPTable(listW2.size() / 2);
				PdfPTable table15 = new PdfPTable(listWc.size() / 2);
				PdfPTable table17 = new PdfPTable(listWs.size() / 2);
				PdfPTable table19 = new PdfPTable(listWw.size() / 2);
				PdfPTable table21 = new PdfPTable(listw.size() / 2);
				PdfPTable table23 = new PdfPTable(listProm.size() / 2);
				// table6.setTotalWidth(widths6);

				int i, j;

					for(i=0;i<listTipo.size();i++){
						
						switch(i){
						case 0:
							PdfPCell cell6a = new PdfPCell();
							cell6a.setNoWrap(false);
							cell6a.setBorder(0);
							PdfPCell  cell6b = new PdfPCell();
							cell6b = new PdfPCell(
									new Paragraph(
											listTipo.get(i)));
							cell6b.setHorizontalAlignment(Element.ALIGN_CENTER);

							table6.addCell(cell6a);
							table6.addCell(cell6b);
							break;
						case 1:
							PdfPCell cell8a = new PdfPCell();
							cell8a.setNoWrap(false);
							cell8a.setBorder(0);
							PdfPCell  cell8b = new PdfPCell();
							cell8b = new PdfPCell(
									new Paragraph(
											listTipo.get(i)));
							cell8b.setHorizontalAlignment(Element.ALIGN_CENTER);

							table8.addCell(cell8a);
							table8.addCell(cell8b);
							break;
						}
						
						for(j=0;j<listEncabezado.size()/2;j++){
							switch(i){
								case 0:
									PdfPCell  cell7a = new PdfPCell();
									cell7a = new PdfPCell(
											new Paragraph(
													listEncabezado.get(j)));
									cell7a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table7.addCell(cell7a);
									break;
								case 1:
									PdfPCell  cell9a = new PdfPCell();
									cell9a = new PdfPCell(
											new Paragraph(
													listEncabezado.get(listEncabezado.size()/2 + j)));
									cell9a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table9.addCell(cell9a);
									break;
							}
					}

					for (j = 0; j < listW1.size() / 2 ; j++) {
						switch (i) {
							case 0:
								PdfPCell cell10a = new PdfPCell();
								cell10a = new PdfPCell(new Paragraph(listW1.get(j)));
								cell10a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
								table10.addCell(cell10a);
								break;
							case 1:
								PdfPCell cell11a = new PdfPCell();
								cell11a = new PdfPCell(new Paragraph(
										listW1.get(listW1.size() / 2 + j)));
								cell11a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
								table11.addCell(cell11a);
								break;
						}
					}
						
						for(j=0;j<listW2.size()/2;j++){
							switch(i){
								case 0:
									PdfPCell  cell12a = new PdfPCell();
									cell12a = new PdfPCell(
											new Paragraph(
													listW2.get(j)));
									cell12a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table12.addCell(cell12a);
									break;
								case 1:
									PdfPCell  cell13a = new PdfPCell();
									cell13a = new PdfPCell(
											new Paragraph(
													listW2.get(listW2.size()/2 + j)));
									cell13a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table13.addCell(cell13a);
									break;
							}
						}

						for(j=0;j<listWc.size()/2;j++){
							switch(i){
								case 0:
									PdfPCell  cell14a = new PdfPCell();
									cell14a = new PdfPCell(
											new Paragraph(
													listWc.get(j)));
									cell14a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table14.addCell(cell14a);
									break;
								case 1:
									PdfPCell  cell15a = new PdfPCell();
									cell15a = new PdfPCell(
											new Paragraph(
													listWc.get(listWc.size()/2 + j)));
									cell15a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table15.addCell(cell15a);
									break;
							}
						}
						
						for(j=0;j<listWs.size()/2;j++){
							switch(i){
								case 0:
									PdfPCell  cell16a = new PdfPCell();
									cell16a = new PdfPCell(
											new Paragraph(
													listWs.get(j)));
									cell16a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table16.addCell(cell16a);
									break;
								case 1:
									PdfPCell  cell17a = new PdfPCell();
									cell17a = new PdfPCell(
											new Paragraph(
													listWs.get(listWs.size()/2 + j)));
									cell17a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table17.addCell(cell17a);
									break;
							}
						}
						
						for(j=0;j<listWw.size()/2;j++){
							switch(i){
								case 0:
									PdfPCell  cell18a = new PdfPCell();
									cell18a = new PdfPCell(
											new Paragraph(
													listWw.get(j)));
									cell18a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table18.addCell(cell18a);
									break;
								case 1:
									PdfPCell  cell19a = new PdfPCell();
									cell19a = new PdfPCell(
											new Paragraph(
													listWw.get(listWw.size()/2 + j)));
									cell19a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table19.addCell(cell19a);
									break;
							}
						}
						
						for(j=0;j<listw.size()/2;j++){
							switch(i){
								case 0:
									PdfPCell  cell20a = new PdfPCell();
									cell20a = new PdfPCell(
											new Paragraph(
													listw.get(j)));
									cell20a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table20.addCell(cell20a);
									break;
								case 1:
									PdfPCell  cell21a = new PdfPCell();
									cell21a = new PdfPCell(
											new Paragraph(
													listw.get(listw.size()/2 + j)));
									cell21a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table21.addCell(cell21a);
									break;
							}
						}
						
						for(j=0;j<listProm.size()/2;j++){
							switch(i){
								case 0:
									PdfPCell  cell22a = new PdfPCell();
									cell22a = new PdfPCell(
											new Paragraph(
													listProm.get(j)));
									cell22a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table22.addCell(cell22a);
									break;
								case 1:
									PdfPCell  cell23a = new PdfPCell();
									cell23a = new PdfPCell(
											new Paragraph(
													listProm.get(listProm.size()/2 + j)));
									cell23a.setHorizontalAlignment(Element.ALIGN_CENTER);
	
									table23.addCell(cell23a);
									break;
							}
						}
						 
					}

				longC = cursorM122.getCount();
				float[] widthsA = { 40f, 15f, 15f, 15f };
				float[] widthsB = { 40f, 15f, 15f, 15f, 15f };
				if(longC / 2 == 2){
					table7.setTotalWidth(widthsA);
					table9.setTotalWidth(widthsA);
					table10.setTotalWidth(widthsA);
					table11.setTotalWidth(widthsA);
					table12.setTotalWidth(widthsA);
					table13.setTotalWidth(widthsA);
					table14.setTotalWidth(widthsA);
					table15.setTotalWidth(widthsA);
					table16.setTotalWidth(widthsA);
					table17.setTotalWidth(widthsA);
					table18.setTotalWidth(widthsA);
					table19.setTotalWidth(widthsA);
					table20.setTotalWidth(widthsA);
					table21.setTotalWidth(widthsA);
				}else if(longC / 2 == 3){
					table7.setTotalWidth(widthsB);
					table9.setTotalWidth(widthsB);
					table10.setTotalWidth(widthsB);
					table11.setTotalWidth(widthsB);
					table12.setTotalWidth(widthsB);
					table13.setTotalWidth(widthsB);
					table14.setTotalWidth(widthsB);
					table15.setTotalWidth(widthsB);
					table16.setTotalWidth(widthsB);
					table17.setTotalWidth(widthsB);
					table18.setTotalWidth(widthsB);
					table19.setTotalWidth(widthsB);
					table20.setTotalWidth(widthsB);
					table21.setTotalWidth(widthsB);
				}
				
				document.add(table0);
				document.add(table1);
				document.add(table2);
				document.add(table3);
				document.add(table4);
				document.add(table5);
				document.add(table6);
				document.add(table7);
				document.add(table10);
				document.add(table12);
				document.add(table14);
				document.add(table16);
				document.add(table18);
				document.add(table20);
				document.add(table22);
				document.add(table8);
				document.add(table9);
				document.add(table11);
				document.add(table13);
				document.add(table15);
				document.add(table17);
				document.add(table19);
				document.add(table21);
				document.add(table23);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			document.close();
			mueSQLite.close();
			finish();
		} else {
			Toast.makeText(this, "No SDCard Mounted!", Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_cari, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.men_iniciar:
			iniciarProyecto();
			return true;
		case R.id.men_regresar:
			finish();
			return true;
		case R.id.men_anotar:
			callAnotar();
			return true;
		case R.id.men_capturar:
			callCapturar();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void callAnotar() {
		Intent intent = new Intent();
		intent.setClass(this, Lab_Ano_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivityForResult(intent, REQUEST_CODE);
	}

	private void callCapturar() {
		Intent intent = new Intent();
		intent.setClass(this, Lab_Cap_Crear.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivityForResult(intent, REQUEST_CODE);
	}
}