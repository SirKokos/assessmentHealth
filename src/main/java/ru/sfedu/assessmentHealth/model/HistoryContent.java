package ru.sfedu.assessmentHealth.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.bson.Document;

import java.util.*;

public class HistoryContent {
    private UUID id;
    private  String className;
    private Date createdDate;
    private String actor;
    private String methodName;
    private CommandType status;
    private Map<String,Object> object;



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

    public CommandType getStatus(){return status;}
    public void setStatus(CommandType status){this.status = status;}


    public Map<String, Object> getObject() {
        return object;
    }

    public void setObject(Map<String, Object> object) {

        this.object = object;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoryContent that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(className, that.className) && Objects.equals(createdDate, that.createdDate) && Objects.equals(actor, that.actor) && Objects.equals(methodName, that.methodName) && status == that.status && Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, createdDate, actor, methodName, status, object);
    }

    @Override
    public String toString() {
        return "{" +
                " id=" + id +
                ", className='" + className + '\'' +
                ", createdDate=" + createdDate +
                ", actor='" + actor + '\'' +
                ", methodName='" + methodName + '\'' +
                ", status=" + status +
                ", object=" + object +
                '}';
    }
}
