package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class TakeScreenShot extends TestListenerAdapter{
	private boolean createFile(File screenshot) throws IOException {
		boolean fileCreated = false;
		if (screenshot.exists()) {
			fileCreated = true;
		} else {
			File parentDirectory = new File(screenshot.getParent());
			if (parentDirectory.exists() || parentDirectory.mkdirs()) {
				fileCreated = screenshot.createNewFile();
			}
		}
		return fileCreated;
	}
	private void writeScreenshotToFile(WebDriver driver, File screenshot) throws IOException {
		FileOutputStream screenshotStream = new FileOutputStream(screenshot);
		screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
		screenshotStream.close();
	}
	@Override
	public void onTestFailure(ITestResult failingTest) {
		WebDriver driver=((TestBase)failingTest.getInstance()).getDriver();
		try {
			String pathFile="./target/Screenshots";
			String imageName = System.currentTimeMillis() + "_" + failingTest.getInstanceName() + "_"
					+ failingTest.getName() + ".png";
			String screenshotAbsolutePath = pathFile + File.separator + imageName;
			File screenshot = new File(screenshotAbsolutePath);
			
			if (createFile(screenshot)) {
				try {
					if (!failingTest.isSuccess()) {
						writeScreenshotToFile(driver, screenshot);
						Reporter.setCurrentTestResult(failingTest);   
						String url = "../../../../artifact/target/Screenshots/";
						System.out.println(url);
						Reporter.log("<a href ='" + url + imageName + "'> ");
						Reporter.log("<br> <img src='" + url + imageName + "' height='200' width='200'/> <br>");
					    }
				} catch (ClassCastException weNeedToAugmentOurDriverObject) {
					writeScreenshotToFile(new Augmenter().augment(driver), screenshot);
				}
				System.out.println("Written screenshot to " + screenshotAbsolutePath);
			} else {
				System.err.println("Unable to create " + screenshotAbsolutePath);
			}
		} catch (Exception ex) {
			System.err.println("Unable to capture screenshot...");
			ex.printStackTrace();
		}
	}
}