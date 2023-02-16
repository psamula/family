package com.evaluationtask.FamilyMemberApp.controller;

import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import com.evaluationtask.FamilyMemberApp.service.FamilyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("families")
public class FamilyMemberController {
    private final FamilyMemberService familyMemberService;

    /* An endpoint used to receive and process the POST request from FamilyApp component by saving
        each member of family of given data and id to the FamilyMemberDB */
    @PostMapping("/{familyId}/member")
    public void createFamilyMember(@RequestBody FamilyMemberDto familyMemberDto,
                                   @PathVariable Long familyId) {

        this.familyMemberService.createFamilyMember(familyMemberDto, familyId);
    }
    /* An endpoint used to receive and process the GET request from FamilyApp component by returning persisted members
        of the family of given id */
    @GetMapping("/{familyId}/members")
    public List<FamilyMemberEntity> searchFamilyMembers(@PathVariable Long familyId) {

        return this.familyMemberService.searchFamilyMembers(familyId);
    }
}