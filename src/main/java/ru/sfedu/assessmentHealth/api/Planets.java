package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.sfedu.assessmentHealth.CONST;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Planets  {
    private static final Logger log = LogManager.getLogger(Planets.class.getName());

    public Document getXml()throws ParserConfigurationException, IOException, SAXException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder =factory.newDocumentBuilder() ;
        Document document = builder.parse(new File(CONST.PATH_CONST_XML));
        return document;
    }
    public ArrayList<String> getPlanet(Document document)  {

        ArrayList<String> planets = new ArrayList<>();
        Element element = document.getDocumentElement();

        NodeList nodeList = element.getChildNodes();
        for(int i = 0; i< nodeList.getLength();i++){
        if(nodeList.item(i) instanceof Element){
            if( ((Element) nodeList.item(i)).hasAttribute("name")){
                String planet =  ((Element) nodeList.item(i)).getAttribute("name");
                planets.add(planet);
                }
            }
        }
        return planets;
    }


}
