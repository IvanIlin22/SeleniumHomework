import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class            myHomework9 {
	private WebDriver   driver;
	private int         bool;
	
	@Before
	public void         start() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void         sortTest() {
		driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
		
		//a
		List<WebElement> elements = driver.findElements(By.xpath("//tr[@class = 'row']"));
		int len = elements.size();
		
		for (int i = 0; i < len - 1; i++) {
			String country1 = elements.get(i).findElement(By.xpath("./td[5]")).getText();
			String country2 = elements.get(i + 1).findElement(By.xpath("./td[5]")).getText();
			
			bool = country1.compareTo(country2);
			if (bool > 0) {
				System.out.println("Counties are not sorted");
				break;
			}
		}
		
		//b
		for (int i = 0; i < len; i++) {
			String zoneCount = elements.get(i).findElement(By.xpath("./td[6]")).getText();
			if (zoneCount.compareTo("0") != 0) {
				elements.get(i).findElement(By.xpath("./td[7]")).click();
				List<WebElement> zones = driver.findElements(By.xpath("//table[@class = 'dataTable']//tr"));
				int lenZones = zones.size();
				
				for (int j = 1; j < lenZones - 2; j++) {
					String zoneCountry1 = zones.get(j).findElement(By.xpath("./td[3]")).getText();
					String zoneCountry2 = zones.get(j).findElement(By.xpath("./td[3]")).getText();
					bool = zoneCountry1.compareTo(zoneCountry2);
					if (bool > 0) {
						System.out.println("Counties are not sorted");
						break;
					}
				}
				driver.findElement(By.name("cancel")).click();
				elements = driver.findElements(By.xpath("//tr[@class = 'row']"));
			}
		}
		
		//2
		driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
		List<WebElement>nameGeoZone = driver.findElements(By.xpath("//td[@id = 'content']//tr"));
		for (int i = 1; i < nameGeoZone.size() - 1; i++) {
			nameGeoZone.get(i).findElement(By.xpath("./td[3]/a")).click();
			List<WebElement>editGeoZone = driver.findElements(By.xpath("//table[@class = 'dataTable']//tr"));
			for (int j = 1; j < editGeoZone.size() - 2; j++) {
				String country1 = editGeoZone.get(j).findElement(By.xpath("./td[3]//option[@selected='selected']")).getText();
				String country2 = editGeoZone.get(j).findElement(By.xpath("./td[3]//option[@selected='selected']")).getText();
				bool = country1.compareTo(country2);
				if (bool > 0) {
					System.out.println("Counties are not sorted");
					break;
				}
			}
			driver.findElement(By.name("cancel")).click();
			nameGeoZone = driver.findElements(By.xpath("//td[@id = 'content']//tr"));
		}
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}