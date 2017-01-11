package test;

import org.testng.annotations.Test;

import pages.TestgroupTrabajaNosotrosPage;
import pages.TestgroupCabeceraPage;
import util.TestBase;

import org.testng.Assert;

public class ScriptTestNG2 extends TestBase {
	private TestgroupCabeceraPage cabeceraPage;
	private TestgroupTrabajaNosotrosPage trabajeNosotros;

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
	public void clicMenuTrabajeNosotros() {
		try {
			cabeceraPage.clicMenuTrabajaNosotros();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("Se presento un problema " + e);
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void clicBotonCurriculoVitae() {
		String msj = "";
		trabajeNosotros = new TestgroupTrabajaNosotrosPage(driver);
		try {
			Thread.sleep(3000);
			msj=trabajeNosotros.clicDejarCurriculoVitae();
			Thread.sleep(2000);
			if (msj.equals("Ok")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail(msj);
			}
		} catch (Exception e) {
			System.out.println("Se presento un problema " + e);
			e.printStackTrace();
		}
	}
}
