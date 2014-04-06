package com.cgmconcept;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.cgmconcept.model.SteelDrawing;

public class Charts extends Activity {

	
	SteelDrawing mSteelDrawing;
	private GraphicalView mChart;

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

    private XYSeries mCurrentSeries;

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
		 	mCurrentSeries.add(1, 2);
	        mCurrentSeries.add(2, 3);
	        mCurrentSeries.add(3, 2);
	        mCurrentSeries.add(4, 5);
	        mCurrentSeries.add(5, 4);
	}

	private void initChart() {
		mCurrentSeries = new XYSeries("Sample Data");
        mDataset.addSeries(mCurrentSeries);
        mCurrentRenderer = new XYSeriesRenderer();
        mRenderer.addSeriesRenderer(mCurrentRenderer);
	}
}
