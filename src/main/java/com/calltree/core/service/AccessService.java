package com.calltree.core.service;

import java.util.List;
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
		accessEntity.setBrgy(access.getBrgy());
		accessEntity.setRole(access.getRole());
		accessRepository.save(accessEntity);
	}
	
	public String verifyAccess(AccessDTO access) {
		Optional<AccessEntity> accessEntity = accessRepository.findOneByUsernameAndPassword(access.getUsername(), access.getPassword());
		if(accessEntity.isPresent()) {
			return accessEntity.get().getName() + "," + accessEntity.get().getRole() + "," + accessEntity.get().getBrgy() + "," + accessEntity.get().getUsername();
		} else {
			return null;
		}
	}
	
	public List<AccessEntity> getAccounts() {
		List<AccessEntity> accounts = accessRepository.findByOrderByCreatedDateDesc();
		return accounts;
	}
	
	public void updateAccount(long id, AccessDTO access) {
		AccessEntity account = accessRepository.findById(id).get();
		account.setBrgy(access.getBrgy());
		account.setName(access.getName());
		account.setUsername(access.getUsername());
		account.setRole(access.getRole());
		accessRepository.save(account);
	}
	
	public void deleteAccount(long id) {
		accessRepository.deleteById(id);
	}
}
