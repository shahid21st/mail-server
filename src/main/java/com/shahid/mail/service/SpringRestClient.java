package com.shahid.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author Shahid Zaman
 * Date: 6/19/18
 */

@Service("springRestClient")
public class SpringRestClient implements RESTClient {

    private RestTemplate restTemplate;

    @Autowired
    public SpringRestClient(RestTemplateBuilder builder) {
        this.restTemplate =  builder.build();
    }

    @Override
    public ResponseEntity post(String resourceUrl, MultiValueMap<String, String> request, String username, String password) {
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(request,
                createHeadersWithUsernamePassword(username, password));
        ResponseEntity<String> response = this.restTemplate.exchange(resourceUrl, HttpMethod.POST, requestEntity, String.class);
        return response;
    }

    private HttpHeaders createHeadersWithUsernamePassword(String username, String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED.toString());
                set(HttpHeaders.AUTHORIZATION, authHeader);
            }
        };
    }
}
