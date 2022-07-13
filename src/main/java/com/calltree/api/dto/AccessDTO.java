package com.calltree.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessDTO {
	private String username;
	private String password;
	private String name;
	private String createdBy;
	private String role;
	private String brgy;
}
