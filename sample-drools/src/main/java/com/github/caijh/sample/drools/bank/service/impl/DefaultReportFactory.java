package com.github.caijh.sample.drools.bank.service.impl;

import java.util.Arrays;

import com.github.caijh.sample.drools.bank.service.Message;
import com.github.caijh.sample.drools.bank.service.ReportFactory;
import com.github.caijh.sample.drools.bank.service.ValidationReport;

public class DefaultReportFactory implements ReportFactory {

    @Override
    public ValidationReport createValidationReport() {
        return new DefaultValidationReport();
    }

    @Override
    public Message createMessage(Message.Type type, String messageKey, Object... context) {
        return new DefaultMessage(type, messageKey, Arrays.asList(context));
    }

}
