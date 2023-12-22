package ru.sfedu.assessmentHealth.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;
@Root
public class XmlWrapper <T> {
    @ElementList(inline=true)
    public List<T> listObjXml = new ArrayList<>();
    public List<T> getListObjXml(){return listObjXml;}
    public void setListObjXml(List<T> listObjXml){this.listObjXml = listObjXml;}
}
