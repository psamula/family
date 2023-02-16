package com.evaluationtask.FamilyMemberApp.service;

import com.evaluationtask.FamilyMemberApp.exceptions.DuplicateEntityException;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import com.evaluationtask.FamilyMemberApp.repository.FamilyMemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FamilyMemberServiceTest {
    @Autowired
    private FamilyMemberService familyMemberService;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Transactional
    @Test
    public void shouldSuccessfullyCreateFamilyMember() {
        // given
        FamilyMemberDto familyMemberDto = new FamilyMemberDto();
        familyMemberDto.setFirstName("Leo");
        familyMemberDto.setLastName("Messi");
        familyMemberDto.setSocialNumber(123456789L);

        Long familyId = -123L;

        // when
        familyMemberService.createFamilyMember(familyMemberDto, familyId);

        // then
        List<FamilyMemberEntity> familyMembers = familyMemberRepository.findAllByFamilyid(familyId);
        assertThat(familyMembers).hasSize(1);

        FamilyMemberEntity familyMemberEntity = familyMembers.get(0);
        assertThat(familyMemberEntity.getFamilyid()).isEqualTo(familyId);
        assertThat(familyMemberEntity.getFirstname()).isEqualTo(familyMemberDto.getFirstName());
        assertThat(familyMemberEntity.getLastname()).isEqualTo(familyMemberDto.getLastName());
        assertThat(familyMemberEntity.getSocialnumber()).isEqualTo(familyMemberDto.getSocialNumber());
    }

    @Transactional
    @Test
    public void shouldThrowDuplicateEntityException_whenPassedADuplicate() {
        // given
        FamilyMemberDto familyMemberDto1 = new FamilyMemberDto();
        familyMemberDto1.setFirstName("Patrick");
        familyMemberDto1.setLastName("Bateman");
        familyMemberDto1.setSocialNumber(123456789L);

        FamilyMemberDto familyMemberDto2 = new FamilyMemberDto();
        familyMemberDto2.setFirstName("Patrick");
        familyMemberDto2.setLastName("Bateman");
        familyMemberDto2.setSocialNumber(123456789L);

        Long familyId = -123L;

        familyMemberService.createFamilyMember(familyMemberDto1, familyId);

        // when
        DuplicateEntityException thrown = assertThrows(DuplicateEntityException.class, () ->
                familyMemberService.createFamilyMember(familyMemberDto2, familyId));

        // then
        assertThat(thrown.getMessage()).isEqualTo(String.format("Family member of social number %d already exists",
                familyMemberDto1.getSocialNumber()));
    }

    @Transactional
    @Test
    public void shouldSuccessfullySearchFamilyMembers() {
        // given
        FamilyMemberDto familyMemberDto1 = new FamilyMemberDto();
        familyMemberDto1.setFirstName("White");
        familyMemberDto1.setLastName("Gandalf");
        familyMemberDto1.setSocialNumber(123456789L);

        FamilyMemberDto familyMemberDto2 = new FamilyMemberDto();
        familyMemberDto2.setFirstName("Grey");
        familyMemberDto2.setLastName("Gandalf");
        familyMemberDto2.setSocialNumber(987654321L);

        Long familyId = -123L;

        familyMemberService.createFamilyMember(familyMemberDto1, familyId);
        familyMemberService.createFamilyMember(familyMemberDto2, familyId);

        // when
        List<FamilyMemberEntity> familyMembers = familyMemberService.searchFamilyMembers(familyId);
        FamilyMemberEntity familyMemberEntity1 = familyMembers.get(0);
        FamilyMemberEntity familyMemberEntity2 = familyMembers.get(1);

        // then
        assertThat(familyMembers).hasSize(2);

        assertThat(familyMemberEntity1.getFamilyid()).isEqualTo(familyId);
        assertThat(familyMemberEntity1.getFirstname()).isEqualTo(familyMemberDto1.getFirstName());
        assertThat(familyMemberEntity1.getLastname()).isEqualTo(familyMemberDto1.getLastName());
        assertThat(familyMemberEntity1.getSocialnumber()).isEqualTo(familyMemberDto1.getSocialNumber());

        assertThat(familyMemberEntity2.getFamilyid()).isEqualTo(familyId);
        assertThat(familyMemberEntity2.getFirstname()).isEqualTo(familyMemberDto2.getFirstName());
        assertThat(familyMemberEntity2.getLastname()).isEqualTo(familyMemberDto2.getLastName());
        assertThat(familyMemberEntity2.getSocialnumber()).isEqualTo(familyMemberDto2.getSocialNumber());
    }
    @Transactional
    @Test
    public void shouldThrowEntityNotFound_whenPassedNonExistingId() {
        // given
        Long familyId = -404L;

        // when
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () ->
                familyMemberService.searchFamilyMembers(familyId));

        // then
        assertThat(thrown.getMessage()).isEqualTo(String.format("There are no members of family of id %d", familyId));
    }
}