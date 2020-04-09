package basicTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidDriver;

public class SelendroidTestAppium {

	@Test
	public void selendroidAppTest() throws Exception{
	
		SelendroidCapabilities caps = new SelendroidCapabilities("io.selendroid.testapp:0.17.0");
		
		WebDriver driver  = new SelendroidDriver(caps);
		
		driver.findElement(By.id("")).clear();
		
		
	}
	
	
	
	
}
