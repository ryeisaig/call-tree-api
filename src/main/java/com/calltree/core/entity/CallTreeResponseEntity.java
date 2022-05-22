package com.calltree.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.calltree.core.enumeration.CallTreeResponseTypes;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_calltree_response")
public class CallTreeResponseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private CallTreeEntity callTree;
			
	@Enumerated(EnumType.STRING)
	private CallTreeResponseTypes response;
	
	private String additionalDetails;
	
	private LocalDateTime dateTimeResponded;
	
	private String createdBy;
	
	private LocalDateTime createdDate;
	
	@Transient
	private String fullname;
	
	@Transient
	private String address;
	
	@Transient
	private String geolocationX;
	
	@Transient
	private String geolocationY;
	
	@PrePersist
	private void prePersist() {
		this.setCreatedDate(LocalDateTime.now());
	}
}
