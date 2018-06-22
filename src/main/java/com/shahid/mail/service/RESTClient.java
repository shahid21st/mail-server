package com.shahid.mail.service;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * @author Shahid Zaman
 * Date: 6/19/18
 */
public interface RESTClient {
    ResponseEntity post(String resourceUrl, MultiValueMap<String, String> request, String username, String password);
}
