package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class favouritesOperations extends checkout
{
private By editBttn = By.name("edit_btn");
private By refineBttn = By.name("filter_by");
private By doneBttn = By.name("done_btn");
private By deleteBttn = By.name("del_btn");
private By selectProduct = By.name("productlist_cell");
private By clearAllBttn = By.name("clear_all_filters");
private By defaultBttn = By.name("default_btn");
private By lowToHighBttn = By.name("lowtohigh_btn");
private By highToLowBttn = By.name("hightolow_btn");
private By aToz = By.name("atoz_btn");
private By zToa = By.name("ztoa_btn");
private By titleBttn = By.name("title");
private By applyBttn = By.name("header_apply_btn");

/*Default constructor*/
public favouritesOperations(RemoteWebDriver driver)
{
   super(driver);
}

/*adding items from favorites*/
public void addItemsFromFavoritesList(int numberOfItemsToBeAdded) throws InterruptedException
{
	browseFav();
	for(int i=1;i<=numberOfItemsToBeAdded;i++)
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editBttn)));
		driver.findElement(By.name("add_btn")).click();
	}
}

/*sorting operations*/
public void sortByOperation(String parameter) throws InterruptedException
{
	driver.findElement(refineBttn).click();
	switch(parameter){
	case "Default":
		driver.findElement(defaultBttn).click();
		Thread.sleep(2000);
		driver.findElement(applyBttn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editBttn)));
		break;
		
	case "Low to High":
		driver.findElement(lowToHighBttn).click();
		Thread.sleep(2000);
		driver.findElement(applyBttn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editBttn)));
		break;
		
	case "High to Low":
		driver.findElement(highToLowBttn).click();
		Thread.sleep(2000);
		driver.findElement(applyBttn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editBttn)));
		break;
	
	case "A to Z":
		driver.findElement(aToz).click();
		Thread.sleep(2000);
		driver.findElement(applyBttn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editBttn)));
		break;
		
	case "Z to A":
		driver.findElement(zToa).click();
		Thread.sleep(2000);
		driver.findElement(applyBttn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editBttn)));
		break;
		
	case "Special offers":
		driver.findElement(titleBttn).click();
		Thread.sleep(2000);
		driver.findElement(applyBttn).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editBttn)));
		break;
	}	
}

/*Delete favourites*/
public void deleteFavourites(int itemsToBeDeleted)
{
	driver.findElement(editBttn).click();
	//List<WebElement> elements = driver.findElementsByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell"); 
	for(int i=1;i<=itemsToBeDeleted;i++)
	{
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell["+String.format("%d", i)+"]/UIAStaticText[1]")).click();
	}
	driver.findElement(deleteBttn).click();
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(doneBttn)));
	driver.findElement(doneBttn).click();
}
}
