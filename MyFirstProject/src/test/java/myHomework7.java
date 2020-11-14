import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class myHomework7 {
	private WebDriver driver;
	private List<WebElement> elements;
	
	@Before
	public void start() {
		driver = new FirefoxDriver();
	}
	
	@Test
	public void testLitecart() {
		driver.get("http://localhost/litecart/admin");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
		
		int len = driver.findElements(By.id("app-")).size();
		
		for (int i = 0; i < len; i++) {
			elements = driver.findElements(By.cssSelector("[id = app-]"));
			System.out.println(i + "." + elements.get(i).getText());
			elements.get(i).click();
			
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			
			int lenChild = driver.findElements(By.cssSelector("ul.docs > li")).size();
			if (lenChild > 0) {
				for (int j = 0; j < lenChild; j++) {
					List<WebElement> childElements = driver.findElements(By.cssSelector("ul.docs > li"));
					childElements.get(j).click();
					checkHeader(driver);
				}
			}
			else {
				checkHeader(driver);
			}
		}
	}
	
	public void checkHeader(WebDriver driver) {
		if (isElement(driver)) {
			WebElement h1 = driver.findElement(By.cssSelector("[id = content] > h1"));
			System.out.println("\t" + "have a header " + h1.getText());
		} else {
			System.out.println("\t" + "haven`t a header ");
		}
	}
	
	public boolean isElement(WebDriver driver) {
		if (driver.findElements(By.cssSelector("[id = content] > h1")).size() > 0) {
			return true;
		}
		return false;
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}