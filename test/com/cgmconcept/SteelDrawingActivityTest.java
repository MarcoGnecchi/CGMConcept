package com.cgmconcept;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.cgmconcept.model.SteelDrawing;


@RunWith(RobolectricTestRunner.class)
public class SteelDrawingActivityTest {

		@Test
		public void testTotalReduction(){
			SteelDrawing sd = new SteelDrawing();
			sd.setInlet(6.50);
			sd.setOutlet(2.00);
			sd.setNOfDies(8);
			sd.setTargetSpeed(10.0);
			Assert.assertEquals(90.53254437869822, sd.getTotalReduction());
		}
	
		@Test
		public void testAverageReduction(){
			SteelDrawing sd = new SteelDrawing();
			sd.setInlet(6.50);
			sd.setOutlet(2.00);
			sd.setNOfDies(8);
			sd.setTargetSpeed(10.0);
			Assert.assertEquals(25.521802101203527, sd.getAverageReduction());
		}
		
		@Test
		public void testSpeedWireInlet(){
			SteelDrawing sd = new SteelDrawing();
			sd.setInlet(6.50);
			sd.setOutlet(2.00);
			sd.setNOfDies(8);
			sd.setTargetSpeed(10.0);
			Assert.assertEquals(0.9467455621301779, sd.getSpeedWireInlet());
		}
		
		@Test
		public void testInLetTS(){
			SteelDrawing sd = new SteelDrawing();
			sd.setInlet(6.50);
			sd.setOutlet(2.00);
			sd.setNOfDies(8);
			sd.setTargetSpeed(10.0);
			sd.setCarbonContent(0.15);
			Assert.assertEquals(540, sd.getInletTs());
		}
		
		@Test
		public void testOutLetTS(){
			SteelDrawing sd = new SteelDrawing();
			sd.setInlet(6.50);
			sd.setOutlet(2.00);
			sd.setNOfDies(8);
			sd.setTargetSpeed(10.0);
			sd.setCarbonContent(0.15);
			Assert.assertEquals(540, sd.getInletTs());
		}
}
