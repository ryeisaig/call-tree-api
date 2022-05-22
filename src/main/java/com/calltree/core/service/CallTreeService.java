package com.calltree.core.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.calltree.api.dto.CallTreeDTO;
import com.calltree.api.dto.CallTreeResponseDTO;
import com.calltree.core.entity.CallTreeEntity;
import com.calltree.core.entity.CallTreeResponseEntity;
import com.calltree.core.entity.UserEntity;
import com.calltree.core.enumeration.CallTreeResponseTypes;
import com.calltree.core.repository.CallTreeRepository;
import com.calltree.core.repository.CallTreeResponseRepository;
import com.calltree.core.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CallTreeService { 

	private final CallTreeRepository callTreeRepository;
	private final CallTreeResponseRepository callTreeResponseRepository;
	private final UserRepository userRepository;

	public CallTreeEntity createCallTree(CallTreeDTO callTree) {
		CallTreeEntity newCallTree = new CallTreeEntity();
		newCallTree.setCreatedBy(callTree.getUsername());
		newCallTree.setSubject(callTree.getSubject());
		newCallTree.setCaption(callTree.getCaption());
		newCallTree.setReferenceId(UUID.randomUUID().toString());
		newCallTree.setResponseTypes(callTree.getResponseTypes());
		callTreeRepository.save(newCallTree);
		return newCallTree;
	}
	
	public List<CallTreeEntity> getAllCallTrees() {
		List<CallTreeEntity> callTreeList = callTreeRepository.findAll();
		
		callTreeList.forEach(callTree -> {
			int safeResponses = callTreeResponseRepository
					.countByCallTreeIdAndResponse(callTree.getId(), CallTreeResponseTypes.SAFE);
			int uncertainResponses = callTreeResponseRepository
					.countByCallTreeIdAndResponse(callTree.getId(), CallTreeResponseTypes.UNCERTAIN);
			int dangerResponses = callTreeResponseRepository
					.countByCallTreeIdAndResponse(callTree.getId(), CallTreeResponseTypes.NEED_HELP);
			
			callTree.setTotalResponses(safeResponses + uncertainResponses + dangerResponses);
			callTree.setTotalInDanger(dangerResponses);
			callTree.setTotalSafe(safeResponses);
			callTree.setTotalUncertain(uncertainResponses);
		});
		
		return callTreeList;
		
	}
	
	public CallTreeResponseEntity saveCallTreeResponse(CallTreeResponseDTO callTreeResponse) {
		CallTreeEntity callTree = callTreeRepository.findById(callTreeResponse.getCallTreeId()).get();
		
		CallTreeResponseEntity newCallTreeResponse = new CallTreeResponseEntity();
		newCallTreeResponse.setCreatedBy(callTreeResponse.getMobileNumber());
		newCallTreeResponse.setDateTimeResponded(LocalDateTime.now());
		newCallTreeResponse.setAdditionalDetails(callTreeResponse.getAdditionalDetails());
		newCallTreeResponse.setResponse(callTreeResponse.getResponse());
		newCallTreeResponse.setCallTree(callTree);
		callTreeResponseRepository.save(newCallTreeResponse);
		return newCallTreeResponse;
	}
	
	public List<CallTreeEntity> getPendingCallTree(String mobileNumber) {
		UserEntity user = userRepository.findByMobileNumber(mobileNumber);
		List<CallTreeEntity> callTreeList = callTreeRepository.findByCreatedDateGreaterThan(user.getCreatedDate());
		List<CallTreeResponseEntity> respondedCallTreeList = callTreeResponseRepository.findByCreatedBy(mobileNumber);
		
		List<CallTreeEntity> filteredCallTree = new ArrayList<>();
		callTreeList.forEach((callTree) -> {
			boolean found = false;
			for(CallTreeResponseEntity callTreeResponse : respondedCallTreeList) {
				if(callTreeResponse.getCallTree().getId() == callTree.getId()) {
					found = true;
				}
			}
			if(!found) {
				filteredCallTree.add(callTree);
			}
		});
		
		return filteredCallTree;
	}
	
	public CallTreeEntity getCallTreeById(long id) {
		return callTreeRepository.findById(id).get();
	}
	
	public List<CallTreeResponseEntity> getCallTreeResponses(long id) {
		List<CallTreeResponseEntity> responses = callTreeResponseRepository.findByCallTreeId(id);
		responses.forEach(resp -> {
			UserEntity user = userRepository.findByMobileNumber(resp.getCreatedBy());
			resp.setAddress(user.getAddress());
			resp.setFullname(user.getName());
			resp.setGeolocationX(user.getGeolocationX());
			resp.setGeolocationY(user.getGeolocationY());
		});
		return responses;
	}
	
	public List<CallTreeResponseEntity> getRespondedCallTree(String mobileNumber) {
		return callTreeResponseRepository.findByCreatedBy(mobileNumber);
	}
}
