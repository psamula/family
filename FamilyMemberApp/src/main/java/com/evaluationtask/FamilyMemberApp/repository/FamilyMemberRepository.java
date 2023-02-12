package com.evaluationtask.FamilyMemberApp.repository;

import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import org.springframework.data.repository.CrudRepository;

public interface FamilyMemberRepository extends CrudRepository<FamilyMemberEntity, Long> {
}
