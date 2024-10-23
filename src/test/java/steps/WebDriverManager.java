package steps;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import extentreports.ExtentManager;
import extentreports.ExtentTestManager;
import support.DriverSupport;
import support.PropertiesReader;

public class WebDriverManager {

	public static final String defLocale = "de_AT";
	private static WebDriver webdriver;
	private static ThreadLocal<WebDriver> threadLocale;

	
	@BeforeClass
	public void beforeClass() {
//		threadLocale = new ThreadLocal<WebDriver>();
//		webdriver = DriverSupport.getDriver();
//		threadLocale.set(webdriver);
//		threadLocale.get().manage().window().maximize();
//		homePage();

	}
	

	@BeforeMethod
	public void beforeMethod() {
		threadLocale = new ThreadLocal<WebDriver>();
		webdriver = DriverSupport.getDriver();
		threadLocale.set(webdriver);
		threadLocale.get().manage().window().maximize();
		homePage();

	}

	private void homePage() {
		threadLocale.get().navigate().to(getHomePage());
	}

	private String getHomePage() {
		System.out.println("getRequiredSystemProperty" + getRequiredSystemProperty("locale"));
		return PropertiesReader.getKey(getRequiredSystemProperty("locale"));
	}

	public static String getRequiredSystemProperty(String name) {
		
		try {
			System.out.println("System.getProperty(name)"+System.getProperty(name));
			if (System.getProperty(name)==null||System.getProperty(name).isEmpty()) {

					return defLocale;
			}
			else {
			
				return Optional.ofNullable(System.getProperty(name)).orElseThrow(() -> new IllegalArgumentException(
						String.format("Required system property '%s' is missing", name)));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
//		if (!System.getProperty(name).isEmpty()&&System.getProperty(name)!=null) {
//			
//
//		} else {
//			try {
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return defLocale;

	}

    @AfterMethod
    public void afterMethod(){
        System.out.println("after method");
        String fileName = "ExtentOutput.html";
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
        File source = new File(ExtentManager.folderPath() + fileName);
        File dest = new File("test-output/JenkinsReport/"+fileName);
        try {
            FileUtils.copyFile(source,dest);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("extent report end");

    }
	public static WebDriver getWebDriver() {
		if (threadLocale != null) {
			return threadLocale.get();
		}
		return null;

	}

}
