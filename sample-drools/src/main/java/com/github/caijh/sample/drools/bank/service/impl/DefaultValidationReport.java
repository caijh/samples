package com.github.caijh.sample.drools.bank.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.github.caijh.sample.drools.bank.service.Message;
import com.github.caijh.sample.drools.bank.service.ValidationReport;

public class DefaultValidationReport implements ValidationReport {

    protected Map<Message.Type, Set<Message>> messagesMap = new EnumMap<>(Message.Type.class);

    @Override
    public Set<Message> getMessages() {
        Set<Message> messagesAll = new HashSet<>();
        for (Collection<Message> messages :
            messagesMap.values()) {
            messagesAll.addAll(messages);
        }
        return messagesAll;
    }

    @Override
    public Set<Message> getMessagesByType(Message.Type type) {
        if (type == null)
            return Collections.emptySet();

        Set<Message> messages = messagesMap.get(type);
        if (messages == null) {
            return Collections.emptySet();
        }
        return messages;
    }

    @Override
    public boolean contains(String messageKey) {
        for (Message message : getMessages()) {
            if (messageKey.equals(message.getMessageKey())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Message getMessage(Message.Type type, String messageKey) {
        Set<Message> messages = messagesMap.get(type);
        for (Message message : messages) {
            if (message.getMessageKey().equals(messageKey)) {
                return message;
            }
        }
        return null;
    }

    @Override
    public boolean addMessage(Message message) {
        if (message == null)
            return false;

        Set<Message> messages = messagesMap.computeIfAbsent(message.getType(), k -> new HashSet<>());
        return messages.add(message);
    }

}
