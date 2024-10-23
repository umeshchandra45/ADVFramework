package support;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

public class Scroller {
	
	public static void ScrollToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();   
}


public static void ScrollToTop(WebDriver driver) {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
}


public static void ScrollDown(WebDriver driver, WebElement element, int horScroll, int verScroll) {
    
    WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(element);
    Actions actions = new Actions(driver);
    actions.scrollFromOrigin(scrollOrigin, horScroll, verScroll);
    actions.perform();
}


public static void scrollElementToCenter(WebDriver driver, WebElement elementToCenter) {
    String scrollElementIntoMiddle =
            "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
    ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, elementToCenter);
}

}
