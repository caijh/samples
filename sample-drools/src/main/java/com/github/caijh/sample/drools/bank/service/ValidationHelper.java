package com.github.caijh.sample.drools.bank.service;

import java.util.Date;

import org.joda.time.DateMidnight;
import org.joda.time.Years;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.rule.RuleContext;

public class ValidationHelper {

    private ValidationHelper() {
    }

    /**
     * adds an error message to the global validation
     * report
     *
     * @param ruleContext RuleContext that is accessible
     *                    from
     *                    rule condition
     * @param context     for the message
     */
    public static void error(RuleContext ruleContext, Object... context) {
        KieRuntime kieRuntime = ruleContext.getKieRuntime();
        ValidationReport validationReport = (ValidationReport) kieRuntime.getGlobal("validationReport");
        ReportFactory reportFactory = (ReportFactory) kieRuntime.getGlobal("reportFactory");
        validationReport.addMessage(reportFactory.createMessage(
            Message.Type.ERROR,
            ruleContext.getRule().getName(),
            context));
    }

    public static void warning(RuleContext ruleContext, Object... context) {
        KieRuntime kieRuntime = ruleContext.getKieRuntime();
        ValidationReport validationReport = (ValidationReport) kieRuntime.getGlobal("validationReport");
        ReportFactory reportFactory = (ReportFactory) kieRuntime.getGlobal("reportFactory");
        validationReport.addMessage(reportFactory.createMessage(
            Message.Type.WARNING,
            ruleContext.getRule().getName(),
            context));
    }

    /**
     * @return number of years between today and
     * specified date
     */
    public static int yearsPassedSince(Date date) {
        return Years.yearsBetween(new DateMidnight(date), new DateMidnight()).getYears();
    }

}
