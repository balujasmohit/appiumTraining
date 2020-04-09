package pageFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class PageFactoryClass {

	public AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	
	DesiredCapabilities caps = new DesiredCapabilities();
	
	@AndroidFindBy(id="com.waypals:id/username_et")
	public WebElement username;
	
	@AndroidFindBy(id="com.waypals:id/password_et")
	public WebElement password;
	
	@AndroidFindBys({
		@AndroidBy(id="com.waypals:id/password_et"),
		@AndroidBy(id="com.waypals:id/password_et")
	})
	public List<WebElement> elems;
	
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\node.exe"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4723));
		service.start();
		
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage", "com.waypals");
		caps.setCapability("appActivity", "com.waypals.Login_Screen");
		caps.setCapability("noReset", "true");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
	}
	
	public void functions() {
		PageFactory.initElements(new AppiumFieldDecorator(driver, null), this);
		username.sendKeys("mohit@waypals.com");
		password.sendKeys("newTextPassword");
	}
	
	@Test
	public void firstPageFactorytest() throws InterruptedException{
		Thread.sleep(5000);
		functions();
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException{
		
		Thread.sleep(3000);
		driver.quit();
		service.stop();
	}
}
