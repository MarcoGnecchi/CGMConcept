package com.cgmconcept;

import com.cgmconcept.model.SteelDrawing;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmDataActivity extends Activity {
	
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
	@InjectView(R.id.btnCalculate) Button btnCalculate;
	
	private SteelDrawing mSteelDrawing;
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_data);
		ButterKnife.inject(this);
	    
		SteelDrawing mSteelDrawing = null;
		
		Bundle b = getIntent().getExtras();
	    if (b != null){
	    	mSteelDrawing = b.getParcelable(SteelDrawing.class.getName());
	    } else {
	    	finish();
	    }
		
	    inlet.setText(Double.toString(mSteelDrawing.getInlet()));
	    outlet.setText(Double.toString(mSteelDrawing.getOutlet()));
	    nOfDies.setText(Double.toString(mSteelDrawing.getNOfDies()));
	    carbonContent.setText(Double.toString(mSteelDrawing.getCarbonContent()));
	    targetSpeed.setText(Double.toString(mSteelDrawing.getTargetSpeed()));
	    speedwireinlet.setText(Double.toString(mSteelDrawing.getSpeedWireInlet()));
	    averagereduction.setText(Double.toString(mSteelDrawing.getAverageReduction()));
	    totalReduction.setText(Double.toString(mSteelDrawing.getTotalReduction()));
	    inletTS.setText(String.format("%.2f", mSteelDrawing.getInletTS()));
	    outletTS.setText(String.format("%.2f" ,mSteelDrawing.getOutletTS(mSteelDrawing.getNOfDies())));
	    speedwireinlet.setText(Double.toString(mSteelDrawing.getSpeedWireInlet()));
	    
	}
	
	@OnClick(R.id.btnCalculate)
	public void submit(){
		Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, ShowResultsActivity.class);
		i.putExtra(SteelDrawing.class.getName(), mSteelDrawing);
		startActivity(i);
	}
	
}
