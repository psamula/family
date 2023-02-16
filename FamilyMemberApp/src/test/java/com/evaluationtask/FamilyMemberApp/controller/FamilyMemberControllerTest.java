package com.evaluationtask.FamilyMemberApp.controller;

import com.evaluationtask.FamilyMemberApp.model.FamilyMemberEntity;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberTestModel;
import com.evaluationtask.FamilyMemberApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyMemberApp.utils.TestDataProvider;
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

import java.util.Arrays;
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
    @Autowired
    private TestDataProvider testDataProvider;

    // A test method with the FamilyMemberApp global coverage
    @Transactional
    @Test
    public void shouldPostAndGetFamilyMember() throws Exception {
        // given
        FamilyMemberDto familyMemberDto = testDataProvider.getFamilyMemberDto(2);
        Long familyId = -19L;
        FamilyMemberEntity expectedFamilyMemberEntity = testDataProvider.getFamilyMemberEntity(familyMemberDto, familyId);

        // when
        mvc.perform(post(String.format("/families/%d/member", familyId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(familyMemberDto))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        MvcResult result = mvc.perform(get(String.format("/families/%d/members", familyId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String actualJson = result.getResponse().getContentAsString();
        List<FamilyMemberEntity> actualFamilyEntities = Arrays.stream(mapper.readValue(actualJson, FamilyMemberEntity[].class)).toList();
        FamilyMemberEntity actualFamilyMemberEntity = actualFamilyEntities.get(0);


        // then
        assertThat(expectedFamilyMemberEntity).isEqualTo(actualFamilyMemberEntity);
    }
}