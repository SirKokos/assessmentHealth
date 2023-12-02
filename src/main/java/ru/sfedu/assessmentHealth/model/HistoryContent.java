package ru.sfedu.assessmentHealth.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.CONST;

import java.lang.reflect.Field;
import java.util.*;

public class HistoryContent {
    private UUID id;
    private  String className;
    private Date createdDate;
    private String actor;
    private String methodName;
    private Map<String, Object> object;
//    private String object;
    private StatusHistory status;



    private static final Logger log = LogManager.getLogger(HistoryContent.class.getName());

    public HistoryContent(){
    }

    public void setId(){id = UUID.randomUUID();}
    public UUID getId (){return id;}

    public String getSimpleName(){return className;}
    public void setClassName(String className){this.className = className;}

    public Date getcreatedDate(){return createdDate;}
    public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}

    public String getActor(){return actor;}
    public void setActor(String actor){this.actor = actor;}

    public String getMethodName(){return methodName;}
    public void setMethodName(String methodName){this.methodName = methodName;}

    public StatusHistory getStatus(){return status;}
    public void setStatus(StatusHistory status){this.status = status;}

    public Map<String, Object> getObject(){return object;}
    public <T> void setObject (T object){this.object = convertClassToMap(object);}

    protected <T> Map<String, Object>  convertClassToMap (T object) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    @Override
    public String toString() {
        return "HistoryContent: { " +
                "id = " + id +
                " className = " + className +
                " createdDate = " + createdDate +
                " actor = " + actor +
                " methodName = " + methodName +
                " object = "+
                " status = "+ status +
                " }";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoryContent that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(className, that.className) && Objects.equals(createdDate, that.createdDate) && Objects.equals(actor, that.actor) && Objects.equals(methodName, that.methodName) && Objects.equals(object, that.object) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, createdDate, actor, methodName, object, status);
    }
}
