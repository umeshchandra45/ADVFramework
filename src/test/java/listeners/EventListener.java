package listeners;


import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

//import com.relevantcodes.extentreports.LogStatus;
//import extentReport.ExtentManager;
//import extentReport.ExtentTestManager;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;

import com.relevantcodes.extentreports.LogStatus;

import extentreports.ExtentManager;
import extentreports.ExtentTestManager;
import steps.WebDriverManager;





public class EventListener implements WebDriverListener, ITestListener {
    private static final int SCALE_FACTOR = 3;
    Logger loggerFactory=LoggerFactory.getLogger(EventListener.class);
    String destination="";
    
    @Override
    public void beforeClick(WebElement element) {
        System.out.println("BeforeClick is Calling");

            try {
                File source =  ((TakesScreenshot) WebDriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
//                destination=System.getProperty("user.dir")+"/Screenshot"+System.currentTimeMillis()+".png";
//                destination = "test-output/ExtentReport/"+ "Run_" + System.currentTimeMillis()  +".png";
                String reportDate=ExtentManager.returnDate();
                destination = "test-output/ExtentReport/"+ "Run_" + reportDate + "/"+"Screenshot"+".png" ;
                File dest=new File(destination);
                System.out.println("source"+source);
                System.out.println("destination"+destination);
//                FileUtils.copyFile(source,dest);
                String fileAbolutPath=dest.getAbsolutePath();
//                ExtentTestManager.attacheScreenshot(fileAbolutPath);

                ExtentTestManager.getTest().log(LogStatus.valueOf("Sucess"),"SnapShot"+ExtentTestManager.attacheScreenshot(fileAbolutPath));
                ExtentManager.getReporter().flush();
            }catch (Exception e){
                ExtentTestManager.write(LogStatus.FAIL,destination);
                ExtentManager.getReporter().flush();
                e.printStackTrace();
            }

    }
//
    private static byte[] getScaledScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) WebDriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            ByteArrayInputStream in = new ByteArrayInputStream(screenshot);
            BufferedImage img = ImageIO.read(in);
            int height = img.getHeight() / EventListener.SCALE_FACTOR;
            int width = img.getWidth() / EventListener.SCALE_FACTOR;
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ImageIO.write(imageBuff, "jpg", buffer);
            screenshot = buffer.toByteArray();
            return screenshot;
        } catch (Exception e) {
            return new byte[0];
        }
    }
//    
////    @Override
////    public void beforeClick(WebElement element) {
////    	// TODO Auto-generated method stub
////    	
////    }
//
//    @Override
//    public void onFinish(ITestContext context) {
////        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
//
//    }
////    @Override
////    public void onTestFailure(ITestResult result) {
////    	// TODO Auto-generated method stub
////    	
////    }
    
    
}
