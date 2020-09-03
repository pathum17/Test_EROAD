package com.pathum.eroad.model;

import java.time.LocalDateTime;

import com.pathum.eroad.model.Record;

public class OutputFileRecord extends Record{
	
	private String zoneName;
	private LocalDateTime localDateTime;
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
	

}
