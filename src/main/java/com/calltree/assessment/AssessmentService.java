package com.calltree.assessment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfDiv;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

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
		assessment.setAffectedBrgyRoad(request.getAssessment().getAffectedBrgyRoad());
		assessment.setAffectedCityRoad(request.getAssessment().getAffectedCityRoad());
		assessment.setAffectedCommunication(request.getAssessment().getAffectedCommunication());
		assessment.setAffectedNationalRoad(request.getAssessment().getAffectedNationalRoad());
		assessment.setAreasCovered(request.getAssessment().getAreasCovered());
		assessment.setBrgy(request.getAssessment().getBrgy());
		assessment.setCreatedBy(request.getAssessment().getCreatedBy());
		assessment.setDamagedAgriculture(request.getAssessment().getDamagedAgriculture());
		assessment.setDamagedGovtOffice(request.getAssessment().getDamagedGovtOffice());
		assessment.setDamagedHospital(request.getAssessment().getDamagedHospital());
		assessment.setDamagedMarket(request.getAssessment().getDamagedMarket());
		assessment.setDamagedSchool(request.getAssessment().getDamagedSchool());
		assessment.setDead(request.getAssessment().getDead());
		assessment.setEvacuatedFamilies(request.getAssessment().getEvacuatedFamilies());
		assessment.setFamiliesAffected(request.getAssessment().getFamiliesAffected());
		assessment.setIncident(request.getAssessment().getIncident());
		assessment.setInjured(request.getAssessment().getInjured());
		assessment.setMissing(request.getAssessment().getMissing());
		assessment.setOtherDamages(request.getAssessment().getOtherDamages());
		assessment.setPartiallyDamagedHouse(request.getAssessment().getPartiallyDamagedHouse());
		assessment.setPersonsAffected(request.getAssessment().getPersonsAffected());
		assessment.setPowerInterruption(request.getAssessment().getPowerInterruption());
		assessment.setTotallyDamagedHouse(request.getAssessment().getTotallyDamagedHouse());
		assessment.setType(request.getAssessment().getType());
		assessment.setWaterShortage(request.getAssessment().getWaterShortage());
		assessment.setWeather(request.getAssessment().getWeather());

		assessmentRepository.save(assessment);

		log.info("[END] AssessmentServiceImpl.createAssessment");
	}

	public ByteArrayInputStream print(long id) {
		
		Optional<AssessmentEntity> assessment = assessmentRepository.findById(id);
		AssessmentEntity ass = assessment.get();
		
		Document doc = new Document();
		PdfWriter docWriter = null;

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			// special font sizes
			Font logoFont = new Font(FontFamily.HELVETICA, 16, Font.BOLD, new BaseColor(252, 189, 64));
			Font bfBold12 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
			Font normalFont = new Font(FontFamily.HELVETICA, 8);
			// file path
			docWriter = PdfWriter.getInstance(doc, out);

			// document header attributes
			doc.addAuthor("tagaytay-cdrrmc");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("tagaytay-cdrrmc");
			doc.addTitle("Tagaytay City Disaster Risk Reduction and Management Council");
			doc.setPageSize(PageSize.LETTER);
			doc.setMargins(50, 50, 5, 5);

			// open document
			doc.open();
			
			// create a paragraph
			Paragraph paragraph = new Paragraph();
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setFont(new Font(FontFamily.HELVETICA, 10));
	
			paragraph.add(new Phrase("Republic of the Philippines", new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, new BaseColor(40, 53, 147))));
			paragraph.add(Chunk.NEWLINE);
			paragraph.add(new Phrase("City Government of Tagaytay", new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, new BaseColor(40, 53, 147))));
			paragraph.add(Chunk.NEWLINE);
			paragraph.add(new Phrase("Tagaytay City Risk Reduction and Management Council", new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(40, 53, 147))));
			paragraph.add(Chunk.NEWLINE);
			paragraph.add(new Phrase("1 Akle Street, City Hall Complex, Tagaytay City", new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, new BaseColor(40, 53, 147))));
			paragraph.add(Chunk.NEWLINE);
			paragraph.add(new Phrase("Hotline No. (046) 483-0446", new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, new BaseColor(40, 53, 147))));
			paragraph.add(Chunk.NEWLINE);
			paragraph.add(new LineSeparator());
			paragraph.add(Chunk.NEWLINE);
			paragraph.add(new Phrase("Initial Report", new Font(FontFamily.HELVETICA, 14, Font.BOLD)));
			paragraph.add(Chunk.NEWLINE);
			
			String pattern = "MMMM dd yyyy (hh:mm aa)";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			paragraph.add(new Phrase(simpleDateFormat.format(new Date()), new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(Chunk.NEWLINE);

			PdfDiv mainDiv = new PdfDiv();
			mainDiv.addElement(new Chunk("A. Profile of the Event", new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			mainDiv.addElement(new Chunk("                    Type                      :          " + ass.getType(), new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			mainDiv.addElement(new Chunk("                    Source of Report :          Tagaytay City Disaster Risk Reduction Management Council", new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			mainDiv.addElement(new Chunk("                    Weather                :          " + ass.getWeather(), new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			mainDiv.addElement(new Chunk("B. Summary of Effects", new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			mainDiv.addElement(new Chunk("     1.    Areas Covered          :          " + ass.getBrgy(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("     2.    Untowards Incident    :          " + ass.getIncident(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("     3.    Population Affected", new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           a.    Families           :          " + ass.getFamiliesAffected(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           b.    Persons           :          " + ass.getPersonsAffected(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           c.    Population displaced at the evacuation center (Pre-EVAC):          ", new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("                   Families          :          " + ass.getEvacuatedFamilies(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("     4.    Casualties", new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           a.    Dead        :          " + ass.getDead(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           b.    Injured     :          " + ass.getInjured(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           c.    Missing     :          " + ass.getMissing(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("     5.    Damaged Properties", new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           a.    Houses", new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("               a.a    Partially Damaged        :          " + ass.getPartiallyDamagedHouse(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("               a.b    Totally Damaged          :          " + ass.getTotallyDamagedHouse(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           b.    School Building   :     " + ass.getDamagedSchool(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           c.    Hospital          :     " + ass.getDamagedHospital(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           d.    Govt Offices      :     " + ass.getDamagedGovtOffice(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           e.    Public Market     :     " + ass.getDamagedMarket(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           f.    Other             :     " + ass.getOtherDamages(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("     6.    Affected Lifelines", new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           6.1.    Transporation Facilities", new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           National Road        :          " + ass.getAffectedNationalRoad(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           City Road            :          " + ass.getAffectedCityRoad(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           Brgy Road            :          " + ass.getAffectedBrgyRoad(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           6.2.    Communication Facilities       :      " + ass.getAffectedCommunication(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           6.3.    Power Interruption             :      " + ass.getPowerInterruption(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("           6.4.    Water Shortage                 :      " + ass.getWaterShortage(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			mainDiv.addElement(new Chunk("     7.    Damage to Agricultural Products        :      " + ass.getDamagedAgriculture(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));

			paragraph.add(mainDiv);
			doc.add(paragraph);

			
		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null) {
				// close the document
				doc.close();
			}
			if (docWriter != null) {
				// close the writer
				docWriter.close();
			}
		}
		return new ByteArrayInputStream(out.toByteArray());
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
