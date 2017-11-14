package com.yoigo.app.seleniumwd.pom;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yoigo.app.seleniumwd.pom.driver.appYoigoDriver;

import io.appium.java_client.android.AndroidDriver;


public class IniciarSesionPage {
	private By botonIngresar = By.id("net.bfgnet.miandroigo:id/wbut_login");
	private By botonAceptar = By.id("com.yoigo.movil:id/btnPositive");
	private By botonPermitir = By.id("com.android.packageinstaller:id/permission_allow_button");
	private By cajaUsuario = By.id("net.bfgnet.miandroigo:id/wlogin_user");
	private By cajaClave = By.id("net.bfgnet.miandroigo:id/wlogin_pass");
	private By mensajeRespuesta = By.id("com.yoigo.movil:id/txtMessage");
	private By mensajeBienvenida = By.id("net.bfgnet.miandroigo:id/summary_app_name");
	private AndroidDriver driver = null;
	
	public IniciarSesionPage() throws MalformedURLException, InterruptedException{
		driver = appYoigoDriver.inicializarDriver();
	}

	public String iniciarSesion(String usuario, String clave) throws Exception{	
/*		if (isAlertPresent()) {
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
		}*/		
//		boolean present;
/*		try {
		   driver.findElement(botonPermitir).click();
		   Thread.sleep(3000);
//		   present = true;
		} catch (NoSuchElementException e) {
//		   present = false;
		}
		driver.findElement(botonPermitir).click();
		Thread.sleep(3000);*/
		driver.findElement(cajaUsuario).clear();
		driver.findElement(cajaUsuario).sendKeys(usuario);
		driver.findElement(cajaClave).clear();
		driver.findElement(cajaClave).sendKeys(clave);
		driver.findElement(botonIngresar).click();
		Thread.sleep(3000);
		/*findElements will return an empty list if no matching elements are found instead of an exception
		This will return true if at least one element is found and false if it does not exist.
		there's an implicit 3 second wait if no elements are found which can be switched on/off*/
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		Boolean isPresent = driver.findElements(mensajeRespuesta).size() > 0;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		/*Captura el mensaje de respuesta en los escenarios de error*/
		if (isPresent) {
			String mensaje= driver.findElement(mensajeRespuesta).getText();
			driver.findElement(botonAceptar).click();
			return mensaje;
		/*si no hay error captura el mensaje de bienvenida*/	
		}
		else {
			return driver.findElement(mensajeBienvenida).getText();
		}
	}
	
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   // isAlertPresent()
	
	public boolean isPermisoAlertPresent() 
	{ 
	    try 
	    { 
	    	driver.findElement(botonAceptar); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   // isAlertPresent()
	
	
	public void cerrarSesion(){
		appYoigoDriver.terminarSesion(driver);
	}
	
	public AndroidDriver getWebDriver() {
		return driver;
	}
	
}
