package com.yoigo.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Excel {

	
	public static List<String[][]> leerExcel(String rutaArchivo) {
		String[][] lista = null;
		List<String[][]> listaTotal = new ArrayList<String[][]>();
//		int i = 0;
		try {
			FileInputStream archivo= new FileInputStream(new File(rutaArchivo));
			HSSFWorkbook archivoExcel = new HSSFWorkbook(archivo);	
			//crear el arreglo con el tamaño de hojas
			//lista= new String[archivoExcel.getNumberOfSheets()][][];
			//Leer el excel	
			for (int sheetPos = 0; sheetPos < archivoExcel.getNumberOfSheets(); sheetPos++){
				// Indicar la hoja
				HSSFSheet hojaExcel = archivoExcel.getSheetAt(sheetPos);
				// obtener las filas
				Iterator <Row> filas= hojaExcel.iterator();
				//salto la primera fila porque es informativa
				filas.next();
				//Recorrer cada una de las filas
				lista= new String[hojaExcel.getLastRowNum()][];
				int i = 0; //nos posicionamos en la primera fila
				while (filas.hasNext()) {
					HSSFRow filaActual = (HSSFRow) filas.next(); //saltamos la primera fila por que solo es informativa.
					Iterator<Cell> celdas = filaActual.cellIterator();
					lista[i]= new String[filaActual.getLastCellNum()];
					while(celdas.hasNext()){
						HSSFCell celda = (HSSFCell) celdas.next();
						String value = "";
			            switch (celda.getCellType()) {
			                case HSSFCell.CELL_TYPE_STRING:
			                    value = celda.getStringCellValue();
			                    break;
	
			                case HSSFCell.CELL_TYPE_BLANK:
			                    value = "";
			                    break;
			                default:
			                    break;
			            }
						
						lista[i][celda.getColumnIndex()]= value;
					}
					i++; //siguiente fila
				}
				listaTotal.add(lista);

			} //end For
			archivoExcel.close();
			archivo.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return listaTotal;
	}
}
