package org.example;

import org.apache.camel.CamelContext;
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

        Properties properties = camelContext.getPropertiesComponent().loadProperties();

        DaDataRouteBuilder daDataRoute = new DaDataRouteBuilder();
        Scanner scan = new Scanner(System.in,"Windows-1251");

        System.out.println("=====>>>>>");

        String query = scan.nextLine();

        daDataRoute.setQuery(query);

        camelContext.addRoutes(daDataRoute);

        camelContext.start();
        Thread.sleep(3_000);
        camelContext.stop();

        info(properties.getProperty("pathToXml") + "/" + properties.getProperty("name"));
    }

    public static void info(String path) throws ParserConfigurationException, IOException, SAXException {
        XmlDocReader xmlDocBuilder = new XmlDocReader(path);
        xmlDocBuilder.setDocBuilder();
        xmlDocBuilder.setDocument();

        Node root = xmlDocBuilder.getDocument().getDocumentElement();
        NodeList childs = root.getChildNodes();

        int count = childs.getLength();

        System.out.println("Количество: " + count);
        for(int i=0;i < childs.getLength();i++){
            Node child = childs.item(i).getChildNodes().item(0);
            System.out.println(child.getTextContent());
        }
    }
}
