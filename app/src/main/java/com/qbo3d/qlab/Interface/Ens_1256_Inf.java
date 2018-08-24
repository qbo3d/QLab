package com.qbo3d.qlab.Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbo3d.qlab.Logica.Herr;
import com.qbo3d.qlab.Logica.M1256;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_LL;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_LP;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class Ens_1256_Inf extends Activity {

	private File UD_Lab;

	String pro_nombre;
	String ens_numero;

	String m1256_str = "";
	int m1256_id;
	
	List<String> listLLrn = new ArrayList<String>();
	List<String> listLLpshr = new ArrayList<String>();
	List<String> listLLpssr = new ArrayList<String>();
	List<String> listLLpa = new ArrayList<String>();
	List<String> listLLpr = new ArrayList<String>();
	List<String> listLLpss = new ArrayList<String>();
	List<String> listLLch = new ArrayList<String>();
	List<String> listLLng = new ArrayList<String>();
	Double LL;

	List<String> listLPrn = new ArrayList<String>();
	List<String> listLPpshr = new ArrayList<String>();
	List<String> listLPpssr = new ArrayList<String>();
	List<String> listLPpa = new ArrayList<String>();
	List<String> listLPpr = new ArrayList<String>();
	List<String> listLPpss = new ArrayList<String>();
	List<String> listLPch = new ArrayList<String>();
	Double LP;
	
	String HNrn;
	Double HNpshr;
	Double HNpssr;
	Double HNpa;
	Double HNpr;
	Double HNpss;
	Double HNch;
	
	Double IP;
	Double PP200;
	String AASHTO;
	Double IG;
	String USC;
	Double IL;
	Double CR;
	
	int longC;
	SQLiteM1256_LL mllSQLite;
	SQLiteM1256_LP mlpSQLite;
	Button bt_ens_1256_consultar;
	Bundle extra;
	Intent data;
//	Cursor cursorM1256;
	private static final int REQUEST_CODE = 10;

	@SuppressLint("SdCardPath")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_1256_inf);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		mllSQLite = new SQLiteM1256_LL(this);
		mlpSQLite = new SQLiteM1256_LP(this);
		
		callCalcular();

		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			UD_Lab = new File(
					android.os.Environment.getExternalStorageDirectory(),
					"UD-Lab");
			if (!UD_Lab.exists()) {
				UD_Lab.mkdirs();
			}
