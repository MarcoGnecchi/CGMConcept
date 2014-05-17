package com.cgmconcept;

import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.cgmconcept.model.SteelDrawing;

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
	@InjectView(R.id.btnConstant) Button btnConstant;
	@InjectView(R.id.btnOptimized) Button btnOptimised;
	
	private SteelDrawing mSteelDrawing;
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_data);
		ButterKnife.inject(this);
	    
		mSteelDrawing = null;
		
		Bundle b = getIntent().getExtras();
	    if (b != null){
	    	mSteelDrawing = b.getParcelable(SteelDrawing.class.getName());
	    } else {
	    	finish();
	    }
		
	    inlet.setText(String.format(Locale.ENGLISH, "%.2f", mSteelDrawing.getInlet()));
	    outlet.setText(String.format(Locale.ENGLISH, "%.2f", mSteelDrawing.getOutlet()));
	    nOfDies.setText(mSteelDrawing.getNOfDies() + "");
	    carbonContent.setText(String.format(Locale.ENGLISH, "%.2f", mSteelDrawing.getCarbonContent()));
	    targetSpeed.setText(String.format(Locale.ENGLISH, "%.0f", mSteelDrawing.getTargetSpeed()));
	    speedwireinlet.setText(String.format(Locale.ENGLISH, "%.2f", mSteelDrawing.getSpeedWireInlet()));
	    averagereduction.setText(String.format(Locale.ENGLISH, "%.2f", mSteelDrawing.getAverageReduction()));
	    totalReduction.setText(String.format(Locale.ENGLISH, "%.2f", mSteelDrawing.getTotalReduction()));
	    inletTS.setText(String.format(Locale.ENGLISH, "%.0f", mSteelDrawing.getInletTS()));
	    outletTS.setText(String.format(Locale.ENGLISH, "%.0f" ,mSteelDrawing.getOutletTS(mSteelDrawing.getNOfDies())));
	    speedwireinlet.setText(String.format(Locale.ENGLISH, "%.2f", mSteelDrawing.getSpeedWireInlet()));
	    
	}
	
	@OnClick(R.id.btnConstant)
	public void submitConstant(){
		//Standard taper reduction
		mSteelDrawing.setTaperReduction(21.0);
		Intent i = new Intent(this, ShowResultsActivity.class);
		i.putExtra(SteelDrawing.class.getName(), mSteelDrawing);
		startActivity(i);
	}
	
	@OnClick(R.id.btnOptimized)
	public void submitOptimized(){
		new OptimizeTaperReduction(ConfirmDataActivity.this).execute(mSteelDrawing);
	}
	
	
	private class OptimizeTaperReduction extends AsyncTask<SteelDrawing, Double, Double>{
		
		private ProgressDialog progressDialog;
		private Context context;
		
		public OptimizeTaperReduction(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(context);
			progressDialog.setTitle("GCMConcept");
			progressDialog.setMessage("Starting optimization");
			progressDialog.setCancelable(true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					cancel(true);
				}
			});
            progressDialog.show();
		}
		@Override
		protected Double doInBackground(SteelDrawing... steelDrawing) {
			//Looking for a good taper reduction
			SteelDrawing sdCurrent;
			SteelDrawing sdNext;
			double taperReduction = 5.0;
			//setInitialData
			sdCurrent = new SteelDrawing(steelDrawing[0]);
			sdCurrent.setTaperReduction(taperReduction);
			
			taperReduction = taperReduction + 0.5;
			sdNext = new SteelDrawing(mSteelDrawing);
			sdNext.setTaperReduction(taperReduction);
			publishProgress(taperReduction);
			while(sdCurrent.getVariance() > sdNext.getVariance() && taperReduction < 50.0 && !isCancelled()) {
					Log.d("CGMConcept", " current variance = " + sdCurrent.getVariance() + " which is > of next variance "  + sdNext.getVariance());
					taperReduction = taperReduction + 0.5;
					sdCurrent = sdNext;
					sdNext = new SteelDrawing(mSteelDrawing);
					sdNext.setTaperReduction(taperReduction);
					publishProgress(taperReduction);
			}
			
			if (!isCancelled()){
				Log.d("CGMConcept", "FOUND THE OPTIM VALUE as current variance = " + sdCurrent.getVariance() + " which is < of next variance "  + sdNext.getVariance());
				Log.d("CGMConcept", "best taper reduction = " + sdCurrent.getTaperReduction());
				progressDialog.dismiss();
				return sdCurrent.getTaperReduction();
			} else {
				progressDialog.dismiss();
				Log.d("CGMConcept", "Task has been cancelled at " + sdCurrent.getTaperReduction());
				return null;
			}
		}
		
		@Override
		protected void onProgressUpdate(Double... values) {
			super.onProgressUpdate(values);
			progressDialog.setMessage("Testing taper reduction " + values[0].toString());
		}
		
		@Override
		protected void onPostExecute(Double result) {
			super.onPostExecute(result);
			mSteelDrawing.setTaperReduction(result);
			progressDialog.dismiss();
			Intent i = new Intent(context, ShowResultsActivity.class);
			i.putExtra(SteelDrawing.class.getName(), mSteelDrawing);
			startActivity(i);
		}
		
		@Override
		protected void onCancelled() {
			super.onCancelled();
			Log.i("CGMConcept", "Cancelled");
		}
	}
	
	
}
