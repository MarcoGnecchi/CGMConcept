package com.cgmconcept;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cgmconcept.model.StreelDrawing;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType;
import com.mobsandgeeks.saripaar.annotation.Required;

public class SteelDrawingActivity extends Activity implements
		ValidationListener, OnClickListener{

	@Required(order = 1)
	private EditText inletEditText;

	@Required(order = 2)
	private EditText outletEditText;

	@Required(order = 3)
	@NumberRule(order = 4, type = NumberType.INTEGER, gt = 1, lt = 10, message="Please enter a number between 1 and 10")
	private EditText nOfBlockEditText;

	@Required(order = 5)
	private EditText targetSpeedEditText;
	
	private Spinner spinner;

	private Button confirmButton; 
	
	private Validator validator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_steel_drawing);

		inletEditText = (EditText) findViewById(R.id.txtinlet);
		outletEditText = (EditText) findViewById(R.id.txtoutlet);
		nOfBlockEditText = (EditText) findViewById(R.id.txtnofblocks);
		targetSpeedEditText = (EditText) findViewById(R.id.txttargetspeed);
		confirmButton = (Button) findViewById(R.id.btnSubmit);

		// Setting the spinner
		spinner = (Spinner) findViewById(R.id.typeofwire);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.wires_array,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		validator = new Validator(this);
		validator.setValidationListener(this);
		
		confirmButton.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.steel_drawing, menu);
		return true;
	}

	@Override
	public void onValidationSucceeded() {

		StreelDrawing sd = new StreelDrawing();
		sd.setInlet(Integer.valueOf(inletEditText.getText().toString()));
		sd.setOutlet(Integer.valueOf(inletEditText.getText().toString()));
		sd.setNOfBlocks(Integer.valueOf(inletEditText.getText().toString()));
		sd.setTypeOfWire(spinner.getSelectedItemPosition());
		sd.setTargetSpeed(Integer.valueOf(inletEditText.getText().toString()));
		
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
