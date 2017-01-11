package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TestgroupContactanosPage {
	WebDriver driver;

	// Constructor
	public TestgroupContactanosPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Comprueba que nos encontramos en la pagina TestGroup Contacto
		if (!"TestGroup Contacto".equals(driver.getTitle())) {
			throw new IllegalStateException("Esta no es la página de TestGroup Contacto!: "+driver.getTitle());
		}
	}

	// Área de texto Nombre
	@FindBy(how = How.XPATH, using = "//input[@name='nombre']")
	private WebElement taNombre;
	// Área de texto Apellido
	@FindBy(how = How.XPATH, using = "//input[@name='apellido']")
	private WebElement taApellido;
	// Área de texto Correo
	@FindBy(how = How.XPATH, using = "//input[@name='correo']")
	private WebElement taCorreo;
	// Área de texto Empresa
	@FindBy(how = How.ID, using = "//input[@name='empresa']")
	private WebElement taEmpresa;
	// Área de texto Cargo
	@FindBy(how = How.ID, using = "//input[@name='cargo']")
	private WebElement taCargo;
	// Área de texto Servicio
	@FindBy(how = How.ID, using = "//select[@name='servicios']")
	private WebElement taServicios;
	// Área de texto Teléfono
	@FindBy(how = How.ID, using = "//input[@name='telefono']")
	private WebElement taTelefono;
	// Área de texto Enviar
	@FindBy(how = How.ID, using = "//input[@value='Enviar']")
	private WebElement taEnviar;

	public String rellenarCamposContacto(String nombre, String apellido, String correo) throws InterruptedException {
		String msg = "Ok";
		
			if(taNombre.isDisplayed()){
				taNombre.clear();
				taNombre.sendKeys(nombre);
			}else{
				msg="No se encontro el campo de Nombre";
			}
			if(taApellido.isDisplayed()){
				taApellido.clear();
				taApellido.sendKeys(apellido);
			}else{
				msg="No se encontro el campo de Apellido";
			}
			if (taCorreo.isDisplayed()){
				taCorreo.clear();
				taCorreo.sendKeys(correo);			
			}else{
				msg="No se encontro el campo de Correo";
			}
		
			return msg;
	}

	public String validarCampos() {
		String msg = "Ok";
		if (!taNombre.isDisplayed()) {
			if (msg.equals("Ok")) {
				msg = "";
			}
			msg = msg + "No se encontró el área de ingreso de texto Nombre \n";
		}
		if (!taApellido.isDisplayed()) {
			if (msg.equals("Ok")) {
				msg = "";
			}
			msg = msg + "No se encontró el área de ingreso de texto Apellido \n";
		}
		if (!taCorreo.isDisplayed()) {
			if (msg.equals("Ok")) {
				msg = "";
			}
			msg = msg + "No se encontró el área de ingreso de texto Correo \n";
		}
		return msg;
	}
}
