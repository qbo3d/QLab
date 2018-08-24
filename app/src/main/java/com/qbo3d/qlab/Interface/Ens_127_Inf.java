package com.qbo3d.qlab.Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.qbo3d.qlab.Logica.Datos_M127;
import com.qbo3d.qlab.Logica.Herr;
import com.qbo3d.qlab.Persistencia.SQLiteM127;
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

public class Ens_127_Inf extends Activity {

	private File UD_Lab;

	String pro_nombre;
	String ens_numero;

	String m127_str = "";
	int m127_id;
	
	TextView tv_ens_127_super;
	TextView tv_m127;
	Button bt_127_con;
	
	int longC;
	SQLiteM127 mueSQLite;
	Button bt_ens_127_consultar;
	Bundle extra;
	Datos_M127 m127;
	Intent data;
	Cursor cursorM127;
	private static final int REQUEST_CODE = 10;

	@SuppressLint("SdCardPath")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_127_inf);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		mueSQLite = new SQLiteM127(this);

		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			UD_Lab = new File(
					android.os.Environment.getExternalStorageDirectory(),
					"UD-Lab");
			if (!UD_Lab.exists()) {
				UD_Lab.mkdirs();
			}
			cursorM127 = mueSQLite.getAllM127(ens_numero);
			
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
									"DETERMINACION DEL CONTENIDO DE HUMEDAD INV E-127 - 07"));
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
									Herr.cambiarAnho(mueSQLite.getM127Fecha(ens_numero))));
					cell4d.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4e = new PdfPCell();
					cell4e = new PdfPCell(
							new Paragraph(
									"Descripción del suelo:"));
					PdfPCell cell4f = new PdfPCell();
					cell4f = new PdfPCell(
							new Paragraph(
									mueSQLite.getM127DesSuelo(ens_numero)));
					cell4f.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4g = new PdfPCell();
					cell4g = new PdfPCell(
							new Paragraph(
									"Proyecto:"));
					PdfPCell cell4h = new PdfPCell();
					cell4h = new PdfPCell(
							new Paragraph(
									mueSQLite.getM127proN(ens_numero)));
					cell4h.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4i = new PdfPCell();
					cell4i = new PdfPCell(
							new Paragraph(
									"Localización del proyecto:"));
					PdfPCell cell4j = new PdfPCell();
					cell4j = new PdfPCell(
							new Paragraph(
									mueSQLite.getM127Localizacion(ens_numero)));
					cell4j.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4k = new PdfPCell();
					cell4k = new PdfPCell(
							new Paragraph(
									"Realizado por:"));
					PdfPCell cell4l = new PdfPCell();
					cell4l = new PdfPCell(
							new Paragraph(
									mueSQLite.getM127Usuario(ens_numero)));
					cell4l.setHorizontalAlignment(Element.ALIGN_CENTER);
					

					PdfPCell cell4m = new PdfPCell();
					cell4m = new PdfPCell(
							new Paragraph(
									"Perforación No.:"));
					PdfPCell cell4n = new PdfPCell();
					cell4n = new PdfPCell(
							new Paragraph(
									mueSQLite.getM127Perforacion(ens_numero)));
					cell4n.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell4o = new PdfPCell();
					cell4o = new PdfPCell(
							new Paragraph(
									"Profundidad:"));
					PdfPCell cell4p = new PdfPCell();
					cell4p = new PdfPCell(
							new Paragraph(
									mueSQLite.getM127Profundidad(ens_numero) + " m"));
					cell4p.setHorizontalAlignment(Element.ALIGN_CENTER);
					
					
					PdfPCell cell4q = new PdfPCell();
					cell4q.setNoWrap(false);
					cell4q.setBorder(0);
					
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
					table4.addCell(cell4n);
					table4.addCell(cell4o);
					table4.addCell(cell4p);
					table4.addCell(cell4q);
					
				PdfPTable table5 = new PdfPTable(1);
					PdfPCell cell5a = new PdfPCell();
					cell5a.setNoWrap(false);
					cell5a.setBorder(0);

					table5.addCell(cell5a);

					float[] widths = { 80f, 20f };

					PdfPTable table6 = new PdfPTable(2);
					if (cursorM127.moveToFirst()) {
						table6.setTotalWidth(widths);
						table6.addCell("Masa del recipiente y del espécimen húmedo (g) W1");
						table6.addCell(cursorM127.getString(12) + " g");
						table6.addCell("Masa del recipiente y del espécimen seco (g) W2");
						table6.addCell(cursorM127.getString(13) + " g");
						table6.addCell("Masa del recipiente (g) W3");
						table6.addCell(cursorM127.getString(14) + " g");
						table6.addCell("Contenido de agua (%) w");
						table6.addCell(cursorM127.getString(11) + " %");
						table6.addCell("Volumen de la pastilla de suelo húmedo (cm³) V");
						table6.addCell(cursorM127.getString(16) + " ");
						table6.addCell("Volumen de la pastilla de suelo secada al horno (cm³) Vo");
						table6.addCell(cursorM127.getString(17) + " ");
						table6.addCell("Masa de la pastilla de suelo seco(g) Wo");
						table6.addCell(cursorM127.getString(18) + " ");
						table6.addCell("Relación de contracción (g/cm³) R");
						table6.addCell(cursorM127.getString(20) + " ");
						table6.addCell("Peso específico real de los sólidos Gs");
						table6.addCell(cursorM127.getString(21) + " ");
						table6.addCell("Masa unitaria del agua (g/cm³) (aproximadamente 1.0 g/cm³) γw");
						table6.addCell(cursorM127.getString(19) + " ");
						table6.addCell("Límite de contracción (%) LC");
						table6.addCell(cursorM127.getString(15) + " ");
						table6.addCell("Contenido de agua dado, superior al límite de contracción Wi");
						table6.addCell(cursorM127.getString(23) + " ");
						table6.addCell("Cambio volumétrico CV");
						table6.addCell(cursorM127.getString(22) + " ");
						table6.addCell("Contracción lineal CL");
						table6.addCell(cursorM127.getString(24) + " ");
					}
				document.add(table0);
				document.add(table1);
				document.add(table2);
				document.add(table3);
				document.add(table4);
				document.add(table5);
				document.add(table6);
				
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