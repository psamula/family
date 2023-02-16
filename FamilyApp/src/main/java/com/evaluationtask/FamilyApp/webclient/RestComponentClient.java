package com.evaluationtask.FamilyApp.webclient;

import com.evaluationtask.FamilyApp.exceptions.FamilyMemberAppException;
import com.evaluationtask.FamilyApp.exceptions.IntercomponentException;
import com.evaluationtask.FamilyApp.model.FamilyMember;
import com.evaluationtask.FamilyApp.model.FamilyMemberDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

// A class which facilitates its instances to provide communication between FamilyApp and FamilyMemberApp components
@Service
public class RestComponentClient {
    /* Using RestTemplate, which provides a RESTful, higher level abstraction of HttpClient to POST and GET
        resources to/from the FamilyMemberApp component */
    private final RestTemplate restTemplate = new RestTemplate();
    private final String componentPort = "8081";
    private final String componentHost = "familymember-app";

    /* Pass the family member data and the corresponding family id (Json and PathVariable) via REST POST endpoint
        on the side of FamilyMemberApp component */
    public void postFamilyMember(FamilyMemberDto member, Long familyEntityId) {
        try {
            HttpEntity<FamilyMemberDto> request = new HttpEntity<>(member, createHeaders());
            URI uri = URI.create(this.getComponentBaseUrl() + "/families/" + familyEntityId + "/member");
            restTemplate.postForObject(uri, request, Void.class);
        }
        // When exceptions regard a problem with communication between components
        catch (HttpClientErrorException | NullPointerException ex) {
            throw new IntercomponentException(ex.getMessage(), ex.getCause());
        }
        // When exception regards an internal problem on the side of FamilyMemberApp component
        catch (HttpServerErrorException ex) {
            throw new FamilyMemberAppException(ex.getMessage(), ex.getCause());
        }
    }

    // GET the family members of the corresponding family id via REST GET endpoint of the FamilyMemberApp component
    public List<FamilyMember> getFamilyMembers(Long familyId) {
        try {
            URI uri = URI.create(this.getComponentBaseUrl() + "/families/" + familyId + "/members");
            return Arrays.stream(restTemplate.getForObject(uri, FamilyMember[].class)).toList();
        }
        catch (HttpClientErrorException | NullPointerException ex) {
            throw new IntercomponentException(ex.getMessage(), ex.getCause());
        }
        catch (HttpServerErrorException ex) {
            throw new FamilyMemberAppException(ex.getMessage(), ex.getCause());
        }
    }

    public String getComponentBaseUrl() {
        return "http://" + componentHost + ":" + componentPort;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
