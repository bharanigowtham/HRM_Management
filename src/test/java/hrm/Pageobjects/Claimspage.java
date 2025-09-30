package hrm.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Claimspage {
	
	private WebDriver driver;
	
	public Claimspage(WebDriver driver) {
		this.driver=driver;
	}
	
	By btn_create = By.xpath("//button[@type='submit']");
	By field_RefId = By.xpath("((//div[@class='oxd-input-group__label-wrapper'])[1])//following-sibling::div//input");
	By field_status = By.xpath("((//div[@class='oxd-input-group__label-wrapper'])[3])//following-sibling::div//input");
	By btn_add = By.xpath("(//div[@class='orangehrm-action-header'])[1]//button");
	By txt_amount = By.xpath("(//div[@class='oxd-input-group__label-wrapper'])[8]//following-sibling::div//input");
	By txt_note = By.xpath("(//div[@class='oxd-input-group oxd-input-field-bottom-space'])[9]//descendant::textarea");
	By datefield = By.xpath("//input[@placeholder='yyyy-dd-mm']");
	By btn_submit = By.xpath("(//div[@class='orangehrm-action-buttons-container']//button[@type='button'])[3]");
	
	public void selectClaimMethod(String claimmethod) {
		try {
			By btn_submitclaim = By.xpath("//div[@class='oxd-topbar-body']//descendant::li//a[contains(text(),'"+claimmethod+"')]");
			driver.findElement(btn_submitclaim).click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickCreate() {
		try {
			driver.findElement(btn_create).click();
			Thread.sleep(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getRefid() {
		
		String refIDtext = driver.findElement(field_RefId).getText();
		return refIDtext;
	}
	
	public String getStatus() {
		String status=null;
		try {
			status = driver.findElement(field_RefId).getText();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	public void clickonAdd() {
		try {
			driver.findElement(btn_add).click();
			Thread.sleep(1000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void enteramount(String value) {
		try
		{
			driver.findElement(txt_amount).sendKeys(value);
			Thread.sleep(1000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterNote(String value) {
		try
		{
			driver.findElement(txt_note).sendKeys(value);
			Thread.sleep(1000);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterdate(String dt) {
		driver.findElement(datefield).sendKeys(dt);
	}
	
	public void clickonsubmit() {
		try {
			driver.findElement(btn_submit).click();
			Thread.sleep(3000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	} 
}
