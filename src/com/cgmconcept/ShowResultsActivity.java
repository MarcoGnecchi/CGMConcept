package com.cgmconcept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.Table.TableMainLayout;
import com.cgmconcept.model.SteelDrawing;

public class ShowResultsActivity extends Activity {

	SteelDrawing mSteelDrawing;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mSteelDrawing = null;
		
		Bundle b = getIntent().getExtras();
	    if (b != null){
	    	mSteelDrawing = b.getParcelable(SteelDrawing.class.getName());
	    } else {
	    	finish();
	    }
		
	    setContentView(new TableMainLayout(this, mSteelDrawing));
	}
	
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.steel_drawing, menu);
	    return true;
	  } 
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_charts:
	        	openChartView();
	            return true;
	        case R.id.action_email:
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void openChartView() {
		Intent i = new Intent(ShowResultsActivity.this, Charts.class);
		i.putExtra(SteelDrawing.class.getName(), mSteelDrawing);
		startActivity(i);
	}
	
	
}
