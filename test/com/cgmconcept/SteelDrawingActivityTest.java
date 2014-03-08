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
		public void totalReduction(){
			
			Assert.assertEquals(90.53254437869822, sd.getTotalReduction());
		}
	
		@Test
		public void averageReduction(){
			
			Assert.assertEquals(25.521802101203527, sd.getAverageReduction());
		}
		
		@Test
		public void speedWireInlet(){
			
			Assert.assertEquals(0.9467455621301779, sd.getSpeedWireInlet());
		}
		
		@Test
		public void inLetTS(){
			
			Assert.assertEquals(441.45, sd.getInletTS());
		}
		
		@Test
		public void outLetTS(){
			
			Assert.assertEquals(940.804810901621, sd.getOutletTS(sd.getNOfDies()));
		}
		
		@Test
		public void outLetTSforStep(){
			
			Assert.assertEquals(761.4740397964387, sd.getOutletTS(3));
		}
		
		@Test
		public void carbonContent(){
			
			Assert.assertEquals(0.15,sd.getCarbonContent());
		}
		
		@Test
		public void diameter(){
			
			Assert.assertEquals(3.9222090827776745,sd.getDiameter(3));
		}
		
		@Test
		public void totalReductionAtSomeStep(){
			
			Assert.assertEquals(63.5888187242043, sd.getTotalReductionAtStep(3));			
		}
		
		@Test
		public void totalReductionShouldMatchReductionAtFinalStep(){
			
			Assert.assertEquals(sd.getTotalReduction(), sd.getTotalReductionAtStep(8));
		}
		
		@Test
		public void speedAtFirstStep(){
			
			Assert.assertEquals(0.9467455621301779, sd.getSpeed(0));			
		}
		
		@Test
		public void speedAtStep(){
			
			Assert.assertEquals(1.3483481692859427, sd.getSpeed(1));			
		}
		
		@Test
		public void tensileStrenght(){
			
		}
}
