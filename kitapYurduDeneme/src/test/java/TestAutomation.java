import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestAutomation {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private final static Logger logger = Logger.getLogger(TestAutomation.class.getName());

    private String readFirstValueFromCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine().split(",")[0];
        }
    }

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

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
        String searchTerm;
        try {
            searchTerm = readFirstValueFromCSV("src/test/roman/roman.csv");
        } catch (IOException e) {
            logger.severe("CSV dosyasından veri okuma hatası: " + e.getMessage());
            searchTerm = "roman"; // Bir hata durumunda varsayılan arama terimi olarak "roman" kullanılsın.
        }
        homePage.searchForProduct(searchTerm);
        productPage.selectRandomProduct();
        productPage.addToCart();
        productPage.clickSepetimButton();
        productPage.clickSepeteGitButton();
        productPage.sepettekiMiktariArttirVeGuncelle();
        productPage.urunuSepettenKaldir();
        productPage.uyariMesajiKontrol();
    }
}
