package com.calltree.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InformationalCallTreeDTO {
	private long id;
	private String subject;
	private String content;
	private LocalDateTime dateCreated;
	private String username;
}
