package com.evaluationtask.FamilyMemberApp.service;

import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import com.evaluationtask.FamilyMemberApp.repository.FamilyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyMemberService {
    private final FamilyMemberRepository familyMemberRepository;
    public void createFamilyMember(FamilyMemberDto familyMemberDto, Long familyId) {
        var familyMemberEntity = createFamilyMemberEntity(familyMemberDto, familyId);
        familyMemberRepository.save(familyMemberEntity);
    }

    private FamilyMemberEntity createFamilyMemberEntity(FamilyMemberDto familyMemberDto, Long familyId) {
        return FamilyMemberEntity.builder()
                .familyId(familyId)
                .firstName(familyMemberDto.getFirstName())
                .lastName(familyMemberDto.getLastName())
                .socialNumber(familyMemberDto.getSocialNumber())
                .build();
    }
}
