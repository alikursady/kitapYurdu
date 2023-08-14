import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;

public class BasePage {
    public WebDriver driver;
    public Logger logger = Logger.getLogger(BasePage.class.getName());

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
