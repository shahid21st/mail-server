package com.shahid.mail.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "personalizations",
        "from",
        "reply_to",
        "subject",
        "content"
})
public class SendGridEmail {

    @JsonProperty("personalizations")
    private List<Personalization> personalizations = null;
    @JsonProperty("from")
    private Address from;
    @JsonProperty("reply_to")
    private Address replyTo;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("content")
    private List<Content> content = null;

    @JsonProperty("personalizations")
    public List<Personalization> getPersonalizations() {
        return personalizations;
    }

    @JsonProperty("personalizations")
    public void setPersonalizations(List<Personalization> personalizations) {
        this.personalizations = personalizations;
    }



    @JsonProperty("from")
    public Address getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(Address from) {
        this.from = from;
    }

    @JsonProperty("reply_to")
    public Address getReplyTo() {
        return replyTo;
    }

    @JsonProperty("reply_to")
    public void setReplyTo(Address replyTo) {
        this.replyTo = replyTo;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("content")
    public List<Content> getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(List<Content> content) {
        this.content = content;
    }
}