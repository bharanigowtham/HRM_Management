package hrm.StepDefinitions;

import java.util.Properties;

import hrm.DriverManagers.DriverFactory;
import hrm.Hooks.Hooks;
import hrm.Pageobjects.Loginpage;
import hrm.Utilities.ConfigpropertiesReader;
import io.cucumber.java.en.Given;

public class LoginandCommonStepDefinition  {
	
	private ConfigpropertiesReader config = new ConfigpropertiesReader();
	private Loginpage loginpage = new Loginpage(DriverFactory.getDriver());
	private Properties prop;
	String username;
	String password;
	
	public LoginandCommonStepDefinition(Hooks hooks) {
        this.prop = hooks.prop; 
    }
	
	@Given("I launch url & have to login to system with valid credentials for user {string}")
	public void loginintoApplication(String user) throws InterruptedException {
		if(user.equalsIgnoreCase("Admin1")) {
			username = prop.getProperty("username1");
			password = prop.getProperty("password1");
		}
		else if(user.equalsIgnoreCase("Admin2")) {
			username = prop.getProperty("username2");
			password = prop.getProperty("password2");
		}
		else if(user.equalsIgnoreCase("Admin3")) {
			username = prop.getProperty("username3");
			password = prop.getProperty("password3");
		}
		
		loginpage.enterusername(username);
		loginpage.enterpassword(password);
		loginpage.clickloginbtn();
	}
	
	
	
}
