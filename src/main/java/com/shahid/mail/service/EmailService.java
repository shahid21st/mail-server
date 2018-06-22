package com.shahid.mail.service;

import com.shahid.mail.domain.Payload;
import com.shahid.mail.response.StatusResponse;

import java.io.IOException;

/**
 * @author Shahid Zaman
 * Date: 6/19/18
 */

public interface EmailService {
    StatusResponse send(Payload payload) throws IOException;
}
