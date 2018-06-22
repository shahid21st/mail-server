package com.shahid.mail;

import com.shahid.mail.domain.Content;
import com.shahid.mail.domain.Payload;
import com.shahid.mail.domain.Personalization;
import com.shahid.mail.domain.SendGridEmail;
import com.shahid.mail.response.Error;
import com.shahid.mail.response.SendGridResponse;
import com.shahid.mail.response.StatusResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

/**
 * @author Shahid Zaman
 * Date: 6/20/18
 */
public class Utils {

    public static SendGridEmail toSendGridEmail(Payload payload) {

        Personalization personalization = new Personalization();
        personalization.setTo(payload.getTo());
        personalization.setCc(payload.getCc());
        personalization.setBcc(payload.getBcc());

        SendGridEmail sendGridEmail = new SendGridEmail();
        sendGridEmail.setPersonalizations(Collections.singletonList(personalization));
        sendGridEmail.setFrom(payload.getFrom());
        sendGridEmail.setReplyTo(payload.getFrom());
        sendGridEmail.setSubject(payload.getSubject());

        Content content = new Content();
        content.setType(MediaType.TEXT_PLAIN_VALUE);
        content.setValue(payload.getBody());

        sendGridEmail.setContent(Collections.singletonList(content));

        return sendGridEmail;
    }

    public static StatusResponse toStatusResponseFromSendGridResponse(SendGridResponse sendGridResponse) {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setSuccess(false);
        if (sendGridResponse != null) {
            for(Error error: sendGridResponse.getErrors()) {
                statusResponse.getMessage().add(error.getField().toString() + ": " + error.getMessage());
            }
        }

        return statusResponse;
    }

    public static StatusResponse toStatusResponseFromMailGun(ResponseEntity responseEntity) {

        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return new StatusResponse(true);
        }
        return new StatusResponse(false);
    }
}
