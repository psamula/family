package com.evaluationtask.FamilyMemberApp.controller;

import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyMemberApp.service.FamilyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
//@RequestMapping("family")
public class FamilyMemberController {
    private final FamilyMemberService familyMemberService;
    @PostMapping("/create/{familyId}")
    public void createFamilyMember(@RequestBody FamilyMemberDto familyMemberDto,
                                   @PathVariable Long familyId) {

        this.familyMemberService.createFamilyMember(familyMemberDto, familyId);
    }
}