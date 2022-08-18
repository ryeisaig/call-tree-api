package com.calltree.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReferenceDTO {
	
	private long id;

	private String key;

	private String value;

	private String type;

	private String createdBy;
	
	private LocalDateTime createdDate;
}
