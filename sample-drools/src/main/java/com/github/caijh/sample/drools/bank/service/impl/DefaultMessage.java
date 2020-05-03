package com.github.caijh.sample.drools.bank.service.impl;

import java.io.Serializable;
import java.util.List;

import com.github.caijh.sample.drools.bank.service.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class DefaultMessage implements Message, Serializable {

    private final Message.Type type;
    private final String messageKey;
    private final List<Object> context;

    public DefaultMessage(Message.Type type, String messageKey, List<Object> context) {
        if (type == null || messageKey == null) {
            throw new IllegalArgumentException("Type and messageKey cannot be null");
        }
        this.type = type;
        this.messageKey = messageKey;
        this.context = context;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }

    @Override
    public List<Object> getContextOrdered() {
        return context;
    }

}
