package com.evaluationtask.FamilyMemberApp.utils;


import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import org.springframework.stereotype.Component;

@Component
public class TestDataProvider {

    public FamilyMemberEntity getFamilyMemberEntity (FamilyMemberDto familyMemberDto, Long familyId) {
        return FamilyMemberEntity.builder()
                .familyid(familyId)
                .firstname(familyMemberDto.getFirstName())
                .lastname(familyMemberDto.getLastName())
                .age(familyMemberDto.getAge())
                .socialnumber(familyMemberDto.getSocialNumber())
                .build();
    }

    public FamilyMemberDto getFamilyMemberDto(int age) {
        FamilyMemberDto returnedMemberDto = new FamilyMemberDto();
        returnedMemberDto.setFirstName("Felipe");
        returnedMemberDto.setLastName("Santos");
        returnedMemberDto.setAge(age);
        returnedMemberDto.setSocialNumber(123123123L);
        return returnedMemberDto;
    }
}
