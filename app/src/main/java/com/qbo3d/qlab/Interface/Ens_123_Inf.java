package com.qbo3d.qlab.Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.qbo3d.qlab.Logica.Datos_M123;
import com.qbo3d.qlab.Logica.Herr;
import com.qbo3d.qlab.Logica.M123;
import com.qbo3d.qlab.Persistencia.SQLiteM123;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
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

public class Ens_123_Inf extends Activity {

	private File UD_Lab;

	String pro_nombre;
	String ens_numero;

	String m123_str = "";
	int m123_id;
	
	TextView tv_ens_123_super;
	TextView tv_m123;
	Button bt_123_con;
	
	int longC;
	SQLiteM123 mueSQLite;
	Button bt_ens_123_consultar;
	Bundle extra;
	Datos_M123 m123;
	Intent data;
	Cursor cursorM123;
	private static final int REQUEST_CODE = 10;

	Double mrT2P, mrT1E1S2P, mrT1P, mrT1S2P, mrT3S8P, mrTN4P, mrTN10P, mrTN40P, mrTN200P, mrFondo, mrTotal;
	Double prT2P, prT1E1S2P, prT1P, prT1S2P, prT3S8P, prTN4P, prTN10P, prTN40P, prTN200P, prFondo, prTotal;
	Double ppT2P, ppT1E1S2P, ppT1P, ppT1S2P, ppT3S8P, ppTN4P, ppTN10P, ppTN40P, ppTN200P;
	Double pg, pa, D60, D30, D10, CU, CC;
	String LL, LP, IP, AASHTO, USC;
	
