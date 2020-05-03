package com.github.caijh.sample.drools.bank.service;

public interface ReportFactory {

    ValidationReport createValidationReport();

    Message createMessage(Message.Type type, String messageKey, Object... context);

}
