package ru.sfedu.assessmentHealth.api;

import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class DataProviderYMLTest {


    @Test
    void getPeople() throws IOException {
        DataProviderYML dataProviderYML = new DataProviderYML();
       Map<Integer,String> person =  dataProviderYML.getPeople();
        System.out.println(person.values());
        System.out.println(person.keySet());
        System.out.println(person.get(1));
    }
}