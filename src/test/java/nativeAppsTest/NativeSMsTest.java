package nativeAppsTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class NativeSMsTest {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException, InterruptedException {
		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
// Provide here the location of node.exe 				
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
// Provide here the location of Appium js file i.e. main.js file
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4685));
	
			service.start();
		
		DesiredCapabilities caps1 = new DesiredCapabilities();
		caps1.setPlatform(Platform.ANDROID);
		caps1.setCapability("deviceName", "HNB3NA88");
		caps1.setCapability("platformVersion", "8.0.0");
		caps1.setCapability("appPackage", "com.google.android.apps.messaging");
		caps1.setCapability("appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
		caps1.setCapability("noReset","true");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4685/wd/hub"), caps1);
	}
	
	@Test
	public void smsTest() throws InterruptedException {
		
		String sms = "com.google.android.apps.messaging:id/start_new_conversation_button";
		String enterNum = "com.google.android.apps.messaging:id/recipient_text_view";
		String enterMessage = "com.google.android.apps.messaging:id/compose_message_text";
		String sendBtn = "com.google.android.apps.messaging:id/send_message_button_icon";

		WebDriverWait wait  = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(sms))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(enterNum))).sendKeys("9811741861");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(enterMessage))).sendKeys("This is a test SMS by Appium Automation");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(sendBtn))).click();
		Thread.sleep(3000);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
