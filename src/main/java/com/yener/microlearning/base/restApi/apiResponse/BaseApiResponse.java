package com.yener.microlearning.base.restApi.apiResponse;


import com.yener.microlearning.base.util.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseApiResponse {
    protected boolean hasError = false;
    protected List<Message> messageList = new ArrayList();

    public BaseApiResponse() {
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
