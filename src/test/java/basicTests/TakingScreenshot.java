package basicTests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TakingScreenshot {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@Test
	public void takinScreenshots() throws InterruptedException, IOException{
		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4726));
		service.start();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage", "com.waypals");
		caps.setCapability("appActivity", "Login_Screen");
		caps.setCapability("noReset", "true");
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4726/wd/hub"),caps);
		Thread.sleep(2000);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String destFile = date.format(new Date())+".png";
		String destDir = "D:\\NewBackUp\\TestAppium\\screenshots";
		FileUtils.copyFile(scrFile, new File(destDir+"/"+destFile));
		
		Thread.sleep(3000);
		driver.quit();
		service.stop();
	}
}
