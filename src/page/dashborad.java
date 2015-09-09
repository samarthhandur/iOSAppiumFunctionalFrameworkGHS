package page;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.openqa.selenium.JavascriptExecutor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchableElement;
import io.appium.java_client.ios.IOSDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import static org.junit.Assert.*;
import appiumIOS.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class dashborad extends anonymousPage{
	public dashborad(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//public RemoteWebDriver driver;
	public AppiumDriver appiumDriver;
	private By signout = By.name("register");
	private By lhnButton = By.name("action_bar_up_navigation");
	private By lhn_browse_all = By.name("anonymous_shop_btn");
	private By lhn_favourite = By.name("lhn_favourites_accessibility_id");
	private By lhn_special_offer = By.name("lhn_specialoffers_accessibility_id");
	WebDriverWait wait = new WebDriverWait(driver, 30);

    //propertyFile prop = new propertyFile(driver);
   
    //Sign out function
    public void signOut(){
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lhnButton)));
    	driver.findElement(lhnButton).click();
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(signout)));
    	driver.findElement(signout).click();
    	driver.findElement(By.name("Sign-out")).click();
    }
    //Favorites browsing
    public void browseFav() throws InterruptedException{
    	//Explicitly waiting for dashboard
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lhnButton)));
    	driver.findElement(lhnButton).click();
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lhn_favourite)));
    	driver.findElement(lhn_favourite).click();
    	Thread.sleep(5000);
    	//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("favourites_layout_option"))));
    	driver.findElement(By.name("favourites_layout_option")).click();
    	assertEquals("My favourites", driver.findElement(By.name("title_label")).getText());
    }
    
    //Special offers browsing
    public void browseSpecialOffers(String Offername){
    	//Explicitly waiting for dashboard
    	//appiumDriver.swipe(150, 394, 153, 324, 2);
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("Shop your favourites"))));
    	driver.findElement(lhnButton).click();
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lhn_special_offer)));
    	driver.findElement(lhn_special_offer).click();
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name(Offername))));
    	driver.findElement(By.name(Offername)).click();
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("filter_by"))));
    	assertEquals(Offername, driver.findElement(By.name(Offername)).getText());
    }
    
    public void browseBasket(){
    	//Explicitly waiting for dashboard
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lhnButton)));
    	driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAElement[1]")).click();
    }
    
    public void emptyYourBasket()
    {	
    	browseBasket();
  	    JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("mobile: tap", new HashMap<String, Double>() {{ put("tapCount", (double) 1); put("touchCount", (double) 1); put("duration", 0.7883984374999999); put("x", (double) 301); put("y", (double) 92); }});
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("empty_basket"))));
    	driver.findElement(By.name("empty_basket")).click();
    	driver.switchTo().alert().accept();
    	wait.until(ExpectedConditions.elementToBeClickable(By.name("basket_empty_msg")));

    }
    
}
