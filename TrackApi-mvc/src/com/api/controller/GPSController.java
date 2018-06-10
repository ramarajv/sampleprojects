package com.api.controller;

import com.api.pojo.DeviceInfo;
import java.io.BufferedReader;
import java.io.DataOutputStream;
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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author RAM
 *
 */

@Path("/track")
public class GPSController
{
  private static String token = null;
  private static String msg = null;
  private static Integer expires = null;
  private static Integer stats_code = null;
  
  @GET
  @Path("/login")
  @Produces({"application/json"})
  public Response login(@Context HttpHeaders headers, @Context HttpServletRequest request, @QueryParam("id") String userId, @QueryParam("authString") String password, @QueryParam("imei") String imei)
  {
    String jsonResponse = null;
    try
    {
      long unixTime = System.currentTimeMillis() / 1000L;
      String passMd = md5(password);
      String key = md5(passMd + unixTime);
      String q = "account=" + userId + "&time=" + unixTime + "&signature=" + key;
      
      String url = "http://api.gpsoo.net/1/auth/access_token?" + URLEncoder.encode(q, "UTF-8");
      jsonResponse = sendGet(url, "GET", "");
      if (jsonResponse != null)
      {
        JSONObject str = new JSONObject(jsonResponse.toString());
        if (str.has("access_token")) {
          token = (String)str.get("access_token");
        }
        if (str.has("expires_in")) {
          expires = (Integer)str.get("expires_in");
        }
        if (str.has("ret")) {
          stats_code = (Integer)str.get("ret");
        }
        if (stats_code.equals(Integer.valueOf(0))) {
          //jsonResponse = trackings(token, userId, imei);
        	
        }
        else
        {
        	//jsonResponse ="{data : user is not valid}";
        }
        System.out.println(" response..... " + jsonResponse);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Response.status(200).entity(jsonResponse.toString()).build();
  }
  
  
  @GET
  @Path("/moniter")
  public Response moniter(@Context HttpHeaders headers, @Context HttpServletRequest request, @QueryParam("token") String token, @QueryParam("id") String userId, @QueryParam("target") String target)
  {
    String jsonResponse = null;
    try
    {
      long unixTime = System.currentTimeMillis() / 1000L;
      String q = "access_token=" + token + "&map_type=GOOGLE&target=" + target + "&account=demotipl&time=" + unixTime;
      String url = "http://api.gpsoo.net/1/account/monitor?" + URLEncoder.encode(q, "UTF-8");
      jsonResponse = sendGet(url, "GET", "");
      if (jsonResponse != null)
      {
        JSONObject str = new JSONObject(jsonResponse.toString());
        /*if (str.has("ret")) {
          stats_code = (Integer)str.get("ret");
        }
        if (str.has("msg")) {
          msg = (String)str.get("msg");
        }
        if (str.has("data"))
        {
          JSONArray msg = (JSONArray)str.get("data");
          if ((msg != null) && (msg.length() > 0)) {
            for (int i = 0; i < msg.length(); i++)
            {
              JSONObject js = new JSONObject(msg.toString());
              DeviceInfo ds = new DeviceInfo();
              ds.setImei(js.has("imei") ? (String)js.get("imei") : null);
              ds.setCourse(js.has("course") ? (Integer)js.get("course") : null);
              ds.setDevice_info(js.has("device_info") ? (Integer)js.get("device_info") : null);
              ds.setGps_time(js.has("gps_time") ? (Integer)js.get("gps_time") : null);
              ds.setHeart_time(js.has("heart_time") ? (Integer)js.get("heart_time") : null);
              ds.setLat(js.has("lat") ? (Double)js.get("lat") : null);
              ds.setLng(js.has("lng") ? (Double)js.get("lng") : null);
              ds.setServer_time(js.has("server_time") ? (Integer)js.get("server_time") : null);
              ds.setSpeed(js.has("speed") ? (Integer)js.get("speed") : null);
              ds.setSys_time(js.has("sys_time") ? (Integer)js.get("sys_time") : null);
              data.add(ds);
            }
          }
        }*/
        System.out.println(" response..... " + str.toString());
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Response.status(200).entity(jsonResponse.toString()).build();
  }
  
  
  @GET
  @Path("/tracking")
  public Response tracking(@Context HttpHeaders headers, @Context HttpServletRequest request, @QueryParam("token") String token,@QueryParam("id") String userId, @QueryParam("imei") String imeis)
  {
    String jsonResponse = null;
    try
    {
      long unixTime = System.currentTimeMillis() / 1000L;
      String q = null;String reqData = null;
      
      String url = "http://api.gpsoo.net/1/devices/tracking?";
      if (imeis.contains(",")) {
    	  
        q = "access_token=" + token + "&map_type=GOOGLE&imeis=" + imeis + "&account=" + userId + "&time=" + unixTime;
        url = url + URLEncoder.encode(q, "UTF-8");
        String im = "{imeis="+imeis+"}";
        jsonResponse = sendPost(url, "POST", im);
        
      	} 
      else {
        q = "access_token=" + token + "&map_type=GOOGLE&imeis=" + imeis + "&account=" + userId + "&time=" + unixTime;
        url = url + URLEncoder.encode(q, "UTF-8");
        jsonResponse = sendGet(url, "GET", "");
      }
      if (jsonResponse != null)
      {
     
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Response.status(200).entity(jsonResponse).build();
  }
  
  @GET
  @Path("/trackings")
  public Response trackings()
  {
	  String token ="20007284894010145482136032ed6a894d9ccd719e907d43aa5aacac4000010010014010";
	  String userId ="htsfleet"; 
	  String imeis ="358899055871977";
    String jsonResponse = null;
    List<DeviceInfo> data = new ArrayList();
    try
    {
      long unixTime = System.currentTimeMillis() / 1000L;
      String q = null;String reqData = null;
      String url = "http://api.gpsoo.net/1/devices/tracking?";
      if (imeis.contains(",")) {
    	  
        q = "access_token=" + token + "&map_type=GOOGLE&imeis=" + imeis + "&account=" + userId + "&time=" + unixTime;
        url = url + URLEncoder.encode(q, "UTF-8");
        String im = "{imeis="+imeis+"}";
        jsonResponse = sendPost(url, "POST", im);
        
      } else {
        q = "access_token=" + token + "&map_type=GOOGLE&imeis=" + imeis + "&account=" + userId + "&time=" + unixTime;
        url = url + URLEncoder.encode(q, "UTF-8");
        jsonResponse = sendGet(url, "GET", "");
      }
      
      if (jsonResponse != null)
      {
        JSONObject str = new JSONObject(jsonResponse.toString());
        if (str.has("ret")) {
          stats_code = (Integer)str.get("ret");
        }
        if (str.has("msg")) {
          msg = (String)str.get("msg");
        }
        if (str.has("data"))
        {
        	
        }
        System.out.println(" response..... " + data.toString());
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Response.status(200).entity(jsonResponse).build();
  }
  
  @GET
  @Path("/histroy")
  public Response histroy(@Context HttpHeaders headers, @Context HttpServletRequest request, @QueryParam("token") String token,@QueryParam("id") String userId, @QueryParam("imei") String imeis, 
		  @QueryParam("btime") String beg_time, @QueryParam("etime") String end_time)
  {
    String jsonResponse = null;
    try
    {
      long unixTime = System.currentTimeMillis() / 1000L;
      String q = "access_token=" + token + "&map_type=GOOGLE&account="+userId+"&imei="+imeis+"&time=" + unixTime+ "&begin_time=" +beg_time+ "&end_time=" +end_time;
      
      String url = "http://api.gpsoo.net/1/devices/history?" + URLEncoder.encode(q, "UTF-8");
      jsonResponse = sendGet(url, "GET", "");
      if (jsonResponse != null)
      {
        JSONObject str = new JSONObject(jsonResponse.toString());
        if (str.has("access_token")) {
          token = (String)str.get("access_token");
        }
        if (str.has("expires_in")) {
          expires = (Integer)str.get("expires_in");
        }
        if (str.has("ret")) {
          stats_code = (Integer)str.get("ret");
        }
        System.out.println(" response..... " + jsonResponse);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Response.status(200).entity(jsonResponse.toString()).build();
  }
  
  @GET
  @Path("/address")
  public Response address(@Context HttpHeaders headers, @Context HttpServletRequest request, @QueryParam("token") String token,@QueryParam("id") String userId, 
		  @QueryParam("lati") String lati, @QueryParam("lang") String lang)
  {
    String jsonResponse = null;
    try
    {
      long unixTime = System.currentTimeMillis() / 1000L;
      String q = "access_token=" + token + "&lat=" +lati+ "&account=" +userId+ "&time=" + unixTime+ "&lng=" +lang;
      
      String url = "http://api.gpsoo.net/1/tool/address/?" + URLEncoder.encode(q, "UTF-8");
      jsonResponse = sendGet(url, "GET", "");
      if (jsonResponse != null)
      {
       
        System.out.println(" response..... " + jsonResponse);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Response.status(200).entity(jsonResponse.toString()).build();
  }
  
  @GET
  @Path("/devinfo")
  public Response devinfo(@Context HttpHeaders headers, @Context HttpServletRequest request, @QueryParam("token") String token,@QueryParam("id") String userId, 
		  @QueryParam("target") String target)
  {
    String jsonResponse = null;
    try
    {
      long unixTime = System.currentTimeMillis() / 1000L;
      
      String q = "access_token=" + token + "&target=" +target+ "&account=" +userId+ "&time=" + unixTime;
      String url = "http://api.gpsoo.net/1/account/devinfo?" + URLEncoder.encode(q, "UTF-8");
      jsonResponse = sendGet(url, "GET", "");
      if (jsonResponse != null)
      {
       
        System.out.println(" response..... " + jsonResponse);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Response.status(200).entity(jsonResponse.toString()).build();
  }
  
  
  @GET
  @Path("/blacklist")
  public Response devinfo(@Context HttpHeaders headers, @Context HttpServletRequest request, @QueryParam("token") String token,@QueryParam("id") String userId, 
		  @QueryParam("target") String target,@QueryParam("cardno") String cardno ,@QueryParam("drive_cardno") String drive_cardno )
  {
    String jsonResponse = null;
    try
    {
      long unixTime = System.currentTimeMillis() / 1000L;
      
      String q = "access_token=" + token + "&target=" +target+ "&account=" +userId+ "&time=" + unixTime +"&cardno=" +cardno;
      String url = "http://api.gpsoo.net/1/tool/blacklist?" + URLEncoder.encode(q, "UTF-8");
      jsonResponse = sendGet(url, "GET", "");
      if (jsonResponse != null)
      {
       
        System.out.println(" response..... " + jsonResponse);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Response.status(200).entity(jsonResponse.toString()).build();
  }
  
  
  private String sendGet(String url, String requestMethod, String data)
    throws Exception
  {
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection)obj.openConnection();
    

    con.setRequestMethod("GET");
    con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
    con.setRequestProperty("Accept", "application/json");
    
    int responseCode = con.getResponseCode();
    System.out.println("Response Code : " + responseCode);
    
    BufferedReader in = new BufferedReader(
      new InputStreamReader(con.getInputStream()));
    
    StringBuffer response = new StringBuffer();
    String inputLine;
    while ((inputLine = in.readLine()) != null)
    {
      response.append(inputLine);
    }
    in.close();
    

    System.out.println(response.toString());
    return response.toString();
  }
  
  private String sendPost(String url, String requestMethod, String data)
    throws Exception
  {
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection)obj.openConnection();

    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
    con.setRequestProperty("Accept", "application/json");

    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(data);
    wr.flush();
    wr.close();
    
    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'POST' request to URL : " + url);
    System.out.println("Post parameters : " + data);
    System.out.println("Response Code : " + responseCode);
    
    BufferedReader in = new BufferedReader(
      new InputStreamReader(con.getInputStream()));
    
    StringBuffer response = new StringBuffer();
    String inputLine;
    while ((inputLine = in.readLine()) != null)
    {
      response.append(inputLine);
    }
    in.close();
    

    System.out.println(response.toString());
    return response.toString();
  }
  
  
  
  public String md5(String input)
  {
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    byte[] messageDigest = md.digest(input.getBytes());
    BigInteger number = new BigInteger(1, messageDigest);
    String hashtext = number.toString(16);
    while (hashtext.length() < 32) {
      hashtext = "0" + hashtext;
    }
    return hashtext;
  }
}
