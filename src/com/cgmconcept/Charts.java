package com.cgmconcept;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.cgmconcept.model.SteelDrawing;

public class Charts extends Activity {

	
	SteelDrawing mSteelDrawing;
	private GraphicalView mChart;

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

    private XYSeries mPowerSeries;
//    private XYSeries mTensileSeries;

    private XYSeriesRenderer mCurrentRenderer;
	
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
		
	    setContentView(R.layout.charts);
	    
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		 LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
	      if (mChart == null) {
	            initChart();
	            addSampleData();
	            mChart = ChartFactory.getCubeLineChartView(this, mDataset, mRenderer, 0.3f);
	            layout.addView(mChart);
	        } else {
	            mChart.repaint();
	        }
	}

	private void addSampleData() {
		
			mPowerSeries.setTitle("POWER");
			for (int i = 1; i < mSteelDrawing.getNOfDies(); i++) {
				mPowerSeries.add(i, mSteelDrawing.getPower(i));
			}
			
//			for (int i = 1; i < mSteelDrawing.getNOfDies(); i++) {
//				mPowerSeries.add(i, mSteelDrawing.getOutletTSKG(i));
//			}
			
	}

	private void initChart() {
		mPowerSeries = new XYSeries("Power");
        mDataset.addSeries(mPowerSeries);
        //mDataset.addSeries(mTensileSeries);
        
        mCurrentRenderer = new XYSeriesRenderer();
        mCurrentRenderer.setColor(Color.GREEN);
        mRenderer.addSeriesRenderer(mCurrentRenderer);
	}
}
