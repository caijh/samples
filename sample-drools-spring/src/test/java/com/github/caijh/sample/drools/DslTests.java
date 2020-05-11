package com.github.caijh.sample.drools;

import java.io.IOException;
import java.io.StringReader;

import org.drools.compiler.compiler.DrlParser;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.lang.dsl.DefaultExpanderResolver;
import org.junit.jupiter.api.Test;

public class DslTests {

    @Test
    public void test() throws IOException, DroolsParserException {
        String dslContent = "[when] There is a person = $p:Person()\n" +
            "[when] - id greater than {id:\\d*} = id > {id}\n" +
            "[then] print = System.out.println(\"I am fired!\")";

        String dslrContent = "rule 'test-dsl'\n" +
            "\n" +
            "when\n" +
            "    There is a person\n" +
            "    - id greater than 10\n" +
            "then\n" +
            "    print\n" +
            "end";

        DefaultExpanderResolver resolver = new DefaultExpanderResolver(new StringReader(dslContent));

        DrlParser parser = new DrlParser();

        String string = parser.getExpandedDRL(dslrContent, resolver);

        System.out.println(string);
    }

}
