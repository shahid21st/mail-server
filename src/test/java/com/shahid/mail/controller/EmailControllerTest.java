package com.shahid.mail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shahid.mail.domain.Payload;
import com.shahid.mail.response.StatusResponse;
import com.shahid.mail.service.MailGunEmailService;
import com.shahid.mail.service.SendGridEmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Shahid Zaman
 * Date: 6/21/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmailControllerTest {

    private MockMvc mockMvc;

    @Value("classpath:payload.json")
    private Resource file;

    @Mock
    private
    SendGridEmailService sendGridEmailService;

    @Mock
    private
    MailGunEmailService mailGunEmailService;

    private EmailController emailController;

    private Payload payload;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        emailController = new EmailController(sendGridEmailService, mailGunEmailService);
        ObjectMapper mapper = new ObjectMapper();
         payload = mapper.readValue(new String(Files.readAllBytes(Paths.get(file.getURI()))), Payload.class);
    }

    @Test
    public void createArticle() throws Exception {
        when(sendGridEmailService.send(any())).thenReturn(new StatusResponse(true));

        this.mockMvc.perform(post("/email/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new String(Files.readAllBytes(Paths.get(file.getURI())))))
                .andExpect(status().is2xxSuccessful());

        assertTrue(emailController.send(payload).getSuccess());
        verifyZeroInteractions(mailGunEmailService);
    }
}
