package ru.sfedu.assessmentHealth.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.utils.PersTestUnit;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HistoryContentTest {

    public static final Logger log = LogManager.getLogger(HistoryContent.class.getName());
    @Test
    void convertClassToMap() {
        log.debug("convertClassToMap [0]: Начало теста по конвертации объекта в Map");
        try {
            HistoryContent historyContent = new HistoryContent();
            PersTestUnit persTestUnit = new PersTestUnit();
            persTestUnit.age = 12;
            persTestUnit.name = "Artem";

            Map<String, Object> mapTest = new HashMap<>();
            mapTest.put("age",12);
            mapTest.put("name","Artem");

            Map<String,Object> convertMapTest = historyContent.convertClassToMap(persTestUnit);

            assertEquals(mapTest,convertMapTest);
            mapTest.replace("name","Bob");
            assertNotEquals(mapTest, convertMapTest);

            log.debug("convertClassToMap [1]: Тесты прошли успешно конвертация = {}",convertMapTest);
        }catch (Exception e){
            log.error(e.getMessage());
        }


    }

}