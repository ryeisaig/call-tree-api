package com.calltree.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
	private String mobileNumber;
	private String name;
	private String lastName;
	private String brgy;
	private String address;
	private String geolocationX;
	private String geolocationY;
}
