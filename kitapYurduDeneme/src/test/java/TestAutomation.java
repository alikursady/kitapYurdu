import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TestAutomation {

    public WebDriver driver;
    public HomePage homePage;
    public ProductPage productPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
    }

    @AfterEach
    public void tearDown() {
            driver.quit();
        }

    @Test
    public void testProductSearchAndAddToCart() {
        homePage.open();
        homePage.searchForProduct("roman");
        productPage.selectRandomProduct();
        productPage.addToCart();
        productPage.clickSepetimButton();
        productPage.clickSepeteGitButton();
    }
}
