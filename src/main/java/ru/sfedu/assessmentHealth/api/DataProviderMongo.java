package ru.sfedu.assessmentHealth.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.model.CommandType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataProviderMongo {
    private static final Logger log = LogManager.getLogger(DataProviderMongo.class.getName());

    public   <T> List<T> jsonArrayToObjectList(List<Map<String, Object>> map, Class<T> tClass) {
        try {
            //это объект Jackson, который выполняет сериализацию
            ObjectMapper mapper = new ObjectMapper();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);

            List<T> objects = mapper.convertValue(map, listType);
            return objects;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ClassCastException(ex.getMessage());
        }
    }
}
