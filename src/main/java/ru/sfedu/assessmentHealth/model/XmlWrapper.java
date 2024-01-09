package ru.sfedu.assessmentHealth.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс нужен для добавления нескольких объектов в xml Обертка которую будем использовать
 * @param <T>
 */
@Root
public class XmlWrapper <T>{
    @ElementList(inline = true)
    public List<T> ListObject = new ArrayList<>();

    public List<T> getListObjectXml(){
        return ListObject;
    }

    public void setListObject(List<T> ListObjectXml){
        this.ListObject = ListObjectXml;
    }


}
