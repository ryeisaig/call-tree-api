package com.calltree.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calltree.api.dto.InformationalCallTreeDTO;
import com.calltree.api.dto.TextMessageDTO;
import com.calltree.core.entity.InformationalCallTreeEntity;
import com.calltree.core.service.InformationalCallTreeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/informational")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InformationalCallTreeAPI {
	
	private final InformationalCallTreeService callTreeService;
	
	private final SimpMessagingTemplate template;
	
	@SendTo("/topic/informational")
	public TextMessageDTO broadcastMessageInfo(@Payload TextMessageDTO textMessageDTO) {
		return textMessageDTO;
	}
	
	@PatchMapping("/{callTreeId}")
	public ResponseEntity<InformationalCallTreeEntity> modifyCallTree(@PathVariable long callTreeId, @RequestBody InformationalCallTreeDTO callTree) {
		return ResponseEntity.ok(callTreeService.modify(callTreeId, callTree));
	}
	

	@PostMapping
	public ResponseEntity<InformationalCallTreeEntity> createCallTree(@RequestBody InformationalCallTreeDTO callTree) {
		template.convertAndSend("/topic/informational", new TextMessageDTO("test"));
		return ResponseEntity.ok(callTreeService.createCallTree(callTree));
	}
	
	
	@GetMapping("/{callTreeId}/details")
	public ResponseEntity<InformationalCallTreeEntity> getPendingCallTreeById(@PathVariable long callTreeId) {
		return ResponseEntity.ok(callTreeService.getCallTreeById(callTreeId));
	}
	
	@DeleteMapping("/{callTreeId}")
	public ResponseEntity<InformationalCallTreeEntity> deleteCallTree(@PathVariable long callTreeId) {
		callTreeService.delete(callTreeId);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping
	public ResponseEntity<List<InformationalCallTreeEntity>> getAllCallTree() {
		return ResponseEntity.ok(callTreeService.getAllCallTrees());
	}
}
