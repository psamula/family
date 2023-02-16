package com.evaluationtask.FamilyApp.utils;

import com.evaluationtask.FamilyApp.model.*;
import com.evaluationtask.FamilyApp.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Component
public class TestDataProvider {

    public FamilyDto getFamilyDto() {
        FamilyDto returnedFamilyDto = new FamilyDto();
        returnedFamilyDto.setFamilyName("Lovelace");
        returnedFamilyDto.setNrOfInfants(0);
        returnedFamilyDto.setNrOfChildren(0);
        returnedFamilyDto.setNrOfAdults(0);
        return returnedFamilyDto;
    }
    public FamilyDto getFamilyDto(String name, int nrOfInfants, int nrOfChildren, int nrOfAdults) {
        FamilyDto returnedFamilyDto = new FamilyDto();
        returnedFamilyDto.setFamilyName(name);
        returnedFamilyDto.setNrOfInfants(nrOfInfants);
        returnedFamilyDto.setNrOfChildren(nrOfChildren);
        returnedFamilyDto.setNrOfAdults(nrOfAdults);
        return returnedFamilyDto;
    }
    public FamilyDto getFamilyDto(int nrOfInfants, int nrOfChildren, int nrOfAdults) {
        FamilyDto returnedFamilyDto = new FamilyDto();
        returnedFamilyDto.setFamilyName("Lovelace");
        returnedFamilyDto.setNrOfInfants(nrOfInfants);
        returnedFamilyDto.setNrOfChildren(nrOfChildren);
        returnedFamilyDto.setNrOfAdults(nrOfAdults);
        return returnedFamilyDto;
    }

    public FamilyMemberDto getFamilyMemberDto(int age) {
        return FamilyMemberDto.builder()
                .firstName("Felipe")
                .lastName("Santos")
                .age(age)
                .socialNumber(123123123L)
                .build();
    }
    public FamilyMemberDto getFamilyMemberDto(String lastName, int age) {
        return FamilyMemberDto.builder()
                .firstName("Felipe")
                .lastName(lastName)
                .age(age)
                .socialNumber(123123123L)
                .build();
    }
    public FamilyMemberDto getFamilyMemberDto(String lastName) {
        return FamilyMemberDto.builder()
                .firstName("Felipe")
                .lastName(lastName)
                .age(23)
                .socialNumber(123123123L)
                .build();
    }
    public FamilyEntity getFamilyEntity() {
        var returnedFamilyEntity =  new FamilyEntity();
        returnedFamilyEntity.setName("Snejider");
        returnedFamilyEntity.setNrofchildren(0);
        returnedFamilyEntity.setNrofinfants(0);
        returnedFamilyEntity.setNrofadults(0);
        return returnedFamilyEntity;
    }
    public FamilyEntity getFamilyEntity(int nrOfInfants, int nrOfChildren, int nrOfAdults) {
        var returnedFamilyEntity =  new FamilyEntity();
        returnedFamilyEntity.setName("Esteban");
        returnedFamilyEntity.setNrofchildren(nrOfChildren);
        returnedFamilyEntity.setNrofinfants(nrOfInfants);
        returnedFamilyEntity.setNrofadults(nrOfAdults);
        return returnedFamilyEntity;
    }
    public FamilyEntity getFamilyEntity(String lastName, int nrOfInfants, int nrOfChildren, int nrOfAdults) {
        var returnedFamilyEntity =  new FamilyEntity();
        returnedFamilyEntity.setName(lastName);
        returnedFamilyEntity.setNrofchildren(nrOfChildren);
        returnedFamilyEntity.setNrofinfants(nrOfInfants);
        returnedFamilyEntity.setNrofadults(nrOfAdults);
        return returnedFamilyEntity;
    }
    public FamilyMember getFamilyMember(FamilyMemberDto familyMemberDto) {
        var returnedFamilyMember = new FamilyMember();
        returnedFamilyMember.setFirstname(familyMemberDto.getFirstName());
        returnedFamilyMember.setLastname(familyMemberDto.getLastName());
        returnedFamilyMember.setAge(familyMemberDto.getAge());
        returnedFamilyMember.setSocialnumber(familyMemberDto.getSocialNumber());
        return returnedFamilyMember;
    }
}
