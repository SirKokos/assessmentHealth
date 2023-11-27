package ru.sfedu.assessmentHealth.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.CONST;

import java.util.*;

public class HistoryContent {
    private UUID id;
    private  String className;
    private Date createdDate;
    private String actor;
    private String methodName;
    private Map<String, Object> object;
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

    public Map<String,Object> setObject(){
        object = new HashMap<>();
        object.put("id",id);
        object.put("className",className);
        object.put("createdDate",createdDate);
        object.put("actor",actor);
        object.put("methodName",methodName);
        object.put("status",status);
        return object;
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








}
