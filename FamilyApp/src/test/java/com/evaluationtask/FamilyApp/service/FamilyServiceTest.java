package com.evaluationtask.FamilyApp.service;

import com.evaluationtask.FamilyApp.exceptions.InvalidInputException;
import com.evaluationtask.FamilyApp.model.*;
import com.evaluationtask.FamilyApp.repository.FamilyRepository;
import com.evaluationtask.FamilyApp.utils.TestDataProvider;
import com.evaluationtask.FamilyApp.webclient.RestComponentClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FamilyServiceTest {
    @Autowired
    private FamilyService familyService;
    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private RestComponentClient restComponentClient;
    @Autowired
    private TestDataProvider testDataProvider;
    private List<Long> familyIdsToDelete = new ArrayList<>();

    @Transactional
    @Test
    public void shouldSuccessfullyCreateFamily() {
        //given
        FamilyDto expectedFamily = testDataProvider.getFamilyDto();

        //when
        Long actualFamilyId = familyService.createFamily(expectedFamily);
        if (!familyRepository.existsById(actualFamilyId)) {
            fail("Failure during family creation");
        }
        var actualFamilyEntity = familyRepository.findById(actualFamilyId).get();
        FamilyDto actualFamily = FamilyDto.ofFamilyEntity(actualFamilyEntity);

        //then
        assertThat(actualFamily).isEqualTo(expectedFamily);

        familyIdsToDelete.add(actualFamilyId);
    }
    @Transactional
    @Test
    public void shouldThrowInvalidInputException_whenInvalidFamilyTypeNumbers() {
        //given
        FamilyDto expectedFamily = testDataProvider.getFamilyDto(10, 11, 12);
        expectedFamily.setFamilyMemberDtos(new ArrayList<>());

        //when
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () ->
                familyIdsToDelete.add(familyService.createFamily(expectedFamily)));
        //then
        assertThat(thrown.getMessage()).isEqualTo("Declared number of member types in family is incorrect");


    }
    @Transactional
    @Test
    public void shouldThrowInvalidInputException_whenFamilyMemberAgeOver_199() {
        //given
        FamilyDto expectedFamily = testDataProvider.getFamilyDto();
        List<FamilyMemberDto> testMembers =  Collections.singletonList(testDataProvider.getFamilyMemberDto(200));
        expectedFamily.setFamilyMemberDtos(testMembers);

        //when
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () ->
                familyIdsToDelete.add(familyService.createFamily(expectedFamily)));

        //then
        assertThat(thrown.getMessage()).isEqualTo(
                "An age of one of the given family members is incorrect Accepted values: 0-199");
    }
    @Transactional
    @Test
    public void shouldThrowInvalidInputException_whenNegativeFamilyMemberAge() {
        //given
        FamilyDto expectedFamily = testDataProvider.getFamilyDto();
        List<FamilyMemberDto> testMembers =  Collections.singletonList(testDataProvider.getFamilyMemberDto(-10));
        expectedFamily.setFamilyMemberDtos(testMembers);

        //when
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () ->
                        familyIdsToDelete.add(familyService.createFamily(expectedFamily)));

        //then
        assertThat(thrown.getMessage()).isEqualTo("Age cannot be a negative number");

    }

    @Transactional
    @Test
    public void shouldSuccessfullyGetFamily() {
        //given

        FamilyEntity expectedFamilyEntity = testDataProvider.getFamilyEntity(0,0,1);
        FamilyDto expectedFamilyDto = FamilyDto.ofFamilyEntity(expectedFamilyEntity);


        FamilyMemberDto expectedFamilyMemberDto = testDataProvider
                .getFamilyMemberDto(expectedFamilyEntity.getName(), 23);
        FamilyMember expectedFamilyMember = testDataProvider.getFamilyMember(expectedFamilyMemberDto);
        FullFamilyDto expectedFullFamilyDto = new FullFamilyDto(expectedFamilyEntity,
                Collections.singletonList(expectedFamilyMember));
        List<FamilyMemberDto> expectedMemberDtos = Collections.singletonList(expectedFamilyMemberDto);
        expectedFamilyDto.setFamilyMemberDtos(expectedMemberDtos);


        //when
        Long actualFamilyId = familyService.createFamily(expectedFamilyDto);

        FullFamilyDto actualFullFamilyDto = familyService.getFamily(actualFamilyId);

        //then
        assertThat(actualFullFamilyDto).isEqualTo(expectedFullFamilyDto);

        familyIdsToDelete.add(actualFamilyId);
    }
    /* "Transactional" annotation involves cleaning up solely inside familyApp component, thus leaving the
        FamilyMemberApp component polluted with test data. As a solution, I use DELETE endpoint to pass ids of all
         test families whose members need to be cleaned up on the other component */
    @AfterEach
    public void cleanUp() {
        if (!familyIdsToDelete.isEmpty()) {
            this.familyIdsToDelete.forEach(i -> restComponentClient.deleteAllByFamilyId(i));
            this.familyIdsToDelete.clear();
        }
    }

}
