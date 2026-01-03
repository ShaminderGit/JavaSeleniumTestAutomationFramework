package org.example.extentreports;

import com.aventstack.extentreports.AbstractProcessor;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

    public static ExtentReports getReportObject(){
        // Step 1 : Set Path
        String path = System.getProperty("user.dir")+"//reports//index.html";
        // Step 2 : Extent Reporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Java Selenium Web Automation Framework");
        reporter.config().setDocumentTitle("Test Results");
        // Step 3 : Extent Reports
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Shaminder Singh");
        return extent;




    }
}
