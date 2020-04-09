package nativeAppsTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Dialing_Phone_Number {
// Declaring Class Variables
	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@Test
	public void appiumsecondTest() throws IOException, InterruptedException {
// 	Instantiating AppiumDriverLocalService	
		         service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
// Provide here the location of node.exe 				
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
// Provide here the location of Appium js file i.e. main.js file
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingAnyFreePort()
				.withLogFile(new File("D:\\appiumLogs13.txt")));
					
		        service.start();
// Declaring and Instantiating DesiredCapabilities					
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage","com.motorola.launcher3");
		caps.setCapability("appActivity", "com.android.launcher3.CustomizationPanelLauncher");
		caps.setCapability("noReset", "true");
// Instantiating Appium Server		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
//Finding Mobile Elements with dynamic xpath		
		driver.findElement(By.xpath("//android.view.ViewGroup/android.widget.TextView[@content-desc='Phone']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
// Simulating Phone dialing activity		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.dialer:id/floating_action_button"))).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.dialer:id/one"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.dialer:id/one"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.dialer:id/one"))).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.dialer:id/dialpad_floating_action_button"))).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.dialer:id/incall_end_call"))).click();
		
		Thread.sleep(1000);
		
// Simulating back button of Mobile Device		
		driver.navigate().back();
		
		Thread.sleep(1000);
// Quitting the Session and Stopping Appium Service	
		driver.quit();
		service.stop();
	}
}
