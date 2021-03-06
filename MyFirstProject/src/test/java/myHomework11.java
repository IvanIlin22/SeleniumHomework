import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.stringtemplate.v4.ST;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class myHomework11 {
	private WebDriver   driver;

	
	@Before
	public void         start() {
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
	}
	
	@Test
	public void         openTest() {
		driver.get("http://localhost/litecart/en/create_account");
		driver.findElement(By.name("firstname")).sendKeys("Ivan");
		driver.findElement(By.name("lastname")).sendKeys("Ivanov");
		driver.findElement(By.name("address1")).sendKeys("Backstreet avenue");
		driver.findElement(By.name("postcode")).sendKeys("12345");
		driver.findElement(By.name("city")).sendKeys("Briton");
		
		WebElement element = driver.findElement(By.tagName("select"));
		Select select = new Select(element);
		select.selectByValue("US");
		
		String mail = givenMail();
		driver.findElement(By.name("email")).sendKeys(mail);
		driver.findElement(By.name("phone")).sendKeys("+79033875467");
		driver.findElement(By.name("password")).sendKeys("qwerty");
		driver.findElement(By.name("confirmed_password")).sendKeys("qwerty");
		driver.findElement(By.name("create_account")).click();
		driver.findElement(By.xpath("//div[@class = 'left']//a[@href = 'http://localhost/litecart/en/logout']")).click();
		
		driver.findElement(By.name("email")).sendKeys(mail);
		driver.findElement(By.name("password")).sendKeys("qwerty");
		driver.findElement(By.name("login")).click();
		driver.findElement(By.xpath("//div[@class = 'left']//a[@href = 'http://localhost/litecart/en/logout']")).click();
	}
	
	public String givenMail() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder(5);
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String mail = sb.toString();
		String[] domen = {"@yandex.ru", "@mail.ru", "@rambler.com", "@gmail.com", "@Ukr.net "};
		int index = 0 + (int)(Math.random() * 4);
		mail += domen[index];
		
		return mail;
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}