package com.Table;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cgmconcept.R;
import com.cgmconcept.model.SteelDrawing;

public class TableMainLayout extends RelativeLayout {

	public final String TAG = "TableMainLayout.java";
	
	// set the header titles
	String headers[] = {
		"Step"		
	};
	
	TableLayout tableA;
	TableLayout tableB;
	TableLayout tableC;
	TableLayout tableD;
	
	HorizontalScrollView horizontalScrollViewB;
	HorizontalScrollView horizontalScrollViewD;

	ScrollView scrollViewC;
	ScrollView scrollViewD;
	
	Context context;
	
	
	//harcoded number of headers
	//TODO: get it from the view
	int headerCellsWidth[] = new int[8];
	
	public TableMainLayout(Context context, SteelDrawing sd) {
		
		super(context);
		
		this.context = context;
		
		// initialize the main components (TableLayouts, HorizontalScrollView, ScrollView)
		this.initComponents();
		this.setComponentsId();
		this.setScrollViewAndHorizontalScrollViewTag();
		
		
		// no need to assemble component A, since it is just a table
		this.horizontalScrollViewB.addView(this.tableB);
		
		this.scrollViewC.addView(this.tableC);
		
		this.scrollViewD.addView(this.horizontalScrollViewD);
		this.horizontalScrollViewD.addView(this.tableD);
		
		// add the components to be part of the main layout
		this.addComponentToMainLayout();
		this.setBackgroundColor(Color.BLACK);
		
		
		// add some table rows
		this.addTableRowToTableA();
		this. addTableRowToTableB();
		
		this.resizeHeaderHeight();
		
		this.getTableRowHeaderCellWidth();
		
		this.generateTableC_AndTable_D(sd);
		
		this.resizeBodyTableRowHeight();
	}

	// initalized components 
	private void initComponents(){
		
		this.tableA = new TableLayout(this.context); 
		this.tableB = new TableLayout(this.context); 
		this.tableC = new TableLayout(this.context); 
		this.tableD = new TableLayout(this.context);
		
		this.horizontalScrollViewB = new MyHorizontalScrollView(this.context);
		this.horizontalScrollViewD = new MyHorizontalScrollView(this.context);
		
		this.scrollViewC = new MyScrollView(this.context);
		this.scrollViewD = new MyScrollView(this.context);
		
		this.tableA.setBackgroundColor(Color.GREEN);
		this.horizontalScrollViewB.setBackgroundColor(Color.BLACK);
		
	}
	
	// set essential component IDs
	private void setComponentsId(){
		this.tableA.setId(1);
		this.horizontalScrollViewB.setId(2);
		this.scrollViewC.setId(3);
		this.scrollViewD.setId(4);
	}
	
	// set tags for some horizontal and vertical scroll view
	private void setScrollViewAndHorizontalScrollViewTag(){
		
		this.horizontalScrollViewB.setTag("horizontal scroll view b");
		this.horizontalScrollViewD.setTag("horizontal scroll view d");
		
		this.scrollViewC.setTag("scroll view c");
		this.scrollViewD.setTag("scroll view d");
	}
	
	// we add the components here in our TableMainLayout
	private void addComponentToMainLayout(){
		
		// RelativeLayout params were very useful here
		// the addRule method is the key to arrange the components properly
		RelativeLayout.LayoutParams componentB_Params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		componentB_Params.addRule(RelativeLayout.RIGHT_OF, this.tableA.getId());
		
		RelativeLayout.LayoutParams componentC_Params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		componentC_Params.addRule(RelativeLayout.BELOW, this.tableA.getId());
		
		RelativeLayout.LayoutParams componentD_Params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		componentD_Params.addRule(RelativeLayout.RIGHT_OF, this.scrollViewC.getId());
		componentD_Params.addRule(RelativeLayout.BELOW, this.horizontalScrollViewB.getId());
		
		// 'this' is a relative layout, 
		// we extend this table layout as relative layout as seen during the creation of this class
		this.addView(this.tableA);
		this.addView(this.horizontalScrollViewB, componentB_Params);
		this.addView(this.scrollViewC, componentC_Params);
		this.addView(this.scrollViewD, componentD_Params);
			
	}
	

	
	private void addTableRowToTableA(){
		this.tableA.addView(this.componentATableRow());
	}
	
	private void addTableRowToTableB(){
		this.tableB.addView(this.componentBTableRow());
	}
	
	// generate table row of table A
	TableRow componentATableRow(){
		
		TableRow componentATableRow = new TableRow(this.context);
		TextView textView = this.headerTextView(this.headers[0]);
		componentATableRow.addView(textView);
		
		return componentATableRow;
	}
	
