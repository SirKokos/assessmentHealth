package ru.sfedu.assessmentHealth.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.PersTestUnit;

class DataProviderMongoTest {
    public static final Logger log = LogManager.getLogger(DataProviderMongo.class.getName());
    DataProviderMongo dbMongo = new DataProviderMongo();

    private PersTestUnit createPersTestUnit(){
        PersTestUnit persTestUnit = new PersTestUnit();
        persTestUnit.age = 12;
        persTestUnit.name = "Artem";
        return persTestUnit;
    }

    @Test
    void objectToJson() {
        log.debug("objectToJsonTest[0]:Test serialization object in json");
        String result =  dbMongo.objectToJson(createPersTestUnit());
        log.debug("objectToJsonTest[1]: SUCCESS jsonTest {}",result );
         }

    @Test
    void save() {
        log.debug("saveTest[0]: Test write data in mongoDB");
        PersTestUnit p = createPersTestUnit();
        dbMongo.save(CommandType.UPDATED,CONST.BD_MONGO_NAME ,p);
        dbMongo.mongoClient.close();
        log.debug("saveTest[1]: Test end close MONGO");
    }
}