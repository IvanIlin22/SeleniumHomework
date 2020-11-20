
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class                myHomework13 {
	private WebDriver       driver;
	private WebDriverWait   waitDriver;

	
	@Before
	public void             start() {
		driver = new FirefoxDriver();
		waitDriver = new WebDriverWait(driver, 5);
		//driver = new FirefoxDriver();
	}
	
	@Test
	public void             cartTest() {
		driver.get("http://localhost/litecart/en/");
		
		for (int i = 0;  i < 3; i++) {
			driver.findElement(By.className("product")).click();
			
			WebElement count = driver.findElement(By.className("quantity"));
			String prev = count.getText();
			
			//if select is present
			if (isPresent(driver)) {
				Select select = new Select(driver.findElement(By.name("options[Size]")));
				select.selectByValue("Small");
			}
			//
			driver.findElement(By.name("add_cart_product")).click();
			
			waitDriver.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.findElement(By.className("quantity")).getText().compareTo(prev) != 0;
				}
			});
			driver.get("http://localhost/litecart/en/");
		}

		driver.findElement(By.linkText("Checkout »")).click();
		List<WebElement> del = driver.findElements(By.cssSelector("td.unit-cost"));
		int lenDelete = del.size();
		
		for (int i = 0; i < lenDelete; i++) {
			driver.findElement(By.name("remove_cart_item")).click();
			waitDriver.until(ExpectedConditions.stalenessOf(del.get(0)));
			del = driver.findElements(By.cssSelector("td.unit-cost"));
		}
	}
	
	public Boolean isPresent(WebDriver driver) {
		int len = driver.findElements(By.name("options[Size]")).size();
		if (len == 0) {
			return false;
		}
		return true;
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}