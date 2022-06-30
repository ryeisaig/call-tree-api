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
@Table(name = "tbl_user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String mobileNumber;
	
	private String name;
	
	private String lastName;
	
	private String brgy;

	private String address;
	
	private String geolocationX;
	
	private String geolocationY;

	private String createdBy;
	
	private LocalDateTime createdDate;
	
	@PrePersist
	private void prePersist() {
		this.setCreatedDate(LocalDateTime.now());
	}
}
