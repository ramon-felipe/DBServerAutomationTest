package base;

import static driver.DriverFactory.GetDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import helper.WaitHelper;

public class DSL {
	
	private Actions actions;
	private WebElement element;
	private WaitHelper waitHelper;
	
	public DSL() {
		this.actions = new Actions(GetDriver());
		this.waitHelper = new WaitHelper();
	}
	
	public void Click(By by) {
		waitHelper.WaitUntilClickable(by).click();
	}
	
	public void Click(String XPath) {
		waitHelper.WaitUntilClickable(XPath).click();
	}
	
	public void ClickOnRadioButton(String radioTitle) {		
		String XPath = "//div[contains(@class, 'radio-inline')]/*[text()[contains(.,'" + radioTitle + "')]]";
		
		this.Click(XPath);
	}
	
	public void ClickOnButton(String buttonText) {
		this.Click("//button[span[normalize-space(text())='" + buttonText + "']]");
	}
	
	public void SendKeys(String fieldName, String text) {
		waitHelper.WaitUntilVisible(By.xpath("//label[normalize-space(text())='" + fieldName + "']/following-sibling::input")).sendKeys(text);
	}
	
	public void SendKeys(By by, String text) {
		waitHelper.WaitUntilVisible(by).sendKeys(text);
	}
	
	public void MouseOver(By by) {
		this.element = waitHelper.WaitUntilClickable(by); 
		actions.moveToElement(element).build().perform();
	}
	
	public String GetElementText(By by) {
		return waitHelper.WaitUntilVisible(by).getText();
	}
	
	public String GetElementText(WebElement element) {
		return waitHelper.WaitUntilVisible(element).getText();
	}
	
	public void SelectComboValue(String fieldName, String id, String value) {
		String elementXPath = "//label[normalize-space(text())='" + fieldName + "']/following::select[@id='" + id + "']/option[@value='" + value + "']";
		waitHelper.WaitUntilClickable(By.xpath(elementXPath)).click();
	}
	
	public void SelectComboValue(String fieldName, String value) {
		String elementXPath = "//label[normalize-space(text())='" + fieldName + "']/following::select/option[@value='" + value + "']";
		waitHelper.WaitUntilClickable(By.xpath(elementXPath)).click();
	}
	
	public void SelectComboOption(String fieldName, String option) {
		String elementXPath = "//label[normalize-space(text())='" + fieldName + "']/following::select/option[text()='" + option + "']";
		waitHelper.WaitUntilClickable(By.xpath(elementXPath)).click();
	}
	
}
