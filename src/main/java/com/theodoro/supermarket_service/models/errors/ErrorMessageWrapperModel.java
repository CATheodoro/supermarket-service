package com.theodoro.supermarket_service.models.errors;

import java.util.LinkedList;
import java.util.List;

public class ErrorMessageWrapperModel {

    private List<ErrorMessageResponse> messages;

    public ErrorMessageWrapperModel() {
        messages = new LinkedList<>();
    }

    public List<ErrorMessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(final List<ErrorMessageResponse> messages) {
        this.messages = messages;
    }

    public void addErrorMessage(final Integer errorCode, final String errorMessage) {
        messages.add(new ErrorMessageResponse(errorCode, errorMessage));
    }
}
