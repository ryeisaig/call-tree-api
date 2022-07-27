package com.calltree.assessment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_assessment")
public class AssessmentEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String brgy;

	private String type;
	
	private String weather;
	
	private String areasCovered;
	
	private String incident;
	
	private String familiesAffected;
	
	private String personsAffected;
	
	private String evacuatedFamilies;

	private int dead;
	
	private int injured;
	
	private int missing;
	
	private String totallyDamagedHouse;
	
	private String partiallyDamagedHouse;
	
	private String damagedSchool;
	
	private String damagedHospital;
	
	private String damagedGovtOffice;
	
	private String damagedMarket;
	
	private String otherDamages;
	
	private String affectedNationalRoad;
	
	private String affectedCityRoad;
	
	private String affectedBrgyRoad;
	
	private String affectedCommunication;
	
	private String powerInterruption;
	
	private String waterShortage;
	
	private String damagedAgriculture;
	
	private String createdBy;
	
	private LocalDateTime createdDate;
	
	@PrePersist
	private void prePersist() {
		this.setCreatedDate(LocalDateTime.now());
	}

}
