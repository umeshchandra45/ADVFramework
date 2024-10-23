package extentreports;



import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    public static ExtentReports extent = ExtentManager.getReporter();
    static Map<Integer, ITestContext> iITestContextMap = new HashMap<Integer, ITestContext>();
    static Map<Integer, String> testcasenameMap = new HashMap<Integer, String>();
    static ExtentTest test;

    public static synchronized String getITestCaseName() {
        return testcasenameMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void setITestCaseName(String testcaseName) {
        testcasenameMap.put((int) (long) (Thread.currentThread().getId()),testcaseName);
    }

    public static synchronized ITestContext getITestContext() {
        return iITestContextMap.get((int) (long) (Thread.currentThread().getId()));
    }
    public static synchronized void setITestContext(ITestContext context) {
        iITestContextMap.put((int) (long) (Thread.currentThread().getId()),context);
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
    public static boolean write(LogStatus log, String message){
        extentTestMap.get((int) (long) (Thread.currentThread().getId())).log(log, "Step message:", message);
        return true;
    }
    public static boolean write(LogStatus log, String stepName, String stepDetails){
        extentTestMap.get((int) (long) (Thread.currentThread().getId())).log(log, stepName, stepDetails);
        return true;
    }

    public static boolean write(LogStatus log, String stepName, String stepDetails, Throwable e){
        extentTestMap.get((int) (long) (Thread.currentThread().getId())).log(log, stepName, stepDetails);
        return true;
    }

    public static synchronized void endTest() {
        extentTestMap.get((int) (long) (Thread.currentThread().getId())).setEndedTime(new Date());
        extent.endTest(extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
         test= extent.startTest(testName, desc);
        test.setStartedTime(new Date());
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

        return test;
    }

//    public static synchronized boolean assignCategory (Collection<String> category){
//        category.forEach(new Consumer<String>() {
//            public void accept(String category) {
//                extentTestMap.get((int) (long) (Thread.currentThread().getId())).assignCategory(category);
//            }
//        });
//        return true;
//    }

    public static synchronized boolean assignAuthor(String author[]){
        for (int i=0; i<author.length; i++){
            extentTestMap.get((int) (long) (Thread.currentThread().getId())).assignAuthor(author[i]);
        }
        return true;
    }


    public static synchronized String  attacheScreenshot(String screenShotPath) {
        String screenshhot="";
        if(test!=null){
            screenshhot=test.addScreenCapture(screenShotPath);
            
        }
        return  screenshhot;
    }

}
