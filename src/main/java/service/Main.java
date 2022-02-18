package service;

import org.xml.sax.SAXException;
import util.OSMHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

import static setting.setting.fileAddress;

public class Main {



    public static void main(String[] args) {

        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            OSMHandler osmHandler = new OSMHandler();
            saxParser.parse(new File(fileAddress), osmHandler);
            System.out.println(OSMHandler.bound.toString());
            System.out.println(OSMHandler.nodes.toString());

            OSMHandler.nodes.stream().forEach(node -> System.out.println("nodeID: " + node.getId()));
            OSMHandler.ways.stream().forEach(way -> System.out.println("wayID: "+ way.getId()));
            OSMHandler.relations.stream().forEach(relation -> System.out.println("relationID: "+ relation.getId()));
        } catch (SAXException | ParserConfigurationException | IOException e){
            e.printStackTrace();
        }
    }
}
