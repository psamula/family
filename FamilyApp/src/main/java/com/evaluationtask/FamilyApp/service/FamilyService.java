package com.evaluationtask.FamilyApp.service;

import com.evaluationtask.FamilyApp.exceptions.ValidationException;
import com.evaluationtask.FamilyApp.model.FamilyDto;
import com.evaluationtask.FamilyApp.model.FamilyEntity;
import com.evaluationtask.FamilyApp.repository.FamilyRepository;
import com.evaluationtask.FamilyApp.service.validators.IValidator;
import com.evaluationtask.FamilyApp.webclient.RestComponentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class FamilyService {
    private final RestComponentClient restComponentClient;
    private final FamilyRepository familyRepository;
    private final IValidator familyValidator;
    public void createFamily(String familyName, int nrOfInfants, int nrOfChildren, int nrOfAdults, FamilyDto familyDto) {
        try {
            familyValidator.validate(familyDto, nrOfInfants, nrOfChildren, nrOfAdults);
            var familyEntity = createFamilyEntity(familyName, nrOfInfants, nrOfChildren, nrOfAdults);
            var familyEntityId = familyRepository.save(familyEntity).getId();
            familyEntity.setId(familyEntityId);
            familyDto.getFamilyMemberDtos()
                    .forEach(member -> restComponentClient.postFamilyMember(member, familyEntityId));

        }
        catch (ValidationException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    private FamilyEntity createFamilyEntity(String familyName,
                                            int nrOfInfants, int nrOfChildren, int nrOfAdults) {
        return FamilyEntity.builder()
                .name(familyName)
                .nrOfInfants(nrOfInfants)
                .nrOfChildren(nrOfChildren)
                .nrOfAdults(nrOfAdults)
                .build();
    }

}
