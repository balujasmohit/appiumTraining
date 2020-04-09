package mobileGestures;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DragN_Drop {

	// DragNDrop using TochAction Class
	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		
		service  = AppiumDriverLocalService
		            .buildService(new AppiumServiceBuilder()
// Provide here the location of node.exe 				
	   				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
// Provide here the location of Appium js file i.e. main.js file
		        	.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
		        	.usingPort(5777));
					service.start();
					
	   DesiredCapabilities capabilities = new DesiredCapabilities();
	   capabilities.setPlatform(Platform.ANDROID);
	   capabilities.setCapability("deviceName", "HNB3NA88");
	   capabilities.setCapability("platformVersion", "8.0.0");
	   capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
	   capabilities.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");
	   capabilities.setCapability("noReset", "true");
	   
	   driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:5777/wd/hub"), capabilities);
	}
	
	@Test
	public void dragN_DropTest() {
		
// Drag-sort demos app should be installed on the device for practice(.apk file needed to downloaded)		
		
		driver.findElements(By.id("com.mobeta.android.demodslv:id/activity_title")).get(0).click();
	//	WebDriverWait wait = new WebDriverWait(driver, 20);
		System.out.println(driver.findElements(By.id("com.mobeta.android.demodslv:id/drag_handle")).size());
		System.out.println("------------===========================================================");
		System.out.println("------------===========================================================");
		System.out.println("------------===========================================================");
		System.out.println("------------===========================================================");
		System.out.println("------------===========================================================");

		WebElement elems = driver.findElements(By.id("com.mobeta.android.demodslv:id/drag_handle")).get(0);
		
		WebElement elemms = driver.findElements(By.id("com.mobeta.android.demodslv:id/drag_handle")).get(3);
		
		Actions action  = new Actions(driver);
		
		action.dragAndDrop(elems, elemms).build().perform();
		
//		MobileElement elem1 = driver.findElements(By.id("com.mobeta.android.demodslv:id/drag_handle")).get(0);
//		
//		MobileElement elem2 = driver.findElements(By.id("com.mobeta.android.demodslv:id/drag_handle")).get(3);
//		
//		TouchAction action = new TouchAction((MobileDriver)driver);
//		
//		action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(elem1))).moveTo(PointOption.point(431, 1082)).release().perform();
			
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
		service.stop();
	}
}
