package com.evaluationtask.FamilyMemberApp.service;

import com.evaluationtask.FamilyMemberApp.exceptions.DuplicateEntityException;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import com.evaluationtask.FamilyMemberApp.repository.FamilyMemberRepository;
import com.evaluationtask.FamilyMemberApp.utils.TestDataProvider;
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
    @Autowired
    private TestDataProvider testDataProvider;

    @Transactional
    @Test
    public void shouldSuccessfullyCreateFamilyMember() {
        // given
        FamilyMemberDto familyMemberDto = testDataProvider.getFamilyMemberDto(23);
        Long familyId = -123L;
        FamilyMemberEntity expectedFamilyMemberEntity = testDataProvider.getFamilyMemberEntity(familyMemberDto, familyId);

        // when
        familyMemberService.createFamilyMember(familyMemberDto, familyId);
        List<FamilyMemberEntity> familyMembers = familyMemberRepository.findAllByFamilyid(familyId);
        FamilyMemberEntity actualFamilyMemberEntity = familyMembers.get(0);

        // then
        assertThat(familyMembers).hasSize(1);
        assertThat(actualFamilyMemberEntity).isEqualTo(expectedFamilyMemberEntity);
    }

    @Transactional
    @Test
    public void shouldThrowDuplicateEntityException_whenPassedADuplicate() {
        // given
        FamilyMemberDto familyMemberDto1 = testDataProvider.getFamilyMemberDto(23);
        FamilyMemberDto familyMemberDto2 = testDataProvider.getFamilyMemberDto(23);

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
        Long familyId = -123L;

        FamilyMemberDto expectedFamilyMemberDto1 = testDataProvider.getFamilyMemberDto(24);
        expectedFamilyMemberDto1.setSocialNumber(111111111L);
        FamilyMemberEntity expectedFamilyMemberEntity1 = testDataProvider.getFamilyMemberEntity(expectedFamilyMemberDto1, familyId);
        FamilyMemberDto expectedFamilyMemberDto2 = testDataProvider.getFamilyMemberDto(25);
        expectedFamilyMemberDto2.setSocialNumber(999111555L);
        FamilyMemberEntity expectedFamilyMemberEntity2 = testDataProvider.getFamilyMemberEntity(expectedFamilyMemberDto2, familyId);

        // when
        familyMemberService.createFamilyMember(expectedFamilyMemberDto1, familyId);
        familyMemberService.createFamilyMember(expectedFamilyMemberDto2, familyId);
        List<FamilyMemberEntity> familyMembers = familyMemberService.searchFamilyMembers(familyId);
        FamilyMemberEntity actualFamilyMemberEntity1 = familyMembers.get(0);
        FamilyMemberEntity actualFamilyMemberEntity2 = familyMembers.get(1);

        // then
        assertThat(familyMembers).hasSize(2);

        assertThat(actualFamilyMemberEntity1).isEqualTo(expectedFamilyMemberEntity1);
        assertThat(actualFamilyMemberEntity2).isEqualTo(expectedFamilyMemberEntity2);
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