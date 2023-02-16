package com.evaluationtask.FamilyApp.controller;

import com.evaluationtask.FamilyApp.model.FamilyDto;
import com.evaluationtask.FamilyApp.model.FamilyMember;
import com.evaluationtask.FamilyApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyApp.model.FullFamilyDto;
import com.evaluationtask.FamilyApp.utils.TestDataProvider;
import com.evaluationtask.FamilyApp.webclient.RestComponentClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FamilyControllerTest {

    @Autowired
    private RestComponentClient restComponentClient;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private TestDataProvider testDataProvider;
    private List<Long> familyIdsToDelete = new ArrayList<>();

    @Transactional
    @Test
    public void shouldCreateAndGetFamily() throws Exception {
        // given
        FamilyDto familyDto = testDataProvider.getFamilyDto(0,0,1);
        FamilyMemberDto familyMemberDto = testDataProvider.getFamilyMemberDto(familyDto.getFamilyName(), 23);
        FamilyMember expectedFamilyMember = testDataProvider.getFamilyMember(familyMemberDto);

        familyDto.setFamilyMemberDtos(Collections.singletonList(familyMemberDto));
        FullFamilyDto expectedFullFamilyDto = new FullFamilyDto(
                testDataProvider.getFamilyEntity(familyDto.getFamilyName(), familyDto.getNrOfInfants(),
                        familyDto.getNrOfChildren(), familyDto.getNrOfAdults()),
                Collections.singletonList(expectedFamilyMember));
        // when
        MvcResult createResult = mvc.perform(post("/families")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(familyDto))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
        Long familyId = Long.valueOf(createResult.getResponse().getContentAsString());
        this.familyIdsToDelete.add(familyId);


        MvcResult getResult = mvc.perform(get(String.format("/families/%d", familyId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String actualJson = getResult.getResponse().getContentAsString();
        FullFamilyDto actualFamily = mapper.readValue(actualJson, FullFamilyDto.class);

        // then
        assertThat(actualFamily).isEqualTo(expectedFullFamilyDto);

    }
    /* "Transactional" annotation involves cleaning up solely inside familyApp component, thus leaving the
        FamilyMemberApp component polluted with test data. As a solution, I use DELETE endpoint to pass ids of all
         test families whose members need to be cleaned up on the other component */
    @AfterEach
    public void cleanUp() {
        if (!familyIdsToDelete.isEmpty()) {
            this.familyIdsToDelete.forEach(i -> restComponentClient.deleteAllByFamilyId(i));
            this.familyIdsToDelete.clear();
        }
    }
}
