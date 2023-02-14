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
    @PostMapping
    public Long createFamily(@RequestBody FamilyDto familyDto) {
        return this.familyService.createFamily(familyDto);
    }

    @GetMapping("/{familyId}")
    public FullFamilyDto getFamily(@PathVariable Long familyId) {
        return this.familyService.getFamily(familyId);
    }
}
