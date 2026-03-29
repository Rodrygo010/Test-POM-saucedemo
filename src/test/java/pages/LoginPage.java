package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    By email = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");
    
	By addtocart = By.id("add-to-cart-sauce-labs-backpack");
	By cart = By.className("shopping_cart_link");
	By numberofcart = By.xpath("//span[@class='shopping_cart_badge']");
	
	By checkout = By.id("checkout");
	
	By fnamechekout = By.id("first-name");
	By lsanmechekout = By.id("last-name");
	By postalcode = By.id("postal-code");
	By btnContinue = By.id("continue");
	
	By BtnFinish =By.id("finish");

    
    By errorMsg = By.xpath("//h3[@data-test='error']");
    By successMsg = By.className("complete-header");

    public void login(String user, String pass){
        driver.findElement(email).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public void waitForProducts(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(addtocart));
    }
    
    public void addtocartM() {
		driver.findElement(addtocart).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(numberofcart));
	}
	
	public void clickcart() {
		driver.findElement(cart).click();
	}
	
	
	public void clickchekout() {
		driver.findElement(checkout).click();
	}
	
	public void fname(String firstname) {
		driver.findElement(fnamechekout).sendKeys(firstname);
	}
	
	public void lname(String lastname) {
		driver.findElement(lsanmechekout).sendKeys(lastname);
	}
	
	public void Sendcodepostal(String codepostal) {
		driver.findElement(postalcode).sendKeys(codepostal);
	}
	
	public void ClickContinue() {
		driver.findElement(btnContinue).click();
	}
	
	public void ClickFinish() {
		driver.findElement(BtnFinish).click();
	}

    public boolean isErrorDisplayed(){
        return !driver.findElements(errorMsg).isEmpty();
    }

    public boolean isSuccessDisplayed(){
        return driver.findElements(successMsg).size() > 0;
    }
}