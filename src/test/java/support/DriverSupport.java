package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import listeners.EventListener;



public class DriverSupport extends EventFiringDecorator<WebDriver>{
	private static WebDriver webdriver;
	
	public static final String defaultDriver="chrome";
	public static final String defaultHeadLess="false";
	
	public static WebDriver getDriver() {
		String driverType=System.getProperty("Driver",defaultDriver);
		System.out.println("driverType"+driverType);
		String headlessStr=System.getProperty("headless",defaultHeadLess);
		System.out.println("headlessStr"+headlessStr);

		
		switch (driverType) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();

	        if(headlessStr.equalsIgnoreCase("true")) {
	        	chromeOptions.addArguments("--headless");
	        	
	        }
	        
	        // Add argument to disable the automation message
//	        chromeOptions.addArguments("disable-infobars");  // Old way (sometimes works)
	        
	        // Add argument to disable automation control
	        chromeOptions.setExperimentalOption("useAutomationExtension", false);
	        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
	        webdriver=new EventFiringDecorator<WebDriver>(new EventListener()).decorate(new ChromeDriver(chromeOptions));

	        return webdriver;
	        

		case "firefox":
			FirefoxOptions firefoxOptions=new FirefoxOptions();
			
			if(headlessStr.equalsIgnoreCase("true")) {
				firefoxOptions.addArguments("--headless");
	        }
			webdriver=new EventFiringDecorator<WebDriver>(new EventListener()).decorate(new FirefoxDriver(firefoxOptions));
	        
			return webdriver;
		case "edge":
			EdgeOptions  edgeOptions=new EdgeOptions();
			if(headlessStr.equalsIgnoreCase("true")) {
				edgeOptions.addArguments("--headless");
	        }
			
			webdriver=new EventFiringDecorator<WebDriver>(new EventListener()).decorate(new EdgeDriver(edgeOptions));
			
			return webdriver;

		}
		
		return webdriver;
		
	}


}
