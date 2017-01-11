package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.TestgroupCabeceraPage;
import pages.TestgroupContactanosPage;
import util.TestBase;

public class ScriptTestNG extends TestBase{
	private TestgroupCabeceraPage cabeceraPage;
	private TestgroupContactanosPage contactanosPage;
	
	 @Test 
	  public void validarMenuCabecera() {
			cabeceraPage=new TestgroupCabeceraPage(driver);
			String msj = "";
	  	try{
	  		msj=cabeceraPage.validarElementosMenu();
	  		if (msj.equals("Ok")){
					Assert.assertTrue(true);
				}else{
					Assert.fail(msj);
				}
	  	}catch(Exception e){
	  		Assert.fail("Ocurrio un problema inexperado " +e);
	  	}
	  }
	  @Test(priority=1)
	  public void clicMenuContactenos(){
		  try{
			  cabeceraPage.clicMenuContactenos();
			  Thread.sleep(1000);
		  }catch(Exception e){
			  System.out.println("Se presento un problema " + e);
			  e.printStackTrace();
		  }
	  }
	  @Test(priority=2)
	  public void rellenarCamposContactenos(){
			contactanosPage=new TestgroupContactanosPage(driver);	  
			String msj="";
			try {
				msj =contactanosPage.rellenarCamposContacto("Ana", "Quintero", "ana.quintero@testgroup.cl");
				if (msj.equals("Ok")){
					Assert.assertTrue(true);
				}else{
					Assert.fail(msj);
				}
	  	}catch(Exception e){
	  		Assert.fail("Ocurrio un problema inexperado " +e);		
		}
	  }
}
