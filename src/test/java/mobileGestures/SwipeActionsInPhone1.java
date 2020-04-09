package mobileGestures;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class SwipeActionsInPhone1 {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4781));
		service.start();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage", "in.AajTak.headlines");
		caps.setCapability("appActivity", "com.indiatoday.ui.splash.SplashActivity");
		caps.setCapability("noReset", "true");
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4781/wd/hub"),caps);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void swipeTest() throws InterruptedException{

		Thread.sleep(16000);
// The selected element should be available in the app otherwise exception message will be thrown		
		MobileElement swipe = driver.findElement(By.id("in.AajTak.headlines:id/ll_topnews_tempLayout"));
		
		TouchAction act = new TouchAction((MobileDriver)driver);
		act.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(swipe))).moveTo(PointOption.point(400, 1100)).release().perform();
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException{
		Thread.sleep(2000);
		driver.quit();
		service.stop();
	}
}
