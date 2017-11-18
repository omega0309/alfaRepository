package com.yoigo.app.seleniumwd.pom;

import java.net.MalformedURLException;
import org.openqa.selenium.By;

import com.yoigo.app.seleniumwd.pom.driver.appYoigoDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CondicionesPage {
	
	private By botonRechazar = By.name("Rechazar");
	private By botonAceptar = By.name("Aceptar");
	private AndroidDriver driver = null;
	
	public CondicionesPage() throws MalformedURLException, InterruptedException{
		driver = appYoigoDriver.inicializarDriver();
	}

	/*Ventana para aceptar las condiciones iniciales*/
	public void aceptarCondiciones() throws Exception{	
		
// Name locator strategy is deprecated in appium 1.5, use MobileElement and UiSelector instead webElement				
//		driver.findElement(botonAceptar).click(); 
		
		MobileElement makeTextLocator = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Aceptar\")");
		makeTextLocator.click();
		Thread.sleep(3000);
		System.out.println("Condiciones aceptadas");

	}

	public void cerrarSesion(){
		appYoigoDriver.terminarSesion(driver);
	}
	
	public AndroidDriver getWebDriver() {
		return driver;
	}
}
