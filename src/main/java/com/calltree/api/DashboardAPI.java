package com.calltree.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calltree.api.dto.DashboardDTO;
import com.calltree.core.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/dashboard")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DashboardAPI {
	
	private final DashboardService dashboardService;
	
	@GetMapping
	public ResponseEntity<DashboardDTO> getStats() {
		return ResponseEntity.ok(dashboardService.getDashboard());
	}
}
