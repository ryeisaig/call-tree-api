package com.calltree.core.service;

import org.springframework.stereotype.Service;

import com.calltree.api.dto.DashboardDTO;
import com.calltree.core.repository.AlertRepository;
import com.calltree.core.repository.InformationCallTreeRepository;
import com.calltree.core.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService { 

	private final UserRepository userRepository;
	private final AlertRepository alertRepository;
	private final InformationCallTreeRepository informationCallTreeRepository;

	public DashboardDTO getDashboard() {
		DashboardDTO dashboard = new DashboardDTO();
		dashboard.setUsers(userRepository.count());
		dashboard.setAlerts(informationCallTreeRepository.count());
		dashboard.setReports(alertRepository.count());
		dashboard.setResponded(alertRepository.countByStatus("Responded"));
		return dashboard;
	}

}
