package pcf.run;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//import net.sf.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
//import org.json.simple.JSONObject;
import org.jtest.util.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:ohio/stage/features/",
        //tags = {"@debug"},
        //tags = {"@OregonDevzhConcurrentTest50"},
        tags = {"@buildtest"},
        //plugin = {"pretty","junit:output/junitLog.xml"},
        glue = {"pcf.steps"},
        strict = true
)
public class RunCukesTest {
	@BeforeClass
	public static void setUp() throws Exception,StringIndexOutOfBoundsException{
		
		File reportFolder = new File("output/reports");
		if (reportFolder.exists()) {
			FileHelper.deleteDir(reportFolder);
		}
		FileHelper.createDir(reportFolder);
		
		File cucumberLogFile = new File("log/cucumberLog.xml");
		FileHelper.delFile(cucumberLogFile);
		
		File csvLogFile = new File("log/log.csv");
		FileHelper.delFile(csvLogFile);
		File xmlLogFile = new File("log/log.xml");
		FileHelper.delFile(xmlLogFile);
	}
}
