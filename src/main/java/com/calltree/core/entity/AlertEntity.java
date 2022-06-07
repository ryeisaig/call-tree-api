package com.calltree.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_alert")
public class AlertEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	private String response;
	
	private String status;
	
	@PrePersist
	private void prePersist() {
		this.setStatus("New");
		this.setCreatedDate(LocalDateTime.now());
	}
}
