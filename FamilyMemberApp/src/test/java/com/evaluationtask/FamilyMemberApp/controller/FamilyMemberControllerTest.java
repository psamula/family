package com.evaluationtask.FamilyMemberApp.controller;

import com.evaluationtask.FamilyMemberApp.model.FamilyMemberTestModel;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FamilyMemberControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    // A test method with the FamilyMemberApp global coverage
    @Transactional
    @Test
    public void shouldPostAndGetFamilyMember() throws Exception {
        // given
        FamilyMemberDto familyMemberDto = new FamilyMemberDto();
        familyMemberDto.setFirstName("Ralph");
        familyMemberDto.setLastName("Fiennes");
        familyMemberDto.setSocialNumber(123456789L);
        Long familyId = 1L;

        // when
        mvc.perform(post(String.format("/families/%d/member", familyId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format(
                                "{\"firstName\":\"%s\",\"lastName\":\"%s\",\"socialNumber\":\"%d\"}",
                                familyMemberDto.getFirstName(), familyMemberDto.getLastName(),
                                familyMemberDto.getSocialNumber()))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        MvcResult result = mvc.perform(get(String.format("/families/%d/members", familyId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String actualJson = result.getResponse().getContentAsString();
        List<FamilyMemberTestModel> actualFamilyMembers =
                mapper.readValue(actualJson, new TypeReference<>() {});
        FamilyMemberTestModel actualFamilyMember = actualFamilyMembers.get(0);

        // then
        assertThat(actualFamilyMember.getFirstname()).isEqualTo(familyMemberDto.getFirstName());
        assertThat(actualFamilyMember.getLastname()).isEqualTo(familyMemberDto.getLastName());
        assertThat(actualFamilyMember.getSocialnumber()).isEqualTo(familyMemberDto.getSocialNumber());
        assertThat(actualFamilyMember.getFamilyid()).isEqualTo(familyId);
    }
}