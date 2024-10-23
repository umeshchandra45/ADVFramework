package pages;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import steps.WebDriverManager;
import support.WaitFunctions;

public class AbstractPage {
	
	public static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);
    protected static WebDriver driver = null;
    private WebDriverWait wait;
    private WebElement webElement;
    private Duration waitTimeout = Duration.ofSeconds(15);


    public AbstractPage() {

        this.driver = WebDriverManager.getWebDriver();
    }

    protected WaitFunctions waitFor(WebElement webElement) {
        return new WaitFunctions(driver, webElement);
    }
    protected WaitFunctions waitFor(File file) {
        return new WaitFunctions(driver,file);
    }

    protected WaitFunctions waiting() {
        return new WaitFunctions(driver);
    }
    public void navigateToPreviousPage(){
        driver.navigate().back();
        }

}
