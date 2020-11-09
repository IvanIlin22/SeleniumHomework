import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class myFirstTest {
	private WebDriver driver;
	
	@Before
	public void start() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void firstTest()	{
		driver.get("http://www.yandex.ru/");
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}