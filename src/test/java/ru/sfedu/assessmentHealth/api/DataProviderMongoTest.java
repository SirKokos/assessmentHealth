package ru.sfedu.assessmentHealth.api;

import com.mongodb.client.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.model.CommandType;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.StatusHistory;

import javax.print.Doc;

import java.util.Map;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderMongoTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(DataProviderMongoTest.class.getName());

    @Test
    void objToJson() {
        log.debug("objToJson [1]: Start working method");
        DataProviderMongo dataProviderMongo = new DataProviderMongo();
        Doctor expected = getDoctor();
        Map<String, Object> result = dataProviderMongo.objToJson(expected);
        assertEquals(result.get("id"),expected.getId());
        log.debug("objToJson [2]: End working method");
    }

    @Test
    void objToJsonNeg() {
        log.debug("objToJsonNeg [1]: Start working method");
        DataProviderMongo dataProviderMongo = new DataProviderMongo();
        Doctor expected = new Doctor();
        Map<String, Object> result = dataProviderMongo.objToJson(expected);
        assertEquals(result.get("id"),null);
        log.debug("objToJsonNeg [2]: End working method");
    }
    @Test
    void save() {
        log.debug("save [1]: Start working method");
        DataProviderMongo dataProviderMongo = new DataProviderMongo();
        Doctor expected = getDoctor();
        StatusHistory statusHistory =  dataProviderMongo.save(CommandType.UPDATED,expected);
        assertEquals(statusHistory,StatusHistory.SUCCESS);
        log.debug("save [2]: End working method");
    }
    @Test
    void saveNeg() {
        log.debug("saveNeg [1]: Start working method");
        DataProviderMongo dataProviderMongo = new DataProviderMongo();
        Doctor expected = getDoctor();
        StatusHistory statusHistory =  dataProviderMongo.save(CommandType.UPDATED,expected);
        assertNotEquals(statusHistory,StatusHistory.FAULT);
        log.debug("saveNeg [2]: End working method");
    }
}