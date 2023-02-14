package com.evaluationtask.FamilyMemberApp.repository;

import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FamilyMemberRepository extends CrudRepository<FamilyMemberEntity, Long> {
    List<FamilyMemberEntity> findAllByFamilyid(Long familyid);
    boolean existsBySocialnumber(Long socialnumber);
}
