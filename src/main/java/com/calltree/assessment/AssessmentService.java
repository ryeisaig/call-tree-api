package com.calltree.assessment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssessmentService {
	
	private final AssessmentRepository assessmentRepository;

	public List<AssessmentEntity> getAllAssessments(GenericRequest request) {
		log.info("[START] AssessmentServiceImpl.getAllAssessments", request);
		
		List<AssessmentEntity> assessments = assessmentRepository.findAll();
			

		log.info("[END] AssessmentServiceImpl.getAllAssessments");
			
		return assessments;
	}
	

	public AssessmentEntity getAssessmentById(GenericRequest request, Long assessmentId) {
		log.info("[START] AssessmentServiceImpl.getAssessmentById {}", assessmentId);

		Optional<AssessmentEntity> assessment = assessmentRepository.findById(assessmentId);
	
		
		log.info("[END] AssessmentServiceImpl.getAssessmentById");
		return assessment.get();
	}

	public void createAssessment(AssessmentRequest request) {
		
		log.info("[START] AssessmentServiceImpl.createAssessment  {} ", request);
					
		AssessmentEntity assessment = new AssessmentEntity();
		assessment.setCasualties(request.getAssessment().getCasualties());
		assessment.setDamagedInfra(request.getAssessment().getDamagedInfra());
		assessment.setLivestock(request.getAssessment().getLivestock());
		assessment.setSubject(request.getAssessment().getSubject());
		assessment.setRemarks(request.getAssessment().getRemarks());
		assessmentRepository.save(assessment);
		
		log.info("[END] AssessmentServiceImpl.createAssessment");
	}

//	@Override
//	public void deleteAssessment(GenericRequest request, Long assessmentId) {
//		log.info("[START] AssessmentServiceImpl.deleteAssessment: assessmentId={}, request={}", assessmentId, request);
//				
//			String username = request.getSession().getUsername();
//			Date dateToday = new Date();
//			SessionEntity session = sessionRepository.findBySessionId(request.getSession().getSessionId());
//
//			Optional<AssessmentEntity> assessmentToBeDeleted = assessmentRepository.findById(assessmentId);
//			
//			if(assessmentToBeDeleted.get() == null) {
//				throw new ResourceNotFoundException();
//			}
//			
//			assessmentRepository.delete(assessmentToBeDeleted.get());
//			
//			AuditTrailEntity auditTrailEntry = new AuditTrailEntity();
//			auditTrailEntry.setType(GenericConstant.ROLE);
//			auditTrailEntry.setAction(GenericConstant.DELETE);
//			auditTrailEntry.setExecutedByUsername(username);
//			auditTrailEntry.setOldValue(assessmentToBeDeleted.toString());
//			auditTrailEntry.setTransactionDate(dateToday);
//			auditTrailEntry.setSession(session);
//			auditRepository.save(auditTrailEntry);
//			
//			log.info("[END] AssessmentServiceImpl.deleteAssessment");	
//	}
//
//	@Override
//	public void modifyAssessment(AssessmentRequest request, Long assessmentId) {
//		log.info("[START] AssessmentServiceImpl.modifyAssessment {}", request);
//			
//		String username = request.getSession().getUsername();
//		UserEntity user = userRepository.findUserBySchoolId(username);
//		SessionEntity session = sessionRepository.findBySessionId(request.getSession().getSessionId());
//
//		Date dateToday = new Date();
//		
//		Optional<AssessmentEntity> oldAssessment = assessmentRepository.findById(assessmentId);
//		AssessmentEntity prevOldAssessment = oldAssessment.get();
//		
//		if(oldAssessment.get() == null) {
//			throw new ResourceNotFoundException();
//		}
//		
//		oldAssessment.get().setAssessmentName(request.getAssessment().getAssessmentName());
//		oldAssessment.get().setFunctions(request.getAssessment().getFunctions());
//		oldAssessment.get().setUpdatedBy(user);
//		assessmentRepository.save(oldAssessment.get());
//		
//		AuditTrailEntity auditTrailEntry = new AuditTrailEntity();
//		auditTrailEntry.setType(GenericConstant.ROLE);
//		auditTrailEntry.setAction(GenericConstant.EDIT);
//		auditTrailEntry.setExecutedByUsername(username);
//		auditTrailEntry.setOldValue(prevOldAssessment.toString());
//		auditTrailEntry.setNewValue(oldAssessment.get().toString());
//		auditTrailEntry.setTransactionDate(dateToday);
//		auditTrailEntry.setSession(session);
//
//		log.info("[END] AssessmentServiceImpl.modifyAssessment");
//		
//	}

}
