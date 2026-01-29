package hrm.DriverManagers;

import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	
	public static WebDriver driver;
	
	public static ThreadLocal<WebDriver> thrdlocaldriver = new ThreadLocal<>();
	
	
	public static WebDriver initDriver(String Runtype, String OS, String browser) throws Exception {
		
		if(Runtype.equalsIgnoreCase("remote"))
		{	
			System.out.println("Run type = " + Runtype);
			
			MutableCapabilities options = null;
			
			if(browser.equalsIgnoreCase("chrome"))
			{
				options = new ChromeOptions();
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				options = new EdgeOptions();
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				options = new FirefoxOptions();
			}
			else {
				System.out.println("Please provide correct browser name");
			}
			
			setPlatform(options,OS);
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
			thrdlocaldriver.set(driver);
			
			System.out.println("REMOTE RUN STARTED");
		}
		else if(Runtype.equalsIgnoreCase("local")) 
		{
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
				thrdlocaldriver.set(new ChromeDriver());
			}
			else if(browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/Drivers/msedgedriver.exe");
				thrdlocaldriver.set(new EdgeDriver());
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"/Drivers/geckodriver.exe");
				thrdlocaldriver.set(new FirefoxDriver());
			}
			else {
				System.out.println("Provide correct browser name....");
			}
			
			System.out.println("*********** LOCAL RUN STARTED *********");
			System.out.println("Run type = " + Runtype);
			System.out.println("OS = " + OS);
			System.out.println("Browser = " + browser);
	}
		return getDriver();
	}
	
	public static void setPlatform(MutableCapabilities options, String OS) 
	{
	    if (OS.equalsIgnoreCase("windows")) {
	        options.setCapability("platformName", "WINDOWS");
	    } 
	    else if (OS.equalsIgnoreCase("linux")) {
	        options.setCapability("platformName", "LINUX");
	    } 
	    else if (OS.equalsIgnoreCase("mac")) {
	        options.setCapability("platformName", "MAC");
	    } 
	    else {
	        System.out.println("Please provide correct OS name");
	    }
	}
	
	public static synchronized WebDriver getDriver() {
		return thrdlocaldriver.get();
	}
}
