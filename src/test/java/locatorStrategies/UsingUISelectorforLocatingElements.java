package locatorStrategies;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class UsingUISelectorforLocatingElements {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	@Test
	public void appiumFirstTest() throws IOException, InterruptedException, MalformedURLException {
	
// Program to start Appium service		
			AppiumDriverLocalService service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
// Provide here the location of node.exe 				
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
// Provide here the location of Appium js file i.e. main.js file
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4744));
					
		        service.start();
		
// Setting Desired Capabilities					
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage","com.waypals");
		caps.setCapability("appActivity", "com.waypals.Login_Screen");
		caps.setCapability("noReset", "true");
		try {
// Instantiating Appium Driver		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4744/wd/hub"), caps);
		}catch(Exception e) {
			System.out.println("Server can not be launched due to the following exception: "+e);
		}
// Finding elements by Android UI Automator class		
		driver.findElementByAndroidUIAutomator("UiSelector().className(\"android.widget.EditText\").text(\"UserName\")").sendKeys("mohit@waypals.com");
		
		Thread.sleep(1000);
		
		driver.findElementByAndroidUIAutomator("UiSelector().className(\"android.widget.Button\").text(\"Login\")").click();
		driver.quit();
		
		service.stop();
	}
}
