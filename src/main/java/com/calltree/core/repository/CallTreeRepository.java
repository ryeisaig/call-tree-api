package com.calltree.core.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calltree.core.entity.CallTreeEntity;

@Repository
public interface CallTreeRepository extends JpaRepository<CallTreeEntity, Long> {

	List<CallTreeEntity> findByCreatedDateGreaterThan(LocalDateTime createdDate);

}
