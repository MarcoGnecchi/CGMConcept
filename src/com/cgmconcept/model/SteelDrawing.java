package com.cgmconcept.model;

import java.text.DecimalFormat;

import expr.Expr;
import expr.Parser;
import expr.SyntaxException;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class SteelDrawing implements Parcelable {

	private double mInlet;
	private double mOutlet;
	private int mNOfDies;
	private double mTargetSpeed;
	private double speedWireInlet;
	private double mAverageReduction;
	private double mTotalReduction;
	private double inletTs;
	private double outletTs;

	public double getmInlet() {
		return mInlet;
	}

	public void setInlet(double mInlet) {
		this.mInlet = mInlet;
	}

	public double getmOutlet() {
		return mOutlet;
	}

	public void setOutlet(double mOutlet) {
		this.mOutlet = mOutlet;
	}

	public int getNOfDies() {
		return mNOfDies;
	}

	public void setNOfDies(int mNOfDies) {
		this.mNOfDies = mNOfDies;
	}

	public double getmTargetSpeed() {
		return mTargetSpeed;
	}

	public void setTargetSpeed(double targetSpeed) {
		this.mTargetSpeed = targetSpeed;
	}

	public double getAverageReduction() {
		return mAverageReduction;
	}

	public void setmAverageReduction(int mAverageReduction) {
		this.mAverageReduction = mAverageReduction;
	}

	public double getmTotalReduction() {
		// =(1-(outlet^2/inlet^2))*100
		
		String eqString = "(1-(%s^2)/(%s^2))*100";
		
		String eqFormString = String.format(eqString, mOutlet, mInlet);
		Expr expr;

		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			//TODO create constants
			Log.e("CSR", e.explain());
			return 0;
		}
		return roundTwoDecimals(expr.value());
	}

	public void setmTotalReduction(int mTotalReduction) {
		this.mTotalReduction = mTotalReduction;
	}

	public double getInletTs() {
		return inletTs;
	}

	public void setInletTs(double inletTs) {
		this.inletTs = inletTs;
	}

	public double getOutletTs() {
		return outletTs;
	}

	public void setOutletTs(double outletTs) {
		this.outletTs = outletTs;
	}

	public double getSpeedWireInlet() {
		return speedWireInlet;
	}

	public void setSpeedWireInlet(double speedWireInlet) {
		this.speedWireInlet = speedWireInlet;
	}

	public void setmInlet(int mInlet) {
		this.mInlet = mInlet;
	}

	public void setmOutlet(int mOutlet) {
		this.mOutlet = mOutlet;
	}

	public void setmNOfDies(int mNOfDies) {
		this.mNOfDies = mNOfDies;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeDouble(mInlet);
		dest.writeDouble(mOutlet);
		dest.writeInt(mNOfDies);
		dest.writeDouble(mTargetSpeed);
	}

	static final Parcelable.Creator<SteelDrawing> CREATOR = new Parcelable.Creator<SteelDrawing>() {

		@Override
		public SteelDrawing createFromParcel(Parcel in) {
			SteelDrawing sd = new SteelDrawing();
			sd.mInlet = in.readDouble();
			sd.mOutlet = in.readDouble();
			sd.mNOfDies = in.readInt();
			sd.mTargetSpeed = in.readDouble();
			return sd;
		}

		@Override
		public SteelDrawing[] newArray(int size) {

			return new SteelDrawing[size];
		}
	};
	
	double roundTwoDecimals(double d) {
       return Math.round((d*10.0)/10.0);
}
}
