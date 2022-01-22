package org.example;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.getPropertiesComponent().setLocation("classpath:application.properties");
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();

        DaDataRouteBuilder dataRouteBuilder = new DaDataRouteBuilder();

        camelContext.addRoutes(dataRouteBuilder);

        camelContext.start();

        Scanner scanner = new Scanner(System.in);
        System.out.print("=>>>>>");
        String query = scanner.nextLine();

        while(!query.equals("\\q")){
            producerTemplate.sendBody("direct:start", query);

            System.out.print("=>>>>>");
            query = scanner.nextLine();
        }

        camelContext.stop();
    }
}
