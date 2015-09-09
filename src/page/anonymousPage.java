package page;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class anonymousPage
{
	/*Class variables*/
	public RemoteWebDriver driver;
    private By signin = By.name("signin_btn");
    private By username = By.name("username_et");
    private String email = "sam@t.com";
    private String pwd = "Tesco123_";
    private By password = By.name("password_et");
    public String searchtext = "milk";
    private By anmshopbutton = By.name("anonymous_shop_btn");
    
/*Default constructor*/
    public anonymousPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
 
/*Sign in from anonymous page*/
public void signIn() throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(signin)));
	driver.findElement(signin).click();
	enterText((RemoteWebElement) driver.findElement(username), email);
	enterText((RemoteWebElement) driver.findElement(password), pwd);
	RemoteWebElement en = (RemoteWebElement) driver.findElement(By.name("signin_btn"));
    if (en.isEnabled() == true){
    	en.click();
    }else{
    	System.out.print("INVALID CREDENTIALS...");
    }
    /*LHN On-boarding*/
    bookSlotOperations obj = new bookSlotOperations(driver);
    if (obj.checkElementVisibility(driver, "Images/ONBOARDING/LHN_bg_blue.png") == true){
		driver.findElement(By.name("ok_gotit_btn")).click();
	}
    /*Basket LHN On-boarding*/
    if (obj.checkElementVisibility(driver,"Images/ONBOARDING/Basket_bg_blue.png") == true){
		driver.findElement(By.name("ok_gotit_btn")).click();
	}
}
//Anonymous search function 
public void anonymousSearch() throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver, 60);
	driver.findElement(By.name("Search field")).click();
	enterText((RemoteWebElement) driver.findElement(By.name("SearcEditText")), searchtext);
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("Search for \"milk\""))));
	driver.findElement(By.name("Search for \"milk\"")).click();
}
//Anonymous shop function
public void anmShop() throws Exception{
	WebDriverWait wait = new WebDriverWait(driver, 60);
	driver.findElement(anmshopbutton).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAElement[1]/UIAButton[1]"))));
	for(int i=1; i<=3;i++){                                                        
	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]").click();
	Thread.sleep(3000);
}
	String searchResult = (String) driver.findElement(By.name("title_label")).getText();
	System.out.print("Searched product category is "+searchResult);
	addItems();
	}

public void addNewClubcard(String clubcardnumber, String postcode) throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver, 60);
	driver.findElement(By.name("action_bar_up_navigation")).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("lhn_clubcard_accessibility_id"))));
    driver.findElement(By.name("lhn_clubcard_accessibility_id")).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("Get the new clubcard app"))));
	enterText((RemoteWebElement) driver.findElement(By.name("postcode")), postcode);
	enterText((RemoteWebElement) driver.findElement(By.name("clubcard_number")), clubcardnumber);
	assertTrue(driver.findElement(By.name("add_clubcard_btn")).isEnabled());
	driver.findElement(By.name("add_clubcard_btn")).click();

}

@SuppressWarnings("unchecked")
public void addItems(){
    List<WebElement> rows = driver.findElementsByClassName("UIATableView");
	System.out.print(rows.size());
	for (int i=1;i<=rows.size();i++){
		/*checks if product is available for adding*/
		if(checkElementVisibility(driver, "add_btn")){
			System.out.print(driver.findElement(By.name("add_btn")).isEnabled());
			/*Tap Add Button*/
			driver.findElement(By.name("add_btn")).click();
		}
		    i=i+1;
			System.out.print("product added");
		}
	}

/*Element verifier*/
public boolean checkElementVisibility(RemoteWebDriver driver, String name){
	 try{
		 driver.findElement(By.name(name));
		 return true;
	 }catch (NoSuchElementException e) {
		 System.out.print("\nNo such element found hence returning FALSE!!");
	        return false; 
	 }
} 

/*ENTER TEXT FUNCTION*/
public void enterText(RemoteWebElement key, String searchText) throws InterruptedException{
	WebDriverWait hold = new WebDriverWait(driver, 60);
	hold.until(ExpectedConditions.elementToBeClickable(key));
	//Thread.sleep(5000);
	key.click();
	//Thread.sleep(3000);
	key.sendKeys(searchText);	
}




}