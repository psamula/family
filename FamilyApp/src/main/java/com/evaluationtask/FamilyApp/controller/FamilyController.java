package com.evaluationtask.FamilyApp.controller;

import com.evaluationtask.FamilyApp.model.FamilyDto;
import com.evaluationtask.FamilyApp.model.FullFamilyDto;
import com.evaluationtask.FamilyApp.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("families")
public class FamilyController {
    private final FamilyService familyService;

    /* A client invokes a createFamily service via POST /families endpoint and stores
         the data about new family in a Json format of FamilyDto RequestBody */
    @PostMapping
    public Long createFamily(@RequestBody FamilyDto familyDto) {
        return this.familyService.createFamily(familyDto);
    }


    // A client invokes a getFamily service by providing a familyId in GET/families/{familyId} endpoint
    @GetMapping("/{familyId}")
    public FullFamilyDto getFamily(@PathVariable Long familyId) {
        return this.familyService.getFamily(familyId);
    }
}
