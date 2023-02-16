package com.evaluationtask.FamilyApp.service;

import com.evaluationtask.FamilyApp.exceptions.DuplicateEntityException;
import com.evaluationtask.FamilyApp.exceptions.InvalidInputException;
import com.evaluationtask.FamilyApp.exceptions.ValidationException;
import com.evaluationtask.FamilyApp.model.*;
import com.evaluationtask.FamilyApp.repository.FamilyRepository;
import com.evaluationtask.FamilyApp.service.validator.IValidator;
import com.evaluationtask.FamilyApp.webclient.RestComponentClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FamilyService {
    private final RestComponentClient restComponentClient;
    private final FamilyRepository familyRepository;
    private final IValidator familyValidator;

    @Transactional
    public Long createFamily(FamilyDto familyDto) {
        try {

            // Extract the family data to separate variables and validate them
            String familyName = familyDto.getFamilyName();
            int nrOfInfants = familyDto.getNrOfInfants();
            int nrOfChildren = familyDto.getNrOfChildren();
            int nrOfAdults = familyDto.getNrOfAdults();
            List<FamilyMemberDto> familyMemberDtos = familyDto.getFamilyMemberDtos();

            familyValidator.validate(familyMemberDtos, nrOfInfants, nrOfChildren, nrOfAdults);

            // Assign the ID to the new family being created
            var familyEntity = new FamilyEntity();
            var familyEntityId = familyRepository.save(familyEntity).getId();

            // Pass (POST) the data of each family member to the FamilyMemberApp component
            familyDto.getFamilyMemberDtos()
                    .forEach(member -> restComponentClient.postFamilyMember(member, familyEntityId));

            /* After successful, exceptionless persisting of all the members of the family, eventually insert the
                family information in database, persist it and return the family ID to the client */
            return this.saveFamilyEntity(familyEntity, familyName, nrOfInfants, nrOfChildren, nrOfAdults);
        }
        // When the user's input is either null or invalid
        catch (NullPointerException | ValidationException ex) {
            throw new InvalidInputException(ex.getMessage(), ex.getCause());
        }
        // When user tries to insert the same family members (assuming no person can belong to more than 1 family)
        catch (DuplicateEntityException ex) {
            /* Rethrow it in service class for the better exception organisation and
                let it be caught by GlobalExceptionHandler */
            throw new DuplicateEntityException(ex.getMessage());
        }
    }

    private Long saveFamilyEntity(FamilyEntity familyEntity, String familyName, int nrOfInfants, int nrOfChildren, int nrOfAdults) {
        familyEntity.setName(familyName);
        familyEntity.setNrofinfants(nrOfInfants);
        familyEntity.setNrofchildren(nrOfChildren);
        familyEntity.setNrofadults(nrOfAdults);

        return familyRepository.save(familyEntity).getId();
    }

    /* A method to retrieve full information about the family and its members and
        wrap it in a big Dto container - FullFamilyDto */
    public FullFamilyDto getFamily(Long familyId) {
        // Retrieve the general info about family from the FamilyDB
        var optFamilyEntity = familyRepository.findById(familyId);
        if (optFamilyEntity.isEmpty()) {
            throw new EntityNotFoundException(String.format("Family of id %d has not been found", familyId));
        }
        var familyEntity = optFamilyEntity.get();

        /* GET all the members of family of the given familyId from the FamilyMemberApp component and
            gather them inside the list */
        List<FamilyMember> familyMembers = restComponentClient.getFamilyMembers(familyId);

        // Wrap it all together and return as a big Dto container
        return new FullFamilyDto(familyEntity, familyMembers);
    }
}
