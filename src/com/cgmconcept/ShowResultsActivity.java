package com.cgmconcept;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

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
	        	sendEmail(mSteelDrawing);
	            return true;
	        case R.id.action_settings:
	        	showDialog();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
	private void sendEmail(SteelDrawing sd){
		Uri fileUri = createCSV(sd);
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/html");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Steel Drawing");
		intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(getEmailBody(sd)));
		intent.putExtra(Intent.EXTRA_STREAM, fileUri);
		

		startActivity(Intent.createChooser(intent, "Send Email"));
		
	}
	
	private Uri createCSV(SteelDrawing sd) {

		String columnString =   "\"Step\",\"Diameter\",\"Reduction\",\"Total Reduction\",\"Speed\",\"Total Reduction\",\"Speed\",\"Tensile Strength\",\"Pull\",\"Power\"";
		StringBuilder dataString = new StringBuilder();
		for (int i = 1; i <= sd.getNOfDies(); i++) {
			dataString.append("\"" + String.format( "%.2f", sd.getDiameter(i)) + "\",");
			dataString.append("\"" + String.format( "%.2f%%", sd.getReduction(i)*100)+ "\",");
			dataString.append("\"" + String.format( "%.2f%%", sd.getTotalReductionAtStep(i))+ "\",");
			dataString.append("\"" + String.format( "%.2f", sd.getSpeed(i))+ "\",");
			dataString.append("\"" + String.format( "%.2f", sd.getOutletTS(i))+ "\",");
			dataString.append("\"" + String.format( "%.2f", sd.getPull(i))+ "\",");
			dataString.append("\"" + String.format( "%.1f", sd.getPower(i))+ "\"\n");
		}
		String combinedString = columnString + "\n" + dataString;
	
		File file   = null;
		File root   = Environment.getExternalStorageDirectory();
		if (root.canWrite()){
		    File dir    =   new File (root.getAbsolutePath() + "/CGMConcept");
		     dir.mkdirs();
		     file   =   new File(dir, "SteelDrawing.csv");
		     FileOutputStream out   =   null;
		    try {
		        out = new FileOutputStream(file);
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
		        try {
		            out.write(combinedString.getBytes());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        try {
		            out.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		
		Uri uri  =   null;
		uri  =   Uri.fromFile(file);
		return uri;
	}
		

	private void openChartView() {
		Intent i = new Intent(ShowResultsActivity.this, Charts.class);
		i.putExtra(SteelDrawing.class.getName(), mSteelDrawing);
		startActivity(i);
	}
	
	private void showDialog(){
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Not available");
		alertDialog.setMessage("The functionality is not available in the free version, please contact www.cgmconcept.com");
		alertDialog.setIcon(R.drawable.ic_launcher);
		alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				alertDialog.dismiss();
			}
		});
		alertDialog.show();
	}
	
	private String getEmailBody(SteelDrawing sd){
		StringBuilder emailBody = new StringBuilder();
		emailBody.append("<html>\r\n" + 
				"	<head>\r\n" + 
				"		<title></title>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<h1>\r\n" + 
				"			CGMConcept</h1>\r\n" + 
				"		<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 500px;\">\r\n" + 
				"			<tbody>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Inlet</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getInlet()) +
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Outlet</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getOutlet()) +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Steps</td>\r\n" + 
				"					<td>\r\n" + 
				sd.getNOfDies() +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Carbon</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getCarbonContent()) +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Speed</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.0f", sd.getTargetSpeed()) +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Speed Wire Inlet</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getSpeedWireInlet()) +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Average Reduction</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getAverageReduction()) +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Total reduction</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getTotalReduction()) +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Inlet T/S</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.0f", sd.getInletTS()) +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						Outlet T/S</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.0f", sd.getOutlet()) +
				"				</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</tbody>\r\n" + 
				"		</table>\r\n" + 
				"		<p>\r\n" + 
				"			In allegato la tabella dei risultati.</p>\r\n" + 
				"		<p>\r\n" + 
				"			Visita il sito <a href=\"http://www.cgmconcept.com\">www.cgmconcept.com</a></p>\r\n" + 
				"		<p>\r\n" + 
				"			&nbsp;</p>\r\n" + 
				"	</body>\r\n" + 
				"</html>\r\n" + 
				""
				);
		
		return emailBody.toString();
	}
	
	@SuppressWarnings("unused")
	private void createHTML(SteelDrawing sd){
		StringBuilder emailBody = new StringBuilder();
		emailBody.append("<html>\r\n" + 
				"	<head>\r\n" + 
				"		<title><h2>CGMconcept</h2></title>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 500px;\">\r\n" + 
				"			<tbody>\r\n" + 
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
				"						step</td>\r\n" + 
				"					<td>\r\n" + 
				"						Diameter</td>\r\n" + 
				"					<td>\r\n" + 
				"						Reduction</td>\r\n" + 
				"					<td>\r\n" + 
				"						Total Reduction</td>\r\n" + 
				"					<td>\r\n" + 
				"						Speed</td>\r\n" + 
				"					<td>\r\n" + 
				"						Tensile Strength</td>\r\n" + 
				"					<td>\r\n" + 
				"						Pull</td>\r\n" + 
				"					<td>\r\n" + 
				"						Power</td>\r\n" + 
				"				</tr>\r\n");
		
				for (int i = 1; i <= sd.getNOfDies(); i++) {
				
				emailBody.append(
				"				<tr>\r\n" + 
				"					<td>\r\n" + 
							i				   +
				"					</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getDiameter(i)) +
				"					</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f%%", sd.getReduction(i)*100) +
				"					</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f%%", sd.getTotalReductionAtStep(i)) +
				"					</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getSpeed(i)) +
				"					</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getOutletTS(i)) +
				"					</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.2f", sd.getPull(i)) +
				"					</td>\r\n" + 
				"					<td>\r\n" + 
				String.format( "%.1f", sd.getPower(i)) +
				"					</td>\r\n" + 
				"				</tr>\r\n");
				}
				
				emailBody.append(
				"			</tbody>\r\n" + 
				"		</table>\r\n" + 
				"		<a href=\"http://www.cgmconcept.com/\">Visit our website</a>" +
				"	</body>\r\n" + 
				"</html>\r\n");
				
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/html");
				intent.putExtra(Intent.EXTRA_SUBJECT, "Steel Drawing");
				intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(emailBody.toString()).toString());

				startActivity(Intent.createChooser(intent, "Send Email"));
	}
}
