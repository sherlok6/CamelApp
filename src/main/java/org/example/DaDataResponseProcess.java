package org.example;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DaDataResponseProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Gson converter = new Gson();

        String response = exchange.getIn().getBody(String.class);

        System.out.println(response);

        DaDataResponse daDataResponse = converter.fromJson(response, DaDataResponse.class);

        StringBuilder stringBuilder = new StringBuilder();

        for(Suggestion suggestion : daDataResponse.getSuggestions()){
            stringBuilder.append(suggestion.getValue());
            stringBuilder.append("\n");
        }

        exchange.getIn().setBody(stringBuilder.toString(),String.class);
    }
}
