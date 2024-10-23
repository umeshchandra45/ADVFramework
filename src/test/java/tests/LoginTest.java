package tests;

import java.io.File;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import extentreports.ExtentTestManager;
import pages.LoginPage;
import steps.WebDriverManager;
import support.ExcelUtilties;

public class LoginTest extends WebDriverManager {
	public static String securityPath = System.getProperty("user.dir") + "/src/test/resources/UserNamePassword.xlsx";

	@Test( dataProvider = "loginData",groups="Regression")
	public void testGoogle(Map<Object, Object> objectMap) {
//		System.out.println("TestNG.getDefault().getGroups()"+TestNG.getGroups());

//        ExtentTest extentTest=new ExtentTest("Login Test Case","Login Test Case");
		
		ExtentTestManager.startTest(this.getClass().getName());
		ExtentTestManager.getTest().log(LogStatus.INFO, /* "Test case started..." + */ this.getClass().getTypeName());
		new LoginPage(objectMap);

	}
//	@Test( dataProvider = "loginData",groups = "Regression",enabled = true)
//	public void testGoogle1(Map<Object, Object> objectMap) {
////		System.out.println("TestNG.getDefault().getGroups()"+TestNG.getGroups());
//        System.out.println("t1");
////        ExtentTest extentTest=new ExtentTest("Login Test Case","Login Test Case");
//		
//		ExtentTestManager.startTest(this.getClass().getName());
//		ExtentTestManager.getTest().log(LogStatus.INFO, /* "Test case started..." + */ this.getClass().getTypeName());
//		LoginPage loginPage = new LoginPage(objectMap);
//
//	}
//	@Test(groups = "Regression", dataProvider = "loginData",enabled = true)
//	public void testGoogle2(Map<Object, Object> objectMap) {
//	//	System.out.println("TestNG.getDefault().getGroups()"+TestNG.getGroups());
//		System.out.println("t2");
////        ExtentTest extentTest=new ExtentTest("Login Test Case","Login Test Case");
//		
//		ExtentTestManager.startTest(this.getClass().getName());
//		ExtentTestManager.getTest().log(LogStatus.INFO, /* "Test case started..." + */ this.getClass().getTypeName());
//		LoginPage loginPage = new LoginPage(objectMap);
//
//	}

	@DataProvider(name = "loginData")
	public static Object[][] securityLoginData() throws Exception {
		File filePath = new File(securityPath);
		Object[][] testObjArray = ExcelUtilties.getTestDataMap(filePath, "LoginCredsNew");

		return testObjArray;
	}
	
}
