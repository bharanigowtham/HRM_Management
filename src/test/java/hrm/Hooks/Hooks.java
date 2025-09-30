package hrm.Hooks;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import hrm.DriverManagers.DriverFactory;
import hrm.Utilities.ConfigpropertiesReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	private DriverFactory driverfactory;
	private ConfigpropertiesReader config;
	private WebDriver driver;
	public Properties prop;
	String Appurl;
	
	@Before(order=1)
	public void getPropertyValues() throws Throwable {
		config = new ConfigpropertiesReader();
		prop = config.init_Properties();
	}
	
	@Before(order=2) 
	public void launchBrowser() throws Exception {
		String runType = prop.getProperty("executionrunType");
		String operatingSystem = prop.getProperty("operatingsystem");
		String browser = prop.getProperty("browser");
		
		
		driverfactory = new DriverFactory();
		driver = driverfactory.initDriver(runType, operatingSystem, browser);
	}
	
	@Before(order=3)
	public void launchApplication() {
		
		String environment = prop.getProperty("executionenvironment");
		if(environment.equalsIgnoreCase("SIT")) {
			Appurl = prop.getProperty("SIT_url");
		} 
		else if(environment.equalsIgnoreCase("UAT")) {
			Appurl = prop.getProperty("UAT_url");
		} 
		else if(environment.equalsIgnoreCase("PREPROD")) {
			Appurl = prop.getProperty("PREPROD_url");
		} 
		else {
			System.out.println("Please provide correct environment name");
			return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(Appurl);
		
		System.out.println("Environment = " + environment);
	}
	
	@After()
	public void closebrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	
	
}
