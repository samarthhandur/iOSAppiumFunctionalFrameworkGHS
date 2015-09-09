package appiumIOS;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import page.amendJourney;
import page.anonymousPage;
import page.bookSlotOperations;
import page.checkout;
import page.dashborad;
import page.favouritesOperations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import appiumIOS.propertyFile;

public class appiumTest{
	private static RemoteWebDriver driver;
	propertyFile prop = new propertyFile();
	@BeforeClass
	public static void setUp() throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "8.1");
		capabilities.setCapability("deviceName", "iPhone 6")	;
		capabilities.setCapability("app", "/Users/Samarth/Documents/Selenium/Workspace/appiumIOS/app/GroceryiOS.app");
		driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
@Test
public void testSignInSignOut() throws InterruptedException, ClientProtocolException, ParseException, IOException, org.json.simple.parser.ParseException, JSONException, URISyntaxException, HttpException{
	responseURL obj2 = new responseURL(driver);
	obj2.loginAccess();
	/*add items with count*/
	obj2.addItemsWithCount("milk", 8);
	anonymousPage obj = new anonymousPage(driver);
	if (driver.findElement(By.name("signin_btn")).isEnabled() == true){
		System.out.print("\nUser is in anonymous mode");
		obj.signIn();
	}
	/*Sign out*/
	dashborad obj1 = new dashborad(driver);
	obj1.signOut();
}
 @Test
public void getProd() throws ClientProtocolException, IOException, ParseException, org.json.simple.parser.ParseException, JSONException, URISyntaxException, HttpException, InterruptedException{
	 responseURL obj = new responseURL(driver);
	obj.loginAccess();
	obj.getSearchProducts("Beef");
}

@Test
public void searchAnonymous() throws InterruptedException{
	anonymousPage obj = new anonymousPage(driver);
	if ( obj.checkElementVisibility(driver, "sign_in") == false)
	{ 
		dashborad obj1 = new dashborad(driver);
		System.out.print("\nUser is in anonymous mode");
		obj1.signOut();
	}
		obj.anonymousSearch();
}
	
@Test
public void anonymousShop() throws Exception
{
	anonymousPage obj = new anonymousPage(driver);
	if ( obj.checkElementVisibility(driver, "sign_in")== false){
		dashborad obj1 = new dashborad(driver);
		System.out.print("\nUser is in anonymous mode");
		obj1.signOut();
	}
		obj.anmShop();
	    Assert.assertEquals("Sign in", driver.findElement(By.name("Sign in")).getText());
}

@Test
public void clubcardTest() throws InterruptedException
{
	anonymousPage obj = new anonymousPage(driver);
    obj.addNewClubcard("634004024126176181", "LL219YB");
}

@Test
public void favourites_Test() throws InterruptedException{
	dashborad obj = new dashborad(driver);
	if ( obj.checkElementVisibility(driver, "sign_in") == true){
		dashborad obj1 = new dashborad(driver);
		System.out.print("\nUser is in anonymous mode");
		obj1.signIn();
	}
    obj.browseFav();
    anonymousPage obj1 = new anonymousPage(driver);
    obj1.addItems();
}

@Test
public void special_OfferTest() throws InterruptedException{
	dashborad obj = new dashborad(driver);
	bookSlotOperations obj1 = new bookSlotOperations(driver);
	if (obj1.checkElementVisibility(driver, "signin_btn") == true){
		obj.signIn();
	}
    obj.browseSpecialOffers("All Offers");
}

@Test
public void addItemsToBasketTest() throws Exception{
	anonymousPage obj = new anonymousPage(driver);
	bookSlotOperations obj1 = new bookSlotOperations(driver);

	if (obj1.checkElementVisibility(driver, "signin_btn") == true){
		obj.signIn();
	}
	obj1.browseSpecialOffers("Top Offers");
	obj.addItems();
}
@Test
public void addItemsTest() throws ClientProtocolException, ParseException, IOException, org.json.simple.parser.ParseException, InterruptedException, JSONException, URISyntaxException, HttpException{
	responseURL obj = new responseURL(driver);
	obj.loginAccess();
	//obj.getSearchProducts("milk");
	//add items with count
	obj.addItemsWithCount("milk", 8);
	//sign in 
	anonymousPage signin = new anonymousPage(driver);
	signin.signIn();
}
@Test
public void emptyBasketApp() throws InterruptedException
{
	bookSlotOperations obj1 = new bookSlotOperations(driver);
	if (obj1.checkElementVisibility(driver, "signin_btn") == true)
	{
		anonymousPage obj = new anonymousPage(driver);
		obj.signIn();
	}
	obj1.emptyYourBasket();
}

