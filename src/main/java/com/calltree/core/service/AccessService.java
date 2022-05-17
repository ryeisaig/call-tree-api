package com.calltree.core.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.calltree.api.dto.AccessDTO;
import com.calltree.core.entity.AccessEntity;
import com.calltree.core.repository.AccessRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessService { 

	private final AccessRepository accessRepository;

	public void createAdminAccess(AccessDTO access) {
		AccessEntity accessEntity = new AccessEntity();
		accessEntity.setName(access.getName());
		accessEntity.setPassword(access.getPassword());
		accessEntity.setUsername(access.getUsername());
		accessEntity.setCreatedBy(access.getCreatedBy());
		accessRepository.save(accessEntity);
	}
	
	public String verifyAccess(AccessDTO access) {
		Optional<AccessEntity> accessEntity = accessRepository.findOneByUsernameAndPassword(access.getUsername(), access.getPassword());
		if(accessEntity.isPresent()) {
			return accessEntity.get().getName();
		} else {
			return null;
		}
	}
}
