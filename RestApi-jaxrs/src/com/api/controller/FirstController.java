/**
 * 
 */
package com.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.api.pojo.DeviceInfo;

/**
 * @author css105543
 *
 */
@Path("/testing")
public class FirstController {
	
	private static String token = null;
	private static String msg = null;
	private static Integer expires = null;
	private static Integer stats_code = null;
	

	@GET
	@Path("/login")
	public Response login() {
 
		String jsonResponse = null;
		try {
			
			long unixTime = System.currentTimeMillis() / 1000L;
			String passMd = md5("12345");
			String key = md5(passMd+unixTime);
			String q = "account=demotipl&time="+unixTime+"&signature="+key;
			
			String url = "http://api.gpsoo.net/1/auth/access_token?"+URLEncoder.encode(q, "UTF-8");
			jsonResponse = callWebservice(url);
			if(jsonResponse != null)
			{
			JSONObject str = new JSONObject(jsonResponse.toString());
			
			if (str.has("access_token"))
				token = (String) str.get("access_token");
			
			if (str.has("expires_in"))
				expires = (Integer) str.get("expires_in");
			
			if (str.has("ret"))
				stats_code = (Integer) str.get("ret");
			
			System.out.println(" response..... "+jsonResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return Response.status(200).entity(jsonResponse.toString()).build();
	}
	
	@GET
	@Path("/moniter")
	public Response moniter() {
		List<DeviceInfo> data  = new ArrayList<DeviceInfo>();
		String jsonResponse = null;
		try {
			
			long unixTime = System.currentTimeMillis() / 1000L;
			String q = "access_token="+token+"&map_type=GOOGLE&target=JM01&account=demotipl&time="+unixTime;
			String url = "http://api.gpsoo.net/1/account/monitor?"+URLEncoder.encode(q, "UTF-8");
			jsonResponse = callWebservice(url);
			if(jsonResponse != null)
			{
				
				JSONObject str = new JSONObject(jsonResponse.toString());
				
				if (str.has("ret"))
					stats_code = (Integer) str.get("ret");
				
				if (str.has("msg"))
					msg = (String) str.get("msg");
				if(str.has("data"))
				{
					JSONArray msg = (JSONArray) str.get("data");
					if(msg !=null && msg.length() >0 )
					{
						for(int i=0;i<msg.length();i++)
						{
							JSONObject js = new JSONObject(msg.toString());
							DeviceInfo ds = new DeviceInfo();
							ds.setImei(js.has("imei") ? (String) js.get("imei") : null);
							ds.setCourse(js.has("course") ? (String) js.get("course") : null);
							ds.setDevice_info(js.has("device_info") ? (String) js.get("device_info") : null);
							ds.setGps_time(js.has("gps_time") ? (String) js.get("gps_time") : null);
							ds.setHeart_time(js.has("heart_time") ? (String) js.get("heart_time") : null);
							ds.setLat(js.has("lat") ? (String) js.get("lat") : null);
							ds.setLng(js.has("lng") ? (String) js.get("lng") : null);
							ds.setServer_time(js.has("server_time") ? (String) js.get("server_time") : null);
							ds.setSpeed(js.has("speed") ? (String) js.get("speed") : null);
							ds.setSys_time(js.has("sys_time") ? (String) js.get("sys_time") : null);
							data.add(ds);
						}
					}
				}
				
				System.out.println(" response..... "+data.toString());
				}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return Response.status(200).entity(jsonResponse.toString()).build();
	}
	
	@GET
	@Path("/tracking")
	public Response tracking() {
 
		String jsonResponse = null;
		List<DeviceInfo> data  = new ArrayList<DeviceInfo>();
		try {
			
			long unixTime = System.currentTimeMillis() / 1000L;
			String q = "access_token="+token+"&map_type=GOOGLE&imeis=862304021636363&account=demotipl&time="+unixTime;
			String url = "http://api.gpsoo.net/1/devices/tracking?"+URLEncoder.encode(q, "UTF-8");
			jsonResponse = callWebservice(url);
			if(jsonResponse != null)
			{
			
			JSONObject str = new JSONObject(jsonResponse.toString());
			
			if (str.has("ret"))
				stats_code = (Integer) str.get("ret");
			
			if (str.has("msg"))
				msg = (String) str.get("msg");
			if(str.has("data"))
			{
				JSONArray msg = (JSONArray) str.get("data");
				if(msg !=null && msg.length() >0 )
				{
					for(int i=0;i<msg.length();i++)
					{
						DeviceInfo ds = new DeviceInfo();
						ds.setImei(msg.getJSONObject(i).has("imei") ? (String) msg.getJSONObject(i).get("imei") : null);
						ds.setCourse(msg.getJSONObject(i).has("course") ? (String) msg.getJSONObject(i).get("course") : null);
						ds.setDevice_info(msg.getJSONObject(i).has("device_info") ? (String) msg.getJSONObject(i).get("device_info") : null);
						ds.setGps_time(msg.getJSONObject(i).has("gps_time") ? (String) msg.getJSONObject(i).get("gps_time") : null);
						ds.setHeart_time(msg.getJSONObject(i).has("heart_time") ? (String) msg.getJSONObject(i).get("heart_time") : null);
						ds.setLat(msg.getJSONObject(i).has("lat") ? (String) msg.getJSONObject(i).get("lat") : null);
						ds.setLng(msg.getJSONObject(i).has("lng") ? (String) msg.getJSONObject(i).get("lng") : null);
						ds.setServer_time(msg.getJSONObject(i).has("server_time") ? (String) msg.getJSONObject(i).get("server_time") : null);
						ds.setSpeed(msg.getJSONObject(i).has("speed") ? (String) msg.getJSONObject(i).get("speed") : null);
						ds.setSys_time(msg.getJSONObject(i).has("sys_time") ? (String) msg.getJSONObject(i).get("sys_time") : null);
						data.add(ds);
					}
				}
			}
			
			System.out.println(" response..... "+data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return Response.status(200).entity(data.toString()).build();
	}
	
	@GET
	@Path("/histroy")
	public Response histroy() {
 
		String jsonResponse = null;
		try {
			
			long unixTime = System.currentTimeMillis() / 1000L;
			String q = "access_token="+token+"&map_type=GOOGLE&imeis=mycar&account=demotipl&time="+unixTime;
			
			//access_token=&map_type=BAIDU &account=testacc&imei=353419031939627&time=1366786321&begin_time=1452754256&end_time=14527547562
			
			String url = "http://api.gpsoo.net/1/devices/history?"+URLEncoder.encode(q, "UTF-8");
			jsonResponse = callWebservice(url);
			if(jsonResponse != null)
			{
			JSONObject str = new JSONObject(jsonResponse.toString());
			
			if (str.has("access_token"))
				token = (String) str.get("access_token");
			
			if (str.has("expires_in"))
				expires = (Integer) str.get("expires_in");
			
			if (str.has("ret"))
				stats_code = (Integer) str.get("ret");
			
			System.out.println(" response..... "+jsonResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return Response.status(200).entity(jsonResponse.toString()).build();
	}
	
	
	public String callWebservice(String url)
	{
		 
		StringBuilder jsonResponse = new StringBuilder();
		URLConnection connection = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedInputStreamReader = null;
		String requestMethod = "GET";

		try {
			
			
			URL service = new URL(url);
			connection = service.openConnection();
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			connection.setRequestProperty("Content-Type", "text/html; charset=utf-8");
			connection.setRequestProperty("Accept", "application/json");
			
			
			if (connection instanceof HttpURLConnection) {
		        ((HttpURLConnection)connection).setRequestMethod(requestMethod);
			} else if  (connection instanceof HttpsURLConnection) {
				((HttpsURLConnection)connection).setRequestMethod(requestMethod);
			}
			
			try {
				
				inputStream = connection.getInputStream();

			} catch (IOException ioException) {
				
				ioException.printStackTrace();
				
				inputStream = ((HttpURLConnection)connection).getErrorStream();
			}
			
			inputStreamReader = new InputStreamReader(inputStream);
			
			bufferedInputStreamReader = new BufferedReader(inputStreamReader);

			String inputLine = null;

			while ((inputLine = bufferedInputStreamReader.readLine()) != null) {
				jsonResponse.append(inputLine);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedInputStreamReader != null) {
				try {
					bufferedInputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		return jsonResponse.toString();
		
	}
	
	//Encrypted signature; algorithm：md5(md5(password of dealer account) + time) 
	
	public String md5(String input)
	{
		
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        
       
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
        	hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
