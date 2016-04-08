package com.alibaba.selemiumer.seleniumer;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.alibaba.selemiumer.utils.*;

public class SeleniumTest extends ExcelPatch {

	WebDriver driver;
	private static final String dataPoolPath = "classpath:TestCase\\TestData.xlsx";
	
	@BeforeMethod
	public void beforeMethod() throws Exception {

//	        ProfilesIni allProfiles = new ProfilesIni();
//			FirefoxProfile profile = allProfiles.getProfile("selenium");		
//			System.setProperty("webdriver.firefox.bin","D:\\tools\\firefox\\firefox.exe");		   
//		    driver = new FirefoxDriver(profile);
//		    driver.manage().window().maximize();
//		    Navigation navigation = driver.navigate();
//		    navigation.to("http://www.store.demoqa.com");
//		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability("chrome.switches", Arrays.asList("--incognito"));
		ChromeOptions options =new ChromeOptions();
		options.addArguments("--test-type");
		cap.setCapability("chrome.binary", "src/ucBrowserDrivers/chromedriver.exe");
		cap.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(cap);
        driver.manage().window().maximize();
        driver.get("http://www.store.demoqa.com");
        
        
	}

	@Test(dataProvider = "excel")
	@DataFile(path=dataPoolPath,sheetName="Sheet1")
	public void Registration_data(String sUserName, String sPassword) throws Exception {

		driver.findElement(By.xpath(".//*[@id='account']/a")).click();

		driver.findElement(By.id("log")).sendKeys(sUserName);

		System.out.println(sUserName);

		driver.findElement(By.id("pwd")).sendKeys(sPassword);

		System.out.println(sPassword);
	}

	@AfterMethod
	public void afterMethod() {

		driver.close();

	}

}

