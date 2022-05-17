package com.calltree.api.dto;

import com.calltree.core.enumeration.CallTreeResponseTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CallTreeResponseDTO {
	private String mobileNumber;
	private long callTreeId;
	private String additionalDetails;
	private CallTreeResponseTypes response;
}