	// generate table row of table B
	TableRow componentBTableRow(){
		
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TableRow componentBTableRow = (TableRow) inflater.inflate(R.layout.show_result_table_headers, null);
		return componentBTableRow;
		/*
		int headerFieldCount = this.headers.length;
		
		TableRow.LayoutParams params = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		params.setMargins(2, 0, 0, 0);
		
		for(int x=0; x<(headerFieldCount-1); x++){
			TextView textView = this.headerTextView(this.headers[x+1]);
			textView.setLayoutParams(params);
			componentBTableRow.addView(textView);
		}
		*/
	}
	
	// generate table row of table C and table D
	private void generateTableC_AndTable_D(SteelDrawing sd){
		
		// just seeing some header cell width
		for(int x=0; x<this.headerCellsWidth.length; x++){
			Log.v("TableMainLayout.java", this.headerCellsWidth[x]+"");
		}
		
		for (int i = 1; i <= sd.getNOfDies(); i++) {
			
			TableRow tableRowForTableC = this.tableRowForTableC(sd, i);
			TableRow taleRowForTableD = this.taleRowForTableD(sd, i);
			
			tableRowForTableC.setBackgroundColor(Color.BLACK);
			taleRowForTableD.setBackgroundColor(Color.BLACK);
			
			this.tableC.addView(tableRowForTableC);
			this.tableD.addView(taleRowForTableD);

		}
		
		/*for(SampleObject sampleObject : this.sampleObjects){
			
			TableRow tableRowForTableC = this.tableRowForTableC(sampleObject);
			TableRow taleRowForTableD = this.taleRowForTableD(sampleObject);
			
			tableRowForTableC.setBackgroundColor(Color.LTGRAY);
			taleRowForTableD.setBackgroundColor(Color.LTGRAY);
			
			this.tableC.addView(tableRowForTableC);
			this.tableD.addView(taleRowForTableD);
			
		}*/
	}
	
	// a TableRow for table C
	TableRow tableRowForTableC(SteelDrawing sd, int i){
		
		TableRow.LayoutParams params = new TableRow.LayoutParams( this.headerCellsWidth[0],LayoutParams.MATCH_PARENT);
		params.setMargins(0, 2, 0, 0);
		
		//Basically this is just the row header
		TableRow tableRowForTableC = new TableRow(this.context);
		TextView textView = this.bodyTextView(Integer.valueOf(i).toString());
		textView.setBackgroundColor(((i % 2) == 0) ? Color.LTGRAY : Color.WHITE);
		textView.setTextColor(Color.BLACK);
		textView.setTextSize(30);
		tableRowForTableC.addView(textView,params);
		
		return tableRowForTableC;
	}
	
	TableRow taleRowForTableD(SteelDrawing sd, int i){

		TableRow taleRowForTableD = new TableRow(this.context);
		
		int loopCount = ((TableRow)this.tableB.getChildAt(0)).getChildCount();
		String info[] = {
				String.format( "%.2f", sd.getDiameter(i)),
				String.format( "%.2f%%", sd.getReduction(i)*100),
				String.format( "%.2f%%", sd.getTotalReductionAtStep(i)),
				String.format( "%.2f", sd.getSpeed(i)),
				String.format( "%.2f", sd.getOutletTS(i)),
				String.format( "%.2f", sd.getPull(i)),
				String.format( "%.1f", sd.getPower(i))
		};
		
		int layout = ((i % 2) == 0) ? R.layout.result_item_pair : R.layout.result_item_odd;

		for(int x=0 ; x<loopCount; x++){
			
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			TextView textViewB = (TextView) inflater.inflate(layout, null);
			textViewB.setBackgroundColor(((i % 2) == 0) ? Color.LTGRAY : Color.WHITE);
			
			int margin = 2;
			TableRow.LayoutParams params = new TableRow.LayoutParams( headerCellsWidth[x+1] - margin,LayoutParams.MATCH_PARENT);
			params.setMargins(margin, 2, 0, 0);
			
			textViewB.setText(info[x]);
			taleRowForTableD.addView(textViewB,params);
		}
		
		return taleRowForTableD;
		
	}
	
	// table cell standard TextView
	TextView bodyTextView(String label){
		
		TextView bodyTextView = new TextView(this.context);
		bodyTextView.setText(label);
		bodyTextView.setGravity(Gravity.CENTER);
		bodyTextView.setPadding(5, 5, 5, 5);
		
		return bodyTextView;
	}
	
	// header standard TextView
	TextView headerTextView(String label){
		
		TextView headerTextView = new TextView(this.context);
		headerTextView.setBackgroundColor(Color.WHITE);
		headerTextView.setText(label);
		headerTextView.setGravity(Gravity.CENTER);
		headerTextView.setPadding(5, 5, 5, 5);
		
		return headerTextView;
	}
	
