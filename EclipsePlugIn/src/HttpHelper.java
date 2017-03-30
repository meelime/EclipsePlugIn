import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.json.simple.JSONObject;

public class HttpHelper {
	public static String IP_SERVER = "10.128.24.208";
	private boolean isDebugMode = false;
	public HttpHelper(boolean isDebugMode){
		this.isDebugMode = isDebugMode;
	}
	public void sendEmotion(int intensity){
		String url="http://"+IP_SERVER+":8080/api/measures";
		URL object;
		try {
			object = new URL(url);
			HttpURLConnection con;
			con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setConnectTimeout(10000);

			con.setRequestProperty("Content-Type", "application/json");
			con.addRequestProperty("Accept", "application/json");
			con.addRequestProperty("charset", "UTF-8");
			con.setRequestMethod("POST");
			String json = getJSON(intensity);
			OutputStream os = con.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();
			if(isDebugMode)System.out.println("[HTTPHELPER] : Before read");
			InputStream in = new BufferedInputStream(con.getInputStream());
			String result = in.toString();
			if(isDebugMode)System.out.println("[HTTPHELPER] : Result : "+ result);

			in.close();
			con.disconnect();
		} catch (MalformedURLException e) {
			System.err.println("[HTTPHELPER] : Malformed URL");

		} catch (SocketTimeoutException e){
			System.err.println("[HTTPHELPER] : Connexion TimeOut");
		}catch (IOException e) {
			System.err.println("[HTTPHELPER] : IO exception");
		}
		if(isDebugMode)System.out.println("[HTTPHELPER] : JSON string sent : "+getJSON(intensity));


	}
	private String getJSON(int intensity){
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date now = new Date();
		String date = format.format(now);
		


		return "{"+
		"\"emotion\": {"+
		"\"category\": \"string\","+
		"\"intensity\": "+intensity+""+
		"},"+
		"\"sensor\": {"+
		"\"href\": \"string\","+
		"\"properties\": {},"+
		"\"type\": \"string\""+
		"},"+
		"\"subject\": {"+
		"\"href\": \"string\","+
		"\"properties\": {},"+
		"\"type\": \"string\""+
		"},"+
		"\"timestamp\": \""+date+"\","+
		"\"trigger\": {"+
		"\"href\": \"string\","+
		"\"properties\": {},"+
		"\"type\": \"string\""+
		"}"+
		"}";
	}

}
