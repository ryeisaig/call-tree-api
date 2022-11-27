package com.calltree.core.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.calltree.api.dto.AlertDTO;
import com.calltree.core.entity.AlertEntity;
import com.calltree.core.repository.AlertRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertService { 
	
	private final AlertRepository alertRepository;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	public AlertEntity createAlert(AlertDTO alert) {
		AlertEntity newAlert = new AlertEntity();
		newAlert.setSubject(alert.getSubject());
		newAlert.setCreatedBy(alert.getFullName());
		newAlert.setDescription(alert.getDescription());
		newAlert.setFullName(alert.getFullName());
		newAlert.setGeolocationX(alert.getGeolocationX());
		newAlert.setGeolocationY(alert.getGeolocationY());
		newAlert.setLocation(alert.getLocation());
		newAlert.setHumanDamage(alert.isHumanDamage());
		newAlert.setMobileNumber(alert.getMobileNumber());
		newAlert.setReportType(alert.getReportType());
		newAlert.setSubject(alert.getSubject());
		alertRepository.save(newAlert);
		return newAlert;
	}
	
	public List<AlertEntity> getAlertsByMonth(String month) {
		List<AlertEntity> alerts = null;
		
		if(month == null || month.trim().isBlank()) {
			alerts = alertRepository.findByOrderByCreatedDateDesc();
		} else {
			
			LocalDate localDate = LocalDate.parse(month, formatter);
			LocalDateTime start = localDate.atStartOfDay();
			LocalDateTime end = localDate.plusMonths(1).atStartOfDay();
			alerts = alertRepository.findByCreatedDateBetween(start, end);
		}
				
		return alerts;
	}
	
	public List<AlertEntity> getAlerts(String mobileNumber) {
		List<AlertEntity> alerts = alertRepository.findByMobileNumberOrderByCreatedDateDesc(mobileNumber);
		return alerts;
	}
	
	
	public AlertEntity getAlert(long id) {
		AlertEntity alert = alertRepository.findById(id).get();
		return alert;
	}
	
	public void updateAlert(long id, String status) {
		AlertEntity alert = alertRepository.getById(id);
		alert.setStatus(status);
		alertRepository.save(alert);
		
	}
}
