package sauceLabsTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class AndoroidTests {
	
	WebDriver driver;
	
	// https://github.com/appium-boneyard/sample-code/blob/master/sample-code/apps/selendroid-test-app.apk
	
	@SuppressWarnings("rawtypes")
	@Test
	public void main() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities caps = DesiredCapabilities.android();
		caps.setCapability("appiumVersion", "1.16.0");
		caps.setCapability("deviceName","Samsung Galaxy S9 HD GoogleAPI Emulator");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("browserName", "");
		caps.setCapability("platformVersion","8.0");
		caps.setCapability("platformName","Android");
		caps.setCapability("app","https://drive.google.com/file/d/1VVTrqBvP-msEXeMsAw7sI_xznB67RXfq/view?usp=sharing/waypals.apk");
		
		driver = new AndroidDriver(new URL("https://balujasmohit:480d5e6c-cba8-45a7-a91c-6411201e66bf@ondemand.saucelabs.com:443/wd/hub"), caps);

		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		
		Thread.sleep(10000);

		driver.quit();
	}
}