	// resizing TableRow height starts here
	void resizeHeaderHeight() {
		
		TableRow productNameHeaderTableRow = (TableRow) this.tableA.getChildAt(0);
		TableRow productInfoTableRow = (TableRow)  this.tableB.getChildAt(0);

		int rowAHeight = this.viewHeight(productNameHeaderTableRow);
		int rowBHeight = this.viewHeight(productInfoTableRow);

		TableRow tableRow = rowAHeight < rowBHeight ? productNameHeaderTableRow : productInfoTableRow;
		int finalHeight = rowAHeight > rowBHeight ? rowAHeight : rowBHeight;

		this.matchLayoutHeight(tableRow, finalHeight);
	}
	
	void getTableRowHeaderCellWidth(){
		
		int tableAChildCount = ((TableRow)this.tableA.getChildAt(0)).getChildCount();
		int tableBChildCount = ((TableRow)this.tableB.getChildAt(0)).getChildCount();;
		
		for(int x=0; x<(tableAChildCount+tableBChildCount); x++){
			
			if(x==0){
				this.headerCellsWidth[x] = this.viewWidth(((TableRow)this.tableA.getChildAt(0)).getChildAt(x));
			}else{
				this.headerCellsWidth[x] = this.viewWidth(((TableRow)this.tableB.getChildAt(0)).getChildAt(x-1));
				Log.d("TableMainLayout", "Table B column " + x + " size " + headerCellsWidth[x]);
			}
			
		}
	}
	
	// resize body table row height
	void resizeBodyTableRowHeight(){
		
		int tableC_ChildCount = this.tableC.getChildCount();
		
		for(int x=0; x<tableC_ChildCount; x++){
		
			TableRow productNameHeaderTableRow = (TableRow) this.tableC.getChildAt(x);
			TableRow productInfoTableRow = (TableRow)  this.tableD.getChildAt(x);
	
			int rowAHeight = this.viewHeight(productNameHeaderTableRow);
			int rowBHeight = this.viewHeight(productInfoTableRow);
	
			TableRow tableRow = rowAHeight < rowBHeight ? productNameHeaderTableRow : productInfoTableRow;
			int finalHeight = rowAHeight > rowBHeight ? rowAHeight : rowBHeight;

			this.matchLayoutHeight(tableRow, finalHeight);		
		}
		
	}
	
	// match all height in a table row
	// to make a standard TableRow height
	private void matchLayoutHeight(TableRow tableRow, int height) {
		
		int tableRowChildCount = tableRow.getChildCount();
		
		// if a TableRow has only 1 child
		if(tableRow.getChildCount()==1){
			
			View view = tableRow.getChildAt(0);
			TableRow.LayoutParams params = (TableRow.LayoutParams) view.getLayoutParams();
			params.height = height - (params.bottomMargin + params.topMargin);
			
			return ;
		}
		
		// if a TableRow has more than 1 child
		for (int x = 0; x < tableRowChildCount; x++) {
			
			View view = tableRow.getChildAt(x);
			
			TableRow.LayoutParams params = (TableRow.LayoutParams) view.getLayoutParams();

			if (!isTheHeighestLayout(tableRow, x)) {
				params.height = height - (params.bottomMargin + params.topMargin);
				return;
			}
		}

	}

	// check if the view has the highest height in a TableRow
	private boolean isTheHeighestLayout(TableRow tableRow, int layoutPosition) {
		
		int tableRowChildCount = tableRow.getChildCount();
		int heighestViewPosition = -1;
		int viewHeight = 0;

		for (int x = 0; x < tableRowChildCount; x++) {
			View view = tableRow.getChildAt(x);
			int height = this.viewHeight(view);

			if (viewHeight < height) {
				heighestViewPosition = x;
				viewHeight = height;
			}
		}

		return heighestViewPosition == layoutPosition;
	}

	// read a view's height
	private int viewHeight(View view) {
		view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		return view.getMeasuredHeight();
	}

	// read a view's width
	private int viewWidth(View view) {
		view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		return view.getMeasuredWidth();
	}

	// horizontal scroll view custom class
	class MyHorizontalScrollView extends HorizontalScrollView{

		public MyHorizontalScrollView(Context context) {
			super(context);
		}
		
		@Override
		protected void onScrollChanged(int l, int t, int oldl, int oldt) {
			String tag = (String) this.getTag();
			
			if(tag.equalsIgnoreCase("horizontal scroll view b")){
				horizontalScrollViewD.scrollTo(l, 0);
			}else{
				horizontalScrollViewB.scrollTo(l, 0);
			}
		}
		
	}

	// scroll view custom class
	class MyScrollView extends ScrollView{

		public MyScrollView(Context context) {
			super(context);
		}
		
		@Override
		protected void onScrollChanged(int l, int t, int oldl, int oldt) {
			
			String tag = (String) this.getTag();
			
			if(tag.equalsIgnoreCase("scroll view c")){
				scrollViewD.scrollTo(0, t);
			}else{
				scrollViewC.scrollTo(0,t);
			}
		}
	}

	
}
