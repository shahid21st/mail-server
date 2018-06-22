package com.shahid.mail.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author Shahid Zaman
 * Date: 6/20/18
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "to",
        "cc",
        "bcc",
        "subject",
        "from",
        "body"
})
public class Payload {

    @JsonProperty("to")
    private List<Address> to = null;

    @JsonProperty("cc")
    private List<Address> cc = null;

    @JsonProperty("bcc")
    private List<Address> bcc = null;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("from")
    private Address from;

    @JsonProperty("body")
    private String body;

    @JsonProperty("to")
    public List<Address> getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(List<Address> to) {
        this.to = to;
    }

    @JsonProperty("cc")
    public List<Address> getCc() {
        return cc;
    }

    @JsonProperty("cc")
    public void setCc(List<Address> cc) {
        this.cc = cc;
    }

    @JsonProperty("bcc")
    public List<Address> getBcc() {
        return bcc;
    }

    @JsonProperty("bcc")
    public void setBcc(List<Address> bcc) {
        this.bcc = bcc;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("from")
    public Address getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(Address from) {
        this.from = from;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }
}
