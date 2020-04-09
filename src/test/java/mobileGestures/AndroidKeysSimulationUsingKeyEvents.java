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

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AndroidKeysSimulationUsingKeyEvents {

// This class contains the Test Method from which we will tap on Android native key(e.g. ENTER key and DEL(Backspace) Key) 
	
//Declaring public AndroidDriver reference with generic type MobileElement 	
	public AndroidDriver<MobileElement> driver;
//Declaring AndroidDriverLocalService reference
	public AppiumDriverLocalService service;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException, InterruptedException {
// Instantiating AndroidDriverLocalService to start the Appium Server programmatically
// We need to give node.exe and Appium Js file path 
// we can specify the port number as well here		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
// Provide here the location of node.exe 						
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
// Provide here the location of Appium js file i.e. main.js file				
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(3345));
	
				service.start();
// Declaring and initializing DesiredCapabilities class			
				DesiredCapabilities capab = new DesiredCapabilities();
// Defining the desired capabilities object keys and values			
				capab.setPlatform(Platform.ANDROID);
				capab.setCapability("deviceName", "HNB3NA88");
				capab.setCapability("platformVersion", "8.0.0");
				capab.setCapability("appPackage", "com.google.android.apps.messaging");
				capab.setCapability("appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
// Note: App Package and App Activity and the located elements may vary because of different make and models of mobile devices.
				
				capab.setCapability("noReset", "true");
// Instantiating Appium Server			
				driver  = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:3345/wd/hub"),capab);
	}
	
// Test Method for KeyEvent testing	
	
	@Test
	public void keyEventTest() throws InterruptedException {
// Locating the Mobile elements by using locator as id.		
		driver.findElement(By.id("com.google.android.apps.messaging:id/start_new_conversation_button")).click();
		
		driver.findElement(By.id("com.google.android.apps.messaging:id/recipient_text_view")).sendKeys("9811741861");

// Simulating the native ENTER key of phone by using AndroidDriver native KeyEvent class		
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		
		driver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys("The Keyevent test is in the testing process");
// Using the ENTER key		
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
// Using the DEL(BackSpace) Key		
		driver.pressKey(new KeyEvent(AndroidKey.DEL));
// Used forced Pause to view the  		
		Thread.sleep(1000);
	}
	
// Killing the Session	
	
	@AfterMethod
	public void tearDown() throws InterruptedException{
		Thread.sleep(2000);
		driver.quit();
		service.stop();
	}
}