package com.calltree.assessment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenericRequest {
	public GenericRequest(String requestId, String sessionId, String username, String clientIp, String application) {
		this.requestId = requestId;
	}
	private String requestId;
}
