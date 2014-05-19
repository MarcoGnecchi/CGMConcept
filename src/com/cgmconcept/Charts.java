package com.cgmconcept;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
	            mChart = ChartFactory.getLineChartView(this, mDataset, mRenderer);
	            layout.addView(mChart);
	        } else {
	            mChart.repaint();
	        }
	}

	private void addSampleData() {
		
			for (int i = 1; i <= mSteelDrawing.getNOfDies(); i++) {
				mPowerSeries.add(i, mSteelDrawing.getPower(i));
			}
			
	}

	private void initChart() {
		mPowerSeries = new XYSeries("");
        mDataset.addSeries(mPowerSeries);
        
        mCurrentRenderer = new XYSeriesRenderer();
        mCurrentRenderer.setColor(Color.RED);
        mCurrentRenderer.setLineWidth(5);
        mRenderer.addSeriesRenderer(mCurrentRenderer);
        mRenderer.setZoomEnabled(false);
        mRenderer.setPointSize(10);
        mRenderer.setLabelsTextSize(30);
        mRenderer.setShowGrid(true);
        
        if (!mSteelDrawing.isTRConstant()){
        	
        	mRenderer.setYAxisMax(mSteelDrawing.getAveragePower()*3);
        	mRenderer.setYAxisMin(mSteelDrawing.getAveragePower()/3);
        } 
        
	}
}
