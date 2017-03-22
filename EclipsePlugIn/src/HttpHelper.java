import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.json.simple.JSONObject;

public class HttpHelper {
	public static String IP_SERVER = "188.62.72.188";
	public HttpHelper(){

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
			System.out.println("Befrore read");
			InputStream in = new BufferedInputStream(con.getInputStream());
            String result = in.toString();
            System.out.println("result : "+ result);

            in.close();
            con.disconnect();
		} catch (MalformedURLException e) {
			System.err.println("Malformed URL");

		} catch (SocketTimeoutException e){
			System.err.println("Connexion TimeOut");
		}catch (IOException e) {
			System.err.println("IO exception");
		}
		System.out.println("JSON string : "+getJSON(intensity));


	}
	private String getJSON(int intensity){
		// TODO add correct TimeStamp
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
				"\"timestamp\": \"2017-03-22T16:24:05.901Z\","+
				"\"trigger\": {"+
				"\"href\": \"string\","+
				"\"properties\": {},"+
				"\"type\": \"string\""+
				"}"+
				"}";
	}

}
