package com.cgmconcept;

import android.app.Activity;
import android.os.Bundle;

import com.cgmconcept.model.SteelDrawing;

public class ShowResultsActivity extends Activity {

	SteelDrawing sd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_results);
		
	}
}
