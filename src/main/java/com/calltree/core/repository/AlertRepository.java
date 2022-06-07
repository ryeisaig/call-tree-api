package com.calltree.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calltree.core.entity.AlertEntity;

@Repository
public interface AlertRepository extends JpaRepository<AlertEntity, Long> {

	List<AlertEntity> findByOrderByCreatedDateDesc();

	List<AlertEntity> findByMobileNumberOrderByCreatedDateDesc(String mobileNumber);

}
