package com.shahid.mail.service;

import com.shahid.mail.Utils;
import com.shahid.mail.domain.Address;
import com.shahid.mail.domain.Payload;
import com.shahid.mail.response.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author Shahid Zaman
 * Date: 6/19/18
 */

@Service
public class MailGunEmailService implements EmailService {

    private RESTClient springRestClient;
    @Value("${mailgun.api.password}") private String password;
    @Value("${mailgun.api.messages.url}") private String messagesUrl;
    @Value("${mailgun.api.domain}") private String domain;
    @Value("${mailgun.api.username}") private String username;

    @Autowired
    public MailGunEmailService(RESTClient springRestClient) {
        this.springRestClient = springRestClient;
    }

    @Override
    public StatusResponse send(Payload payload) {
        MultiValueMap<String, String> map = getPostRequestObject(payload);
        ResponseEntity responseEntity = sendEmail(map);

        return Utils.toStatusResponseFromMailGun(responseEntity);
    }

    private ResponseEntity sendEmail(MultiValueMap<String, String> map) {
        return this.springRestClient.post(this.messagesUrl, map, this.username, this.password);
    }

    private MultiValueMap<String, String> getPostRequestObject(Payload payload) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

//        map.add("from", payload.getFrom().getNameAndEmail());
        map.add("from", payload.getFrom().getNameWithEmail("mailgun@" + domain));
        map.add("to", getCommaSeparatedList(payload.getTo()));
        if (payload.getCc() != null) {
            map.add("cc", getCommaSeparatedList(payload.getCc()));
        }
        if (payload.getBcc() != null) {
            map.add("bcc", getCommaSeparatedList(payload.getBcc()));
        }
        map.add("subject", payload.getSubject());
        map.add("text", payload.getBody());
        return map;
    }

    private String getCommaSeparatedList(List<Address> addresses) {
        int size = addresses.size();
        if(size == 1) {
            return addresses.get(0).getNameAndEmail();
        }

        StringBuilder result = new StringBuilder();
        for(int j = 0; j < size; j++) {
            result.append(addresses.get(j).getNameAndEmail());
            if(j != size-1) {
                result.append(",");
            }
        }

        return result.toString();
    }

}
