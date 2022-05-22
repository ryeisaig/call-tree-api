package com.calltree.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calltree.api.dto.CallTreeDTO;
import com.calltree.api.dto.CallTreeResponseDTO;
import com.calltree.core.entity.CallTreeEntity;
import com.calltree.core.entity.CallTreeResponseEntity;
import com.calltree.core.service.CallTreeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/calltree")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CallTreeAPI {
	
	private final CallTreeService callTreeService;
	
	@PostMapping
	@SendTo("/created")
	public ResponseEntity<CallTreeEntity> createCallTree(@RequestBody CallTreeDTO callTree) {
		return ResponseEntity.ok(callTreeService.createCallTree(callTree));
	}
	
	@PostMapping("/respond")
	public ResponseEntity<CallTreeResponseEntity> saveCallTree(@RequestBody CallTreeResponseDTO callTreeResponse) {
		return ResponseEntity.ok(callTreeService.saveCallTreeResponse(callTreeResponse));
	}
	
	@GetMapping("/pending")
	public ResponseEntity<List<CallTreeEntity>> getPendingCallTree(@RequestParam String mobileNumber) {
		return ResponseEntity.ok(callTreeService.getPendingCallTree(mobileNumber));
	}
	
	@GetMapping("/pending/{callTreeId}")
	public ResponseEntity<CallTreeEntity> getPendingCallTreeById(@PathVariable long callTreeId) {
		return ResponseEntity.ok(callTreeService.getCallTreeById(callTreeId));
	}
	
	@GetMapping("/responded")
	public ResponseEntity<List<CallTreeResponseEntity>> getRespondedCallTree(@RequestParam String mobileNumber) {
		return ResponseEntity.ok(callTreeService.getRespondedCallTree(mobileNumber));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CallTreeEntity>> getAllCallTree() {
		return ResponseEntity.ok(callTreeService.getAllCallTrees());
	}
	
	@GetMapping("/{id}/responses")
	public ResponseEntity<List<CallTreeResponseEntity>> getAllCallTreeResponses(@PathVariable long id) {
		return ResponseEntity.ok(callTreeService.getCallTreeResponses(id));
	}
}
