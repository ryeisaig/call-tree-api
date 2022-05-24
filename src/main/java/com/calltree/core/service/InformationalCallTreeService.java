package com.calltree.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.calltree.api.dto.InformationalCallTreeDTO;
import com.calltree.core.entity.InformationalCallTreeEntity;
import com.calltree.core.repository.InformationCallTreeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InformationalCallTreeService { 

	private final InformationCallTreeRepository informationCallTreeRepository;

	public InformationalCallTreeEntity createCallTree(InformationalCallTreeDTO callTree) {
		InformationalCallTreeEntity newCallTree = new InformationalCallTreeEntity();
		newCallTree.setCreatedBy(callTree.getUsername());
		newCallTree.setSubject(callTree.getSubject());
		newCallTree.setContent(callTree.getContent());
		informationCallTreeRepository.save(newCallTree);
		return newCallTree;
	}
	
	public List<InformationalCallTreeEntity> getAllCallTrees() {
		List<InformationalCallTreeEntity> callTreeList = informationCallTreeRepository.findAll();
		return callTreeList;
	}
	
	public InformationalCallTreeEntity getCallTreeById(long id) {
		return informationCallTreeRepository.findById(id).get();
	}
}
