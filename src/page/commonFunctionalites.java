package page;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.ios.IOSDriver;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("deprecation")
public class commonFunctionalites {
	public RemoteWebDriver driver;
	
	public void swipeRIGHT(String name){
		MobileElement ele = (MobileElement) driver.findElement(By.name(name));
		ele.swipe(SwipeElementDirection.RIGHT, 2000);
	}
	
	public void swipeUP(String name){
		MobileElement ele = (MobileElement) driver.findElement(By.name(name));
		ele.swipe(SwipeElementDirection.UP, 2000);
	}
	
	
	public void goToHomePage(){
   // MobileElement backButton= (MobileElement) driver.findElement(By.name("action_bar_up_navigation"));

	WebElement actionBar = driver.findElement(By.name("action_bar_up_navigation"));
	WebElement triat= (WebElement) driver.findElement(By.name("dashboard_context_card_title_bar"));
	
	if (actionBar.isEnabled()){
			actionBar.click();
		}else{
			while (triat.isEnabled() == true){
				System.out.print("on Home page");
			}
		}
			
	}
	
	
}
