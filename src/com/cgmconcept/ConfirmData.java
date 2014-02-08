package com.cgmconcept;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmData extends Activity {
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_data);
		
		TextView inlet;
		TextView outlet;
		TextView nOfBlock;
		TextView targetSpeed;
		Button confirmButton; 
	}
}
