package com.track.api.model;

import java.util.Date;

/**
 * @author RAM
 *
 */

public class AlarmMaster{

	private Integer sno;
	private Date sendTime;
	private String content;
	private String imeiNumber;
	private String vehicleId;
	private String status;
	private Date createDate;
	private String createdBy;
	
	
	public AlarmMaster() {
		super();
	}


	public Integer getSno() {
		return sno;
	}


	public void setSno(Integer sno) {
		this.sno = sno;
	}


	public Date getSendTime() {
		return sendTime;
	}


	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImeiNumber() {
		return imeiNumber;
	}


	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}


	public String getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	@Override
	public String toString() {
		return "AlarmMaster [sno=" + sno + ", sendTime=" + sendTime
				+ ", content=" + content + ", imeiNumber=" + imeiNumber
				+ ", vehicleId=" + vehicleId + ", status=" + status
				+ ", createDate=" + createDate + ", createdBy=" + createdBy
				+ "]";
	}
	
	

}
