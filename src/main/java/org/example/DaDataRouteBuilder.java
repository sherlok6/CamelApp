package org.example;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class DaDataRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:start")
                .log(" [ Http Component Started ] ")
                .setProperty("CITY_FIAS_ID", constant("{{city.fias}}"))
                .process(new DaDataRequestProcess())
                .setHeader("Authorization", constant("Token {{api.key}}"))
                .toD("{{url}}")
                .process(new DaDataResponseProcess())
                .to("stream:out")
                .end();
    }
}
