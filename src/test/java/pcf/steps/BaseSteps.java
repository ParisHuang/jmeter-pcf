package pcf.steps;

import java.io.File;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

//import org.gradle.api.internal.tasks.compile.reflect.SourcepathIgnoringInvocationHandler;
import org.jtest.tool.TestRailClient;
import org.jtest.util.CsvHelper;
import org.jtest.util.FileHelper;
import org.jtest.util.JsHelper;
import org.jtest.util.XmlHelper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class BaseSteps {
	
	public static Integer users;
	public static Integer rampupPeriod;
	public static Integer durationTime;
	
	public static String scenarioName;
	private String featureTitle;
	private String references = "";
	public String scenarioTitle;
	public static String runName;
	public static String runTag;
	public static String versionText = "";
	public static TestRailClient testRail;
	
	private static Date startTime;
	private static Date endTime;
	
	@Before
	public void before(Scenario scenario) throws Throwable{
		
		String argsCsv = System.getProperty("user.dir") + "/input/args.csv";
		CsvHelper csv = new CsvHelper(argsCsv);
		users = Integer.parseInt(csv.readRecord(1, "users"));
		rampupPeriod = Integer.parseInt(csv.readRecord(1, "rampupPeriod"));
		rampupPeriod *= 60;
		durationTime = Integer.parseInt(csv.readRecord(1, "durationTime"));
		durationTime *= 60;
		
		System.out.println("\r\n");
		System.out.println("=============================================================================================");
		System.out.println("Test Scenario: " + scenario.getName());
		System.out.println("Test Users: " + users);
		if (durationTime!=0) {
			System.out.println("Duration Time: " + durationTime/60 + "m");
		}
		System.out.println("=============================================================================================");
		
		System.out.println("Enter into the method of '@Before' ----------------------------------------------------------");
		startTime = new Date();
		testRail = new TestRailClient();
		Collection<String> tagNames = scenario.getSourceTagNames();
		for (String tagName : tagNames) {
			if(tagName.startsWith("@JmeterPcfTest")){
				runTag = tagName;
			}
			if(tagName.startsWith("@Story:")){
				references = tagName.substring(tagName.indexOf(":")+1);
			}
		}
	    scenarioName = scenario.getName().replace(' ', '_');
	    featureTitle = scenario.getId().split(";")[0].replace('-', ' ');
	    scenarioTitle = scenario.getName();
	    
	    File csvLogFile = new File("log/log.csv");
		FileHelper.delFile(csvLogFile);
		File xmlLogFile = new File("log/log.xml");
		FileHelper.delFile(xmlLogFile);
		System.out.println("Exit the method of '@Before' ----------------------------------------------------------------");
	}
	
	@After
	public void After(Scenario scenario) throws Throwable{
		
		System.out.println("Enter into the method of '@After' -----------------------------------------------------------"); 
		endTime = new Date();
		long diff = endTime.getTime() - startTime.getTime();
		long day = diff / (1000 * 60 * 60 * 24);
		long hour=(diff/(60*60*1000)-day*24);   
		long min=((diff/(60*1000))-day*24*60-hour*60);   
		long s=(diff/1000-day*24*60*60-hour*60*60-min*60);
		String durationRunningTime = hour + "h " + min + "m " + s + "s";
		
		String csvLog = "log\\log.csv";
		String xmlLogFile = "log\\log.xml";
		String reportPath = "output\\reports\\" + scenarioName;
		FileHelper.copyFile(csvLog, reportPath);
		FileHelper.copyFile(xmlLogFile, reportPath);
		
		File csvLogFile = new File("log/log.csv");
		//csvLogFile.
		JsHelper js = new JsHelper("output/reports/" + scenarioName + "/content/js/dashboard.js");
		js.replaceString();
		String comments = "Test Environment: " + runName + "\r\n";
		//comments += versionText;
		//comments += "\r\n\r\n";
		comments += "Test Users: " + users;
		comments += "\r\n";
		if (durationTime!=0) {
			comments += "Duration Time: " + durationTime/60 + "m";
			comments += "\r\n";
		}
		if (rampupPeriod!=0) {
			comments += "Ramp-up Period: " + rampupPeriod/60 + "m";
			comments += "\r\n";
		}
		comments += "\r\n";
		comments += js.readJsonStr();
		comments += "\r\n";
		
		System.out.println("Start read xml log...");
		XmlHelper xmlHelper = new XmlHelper("log/log.xml");
		comments += xmlHelper.parseJmeterXmlLog();
		System.out.println("End read xml log...");
		
		System.out.println("start add test result on TestRail..."); 
		testRail.createCaseRunResult(runName,featureTitle, scenarioTitle, references, scenario, comments, versionText, durationRunningTime);
		System.out.println("Finished to add test result on TestRail..."); 
		
		System.out.println("Exit the method of '@After' -----------------------------------------------------------------"); 
	}
	
	@Given("^(prepare (\\d+) databases: (\\d+) customers)$")
	public void prepareDatabasees(String stepTitle,int num1,int num2){
		testRail.appendCustomSteps("Background:");
		testRail.appendCustomSteps("Given", stepTitle);
	}
	
	@And("^(prepare (\\d+) schools for each customer)$")
	public void prepareSchools(String stepTitle,int num){
		testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(prepare (\\d+) students for each school)$")
	public void prepareStudents(String stepTitle,int num){
		testRail.appendCustomSteps("And", stepTitle);
		testRail.appendCustomSteps("");
	}
}
