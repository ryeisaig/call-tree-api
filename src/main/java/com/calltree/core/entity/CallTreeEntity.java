package com.calltree.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_calltree")
public class CallTreeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String subject;
	
	private String createdBy;
	
	@JsonFormat(pattern="hh:mm MMM dd, yyyy")
	private LocalDateTime createdDate;
	
	@Transient
	private int totalResponses;
	
	@Transient
	private int totalSafe;
	
	@Transient
	private int totalUncertain;
	
	@Transient
	private int totalInDanger;
	
	@PrePersist
	private void prePersist() {
		this.setCreatedDate(LocalDateTime.now());
	}
}