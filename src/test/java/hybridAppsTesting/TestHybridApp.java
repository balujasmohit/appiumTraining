package hybridAppsTesting;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestHybridApp {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@Test
	public void hybridAppTesting() throws InterruptedException, MalformedURLException{
		
	service = AppiumDriverLocalService
			.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
					.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
					.usingPort(4727));	
	service.stop();
	service.start();
	
	DesiredCapabilities caps = new DesiredCapabilities();
	
	caps.setPlatform(Platform.ANDROID);
	caps.setCapability("deviceName", "HNB3NA88");
	caps.setCapability("platformVersion","8.0.0");
	caps.setCapability("appPackage", "com.snc.test.webview2");
	caps.setCapability("appActivity", "com.snc.test.webview.activity.SplashActivity");
	caps.setCapability("noReset","true");
	
	driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4727/wd/hub"), caps);
	Thread.sleep(5000);
	
	driver.findElement(By.className("android.widget.ImageButton")).click();
	Thread.sleep(500);
	
	driver.findElement(By.id("com.snc.test.webview2:id/design_menu_item_text")).click();
	Thread.sleep(500);
	
	driver.findElement(By.id("android:id/button1")).click();
	Thread.sleep(2000);
	
	  Set<String> contextNames = driver.getContextHandles();
	  
	  for (String contextName : contextNames) 
	  { 
	  System.out.println(contextName); 
	  }
	 	
	driver.context("WEBVIEW_com.snc.test.webview2");
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//input[@class='gLFyf']")).sendKeys("Covind19 India");
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//button[@class='Tg7LZd']")).click();
	Thread.sleep(1000);
	
	driver.quit();
	service.stop();
	}
}
