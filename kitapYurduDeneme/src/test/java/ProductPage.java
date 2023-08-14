import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {

    private By productItems = By.cssSelector("div.product-cr");
    private By cartButtonLocator = By.id("button-cart");
    private By sepetimButton = By.id("sprite-cart-icon");
    private By sepeteGitButton = By.id("js-cart");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectRandomProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-cr .name a"));

        if (products.size() > 0) {
            Random random = new Random();
            int randomProductIndex = random.nextInt(products.size());
            WebElement randomProduct = products.get(randomProductIndex);
            randomProduct.click();
            logger.info("Rastgele bir ürün seçildi.");
        } else {
            logger.warning("Ürün listesi boş.");
        }
    }

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartButtonLocator));
        cartButton.click();
        logger.info("Ürün sepete eklendi.");
    }

    public void clickSepetimButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sepetimButtonElem = wait.until(ExpectedConditions.elementToBeClickable(sepetimButton));
        sepetimButtonElem.click();
        logger.info("Sepetim butonuna tıklandı.");
    }

    public void clickSepeteGitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sepeteGitButtonElem = wait.until(ExpectedConditions.elementToBeClickable(sepeteGitButton));
        sepeteGitButtonElem.click();
        logger.info("Sepete Git butonuna tıklandı.");
    }
}
