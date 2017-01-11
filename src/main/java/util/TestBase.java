package util;

import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
	static ResourceBundle bundle = ResourceBundle.getBundle("datos");
	protected static final String CUR_DIR = System.getProperty("user.dir");
	protected static final String BROWSER = System.getProperty("BROWSER",bundle.getString("Navegador"));
	protected static final String WEB_SERVER = System.getProperty("WEB_SERVER",bundle.getString("Url"));
	protected static WebDriver driver;
	
	protected static final String DIR_DOWNLOAD = System.getProperty("user.home", "C:") + File.separator + "Downloads" + File.separator;
	
	public  WebDriver getDriver() {
		return driver;
	}
	private static void setupDriver(String browserType, String appURL) {
		
		if(browserType.equals("firefox")){
			driver = initFirefoxDriver(appURL);
		}else if (browserType.equals("chrome")){
			driver = initChromeDriver(appURL);
		}else if (browserType.equals("internetExplorer")){
			driver = initIExplorerDriver(appURL);
		}else {
			throw new RuntimeException("Browser type unsupported: "+ browserType);
		}
	}
	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		//WebDriver driver = new FirefoxDriver();
		FirefoxProfile fprofile = new FirefoxProfile();
		fprofile.setPreference("browser.download.dir", DIR_DOWNLOAD);						
		fprofile.setPreference("browser.download.folderList", 2);
		fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
				  "application/msword, "
				+ "application/vnd.ms-excel, "
				+ "application/mspowerpoint, "
				+ "application/csv, "
				+ "text/csv, "
				+ "application/pdf, "
				+ "image/png, "					
				+ "text/plain, "
				+ "application/plain,"
				+ "application/zip, "
				+ "application/x-zip, "
				+ "application/octet-stream");
		fprofile.setPreference("browser.download.manager.showWhenStarting", false);

		fprofile.setPreference("browser.helperApps.neverAsk.openFile", 
				  "application/msword, "
				+ "application/vnd.ms-excel, "
				+ "application/mspowerpoint, "
				+ "application/csv, "
				+ "text/csv, "
				+ "application/pdf, "
				+ "image/png, "					
				+ "text/plain, "
				+ "application/plain,"
				+ "application/zip, "
				+ "application/x-zip, "
				+ "application/octet-stream");
		fprofile.setPreference("browser.helperApps.alwaysAsk.force", false);
		fprofile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		fprofile.setPreference("browser.download.manager.focusWhenStarting", false);
		fprofile.setPreference("browser.download.manager.useWindow", false);
		fprofile.setPreference("browser.download.manager.showAlertOnComplete", false);
		fprofile.setPreference("browser.download.manager.closeWhenDone", false);
		driver = new FirefoxDriver(fprofile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	private static WebDriver initChromeDriver(String appURL) {
		String path = CUR_DIR + "\\src\\test\\resources\\driver\\ChromeDriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		System.out.println("Launching Chrome browser..");
		//WebDriver driver = new ChromeDriver();
		
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", DIR_DOWNLOAD);
		prefs.put("download.prompt_for_download", true);
		prefs.put("download.directory_upgrade", true);
		prefs.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });				
		options.setExperimentalOption("prefs", prefs);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		driver = new ChromeDriver(capabilities);
		
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	private static WebDriver initIExplorerDriver(String appURL) {
		String path = CUR_DIR + "\\src\\test\\resources\\driver\\IExplorerDriver\\IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", path);
		System.out.println("Launching IExplorer browser..");
		//WebDriver driver = new InternetExplorerDriver();
		
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
		
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,	true);
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        capabilities.setJavascriptEnabled(true);

		driver = new InternetExplorerDriver(capabilities);
		
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@BeforeClass
	public void initializeTestBaseSetup() {
		try {
			setupDriver(BROWSER, WEB_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}