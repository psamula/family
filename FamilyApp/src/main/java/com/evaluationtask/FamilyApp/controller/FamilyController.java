package com.evaluationtask.FamilyApp.controller;

import com.evaluationtask.FamilyApp.model.FamilyDto;
import com.evaluationtask.FamilyApp.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("family")
public class FamilyController {
    private final FamilyService familyService;
    @PostMapping("/createFamily")
    public void createFamily(@RequestParam String familyName,
                              @RequestParam(required = false) int nrOfInfants,
                              @RequestParam(required = false) int nrOfChildren,
                              @RequestParam(required = false) int nrOfAdults,
                              @RequestBody FamilyDto familyDto) {

        this.familyService.createFamily(familyName, nrOfInfants, nrOfChildren, nrOfAdults, familyDto);
    }
}
