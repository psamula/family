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

@Service
public class RestComponentClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String componentPort = "8081";
    private final String componentHost = "familymember-app";
    public void postFamilyMember(FamilyMemberDto member, Long familyEntityId) {

        try {
            HttpEntity<FamilyMemberDto> request = new HttpEntity<>(member, createHeaders());
            URI uri = URI.create(this.getComponentBaseUrl() + "/families/" + familyEntityId + "/member");
            restTemplate.postForObject(uri, request, Void.class);
        }
        catch (HttpClientErrorException | NullPointerException ex) {
            throw new IntercomponentException(ex.getMessage(), ex.getCause());
        }
        catch (HttpServerErrorException ex) {
            throw new FamilyMemberAppException(ex.getMessage(), ex.getCause());
        }
    }
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
