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
            String familyName = familyDto.getFamilyName();
            int nrOfInfants = familyDto.getNrOfInfants();
            int nrOfChildren = familyDto.getNrOfChildren();
            int nrOfAdults = familyDto.getNrOfAdults();
            List<FamilyMemberDto> familyMemberDtos = familyDto.getFamilyMemberDtos();

            familyValidator.validate(familyMemberDtos, nrOfInfants, nrOfChildren, nrOfAdults);

            var familyEntity = new FamilyEntity();
            var familyEntityId = familyRepository.save(familyEntity).getId();

            familyDto.getFamilyMemberDtos()
                    .forEach(member -> restComponentClient.postFamilyMember(member, familyEntityId));

            return this.saveFamilyEntity(familyEntity, familyName, nrOfInfants, nrOfChildren, nrOfAdults);
        }
        catch (NullPointerException | ValidationException ex) {
            throw new InvalidInputException(ex.getMessage(), ex.getCause());
        }
        catch (DuplicateEntityException ex) {
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

    public FullFamilyDto getFamily(Long familyId) {
        var optFamilyEntity = familyRepository.findById(familyId);
        if (optFamilyEntity.isEmpty()) {
            throw new EntityNotFoundException(String.format("Family of id %d has not been found", familyId));
        }
        var familyEntity = optFamilyEntity.get();
        List<FamilyMember> familyMembers = restComponentClient.getFamilyMembers(familyId);

        return new FullFamilyDto(familyEntity, familyMembers);
    }
}
