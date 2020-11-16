import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class myHomework8 {
	private WebDriver driver;
	
	@Before
	public void start() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void litecartTest() {
		driver.get("http://localhost/litecart/en/");
		List<WebElement> elements = driver.findElements(By.className("product"));
		int len = elements.size();

		for (int i = 0; i < len; i++) {
			List<WebElement> element = elements.get(i).findElements(By.xpath(".//div[starts-with(@class, 'sticker')]"));
			if (element.size() == 1) {
				System.out.println(i + ". One sticker");
			} else {
				System.out.println(i + ". More then one sticker");
			}
			//System.out.println(elements.get(i).getText());
		}
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}