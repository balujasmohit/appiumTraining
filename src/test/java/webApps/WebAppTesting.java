package webApps;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class WebAppTesting {

	public AndroidDriver<MobileElement> driver;
	
	public AppiumDriverLocalService service;
	
	@BeforeTest
	public void setUp() throws InterruptedException, MalformedURLException{
		
		service  = AppiumDriverLocalService
				   .buildService(new AppiumServiceBuilder()
				   .usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
				   .withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				   .usingPort(4723));
		service.start();
	
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		
		driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
	}
	
	@Test
	public void webAppsTesting ()throws InterruptedException{
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
		
		Thread.sleep(7000);
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("mohitbaluja@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("ajwaypals");
		Thread.sleep(3000);
		driver.quit();
		service.stop();
	}
}
