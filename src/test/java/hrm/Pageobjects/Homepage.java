package hrm.Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
	
private WebDriver driver;
	
	public Homepage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickonmodulename (String uname) throws InterruptedException {
		try {
			List<WebElement> allelem = driver.findElements(By.xpath("//div[@class='oxd-sidepanel-body']//ul//li//span"));
			for(WebElement module1 : allelem)
			{
				String modulename = module1.getText();				
				if(modulename.equals(uname)) 
				{
					System.out.println("Given module name = "+modulename);
					module1.click();
					Thread.sleep(2000);
				}
			}
		}
		catch(StaleElementReferenceException e) {
			e.printStackTrace();
		}	
	}
	
}
