package mobileGestures;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.offset.PointOption;

public class TapActionAndWhatappMessage {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4881));
		service.start();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage", "com.whatsapp");
		caps.setCapability("appActivity", "com.whatsapp.HomeActivity");
		caps.setCapability("noReset", "true");
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4881/wd/hub"),caps);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void tapTesting() throws InterruptedException{
		
// Explicit wait for the elements to be appeared before the execution step
		WebDriverWait wait  = new WebDriverWait(driver, 20);
		
		driver.findElement(By.id("com.whatsapp:id/menuitem_search")).click();
		driver.findElement(By.id("com.whatsapp:id/search_src_text")).sendKeys("Mohit Baluja");
// sing KeyEvent for Android keys simulation		
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		Thread.sleep(1000);
		
// Using TouchAction class for tap simulation	
		TouchAction act = new TouchAction((MobileDriver)driver);
// Tapping on screen coordinates / screen location
		act.tap(PointOption.point(366, 451)).perform();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.whatsapp:id/entry"))).sendKeys("Hello");
		driver.findElement(By.id("com.whatsapp:id/send")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.whatsapp:id/camera_btn"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.whatsapp:id/switch_camera_btn"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.whatsapp:id/shutter"))).click();
		Thread.sleep(1000);
// Tapping on screen coordinates / screen location		
		act.tap(PointOption.point(340, 1770)).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.whatsapp:id/caption"))).sendKeys("New Image");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.whatsapp:id/send"))).click();
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		
		Thread.sleep(2000);
		driver.quit();
		service.stop();
	}
}
