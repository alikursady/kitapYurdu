import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public By searchInput = By.id("search-input");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.kitapyurdu.com/");
    }

    public void searchForProduct(String keyword) {
        WebElement searchBox = driver.findElement(searchInput);
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
    }
}
