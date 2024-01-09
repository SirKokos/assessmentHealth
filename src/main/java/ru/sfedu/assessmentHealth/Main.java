package ru.sfedu.assessmentHealth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import javax.print.Doc;
import javax.swing.text.html.parser.Parser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class Main {
    public static void main(String[] args) {
//        Properties p = new Properties();
//        p.getProperty("dataBase.Mongo ");
//        System.out.println(p.getProperty("dataBase.Mongo"));

//        PropertyConfig propertyConfig = new PropertyConfig();
//        propertyConfig.getPropertyValue("dataBase.Mongo",Const.NAME_PROPERTY_FILE);

//        CalcReport c = new CalcReport();
//        c.setId(1);
//        c.setFioPatient("Artem")
//                .setFioDoctor("Bob")
//                .setBloodAnalysis(true)
//                .setGlucoseAnalysis(true)
//                .setArterialAnalysis(true).setPrice(102.2);
//        System.out.println(c);

//        System.out.println(System.getProperty("BdConnection.properties"));
//        Doctor d = new Doctor();

// пример использования Optional.of(T Value)
//        String name = "foo";
//        Optional<String> stringExample = Optional.of(name);
//        System.out.println(stringExample.get());
//// пример использования Optional.ofNullable(T Value)
//        Integer age = null;
//        Optional<Integer> integerExample= Optional.ofNullable(age);
//        System.out.println(integerExample.isPresent());
//// пример использования Optional.empty()
//        Optional<Object> emptyExample = Optional.empty();
//        System.out.println(emptyExample.get());

//        Optional<Doctor> result = Optional.empty();
//        System.out.println(result.isEmpty());


//        CSVReader csvReader = null;
//        try {
//            csvReader = new CSVReader(new FileReader("csv/CALCREPORT.csv"));
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        CsvToBean csvToBean = new CsvToBeanBuilder(csvReader)
//                .withType(CalcReport.class) // Замените YourBean на имя вашего класса
//                .build();
//        List<CalcReport> yourBeans = csvToBean.parse();
//
//        for (CalcReport i :yourBeans){
//            System.out.println(i);
//        }


//        System.out.println(Const.SQL_TABLE_CREATE_PREPARATION);

//        Person person = new Doctor();
//        System.out.println(person.getClass());
//        Doctor doctor = (Doctor) person;

//        String d = "1 1 ";
//        System.out.println(Arrays.stream(d.split(" ")).toList().get(0).length());
//        doctor.setId(1);
//
//        doctor.setFio("Sim Artem Evgen")
//                .setAge(29)
//                .setGender("M")
//                .setStatusPerson(StatusPerson.DOCTOR);
//
//        doctor.setExperience(12)
//                .setAvgPatient(12.3)
//                .setQualification("Med")
//                .setSpecialization("Genikolog");





      String s = "1$Ubeprofen$2024-12-20$2.300000$LOW$this prep important";
        String[] split = s.split("\\$", 6);
        System.out.println(Arrays.stream(split).toList());

    }
}