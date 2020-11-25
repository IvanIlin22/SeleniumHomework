import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class myHomework17 {
	private WebDriver       driver;
	private WebDriverWait   waitDriver;

	
	@Before
	public void             start() {
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		waitDriver = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void             logsTest() {
		driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		WebElement button = driver.findElement(By.name("login"));
		button.click();
		waitDriver.until(ExpectedConditions.stalenessOf(button));
		
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Cats")));
		int len = driver.findElements(By.cssSelector("[title=Edit]")).size();
		List<WebElement> links = driver.findElements(By.cssSelector("[title=Edit]"));
		for (int i = 1; i < len; i++) {
			links.get(i).click();
			driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
			driver.navigate().back();
			links = driver.findElements(By.cssSelector("[title=Edit]"));
		}
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}