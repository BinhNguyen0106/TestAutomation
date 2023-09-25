package Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetCodeActiveByAPI {

	String client_id = "120769661541-tl8v3j16530lmec3bf1elb0r9bus3kc4.apps.googleusercontent.com";
	String client_serect = "11WRplNPhoryPmOxJohtfacT";
	String refresh_token = "1/BBOg-1KoubM-yh4i8qLeNbuanPLHee43adzIm9McPtg";

	public String GetLinkActive(String TitleEmail) {
		String url_getToken = "https://accounts.google.com/o/oauth2/token";
		String access_token = GetValueInJson(GetAccessTokenJson(url_getToken), "access_token");
		String url_getListMail = "https://www.googleapis.com/gmail/v1/users/me/messages?access_token=" + access_token;
		String LinkActive = "";
		int timeout = 0;
		while (!(LinkActive.length() > 0)) {
			try {
				if (timeout >= 900) {
					throw new Exception("Test case stoped due to wait over 15 mins");
				}
				Thread.sleep(2000);
				timeout += 2;
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			List<String> ListIDEmail = GetListinJson(GetJsonData(url_getListMail), "messages", "id");
			for (int i = 0; i < 10; i++) {
				String url_getMailDetail = "https://www.googleapis.com/gmail/v1/users/me/messages/" + ListIDEmail.get(i)
						+ "?access_token=" + access_token;
				String EmailDetail = GetJsonData(url_getMailDetail);
				if (EmailDetail.contains(TitleEmail)) {
					LinkActive = GetValueInJson(EmailDetail, "snippet");
					break;
				}
			}
		}
		String result;
		result = LinkActive.replace("token to reset", "");
		result = result.replace("The token", "");
		result = result.substring(result.indexOf("http:"), result.indexOf(" to"));
		return result;
	}

	private String GetValueInJson(String json, String value) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			String result = (String) jsonObject.get(value);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<String> GetListinJson(String json, String listname, String value) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		List<String> ListID = new ArrayList<String>();
		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			JSONArray arr = (JSONArray) jsonObject.get(listname);
			for (int i = 0; i < arr.size(); i++) {
				jsonObject = (JSONObject) jsonParser.parse(arr.get(i).toString());
				ListID.add((String) jsonObject.get(value));
			}
			return ListID;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	private String GetAccessTokenJson(String url_getToken) {
		String postData = "client_id=" + client_id + "&client_secret=" + client_serect + "&refresh_token="
				+ refresh_token + "&grant_type=refresh_token";
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url_getToken);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(postData);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private String GetJsonData(String url_getListMail) {
		try {
			URL url = new URL(url_getListMail);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = in.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			String result = builder.toString();
			return result;
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
		return null;
	}
}
