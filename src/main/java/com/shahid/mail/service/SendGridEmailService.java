package com.shahid.mail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shahid.mail.Utils;
import com.shahid.mail.domain.Payload;
import com.shahid.mail.domain.SendGridEmail;
import com.shahid.mail.response.SendGridResponse;
import com.shahid.mail.response.StatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Service
public class SendGridEmailService implements EmailService {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${sendgrid.api.key}")
	private String sendgridApiKey;

	@Value("${sendgrid.api.url}")
	private String sendgridUrl;

	@Override
	public StatusResponse send(Payload payload) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + sendgridApiKey);
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<SendGridEmail> entity = new HttpEntity<>(Utils.toSendGridEmail(payload), headers);

			restTemplate.exchange(sendgridUrl, HttpMethod.POST, entity, Object.class);
		} catch (HttpStatusCodeException ex) {
			return Utils.toStatusResponseFromSendGridResponse(objectMapper.readValue(ex.getResponseBodyAsString(), SendGridResponse.class) );
		} catch (Exception ex) {
			return new StatusResponse(false, null, "An error has occurred!" + Arrays.toString(ex.getStackTrace()));
		}

		return new StatusResponse(true);
	}
}