	@SuppressLint("SdCardPath")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_123_inf);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		mueSQLite = new SQLiteM123(this);

		mrT2P = mueSQLite.getM123T2P(ens_numero);
		mrT1E1S2P = mueSQLite.getM123T1E1S2P(ens_numero);
		mrT1P = mueSQLite.getM123T1P(ens_numero);
		mrT1S2P = mueSQLite.getM123T1S2P(ens_numero);
		mrT3S8P = mueSQLite.getM123T3S8P(ens_numero);
		mrTN4P = mueSQLite.getM123TN4P(ens_numero);
		mrTN10P = mueSQLite.getM123TN10P(ens_numero);
		mrTN40P = mueSQLite.getM123TN40P(ens_numero);
		mrTN200P = mueSQLite.getM123TN200P(ens_numero);
		mrFondo = mueSQLite.getM123Fondo(ens_numero);
		mrTotal = mueSQLite.getM123Total(ens_numero);
		D60 = mueSQLite.getM123D60(ens_numero);
		D30 = mueSQLite.getM123D30(ens_numero);
		D10 = mueSQLite.getM123D10(ens_numero);
		CU = M123.lab123CU(D60, D10);
		CC = M123.lab123CC(D60, D30, D10);
		LL = mueSQLite.getM123LL(ens_numero);
		LP = mueSQLite.getM123LP(ens_numero);
		IP = mueSQLite.getM123IP(ens_numero);
		AASHTO = mueSQLite.getM123AASHTO(ens_numero);
		USC = mueSQLite.getM123USC(ens_numero);
		
		prT2P = M123.lab123pR(mrT2P, mrTotal);
		prT1E1S2P = M123.lab123pR(mrT1E1S2P, mrTotal);
		prT1P = M123.lab123pR(mrT1P, mrTotal);
		prT1S2P = M123.lab123pR(mrT1S2P, mrTotal);
		prT3S8P = M123.lab123pR(mrT3S8P, mrTotal);
		prTN4P = M123.lab123pR(mrTN4P, mrTotal);
		prTN10P = M123.lab123pR(mrTN10P, mrTotal);
		prTN40P = M123.lab123pR(mrTN40P, mrTotal);
		prTN200P = M123.lab123pR(mrTN200P, mrTotal);
		prFondo = M123.lab123pR(mrFondo, mrTotal);
		prTotal = M123.lab123pR(mrTotal, mrTotal);
		
		ppT2P = M123.lab123pP(prT2P, 100, mrTotal);
		ppT1E1S2P = M123.lab123pP(prT1E1S2P, ppT2P, mrTotal);
		ppT1P = M123.lab123pP(prT1P, ppT1E1S2P, mrTotal);
		ppT1S2P = M123.lab123pP(prT1S2P, ppT1P, mrTotal);
		ppT3S8P = M123.lab123pP(prT3S8P, ppT1S2P, mrTotal);
		ppTN4P = M123.lab123pP(prTN4P, ppT3S8P, mrTotal);
		ppTN10P = M123.lab123pP(prTN10P, ppTN4P, mrTotal);
		ppTN40P = M123.lab123pP(prTN40P, ppTN10P, mrTotal);
		ppTN200P = M123.lab123pP(prTN200P, ppTN40P, mrTotal);
		
		pg = M123.lab123pG(ppTN4P);
		pa = M123.lab123pA(pg, ppTN200P);
		
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			UD_Lab = new File(
					android.os.Environment.getExternalStorageDirectory(),
					"UD-Lab");
			if (!UD_Lab.exists()) {
				UD_Lab.mkdirs();
			}
			cursorM123 = mueSQLite.getAllM123(ens_numero);
			
			Document document = new Document(PageSize.LETTER, 20, 20, 20, 20);
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
									+ "FACULTAD TECNOLÓGICA\n"
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
									"DETERMINACION DEL CONTENIDO DE HUMEDAD INV E-123 - 07"));
					cell1b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table1.addCell(cell1a);
					table1.addCell(cell1b);
					table1.addCell(cell1a);
				
				PdfPTable table2 = new PdfPTable(5);
					PdfPCell cell2e = new PdfPCell();
					cell2e = new PdfPCell(new Paragraph("Ensayo No.:"));
					cell2e.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2j = new PdfPCell();
					cell2j = new PdfPCell(new Paragraph(ens_numero));
					cell2j.setHorizontalAlignment(Element.ALIGN_CENTER);
					table2.addCell(cell1a);
					table2.addCell(cell1a);
					table2.addCell(cell1a);
					table2.addCell(cell1a);
					table2.addCell(cell2e);
					table2.addCell(cell1a);
					table2.addCell(cell1a);
					table2.addCell(cell1a);
					table2.addCell(cell1a);
					table2.addCell(cell2j);
					
				PdfPTable table3 = new PdfPTable(1);
					
					table3.addCell(cell1a);
					
				float[] widths4 = { 35f, 20f, 22f, 23f };
				PdfPTable table4 = new PdfPTable(4);
					table4.setTotalWidth(widths4);
					PdfPCell cell4c = new PdfPCell();
					cell4c = new PdfPCell(
							new Paragraph(
									"Fecha de ensayo:"));
					PdfPCell cell4d = new PdfPCell();
					cell4d = new PdfPCell(
							new Paragraph(
									Herr.cambiarAnho(mueSQLite.getM123Fecha(ens_numero))));
					cell4d.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4e = new PdfPCell();
					cell4e = new PdfPCell(
							new Paragraph(
									"Descripción del suelo:"));
					PdfPCell cell4f = new PdfPCell();
					cell4f = new PdfPCell(
							new Paragraph(
									mueSQLite.getM123DesSuelo(ens_numero)));
					cell4f.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4g = new PdfPCell();
					cell4g = new PdfPCell(
							new Paragraph(
									"Proyecto:"));
					PdfPCell cell4h = new PdfPCell();
					cell4h = new PdfPCell(
							new Paragraph(
									mueSQLite.getM123proN(ens_numero)));
					cell4h.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4i = new PdfPCell();
					cell4i = new PdfPCell(
							new Paragraph(
									"Localización del proyecto:"));
					PdfPCell cell4j = new PdfPCell();
					cell4j = new PdfPCell(
							new Paragraph(
									mueSQLite.getM123Localizacion(ens_numero)));
					cell4j.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4k = new PdfPCell();
					cell4k = new PdfPCell(
							new Paragraph(
									"Realizado por:"));
					PdfPCell cell4l = new PdfPCell();
					cell4l = new PdfPCell(
							new Paragraph(
									mueSQLite.getM123Usuario(ens_numero)));
					cell4l.setHorizontalAlignment(Element.ALIGN_CENTER);
					

					PdfPCell cell4m = new PdfPCell();
					cell4m = new PdfPCell(
							new Paragraph(
									"Perforación No.:"));
					PdfPCell cell4n = new PdfPCell();
					cell4n = new PdfPCell(
							new Paragraph(
									mueSQLite.getM123Perforacion(ens_numero)));
					cell4n.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4o = new PdfPCell();
					cell4o = new PdfPCell(
							new Paragraph(
									"Profundidad:"));
					PdfPCell cell4p = new PdfPCell();
					cell4p = new PdfPCell(
							new Paragraph(
									mueSQLite.getM123Profundidad(ens_numero) + " m"));
					cell4p.setHorizontalAlignment(Element.ALIGN_CENTER);
					
					
					
					table4.addCell(cell1a);
					table4.addCell(cell1a);
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
					table4.addCell(cell4n);
					table4.addCell(cell4o);
					table4.addCell(cell4p);
					table4.addCell(cell1a);
					
				PdfPTable table5 = new PdfPTable(1);
					table5.addCell(cell1a);

				PdfPTable table6 = new PdfPTable(1);
					PdfPCell cell6a = new PdfPCell();
					cell6a = new PdfPCell(
							new Paragraph(
									"ANÁLISIS GRANULOMÉTRICO"));
					cell6a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table6.addCell(cell6a);

				PdfPTable table7 = new PdfPTable(6);
					float[] widths7 = { 9f, 15f, 15f, 12f, 9f, 20f };
					table7.setTotalWidth(widths7);

					PdfPCell cell7a = new PdfPCell();
					cell7a = new PdfPCell(new Paragraph("Tamiz"));
					cell7a.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell7a.setVerticalAlignment(Element.ALIGN_CENTER);
					table7.addCell(cell7a);
					PdfPCell cell7b = new PdfPCell();
					cell7b = new PdfPCell(new Paragraph("Abertura mm"));
					cell7b.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell7a.setVerticalAlignment(Element.ALIGN_CENTER);
					table7.addCell(cell7b);
					PdfPCell cell7c = new PdfPCell();
					cell7c = new PdfPCell(new Paragraph("P. retenido g"));
					cell7c.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell7a.setVerticalAlignment(Element.ALIGN_CENTER);
					table7.addCell(cell7c);
					PdfPCell cell7d = new PdfPCell();
					cell7d= new PdfPCell(new Paragraph("% Retenido"));
					cell7d.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell7a.setVerticalAlignment(Element.ALIGN_CENTER);
					table7.addCell(cell7d);
					PdfPCell cell7e = new PdfPCell();
					cell7e = new PdfPCell(new Paragraph("% Pasa"));
					cell7e.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell7a.setVerticalAlignment(Element.ALIGN_CENTER);
					table7.addCell(cell7e);
					PdfPCell cell7f = new PdfPCell();
					cell7f = new PdfPCell(new Paragraph("INVIAS 2007-SBG 1"));
					cell7f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table7.addCell(cell7f);

				PdfPTable table8 = new PdfPTable(7);
					float[] widths8 = { 9f, 15f, 15f, 12f, 9f, 10f, 10f };
					table8.setTotalWidth(widths8);
					PdfPCell cell8a = new PdfPCell();
					cell8a = new PdfPCell(new Paragraph("2\""));
					cell8a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table8.addCell(cell8a);
					PdfPCell cell8b = new PdfPCell();
					cell8b = new PdfPCell(new Paragraph("50,8"));
					cell8b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table8.addCell(cell8b);
					PdfPCell cell8c = new PdfPCell();
					cell8c = new PdfPCell(new Paragraph(String.format("%.2f", mrT2P)));
					cell8c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table8.addCell(cell8c);
					PdfPCell cell8d = new PdfPCell();
					cell8d = new PdfPCell(new Paragraph(String.format("%.2f", prT2P)));
					cell8d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table8.addCell(cell8d);
					PdfPCell cell8e = new PdfPCell();
					cell8e = new PdfPCell(new Paragraph(String.format("%.2f", ppT2P)));
					cell8e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table8.addCell(cell8e);
					PdfPCell cell8f = new PdfPCell();
					cell8f = new PdfPCell(new Paragraph("100,0"));
					cell8f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table8.addCell(cell8f);
					PdfPCell cell8g = new PdfPCell();
					cell8g = new PdfPCell(new Paragraph("100,0"));
					cell8g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table8.addCell(cell8g);

				PdfPTable table9 = new PdfPTable(7);
					table9.setTotalWidth(widths8);
					PdfPCell cell9a = new PdfPCell();
					cell9a = new PdfPCell(new Paragraph("1½\""));
					cell9a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table9.addCell(cell9a);
					PdfPCell cell9b = new PdfPCell();
					cell9b = new PdfPCell(new Paragraph("38,1"));
					cell9b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table9.addCell(cell9b);
					PdfPCell cell9c = new PdfPCell();
					cell9c = new PdfPCell(new Paragraph(String.format("%.2f", mrT1E1S2P)));
					cell9c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table9.addCell(cell9c);
					PdfPCell cell9d = new PdfPCell();
					cell9d = new PdfPCell(new Paragraph(String.format("%.2f", prT1E1S2P)));
					cell9d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table9.addCell(cell9d);
					PdfPCell cell9e = new PdfPCell();
					cell9e = new PdfPCell(new Paragraph(String.format("%.2f", ppT1E1S2P)));
					cell9e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table9.addCell(cell9e);
					PdfPCell cell9f = new PdfPCell();
					cell9f = new PdfPCell(new Paragraph("70,0"));
					cell9f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table9.addCell(cell9f);
					PdfPCell cell9g = new PdfPCell();
					cell9g = new PdfPCell(new Paragraph("95,0"));
					cell9g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table9.addCell(cell9g);

				PdfPTable table10 = new PdfPTable(7);
					table10.setTotalWidth(widths8);
					PdfPCell cell10a = new PdfPCell();
					cell10a = new PdfPCell(new Paragraph("1\""));
					cell10a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table10.addCell(cell10a);
					PdfPCell cell10b = new PdfPCell();
					cell10b = new PdfPCell(new Paragraph("25,4"));
					cell10b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table10.addCell(cell10b);
					PdfPCell cell10c = new PdfPCell();
					cell10c = new PdfPCell(new Paragraph(String.format("%.2f", mrT1P)));
					cell10c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table10.addCell(cell10c);
					PdfPCell cell10d = new PdfPCell();
					cell10d = new PdfPCell(new Paragraph(String.format("%.2f", prT1P)));
					cell10d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table10.addCell(cell10d);
					PdfPCell cell10e = new PdfPCell();
					cell10e = new PdfPCell(new Paragraph(String.format("%.2f", ppT1P)));
					cell10e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table10.addCell(cell10e);
					PdfPCell cell10f = new PdfPCell();
					cell10f = new PdfPCell(new Paragraph("60,0"));
					cell10f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table10.addCell(cell10f);
					PdfPCell cell10g = new PdfPCell();
					cell10g = new PdfPCell(new Paragraph("90,0"));
					cell10g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table10.addCell(cell10g);

				PdfPTable table11 = new PdfPTable(7);
					table11.setTotalWidth(widths8);
					PdfPCell cell11a = new PdfPCell();
					cell11a = new PdfPCell(new Paragraph("1/2\""));
					cell11a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table11.addCell(cell11a);
					PdfPCell cell11b = new PdfPCell();
					cell11b = new PdfPCell(new Paragraph("12,7"));
					cell11b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table11.addCell(cell11b);
					PdfPCell cell11c = new PdfPCell();
					cell11c = new PdfPCell(new Paragraph(String.format("%.2f", mrT1S2P)));
					cell11c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table11.addCell(cell11c);
					PdfPCell cell11d = new PdfPCell();
					cell11d = new PdfPCell(new Paragraph(String.format("%.2f", prT1S2P)));
					cell11d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table11.addCell(cell11d);
					PdfPCell cell11e = new PdfPCell();
					cell11e = new PdfPCell(new Paragraph(String.format("%.2f", ppT1S2P)));
					cell11e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table11.addCell(cell11e);
					PdfPCell cell11f = new PdfPCell();
					cell11f = new PdfPCell(new Paragraph("45,0"));
					cell11f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table11.addCell(cell11f);
					PdfPCell cell11g = new PdfPCell();
					cell11g = new PdfPCell(new Paragraph("75,0"));
					cell11g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table11.addCell(cell11g);

				PdfPTable table12 = new PdfPTable(7);
					table12.setTotalWidth(widths8);
					PdfPCell cell12a = new PdfPCell();
					cell12a = new PdfPCell(new Paragraph("3/8\""));
					cell12a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table12.addCell(cell12a);
					PdfPCell cell12b = new PdfPCell();
					cell12b = new PdfPCell(new Paragraph("9,53"));
					cell12b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table12.addCell(cell12b);
					PdfPCell cell12c = new PdfPCell();
					cell12c = new PdfPCell(new Paragraph(String.format("%.2f", mrT3S8P)));
					cell12c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table12.addCell(cell12c);
					PdfPCell cell12d = new PdfPCell();
					cell12d = new PdfPCell(new Paragraph(String.format("%.2f", prT3S8P)));
					cell12d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table12.addCell(cell12d);
					PdfPCell cell12e = new PdfPCell();
					cell12e = new PdfPCell(new Paragraph(String.format("%.2f", ppT3S8P)));
					cell12e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table12.addCell(cell12e);
					PdfPCell cell12f = new PdfPCell();
					cell12f = new PdfPCell(new Paragraph("40,0"));
					cell12f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table12.addCell(cell12f);
					PdfPCell cell12g = new PdfPCell();
					cell12g = new PdfPCell(new Paragraph("70,0"));
					cell12g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table12.addCell(cell12g);

				PdfPTable table13 = new PdfPTable(7);
					table13.setTotalWidth(widths8);
					PdfPCell cell13a = new PdfPCell();
					cell13a = new PdfPCell(new Paragraph("No. 4"));
					cell13a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table13.addCell(cell13a);
					PdfPCell cell13b = new PdfPCell();
					cell13b = new PdfPCell(new Paragraph("4,75"));
					cell13b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table13.addCell(cell13b);
					PdfPCell cell13c = new PdfPCell();
					cell13c = new PdfPCell(new Paragraph(String.format("%.2f", mrTN4P)));
					cell13c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table13.addCell(cell13c);
					PdfPCell cell13d = new PdfPCell();
					cell13d = new PdfPCell(new Paragraph(String.format("%.2f", prTN4P)));
					cell13d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table13.addCell(cell13d);
					PdfPCell cell13e = new PdfPCell();
					cell13e = new PdfPCell(new Paragraph(String.format("%.2f", ppTN4P)));
					cell13e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table13.addCell(cell13e);
					PdfPCell cell13f = new PdfPCell();
					cell13f = new PdfPCell(new Paragraph("25,0"));
					cell13f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table13.addCell(cell13f);
					PdfPCell cell13g = new PdfPCell();
					cell13g = new PdfPCell(new Paragraph("55,0"));
					cell13g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table13.addCell(cell13g);

				PdfPTable table14 = new PdfPTable(7);
					table14.setTotalWidth(widths8);
					PdfPCell cell14a = new PdfPCell();
					cell14a = new PdfPCell(new Paragraph("No. 10"));
					cell14a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table14.addCell(cell14a);
					PdfPCell cell14b = new PdfPCell();
					cell14b = new PdfPCell(new Paragraph("2,00"));
					cell14b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table14.addCell(cell14b);
					PdfPCell cell14c = new PdfPCell();
					cell14c = new PdfPCell(new Paragraph(String.format("%.2f", mrTN10P)));
					cell14c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table14.addCell(cell14c);
					PdfPCell cell14d = new PdfPCell();
					cell14d = new PdfPCell(new Paragraph(String.format("%.2f", prTN10P)));
					cell14d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table14.addCell(cell14d);
					PdfPCell cell14e = new PdfPCell();
					cell14e = new PdfPCell(new Paragraph(String.format("%.2f", ppTN10P)));
					cell14e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table14.addCell(cell14e);
					PdfPCell cell14f = new PdfPCell();
					cell14f = new PdfPCell(new Paragraph("15,0"));
					cell14f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table14.addCell(cell14f);
					PdfPCell cell14g = new PdfPCell();
					cell14g = new PdfPCell(new Paragraph("40,0"));
					cell14g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table14.addCell(cell14g);

				PdfPTable table15 = new PdfPTable(7);
					table15.setTotalWidth(widths8);
					PdfPCell cell15a = new PdfPCell();
					cell15a = new PdfPCell(new Paragraph("No. 40"));
					cell15a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table15.addCell(cell15a);
					PdfPCell cell15b = new PdfPCell();
					cell15b = new PdfPCell(new Paragraph("0,425"));
					cell15b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table15.addCell(cell15b);
					PdfPCell cell15c = new PdfPCell();
					cell15c = new PdfPCell(new Paragraph(String.format("%.2f", mrTN40P)));
					cell15c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table15.addCell(cell15c);
					PdfPCell cell15d = new PdfPCell();
					cell15d = new PdfPCell(new Paragraph(String.format("%.2f", prTN40P)));
					cell15d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table15.addCell(cell15d);
					PdfPCell cell15e = new PdfPCell();
					cell15e = new PdfPCell(new Paragraph(String.format("%.2f", ppTN40P)));
					cell15e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table15.addCell(cell15e);
					PdfPCell cell15f = new PdfPCell();
					cell15f = new PdfPCell(new Paragraph("6,0"));
					cell15f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table15.addCell(cell15f);
					PdfPCell cell15g = new PdfPCell();
					cell15g = new PdfPCell(new Paragraph("25,0"));
					cell15g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table15.addCell(cell15g);

				PdfPTable table16 = new PdfPTable(7);
					table16.setTotalWidth(widths8);
					PdfPCell cell16a = new PdfPCell();
					cell16a = new PdfPCell(new Paragraph("No. 200"));
					cell16a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table16.addCell(cell16a);
					PdfPCell cell16b = new PdfPCell();
					cell16b = new PdfPCell(new Paragraph("0,075"));
					cell16b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table16.addCell(cell16b);
					PdfPCell cell16c = new PdfPCell();
					cell16c = new PdfPCell(new Paragraph(String.format("%.2f", mrTN200P)));
					cell16c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table16.addCell(cell16c);
					PdfPCell cell16d = new PdfPCell();
					cell16d = new PdfPCell(new Paragraph(String.format("%.2f", prTN200P)));
					cell16d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table16.addCell(cell16d);
					PdfPCell cell16e = new PdfPCell();
					cell16e = new PdfPCell(new Paragraph(String.format("%.2f", ppTN200P)));
					cell16e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table16.addCell(cell16e);
					PdfPCell cell16f = new PdfPCell();
					cell16f = new PdfPCell(new Paragraph("2,0"));
					cell16f.setHorizontalAlignment(Element.ALIGN_CENTER);
					table16.addCell(cell16f);
					PdfPCell cell16g = new PdfPCell();
					cell16g = new PdfPCell(new Paragraph("15,0"));
					cell16g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table16.addCell(cell16g);

				PdfPTable table17 = new PdfPTable(7);
					table17.setTotalWidth(widths8);
					PdfPCell cell17a = new PdfPCell();
					cell17a = new PdfPCell(new Paragraph("Fondo"));
					cell17a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table17.addCell(cell17a);
					PdfPCell cell17b = new PdfPCell();
					cell17b = new PdfPCell();
					cell17b.setNoWrap(false);
					table17.addCell(cell17b);
					PdfPCell cell17c = new PdfPCell();
					cell17c = new PdfPCell(new Paragraph(String.format("%.2f", mrFondo)));
					cell17c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table17.addCell(cell17c);
					PdfPCell cell17d = new PdfPCell();
					cell17d = new PdfPCell(new Paragraph(String.format("%.2f", prFondo)));
					cell17d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table17.addCell(cell17d);
					PdfPCell cell17e = new PdfPCell();
					cell17e = new PdfPCell();
					cell17e.setNoWrap(false);
					table17.addCell(cell17e);
					PdfPCell cell17f = new PdfPCell();
					cell17f = new PdfPCell();
					cell17f.setNoWrap(false);
					table17.addCell(cell17f);
					PdfPCell cell17g = new PdfPCell();
					cell17g = new PdfPCell();
					cell17g.setNoWrap(false);
					table17.addCell(cell17g);

				PdfPTable table18 = new PdfPTable(7);
					table18.setTotalWidth(widths8);
					PdfPCell cell18a = new PdfPCell();
					cell18a = new PdfPCell(new Paragraph("Total"));
					cell18a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table18.addCell(cell18a);
					PdfPCell cell18b = new PdfPCell();
					cell18b = new PdfPCell();
					cell18b.setNoWrap(false);
					table18.addCell(cell18b);
					PdfPCell cell18c = new PdfPCell();
					cell18c = new PdfPCell(new Paragraph(String.format("%.2f", mrTotal)));
					cell18c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table18.addCell(cell18c);
					PdfPCell cell18d = new PdfPCell();
					cell18d = new PdfPCell(new Paragraph(String.format("%.2f", prTotal)));
					cell18d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table18.addCell(cell18d);
					PdfPCell cell18e = new PdfPCell();
					cell18e = new PdfPCell();
					cell18e.setNoWrap(false);
					table18.addCell(cell18e);
					PdfPCell cell18f = new PdfPCell();
					cell18f = new PdfPCell();
					cell18f.setNoWrap(false);
					table18.addCell(cell18f);
					PdfPCell cell18g = new PdfPCell();
					cell18g = new PdfPCell();
					cell18g.setNoWrap(false);
					table18.addCell(cell18g);
						
				PdfPTable table19 = new PdfPTable(1);
					PdfPCell cell19a = new PdfPCell();
					cell19a.setNoWrap(false);
					table19.addCell(cell19a);

				PdfPTable table20 = new PdfPTable(9);
					float[] widths9 = { 15f, 9f, 1f, 11f, 9f, 1f, 26f, 1f, 26f };
					table20.setTotalWidth(widths9);
					PdfPCell cell20a = new PdfPCell();
					cell20a = new PdfPCell(new Paragraph("% Gravas:"));
					cell20a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table20.addCell(cell20a);
					PdfPCell cell20b = new PdfPCell();
					cell20b = new PdfPCell(new Paragraph(String.format("%.2f", pg)));
					cell20b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table20.addCell(cell20b);
					table20.addCell(cell1a);
					PdfPCell cell20d = new PdfPCell();
					cell20d = new PdfPCell(new Paragraph("D60"));
					cell20d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table20.addCell(cell20d);
					PdfPCell cell20e = new PdfPCell();
					cell20e = new PdfPCell(new Paragraph(String.format("%.2f", D60)));
					cell20e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table20.addCell(cell20e);
					table20.addCell(cell1a);
					PdfPCell cell20g = new PdfPCell();
					cell20g = new PdfPCell(new Paragraph("Resultados límite de"));
					cell20g.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell20g.setBorder(0);
					table20.addCell(cell20g);
					table20.addCell(cell1a);
					PdfPCell cell20i = new PdfPCell();
					cell20i = new PdfPCell(new Paragraph("Clasificación"));
					cell20i.setHorizontalAlignment(Element.ALIGN_CENTER);
					table20.addCell(cell20i);

				PdfPTable table21 = new PdfPTable(10);
					float[] widths10 = { 15f, 9f, 1f, 11f, 9f, 1f, 26f, 1f, 17f, 9f };
					table21.setTotalWidth(widths10);
					PdfPCell cell21a = new PdfPCell();
					cell21a = new PdfPCell(new Paragraph("% Arenas:"));
					cell21a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table21.addCell(cell21a);
					PdfPCell cell21b = new PdfPCell();
					cell21b = new PdfPCell(new Paragraph(String.format("%.2f", pa)));
					cell21b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table21.addCell(cell21b);
					table21.addCell(cell1a);
					PdfPCell cell21d = new PdfPCell();
					cell21d = new PdfPCell(new Paragraph("D30"));
					cell21d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table21.addCell(cell21d);
					PdfPCell cell21e = new PdfPCell();
					cell21e = new PdfPCell(new Paragraph(String.format("%.2f", D30)));
					cell21e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table21.addCell(cell21e);
					table21.addCell(cell1a);
					PdfPCell cell21g = new PdfPCell();
					cell21g = new PdfPCell(new Paragraph("consistencia"));
					cell21g.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell21g.setBorder(0);
					table21.addCell(cell21g);
					table21.addCell(cell1a);
					PdfPCell cell21i = new PdfPCell();
					cell21i = new PdfPCell(new Paragraph("A.A.S.H.T.O.:"));
					cell21i.setHorizontalAlignment(Element.ALIGN_CENTER);
					table21.addCell(cell21i);
					PdfPCell cell21j = new PdfPCell();
					cell21j = new PdfPCell(new Paragraph(AASHTO));
					cell21j.setHorizontalAlignment(Element.ALIGN_CENTER);
					table21.addCell(cell21j);

				PdfPTable table22 = new PdfPTable(11);
					float[] widths11 = { 15f, 9f, 1f, 11f, 9f, 1f, 15f, 11f, 1f, 17f, 9f };
					table22.setTotalWidth(widths11);
					PdfPCell cell22a = new PdfPCell();
					cell22a = new PdfPCell(new Paragraph("% Finos:"));
					cell22a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell22a);
					PdfPCell cell22b = new PdfPCell();
					cell22b = new PdfPCell(new Paragraph(String.format("%.2f", prFondo)));
					cell22b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell22b);
					table22.addCell(cell1a);
					PdfPCell cell22d = new PdfPCell();
					cell22d = new PdfPCell(new Paragraph("D10"));
					cell22d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell22d);
					PdfPCell cell22e = new PdfPCell();
					cell22e = new PdfPCell(new Paragraph(String.format("%.2f", D10)));
					cell22e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell22e);
					table22.addCell(cell1a);
					PdfPCell cell22g = new PdfPCell();
					cell22g = new PdfPCell(new Paragraph("% L. L.:"));
					cell22g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell22g);
					PdfPCell cell22h = new PdfPCell();
					cell22h = new PdfPCell(new Paragraph(LL));
					cell22h.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell22h);
					table22.addCell(cell1a);
					PdfPCell cell22j = new PdfPCell();
					cell22j = new PdfPCell(new Paragraph("U.S.C.: "));
					cell22j.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell22j);
					PdfPCell cell22k = new PdfPCell();
					cell22k = new PdfPCell(new Paragraph(USC));
					cell22k.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell22k);

				PdfPTable table23 = new PdfPTable(11);
					table23.setTotalWidth(widths11);
					table23.addCell(cell1a);
					table23.addCell(cell1a);
					table23.addCell(cell1a);
					PdfPCell cell23d = new PdfPCell();
					cell23d = new PdfPCell(new Paragraph("CU"));
					cell23d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table23.addCell(cell23d);
					PdfPCell cell23e = new PdfPCell();
					cell23e = new PdfPCell(new Paragraph(String.format("%.2f", CU)));
					cell23e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table23.addCell(cell23e);
					table23.addCell(cell1a);
					PdfPCell cell23g = new PdfPCell();
					cell23g = new PdfPCell(new Paragraph("% L. P.:"));
					cell23g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table23.addCell(cell23g);
					PdfPCell cell23h = new PdfPCell();
					cell23h = new PdfPCell(new Paragraph(LP));
					cell23h.setHorizontalAlignment(Element.ALIGN_CENTER);
					table23.addCell(cell23h);
					table23.addCell(cell1a);
					table23.addCell(cell1a);
					table23.addCell(cell1a);

				PdfPTable table24 = new PdfPTable(11);
					table24.setTotalWidth(widths11);
					table24.addCell(cell1a);
					table24.addCell(cell1a);
					table24.addCell(cell1a);
					PdfPCell cell24d = new PdfPCell();
					cell24d = new PdfPCell(new Paragraph("CC"));
					cell24d.setHorizontalAlignment(Element.ALIGN_CENTER);
					table24.addCell(cell24d);
					PdfPCell cell24e = new PdfPCell();
					cell24e = new PdfPCell(new Paragraph(String.format("%.2f", CC)));
					cell24e.setHorizontalAlignment(Element.ALIGN_CENTER);
					table24.addCell(cell24e);
					table24.addCell(cell1a);
					PdfPCell cell24g = new PdfPCell();
					cell24g = new PdfPCell(new Paragraph("% I. de P.:"));
					cell24g.setHorizontalAlignment(Element.ALIGN_CENTER);
					table24.addCell(cell24g);
					PdfPCell cell24h = new PdfPCell();
					cell24h = new PdfPCell(new Paragraph(IP));
					cell24h.setHorizontalAlignment(Element.ALIGN_CENTER);
					table24.addCell(cell24h);
					table24.addCell(cell1a);
					table24.addCell(cell1a);
					table24.addCell(cell1a);

				document.add(table0);
				document.add(table1);
				document.add(table2);
				document.add(table3);
				document.add(table4);
				document.add(table5);
				document.add(table6);
				document.add(table7);
				document.add(table8);
				document.add(table9);
				document.add(table10);
				document.add(table11);
				document.add(table12);
				document.add(table13);
				document.add(table14);
				document.add(table15);
				document.add(table16);
				document.add(table17);
				document.add(table18);
				document.add(table19);
				document.add(table20);
				document.add(table21);
				document.add(table22);
				document.add(table23);
				document.add(table24);
				
				PdfContentByte cb = writer.getDirectContent();
				cb.setLineWidth(1);
				cb.setCMYKColorStroke(0, 0, 0, 255);

                cb.moveTo(102, 256);
                cb.lineTo(524, 256);
                cb.moveTo(102, 238);
                cb.lineTo(524, 238);
                cb.moveTo(102, 220);
                cb.lineTo(524, 220);
                cb.moveTo(102, 202);
                cb.lineTo(524, 202);
                cb.moveTo(102, 184);
                cb.lineTo(524, 184);
                cb.moveTo(102, 166);
                cb.lineTo(524, 166);
                cb.moveTo(102, 148);
                cb.lineTo(524, 148);
                cb.moveTo(102, 130);
                cb.lineTo(524, 130);
                cb.moveTo(102, 112);
                cb.lineTo(524, 112);
                cb.moveTo(102, 94);
                cb.lineTo(524, 94);
                cb.moveTo(102, 76);
                cb.lineTo(524, 76);

                cb.moveTo(524, 76);
                cb.lineTo(524, 258);
                cb.moveTo(492, 76);
                cb.lineTo(492, 258);
                cb.moveTo(474, 76);
                cb.lineTo(474, 258);
                cb.moveTo(460, 76);
                cb.lineTo(460, 258);
                cb.moveTo(451, 76);
                cb.lineTo(451, 258);
                cb.moveTo(442, 76);
                cb.lineTo(442, 258);
                cb.moveTo(435, 76);
                cb.lineTo(435, 258);
                cb.moveTo(429, 76);
                cb.lineTo(429, 258);
                cb.moveTo(424, 76);
                cb.lineTo(424, 258);
                cb.moveTo(419, 76);
                cb.lineTo(419, 258);

                cb.moveTo(387, 76);
                cb.lineTo(387, 258);
                cb.moveTo(369, 76);
                cb.lineTo(369, 258);
                cb.moveTo(355, 76);
                cb.lineTo(355, 258);
                cb.moveTo(346, 76);
                cb.lineTo(346, 258);
                cb.moveTo(337, 76);
                cb.lineTo(337, 258);
                cb.moveTo(330, 76);
                cb.lineTo(330, 258);
                cb.moveTo(324, 76);
                cb.lineTo(324, 258);
                cb.moveTo(319, 76);
                cb.lineTo(319, 258);
                cb.moveTo(314, 76);
                cb.lineTo(314, 258);

                cb.moveTo(282, 76);
                cb.lineTo(282, 258);
                cb.moveTo(264, 76);
                cb.lineTo(264, 258);
                cb.moveTo(250, 76);
                cb.lineTo(250, 258);
                cb.moveTo(241, 76);
                cb.lineTo(241, 258);
                cb.moveTo(232, 76);
                cb.lineTo(232, 258);
                cb.moveTo(225, 76);
                cb.lineTo(225, 258);
                cb.moveTo(219, 76);
                cb.lineTo(219, 258);
                cb.moveTo(214, 76);
                cb.lineTo(214, 258);
                cb.moveTo(209, 76);
                cb.lineTo(209, 258);

                cb.moveTo(177, 76);
                cb.lineTo(177, 258);
                cb.moveTo(159, 76);
                cb.lineTo(159, 258);
                cb.moveTo(145, 76);
                cb.lineTo(145, 258);
                cb.moveTo(136, 76);
                cb.lineTo(136, 258);
                cb.moveTo(127, 76);
                cb.lineTo(127, 258);
                cb.moveTo(120, 76);
                cb.lineTo(120, 258);
                cb.moveTo(114, 76);
                cb.lineTo(114, 258);
                cb.moveTo(109, 76);
                cb.lineTo(109, 258);
                cb.moveTo(104, 76);
                cb.lineTo(104, 258);
                cb.stroke();
                
                BaseFont bf = BaseFont.createFont();
                cb.beginText();
                cb.setFontAndSize(bf, 8);
                cb.showTextAligned(1, "100,00", 104, 264, 0);
                cb.showTextAligned(1, "10,00", 209, 264, 0);
                cb.showTextAligned(1, "1,00", 314, 264, 0);
                cb.showTextAligned(1, "0,10", 419, 264, 0);
                cb.showTextAligned(1, "0,01", 524, 264, 0);
                cb.showTextAligned(1, "Dimensión abertura del tamiz", 314, 274, 0);

                cb.showTextAligned(1, "100", 93, 256, 0);
                cb.showTextAligned(1, "90", 93, 238, 0);
                cb.showTextAligned(1, "80", 93, 220, 0);
                cb.showTextAligned(1, "70", 93, 202, 0);
                cb.showTextAligned(1, "60", 93, 184, 0);
                cb.showTextAligned(1, "50", 93, 166, 0);
                cb.showTextAligned(1, "40", 93, 148, 0);
                cb.showTextAligned(1, "30", 93, 130, 0);
                cb.showTextAligned(1, "20", 93, 112, 0);
                cb.showTextAligned(1, "10", 93, 94, 0);
                cb.showTextAligned(1, "0", 93, 76, 0);
                cb.showTextAligned(1, "% Pasa", 82, 166, 90);
                
                cb.endText();
                cb.stroke();
                
                PdfContentByte cc = writer.getDirectContent();
                cc.setLineWidth(3);
				cc.setCMYKColorStroke(0, 255, 255, 0);

                cc.moveTo(M123.lab123E(0.075), M123.lab123F(ppTN200P));
                cc.lineTo(M123.lab123E(0.425), M123.lab123F(ppTN40P));
                cc.lineTo(M123.lab123E(2.0), M123.lab123F(ppTN10P));
                cc.lineTo(M123.lab123E(4.75), M123.lab123F(ppTN4P));
                cc.lineTo(M123.lab123E(9.525), M123.lab123F(ppT3S8P));
                cc.lineTo(M123.lab123E(12.7), M123.lab123F(ppT1S2P));
                cc.lineTo(M123.lab123E(25.4), M123.lab123F(ppT1P));
                cc.lineTo(M123.lab123E(38.1), M123.lab123F(ppT1E1S2P));
                cc.lineTo(M123.lab123E(50.8), M123.lab123F(ppT2P));
                
                cc.stroke();
                
                PdfContentByte cd = writer.getDirectContent();
                cd.setLineWidth(3);
                cd.setCMYKColorStroke(153, 71, 255, 13);

                cd.moveTo(432, 80);
                cd.lineTo(353, 87);
                cd.lineTo(282, 103);
                cd.lineTo(243, 121);
                cd.lineTo(211, 148);
                cd.lineTo(198, 157);
                cd.lineTo(166, 184);
                cd.lineTo(148, 202);
                cd.lineTo(135, 256);
                
                cd.stroke();
                
                cd.moveTo(432, 103);
                cd.lineTo(353, 121);
                cd.lineTo(282, 148);
                cd.lineTo(243, 175);
                cd.lineTo(211, 202);
                cd.lineTo(198, 211);
                cd.lineTo(166, 238);
                cd.lineTo(148, 247);
                cd.lineTo(135, 256);
                
                cd.stroke();
                
                PdfContentByte ce = writer.getDirectContent();
                ce.setCMYKColorStroke(0, 0, 0, 255);
                ce.setCMYKColorFill(0, 153, 255, 0);
                ce.circle(M123.lab123E(D60), M123.lab123F(60.0), 3);
                ce.fill();
                
                ce.stroke();
                
                PdfContentByte cf = writer.getDirectContent();
                cf.setCMYKColorStroke(0, 0, 0, 255);
                cf.setCMYKColorFill(255, 255, 0, 0);
                cf.circle(M123.lab123E(D30), M123.lab123F(30.0), 3);
                cf.fill();
                
                cf.stroke();
                
                PdfContentByte cg = writer.getDirectContent();
                cg.setCMYKColorStroke(0, 0, 0, 255);
                cg.setCMYKColorFill(51, 204, 0, 51);
                cg.circle(M123.lab123E(D10), M123.lab123F(10.0), 3);
                cg.fill();
                
                cg.stroke();
                
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