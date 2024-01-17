package ru.sfedu.assessmentHealth;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ru.sfedu.assessmentHealth.api.DataProviderCsv;
import ru.sfedu.assessmentHealth.api.IDataProvider;
import ru.sfedu.assessmentHealth.api.Servis;
import ru.sfedu.assessmentHealth.model.*;

import java.time.temporal.ValueRange;
import java.util.Map;
import java.util.SortedMap;


public class Main {
    public static void main(String[] args) {
        Patient patient = new Patient();
        patient.setId(1);

        patient.setFio("Bob")
                .setAge(20)
                .setGender("M")
                .setStatusPerson(StatusPerson.PATIENT);

        patient.setCellsBlood(4.9)
                .setHemoglobin(140.3)
                .setPlatelets(200.)
                .setGlucose(80.3)
                .setCholesterol(5.3)
                .setStatusVisit(StatusPatient.IN);


        Patient patient2 = new Patient();
        patient2.setId(1);

        patient2.setFio("Bob")
                .setAge(40)
                .setGender("G")
                .setStatusPerson(StatusPerson.PATIENT);

        patient2.setCellsBlood(6.6)
                .setHemoglobin(170.3)
                .setPlatelets(200.)
                .setGlucose(80.3)
                .setCholesterol(5.3)
                .setStatusVisit(StatusPatient.IN);
        Servis healthScore = new Servis();
        IDataProvider iDataProvider = new DataProviderCsv();
//        healthScore.visitDoctor(patient2);
        System.out.println(healthScore.determinationUrgency(healthScore.assessmentHealth(patient2),iDataProvider));
//        System.out.println(healthScore.determinationUrgency(healthScore.assessmentHealth(patient2),iDataProvider));
//        System.out.println(healthScore.visitDoctor(patient));
//        System.out.println(healthScore.visitDoctor(patient2));


       //        double[] inputs = {0.8, 0.6, 0.4, 0.2, 0.9, 0.7, 0.5, 0.3, 0.1, 0.6};

//        System.out.println(Const.RESULT_SYSTEM_CELLS_BLOOD);
//        System.out.println(Const.RESULT_SYSTEM_CHOLESTEROL);
//        System.out.println(Const.RESULT_SYSTEM_HEMOGLOBIN);




//        System.out.println("Оценка здоровья: " + s2);
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


//
//      String s = "1$Ubeprofen$2024-12-20$2.300000$LOW$this prep important";
//        String[] split = s.split("\\$", 6);
//        System.out.println(Arrays.stream(split).toList());

    }
}