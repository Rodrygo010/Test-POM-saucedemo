package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

    @DataProvider(name="excelData")
    public Object[][] getExcelData(){

        String path = System.getProperty("user.dir") + "/testdata/Classeur1.xlsx";
        return ExcelUtils.getExcelData(path, "Feuil1");
    }

    @Test(dataProvider = "excelData")
    public void logintest(String username, String password, String firstname,
                          String lastname, String postal, String validStr) {

        boolean isValid = Boolean.parseBoolean(validStr);

        driver.get("https://www.saucedemo.com/");
        LoginPage login = new LoginPage(driver);

        login.login(username, password);

        if(isValid){

            login.waitForProducts();
            test.info("Valid login → continue flow");
    		
    		
    		test.info("addtocart");
    		login.addtocartM();
    		
    		test.info("clickcart");
    		login.clickcart();
    		
    		test.info("click-chekout");
    		login.clickchekout();
    		
    		test.info("Entering chekout infos");
    		login.fname(firstname);
    		login.lname(lastname);
    		login.Sendcodepostal(postal);
    		
    		test.info("click continue");
    		login.ClickContinue();
    		
    		test.info("click finich");
    		login.ClickFinish();
    		
    		test.info("Checking success message");
    		Assert.assertTrue(login.isSuccessDisplayed());
    		
    		test.pass("Test passed successfully");
    		
            

        } else {
        	test.info("invalid login = erreur message");

            Assert.assertTrue(login.isErrorDisplayed());
            			
			test.fail("Login failed as expected (invalid credentials)");
        }
    }
}