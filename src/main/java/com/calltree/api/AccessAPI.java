package com.calltree.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calltree.api.dto.AccessDTO;
import com.calltree.core.entity.AccessEntity;
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
	
	@PostMapping
	public ResponseEntity<AccessEntity> createAccess(@RequestBody AccessDTO access) {
		accessService.createAdminAccess(access);
		return ResponseEntity.ok(null);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity update(@PathVariable long id, @RequestBody AccessDTO access) {
		accessService.updateAccount(id, access);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping
	public ResponseEntity<List<AccessEntity>> get() {
		return ResponseEntity.ok(accessService.getAccounts());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<List<AccessEntity>> delete(@PathVariable long id) {
		accessService.deleteAccount(id);
		return ResponseEntity.ok(null);
	}
}
