package utilities;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import freemarker.template.SimpleDate;
import testcases.Base;


public class Reports implements ITestListener{

	
	public ExtentHtmlReporter htmlreport;
	public ExtentReports report; //specify the location of report
	public ExtentTest logger; //specify the data to be populated in the report
	Base b=new Base();
	
	@Override
	public void onStart(ITestContext context) {
		String timestamp=new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date());
		String reportName="Test-Report-"+timestamp+".html";
		htmlreport=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\"+reportName);
		report=new ExtentReports();
		htmlreport.loadXMLConfig(System.getProperty("user.dir")+"\\extent-report.xml");
		htmlreport.config().setReportName("Functional Report");
		htmlreport.config().setDocumentTitle("Demo_Project Report");
		htmlreport.config().setTheme(Theme.STANDARD);
		report.attachReporter(htmlreport);
		report.setSystemInfo("HostName", "Localhost");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("OS", "Windows10");
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger=report.createTest(result.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger=report.createTest(result.getName());
		logger.log(Status.FAIL,  MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
		try {
			b.takeScreenshot(result.getName());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = result.getName()+time;
		String pathName=System.getProperty("user.dir") + "\\Screenshots\\" +name+".png";
		System.out.println(pathName);
			//String imagePath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName();
			try {
				logger.log(Status.FAIL, "Sanpshot as below: "+logger.addScreenCaptureFromPath(pathName));
				logger.addScreenCaptureFromPath(pathName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger=report.createTest(result.getName());
		logger.log(Status.SKIP,  MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
		
	}
	
	
	

}
