package page;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class amendJourney extends checkout {

	/*Class variables for amend Page*/
private By cancelAmend = By.name("cancelAmend");
private By saveAmend = By.name("saveAmend");
private By changeSlot = By.name("Change slot");
private By lhn = By.name("action_bar_up_navigation");
private By myOrders = By.name("lhn_myorders_accessibility_id");
private By makeChanges = By.name("amend_btn");
private By listOfOrders = By.name("Empty list");
private By addBttn = By.name("add_btn");
private By cancelOrder = By.name("cancel_btn");
WebDriverWait wait = new WebDriverWait(driver, 60);
	
	// TODO Auto-generated constructor stub
public amendJourney(RemoteWebDriver driver) {
	super(driver);
	}

public void cancelAmendOrderFromDashboard()
{
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(cancelAmend)));
	driver.findElement(cancelAmend).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("Do you want to cancel changes to this order?"))));
	driver.switchTo().alert().accept(); /*Accept the alert message*/
}

public void saveAmendOrder() throws InterruptedException
{
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(saveAmend)));
	driver.findElement(saveAmend).click();
	driver.switchTo().alert().accept(); /*Accept the alert message*/
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("continue_btn"))));
	driver.findElement(By.name("continue_btn")).click();
	if (checkElementVisibility(driver, "Checkout") == true)
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIASecureTextField[1]"))));
		System.out.print("\nEntered into "+driver.findElement(By.name("Checkout")));
		usableNETCheckout();
	}
	else
	{
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("Changes to your order have been saved"))));
	driver.switchTo().alert().accept();
    }
}

public void cancelAllOrder()
{
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lhn)));
	driver.findElement(lhn).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(myOrders)));
	driver.findElement(myOrders).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("My orders"))));
	List<WebElement> elements = driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAElement[1]/UIATableView[1]/UIATableCell"));
    System.out.print("\nTotal number of orders are "+elements.size());
    loop:
    for(int i=1;i<=elements.size();i++)
    {
    	driver.findElement(cancelOrder).click();
    	driver.switchTo().alert().accept(); /*Accept the alert*/
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("My orders"))));
    	if(checkElementVisibility(driver, "cancel_btn") == false)
    	{
    		break loop;
    	}
    	System.out.print("\nAll the Pending orders are cancelled");
    }
}

public void addItemsInAmendMode(int itemNumbers) throws InterruptedException
{
	/*Add items from existing order*/
	invokeBasket();
	for(int i=1;i<=itemNumbers;i++){
		driver.findElement(addBttn).click();
		Thread.sleep(2000);
	}
}

public void changeSlot() throws InterruptedException
{
	driver.findElement(lhn).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("lhn_home_accessibility_id"))));
	driver.findElement(By.name("lhn_home_accessibility_id")).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(changeSlot)));
	driver.findElement(changeSlot).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("delivaryAddress"))));
	if (checkElementVisibility(driver, "Home delivery") == true)
	{
		bookASlot("Home Delivery");
	}else
	{
		bookASlot("Click & Collect");
	}
	/*Save amend order*/
	saveAmendOrder();
 }
}
