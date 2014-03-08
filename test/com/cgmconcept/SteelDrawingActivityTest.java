package com.cgmconcept;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.cgmconcept.model.SteelDrawing;


@RunWith(RobolectricTestRunner.class)
public class SteelDrawingActivityTest {
		
		SteelDrawing sd;
	
		@Before
		public void setup(){
			this.sd = new SteelDrawing();
			this.sd.setInlet(6.50);
			this.sd.setOutlet(2.00);
			this.sd.setNOfDies(8);
			this.sd.setTargetSpeed(10.0);
			this.sd.setCarbonContent(0.15);
			this.sd.setTapeReduction(21.0);
		}
	
		@Test
		public void testTotalReduction(){
			
			Assert.assertEquals(90.53254437869822, sd.getTotalReduction());
		}
	
		@Test
		public void testAverageReduction(){
			
			Assert.assertEquals(25.521802101203527, sd.getAverageReduction());
		}
		
		@Test
		public void testSpeedWireInlet(){
			
			Assert.assertEquals(0.9467455621301779, sd.getSpeedWireInlet());
		}
		
		@Test
		public void testInLetTS(){
			
			Assert.assertEquals(441.45, sd.getInletTs());
		}
		
		@Test
		public void testOutLetTS(){
			
			Assert.assertEquals(940.804810901621, sd.getOutletTs());
		}
		
		@Test
		public void testCarbonContent(){
			
			Assert.assertEquals(0.15,sd.getCarbonContent());
		}
		
		@Test
		public void testDiameter(){
			
			Assert.assertEquals(3.9222090827776745,sd.getDiameter(3));
		}
		
		@Test
		public void testTotalReductionAtSomeStep(){
			
			Assert.assertEquals(63.5888187242043, sd.getTotalReductionAtStep(3));			
		}
}
