package com.calltree.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InformationCallTreeDTO {
	private long id;
	private String subject;
	private LocalDateTime dateCreated;
	private String content;
}
