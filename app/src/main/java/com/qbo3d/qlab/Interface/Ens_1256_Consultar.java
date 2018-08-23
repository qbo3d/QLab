package com.qbo3d.qlab.Interface;

import java.util.ArrayList;
import java.util.List;

import com.qbo3d.qlab.Logica.Herr;
import com.qbo3d.qlab.Logica.M1256;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_LL;
import com.qbo3d.qlab.Persistencia.SQLiteM1256_LP;
import com.qbo3d.qlab.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ens_1256_Consultar extends Activity {

	String pro_nombre;
	String ens_numero;
	
	String m1256_str = "";
	int m1256_id;
	
	TextView tv_ens_1256_super;
	TextView tv_m1256;
	Button bt_1256_con;

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
	
	SQLiteM1256_LL mllSQLite;
	SQLiteM1256_LP mlpSQLite;
	Button bt_ens_1256_consultar;
	Bundle extra;
	Intent data;
	Cursor cursorM1256;
	private static final int REQUEST_CODE = 10;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ens_1256_consultar);
		
		extra = getIntent().getExtras();
		pro_nombre = extra.getString("pro_nombre");
		ens_numero = extra.getString("ens_numero");
		
		tv_ens_1256_super = (TextView) findViewById(R.id.tv_ens_1256_super_con);
		tv_ens_1256_super.setText("Proyecto: " + pro_nombre + "\nEnsayo: " + ens_numero);
		tv_m1256 = (TextView) findViewById(R.id.tv_ens_1256_2_con);
		bt_1256_con = (Button) findViewById(R.id.bt_m1256_con);
		mllSQLite = new SQLiteM1256_LL(this);
		mlpSQLite = new SQLiteM1256_LP(this);
		
		callCalcular();
		callLlenarTV();
		mllSQLite.close();
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

	private void callLlenarTV() {
		cursorM1256 = mllSQLite.getAllM1256(ens_numero);
			if (cursorM1256.moveToFirst()) {
				m1256_str = m1256_str + 
					"Proyecto: " + mllSQLite.getM1256_proN(ens_numero) + "\n" +
					"Ensayo No.: " + ens_numero + "\n" +
					"Fecha de ensayo: " + Herr.cambiarAnho(mllSQLite.getM1256_Fecha(ens_numero)) + "\n" +
					"Descripción del suelo: " + mllSQLite.getM1256_DesSuelo(ens_numero) + "\n" +
					"Localización del proyecto: " + mllSQLite.getM1256_Localizacion(ens_numero) + "\n" +
					"Realizado por: " + mllSQLite.getM1256_Usuario(ens_numero) + "\n";
				
				m1256_str = m1256_str + "\n" + "LÍMITE LÍQUIDO";
				
				m1256_str = m1256_str + "\n" + "RECIPIENTE NUMERO";
				
				for(int i=0;i<listLLpshr.size();i++){
					m1256_str = m1256_str + "\t" + listLLrn.get(i);
				}
				
				m1256_str = m1256_str + "\n" + "PESO SUELO HUM. + RECIP. (gr)";
				
				for(int i=0;i<listLLpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLLpshr.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "PESO SUELO SECO + RECIP. (gr)";
				
				for(int i=0;i<listLLpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLLpssr.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "PESO AGUA  (gr)";
				
				for(int i=0;i<listLLpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLLpa.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "PESO RECIPIENTE  (gr)";
				
				for(int i=0;i<listLLpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLLpr.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "PESO SUELO SECO  (gr)";
				
				for(int i=0;i<listLLpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLLpss.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "CONTENIDO DE HUMEDAD  (%)";
				
				for(int i=0;i<listLLpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLLch.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "NUMERO DE GOLPES";
				
				for(int i=0;i<listLLpshr.size();i++){
					m1256_str = m1256_str + "\t" + listLLng.get(i);
				}
				
				m1256_str = m1256_str + "\n" + "LIMITE LIQUIDO  (%)";

				m1256_str = m1256_str + "\t" + String.format("%.2f", LL);
				
				m1256_str = m1256_str + "\n\n" + "LÍMITE PLÁSTICO";
				
				m1256_str = m1256_str + "\n" + "RECIPIENTE NUMERO";
				
				for(int i=0;i<listLPpshr.size();i++){
					m1256_str = m1256_str + "\t" + listLPrn.get(i);
				}
				
				m1256_str = m1256_str + "\n" + "PESO SUELO HUM. + RECIP. (gr)";
				
				for(int i=0;i<listLPpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLPpshr.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "PESO SUELO SECO + RECIP. (gr)";
				
				for(int i=0;i<listLPpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLPpssr.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "PESO AGUA  (gr)";
				
				for(int i=0;i<listLPpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLPpa.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "PESO RECIPIENTE  (gr)";
				
				for(int i=0;i<listLPpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLPpr.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "PESO SUELO SECO  (gr)";
				
				for(int i=0;i<listLPpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLPpss.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "CONTENIDO DE HUMEDAD  (%)";
				
				for(int i=0;i<listLPpshr.size();i++){
					m1256_str = m1256_str + "\t" + String.format("%.2f", Double.parseDouble(listLPch.get(i)));
				}
				
				m1256_str = m1256_str + "\n" + "LIMITE PLASTICO (%)";

				m1256_str = m1256_str + "\t" + String.format("%.2f", LP);
				
				m1256_str = m1256_str + "\n\n" + "HUMEDAD NATURAL";
				
				m1256_str = m1256_str + "\n" + "RECIPIENTE NUMERO";

				m1256_str = m1256_str + "\t" + HNrn;
				
				m1256_str = m1256_str + "\n" + "PESO SUELO HUM. + RECIP. (gr)";

				m1256_str = m1256_str + "\t" + String.format("%.2f", HNpshr);
				
				m1256_str = m1256_str + "\n" + "PESO SUELO SECO + RECIP. (gr)";

				m1256_str = m1256_str + "\t" + String.format("%.2f", HNpssr);
				
				m1256_str = m1256_str + "\n" + "PESO AGUA (gr)";

				m1256_str = m1256_str + "\t" + String.format("%.2f", HNpa);
				
				m1256_str = m1256_str + "\n" + "PESO RECIPIENTE (gr)";

				m1256_str = m1256_str + "\t" + String.format("%.2f", HNpr);
				
				m1256_str = m1256_str + "\n" + "PESO SUELO SECO  (gr)";

				m1256_str = m1256_str + "\t" + String.format("%.2f", HNpss);
				
				m1256_str = m1256_str + "\n" + "CONTENIDO DE HUMEDAD (%)";

				m1256_str = m1256_str + "\t" + String.format("%.2f", HNch);
				
				m1256_str = m1256_str + "\n\n" + "RESULTADOS";
				
				m1256_str = m1256_str + "\n" + "% L. L.";

				m1256_str = m1256_str + "\t" + String.format("%.2f", LL);
				
				m1256_str = m1256_str + "\n" + "% L. P.";

				m1256_str = m1256_str + "\t" + String.format("%.2f", LP);
				
				m1256_str = m1256_str + "\n" + "% I. de P.";

				m1256_str = m1256_str + "\t" + String.format("%.2f", IP);
				
				m1256_str = m1256_str + "\n" + "% pasa 200";

				m1256_str = m1256_str + "\t" + String.format("%.2f", PP200);
				
				m1256_str = m1256_str + "\n\n" + "CLASIFICACIÓN";
				
				m1256_str = m1256_str + "\n" + "A.A.S.H.O.";

				m1256_str = m1256_str + "\t" + AASHTO;
				
				m1256_str = m1256_str + "\n" + "IG";

				m1256_str = m1256_str + "\t" + String.format("%.2f", IG);
				
				m1256_str = m1256_str + "\n" + "U.S.C.";

				m1256_str = m1256_str + "\t" + USC;
				
				m1256_str = m1256_str + "\n" + "I.LIQUIDEZ";

				m1256_str = m1256_str + "\t" + String.format("%.2f", IL);
				
				m1256_str = m1256_str + "\n" + "CR";

				m1256_str = m1256_str + "\t" + String.format("%.2f", CR);

			}
			
		tv_m1256.setText(m1256_str);
		
		bt_1256_con.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				callGenerarInforme();
			}
		});
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

	private void callGenerarInforme() {
		Intent intent = new Intent();
		intent.setClass(this, Ens_1256_Inf.class);
		intent.putExtra("pro_nombre", pro_nombre);
		intent.putExtra("ens_numero", ens_numero);
		startActivity(intent);
	}

}