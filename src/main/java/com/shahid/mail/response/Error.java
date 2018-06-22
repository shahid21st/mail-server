package com.shahid.mail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "field",
        "help"
})
public class Error {

    @JsonProperty("message")
    private String message;
    @JsonProperty("field")
    private Object field;
    @JsonProperty("help")
    private Object help;

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("field")
    public Object getField() {
        return field;
    }

    @JsonProperty("field")
    public void setField(Object field) {
        this.field = field;
    }

    @JsonProperty("help")
    public Object getHelp() {
        return help;
    }

    @JsonProperty("help")
    public void setHelp(Object help) {
        this.help = help;
    }
}
