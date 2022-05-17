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
@Table(name = "tbl_access")
public class AccessEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	
	private String username;
	
	private String password;
	
	private String name;

	private String createdBy;
	
	private LocalDateTime createdDate;
	
	@PrePersist
	private void prePersist() {
		this.setCreatedDate(LocalDateTime.now());
	}
}
