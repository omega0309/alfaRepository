package com.yoigo.app.seleniumwd.pom.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

public final class appYoigoDriver {
	
	

	public final static AndroidDriver inicializarDriver() throws MalformedURLException, InterruptedException {		
		AndroidDriver driver= null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "AXS0216316002150");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
//		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "net.bfgnet.miandroigo");
		capabilities.setCapability("appActivity", "net.bfgnet.miandroigo.activities.MainViewActivity");
//		capabilities.setCapability("autoGrantPermissions","true");
		capabilities.setCapability("autoAcceptAlerts", true);

		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public final static void terminarSesion(WebDriver webDriver) {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

}
