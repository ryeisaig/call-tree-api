package com.calltree.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calltree.core.entity.CallTreeResponseEntity;
import com.calltree.core.enumeration.CallTreeResponseTypes;

@Repository
public interface CallTreeResponseRepository extends JpaRepository<CallTreeResponseEntity, Long> {

	List<CallTreeResponseEntity> findByCreatedBy(String mobileNumber);

	int countByCallTreeIdAndResponse(long get_id, CallTreeResponseTypes type);

}