//			cursorM1256 = mllSQLite.getAllM1256(ens_numero);
			
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
									"DETERMINACION DEL CONTENIDO DE HUMEDAD INV E-1256 - 07", FontFactory.getFont("arial", 10)));
					cell1b.setHorizontalAlignment(Element.ALIGN_CENTER);
					table1.addCell(cell1a);
					table1.addCell(cell1b);
					table1.addCell(cell1a);
				
				PdfPTable table2 = new PdfPTable(5);
					PdfPCell cell2e = new PdfPCell();
					cell2e = new PdfPCell(new Paragraph("Ensayo No.:", FontFactory.getFont("arial", 10)));
					cell2e.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell2j = new PdfPCell();
					cell2j = new PdfPCell(new Paragraph(ens_numero, FontFactory.getFont("arial", 10)));
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
					table2.addCell(cell1a);
					
				PdfPTable table3 = new PdfPTable(1);
					
					table3.addCell(cell1a);
					
				float[] widths4 = { 35f, 20f, 22f, 23f };
				PdfPTable table4 = new PdfPTable(4);
					table4.setTotalWidth(widths4);
					PdfPCell cell4c = new PdfPCell();
					cell4c = new PdfPCell(
							new Paragraph(
									"Fecha de ensayo:", FontFactory.getFont("arial", 10)));
					PdfPCell cell4d = new PdfPCell();
					cell4d = new PdfPCell(
							new Paragraph(
									Herr.cambiarAnho(mllSQLite.getM1256_Fecha(ens_numero)), FontFactory.getFont("arial", 10)));
					cell4d.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4e = new PdfPCell();
					cell4e = new PdfPCell(
							new Paragraph(
									"Descripción del suelo:", FontFactory.getFont("arial", 10)));
					PdfPCell cell4f = new PdfPCell();
					cell4f = new PdfPCell(
							new Paragraph(
									mllSQLite.getM1256_DesSuelo(ens_numero), FontFactory.getFont("arial", 10)));
					cell4f.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4g = new PdfPCell();
					cell4g = new PdfPCell(
							new Paragraph(
									"Proyecto:", FontFactory.getFont("arial", 10)));
					PdfPCell cell4h = new PdfPCell();
					cell4h = new PdfPCell(
							new Paragraph(
									mllSQLite.getM1256_proN(ens_numero), FontFactory.getFont("arial", 10)));
					cell4h.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4i = new PdfPCell();
					cell4i = new PdfPCell(
							new Paragraph(
									"Localización del proyecto:", FontFactory.getFont("arial", 10)));
					PdfPCell cell4j = new PdfPCell();
					cell4j = new PdfPCell(
							new Paragraph(
									mllSQLite.getM1256_Localizacion(ens_numero), FontFactory.getFont("arial", 10)));
					cell4j.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4k = new PdfPCell();
					cell4k = new PdfPCell(
							new Paragraph(
									"Realizado por:", FontFactory.getFont("arial", 10)));
					PdfPCell cell4l = new PdfPCell();
					cell4l = new PdfPCell(
							new Paragraph(
									mllSQLite.getM1256_Usuario(ens_numero), FontFactory.getFont("arial", 10)));
					cell4l.setHorizontalAlignment(Element.ALIGN_CENTER);
					

					PdfPCell cell4m = new PdfPCell();
					cell4m = new PdfPCell(
							new Paragraph(
									"Perforación No.:", FontFactory.getFont("arial", 10)));
					PdfPCell cell4n = new PdfPCell();
					cell4n = new PdfPCell(
							new Paragraph(
									mllSQLite.getM1256_Perforacion(ens_numero), FontFactory.getFont("arial", 10)));
					cell4n.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4o = new PdfPCell();
					cell4o = new PdfPCell(
							new Paragraph(
									"Profundidad:", FontFactory.getFont("arial", 10)));
					PdfPCell cell4p = new PdfPCell();
					cell4p = new PdfPCell(
							new Paragraph(
									mllSQLite.getM1256_Profundidad(ens_numero) + " m", FontFactory.getFont("arial", 10)));
					cell4p.setHorizontalAlignment(Element.ALIGN_CENTER);
					
					//???????????????????????????????????????????
					
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
					
				PdfPTable table6 = new PdfPTable(3);
					float[] widths6 = { 60f, 1f, 40f };
					table6.setTotalWidth(widths6);
					
					PdfPCell cell6a = new PdfPCell();
					cell6a = new PdfPCell(
							new Paragraph(
									"LÍMITE LÍQUIDO", FontFactory.getFont("arial", 10)));
					cell6a.setHorizontalAlignment(Element.ALIGN_CENTER);
					table6.addCell(cell6a);
					table6.addCell(cell1a);
					PdfPCell cell6c = new PdfPCell();
					cell6c = new PdfPCell(
							new Paragraph(
									"HUMEDAD NATURAL", FontFactory.getFont("arial", 10)));
					cell6c.setHorizontalAlignment(Element.ALIGN_CENTER);
					table6.addCell(cell6c);

					PdfPTable table7 = new PdfPTable(7);
						float[] widths7 = { 30f, 10f, 10f, 10f, 1f, 30f, 10f };
						table7.setTotalWidth(widths7);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell7a1 = new PdfPCell();
						cell7a1 = new PdfPCell(
								new Paragraph(
										"Recipiente número", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7a1);
						PdfPCell cell7b1 = new PdfPCell();
						if(listLLpshr.size() > 0){
							cell7b1 = new PdfPCell(
									new Paragraph(
											listLLrn.get(0), FontFactory.getFont("arial", 10)));
						} else {
							cell7b1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7b1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7b1);
						PdfPCell cell7c1 = new PdfPCell();
						if(listLLpshr.size() > 1){
							cell7c1 = new PdfPCell(
									new Paragraph(
											listLLrn.get(1), FontFactory.getFont("arial", 10)));
						} else {
							cell7c1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7c1);
						PdfPCell cell7d1 = new PdfPCell();
						if(listLLpshr.size() > 2){
							cell7d1 = new PdfPCell(
									new Paragraph(
											listLLrn.get(2), FontFactory.getFont("arial", 10)));
						} else {
							cell7d1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7d1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7d1);
						table7.addCell(cell1a);
						PdfPCell cell7f1 = new PdfPCell();
						cell7f1 = new PdfPCell(
								new Paragraph(
										"Recipiente número", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7f1);
						PdfPCell cell7g1 = new PdfPCell();
						cell7g1 = new PdfPCell(
								new Paragraph(
										HNrn, FontFactory.getFont("arial", 10)));
						cell7g1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7g1);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell7a2 = new PdfPCell();
						cell7a2 = new PdfPCell(
								new Paragraph(
										"Peso suelo hum. + recip. (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7a2);
						PdfPCell cell7b2 = new PdfPCell();
						if(listLLpshr.size() > 0){
							cell7b2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpshr.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell7b2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7b2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7b2);
						PdfPCell cell7c2 = new PdfPCell();
						if(listLLpshr.size() > 1){
							cell7c2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpshr.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell7c2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7c2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7c2);
						PdfPCell cell7d2 = new PdfPCell();
						if(listLLpshr.size() > 2){
							cell7d2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpshr.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell7d2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7d2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7d2);
						table7.addCell(cell1a);
						PdfPCell cell7f2 = new PdfPCell();
						cell7f2 = new PdfPCell(
								new Paragraph(
										"Peso suelo hum. + recip. (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7f2);
						PdfPCell cell7g2 = new PdfPCell();
						cell7g2 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", HNpshr), FontFactory.getFont("arial", 10)));
						cell7g2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7g2);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell7a3 = new PdfPCell();
						cell7a3 = new PdfPCell(
								new Paragraph(
										"Peso suelo seco. + recip. (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7a3);
						PdfPCell cell7b3 = new PdfPCell();
						if(listLLpshr.size() > 0){
							cell7b3 = new PdfPCell(
									new Paragraph(
										String.format("%.2f", Double.parseDouble(listLLpssr.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell7b3 = new PdfPCell(
									new Paragraph(
										""));
						}
						cell7b3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7b3);
						PdfPCell cell7c3 = new PdfPCell();
						if(listLLpshr.size() > 1){
							cell7c3 = new PdfPCell(
									new Paragraph(
										String.format("%.2f", Double.parseDouble(listLLpssr.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell7c3 = new PdfPCell(
									new Paragraph(
										""));
						}
						cell7c3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7c3);
						PdfPCell cell7d3 = new PdfPCell();
						if(listLLpshr.size() > 2){
							cell7d3 = new PdfPCell(
									new Paragraph(
										String.format("%.2f", Double.parseDouble(listLLpssr.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell7d3 = new PdfPCell(
									new Paragraph(
										""));
						}
						cell7d3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7d3);
						table7.addCell(cell1a);
						PdfPCell cell7f3 = new PdfPCell();
						cell7f3 = new PdfPCell(
								new Paragraph(
										"Peso suelo seco. + recip. (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7f3);
						PdfPCell cell7g3 = new PdfPCell();
						cell7g3 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", HNpssr), FontFactory.getFont("arial", 10)));
						cell7g3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7g3);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell7a4 = new PdfPCell();
						cell7a4 = new PdfPCell(
								new Paragraph(
										"Peso agua (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7a4);
						PdfPCell cell7b4 = new PdfPCell();
						if(listLLpshr.size() > 0){
							cell7b4 = new PdfPCell(
									new Paragraph(
										String.format("%.2f", Double.parseDouble(listLLpa.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell7b4 = new PdfPCell(
									new Paragraph(
										""));
						}
						cell7b4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7b4);
						PdfPCell cell7c4 = new PdfPCell();
						if(listLLpshr.size() > 1){
							cell7c4 = new PdfPCell(
									new Paragraph(
										String.format("%.2f", Double.parseDouble(listLLpa.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell7c4 = new PdfPCell(
									new Paragraph(
										""));
						}
						cell7c4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7c4);
						PdfPCell cell7d4 = new PdfPCell();
						if(listLLpshr.size() > 2){
							cell7d4 = new PdfPCell(
									new Paragraph(
										String.format("%.2f", Double.parseDouble(listLLpa.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell7d4 = new PdfPCell(
									new Paragraph(
										""));
						}
						cell7d4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7d4);
						table7.addCell(cell1a);
						PdfPCell cell7f4 = new PdfPCell();
						cell7f4 = new PdfPCell(
								new Paragraph(
										"Peso agua (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7f4);
						PdfPCell cell7g4 = new PdfPCell();
						cell7g4 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", HNpa), FontFactory.getFont("arial", 10)));
						cell7g4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7g4);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell7a5 = new PdfPCell();
						cell7a5 = new PdfPCell(
								new Paragraph(
										"Peso recipiente (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7a5);
						PdfPCell cell7b5 = new PdfPCell();
						if(listLLpshr.size() > 0){
							cell7b5 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpr.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell7b5 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7b5.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7b5);
						PdfPCell cell7c5 = new PdfPCell();
						if(listLLpshr.size() > 1){
							cell7c5 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpr.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell7c5 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7c5.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7c5);
						PdfPCell cell7d5 = new PdfPCell();
						if(listLLpshr.size() > 2){
							cell7d5 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpr.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell7d5 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7d5.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7d5);
						table7.addCell(cell1a);
						PdfPCell cell7f5 = new PdfPCell();
						cell7f5 = new PdfPCell(
								new Paragraph(
										"Peso recipiente (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7f5);
						PdfPCell cell7g5 = new PdfPCell();
						cell7g5 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", HNpr), FontFactory.getFont("arial", 10)));
						cell7g5.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7g5);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell7a6 = new PdfPCell();
						cell7a6 = new PdfPCell(
								new Paragraph(
										"Peso suelo seco (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7a6);
						PdfPCell cell7b6 = new PdfPCell();
						if(listLLpshr.size() > 0){
							cell7b6 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpss.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell7b6 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7b6.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7b6);
						PdfPCell cell7c6 = new PdfPCell();
						if(listLLpshr.size() > 1){
							cell7c6 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpss.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell7c6 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7c6.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7c6);
						PdfPCell cell7d6 = new PdfPCell();
						if(listLLpshr.size() > 2){
							cell7d6 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLpss.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell7d6 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7d6.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7d6);
						table7.addCell(cell1a);
						PdfPCell cell7f6 = new PdfPCell();
						cell7f6 = new PdfPCell(
								new Paragraph(
										"Peso suelo seco (gr)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7f6);
						PdfPCell cell7g6 = new PdfPCell();
						cell7g6 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", HNpss), FontFactory.getFont("arial", 10)));
						cell7g6.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7g6);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell7a7 = new PdfPCell();
						cell7a7 = new PdfPCell(
								new Paragraph(
										"Contenido de hunedad (%)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7a7);
						PdfPCell cell7b7 = new PdfPCell();
						if(listLLpshr.size() > 0){
							cell7b7 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLch.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell7b7 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7b7.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7b7);
						PdfPCell cell7c7 = new PdfPCell();
						if(listLLpshr.size() > 1){
							cell7c7 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLch.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell7c7 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7c7.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7c7);
						PdfPCell cell7d7 = new PdfPCell();
						if(listLLpshr.size() > 2){
							cell7d7 = new PdfPCell(
										new Paragraph(
											String.format("%.2f", Double.parseDouble(listLLch.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell7d7 = new PdfPCell(
									new Paragraph(
										""));
						}
						cell7d7.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7d7);
						table7.addCell(cell1a);
						PdfPCell cell7f7 = new PdfPCell();
						cell7f7 = new PdfPCell(
								new Paragraph(
										"Contenido de hunedad (%)", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7f7);
						PdfPCell cell7g7 = new PdfPCell();
						cell7g7 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", HNch), FontFactory.getFont("arial", 10)));
						cell7g7.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7g7);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell7a8 = new PdfPCell();
						cell7a8 = new PdfPCell(
								new Paragraph(
										"Número de golpes", FontFactory.getFont("arial", 10)));
						table7.addCell(cell7a8);
						PdfPCell cell7b8 = new PdfPCell();
						if(listLLpshr.size() > 0){
							cell7b8 = new PdfPCell(
									new Paragraph(
										listLLng.get(0), FontFactory.getFont("arial", 10)));
						} else {
							cell7b8 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7b8.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7b8);
						PdfPCell cell7c8 = new PdfPCell();
						if(listLLpshr.size() > 1){
							cell7c8 = new PdfPCell(
									new Paragraph(
										listLLng.get(1), FontFactory.getFont("arial", 10)));
						} else {
							cell7c8 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7c8.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7c8);
						PdfPCell cell7d8 = new PdfPCell();
						if(listLLpshr.size() > 2){
							cell7d8 = new PdfPCell(
									new Paragraph(
										listLLng.get(2), FontFactory.getFont("arial", 10)));
						} else {
							cell7d8 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell7d8.setHorizontalAlignment(Element.ALIGN_CENTER);
						table7.addCell(cell7d8);
						table7.addCell(cell1a);
						table7.addCell(cell1a);
						table7.addCell(cell1a);

					PdfPTable table8 = new PdfPTable(5);
						float[] widths8 = { 30f, 30f, 1f, 30f, 10f };
						table8.setTotalWidth(widths8);
						
						PdfPCell cell8a1 = new PdfPCell();
						cell8a1 = new PdfPCell(
								new Paragraph(
										"Límite líquido", FontFactory.getFont("arial", 10)));
						table8.addCell(cell8a1);
						PdfPCell cell8b1 = new PdfPCell();
						cell8b1 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", LL), FontFactory.getFont("arial", 10)));
						cell8b1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table8.addCell(cell8b1);
						table8.addCell(cell1a);
						table8.addCell(cell1a);
						table8.addCell(cell1a);

					PdfPTable table9 = new PdfPTable(3);
						float[] widths9 = { 66f, 24f, 10f };
						table9.setTotalWidth(widths9);

						table9.addCell(cell1a);
						PdfPCell cell9b1 = new PdfPCell();
						cell9b1 = new PdfPCell(
								new Paragraph(
										"RESULTADOS", FontFactory.getFont("arial", 10)));
						cell9b1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table9.addCell(cell9b1);
						table9.addCell(cell1a);

					PdfPTable table10 = new PdfPTable(5);
						float[] widths10 = { 60f, 6f, 14f, 10f, 10f };
						table10.setTotalWidth(widths10);
						
						PdfPCell cell10a1 = new PdfPCell();
						cell10a1 = new PdfPCell(
								new Paragraph(
										"LÍMITE PLÁSTICO", FontFactory.getFont("arial", 10)));
						cell10a1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table10.addCell(cell10a1);
						table10.addCell(cell1a);
						PdfPCell cell10c1 = new PdfPCell();
						cell10c1 = new PdfPCell(
								new Paragraph(
										"% L. L.", FontFactory.getFont("arial", 10)));
						table10.addCell(cell10c1);
						PdfPCell cell10d1 = new PdfPCell();
						cell10d1 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", LL), FontFactory.getFont("arial", 10)));
						cell10d1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table10.addCell(cell10d1);
						table10.addCell(cell1a);

					PdfPTable table11 = new PdfPTable(8);
						float[] widths11 = { 30f, 10f, 10f, 10f, 6f, 14f, 10f, 10f };
						table11.setTotalWidth(widths11);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell11a1 = new PdfPCell();
						cell11a1 = new PdfPCell(
								new Paragraph(
										"Recipiente número", FontFactory.getFont("arial", 10)));
						table11.addCell(cell11a1);
						PdfPCell cell11b1 = new PdfPCell();
						if(listLPpshr.size() > 0){
							cell11b1 = new PdfPCell(
									new Paragraph(
											listLPrn.get(0), FontFactory.getFont("arial", 10)));
						} else {
							cell11b1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11b1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11b1);
						PdfPCell cell11c1 = new PdfPCell();
						if(listLPpshr.size() > 1){
							cell11c1 = new PdfPCell(
									new Paragraph(
											listLPrn.get(1), FontFactory.getFont("arial", 10)));
						} else {
							cell11c1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11c1);
						PdfPCell cell11d1 = new PdfPCell();
						if(listLPpshr.size() > 2){
							cell11d1 = new PdfPCell(
									new Paragraph(
											listLPrn.get(2), FontFactory.getFont("arial", 10)));
						} else {
							cell11d1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11d1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11d1);
						table11.addCell(cell1a);
						PdfPCell cell11f1 = new PdfPCell();
						cell11f1 = new PdfPCell(
								new Paragraph(
										"% L. P.", FontFactory.getFont("arial", 10)));
						table11.addCell(cell11f1);
						PdfPCell cell11g1 = new PdfPCell();
						cell11g1 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", LP), FontFactory.getFont("arial", 10)));
						cell11g1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11g1);
						table11.addCell(cell1a);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell11a2 = new PdfPCell();
						cell11a2 = new PdfPCell(
								new Paragraph(
										"Peso suelo hum. + recip. (gr)", FontFactory.getFont("arial", 10)));
						table11.addCell(cell11a2);
						PdfPCell cell11b2 = new PdfPCell();
						if(listLPpshr.size() > 0){
							cell11b2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpshr.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell11b2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11b2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11b2);
						PdfPCell cell11c2 = new PdfPCell();
						if(listLPpshr.size() > 1){
							cell11c2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpshr.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell11c2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11c2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11c2);
						PdfPCell cell11d2 = new PdfPCell();
						if(listLPpshr.size() > 2){
							cell11d2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpshr.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell11d2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11d2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11d2);
						table11.addCell(cell1a);
						PdfPCell cell11f2 = new PdfPCell();
						cell11f2 = new PdfPCell(
								new Paragraph(
										"% I. de P.", FontFactory.getFont("arial", 10)));
						table11.addCell(cell11f2);
						PdfPCell cell11g2 = new PdfPCell();
						cell11g2 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", IP), FontFactory.getFont("arial", 10)));
						cell11g2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11g2);
						table11.addCell(cell1a);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell11a3 = new PdfPCell();
						cell11a3 = new PdfPCell(
								new Paragraph(
										"Peso suelo seco + recip. (gr)", FontFactory.getFont("arial", 10)));
						table11.addCell(cell11a3);
						PdfPCell cell11b3 = new PdfPCell();
						if(listLPpshr.size() > 0){
							cell11b3 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpssr.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell11b3 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11b3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11b3);
						PdfPCell cell11c3 = new PdfPCell();
						if(listLPpshr.size() > 1){
							cell11c3 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpssr.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell11c3 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11c3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11c3);
						PdfPCell cell11d3 = new PdfPCell();
						if(listLPpshr.size() > 2){
							cell11d3 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpssr.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell11d3 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell11d3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11d3);
						table11.addCell(cell1a);
						PdfPCell cell11f3 = new PdfPCell();
						cell11f3 = new PdfPCell(
								new Paragraph(
										"% pasa 200", FontFactory.getFont("arial", 10)));
						table11.addCell(cell11f3);
						PdfPCell cell11g3 = new PdfPCell();
						cell11g3 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", PP200), FontFactory.getFont("arial", 10)));
						cell11g3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table11.addCell(cell11g3);
						table11.addCell(cell1a);

					PdfPTable table12 = new PdfPTable(7);
						float[] widths12 = { 30f, 10f, 10f, 10f, 6f, 24f, 10f };
						table12.setTotalWidth(widths12);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell12a1 = new PdfPCell();
						cell12a1 = new PdfPCell(
								new Paragraph(
										"Peso agua (gr)", FontFactory.getFont("arial", 10)));
						table12.addCell(cell12a1);
						PdfPCell cell12b1 = new PdfPCell();
						if(listLPpshr.size() > 0){
							cell12b1 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpa.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell12b1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell12b1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table12.addCell(cell12b1);
						PdfPCell cell12c1 = new PdfPCell();
						if(listLPpshr.size() > 1){
							cell12c1 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpa.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell12c1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell12c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table12.addCell(cell12c1);
						PdfPCell cell12d1 = new PdfPCell();
						if(listLPpshr.size() > 2){
							cell12d1 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpa.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell12d1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell12d1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table12.addCell(cell12d1);
						table12.addCell(cell1a);
						PdfPCell cell12f1 = new PdfPCell();
						cell12f1 = new PdfPCell(
								new Paragraph(
										"CLASIFICACIÓN", FontFactory.getFont("arial", 10)));
						cell12f1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table12.addCell(cell12f1);
						table12.addCell(cell1a);

					PdfPTable table13 = new PdfPTable(8);
						float[] widths13 = { 30f, 10f, 10f, 10f, 6f, 14f, 10f, 10f };
						table13.setTotalWidth(widths13);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell13a1 = new PdfPCell();
						cell13a1 = new PdfPCell(
								new Paragraph(
										"Peso recipiente (gr)", FontFactory.getFont("arial", 10)));
						table13.addCell(cell13a1);
						PdfPCell cell13b1 = new PdfPCell();
						if(listLPpshr.size() > 0){
							cell13b1 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpr.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell13b1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13b1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13b1);
						PdfPCell cell13c1 = new PdfPCell();
						if(listLPpshr.size() > 1){
							cell13c1 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpr.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell13c1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13c1);
						PdfPCell cell13d1 = new PdfPCell();
						if(listLPpshr.size() > 2){
							cell13d1 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpr.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell13d1 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13d1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13d1);
						table13.addCell(cell1a);
						PdfPCell cell13f1 = new PdfPCell();
						cell13f1 = new PdfPCell(
								new Paragraph(
										"A.A.S.H.T.O.", FontFactory.getFont("arial", 10)));
						table13.addCell(cell13f1);
						PdfPCell cell13g1 = new PdfPCell();
						cell13g1 = new PdfPCell(
								new Paragraph(
										AASHTO, FontFactory.getFont("arial", 10)));
						cell13g1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13g1);
						table13.addCell(cell1a);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell13a2 = new PdfPCell();
						cell13a2 = new PdfPCell(
								new Paragraph(
										"Peso suelo seco (gr)", FontFactory.getFont("arial", 10)));
						table13.addCell(cell13a2);
						PdfPCell cell13b2 = new PdfPCell();
						if(listLPpshr.size() > 0){
							cell13b2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpss.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell13b2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13b2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13b2);
						PdfPCell cell13c2 = new PdfPCell();
						if(listLPpshr.size() > 1){
							cell13c2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpss.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell13c2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13c2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13c2);
						PdfPCell cell13d2 = new PdfPCell();
						if(listLPpshr.size() > 2){
							cell13d2 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPpss.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell13d2 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13d2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13d2);
						table13.addCell(cell1a);
						PdfPCell cell13f2 = new PdfPCell();
						cell13f2 = new PdfPCell(
								new Paragraph(
										"IG", FontFactory.getFont("arial", 10)));
						table13.addCell(cell13f2);
						PdfPCell cell13g2 = new PdfPCell();
						cell13g2 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", IG), FontFactory.getFont("arial", 10)));
						cell13g2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13g2);
						table13.addCell(cell1a);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell13a3 = new PdfPCell();
						cell13a3 = new PdfPCell(
								new Paragraph(
										"Contenido de humedad (%)", FontFactory.getFont("arial", 10)));
						table13.addCell(cell13a3);
						PdfPCell cell13b3 = new PdfPCell();
						if(listLPpshr.size() > 0){
							cell13b3 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPch.get(0))), FontFactory.getFont("arial", 10)));
						} else {
							cell13b3 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13b3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13b3);
						PdfPCell cell13c3 = new PdfPCell();
						if(listLPpshr.size() > 1){
							cell13c3 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPch.get(1))), FontFactory.getFont("arial", 10)));
						} else {
							cell13c3 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13c3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13c3);
						PdfPCell cell13d3 = new PdfPCell();
						if(listLPpshr.size() > 2){
							cell13d3 = new PdfPCell(
									new Paragraph(
											String.format("%.2f", Double.parseDouble(listLPch.get(2))), FontFactory.getFont("arial", 10)));
						} else {
							cell13d3 = new PdfPCell(
									new Paragraph(
											""));
						}
						cell13d3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13d3);
						table13.addCell(cell1a);
						PdfPCell cell13f3 = new PdfPCell();
						cell13f3 = new PdfPCell(
								new Paragraph(
										"U.S.C.", FontFactory.getFont("arial", 10)));
						table13.addCell(cell13f3);
						PdfPCell cell13g3 = new PdfPCell();
						cell13g3 = new PdfPCell(
								new Paragraph(
										USC, FontFactory.getFont("arial", 10)));
						cell13g3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table13.addCell(cell13g3);
						table13.addCell(cell1a);

					PdfPTable table14 = new PdfPTable(6);
						float[] widths14 = { 30f, 30f, 6f, 14f, 10f, 10f };
						table14.setTotalWidth(widths14);
						
						/////////////////////////////////////////////////////
						
						PdfPCell cell14a1 = new PdfPCell();
						cell14a1 = new PdfPCell(
								new Paragraph(
										"Límite plástico (%)", FontFactory.getFont("arial", 10)));
						table14.addCell(cell14a1);
						PdfPCell cell14b1 = new PdfPCell();
						cell14b1 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", LP), FontFactory.getFont("arial", 10)));
						cell14b1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table14.addCell(cell14b1);
						table14.addCell(cell1a);
						PdfPCell cell14d1 = new PdfPCell();
						cell14d1 = new PdfPCell(
								new Paragraph(
										"I.LIQUIDEZ", FontFactory.getFont("arial", 10)));
						table14.addCell(cell14d1);
						PdfPCell cell14e1 = new PdfPCell();
						cell14e1 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", IL), FontFactory.getFont("arial", 10)));
						cell14e1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table14.addCell(cell14e1);
						table14.addCell(cell1a);

					PdfPTable table15 = new PdfPTable(4);
						float[] widths15 = { 66f, 14f, 10f, 10f };
						table15.setTotalWidth(widths15);
						
						/////////////////////////////////////////////////////

						table15.addCell(cell1a);
						PdfPCell cell15b2 = new PdfPCell();
						cell15b2 = new PdfPCell(
								new Paragraph(
										"CR", FontFactory.getFont("arial", 10)));
						table15.addCell(cell15b2);
						PdfPCell cell15c2 = new PdfPCell();
						cell15c2 = new PdfPCell(
								new Paragraph(
										String.format("%.2f", CR), FontFactory.getFont("arial", 10)));
						cell15c2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table15.addCell(cell15c2);
						table15.addCell(cell1a);
						
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
				
				PdfContentByte cb = writer.getDirectContent();
				cb.setLineWidth(1);
				cb.setCMYKColorStroke(0, 0, 0, 255);

                cb.moveTo(105, 138);
                cb.lineTo(105, 272);
                cb.moveTo(123, 138);
                cb.lineTo(123, 272);
                cb.moveTo(141, 138);
                cb.lineTo(141, 272);
                cb.moveTo(160, 138);
                cb.lineTo(160, 272);
                cb.moveTo(177, 138);
                cb.lineTo(177, 272);
                cb.moveTo(195, 138);
                cb.lineTo(195, 272);
                cb.moveTo(213, 138);
                cb.lineTo(213, 272);
                cb.moveTo(231, 138);
                cb.lineTo(231, 272);
                cb.moveTo(249, 138);
                cb.lineTo(249, 272);
                cb.moveTo(268, 138);
                cb.lineTo(268, 272);

                cb.moveTo(103, 272);
                cb.lineTo(268, 272);
                cb.moveTo(103, 250);
                cb.lineTo(268, 250);
                cb.moveTo(103, 228);
                cb.lineTo(268, 228);
                cb.moveTo(103, 206);
                cb.lineTo(268, 206);
                cb.moveTo(103, 184);
                cb.lineTo(268, 184);
                cb.moveTo(103, 162);
                cb.lineTo(268, 162);
                cb.moveTo(103, 140);
                cb.lineTo(268, 140);

                cb.moveTo(316, 270);
                cb.lineTo(522, 270);
                cb.moveTo(316, 257);
                cb.lineTo(522, 257);
                cb.moveTo(316, 244);
                cb.lineTo(522, 244);
                cb.moveTo(316, 231);
                cb.lineTo(522, 231);
                cb.moveTo(316, 218);
                cb.lineTo(522, 218);
                cb.moveTo(316, 205);
                cb.lineTo(522, 205);
                cb.moveTo(316, 192);
                cb.lineTo(522, 192);
                cb.moveTo(316, 179);
                cb.lineTo(522, 179);
                cb.moveTo(316, 166);
                cb.lineTo(522, 166);
                cb.moveTo(316, 153);
                cb.lineTo(522, 153);
                cb.moveTo(316, 140);
                cb.lineTo(522, 140);

                cb.moveTo(318, 270);
                cb.lineTo(318, 138);
                cb.moveTo(335, 270);
                cb.lineTo(335, 138);
                cb.moveTo(352, 270);
                cb.lineTo(352, 138);
                cb.moveTo(368, 270);
                cb.lineTo(368, 138);
                cb.moveTo(386, 270);
                cb.lineTo(386, 138);
                cb.moveTo(403, 270);
                cb.lineTo(403, 138);
                cb.moveTo(420, 270);
                cb.lineTo(420, 138);
                cb.moveTo(437, 270);
                cb.lineTo(437, 138);
                cb.moveTo(454, 270);
                cb.lineTo(454, 138);
                cb.moveTo(471, 270);
                cb.lineTo(471, 138);
                cb.moveTo(488, 270);
                cb.lineTo(488, 138);
                cb.moveTo(505, 270);
                cb.lineTo(505, 138);
                cb.moveTo(522, 270);
                cb.lineTo(522, 138);
                
                cb.stroke();
                
                BaseFont bf = BaseFont.createFont();
                cb.beginText();
                cb.setFontAndSize(bf, 8);
                cb.showTextAligned(1, "5", 105, 130, 0);
                cb.showTextAligned(1, "10", 123, 130, 0);
                cb.showTextAligned(1, "15", 141, 130, 0);
                cb.showTextAligned(1, "20", 159, 130, 0);
                cb.showTextAligned(1, "25", 177, 130, 0);
                cb.showTextAligned(1, "30", 195, 130, 0);
                cb.showTextAligned(1, "35", 213, 130, 0);
                cb.showTextAligned(1, "40", 231, 130, 0);
                cb.showTextAligned(1, "45", 249, 130, 0);
                cb.showTextAligned(1, "50", 268, 130, 0);
                cb.showTextAligned(1, "LÍMITE  LÍQUIDO", 186, 280, 0);
                cb.showTextAligned(1, "NÚMERO DE GOLPES", 186, 120, 0);

                cb.showTextAligned(1, "40", 95, 272, 0);
                cb.showTextAligned(1, "35", 95, 250, 0);
                cb.showTextAligned(1, "30", 95, 228, 0);
                cb.showTextAligned(1, "25", 95, 206, 0);
                cb.showTextAligned(1, "20", 95, 184, 0);
                cb.showTextAligned(1, "15", 95, 162, 0);
                cb.showTextAligned(1, "10", 95, 140, 0);
                cb.showTextAligned(1, "HUMEDAD (%)", 80, 206, 90);

                cb.showTextAligned(1, "0", 318, 130, 0);
                cb.showTextAligned(1, "10", 335, 130, 0);
                cb.showTextAligned(1, "20", 352, 130, 0);
                cb.showTextAligned(1, "30", 369, 130, 0);
                cb.showTextAligned(1, "40", 386, 130, 0);
                cb.showTextAligned(1, "50", 403, 130, 0);
                cb.showTextAligned(1, "60", 420, 130, 0);
                cb.showTextAligned(1, "70", 437, 130, 0);
                cb.showTextAligned(1, "80", 454, 130, 0);
                cb.showTextAligned(1, "90", 471, 130, 0);
                cb.showTextAligned(1, "100", 488, 130, 0);
                cb.showTextAligned(1, "110", 505, 130, 0);
                cb.showTextAligned(1, "120", 523, 130, 0);
                cb.showTextAligned(1, "CARTA DE PLASTICIDAD", 420, 278, 0);
                cb.showTextAligned(1, "LÍMITE  LÍQUIDO", 420, 120, 0);

                cb.showTextAligned(1, "50", 308, 270, 0);
                cb.showTextAligned(1, "45", 308, 257, 0);
                cb.showTextAligned(1, "40", 308, 244, 0);
                cb.showTextAligned(1, "35", 308, 231, 0);
                cb.showTextAligned(1, "30", 308, 218, 0);
                cb.showTextAligned(1, "25", 308, 205, 0);
                cb.showTextAligned(1, "20", 308, 192, 0);
                cb.showTextAligned(1, "15", 308, 179, 0);
                cb.showTextAligned(1, "10", 308, 166, 0);
                cb.showTextAligned(1, "5", 308, 153, 0);
                cb.showTextAligned(1, "0", 308, 140, 0);
                cb.showTextAligned(1, "ÍNDICE DE PLASTICIDAD", 295, 206, 90);
                
                cb.endText();
                cb.stroke();
                
                PdfContentByte cc = writer.getDirectContent();
                cc.setLineWidth(3);
				cc.setCMYKColorStroke(0, 255, 255, 0);
                cc.setCMYKColorFill(0, 153, 255, 0);
				
                if(listLLpshr.size() > 0)
                	cc.moveTo(M1256.lab1256LL_X(Integer.parseInt(listLLng.get(0))), M1256.lab1256LL_Y(Double.parseDouble(listLLch.get(0))));
                if(listLLpshr.size() > 1)
					cc.lineTo(M1256.lab1256LL_X(Integer.parseInt(listLLng.get(1))), M1256.lab1256LL_Y(Double.parseDouble(listLLch.get(1))));
				if(listLLpshr.size() > 2)
					cc.lineTo(M1256.lab1256LL_X(Integer.parseInt(listLLng.get(2))), M1256.lab1256LL_Y(Double.parseDouble(listLLch.get(2))));
				
				cc.stroke();
				
				PdfContentByte cd = writer.getDirectContent();
				cd.setCMYKColorStroke(0, 255, 255, 0);
				cd.setCMYKColorFill(0, 153, 255, 0);
				
                if(listLLpshr.size() > 0)
                	cd.circle(M1256.lab1256LL_X(Integer.parseInt(listLLng.get(0))), M1256.lab1256LL_Y(Double.parseDouble(listLLch.get(0))), 3);
                if(listLLpshr.size() > 1)
                	cd.circle(M1256.lab1256LL_X(Integer.parseInt(listLLng.get(1))), M1256.lab1256LL_Y(Double.parseDouble(listLLch.get(1))), 3);
                if(listLLpshr.size() > 2)
					cd.circle(M1256.lab1256LL_X(Integer.parseInt(listLLng.get(2))), M1256.lab1256LL_Y(Double.parseDouble(listLLch.get(2))), 3);
				
                cd.fill();
				cd.stroke();
                
                PdfContentByte ce = writer.getDirectContent();
                ce.setCMYKColorStroke(0, 0, 0, 255);
                ce.setCMYKColorFill(0, 153, 255, 0);
                ce.circle(M1256.lab1256CP_X(LL), M1256.lab1256CP_Y(IP), 3);
                ce.fill();
                
                ce.stroke();
				
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			document.close();
			mllSQLite.close();
			finish();
		} else {
			Toast.makeText(this, "No hay memoria SD", Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	private void callCalcular() {
		listLLrn = mllSQLite.getM1256LL_RN(ens_numero);
		listLLpshr = mllSQLite.getM1256LL_PSHR(ens_numero);
		listLLpssr = mllSQLite.getM1256LL_PSSR(ens_numero);
		
		for(int i=0;i<listLLpshr.size();i++){
			listLLpa.add(String.valueOf(Double.parseDouble(listLLpshr.get(i)) - Double.parseDouble(listLLpssr.get(i))));
		}
		
		listLLpr = mllSQLite.getM1256LL_PR(ens_numero);
		
		for(int i=0;i<listLLpshr.size();i++){
			listLLpss.add(String.valueOf(Double.parseDouble(listLLpssr.get(i)) - Double.parseDouble(listLLpr.get(i))));
		}
		
		for(int i=0;i<listLLpshr.size();i++){
			listLLch.add(String.valueOf(M1256.lab1256calc03(Double.parseDouble(listLLpa.get(i)), Double.parseDouble(listLLpss.get(i)))));
		}
		
		listLLng = mllSQLite.getM1256LL_NG(ens_numero);
		
		LL = M1256.lab1256calc04(listLLch, listLLng);
		
		listLPrn = mlpSQLite.getM1256LP_RN(ens_numero);
		listLPpshr = mlpSQLite.getM1256LP_PSHR(ens_numero);
		listLPpssr = mlpSQLite.getM1256LP_PSSR(ens_numero);
		
		for(int i=0;i<listLPpshr.size();i++){
			listLPpa.add(String.valueOf(Double.parseDouble(listLPpshr.get(i)) - Double.parseDouble(listLPpssr.get(i))));
		}
		
		listLPpr = mlpSQLite.getM1256LP_PR(ens_numero);
		
		for(int i=0;i<listLPpshr.size();i++){
			listLPpss.add(String.valueOf(Double.parseDouble(listLPpssr.get(i)) - Double.parseDouble(listLPpr.get(i))));
		}
		
		for(int i=0;i<listLPpshr.size();i++){
			listLPch.add(String.valueOf(M1256.lab1256calc03(Double.parseDouble(listLPpa.get(i)), Double.parseDouble(listLPpss.get(i)))));
		}
		
		LP = M1256.lab1256calc05(listLPch);

		HNrn = mlpSQLite.getM1256HN_RN(ens_numero);
		HNpshr = mlpSQLite.getM1256HN_PSHR(ens_numero);
		HNpssr = mlpSQLite.getM1256HN_PSSR(ens_numero);
		HNpa = HNpshr - HNpssr;
		HNpr = mlpSQLite.getM1256HN_PR(ens_numero);
		HNpss = HNpssr - HNpr;
		HNch = M1256.lab1256calc03(HNpa, HNpss);

		IP = LL - LP;
		PP200 = mlpSQLite.getM1256HN_PP200(ens_numero);
		AASHTO = mlpSQLite.getM1256HN_AASHTO(ens_numero);
		IG = (PP200 - 35) * (0.2 + 0.005 * (LL - 40)) + 0.01 * (PP200 - 15) * (IP - 10);
		USC = mlpSQLite.getM1256HN_USC(ens_numero);
		IL = (HNch - LP) / (LL - LP);
		CR = (LL - HNch) / IP;
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