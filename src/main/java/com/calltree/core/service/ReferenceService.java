package com.calltree.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.calltree.api.dto.ReferenceDTO;
import com.calltree.core.entity.ReferenceEntity;
import com.calltree.core.repository.ReferenceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReferenceService { 

	private final ReferenceRepository referenceRepository;


	public List<ReferenceEntity> retrieveAll() {
		return referenceRepository.findAll();
	}
	
	public Map<String, List<String>> getParentRef(){
		Map<String, List<String>> parents = new HashMap<>();
		 List<ReferenceEntity> refs = referenceRepository.findByType("Report Type");
		
		 for(ReferenceEntity r: refs) {
			 List<ReferenceEntity> children = referenceRepository.findByKey(r.getValue());
			 
			 List<String> re = children.stream().map(ReferenceEntity::getValue).collect(Collectors.toList());
			 parents.put(r.getValue(), re);
		 }
		 return parents;
	}
	
	public List<ReferenceEntity> retrieveByKey(String type) {
		return referenceRepository.findByType(type);
	}

	public ReferenceEntity modifyReference(ReferenceDTO reference) {
		Optional<ReferenceEntity> referenceEntity = referenceRepository.findById(reference.getId());
		
		referenceEntity.get().setKey(reference.getKey());
		referenceEntity.get().setType(reference.getType());
		referenceEntity.get().setValue(reference.getValue());

		referenceRepository.save(referenceEntity.get());
		return referenceEntity.get();
	}
	
	public void createReference(ReferenceDTO reference) {
		ReferenceEntity newRef = new ReferenceEntity();
		
		if(reference.getType() == null) {
			newRef.setType("Report Type");
		} else {
			newRef.setType(reference.getType());
		}
		newRef.setKey(reference.getKey());
		newRef.setValue(reference.getValue());
		
		referenceRepository.save(newRef);
	}
	
	public void deleteReference(long id) {
		referenceRepository.deleteById(id);
	}
}
