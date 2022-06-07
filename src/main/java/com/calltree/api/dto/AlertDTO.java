package com.calltree.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlertDTO {
	private long id;
	
	private String reportType;
	
	private boolean humanDamage;
	
	private String subject;
	
	private String description;
	
	private String location;
			
	private String createdBy;
	
	private LocalDateTime createdDate;
	
	private String fullName;
	
	private String mobileNumber;
	
	private String address;
	
	private String geolocationX;
	
	private String geolocationY;
	
	private String status;
}
