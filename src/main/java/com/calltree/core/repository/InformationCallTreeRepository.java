package com.calltree.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calltree.core.entity.InformationalCallTreeEntity;

@Repository
public interface InformationCallTreeRepository extends JpaRepository<InformationalCallTreeEntity, Long> {

}
