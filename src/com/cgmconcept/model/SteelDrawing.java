package com.cgmconcept.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.common.collect.ImmutableMap;

import expr.Expr;
import expr.Parser;
import expr.SyntaxException;
import expr.Variable;

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
	
	static Double[] polinomialCoefficients010 = {40.48,-61.35,1353.90,-5837.30,11787.00,-11300.00,4148.20};
	static Double[] polinomialCoefficients015 = {45.48,-48.70,1179.80,-5236.60,10852.00,-10609.00,3950.70};
	static Double[] polinomialCoefficients020 = {50.72,-68.09,1816.30,-8693.20,18626.00,-18424.00,6867.00};
	static Double[] polinomialCoefficients025 = {55.66,-56.03,1690.80,-8202.90,17828.00,-17864.00,6736.50};
	static Double[] polinomialCoefficients030 = {60.59,-43.97,1565.20,-7712.70,17031.00,-17305.00,6606.00};
	static Double[] polinomialCoefficients035 = {66.70,-100.91,2210.20,-10490.00,22586.00,-22536.00,8486.50};
	static Double[] polinomialCoefficients040 = {72.81,-157.85,2855.20,-13267.00,28140.00,-27768.00,10367.00};
	static Double[] polinomialCoefficients045 = {79.35,-170.96,3048.50,-14280.00,30481.00,-30235.00,11340.00};
	static Double[] polinomialCoefficients050 = {85.88,-184.08,3241.90,-15294.00,32822.00,-32702.00,12315.00};
	static Double[] polinomialCoefficients055 = {91.87,-181.93,3227.00,-32270.00,15254.00,-32817.00,32789.00};
	static Double[] polinomialCoefficients060 = {97.87,-179.79,3212.00,-15215.00,32811.00,-32876.00,12464.00};
	static Double[] polinomialCoefficients065 = {103.91,-194.40,3332.40,-15761.00,34015.00,-34100.00,12935.00};
	static Double[] polinomialCoefficients070 = {109.95,-209.01,3452.70,-16307.00,35219.00,-35323.00,13405.00};
	static Double[] polinomialCoefficients075 = {115.39,-196.79,3284.50,-15428.00,33191.00,-33249.00,12640.00};
	static Double[] polinomialCoefficients080 = {120.91,-203.20,3351.90,-15607.00,33315.00,-33197.00,12589.00};
	static Double[] polinomialCoefficients085 = {125.84,-170.92,2995.80,-14208.00,30738.00,-30938.00,11827.00};
	static Double[] polinomialCoefficients090 = {130.89,-169.84,3035.60,-14596.00,31807.00,-32104.00,12282.00};
	static Double[] polinomialCoefficients095 = {135.70,-187.51,3113.90,-14665.00,31557.00,-31563.00,12001.00};
	static Double[] polinomialCoefficients100 = {140.64,-236.01,3585.30,-16515.00,34958.00,-34479.00,12949.00};
	static Double[] polinomialCoefficients110 = {150.69,-252.87,3841.40,-17695.00,37455.00,-36942.00,13874.00};

	static final ImmutableMap<Double, Double[]> OUTLET_COEFFICIENT_PASCAL = 
		new ImmutableMap.Builder<Double, Double[]>()
			.put(0.10, polinomialCoefficients010)
			.put(0.15,polinomialCoefficients015)
			.put(0.20,polinomialCoefficients020)
			.put(0.25,polinomialCoefficients025)
			.put(0.30,polinomialCoefficients030)
			.put(0.35,polinomialCoefficients035)
			.put(0.40,polinomialCoefficients040)
			.put(0.45,polinomialCoefficients045)
			.put(0.50,polinomialCoefficients050)
			.put(0.55,polinomialCoefficients055)
			.put(0.60,polinomialCoefficients060)
			.put(0.65,polinomialCoefficients065)
			.put(0.70,polinomialCoefficients070)
			.put(0.75,polinomialCoefficients075)
			.put(0.80,polinomialCoefficients080)
			.put(0.85,polinomialCoefficients085)
			.put(0.90,polinomialCoefficients090)
			.put(0.95,polinomialCoefficients095)
			.put(1.00,polinomialCoefficients100)
			.put(1.10,polinomialCoefficients110)
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
	private double taperReduction;
	private Double alpha;
	private Double unknown;
	private Double delta;
	private Double[] powers = new Double[11];
	private Double[] diameters = new Double[11];
	private boolean isTRConstant = true;

	public SteelDrawing(){
		super();
	}
	
	//Object for cloning
	public SteelDrawing(SteelDrawing sdIn) {
		this.inlet = sdIn.getInlet();
		this.outlet = sdIn.getOutlet();
		this.nOfDies = sdIn.getNOfDies();
		this.targetSpeed = sdIn.getTargetSpeed();
		this.carbonContent = sdIn.getCarbonContent();
		this.taperReduction = sdIn.getTaperReduction();
	}
	
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
	
	public double getTaperReduction(){
		
		return taperReduction;
	}
	
	public void setTaperReduction(double tapeReduction){
		
		this.taperReduction = tapeReduction;
	}
	
	public boolean isTRConstant() {
		return isTRConstant;
	}

	public void setTRConstant(boolean isTRConstant) {
		this.isTRConstant = isTRConstant;
	}
	
	//J5
	public double getUnknown(){
		
		if (this.unknown != null){
			return unknown;
		}
		
		//LOG(alpha) - LOG(output diameter)
		
		String eqString = "log(%s)/log(10) - log(%s)/log(10)";
		
		String eqFormString = String.format(eqString, getAlpha(), outlet);
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			return 0;
		}
		
		this.unknown = expr.value();
		return unknown;
	}
	
	//J4
	public double getAlpha(){
		
		//Check cache
		if (this.alpha != null){
			return alpha;
		}
		
		//Output diameter/ sqrt(1- tape reduction)
		
		String eqString = "%s /sqrt(1 * (1 - %s/100))";
		
		String eqFormString = String.format(eqString, outlet, getTaperReduction());
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			return 0;
		}
		this.alpha = expr.value();
		return alpha;
	}

	//J6 
	public double getDelta(){
		
		if (this.delta != null){
			
			return delta;
		}
		
		//(log(F3)/log(10 - log(F4)/log(10) - (F10 * J5))/(F10*(F10-1)/2)
		String eqString = "(log(%s)/log(10) - log(%s)/log(10) - (%s * %s))/(%s*(%s-1)/2)";
		
		String eqFormString = String.format(eqString, inlet, outlet, nOfDies, getUnknown(), nOfDies, nOfDies);
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			return 0;
		}
		
		this.delta = expr.value();
		return delta;
	}
	
	public double getDiameter(final int step){
	
		if (step > nOfDies){
			
			throw new RuntimeException("The step is > than the number of DIES");
		} 
		
		//Check cache
		if (diameters[step] != null){
			return diameters[step];
		}
		
		//At step 0 the diameter is equal to the input diameter
		if (step == 0){
			
			return getInlet();
		}
		
//		String fromExcel = "10^(LOG($F$4)+($F$10-C24)*$J$5+(($F$10-C24)*($F$10-C24-1)/2)*$J$6";
		String eqString = "10^( log(%s)/log(10) + ( %s - %s ) * %s +(( %s - %s )*(%s - %s - 1)/2) * %s)";
		
		String eqFormString = String.format(eqString, outlet, nOfDies, step, getUnknown(), nOfDies, step, nOfDies, step, getDelta());
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			
			return 0;
		}
		
		diameters[step] = expr.value();
		return diameters[step];
	}

	public double getReduction(final int step){
		
		if (step > nOfDies){
			
			throw new RuntimeException("The step is > than the number of DIES");
		} 
		
		String eqString = "(1-(%s/%s)^2)";
		
		String eqFormString = String.format(eqString, getDiameter(step), getDiameter(step-1));
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			
			return 0;
		}
		
		
		return expr.value();
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
	
	public double getTotalReductionAtStep(final int step) {
		
		String eqString = "(1-(%s^2)/(%s^2))*100";
		
		String eqFormString = String.format(eqString, getDiameter(step), inlet);
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

	
	public double getInletTS() {
			
		return INLET_PASCAL.get(getCarbonContent());
	}

	public void setInletTs(double inletTs) {
		
		this.inletTs = inletTs;
	}
	
	public double getOutletTS() {
		return this.getOutletTS(this.getNOfDies());
	}

	public double getOutletTS(final int step) {
		
		Double[] coeff = OUTLET_COEFFICIENT_PASCAL.get(getCarbonContent());
		//X0 + X1 * TR^1 + X2 * TR^2 + X3 * TR ^3 + X4 *TR^4 + X5*TR^5 + X6*TR^6
		String eqString = "(%s + (%s * %s^1) + %s * %s^2 + %s * %s ^3 + %s * %s^4 + %s* %s^5 + %s* %s^6) * 9.81";
		double tr = getTotalReductionAtStep(step)/100;
		
		String eqFormString = String.format(eqString, coeff[0], coeff[1], tr, coeff[2], tr, coeff[3], tr ,coeff[4], tr, coeff[5], tr, coeff[6], tr);
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			return 0;
		}
		
		return expr.value();
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

	public double getSpeed(final int i) {
		
		if (i == 0){
			return getTargetSpeed()*(1 - getTotalReduction()/100);
		}
		
		//From Excel G18/1-E17
		String eqString = "%s/(1-%s)";
		
		String eqFormString = String.format(eqString, getSpeed(i -1), getReduction(i));
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			Log.e("CSR", e.explain());
			return 0;
		}
		
		return expr.value();
	}
	
	public double getPull(int step) {
		//From Excel (((PI.GRECO()*(D17/2)^2)*LN((D16/D17)^2)*H17)/0.57)*0.95
		String eqString = "(((pi*(%s/2)^2)*log((%s/%s)^2)*%s)/0.57)*0.95";
		
		String eqFormString = String.format(eqString, getDiameter(step), getDiameter(step-1), getDiameter(step), getOutletTSKG(step));
		Expr expr;
		
		try {
			expr = Parser.parse(eqFormString);
		} catch (SyntaxException e) {
			Log.e("CSR", e.explain());
			return 0;
		}
		
		return expr.value();
	}
	
	public Double getPower(int step){
		
		if (powers[step] != null){
			return powers[step];
		}
		
		powers[step] = getPull(step)*getSpeed(step)/102;
		return powers[step];
	}
	
	public Double getMaxPower(){
		Double methodResult;
		methodResult = getPower(1); 
		
		for (int i = 2; i <= nOfDies; i++) {
			if (getPower(i) != null && getPower(i) > methodResult) {
				methodResult = getPower(i);
			}
		}
		
		return methodResult;
	}
	
	public Double getAveragePower(){
		
		Double methodResult = 0.0;
		int i = 1;
		do {
			methodResult = methodResult + getPower(i);
			i++;
		} while (i <= nOfDies);		
		return methodResult/(nOfDies);
		
	}
	
	public Double getVariance(){
		
		return getMaxPower() - getAveragePower();
	}

	
	
	public double getOutletTSKG(int step) {
		
		return getOutletTS(step)/9.81;
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
		dest.writeDouble(getTaperReduction());
		dest.writeByte((byte) (isTRConstant ? 1 : 0 ));
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
			sd.taperReduction = in.readDouble();
			sd.isTRConstant = in.readByte() != 0;
			return sd;
		}

		@Override
		public SteelDrawing[] newArray(int size) {

			return new SteelDrawing[size];
		}
	};

	public void isTRConstant(boolean b) {
		
		isTRConstant = b; 
	}





	
}
