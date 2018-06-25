package pcf.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then; 
import cucumber.api.java.en.When;
import pcf.sa.JMeterFactory;
import pcf.sa.UtilHelper;
import cucumber.api.Scenario;

import java.io.IOException;

import org.jtest.util.ConfigHelper;
import org.jtest.util.CsvHelper;
import org.junit.Assert;
import com.csvreader.*;


public class CustomStepsForSaApi {
	private int usersNum;
	private int rampup;
	private int durationNum;
	
	@Given("^((.*) threads is loaded)$")
	public void givenAllThreadsIsLoaded(String stepTitle,String num) throws Throwable{
		usersNum = BaseSteps.users;
		stepTitle = stepTitle.replace(num, Integer.toString(usersNum));
		BaseSteps.testRail.appendCustomSteps("Scenario:");
		BaseSteps.testRail.appendCustomSteps("Given", stepTitle);
	}
	
	@And("^(ramp-up period is set to (.*) seconds)$")
	public void rampupPeriodIsSeted(String stepTitle,String rampupPeriod) throws Throwable{
		rampup = BaseSteps.rampupPeriod;
		stepTitle = stepTitle.replace(rampupPeriod, Integer.toString(rampup));
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(only run one time for each threads)$")
	public void onlyRunOneTime(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(duration run time is set to (.*) second)$")
	public void durationTimeIsSeted(String stepTitle,String durationTime) throws Throwable{
		durationNum = BaseSteps.durationTime;
		stepTitle = stepTitle.replace(durationTime, Integer.toString(durationNum));
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^((.*) guardian login system)$")
	public void callGetUserOptions(String stepTitle,String num) throws Throwable {
		usersNum = BaseSteps.users;
		stepTitle = stepTitle.replace(num, Integer.toString(usersNum));
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get access token)$")
	public void callGetAccessToken(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call api to get student info by id)$")
	public void callApiToGetStudentInfoById(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get customers)$")
	public void callGetCustomers(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get customer by customerId)$")
	public void callGetCustomerByCustomerId(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get organizations)$")
	public void callGetOrganizations(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get students)$")
	public void callGetStudents(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get student by pkey)$")
	public void callGetstudentByPkey(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get student by studentId)$")
	public void callGetStudentByStudentId(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get student id by pkey)$")
	public void callGetStudentIdByPkey(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call communicate api to get student id by id)$")
	public void callGetStudentIdById(String stepTitle){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(call safearrival api:\"(.*)\")$")
	public void callCommunicateApi(String stepTitle,String callApiName){
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@And("^(if create absence successfully)$")
	public void ifCreateSuccessfully(String stepTitle) throws Throwable{
		BaseSteps.testRail.appendCustomSteps("And", stepTitle);
	}
	
	@When("^(call jmeter with safearrival api jmx script:\"(.*)\")$")
	public void callJmeterWithJmxFile(String stepTitle,String jmxFile){
		BaseSteps.testRail.appendCustomSteps("When", stepTitle);
		
		JMeterFactory jMeterFactory = new JMeterFactory();
		try {
			switch (BaseSteps.runTag) {
				case "@JmeterPcfTest":
					BaseSteps.runName = "pcf test";
					String apiServerUrl = "safearrival-api.app.pcfdev.one.west.com";
					//BaseSteps.versionText = UtilHelper.getVersion("http://" + apiServerUrl);
					BaseSteps.versionText = "SafeArrival Web API - (2.0.0-20180530.1)";
					jMeterFactory.ExecuteSaApiJmx(jmxFile,BaseSteps.scenarioName,usersNum,rampup,apiServerUrl);
					break;
				default:
					break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("^(verify the response time for all call api)$")
	public void verifyResponseTime(String stepTitle) throws Throwable{
		BaseSteps.testRail.appendCustomSteps("Then", stepTitle);
		CsvReader csvReader = new CsvReader("log/log.csv");
		try {
            while (csvReader.readRecord()){
                if(csvReader.get(7).equals("false")){
                	Assert.fail("There are any more error on jmeter report.");
                	break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		finally {
			csvReader.close();
		}
	}
}
