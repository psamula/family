package com.evaluationtask.FamilyApp.service;

import com.evaluationtask.FamilyApp.model.FamilyDto;
import com.evaluationtask.FamilyApp.webclient.RestComponentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class FamilyService {
    private final RestComponentClient restComponentClient;
    public void createFamily(String familyName, int nrOfInfants, int nrOfChildren, int nrOfAdults, FamilyDto familyDto) {

    }

}
