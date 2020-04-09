package locatorStrategies;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ToastNotificationReading {

	public static AndroidDriver<MobileElement> driver;
	public AppiumDriverLocalService service;
	public static String destDir;
	public static DateFormat dateFormat;
	public static String scrPath;
   	
	@BeforeMethod
	public void setUp() throws InterruptedException, MalformedURLException{
		
		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\Appium\\node.exe"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4783));
		service.start();
		
		DesiredCapabilities caps  = new DesiredCapabilities();
		caps.setPlatform(Platform.ANDROID);
		caps.setCapability("deviceName", "HNB3NA88");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage", "com.waypals");
		caps.setCapability("appActivity", "com.waypals.Login_Screen");
		caps.setCapability("noReset", "true");
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4783/wd/hub"),caps);	
	}
	
	@Test
	public void toastVerification() throws InterruptedException, IOException, TesseractException{
		
		WebDriverWait wait  = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.waypals:id/username_et"))).sendKeys("demo@mailinator.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.waypals:id/password_et"))).sendKeys("waypals");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.waypals:id/login_btn"))).click();
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.waypals:id/text_immobilizer_onOff"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys("waypals");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.waypals:id/btn_vehicle_on"))).click();
		
		Thread.sleep(500);
		
		takeScreenshot();
		String Text = OCR(scrPath);
		System.out.println(Text);
	}
	
	public static void takeScreenshot() throws IOException {
		destDir = "screenshots";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		new File(destDir).mkdir();
		String destFile = dateFormat.format(new Date()) + ".png";
		
		try {
			   // Copy paste file at destination folder location
			   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			   scrPath = destDir + "/" + destFile;
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		
	}
	
	public static String OCR(String ImagePath) throws TesseractException {
		
		 String result = null;
		  File imageFil = new File(ImagePath);
	      ITesseract instance = new Tesseract(); 
	      instance.setDatapath("src/main/resources");
	      instance.setLanguage("eng");
	      try {
	          result = instance.doOCR(imageFil);
	     
	      } catch (TesseractException e) {
	          System.err.println(e.getMessage());
	      }
		return result;
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
		service.stop();	
	}
}
