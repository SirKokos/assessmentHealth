package ru.sfedu.assessmentHealth.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.CommandType;
import ru.sfedu.assessmentHealth.model.HistoryContent;
import ru.sfedu.assessmentHealth.model.StatusHistory;


import java.util.Date;




public class DataProviderMongo{
    private static final Logger log = LogManager.getLogger(DataProviderMongo.class.getName());
    MongoClient mongoClient;
    MongoDatabase database;


    DataProviderMongo(){
        mongoClient = MongoClients.create(CONST.BD_MONGO_HOST);
        database = mongoClient.getDatabase(CONST.BD_MONGO_NAME);
    }
    protected <T> String objectToJson(T tClass){
        log.debug("objectToJson [0]: serialization from {} in json",tClass);
        ObjectMapper mapper = new ObjectMapper();
        // Сериализация объекта — преобразование объекта в строку JSON
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(tClass);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            mongoClient.close();
            throw new RuntimeException(e);
        }
        log.debug("objectToJson [1]: Result {}",jsonString);
        return jsonString;
    }

    public <T> void save(CommandType command, String bdName, T obj){
        log.debug("save [0]: command = {},object = {}, dbName = {}", command,  obj, bdName);
        MongoCollection<Document> collection = database.getCollection(CONST.BD_MONGO_COLLECTION);
        try {
            HistoryContent historyContent = new HistoryContent();
            historyContent.setId();
            historyContent.setClassName(obj.getClass().getName());
            historyContent.setCreatedDate(new Date());
            historyContent.setActor(CONST.NAME_ACTOR_HISTORY);
            historyContent.setStatus(StatusHistory.SUCCESS);
            historyContent.setObject(obj);

            String jsonObjectHistory = objectToJson(historyContent);
            log.debug("jsonObjectHistory [1]: serialization in json {}",jsonObjectHistory);
            Document document = Document.parse(jsonObjectHistory);
            collection.insertOne(document);
            log.debug("insertOne [2]: insert data Ok");

        }catch (Exception e){

            log.error("save [3] Error :",e);

        }finally {
            mongoClient.close();
            log.debug("close [4]: close mongoClient");

        }
    }

}
