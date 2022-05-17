package com.calltree.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calltree.core.entity.AccessEntity;

@Repository
public interface AccessRepository extends JpaRepository<AccessEntity, Long> {

	Optional<AccessEntity> findOneByUsernameAndPassword(String username, String password);

}
