package com.shahid.mail.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shahid.mail.domain.Payload;
import com.shahid.mail.response.StatusResponse;
import com.shahid.mail.service.MailGunEmailService;
import com.shahid.mail.service.SendGridEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author Shahid Zaman
 * Date: 6/20/18
 */

@Controller
@RequestMapping("/email")
public class EmailController {

	private final SendGridEmailService sendGridEmailService;
	private final MailGunEmailService mailGunEmailService;

	private static Logger logger = LoggerFactory.getLogger(EmailController.class);


    @Autowired
	public EmailController(SendGridEmailService sendGridEmailService, MailGunEmailService mailGunEmailService) {
		this.sendGridEmailService = sendGridEmailService;
		this.mailGunEmailService = mailGunEmailService;
	}

	@HystrixCommand(fallbackMethod = "sendViaMailgun", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
							value="2000")})
	@RequestMapping(value ="/send", method=RequestMethod.POST)
	public @ResponseBody
	StatusResponse send(@RequestBody Payload payload) throws IOException {
        if(payload.getTo() == null || payload.getTo().isEmpty()) {
            return new StatusResponse(false, HttpStatus.BAD_REQUEST, "you must provide a recipient");
        }

        logger.debug("sendgrid invocation");
        StatusResponse statusResponse = sendGridEmailService.send(payload);
		if (!statusResponse.getSuccess()) {
		    throw new RuntimeException();
        }
        return statusResponse;
	}

	public StatusResponse sendViaMailgun(Payload payload) {
        logger.debug("mailgun invocation");
        return mailGunEmailService.send(payload);
	}
}