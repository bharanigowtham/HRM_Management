package hrm.Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Webcontrol {

	private WebDriver driver;

	public Webcontrol(WebDriver driver) {
		this.driver = driver;
	}

	public void selectdropdownvaluebytextgiven(int index, String field, String value) {
		
		try {
			if (field.equalsIgnoreCase("Employment Status") || field.equalsIgnoreCase("Event") || field.equalsIgnoreCase("Currency")
				|| field.equalsIgnoreCase("Expense Type")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])["+index+"]")).click();
				Thread.sleep(2000);
				WebElement ele = driver.findElement(By.xpath("//div[@role='listbox']//div//span[contains(text(),'" + value + "')]"));
//				Helpers.WaitforvisibilityofElement(ele, 30);
				Thread.sleep(3000);
				ele.click();
				Thread.sleep(500);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void selectdatefromcal(String yearsel, String monthSel, String datesel, int index)
			throws InterruptedException {
		try {
			WebElement dateDDbtn = driver.findElement(By.xpath("//div[@class='oxd-date-wrapper']//div[@class='oxd-date-input']//i"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", dateDDbtn);
			Thread.sleep(500);
			dateDDbtn.click();
			Thread.sleep(2000);
			WebElement yearDDbtn = driver.findElement(
					By.xpath("//div[@class='oxd-calendar-header']//ul/li[@class='oxd-calendar-selector-year']"));
//			Helpers.WaitforClickableElement(yearDDbtn, 10);
			Thread.sleep(2000);
			yearDDbtn.click();
			Helpers.Scrollupbyjs();
			Thread.sleep(1000);
			List<WebElement> allyears = driver.findElements(By.xpath("//li[@class='--active oxd-calendar-selector-year']//ul//li"));
			Thread.sleep(2000);
			for (WebElement year : allyears) {
				String yr = year.getText();
				if (yr.equals(yearsel)) {
					year.click();
					System.out.println(yr);
				}
			}
			Thread.sleep(1000);
			WebElement MonthDDbtn = driver.findElement(By.xpath("//li[@class='oxd-calendar-selector-month']//i")); // li[@class='oxd-calendar-selector-month']//i
//			 Helpers.WaitforClickableElement(MonthDDbtn, 10);
			Thread.sleep(2000);
			MonthDDbtn.click();
			Helpers.Scrollupbyjs();
			Thread.sleep(1000);
			List<WebElement> allMonth = driver.findElements(By.xpath("//li[@class='--active oxd-calendar-selector-month']//ul//li"));
			for (WebElement mon : allMonth) {
				String mn = mon.getText();
				if (mn.equals(monthSel)) {
					mon.click();
					System.out.println(mn);
				}
			}
			Thread.sleep(1000);
			List<WebElement> alldates = driver.findElements(By.xpath("//div[@class='oxd-calendar-wrapper']//div[@class='oxd-calendar-dates-grid']//div//div"));
			for (WebElement date : alldates) {
				String dt = date.getText();
				if (dt.equals(datesel)) {
					date.click();
					System.out.println(dt);
				}
			}
			Thread.sleep(2000);
			Helpers.Scrollupbyjs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ClickSavebyindexno(String savebtnno, int index) throws InterruptedException {
		if (savebtnno.equalsIgnoreCase("save1")) {
			WebElement ele = driver.findElement(By.xpath("(//button[@type='submit'])[" + index + "]"));
			ele.click();
			Thread.sleep(1000);
		} else if (savebtnno.equalsIgnoreCase("save2")) {
			WebElement ele = driver.findElement(By.xpath("(//button[@type='submit'])[" + index + "]"));
			ele.click();
			Thread.sleep(500);
		} else if (savebtnno.equalsIgnoreCase("save3")) {
			WebElement ele = driver.findElement(By.xpath("(//button[@type='submit'])[" + index + "]"));
			ele.click();
			Thread.sleep(2000);
		}
	}
}
