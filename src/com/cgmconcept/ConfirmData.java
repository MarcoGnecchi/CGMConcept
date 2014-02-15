package com.cgmconcept;

import com.cgmconcept.model.SteelDrawing;

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
	@InjectView(R.id.speedwireinlet) TextView speedwireinlet;
	@InjectView(R.id.averagereduction) TextView averagereduction;
	@InjectView(R.id.totalreduction) TextView totalReduction;
	@InjectView(R.id.inletts) TextView inletTS;
	@InjectView(R.id.outletts) TextView outletTS;
	@InjectView(R.id.btnSubmit) Button confirmButton;
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_data);
		ButterKnife.inject(this);
	    
		SteelDrawing sd = null;
		
		Bundle b = getIntent().getExtras();
	    if (b != null){
	    	sd = b.getParcelable(SteelDrawing.class.getName());
	    } else {
	    	finish();
	    }
		
	    inlet.setText(Double.toString(sd.getInlet()));
	    outlet.setText(Double.toString(sd.getOutlet()));
	    nOfDies.setText(Double.toString(sd.getNOfDies()));
	    carbonContent.setText(Double.toString(sd.getCarbonContent()));
	    targetSpeed.setText(Double.toString(sd.getTargetSpeed()));
	    speedwireinlet.setText(Double.toString(sd.getSpeedWireInlet()));
	    averagereduction.setText(Double.toString(sd.getAverageReduction()));
	    totalReduction.setText(Double.toString(sd.getTotalReduction()));
	    inletTS.setText(Double.toString(sd.getInletTs()));
	    outletTS.setText(Double.toString(sd.getOutletTs()));
	    speedwireinlet.setText(Double.toString(sd.getSpeedWireInlet()));
	    
	}
	
}
