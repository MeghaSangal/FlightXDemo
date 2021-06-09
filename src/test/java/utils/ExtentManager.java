package utils;

import TestSuiteBase.SuiteBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentManager extends SuiteBase {

    public ExtentManager() {
        super();
    }

    public static String CreateHTMLDir() {
        String destDir = (System.getProperty("user.dir") + File.separator+ "TestReport");
        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy-hh-mm a");
        String path = destDir + File.separator + dateFormat1.format(new Date());
        new File(path).mkdirs();
        System.setProperty("ReportDirectoryPath", path);
        return path;
    }

    public static ExtentReports Instance(String Path, String SuiteName) {

        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-s a");

        String Path1 = Path + "/" + SuiteName + "_" + dateFormat1.format(new Date()) + ".html";
        //Set file name with combination of test class name + date time.
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Path1);
        ExtentXReporter extentxReporter = new ExtentXReporter("host");

        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Build Version", Param.getProperty("BuildVersion"));
        extent.setSystemInfo("Target Browser", Param.getProperty("testBrowser"));
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setReportName("Blaze Test Automation");
        htmlReporter.config().setDocumentTitle("Blaze Test Automation");

        htmlReporter.config().setCSS("body:not(.default) {overflow: scroll !important;}");

        return extent;
    }
}
