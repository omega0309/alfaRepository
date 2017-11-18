package com.yoigo.app.seleniumwd;

import com.yoigo.app.seleniumwd.pom.CondicionesPage;
import com.yoigo.app.seleniumwd.pom.IniciarSesionPage;
import com.yoigo.app.util.Excel;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

public class LoginYoigoTest {
 
	private CondicionesPage condicionesPage;
	private IniciarSesionPage iniciarSesionPage;
	
	@BeforeTest
	public void inicioClase() throws Exception {
		condicionesPage =new CondicionesPage();
		iniciarSesionPage =new IniciarSesionPage(condicionesPage.getWebDriver());		
	}

	@DataProvider(name = "datosEntrada")
	public static Object[][] datosPoblados(ITestContext context, ITestNGMethod testContext) {
		Object[][] datos = null;
		List<String[][]> datosTotal = new ArrayList<String[][]>();
		String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivo");
		System.out.println("Ruta del archivo de datos: " + rutaArchivo);
		if(testContext.getMethodName().equals("login")){
			datosTotal= Excel.leerExcel(rutaArchivo);
			datos= datosTotal.get(0);
		}
		else if(testContext.getMethodName().equals("registro")){
			datosTotal= Excel.leerExcel(rutaArchivo);
			datos= datosTotal.get(1);
		}
		return datos;
	}
	
	@Test (dataProvider = "datosEntrada")
	public void login(String usuario, String clave, String valorEsperado) throws Exception {
		try {
			condicionesPage.aceptarCondiciones();
			String valorObtenido = iniciarSesionPage.iniciarSesion(usuario, clave);
			Assert.assertEquals(valorEsperado , valorObtenido);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		iniciarSesionPage.cerrarSesion();
	}
		
}
