package parallelTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GridTest1 {

	DesiredCapabilities caps = new DesiredCapabilities();
	
	public RemoteWebDriver driver;
	String URL;
	
	@Parameters("DeviceId")
	@BeforeTest
	public void setUp(String DeviceId) throws MalformedURLException {
		
		if(DeviceId.equalsIgnoreCase("HNB3NA88")) {
			setCaps("com.waypals", "Login_Screen",DeviceId);
			caps.setCapability("platformVersion", "8.0.0");
		}
		
		if(DeviceId.equalsIgnoreCase("192.168.148.102:5555")) {
			setCaps("com.waypals", "Login_Screen",DeviceId);
			caps.setCapability("paltformVersion", "6.0");
		}	
		
		URL = "http://192.168.1.207:4444/wd/hub";
		driver = new RemoteWebDriver(new URL(URL), caps);
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void setCaps(String appPackage, String appActivity,String DeviceId) {
		
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("automationName", "Appium");
		caps.setCapability("deviceName", DeviceId);
		caps.setCapability("appPackage", appPackage);
		caps.setCapability("appActivity", appActivity);
		caps.setCapability("udid", DeviceId);
		caps.setCapability("noReset", "true");
	}
	
	@Test
	public void firstTest() throws InterruptedException {
		
        Thread.sleep(40000);
		
		driver.findElement(By.id("com.waypals:id/username_et")).sendKeys("Parallel Test executed");
	}
	
	@Test
	public void secondTest() throws InterruptedException{
     
		Thread.sleep(40000);
		
		driver.findElement(By.id("com.waypals:id/username_et")).sendKeys("Parallel Test executed");
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.quit();
	}
}
