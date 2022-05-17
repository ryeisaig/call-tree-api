package com.calltree.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calltree.api.dto.AccessDTO;
import com.calltree.core.service.AccessService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/access")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccessAPI {
	
	private final AccessService accessService;
	
	@PostMapping("/verify")
	public ResponseEntity<String> login(@RequestBody AccessDTO access) {
		return ResponseEntity.ok(accessService.verifyAccess(access));
	}
}
