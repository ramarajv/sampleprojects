package com.api.pojo;

public class DeviceInfo
{
  private String imei;
  private Integer device_info;
  private Integer gps_time;
  private Integer sys_time;
  private Integer heart_time;
  private Integer server_time;
  private Double lng;
  private Double lat;
  private Integer course;
  private Integer speed;
  
  public String getImei()
  {
    return this.imei;
  }
  
  public void setImei(String imei)
  {
    this.imei = imei;
  }
  
  public Integer getDevice_info()
  {
    return this.device_info;
  }
  
  public void setDevice_info(Integer device_info)
  {
    this.device_info = device_info;
  }
  
  public Integer getGps_time()
  {
    return this.gps_time;
  }
  
  public void setGps_time(Integer gps_time)
  {
    this.gps_time = gps_time;
  }
  
  public Integer getSys_time()
  {
    return this.sys_time;
  }
  
  public void setSys_time(Integer sys_time)
  {
    this.sys_time = sys_time;
  }
  
  public Integer getHeart_time()
  {
    return this.heart_time;
  }
  
  public void setHeart_time(Integer heart_time)
  {
    this.heart_time = heart_time;
  }
  
  public Integer getServer_time()
  {
    return this.server_time;
  }
  
  public void setServer_time(Integer server_time)
  {
    this.server_time = server_time;
  }
  
  public Double getLng()
  {
    return this.lng;
  }
  
  public void setLng(Double lng)
  {
    this.lng = lng;
  }
  
  public Double getLat()
  {
    return this.lat;
  }
  
  public void setLat(Double lat)
  {
    this.lat = lat;
  }
  
  public Integer getCourse()
  {
    return this.course;
  }
  
  public void setCourse(Integer course)
  {
    this.course = course;
  }
  
  public Integer getSpeed()
  {
    return this.speed;
  }
  
  public void setSpeed(Integer speed)
  {
    this.speed = speed;
  }
  
  public String toString()
  {
    return 
    


      "{imei=" + this.imei + ", device_info=" + this.device_info + ", gps_time=" + this.gps_time + ", sys_time=" + this.sys_time + ", heart_time=" + this.heart_time + ", server_time=" + this.server_time + ", lng=" + this.lng + ", lat=" + this.lat + ", course=" + this.course + ", speed=" + this.speed + "}";
  }
}
