package basicTests;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BasicTestAppium {
// Declaring Class variables	
	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@Test
	public void testOne() throws InterruptedException, IOException {
// Waypals app should be installed on Phone(App is available at Google Play Store and iOS App Store)		
		
// Instantiating AppiumDriverLocalService and saving the logs in a txt file		
		         service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
// Provide here the location of node.exe 				
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
// Provide here the location of Appium js file i.e. main.js file
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4747)
				.withLogFile(new File("D:\\appiumLogs3.txt")));
			service.start();
			
// Declaring and Instantiating DesiredCapabilities		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setPlatform(Platform.ANDROID);
		cap.setCapability("deviceName", "HNB3NA88");
		cap.setCapability("platformVersion", "8.0.0");
		cap.setCapability("appPackage", "com.waypals");
		cap.setCapability("appActivity", "com.waypals.Login_Screen");
		cap.setCapability("noReset","true");
		
// Instantiating Appium Server		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4747/wd/hub"), cap);
// Defining WebDriverWait - Explicit wait		
		WebDriverWait wait = new WebDriverWait(driver, 30);
// Selecting the Mobile Element with explicit wait		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.Button"))).click();
		Thread.sleep(5000);
	}

// Ending the Appium session
	@AfterTest
	public void tearDownServer() throws InterruptedException{
		driver.quit();
		service.stop();
	}
}
