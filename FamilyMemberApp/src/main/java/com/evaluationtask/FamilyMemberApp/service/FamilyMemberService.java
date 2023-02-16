package com.evaluationtask.FamilyMemberApp.service;

import com.evaluationtask.FamilyMemberApp.exceptions.DuplicateEntityException;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import com.evaluationtask.FamilyMemberApp.repository.FamilyMemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyMemberService {
    private final FamilyMemberRepository familyMemberRepository;

    /* Build the family member entity from the FamilyMemberDto provided by FamilyApp
     component and save it in the FamilyMemberDB */
    public void createFamilyMember(FamilyMemberDto familyMemberDto, Long familyId) {
        var familyMemberEntity = createFamilyMemberEntity(familyMemberDto, familyId);
        if (familyMemberRepository.existsBySocialnumber(familyMemberEntity.getSocialnumber())) {
            throw new DuplicateEntityException(
                    String.format("Family member of social number %d already exists",
                            familyMemberEntity.getSocialnumber()));
        }
        familyMemberRepository.save(familyMemberEntity);
    }

    private FamilyMemberEntity createFamilyMemberEntity(FamilyMemberDto familyMemberDto, Long familyId) {
        return FamilyMemberEntity.builder()
                .familyid(familyId)
                .firstname(familyMemberDto.getFirstName())
                .lastname(familyMemberDto.getLastName())
                .socialnumber(familyMemberDto.getSocialNumber())
                .build();
    }

    // GET the members of the family of given id from FamilyMemberDB
    public List<FamilyMemberEntity> searchFamilyMembers(Long familyId) {
        List<FamilyMemberEntity> familyMemberEntities = familyMemberRepository.findAllByFamilyid(familyId);
        if (familyMemberEntities.isEmpty()) {
            throw new EntityNotFoundException(String.format("There are no members of family of id %d", familyId));
        }
        return familyMemberEntities;
    }
}
