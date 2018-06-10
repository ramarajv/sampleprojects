package com.api.pojo;

public class DeviceInfo {
	
private String imei;
private String device_info;
private	String gps_time;
private	String sys_time;
private	String heart_time;
private	String server_time;
private	String lng;
private	String lat;
private	String course;
private	String speed;

public DeviceInfo() {
	
}


public String getImei() {
	return imei;
}


public void setImei(String imei) {
	this.imei = imei;
}


public String getDevice_info() {
	return device_info;
}


public void setDevice_info(String device_info) {
	this.device_info = device_info;
}


public String getGps_time() {
	return gps_time;
}


public void setGps_time(String gps_time) {
	this.gps_time = gps_time;
}


public String getSys_time() {
	return sys_time;
}


public void setSys_time(String sys_time) {
	this.sys_time = sys_time;
}


public String getHeart_time() {
	return heart_time;
}


public void setHeart_time(String heart_time) {
	this.heart_time = heart_time;
}


public String getServer_time() {
	return server_time;
}


public void setServer_time(String server_time) {
	this.server_time = server_time;
}


public String getLng() {
	return lng;
}


public void setLng(String lng) {
	this.lng = lng;
}


public String getLat() {
	return lat;
}


public void setLat(String lat) {
	this.lat = lat;
}


public String getCourse() {
	return course;
}


public void setCourse(String course) {
	this.course = course;
}


public String getSpeed() {
	return speed;
}


public void setSpeed(String speed) {
	this.speed = speed;
}


@Override
public String toString() {
	return "DeviceInfo [imei=" + imei + ", device_info=" + device_info
			+ ", gps_time=" + gps_time + ", sys_time=" + sys_time
			+ ", heart_time=" + heart_time + ", server_time=" + server_time
			+ ", lng=" + lng + ", lat=" + lat + ", course=" + course
			+ ", speed=" + speed + "]";
}

}
