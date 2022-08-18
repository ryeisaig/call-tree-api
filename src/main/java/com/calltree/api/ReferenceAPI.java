package com.calltree.api;

import java.util.List;
import java.util.Map;

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

import com.calltree.api.dto.ReferenceDTO;
import com.calltree.core.entity.ReferenceEntity;
import com.calltree.core.service.ReferenceService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/reference")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReferenceAPI {
	
	private final ReferenceService referenceService;

	@PostMapping
	public ResponseEntity<Object> createReference(@RequestBody ReferenceDTO ref) {
		referenceService.createReference(ref);
		return ResponseEntity.ok(null);
	}
	
	@PatchMapping
	public ResponseEntity<ReferenceEntity> modify(@RequestBody ReferenceDTO ref) {
		return ResponseEntity.ok(referenceService.modifyReference(ref));
	}
	
	@GetMapping
	public ResponseEntity<List<ReferenceEntity>> getRef() {
		return ResponseEntity.ok(referenceService.retrieveAll());
	}
	
	@GetMapping("/{type}")
	public ResponseEntity<List<ReferenceEntity>> getRef(@PathVariable String type) {
		return ResponseEntity.ok(referenceService.retrieveByKey(type));
	}
	
	@GetMapping("/parent/report-type")
	public ResponseEntity<Map<String, List<String>>> getRefParent() {
		return ResponseEntity.ok(referenceService.getParentRef());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id) {
		referenceService.deleteReference(id);
		return ResponseEntity.ok(null);
	}
}
