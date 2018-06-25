package pcf.sa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JMeterFactory {
	
	public void ExecuteSaApiJmx(String jmxFile,String fileName,int usersNum,int rampupNum,String apiServerUrl) throws Exception {
		String logfile = "log/log.csv";
		String xmlReportPath = "log/log.xml";
		String outputpath = "output/reports/" + fileName;
		String args;
		
		System.out.println("================ Start execute jmeter test script ================");
        
        try {
        	if(apiServerUrl.isEmpty())
        	{
        		args = "java -jar apache-jmeter/bin/ApacheJMeter.jar -n -t " + jmxFile + " -l " + logfile + " -e -o " + outputpath + 
        				" -Jusers=" + usersNum + " -Jrampup=" + rampupNum  + " -JxmlReportPath=" + xmlReportPath;
        	}
        	else {
        		if(System.getProperty("os.name").toLowerCase().contains("windows")){
        			args = "apache-jmeter/bin/jmeter.bat -n -t " + jmxFile + " -l " + logfile + " -e -o " + outputpath + 
        					" -Jusers=" + usersNum + " -Jrampup=" + rampupNum + " -JapiServerUrl=" + apiServerUrl + " -JxmlReportPath=" + xmlReportPath;
        		}
        		else {
        			args = "java -jar apache-jmeter/bin/ApacheJMeter.jar -n -t " + jmxFile + " -l " + logfile + " -e -o " + outputpath + " -Jusers=" + usersNum + " -Jrampup=" + rampupNum + 
        					" -JapiServerUrl=" + apiServerUrl + " -JxmlReportPath=" + xmlReportPath;
				}
			}
        	System.out.println(args);
        	
        	Process p = Runtime.getRuntime().exec(args);
        	
        	BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));  
            String line = null;  
            while ((line = input.readLine()) != null) {  
                System.out.println(line);  
            }  
            int exitVal = p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("================= End execute jmeter test script =================");
        
    }
}
