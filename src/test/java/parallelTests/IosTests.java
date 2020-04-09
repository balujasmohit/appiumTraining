package parallelTests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IosTests {
	
	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	DesiredCapabilities caps = new DesiredCapabilities();
	
	@Parameters({"DeviceId", "Version", "Port"} )
	@BeforeMethod
	public void setup(String DeviceId, String Version, int Port) throws InterruptedException, MalformedURLException{
		
		if(DeviceId.equalsIgnoreCase("HNB3NA88")) {
			setCaps("com.waypals", "Login_Screen", DeviceId, Version);
		}
		
		if(DeviceId.equalsIgnoreCase("192.168.148.102:5555")) {
			setCaps("com.waypals", "Login_Screen", DeviceId, Version);
		}
		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\node.exe"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(Port));
		service.start();
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+Port+"/wd/hub"), caps);
	}
	
	public void setCaps(String appPackage, String appActivity, String Deviceid, String version) {
		
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", Deviceid);
		caps.setCapability("platformVersion", version);
		caps.setCapability("appPackage", appPackage);
		caps.setCapability("appActivity", appActivity);
		caps.setCapability("noReset", "true");
	}
	
	@Test
	public void test1() throws InterruptedException, MalformedURLException{
		
		Thread.sleep(6000);
		
		driver.findElement(By.id("com.waypals:id/username_et")).sendKeys("Harshali Baluja");
	
		Thread.sleep(15000);
	
		driver.quit();
		service.stop();
	}
}
