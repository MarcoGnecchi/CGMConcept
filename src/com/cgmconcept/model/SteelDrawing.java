package com.cgmconcept.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.common.collect.ImmutableMap;

import expr.Expr;
import expr.Parser;
import expr.SyntaxException;

public class SteelDrawing implements Parcelable {

	public static double CARBON_CONTENT[] = { 0.10,0.15,0.20,0.25,0.30,0.35,0.40,0.45,0.50,0.55,0.60,0.65,0.70,0.75,0.80,0.85,0.90,0.95,1.00,1.10};
	static final ImmutableMap<Double, Double> INLET_PASCAL = 
		new ImmutableMap.Builder<Double, Double>()
			.put(0.10, 392.4)
			.put(0.15,441.45)
			.put(0.20,490.50)
			.put(0.25,539.55)
			.put(0.30,588.60)
			.put(0.35,647.46)
			.put(0.40,706.32)
			.put(0.45,765.18)
			.put(0.50,833.85)
			.put(0.55,892.71)
			.put(0.60,951.57)
			.put(0.65,1010.43)
			.put(0.70,1069.29)
			.put(0.75,1128.15)
			.put(0.80,1177.20)
			.put(0.85,1226.25)
			.put(0.90,1275.30)
			.put(0.95,1324.35)
			.put(1.00,1373.40)
			.put(1.10,1471.50)
			.build();
	
	static final ImmutableMap<Double, Double> OUTLET_PASCAL = 
		new ImmutableMap.Builder<Double, Double>()
			.put(0.10,907.768538318346)
			.put(0.15,936.096010901630)
			.put(0.20,1126.840963903750)
			.put(0.25,1220.228465847890)
			.put(0.30,1312.707915996460)
			.put(0.35,1391.399695382340)
			.put(0.40,1459.719065540770)
			.put(0.45,1552.229876131550)
			.put(0.50,1647.986723597560)
			.put(0.55,1750.400117351560)
			.put(0.60,1832.650138730360)
			.put(0.65,1931.252778332090)
			.put(0.70,2029.616217057640)
			.put(0.75,2120.186201970900)
			.put(0.80,2203.193699181060)
			.put(0.85,2274.301083820820)
			.put(0.90,2338.658321615020)
			.put(0.95,2420.481264571750)
			.put(1.00,2498.445182099090)
			.put(1.10,2673.451591154970)
			.build();
	
	
	
	
	private double inlet;
	private double outlet;
	private int nOfDies;
	private double targetSpeed;
	private double speedWireInlet;
	private double averageReduction;
	private double totalReduction;
	private double inletTs;
	private double outletTs;
	private double carbonContent;

	
	public double getCarbonContent() {
		return carbonContent;
	}

	public void setCarbonContent(double carbonContent) {
		this.carbonContent = carbonContent;
	}

	public double getInlet() {
		return inlet;
	}

	public void setInlet(double inlet) {
		this.inlet = inlet;
	}

	public double getOutlet() {
		return outlet;
	}

	public void setOutlet(double outlet) {
		this.outlet = outlet;
	}

	public int getNOfDies() {
		return nOfDies;
	}

	public void setNOfDies(int nOfDies) {
		this.nOfDies = nOfDies;
	}

	public double getTargetSpeed() {
		return targetSpeed;
	}

	public void setTargetSpeed(double targetSpeed) {
		this.targetSpeed = targetSpeed;
	}

	public double getAverageReduction() {
		String eqString = "(1-((%s/%s)^2)^(1/%s))*100";
		
		String eqFormString = String.format(eqString, outlet, inlet, nOfDies);
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			Log.e("CSR", e.explain());
			return 0;
		}
		
		
		return expr.value();
	}

	public double getTotalReduction() {
		
		String eqString = "(1-(%s^2)/(%s^2))*100";
		
		String eqFormString = String.format(eqString, outlet, inlet);
		Expr expr;

		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			//TODO create constants
			Log.e("CSR", e.explain());
			return 0;
		}
		
		return expr.value();
	}

	
	public double getInletTs() {
			
		return INLET_PASCAL.get(getCarbonContent());
	}

	public void setInletTs(double inletTs) {
		
		this.inletTs = inletTs;
	}

	public double getOutletTs() {
		
		return OUTLET_PASCAL.get(getCarbonContent());
	}

	public void setOutletTs(double outletTs) {
		this.outletTs = outletTs;
	}

	public double getSpeedWireInlet() {
		String eqString = "%s*(1-%s/100)";
		
		String eqFormString = String.format(eqString, targetSpeed, getTotalReduction());
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			Log.e("CSR", e.explain());
			return 0;
		}
		
		return expr.value();
	}

	public void setmInlet(int mInlet) {
		this.inlet = mInlet;
	}

	public void setmOutlet(int mOutlet) {
		this.outlet = mOutlet;
	}

	public void setmNOfDies(int mNOfDies) {
		this.nOfDies = mNOfDies;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeDouble(getInlet());
		dest.writeDouble(getOutlet());
		dest.writeInt(getNOfDies());
		dest.writeDouble(getTargetSpeed());
		dest.writeDouble(getCarbonContent());
	}

	public static final Parcelable.Creator<SteelDrawing> CREATOR = new Parcelable.Creator<SteelDrawing>() {

		@Override
		public SteelDrawing createFromParcel(Parcel in) {
			SteelDrawing sd = new SteelDrawing();
			sd.inlet = in.readDouble();
			sd.outlet = in.readDouble();
			sd.nOfDies = in.readInt();
			sd.targetSpeed = in.readDouble();
			sd.carbonContent = in.readDouble();
			return sd;
		}

		@Override
		public SteelDrawing[] newArray(int size) {

			return new SteelDrawing[size];
		}
	};
	
}