@Test
public void emptyBasketTest() throws ClientProtocolException, ParseException, IOException, org.json.simple.parser.ParseException, InterruptedException, JSONException, URISyntaxException, HttpException{
	responseURL obj = new responseURL(driver);
	obj.loginAccess();
	obj.addItemsWithCount("milk", 8);
	//sign in 
	anonymousPage signin = new anonymousPage(driver);
	signin.signIn();
	/*fire empty basket call*/
	obj.emptyBasket();
	/*Sign out and Sign in to check empty basket*/
	dashborad signout = new dashborad(driver);
	signout.signOut();
	signin.signIn();
}

@Test
public void bookSlotTest() throws InterruptedException{
	anonymousPage obj = new anonymousPage(driver);
	bookSlotOperations obj1 = new bookSlotOperations(driver);
	if (obj1.checkElementVisibility(driver, "signin_btn") == true){
		obj.signIn();
	}else{
		System.out.print("\nAlready logged in...");
	}
	obj1.selectSlotType("Click & Collect");
	obj1.bookASlot("Click & Collect");
}

@Test
public void chooseSlotTest() throws ClientProtocolException, ParseException, IOException, ParseException, org.apache.http.ParseException, JSONException, URISyntaxException, HttpException, InterruptedException{
	responseURL obj = new responseURL(driver);
	obj.loginAccess();
    obj.getAvailableSlotHD();
    obj.chooseDeliverySlot();
}

@Test
public void checkoutUserJourneyTest() throws ClientProtocolException, org.apache.http.ParseException, IOException, ParseException, JSONException, URISyntaxException, HttpException, InterruptedException{
	responseURL obj = new responseURL(driver);
    obj.loginAccess();
    obj.emptyBasket();
    obj.addItemsWithCount("beef", 8);
    obj.getAvailableSlotHD();
    obj.chooseDeliverySlot();
	bookSlotOperations obj1 = new bookSlotOperations(driver);
	if (obj1.checkElementVisibility(driver, "signin_btn") == true){
		obj.signIn();
	}else{
		obj1.signOut();
		obj1.signIn();
	}
	checkout obj2 = new checkout(driver);
	obj2.invokeBasket();
	obj2.getBookedSlotDetails();
	obj2.beganCheckoutProcess();
	obj2.usableNETCheckout();
}

@Test
public void testAmend() throws ClientProtocolException, org.apache.http.ParseException, IOException, ParseException, JSONException, URISyntaxException, HttpException, InterruptedException{
	responseURL obj = new responseURL(driver);
    obj.loginAccess(); 
    obj.amendOrder();
    bookSlotOperations obj1 = new bookSlotOperations(driver);
	if (obj1.checkElementVisibility(driver, "signin_btn") == true){
		obj.signIn();
	}else{
		obj1.signOut();
		obj1.signIn();
	}
    amendJourney obj2 = new amendJourney(driver);
    obj2.changeSlot();
    Thread.sleep(5000);
}

@Test
public void favoritesTest() throws ClientProtocolException, org.apache.http.ParseException, IOException, ParseException, JSONException, URISyntaxException, HttpException, InterruptedException{
	responseURL obj = new responseURL(driver);
	if (obj.checkElementVisibility(driver, "signin_btn") == true){
		obj.signIn();
	}
    obj.loginAccess();
    obj.favourites();
	favouritesOperations obj1 = new favouritesOperations(driver);
	obj1.addItemsFromFavoritesList(3);
}
@AfterClass
public static void tearDown(){
	driver.quit();
}
}

