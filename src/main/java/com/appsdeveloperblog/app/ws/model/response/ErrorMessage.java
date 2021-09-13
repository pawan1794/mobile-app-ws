package com.appsdeveloperblog.app.ws.model.response;

import java.util.Date;

public class ErrorMessage {

    private Date timestamps;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(Date timestamps, String message) {
        this.timestamps = timestamps;
        this.message = message;
    }

    public Date getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Date timestamps) {
        this.timestamps = timestamps;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
