package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetsTest {
    private static final Logger log = LogManager.getLogger(Planets.class.getName());
    @Test
    void getPlanetTest() throws ParserConfigurationException, IOException, SAXException {
        log.info("Begin getPlanetTest()[1].....");
        List<String> testArrPl = Arrays.asList("Зeмля", "Сатурн", "Марс", "Венера");
        Planets planets = new Planets();
        Document doc = planets.getXml();
        ArrayList<String> pl_list = planets.getPlanet(doc);
        for(int i = 0 ; i < pl_list.size();i++){
            assertEquals(pl_list.get(i),testArrPl.get(i));
        }
        log.info("assertEquals(pl_list.get(i),testArrPl.get(i)) [1]  - success ");



    }
}