package com.calltree.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calltree.api.dto.UserDTO;
import com.calltree.core.entity.OtpEntity;
import com.calltree.core.entity.UserEntity;
import com.calltree.core.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserAPI {
	
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	
	@GetMapping
	public ResponseEntity<UserEntity> getUser(@RequestParam String mobileNumber) {
		return ResponseEntity.ok(userService.retrieveUserByMobileNumber(mobileNumber));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserEntity>> getUsers() {
		return ResponseEntity.ok(userService.retrieveUsers());
	}
	
	@GetMapping("/otp")
	public ResponseEntity<OtpEntity> generateOtp(@RequestParam String mobileNumber) {
		return ResponseEntity.ok(userService.generateOTP(mobileNumber));
	}
	
	@GetMapping("/otp/validate")
	public ResponseEntity<Boolean> validateOtp(@RequestParam String referenceKey, @RequestParam String otp) {
		return ResponseEntity.ok(userService.validateOTP(referenceKey, otp));
	}
}
