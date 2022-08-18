package com.calltree.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calltree.core.entity.ReferenceEntity;

@Repository
public interface ReferenceRepository extends JpaRepository<ReferenceEntity, Long> {

	List<ReferenceEntity> findByType(String type);

	List<ReferenceEntity> findByKey(String value);


}
