package com.cgmconcept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.cgmconcept.model.SteelDrawing;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;

public class SteelDrawingActivity extends Activity implements
		ValidationListener, OnClickListener{

	@Required(order = 1)
	private EditText inletEditText;

	@Required(order = 2)
	private EditText outletEditText;

	@Required(order = 3)
	private NumberPicker nOfDiesPicker;

	@Required(order = 3)
	private NumberPicker carbonContentPicker;

	@Required(order = 5)
	private EditText targetSpeedEditText;
	

	private Button confirmButton; 
	
	private Validator validator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_steel_drawing);
		inletEditText = (EditText) findViewById(R.id.txtinlet);
		outletEditText = (EditText) findViewById(R.id.txtoutlet);
		
		nOfDiesPicker = (NumberPicker) findViewById(R.id.n_of_dies);
		nOfDiesPicker.setMinValue(1);
		nOfDiesPicker.setMaxValue(10);
		nOfDiesPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		
		carbonContentPicker = (NumberPicker) findViewById(R.id.carbon_content);
		String[] carbonValues = getResources().getStringArray(R.array.carbon_content_array);
		carbonContentPicker.setDisplayedValues(carbonValues);
		carbonContentPicker.setMinValue(0);
		carbonContentPicker.setMaxValue(carbonValues.length-1);
		carbonContentPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		
		targetSpeedEditText = (EditText) findViewById(R.id.txttargetspeed);
		confirmButton = (Button) findViewById(R.id.btnSubmit);

		validator = new Validator(this);
		validator.setValidationListener(this);
		
		confirmButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.steel_drawing, menu);
		return true;
	}

	@Override
	public void onValidationSucceeded() {

		SteelDrawing sd = new SteelDrawing();
		sd.setInlet(Double.valueOf(inletEditText.getText().toString()));
		sd.setOutlet(Double.valueOf(outletEditText.getText().toString()));
		sd.setNOfDies(Integer.valueOf(nOfDiesPicker.getValue()));
		sd.setTargetSpeed(Integer.valueOf(targetSpeedEditText.getText().toString()));
		sd.setCarbonContent(Double.valueOf(getResources().getStringArray(R.array.carbon_content_array)[carbonContentPicker.getValue()]));
		Intent i = new Intent(this, ConfirmDataActivity.class);
		i.putExtra(SteelDrawing.class.getName(), sd);
		startActivity(i);
				
	}

	@Override
	public void onValidationFailed(View failedView, Rule<?> failedRule) {

		final String message = failedRule.getFailureMessage();

		if (failedView instanceof EditText) {
			failedView.requestFocus();
			((EditText) failedView).setError(message);
		} else {
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View v) {
		
		validator.validate();
	}

}
