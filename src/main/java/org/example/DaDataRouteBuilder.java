package org.example;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class DaDataRouteBuilder extends RouteBuilder {

    private String query;

    public void setQuery(String query){
        this.query = query;
    }

    @Override
    public void configure() throws Exception {
        from("timer:http?repeatCount=1")
                .log(" [ Http Component Started ] ")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader("Content-Type", constant("application/xml"))
                .setHeader("Accept", constant("application/xml"))
                .setHeader(Exchange.CHARSET_NAME, constant("utf8"))
                .setHeader("Authorization", constant("Token {{api.key}}"))
                .log("Headers: ${headers}")
                .setProperty("query", simple(query))
                .setBody(simple("<req>" +
                        "<query>" + query + "</query>" +
                        "<count>" + 20 + "</count>" +
                        "<locations>" +
                        "<city_fias_id>" + "{{city.fias}}" + "</city_fias_id>" +
                        "</locations>" +
                        "</req>"))
                .log("Body ${body}")
                .toD("{{url}}")
                .log("Headers ${headers}")
                .toD("file:{{pathToXml}}?fileName={{name}}&noop=true")
                .end();
    }
}
