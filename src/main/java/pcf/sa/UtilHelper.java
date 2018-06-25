package pcf.sa;

import org.json.JSONObject;
import org.jtest.util.ApiHelper;

public class UtilHelper {
	public static String getVersion(String url) {
		String versionText = "";
		ApiHelper apiHelper = new ApiHelper(url+"/healthy/check");
		String reponseData = apiHelper.get();
		JSONObject jsonStr = new JSONObject(reponseData);
		versionText = jsonStr.get("versionText").toString();
		
		return versionText;
	}
}
