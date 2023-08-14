import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.junit.Assert;

public class ProductPage extends BasePage {

    private final static Logger logger = Logger.getLogger(ProductPage.class.getName());

    private By productItems = By.cssSelector("div.product-cr");
    private By cartButtonLocator = By.id("button-cart");
    private By sepetimButton = By.id("sprite-cart-icon");
    private By sepeteGitButton = By.id("js-cart");
    private By kitapMiktariBirOlanInput = By.cssSelector("input[value='1']");
    private By yenileButton = By.cssSelector(".fa-refresh");
    private By sepetGuncellemeMesaji = By.xpath("//h2[@class='swal2-title ky-swal-title-single' and @id='swal2-title']");
    private By sepettenKaldirButonu = By.cssSelector(".fa.fa-times.red-icon");
    private By uyariMesaji = By.xpath("//div[.='Sepetinizdeki ürünleri görmek için üye girişi yapmanız gerekmektedir.']");



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

        try {
            // Sepete Eklenme sorunu olduğundan 3 sn bekle.
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.severe("Bekleme işlemi sırasında bir hata meydana geldi: " + e.getMessage());
        }
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

    public void sepettekiMiktariArttir() {
        WebDriverWait bekleme = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement miktarElementi = bekleme.until(ExpectedConditions.elementToBeClickable(kitapMiktariBirOlanInput));

        int mevcutMiktar = Integer.parseInt(miktarElementi.getAttribute("value"));
        mevcutMiktar++;
        miktarElementi.clear();
        miktarElementi.sendKeys(String.valueOf(mevcutMiktar));
        logger.info("Sepetteki miktar 1 arttırıldı. Yeni miktar: " + mevcutMiktar);
    }

    public void yenileButonunaTikla() {
        try {
            WebElement yenileButonElem = driver.findElement(yenileButton);
            yenileButonElem.click();
            logger.info("Yenile butonuna tıklandı.");
        } catch (NoSuchElementException e) {
            logger.warning("Yenile butonu bulunamadı.");
        }
    }

    public void sepetGuncelleniyorKontrol() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sepetGuncellemeMesaji));
            logger.info("sepetiniz güncelleniyor mesajı görüntülendi.");
        } catch (TimeoutException e) {
            logger.warning("Sepetiniz güncelleniyor mesajı görüntülenmedi.");
            Assert.fail("Sepetiniz güncelleniyor mesajı beklenen süre içinde görüntülenmedi.");
        }
    }

    public void sepettekiMiktariArttirVeGuncelle() {
        sepettekiMiktariArttir(); // Miktarı burada artırıyoruz
        yenileButonunaTikla();
        sepetGuncelleniyorKontrol();
    }
    public void urunuSepettenKaldir() {
        try {
            WebElement kaldirButonu = driver.findElement(sepettenKaldirButonu);
            kaldirButonu.click();
            logger.info("Ürün sepetten kaldırıldı.");
        } catch (NoSuchElementException e) {
            logger.warning("Sepetten ürün kaldırma butonu bulunamadı.");
        }
    }
    public void uyariMesajiKontrol() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(uyariMesaji));
            logger.info("Mesaj görüntülendi! :)");
        } catch (TimeoutException e) {
            logger.warning("Mesaj görüntülenmedi.");
            Assert.fail("Mesaj beklenen süre içinde görüntülenmedi.");
        }
    }


}
