import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class myHomework14 {
	private WebDriver       driver;
	private WebDriverWait   waitDriver;

	
	@Before
	public void             start() {
		driver = new FirefoxDriver();
		waitDriver = new WebDriverWait(driver, 5);
	}
	
	@Test
	public void             windowTest() {
		driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
		
		driver.findElement(By.linkText("Afghanistan")).click();
		String mainWindow = driver.getWindowHandle();
		Set<String> oldWindows = driver.getWindowHandles();
		
		List<WebElement> links = driver.findElements(By.cssSelector("[class = 'fa fa-external-link']"));
		for (WebElement link : links) {
			link.click();
			String curWindow = waitDriver.until(anyWindowOtherThan(oldWindows));
			driver.switchTo().window(curWindow);
			driver.close();
			driver.switchTo().window(mainWindow);
		}
	}
	
	public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
		return new ExpectedCondition<String>() {
			public String apply(WebDriver driver) {
				Set<String> handles = driver.getWindowHandles();
				handles.removeAll(oldWindows);
				return handles.size() > 0 ? handles.iterator().next() : null;
			}
		};
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}