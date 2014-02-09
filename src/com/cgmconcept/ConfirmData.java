package com.cgmconcept;

import com.cgmconcept.model.StreelDrawing;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmData extends Activity {
	
	@InjectView(R.id.inlet) TextView inlet;
	@InjectView(R.id.outlet) TextView outlet;
	@InjectView(R.id.n_of_dies) TextView nOfDies;
	@InjectView(R.id.carbon_content) TextView carbonContent;
	@InjectView(R.id.targetspeed) TextView targetSpeed;
	@InjectView(R.id.speedwireinlet) TextView sppedwireinlet;
	@InjectView(R.id.averagereduction) TextView averagereduction;
	@InjectView(R.id.totalreduction) TextView totalReduction;
	@InjectView(R.id.inletts) TextView inletTS;
	@InjectView(R.id.outletts) TextView outletTS;
	@InjectView(R.id.btnSubmit) Button confirmButton;
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_data);
		ButterKnife.inject(this);
	    
		Bundle extras = getIntent().getExtras();
	    if (extras != null){
	    	
	    } else {
	    	finish();
	    }
		
		
	}
	
	private double getSpeedWireInlet(StreelDrawing sd){
		double methodResult;
		return new Double(0);
	}
}
