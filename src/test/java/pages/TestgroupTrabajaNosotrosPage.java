package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TestgroupTrabajaNosotrosPage {
	WebDriver driver;

	// Constructor
	public TestgroupTrabajaNosotrosPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	// botón de Ofertas Laborales Disponibles
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/section[1]/div/div/div/div/div[1]/button")
	private WebElement btnOfertaDisponible;
	// botón de Déjanos tu Curriculum Vitae
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/section[1]/div/div/div/div/div[3]/button")
	private WebElement btnCurriculumVitae;
	// Área de texto Nombre
	@FindBy(how = How.XPATH, using = "//input[@name='nombre']")
	private WebElement taNombre;
	// Área de texto Apellido
	@FindBy(how = How.XPATH, using = "//input[@name='apellido']")
	private WebElement taApellido;
	// Área de texto Nacionalidad
	@FindBy(how = How.XPATH, using = "//input[@name='nacionalidad']")
	private WebElement taNacionalidad;
	
	public String validarOpciones() {
		String msg = "Ok";
		if (!btnOfertaDisponible.isDisplayed()) {
			if (msg.equals("Ok")) {
				msg = "";
			}
			msg = msg + "No se encontró el botón de Ofertas Laborales Disponibles \n";
		}
		if (!btnCurriculumVitae.isDisplayed()) {
			if (msg.equals("Ok")) {
				msg = "";
			}
			msg = msg + "No se encontró el botón de Déjanos tu Curriculum Vitae \n";
		}
		return msg;
	}
	public String clicDejarCurriculoVitae() throws InterruptedException{
		String msg = "Ok";
		if (btnCurriculumVitae.isDisplayed()){
			btnCurriculumVitae.click();
			Thread.sleep(2000); 
		}else{
			msg="no se encontró el boton Déjanos tu Curriculum Vitae";
		}
		return msg;
	}
	public String dejarCurriculoVitae(String nombre, String apellido, String Nacionalidad){
		String msg = "Ok";
		if (taNombre.isDisplayed()){
			taNombre.sendKeys(nombre);
		}else{
			msg= "No se encontró el área de texto del Nombre";
		}
		if (taApellido.isDisplayed()){
			taApellido.sendKeys(apellido);
		}else{
			msg= "No se encontró el área de texto del Apellido";
		}
		if (taNacionalidad.isDisplayed()){
			taNacionalidad.sendKeys(Nacionalidad);
		}else{
			msg= "No se encontró el área de texto del Nacionalidad";
		}
		return msg;
	}
}
