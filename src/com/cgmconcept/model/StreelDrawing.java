package com.cgmconcept.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StreelDrawing implements Parcelable{

	private int mInlet;
	private int mOutlet;
	private int mNOfBlocks;
	private int mTypeOfWire;
	private int mTargetSpeed;
	private int mAverageReduction;
	private int mTotalReduction;
	private int inletTs;
	private int outletTs;
	
	
	public int getmInlet() {
		return mInlet;
	}
	public void setInlet(int mInlet) {
		this.mInlet = mInlet;
	}
	public int getmOutlet() {
		return mOutlet;
	}
	public void setOutlet(int mOutlet) {
		this.mOutlet = mOutlet;
	}
	public int getmNOfBlocks() {
		return mNOfBlocks;
	}
	public void setNOfBlocks(int mNOfBlocks) {
		this.mNOfBlocks = mNOfBlocks;
	}
	public int getmTypeOfWire() {
		return mTypeOfWire;
	}
	public void setTypeOfWire(int mTypeOfWire) {
		this.mTypeOfWire = mTypeOfWire;
	}
	public int getmTargetSpeed() {
		return mTargetSpeed;
	}
	public void setTargetSpeed(int mTargetSpeed) {
		this.mTargetSpeed = mTargetSpeed;
	}
	public int getmAverageReduction() {
		return mAverageReduction;
	}
	public void setmAverageReduction(int mAverageReduction) {
		this.mAverageReduction = mAverageReduction;
	}
	public int getmTotalReduction() {
		return mTotalReduction;
	}
	public void setmTotalReduction(int mTotalReduction) {
		this.mTotalReduction = mTotalReduction;
	}
	public int getInletTs() {
		return inletTs;
	}
	public void setInletTs(int inletTs) {
		this.inletTs = inletTs;
	}
	public int getOutletTs() {
		return outletTs;
	}
	public void setOutletTs(int outletTs) {
		this.outletTs = outletTs;
	}

	
	
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeInt(mInlet);
		dest.writeInt(mOutlet);
		dest.writeInt(mNOfBlocks);
		dest.writeInt(mTypeOfWire);
		dest.writeInt(mTargetSpeed);
	}
	
	static final Parcelable.Creator<StreelDrawing> CREATOR = new Parcelable.Creator<StreelDrawing>() {

		@Override
		public StreelDrawing createFromParcel(Parcel in) {
			StreelDrawing sd = new StreelDrawing();
			sd.mInlet = in.readInt();
			sd.mOutlet = in.readInt();
			sd.mNOfBlocks = in.readInt();
			sd.mTypeOfWire = in.readInt();
			sd.mTargetSpeed = in.readInt();	
			return sd;
		}

		@Override
		public StreelDrawing[] newArray(int size) {
			
			return new StreelDrawing[size];
		}
	};
	
	

}
