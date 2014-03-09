package com.cgmconcept;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cgmconcept.model.SteelDrawing;

public class ShowResultsActivity extends Activity {

	SteelDrawing sd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_results);
		
		SteelDrawing mSteelDrawing = null;
		
		Bundle b = getIntent().getExtras();
	    if (b != null){
	    	mSteelDrawing = b.getParcelable(SteelDrawing.class.getName());
	    } else {
	    	finish();
	    }
	
	    //TODO set for the TAPER value
	    mSteelDrawing.setTapeReduction(21.0);
	    
	    GridLayout gd = (GridLayout) findViewById(R.id.gd_show_result);
	    
	    for (int i = 1; i <= mSteelDrawing.getNOfDies(); i++) {
			
	    	gd.setRowCount(gd.getRowCount() + 1);
	    	
	    	TextView tv = new TextView(this);
	    	tv.setGravity(Gravity.CENTER);
	    	tv.setText(String.valueOf(i));
	    	
	    	gd.addView(tv);
	    	
	    	
	    	View diameterView = getLayoutInflater().inflate(R.layout.result_item, null);
	    	TextView diameterValue = (TextView) diameterView.findViewById(R.id.text);
	    	diameterValue.setText(String.format( "%.2f", mSteelDrawing.getDiameter(i)));
	    	gd.addView(diameterView);
	    	
	    	View reductionView = getLayoutInflater().inflate(R.layout.result_item, null);
	    	TextView reductionValue = (TextView) reductionView.findViewById(R.id.text);
	    	reductionValue.setText(String.format( "%.2f%", mSteelDrawing.getReduction(i)));
	    	gd.addView(reductionValue);
	    	
	    	View totReductionView = getLayoutInflater().inflate(R.layout.result_item, null);
	    	TextView totReductionValue = (TextView) totReductionView.findViewById(R.id.text);
	    	totReductionValue.setText(String.format( "%.2f", mSteelDrawing.getTotalReductionAtStep(i)));
	    	gd.addView(totReductionValue);
	    	
	    	View speedView = getLayoutInflater().inflate(R.layout.result_item, null);
	    	TextView speedValue = (TextView) speedView.findViewById(R.id.text);
	    	speedValue.setText(String.format( "%.2f", mSteelDrawing.getSpeed(i)));
	    	gd.addView(speedValue);
	    	
	    	View outletTSViewKG = getLayoutInflater().inflate(R.layout.result_item, null);
	    	TextView outletTSValueKG = (TextView) outletTSViewKG.findViewById(R.id.text);
	    	outletTSValueKG.setText(String.format( "%.2f", mSteelDrawing.getOutletTSKG(i)));
	    	gd.addView(outletTSViewKG);
	    	
	    	View outletTSView = getLayoutInflater().inflate(R.layout.result_item, null);
	    	TextView outletTSValue = (TextView) outletTSView.findViewById(R.id.text);
	    	outletTSValue.setText(String.format( "%.2f", mSteelDrawing.getOutletTS(i)));
	    	gd.addView(outletTSView);
	    	
	    	View pullView = getLayoutInflater().inflate(R.layout.result_item, null);
	    	TextView pullValue = (TextView) pullView.findViewById(R.id.text);
	    	pullValue.setText(String.format( "%.2f", mSteelDrawing.getPull(i)));
	    	gd.addView(pullView);
	    	
	    	View powerView = getLayoutInflater().inflate(R.layout.result_item, null);
	    	TextView powerValue = (TextView) powerView.findViewById(R.id.text);
	    	powerValue.setText(String.format( "%.2f", mSteelDrawing.getPower(i)));
	    	gd.addView(powerView);
		}
	
	}
}
