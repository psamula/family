package com.evaluationtask.FamilyApp.repository;

import com.evaluationtask.FamilyApp.model.FamilyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends CrudRepository<FamilyEntity, Long> {
}
