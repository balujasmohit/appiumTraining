package nativeAppsTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class NativeCalcTest {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		
		         service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
// Provide here the location of node.exe 				
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
// Provide here the location of Appium js file i.e. main.js file
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4767));
	
				service.start();
				
		DesiredCapabilities cps = new DesiredCapabilities();
		cps.setPlatform(Platform.ANDROID);
		cps.setCapability("deviceName", "HNB3NA88");
		cps.setCapability("paltformVersion","8.0.0");
		cps.setCapability("appPackage", "com.google.android.calculator");
		cps.setCapability("appActivity", "com.android.calculator2.Calculator");
		cps.setCapability("noReset", "true");
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4767/wd/hub"), cps);
	}
	
	@Test(priority=1)
	public void addition() throws InterruptedException {
	//	driver.findElement(By.id("com.android.launcher3:id/icon")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		
		String txt = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		
		String txt1 = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		// com.google.android.calculator:id/display
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println(txt+"="+txt1);
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
	}
	@Test(priority=2)
	public void substraction() throws InterruptedException {
	//	driver.findElement(By.id("com.android.launcher3:id/icon")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_sub")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		
		String txt = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		
		String txt1 = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		// com.google.android.calculator:id/display
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println(txt+"="+txt1);
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
	}
	@Test(priority=3)
	public void multiplication() throws InterruptedException {
	//	driver.findElement(By.id("com.android.launcher3:id/icon")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		
		String txt = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		
		String txt1 = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		// com.google.android.calculator:id/display
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println(txt+"="+txt1);
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
	}
	
	@Test(priority=4)
	public void division() throws InterruptedException {
	//	driver.findElement(By.id("com.android.launcher3:id/icon")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_div")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		
		String txt = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		
		String txt1 = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		// com.google.android.calculator:id/display
		
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println(txt+"="+txt1);
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException{
		Thread.sleep(2000);
		driver.quit();
		service.stop();
	}
}
