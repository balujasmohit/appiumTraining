package parallelTests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class ParallelTests2{

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	DesiredCapabilities caps = new DesiredCapabilities();
	
	@DataProvider(parallel=true)
	public Object[][] getData() {
		
		Object[][] data = new Object[2][5];
		
		data[0][0] =  4723;
		data[0][1] = "HNB3NA88";
		data[0][2] = "8.0.0";
		data[0][3] = "com.waypals";
		data[0][4] = "com.waypals.Login_Screen";
		
		data[1][0] =  4726;
		data[1][1] = "192.168.148.102:5555";
		data[1][2] = "6.0";
		data[1][3] = "com.waypals";
		data[1][4] = "com.waypals.Login_Screen";

		return data;
	}
	
	@Test(dataProvider="getData")
	public void testNew(int port, String deviceId, String version, String Package, String Activity) throws MalformedURLException, InterruptedException {
		
		if(port==4723) {
//		service = AppiumDriverLocalService
//				.buildService(new AppiumServiceBuilder()
//				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\node.exe"))
//				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
//				.usingPort(port));
//		service.start();
		
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", deviceId);
		caps.setCapability("paltformVersion", version);
		caps.setCapability("appPackage", Package);
		caps.setCapability("appActivity", Activity);
		caps.setCapability("udid", "HNB3NA88");
		caps.setCapability("noReset", "true");
		
		}	
		
		else if(port==4726) {
//			service = AppiumDriverLocalService
//					.buildService(new AppiumServiceBuilder()
//					.usingDriverExecutable(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\node.exe"))
//					.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
//					.usingPort(port));
//			service.start();
			
			caps.setPlatform(Platform.ANDROID);
			caps.setCapability("deviceName", deviceId);
			caps.setCapability("paltformVersion", version);
			caps.setCapability("appPackage", Package);
			caps.setCapability("appActivity", Activity);
			caps.setCapability("udid", "192.168.148.102:5555");
			caps.setCapability("noReset", "true");
			
		}
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+port+"/wd/hub"), caps);
		Thread.sleep(20000);
		
    //    WebDriverWait wait = new WebDriverWait(driver, 30);
    //    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.waypals:id/username_et"))).sendKeys("mohit@waypals.com");
		driver.findElement(By.id("com.waypals:id/username_et")).sendKeys("Harshali Baluja");
	
		Thread.sleep(5000);
	
		driver.quit();
		service.stop();	
	}	
}