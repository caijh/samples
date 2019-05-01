package com.coding.sample.graphql.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.function.Function;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

@Component
public class DateScalar extends GraphQLScalarType {

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateScalar() {
        super("Date", "An RFC-3339 compliant Full Date Scalar", new Coercing<Date, String>() {
            @Override
            public String serialize(Object input) throws CoercingSerializeException {
                if (!(input instanceof Date)) {
                    throw new CoercingSerializeException(
                            "Expected a 'String' or 'java.time.temporal.TemporalAccessor' but was '" + typeName(input) + "'."
                    );
                }
                try {
                    Date date = (Date) input;
                    return DATE_FORMAT.format(date);
                } catch (DateTimeException e) {
                    throw new CoercingSerializeException(
                            "Unable to turn TemporalAccessor into full date because of : '" + e.getMessage() + "'."
                    );
                }
            }

            private String typeName(Object input) {
                return input.getClass().getSimpleName();
            }

            @Override
            public Date parseValue(Object input) throws CoercingParseValueException {
                TemporalAccessor temporalAccessor;
                if (!(input instanceof String)) {
                    throw new CoercingParseValueException(
                            "Expected a 'String' or 'java.time.temporal.TemporalAccessor' but was '" + typeName(input) + "'."
                    );
                }
                try {
                    return DATE_FORMAT.parse((String) input);
                } catch (ParseException e) {
                    throw new CoercingParseValueException(
                            "Unable to turn TemporalAccessor into full date because of : '" + e.getMessage() + "'."
                    );
                }
            }

            @Override
            public Date parseLiteral(Object input) throws CoercingParseLiteralException {
                if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException(
                            "Expected AST type 'StringValue' but was '" + typeName(input) + "'."
                    );
                }
                return parseDate(((StringValue) input).getValue(), CoercingParseLiteralException::new);
            }

            private Date parseDate(String s, Function<String, RuntimeException> exceptionMaker) {
                try {
                    return DATE_FORMAT.parse(s);
                } catch (ParseException e) {
                    throw exceptionMaker.apply("Invalid RFC3339 full date value : '" + s + "'. because of : '" + e.getMessage() + "'");
                }
            }
        });
    }

}