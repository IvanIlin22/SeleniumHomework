
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

import java.util.List;

public class                myHomework19 {
	private Application app;
	
	@Before
	public void             start() {
		app = new Application();
	}
	
	@Test
	public void             cartTest() {

		app.login();
		for (int i = 0;  i < 3; i++) {
			
			app.click();
			app.add();
		}

		app.check();
		app.delete();
	}
	
	@After
	public void stop() {
		app.quit();
	}
}

class                       Page {
	protected WebDriver        driver;
	protected WebDriverWait    waitDriver;
	
	public Page(WebDriver driver) {
		this.driver = driver;
		waitDriver = new WebDriverWait(driver, 10);
	}
}

class                       mainPage extends Page {
	
	public mainPage(WebDriver driver) {
		super(driver);
	}
	
	public void loginPage() {
		driver.get("http://localhost/litecart/en/");
	}
	
	public void clickProduct() {
		driver.findElement(By.className("product")).click();
	}
}

class                       addProductPage extends Page {
	
	public  addProductPage(WebDriver driver) {
		super(driver);
	}
	
	public void addProduct() {
		WebElement count = driver.findElement(By.className("quantity"));
		String prev = count.getText();
		
		//if select is present
		if (isPresent(driver)) {
			Select select = new Select(driver.findElement(By.name("options[Size]")));
			select.selectByValue("Small");
		}
		driver.findElement(By.name("add_cart_product")).click();
		
		waitDriver.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				
				return d.findElement(By.className("quantity")).getText().compareTo(prev) != 0;
			}
		});
		driver.navigate().back();
	}
	
	public void checkOut() {
		driver.findElement(By.linkText("Checkout »")).click();
	}
	
	public Boolean isPresent(WebDriver driver) {
		int len = driver.findElements(By.name("options[Size]")).size();
		if (len == 0) {
			return false;
		}
		return true;
	}
}

class                           deleteProductPage extends Page {
	
	public deleteProductPage(WebDriver driver) {
		super(driver);
	}
	
	public void deletePage() {
		List<WebElement> del = driver.findElements(By.cssSelector("td.unit-cost"));
		int lenDelete = del.size();
		
		for (int i = 0; i < lenDelete; i++) {
			driver.findElement(By.name("remove_cart_item")).click();
			waitDriver.until(ExpectedConditions.stalenessOf(del.get(0)));
			del = driver.findElements(By.cssSelector("td.unit-cost"));
		}
	}
}

class   Application {
	private WebDriver           driver;
	
	private mainPage            mPage;
	private addProductPage      aPage;
	private deleteProductPage   dPage;
	
	public Application() {
		driver = new FirefoxDriver();
		mPage = new mainPage(driver);
		aPage = new addProductPage(driver);
		dPage = new deleteProductPage(driver);
	}
	
	public void login() {
		mPage.loginPage();
	}
	
	public void click() {
		mPage.clickProduct();
	}
	
	public void add() {
		aPage.addProduct();
	}
	
	public void check() {
		aPage.checkOut();
	}
	
	public void delete() {
		dPage.deletePage();
	}
	
	public void quit() {
		driver.quit();
		driver = null;
	}
}