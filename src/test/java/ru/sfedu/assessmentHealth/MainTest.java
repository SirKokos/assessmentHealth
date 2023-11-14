package ru.sfedu.assessmentHealth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.api.Client;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static final Logger log = LogManager.getLogger(MainTest.class.getName());
    @Test
    void TestMainFile() {
        log.debug("[0] Начало процесса {}",MainTest.class.getName());

        File file = new File(CONST.log4jPath);
        Properties properties = new Properties();

        try {
            properties.load(new FileReader(file));
        }catch (IOException e){
           log.error("Ошибка файла");
        }

        System.out.println(properties.values());


        for (String key : properties.stringPropertyNames())
        {
            System.out.println(properties.get(key));
        }
    }

    @Test
    @DisplayName("ffewfw")
    void ConstTest() {

        File file = new File(CONST.PATH_CONST_PROPERTIES);
        Properties properties = new Properties();

        try {
            properties.load(new FileReader(file));
        }catch (IOException e){
            log.error("Ошибка файла");
        }


        properties.setProperty("KeyTest","ValueTest");
        assertEquals(properties.getProperty("KeyTest"),"ValueTest");
        log.info("assertEquals() KeyTest == ValueTest [0] - success");

        System.out.println("Список переменных = " + properties.values());
        System.out.println("Список ключей = " + properties.stringPropertyNames());

        properties.remove("KeyTest");
        assertEquals(properties.getProperty("KeyTest"),null);
        log.info("assertEquals() KeyTest ==> del [1] - success");



        try {
            properties.store(new FileWriter(file), "Updated properties");
        } catch (IOException e){
            log.error("Ошибка при сохранении файла");
        }
    }
}