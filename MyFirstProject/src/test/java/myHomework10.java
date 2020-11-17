import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class myHomework10 {
	private WebDriver   driver;
	private int         bool;
	private String      mainName;
	private String      mainRegularPrice;
	private String      mainSale;
	private String      Name;
	private String      RegularPrice;
	private String      Sale;
	private String      mainCPrice;
	private String      mainDPrice;
	private String      mainCSale;
	private String      mainBoldSale;
	private String      CPrice;
	private String      DPrice;
	private String      CSale;
	private String      BoldSale;
	
	@Before
	public void         start() {
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
	}
	
	@Test
	public void         openTest() {
		driver.get("http://localhost/litecart/en/");
		WebElement duck = driver.findElement(By.xpath("//div[@id = 'box-campaigns']"));
		
		mainName = duck.findElement(By.xpath(".//div[@class = 'name']")).getText();
		mainRegularPrice = duck.findElement(By.xpath(".//s[@class = 'regular-price']")).getText();
		mainSale = duck.findElement(By.xpath(".//strong[@class = 'campaign-price']")).getText();
		mainCPrice = duck.findElement(By.xpath(".//s[@class = 'regular-price']")).getCssValue("color");
		mainDPrice = duck.findElement(By.xpath(".//s[@class = 'regular-price']")).getCssValue("text-decoration-line");
		mainCSale = duck.findElement(By.xpath(".//strong[@class = 'campaign-price']")).getCssValue("color");
		mainBoldSale = duck.findElement(By.xpath(".//strong[@class = 'campaign-price']")).getCssValue("font-weight");
		
		//в
		//Chrome
		/*V(Integer.parseInt(mainCPrice.substring(5, 8)) , Integer.parseInt(mainCPrice.substring(10, 13)),
			Integer.parseInt(mainCPrice.substring(10, 13)), mainDPrice.substring(0, 12));*/
		//Firefox
		V(Integer.parseInt(mainCPrice.substring(4, 7)) , Integer.parseInt(mainCPrice.substring(9, 12)),
			Integer.parseInt(mainCPrice.substring(9, 12)), mainDPrice.substring(0, 12));
		
		//г
		//Chrome
		//G(Integer.parseInt(mainBoldSale), Integer.parseInt(mainCSale.substring(10, 11)), Integer.parseInt(mainCSale.substring(13, 14)));
		//Firefox
		G(Integer.parseInt(mainBoldSale), Integer.parseInt(mainCSale.substring(9, 10)), Integer.parseInt(mainCSale.substring(12, 13)));
		
		//д
		D(Integer.parseInt(duck.findElement(By.xpath(".//strong[@class = 'campaign-price']")).getCssValue("font-size").substring(0, 2)),
			Integer.parseInt(duck.findElement(By.xpath(".//s[@class = 'regular-price']")).getCssValue("font-size").substring(0, 2)));
		
		driver.findElement(By.xpath("//div[@id = 'box-campaigns']//a")).click();
		WebElement pageDuck = driver.findElement(By.xpath("//div[@id = 'box-product']"));
		Name = pageDuck.findElement(By.xpath(".//h1[@class = 'title']")).getText();
		RegularPrice = pageDuck.findElement(By.xpath(".//s[@class = 'regular-price']")).getText();
		Sale =pageDuck.findElement(By.xpath(".//strong[@class = 'campaign-price']")).getText();
		CPrice = pageDuck.findElement(By.xpath(".//s[@class = 'regular-price']")).getCssValue("color");
		DPrice = pageDuck.findElement(By.xpath(".//s[@class = 'regular-price']")).getCssValue("text-decoration-line");
		CSale = pageDuck.findElement(By.xpath(".//strong[@class = 'campaign-price']")).getCssValue("color");
		BoldSale = pageDuck.findElement(By.xpath(".//strong[@class = 'campaign-price']")).getCssValue("font-weight");
		
		
		//a
		if (Name.compareTo(mainName) == 0 ) {
			System.out.println("на главной странице и на странице товара совпадает текст названия товара");
		}
		
		//б
		if (RegularPrice.compareTo(mainRegularPrice) == 0 && Sale.compareTo(mainSale) == 0) {
			System.out.println("на главной странице и на странице товара совпадают цены (обычная и акционная)");
		}
		
		//в
		//Chrome
		/*V(Integer.parseInt(CPrice.substring(5, 8)) , Integer.parseInt(CPrice.substring(10, 13)),
			Integer.parseInt(CPrice.substring(10, 13)), DPrice.substring(0, 12));*/
		//Firefox
		V(Integer.parseInt(CPrice.substring(4, 7)) , Integer.parseInt(CPrice.substring(9, 12)),
			Integer.parseInt(CPrice.substring(9, 12)), DPrice.substring(0, 12));
		
		//г
		//Chrome
		//G(Integer.parseInt(BoldSale), Integer.parseInt(CSale.substring(10, 11)), Integer.parseInt(CSale.substring(13, 14)));
		//Firefox
		G(Integer.parseInt(mainBoldSale), Integer.parseInt(CSale.substring(9, 10)), Integer.parseInt(CSale.substring(12, 13)));
		
		//д
		D(Integer.parseInt(pageDuck.findElement(By.xpath(".//strong[@class = 'campaign-price']")).getCssValue("font-size").substring(0, 2)),
			Integer.parseInt(pageDuck.findElement(By.xpath(".//s[@class = 'regular-price']")).getCssValue("font-size").substring(0, 2)));
	}
	
	public void         V(int val1, int val2, int val3, java.lang.String str) {
		if (val1 == val2 && val2 == val3 && str.compareTo("line-through") == 0) {
			System.out.println("обычная цена зачёркнутая и серая");
		}
	}
	
	public void         G(int val1, int val2, int val3) {
		if (val1 > 400 & val2 == 0 && val3 == 0) {
			System.out.println("акционная жирная и красная");
		}
	}
	
	public void         D(int val1, int val2) {
		if (val1 > val2) {
			System.out.println("акционная цена крупнее, чем обычная");
		}
	}
	
	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}