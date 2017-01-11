package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
	public enum ExcelType{
		XLS,
		XLSX
	}


	public static List<List<String>> leerArchivo(String nomArchivo, int hoja) throws IOException {

		List<List<String>> datosExcel = new ArrayList<List<String>>();
		datosExcel = getDatosHojaExcel(nomArchivo, hoja, ExcelType.XLS);

		return datosExcel;
	}

	public static List<List<String>> leerArchivoXLSX(String nomArchivo, int hoja) throws IOException {

		List<List<String>> datosExcel = new ArrayList<List<String>>();
		datosExcel = getDatosHojaExcel(nomArchivo, hoja, ExcelType.XLSX);

		return datosExcel;

	}

	/**
	 * Function: getDatosHojaExcel Description: Retorna lista con los datos
	 * extraidos desde el archivo excel
	 * 
	 * @param nombreDatapool Nombre del archivo que se desea leer
	 *            (String)
	 * @param nrohoja Numero de la hoja que se desea leer
	 * @param tipo Tipo del archivo que se desea leer
	 * @return List<List<String>>
	 * @throws IOException
	 **/
	private static List<List<String>> getDatosHojaExcel(String nombreDatapool, int nrohoja, ExcelType tipo) throws IOException {

		// Crea un ArrayList donde almacenara la data leida desde la hoja
		// del archivo excel
		List<List<String>> datosExcel = new ArrayList<List<String>>();
		// String curDir = System.getProperty("user.dir");

		try {
			// Crea un FileInputStream para leer el archivo excel
			// FileInputStream archivo = new FileInputStream(url.getFile());
			InputStream archivo = Excel.class.getResourceAsStream("/datapool/" + nombreDatapool);
			// Create un libro excel desde el archivo.
			Workbook libro = createWorkBook(archivo, tipo);

			// Obtiene la hoja a leer desde el excel
			Sheet sheet = libro.getSheetAt(nrohoja);

			datosExcel = leerHoja( sheet);
			libro.close();
		} catch (Exception e) {
			System.out.println("Exception getDatosHojaExcel: " + e);
		}
		return datosExcel;
	}

	private static List<List<String>> leerHoja(Sheet sheet) {
		List<List<String>> datosExcel = new ArrayList<List<String>>();
		// Formateo de celdas
		DataFormatter formatter = new DataFormatter();

		// Recorre las filas de la hoja
		for (int rowNumber = sheet.getFirstRowNum(); rowNumber <= sheet.getLastRowNum(); rowNumber++) {
			Row row = sheet.getRow(rowNumber);
			List<String> data = new ArrayList<String>();
			if (row != null) {
				// Recorre las celdas de la fila
				for (int cellNumber = row.getFirstCellNum(); cellNumber <= row.getLastCellNum(); cellNumber++) {
					Cell cell = row.getCell(cellNumber);
					// Si la celda esta en blanco guardar el valor ""
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						data.add("");
					} else {
						// Si la celda no esta vacía agregar valor a la
						// lista
						data.add(formatter.formatCellValue(cell));
					}
				}
			}
			// Almacena la lista con valores de la fila dentro de otra lista
			datosExcel.add(data);
		}
		return datosExcel;
	}

	private static Workbook createWorkBook(InputStream input,ExcelType tipo) throws IOException{
		switch (tipo) {
		case XLS:
			return new HSSFWorkbook(input);
		case XLSX:
			return new XSSFWorkbook(input);
		default:
			return null;
		}
		
	}
}
