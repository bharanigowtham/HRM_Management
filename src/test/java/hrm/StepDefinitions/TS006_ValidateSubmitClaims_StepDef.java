package hrm.StepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import hrm.Pageobjects.Helpers;
import hrm.Utilities.ExcelReader;
import hrm.DriverManagers.DriverFactory;
import hrm.Pageobjects.Claimspage;
import hrm.Pageobjects.Homepage;
import hrm.Pageobjects.Webcontrol;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TS006_ValidateSubmitClaims_StepDef {
	
	private DriverFactory driverfactory;
	private Homepage homepage = new Homepage(driverfactory.getDriver());
	private Claimspage claimspage = new Claimspage(driverfactory.getDriver());
	private Webcontrol wcontrol = new Webcontrol(driverfactory.getDriver());
	ExcelReader excelreader = new ExcelReader();
	String testfilepath = ".//Testdata//Testdata_Addemp.xlsx";
	
	@Given("I navigate to {string} module")
	public void i_navigate_to_module(String string) throws Exception {
		homepage.clickonmodulename(string);
	}
	
	@When("I click on {string}")
	public void I_click_on_submitclaim(String btnName) {
		claimspage.selectClaimMethod(btnName);
	}
	
	@When("I create claim request from {string} & {int}")
	public void i_create_claim_request(String sheetname, int rowno) throws InvalidFormatException, IOException {
		List<Map<String,String>> testdata = excelreader.getData(testfilepath, sheetname);
	    wcontrol.selectdropdownvaluebytextgiven(1, "Event", testdata.get(rowno).get("Event"));
	    wcontrol.selectdropdownvaluebytextgiven(2, "Currency", testdata.get(rowno).get("Currency"));
	    claimspage.clickCreate();	    
	}
	
	@When("Add expense details from {string} & {int}")
	public void add_expense_details(String sheetname, int rowno) throws Exception {
		try {
			
			List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
			String noof = testdata.get(rowno).get("NoofExpensetoClaim");
			int noofexp = Integer.parseInt(noof);
			for(int i=1;i<=noofexp;i++)
			{
				claimspage.clickonAdd();
				wcontrol.selectdropdownvaluebytextgiven(1, "Expense Type", testdata.get(rowno).get("Expensetype"));
				String date = testdata.get(rowno).get("Date"); // DD-MM-YYYY
				System.out.println(date);
				String[] datearray = date.split("-");
				String datetoenter = datearray[2]+"-"+datearray[0]+"-"+datearray[1];
				System.out.println(datetoenter);
				claimspage.enterdate(datetoenter);
				claimspage.enteramount(testdata.get(rowno).get("Amount"));
				claimspage.enterNote(testdata.get(rowno).get("Note"));
				wcontrol.ClickSavebyindexno("save1", 1);
				Thread.sleep(3000);
				rowno++;
			}
			Helpers.Scrolldownbyjs("400");		
		}  
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("validate the expense details & consolidate expense amount from {string} & {int}")
	public void validate_the_expense_details_consolidate_expense_amount(String sheetname, int rowno) throws Exception {
		
	}
	
	@Then("click on submit")
	public void click_on_submit() {
	   claimspage.clickonsubmit();
	}
	
	
}
