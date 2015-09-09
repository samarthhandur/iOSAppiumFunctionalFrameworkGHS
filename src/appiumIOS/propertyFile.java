package appiumIOS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.commonFunctionalites;

public class propertyFile extends commonFunctionalites{


	public String getProp(String key){
		File fs = new File("/Users/Samarth/Documents/Selenium/Workspace/appiumIOS/src/appiumIOS/data.properties");
		FileInputStream inputfile = null;
		try{
			inputfile = new FileInputStream(fs);
		}catch(FileNotFoundException e){
			System.out.print("Please ensure property file has RW permission");
		}
		Properties property = new Properties();
		try{
			property.load(inputfile);
		}catch(IOException e){
			System.out.print("fatal: i/o load error..!!!");
		}
		
		return property.getProperty(key);
	}
}
