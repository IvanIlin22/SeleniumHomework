import io.cucumber.java8.En;
import static org.junit.Assert.*;

public class MyStepdefs implements En{
	private Application app = new Application();
	
	public MyStepdefs() {
		When("^we login to page and add product to basket$", () -> {
		app.login();
		for (int i = 0; i < 3; i++) {
			app.click();
			app.add();
		}
		});
		Then("^products are in the basket$", () -> {
			assertEquals(app.length(), 3);
		});
		And("^delete product from basket and quit$", () -> {
			app.check();
			app.delete();
			app.quit();
		});
	}
}
