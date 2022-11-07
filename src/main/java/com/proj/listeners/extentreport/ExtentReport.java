package com.proj.listeners.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {
    private ExtentReport() {
    }
    private static ExtentReports report;
    private static ExtentTest test;
    public static String timeStamp = new SimpleDateFormat(("yyyyddmm hh_mm_ss")).format(new Date());

    public static void initReport() {
        File file = new File("./extentreports/HTML" + " " + timeStamp);
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(file);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle("Customer App Project");
        extentSparkReporter.config().setReportName("Regression Testing");
        report = new ExtentReports();
        report.attachReporter(extentSparkReporter);
        report.setSystemInfo("PlatForm", "Android");
        report.setSystemInfo("Build Name", "-----");
        report.setSystemInfo("Reporter Name", "Suraj N");
    }

    public static void tearDownReport() {
        report.flush();
    }

    public static void createTest(String testcasename) {
        test = report.createTest(testcasename);
        ExtentManager.setExtent(test);
    }

    public static void addAuthor(String[] authors ) {
        for (String author : authors) {
            ExtentManager.getTest().assignAuthor(author);
        }
    }

    public static void addCategory(String[] categories){
        for(String category:categories){
            ExtentManager.getTest().assignCategory(category);
        }

    }
}
