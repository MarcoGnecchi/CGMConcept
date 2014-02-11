package com.cgmconcept;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.cgmconcept.model.SteelDrawing;


@RunWith(RobolectricTestRunner.class)
public class SteelDrawingActivityTest {

	 @Test
	    public void shouldHaveHappySmiles() throws Exception {
	        String hello = new SteelDrawingActivity().getResources().getString(R.string.app_name);
	        assertThat(hello, equalTo("Hello World, MyActivity!"));
	    }
	 
		@Test
		public void testTotalReduction(){
			SteelDrawing sd = new SteelDrawing();
			sd.setInlet(6.50);
			sd.setOutlet(2.00);
			sd.setNOfDies(8);
			sd.setTargetSpeed(10.0);
			Assert.assertEquals("Porcodio", 90.5, sd.getmTotalReduction());
		}
	
}
