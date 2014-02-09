package com.cgmconcept.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;


public class SteelDrawingTest {
	
	
	
	
	@BeforeClass
	public static void testSetup(){
	}
	
	@Test
	public void testTotalReduction(){
		SteelDrawing sd = new SteelDrawing();
		sd.setInlet(6.50);
		sd.setOutlet(2.00);
		sd.setNOfDies(8);
		sd.setmTargetSpeed(10.0);
		assertEquals(25.5, sd.getAverageReduction(), 0);
	}
}
