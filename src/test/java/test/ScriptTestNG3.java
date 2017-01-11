package test;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.TestgroupCabeceraPage;
import pages.TestgroupContactanosPage;
import util.Excel;
import util.TestBase;

public class ScriptTestNG3 extends TestBase {
	static List<List<String>> excelArchivo;
	private TestgroupCabeceraPage cabeceraPage;
	private TestgroupContactanosPage contactanosPage;

	@Test
	public void validarMenuCabecera() {
		cabeceraPage = new TestgroupCabeceraPage(driver);
		String msj = "";
		try {
			msj = cabeceraPage.validarElementosMenu();
			if (msj.equals("Ok")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail(msj);
			}
		} catch (Exception e) {
			Assert.fail("Ocurrio un problema inexperado " + e);
		}
	}

	@Test(priority = 1)
	public void clicMenuContactenos() {
		try {
			cabeceraPage.clicMenuContactenos();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Se presento un problema " + e);
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void rellenarCamposContactenos() throws IOException, InterruptedException {
		String nombre = "";
		String apellido = "";
		String correo = "";
		excelArchivo = Excel.leerArchivo("ScriptTestNG3.xls", 0);

		contactanosPage = new TestgroupContactanosPage(driver);
		String msj = "";
		try {
			for (int i = 1; i < excelArchivo.size(); i++) {
				List<?> list = (List<?>) excelArchivo.get(i);
				nombre = list.get(0).toString();
				apellido = list.get(1).toString();
				correo = list.get(2).toString();
			}
			Thread.sleep(2000);

			msj = contactanosPage.rellenarCamposContacto(nombre, apellido, correo);
			if (msj.equals("Ok")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail(msj);
			}
		} catch (Exception e) {
			Assert.fail("Ocurrio un problema inexperado " + e);
		}
	}
}
