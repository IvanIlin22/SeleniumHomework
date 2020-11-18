
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class myHomework12 {
	private WebDriver   driver;

	
	@Before
	public void         start() {
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
	}
	
	@Test
	public void         newProductTest() {
		driver.get("http://localhost/litecart/admin/");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
		
		//Catalog
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Catalog")).click();
		driver.findElement(By.linkText("Add New Product")).click();
		driver.findElement(By.cssSelector("label")).click();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("[name = 'name[en]']")).sendKeys("Cats");
		driver.findElement(By.name("code")).sendKeys("1234");
		driver.findElement(By.cssSelector("[value = '1-3']")).click();
		driver.findElement(By.name("quantity")).sendKeys("1");
		driver.findElement(By.cssSelector("[name = 'new_images[]']")).sendKeys((new File("src/test/java/cc.jpeg").getAbsolutePath()));
		driver.findElement(By.name("date_valid_from")).sendKeys("2020-11-18");
		driver.findElement(By.name("date_valid_to")).sendKeys("2020-11-28");

		
		//Information
		driver.findElement(By.linkText("Information")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement elem = driver.findElement(By.name("manufacturer_id"));
		Select select = new Select(elem);
		select.selectByValue("1");
		driver.findElement(By.name("keywords")).sendKeys("cat");
		driver.findElement(By.name("short_description[en]")).sendKeys("Мягкая игрушка Budi Basa Басик и мышка 22 см");
		driver.findElement(By.name("description[en]")).sendKeys("Мягкая игрушка Budi Basa Басик и мышка 22 см");
		driver.findElement(By.name("head_title[en]")).sendKeys("Budi Basa");
		driver.findElement(By.name("meta_description[en]")).sendKeys("Budi Basa");
		
		//Prices
		driver.findElement(By.linkText("Prices")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.name("purchase_price")).sendKeys("20");
		WebElement price = driver.findElement(By.name("purchase_price_currency_code"));
		Select sel = new Select(price);
		sel.selectByValue("USD");
		driver.findElement(By.name("prices[USD]")).sendKeys("20");
		driver.findElement(By.name("prices[EUR]")).sendKeys("18");
		driver.findElement(By.name("save")).click();
	}
	
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}