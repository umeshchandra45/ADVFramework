package extentreports;

import com.relevantcodes.extentreports.ExtentReports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExtentManager {
	static ExtentReports extent, extent1;
	final static String fileName = "ExtentOutput.html";
	static String folderPath = "";

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports(folderPath() + fileName, true);
			// extent.loadConfig(new
			// File(System.getProperty("user.dir")+"\\src\\resources\\extent-config.xml"));
		}
		return extent;
	}

	public static String folderPath() {
		if (folderPath.trim().isEmpty()) {
			String reportDate = returnDate();

			folderPath = "test-output/ExtentReport/" + "Run_" + reportDate + "/";
			System.out.println("folderPath" + folderPath);
		}
		return folderPath;
	}

	public static String returnDate() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;

	}

}
