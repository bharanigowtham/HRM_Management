package hrm.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import hrm.DriverManagers.DriverFactory;
import io.cucumber.java.Scenario;

public class Helpers {

	public static Scenario scenario;
	private static DriverFactory driverfactory;

	public static String generateRandomString() {
		String email = RandomStringUtils.randomAlphabetic(8);
		return email;
	}
	
	public static String generateRandomAlphaNumeric(int num) {
		String id = RandomStringUtils.randomAlphanumeric(num);
		return id;
	}
	
	public static String generateRandomnum() {
		String num = RandomStringUtils.randomNumeric(9);
		return num;
	}

	public static String generateRandomnumbygivenlength(int numb) {
		String num = RandomStringUtils.randomNumeric(numb);
		return num;
	}
	
	public static String mapmonthwithgivennum(String num) {
		String[] months = {"", "January", "February", "March","April", "May", "June","July","August", "September",
				    		"October","November", "December"};
		int newnum = Integer.parseInt(num);
		String mon = months[newnum];
		return mon;
	}
	
	public static String customizenumber_gen(int num) {
		Random random = new Random();
		int numgen = random.nextInt(num);
		String numb = String.valueOf(numgen);
		return numb;
	}

	public static int generaterandomyear() {
		GregorianCalendar gc = new GregorianCalendar();
		int year = randbetween(2026, 2035);
		return gc.get(gc.YEAR);
	}

	public static int randbetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public static String gendatenum() {
		Random random = new Random();
		int dategen = random.nextInt(30);
		String date = String.valueOf(dategen);
		return date;
	}

	public static void WaitforvisibilityofElement(WebElement element, Duration timeoutInseconds) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeoutInseconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void WaitforvisibilityofElement(By element, Duration timeoutInseconds) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeoutInseconds);
		wait.until(ExpectedConditions.visibilityOf((WebElement) element));
	}

	public static void WaitforClickableElement(WebElement element, Duration timeoutInseconds) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeoutInseconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void WaitforClickableElement(By element, Duration timeoutInseconds) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeoutInseconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void Scrollupbyjs() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript("window.scrollBy(0,-300)");
		Thread.sleep(500);
	}

	public static void Scrollupbyjsbyvalue(String scrollvalue) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript("window.scrollBy(0," + scrollvalue + ")");
	}

	public static void Scrollupfullbyjs() {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	}

	public static void Scrolldownbyjs(String scrollvalue) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
//		js.executeScript("window.scrollTo(0, " + scrollvalue + ")");
		js.executeScript("window.scrollBy(0, " + scrollvalue +")");
	}

	public static void clickonele(WebElement ele) throws InterruptedException {
		Thread.sleep(500);
		ele.click();
	}

	public static void entervalue(WebElement ele, String data) throws InterruptedException {
		Thread.sleep(500);
		ele.sendKeys(data);
	}

	public static void takeScreenShot(String ssName) throws Exception {
		try {
			byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", ssName); // Stick it in the report
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {

		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
	}
	
	public static long getscrollvalue(By element) throws InterruptedException {
			
		WebElement elementToScrollTo = driverfactory.getDriver().findElement(element); 
        ((JavascriptExecutor) driverfactory.getDriver()).executeScript("arguments[0].scrollIntoView();", elementToScrollTo);
        Thread.sleep(3000);
  
    	 long scrollval = (long) ((JavascriptExecutor) driverfactory.getDriver()).executeScript("return window.pageYOffset;");
   
        ((JavascriptExecutor) driverfactory.getDriver()).executeScript("window.scrollTo(0, 0)"); 
        
        
		return scrollval;
       
	}
	
	
	
	public static void takethescreenshot(String modulename) {
		
		TakesScreenshot screenshot = (TakesScreenshot) driverfactory.getDriver();
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		
		try {
			Path outputdirectory = Path.of("./Screenprints/");
			if(!Files.exists(outputdirectory))
			{
				Assert.assertTrue(new File(String.valueOf(outputdirectory)).mkdirs(), "Unable to create output directory");
			}
			
			//Create blank word document
			XWPFDocument document;
			
			Path screenshotsDocumentpath = Path.of("./Screenprints/"+modulename+".docx");
			
			if(!Files.exists(screenshotsDocumentpath))
			{
				document = new XWPFDocument();
			}
			else {
				document = new XWPFDocument(Files.newInputStream(Paths.get("./Screenprints/"+modulename+".docx")));
			}
			//Create blank paragraph
			XWPFParagraph paragraph = document.createParagraph();
			
			//Add paragraph text
			XWPFRun run = paragraph.createRun();
			if(Files.exists(screenshotsDocumentpath))
			{
				run.addCarriageReturn();
				run.addCarriageReturn();
			} 
			
			run.setText("Module Name = " + modulename);
			run.addCarriageReturn();
			run.addCarriageReturn();
			
			//Create file input stream
			File image = new File(String.valueOf(file));
			FileInputStream fisImagedata = new FileInputStream(image);
			
			//Set image type & get image name
			int imagetype = XWPFDocument.PICTURE_TYPE_JPEG;
			String imageFileName = image.getName();
			
			//Set image width & height...
			int imagewidth = 500;
			int imageheight = 250;
			
			FileOutputStream fos = new FileOutputStream(new File("./Screenprints/"+modulename+".docx"));
			
			run.addPicture(fisImagedata, imagetype, imageFileName, Units.toEMU(imagewidth), Units.toEMU(imageheight));
			document.write(fos);
			
			fos.close();
			document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
