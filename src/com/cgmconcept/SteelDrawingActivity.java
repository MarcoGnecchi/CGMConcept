package com.cgmconcept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
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
	private Spinner nOfDiesSpinner;

	@Required(order = 3)
	private Spinner carbonContentSpinner;

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
		
		nOfDiesSpinner = (Spinner) findViewById(R.id.n_of_dies);
		Integer[] nOfDies = new Integer[]{1,2,3,4,5,6,7,8,9,10};
		ArrayAdapter<Integer> nOfDiasadapter = new ArrayAdapter<Integer>(this, R.layout.simple_spinner_align_center, nOfDies);
		nOfDiesSpinner.setAdapter(nOfDiasadapter);
		
		carbonContentSpinner = (Spinner) findViewById(R.id.carbon_content);
		String[] carbonValues = getResources().getStringArray(R.array.carbon_content_array);
		ArrayAdapter<String> carbonContentAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_align_center, carbonValues);
		carbonContentSpinner.setAdapter(carbonContentAdapter);
		
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
		sd.setNOfDies(Integer.valueOf((Integer) nOfDiesSpinner.getSelectedItem()));
		sd.setTargetSpeed(Integer.valueOf(targetSpeedEditText.getText().toString()));
		sd.setCarbonContent(Double.valueOf(carbonContentSpinner.getSelectedItem().toString()));
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
