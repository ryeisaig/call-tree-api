package com.calltree.assessment;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("assessment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AssessmentController {
	
	private final AssessmentService assessmentService;
	
	@Autowired
	AssessmentController(AssessmentService assessmentService){
		this.assessmentService = assessmentService;
	}

	@GetMapping
	public ResponseEntity<List<AssessmentEntity>> getAllAssessments(){

		
		return ResponseEntity.ok(assessmentService.getAllAssessments(null));
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssessmentEntity> getAssessmentDetails(@PathVariable("id") Long assessmentId){
		
		return ResponseEntity.ok(assessmentService.getAssessmentById(null, assessmentId));
	}
	
	@GetMapping("/print/{id}")
	public  ResponseEntity<InputStreamResource> print(@PathVariable("id") Long assessmentId){
		ByteArrayInputStream bis = assessmentService.print(assessmentId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=enrollment.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	
	@PostMapping
	public ResponseEntity<String> createAssessment(@RequestBody AssessmentRequest request){
		assessmentService.createAssessment(request);	
		return new ResponseEntity<String>("success", HttpStatus.CREATED);
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteAssessment(@PathVariable("id") Long assessmentId, @RequestParam String requestId, 
//		@RequestParam String username, 
//		@RequestParam String sessionId,
//		@RequestParam String clientIp, 
//		@RequestParam String application){
//		
//		GenericRequest request = new GenericRequest(requestId, sessionId, username, clientIp, application);
//		assessmentService.deleteAssessment(request, assessmentId);	
//		return new ResponseEntity<String>("success", HttpStatus.NO_CONTENT);
//	}
//	
//	@PatchMapping("/{id}")
//	public ResponseEntity<String> modifyAssessmentFunctions(@PathVariable("id") Long assessmentId, @RequestBody AssessmentRequest request){
//		assessmentService.modifyAssessment(request, assessmentId);
//		return new ResponseEntity<String>("success", HttpStatus.NO_CONTENT);
//	}
}
