package com.evaluationtask.FamilyMemberApp.repository;

import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilyMemberRepository extends CrudRepository<FamilyMemberEntity, Long> {
    List<FamilyMemberEntity> findAllByFamilyid(Long familyid);
    boolean existsBySocialnumber(Long socialnumber);

    @Modifying
    @Query("DELETE FROM familymember f WHERE f.familyid = :familyid")
    void deleteByFamilyId(@Param("familyid") Long familyid);
}
