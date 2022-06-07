package com.calltree.core.service;

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
	
	public List<AlertEntity> getAlerts() {
		List<AlertEntity> alerts = alertRepository.findByOrderByCreatedDateDesc();
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
