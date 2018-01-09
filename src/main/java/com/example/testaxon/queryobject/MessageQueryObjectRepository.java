package com.example.testaxon.queryobject;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageQueryObjectRepository extends JpaRepository<MessageQueryObject, UUID> {
	Optional<MessageQueryObject> findById(UUID id);
}
