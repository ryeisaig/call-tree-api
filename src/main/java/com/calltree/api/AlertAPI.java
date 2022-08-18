package com.calltree.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calltree.api.dto.AlertDTO;
import com.calltree.api.dto.TextMessageDTO;
import com.calltree.core.entity.AlertEntity;
import com.calltree.core.entity.CallTreeEntity;
import com.calltree.core.service.AlertService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/alert")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlertAPI {
	
	private final AlertService alertService;
	
	private final SimpMessagingTemplate template;
	
	@SendTo("/topic/alert")
	public TextMessageDTO broadcastMessageResponded(@Payload TextMessageDTO textMessageDTO) {
		return textMessageDTO;
	}
	
	@SendTo("/topic/alert-update")
	public TextMessageDTO broadcastMessageResp(@Payload TextMessageDTO textMessageDTO) {
		return textMessageDTO;
	}
	
	@PostMapping
	public ResponseEntity<AlertEntity> createCallTree(@RequestBody AlertDTO alert) {
		template.convertAndSend("/topic/alert", new TextMessageDTO("test"));
		return ResponseEntity.ok(alertService.createAlert(alert));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity updateStatus(@PathVariable long id, @RequestParam String status) {
		alertService.updateAlert(id, status);
		template.convertAndSend("/topic/alert-update", new TextMessageDTO("test"));
		return ResponseEntity.ok(null);
	}
	
	@GetMapping()
	public ResponseEntity<List<AlertEntity>> getAlerts(@RequestParam String mobileNumber) {
		return ResponseEntity.ok(alertService.getAlerts(mobileNumber));
	}
	
	@GetMapping("/{id}/details")
	public ResponseEntity<AlertEntity> getAlertById(@PathVariable long id) {
		return ResponseEntity.ok(alertService.getAlert(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AlertEntity>> getAlerts() {
		return ResponseEntity.ok(alertService.getAlerts());
	}
}
