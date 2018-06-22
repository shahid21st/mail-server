package com.shahid.mail.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "to",
        "cc",
        "bcc"
})
public class Personalization {

    @JsonProperty("to")
    private List<Address> to = null;
    @JsonProperty("cc")
    private List<Address> cc = null;
    @JsonProperty("bcc")
    private List<Address> bcc = null;

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
}

