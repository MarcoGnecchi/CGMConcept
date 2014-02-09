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
		System.out.println("PUTA");
		assertEquals("10 x 5 must be 50", 50, 10*5);
	}
}
