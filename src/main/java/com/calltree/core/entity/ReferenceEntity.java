package com.calltree.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
@Table(name = "tbl_reference")
public class ReferenceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="refKey")
	private String key;

	private String value;

	private String type;

	private String createdBy;
	
	private LocalDateTime createdDate;

	@PrePersist
	private void prePersist() {
		this.setCreatedDate(LocalDateTime.now());
	}
}
