package page;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkout extends bookSlotOperations {
	/*Default constructor*/
	public checkout(RemoteWebDriver driver) {
		super(driver);
	}
	/*Page class variables*/
	private By lhn = By.name("action_bar_up_navigation");
    private String pwd = "Tesco123_";
	private By basket = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAElement[1]");
	private By downBttn = By.name("arrow down button");
	private By slotInfo = By.name("slot_info_layout");
	private By checkoutBttn = By.name("check_out_btn");
	private By emptyBasketBttn = By.name("empty_basket");
	private By subBttn = By.name("substitution_btn");
	private By justCheckingLabel = By.name("Just Checking");
	private By continueBttn = By.name("continue_btn");
	private By chckoutLabel = By.name("Checkout");
	private By confirmPayment = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIAButton[2]");
	private By proceedForCheckout = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIAButton[1]");
	private By proceedForPayement = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIAButton[1]");
	private By forgotPassword = By.name("Forgotten your password?");
	private By justCheckingPage = By.name("£4 will be added at checkout to baskets under £40");
	private By OrderSummary = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]");
	WebDriverWait wait = new WebDriverWait(driver, 60);

	/*Invoking basket*/
	public void invokeBasket() throws InterruptedException{
		driver.findElement(lhn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("lhn_home_accessibility_id"))));
		driver.findElement(By.name("lhn_home_accessibility_id")).click();
		Thread.sleep(2000);
		driver.findElement(basket).click();
		if (checkElementVisibility(driver, "basket_empty_msg") == true){
			System.out.print("Basket is empty.");
		}
	}
	/*Get the slot details*/
	public void getBookedSlotDetails(){
		if (checkElementVisibility(driver, "slot_info_layout") == true){
			System.out.print("\nSlot is booked and details are "+driver.findElement(slotInfo).getText());
			}
		else
			System.out.print("\nSlot is not booked...!!!!!!");
	}
	
	/*Checkout process*/
	public void beganCheckoutProcess() throws InterruptedException{
		driver.findElement(checkoutBttn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(continueBttn)));
		System.out.print("\nEntered into "+driver.findElement(By.name("Just Checking")));
		driver.findElement(continueBttn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIASecureTextField[1]"))));
		System.out.print("\nEntered into "+driver.findElement(By.name("Checkout")));
	}
	public void usableNETCheckout() throws InterruptedException
	{
		/*enter checkout password*/
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIASecureTextField[1]")).sendKeys(pwd);
		driver.findElement(proceedForCheckout).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]"))));
		/*click proceed to payment*/
		driver.findElement(proceedForPayement).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(OrderSummary)));
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIASecureTextField[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIASecureTextField[1]")).sendKeys("123");
	    Thread.sleep(2000);
	    /*click confirm payment*/
	    driver.findElement(confirmPayment).click();
	    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("continue_btn"))));
	    driver.findElement(By.name("continue_btn")).click();
	    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lhn)));
	    System.out.print("\nOrder is placed and the details are "+driver.findElement(By.name("address_details")).getText()+"and slot details are "+driver.findElement(By.name("slot_details")).getText());
	}
	
}
