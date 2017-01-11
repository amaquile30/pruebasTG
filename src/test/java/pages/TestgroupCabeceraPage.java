package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestgroupCabeceraPage {
	WebDriver driver;

	//Constructor
	public TestgroupCabeceraPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println(driver.getTitle());
		// Comprueba que nos encontramos en la pagina inicial de Testgroup.
		if (!"TestGroup".equals(driver.getTitle())) {
			throw new IllegalStateException("Esta no es la página inicial de TestGroup!: "+driver.getTitle());
		}
	}
	//Logo-link TestGroup
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div[1]/div/div/a/img")
	private WebElement lnkTestgroup;
	//Menú Inicio
	@FindBy(how = How.ID, using = "menu-item-18")
	private WebElement menuInicio;
	//Menú Nosotros
	@FindBy(how = How.ID, using = "menu-item-31")
	private WebElement menuNosotros;
	//Menú Contacto
	@FindBy(how = How.ID, using = "menu-item-16")
	private WebElement menuContacto;
	//Menú Contacto Comercial
	@FindBy(how = How.ID, using = "menu-item-164")
	private WebElement menuContactoComercial;
	//Menú Trabaje con Nostros
	@FindBy(how = How.ID, using = "menu-item-246")
	private WebElement menuTrabajeNosotros;
	
	public String validarElementosMenu() {
		String msg="Ok";
		if(!lnkTestgroup.isDisplayed()){
			if(msg.equals("Ok")){
				msg = "";
			}
			msg=msg + "No se encontró el logo link de Testgroup \n";
		}
		if(!menuInicio.isDisplayed()){
			if(msg.equals("Ok")){
				msg = "";
			}
			msg=msg + "No se encontró la opción del menú Inicio \n";
		}
		if(!menuNosotros.isDisplayed()){
			if(msg.equals("Ok")){
				msg = "";
			}
			msg=msg + "No se encontró la opción del menú Nosotros \n";
		}
		return msg;
	}
	public void clicMenuContactenos() throws InterruptedException{
		menuContacto.click();
		Thread.sleep(500);
//		menuContactoComercial.click();
//		Thread.sleep(1000);
	}
	public void clicMenuNosotros() throws InterruptedException{
		menuNosotros.click();
		Thread.sleep(1000);
	}
	public void clicMenuTrabajaNosotros() throws InterruptedException{
		
//		menuContacto.click();
//		mouseOver(menuContacto);
//		Thread.sleep(500);
//		menuTrabajeNosotros.click();
		WebDriverWait wait = new WebDriverWait(driver, 5,100);
		Actions actions = new Actions(driver);
		actions.moveToElement(menuContacto).perform();
		wait.until( ExpectedConditions.visibilityOf(menuTrabajeNosotros) ).click();
//		actions.moveToElement(menuTrabajeNosotros).click().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("html/body/div/div[2]/section[1]/div/div/p[1]"))));
//		Thread.sleep(1000);
	}

	public void mouseOver(WebElement elemento){
		Actions actions = new Actions(driver);
		actions.moveToElement(elemento).build().perform();
	}
}
