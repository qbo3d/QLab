package com.qbo3d.qlab.Interface;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;

import com.qbo3d.qlab.R;

public class Elv_Norma extends ExpandableListActivity {

	private static final String NOMBRE = "NOMBRE";
	private static final String IS_EVEN = "IS_EVEN";
	private ExpandableListAdapter elAdapter;
	List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
	InputStream is;
	boolean addDescripcion = true;
	boolean addNorma;
	HashMap<String, String> normaMap = new HashMap<String, String>();
	HashMap<String, String> descMap = new HashMap<String, String>();
	int count = 0;
	int count1 = -1;
	int count2 = 0;
	List<String> descripcionCount = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elv_norma_main);

		is = this.getResources().openRawResource(R.raw.normas);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] strings = line.split("&");
				String nrm = strings[0].trim();
				String dsc = strings[1].trim();

				if (normaMap.containsValue(nrm)) {
					addNorma = false;
					descMap.put("descripcion" + count1 + count2, dsc);
					count2++;
				} 
				else {
					count1++;
					descripcionCount.add("" + count2);
					count2 = 0;
					normaMap.put("norma" + count, nrm);
					descMap.put("descripcion" + count1 + count2, dsc);
					addDescripcion = false;
					count2++;
					count++;
				}
			}
		} 
		catch (Exception e) {}
		// re-arranging the state count list
		descripcionCount.add("" + count2);
		descripcionCount.remove(0);
		for (int i = 0; i < normaMap.size(); i++) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			String ctry = normaMap.get("norma" + i);
			curGroupMap.put(NOMBRE, ctry);
			curGroupMap.put(IS_EVEN, "Norma " + i);
			List<Map<String, String>> children = new ArrayList<Map<String, String>>();
			int k = Integer.parseInt(descripcionCount.get(i));
			for (int j = 0; j < k; j++) {
				Map<String, String> curChildMap = new HashMap<String, String>();
				children.add(curChildMap);
				curChildMap.put(NOMBRE, descMap.get("descripcion" + i + j));
//				curChildMap.put(IS_EVEN, "Lab " + j);
			}
			childData.add(children);
		}

		// Set up our adapter
		elAdapter = new SimpleExpandableListAdapter(
				this, 
				groupData,
				R.layout.elv_norma_group, 
				new String[] {
						NOMBRE, IS_EVEN }, 
						new int[] { R.id.groupNorma }, 
				childData,
				R.layout.elv_norma_child, 
				new String[] {
						NOMBRE, IS_EVEN }, 
						new int[] { R.id.childNorma });
		setListAdapter(elAdapter);
	}
	
	private void iniciarProyecto() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_ri, menu);
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
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
