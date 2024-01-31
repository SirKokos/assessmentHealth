package ru.sfedu.assessmentHealth.api;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.bson.Document;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.CommandType;
import ru.sfedu.assessmentHealth.model.HistoryContent;
import ru.sfedu.assessmentHealth.model.StatusHistory;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;


import java.util.Date;
import java.util.Map;


public class DataProviderMongo {
    private static final Logger log = LogManager.getLogger(DataProviderMongo.class.getName());

    public DataProviderMongo(){}

    /**
     * @param command - Тип вносимого изменения CommandType
     * @param obj сам объект который мы хотим зафиксировать
     *
     * @return StatusHistory - об успешном или не успешном окончании
     */
    public <T> StatusHistory save(CommandType command, T obj){
        log.debug("save [1]: command = {},object = {}", command,  obj);
        StatusHistory statusHistory;

        HistoryContent historyContent = new HistoryContent();
//        try (MongoClient mongoClient = MongoClients.create(PropertyConfig.getPropertyValue(Const.NAME_MONGO_HOST,Const.NAME_PROPERTY_FILE))){
//            MongoDatabase database = mongoClient.getDatabase(PropertyConfig.getPropertyValue(Const.NAME_MONGO_NAME,Const.NAME_PROPERTY_FILE));
//            database.createCollection(PropertyConfig.getPropertyValue(Const.NAME_MONGO_COLLECTION,Const.NAME_PROPERTY_FILE));
        try (MongoClient mongoClient = MongoClients.create(PropertyConfig.getPropertyValue(Const.NAME_MONGO_HOST,PropertyConfig.getConfigPath()))){
            MongoDatabase database = mongoClient.getDatabase(PropertyConfig.getPropertyValue(Const.NAME_MONGO_NAME,PropertyConfig.getConfigPath()));
            database.createCollection(PropertyConfig.getPropertyValue(Const.NAME_MONGO_COLLECTION,PropertyConfig.getConfigPath()));

            Document document = new Document();
            ObjectMapper mapper = new ObjectMapper();

            MongoCollection<Document> collection = database.getCollection(PropertyConfig.getPropertyValue(Const.NAME_MONGO_COLLECTION,PropertyConfig.getConfigPath()));
            historyContent.setId();
            historyContent.setClassName(obj.getClass().getName());
            historyContent.setCreatedDate(new Date());
            historyContent.setActor(Const.NAME_ACTOR_HISTORY);
            historyContent.setStatus(command);
            historyContent.setObject(objToJson(obj));

            String jsonObj = mapper.writeValueAsString(historyContent);
            document = Document.parse(jsonObj);

            collection.insertOne(document);
            statusHistory = StatusHistory.SUCCESS;
        }catch (Exception e){
            statusHistory = StatusHistory.FAULT;
            log.error("save [2] Error {}:",e.getMessage());
        }
        return statusHistory;
    }


    /**
     * Метод служит для создания Map чтоб потом можно было использовать
     * в Mongo
     * @param obj  - Объект который будет преобразован в Map
     * @return Map<String,Object>
     */
    public <T> Map<String,Object> objToJson(T obj){
        log.debug("objToJson [1]: start working method");
        Map<String,Object> result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.convertValue(obj, Map.class);
        }catch (Exception e){
            log.error("objToJson [2]: Error working");
        }
        log.debug("objToJson [3]: End working");
        return result;
    }


}
