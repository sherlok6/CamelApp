package org.example;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DaDataRequestProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Gson converter = new Gson();

        String query = exchange.getIn().getBody(String.class);

        DaDataRequest daDataRequest = new DaDataRequest();
        daDataRequest.setQuery(query);
        daDataRequest.setCount(20);

        Locations[] locations = new Locations[]{new Locations()};
        locations[0].setCity_fias_id(exchange.getProperty("CITY_FIAS_ID", String.class));

        daDataRequest.setLocations(locations);

        exchange.getIn().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        exchange.getIn().setHeader(Exchange.CHARSET_NAME, "utf8");
        exchange.getIn().setHeader("Authorization", "Token {{api.key}}");

        exchange.getIn().setBody(converter.toJson(daDataRequest));
    }
}
