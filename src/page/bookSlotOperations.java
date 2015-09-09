package page;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import appiumIOS.responseURL;

public class bookSlotOperations extends responseURL {
	private By bookslot = By.name("lhn_bookaslot_accessibility_id");
	private By lhnButton = By.name("action_bar_up_navigation");
	private AppiumDriver appiumDriver;
	public int m = 50;
	public int HD_X = 19;
	public int CC_X = 121;
	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	/*Default constructor*/
	public bookSlotOperations(RemoteWebDriver driver) {
		super(driver);
	} 
	
/*Get the slot types*/	
	public void selectSlotType(String slotType) throws InterruptedException{
		driver.findElement(lhnButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(bookslot)));
		driver.findElement(bookslot).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("How will you receive your shopping?"))));
		driver.findElement(By.name(slotType)).click();
		Thread.sleep(3000);
		if (slotType == "Home Delivery")
		{
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("changeAddress"))));
		}
		else
		{
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("choose_address"))));
			driver.findElement(By.name("collection_point")).click();
		}	
		
}
	/*Go to Book a slot*/
@SuppressWarnings("serial")
public void bookASlot(String slotType) throws InterruptedException{
	/*Get the items from scroll view*/               
	List<WebElement> ele= driver.findElementsByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[1]/UIATableView[1]/UIATableCell");
    System.out.print("\nAailable Slot array size is "+ele.size());                    
    List<WebElement> ele2 = driver.findElementsByName("slot_day");
    System.out.print("\nAvailable slot days array size is "+ele2.size());
    outerloop: /*Outerloop for traversing between the slot days*/
    for (int j=1;j<=ele2.size();j++)
    {
    	innerloop: /*Innerloop for traversing between the slots*/
    	for (int i=1;i<=ele.size();i++)
    	{                                                                             
    	  RemoteWebElement slotButton = (RemoteWebElement) driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAElement[1]/UIATableView[1]/UIATableCell["+String.format("%d", i)+"]/UIAStaticText[1]"));
    	  System.out.print("\nSlot Button position is "+i+" and slot is "+slotButton.getText());
    	  slotButton.click();
    	if (driver.findElement(By.name("book_slot_btn")).isEnabled() == true)
    	{
          driver.findElement(By.name("book_slot_btn")).click();
          wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("continue_btn"))));
          System.out.print("\nBooked slot is "+driver.findElement(By.name("slot_reserved_date")).getText());
          driver.findElement(By.name("continue_btn")).click();
          break outerloop;
    	}
    } 
    if (driver.findElement(By.name("book_slot_btn")).isEnabled() == false)
    {
      if(slotType == "Home Delivery")
      {
    	  JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript("mobile: tap", new HashMap<String, Double>() {{ put("tapCount", (double) 2); put("touchCount", (double) 2); put("duration", 0.9); put("x", (double) HD_X+m); put("y", (double) 227); }});
          m=m+50;  
      }else
      {
    	  JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript("mobile: tap", new HashMap<String, Double>() {{ put("tapCount", (double) 2); put("touchCount", (double) 2); put("duration", 0.9); put("x", (double) CC_X+m); put("y", (double) 185); }});
          m=m+50;
      }
     
    }
    j++;
   }
  }

}
 



