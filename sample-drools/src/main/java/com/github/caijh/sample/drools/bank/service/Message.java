package com.github.caijh.sample.drools.bank.service;

import java.util.List;

public interface Message {

    /**
     * @return type of this message
     */
    Type getType();

    /**
     * @return key of this message
     */
    String getMessageKey();

    /**
     * objects in the context must be ordered from the
     * least
     * specific to most specific
     *
     * @return list of objects in this message's
     * context
     */
    List<Object> getContextOrdered();

    enum Type {
        ERROR, WARNING
    }

}
