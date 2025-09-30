package hrm.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {
	
	private WebDriver driver;

	private By usernam = By.name("username");
	private By passwrd = By.name("password");
	private By loginbtn = By.xpath("//button[@type='submit']");
	
	public Loginpage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterusername(String uname) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(usernam).sendKeys(uname);
	}

	public void enterpassword(String pwd) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(passwrd).sendKeys(pwd);
	}

	public void clickloginbtn() {
		driver.findElement(loginbtn).click();
	}

}
