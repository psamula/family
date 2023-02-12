package com.evaluationtask.FamilyApp.webclient;

import com.evaluationtask.FamilyApp.model.FamilyMemberDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestComponentClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String componentPort = "8081";
    private final String componentHost = "localhost";
    public void postFamilyMember(FamilyMemberDto member, Long familyEntityId) {
        String url = getComponentBaseUrl() + "/create";

        HttpEntity<FamilyMemberDto> request = new HttpEntity<>(member, createHeaders());
        URI uri = UriComponentsBuilder.fromUriString(url)
                .path("/{id}").buildAndExpand(familyEntityId).toUri();
        restTemplate.postForObject(uri, request, Void.class);
    }
    public String getComponentBaseUrl() {
        return componentHost + ":" + componentPort;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
