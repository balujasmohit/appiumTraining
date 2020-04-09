package parallelTests;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestClass1 {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@Test
	public void firstTest() throws InterruptedException, IOException{
		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4738));
		service.start();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage", "io.selendroid.testapp");
		caps.setCapability("appActivity", "HomeScreenActivity");
		caps.setCapability("udid", "HNB3NA88");
		caps.setCapability("noReset", "true");
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4738/wd/hub"),caps);
		Thread.sleep(2000);
		
		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("Parallel Test executed");
	}
}
