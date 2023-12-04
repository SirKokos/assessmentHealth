package ru.sfedu.assessmentHealth;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.w3c.dom.Document;
import ru.sfedu.assessmentHealth.api.DataProviderPostgres;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.utils.PersTestUnit;

import javax.swing.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
//        System.out.println(CONST.SQL_TABLE_CREATE_DOCTOR);
//        System.out.println("----");
//        System.out.println(CONST.SQL_TABLE_CREATE_PATIENT);
//        System.out.println("----");
//
//        System.out.println(CONST.SQL_TABLE_CREATE_CALC_REPORT);
//        System.out.println("----");
//        System.out.println(CONST.SQL_TABLE_CREATE_PREPARATION);
//        System.out.println("----");
//        System.out.println(CONST.SQL_TABLE_CREATE_SCHEDULE);

//        System.out.println(String.format(CONST.SELECT_DOCTOR_TO_ID,1));
//        System.out.println(String.format(CONST.SELECT_PATIENT_TO_ID,1));
//        System.out.println(String.format(CONST.SELECT_PREPARATION_TO_ID,1));
//        System.out.println(String.format(Locale.US,CONST.INSERT_DOCTOR,1, "Иван", "Иванов", "Иванович", 30, 'M', "Статус", 10, 5000.123, "Профессия", "Общая медицина"));
//        System.out.println(String.format(Locale.US,CONST.INSERT_PATIENT,2, "Петр", "Петров", "Петрович", 40, 'M', "Статус", 100.0, 90.0, 40.0, 300.0, 100.0, 180.0,12, "Ведение"));
//        System.out.println(String.format(Locale.US,CONST.INSERT_PREPARATION,1, 1, "Antibiotic", "2022-01-15", 1.5, "In progress", "Antibiotic for infection", 1));
//        System.out.println(String.format(Locale.US,CONST.INSERT_SCHEDULE,1, 1,"Week 1", "2022-01-01", "09:00", "11:00", "In progress"));

        System.out.println(String.format(Locale.US,CONST.INSERT_CALC_REPORT,1, 1, 1, "Иван Иванов", "Доктор Иванов", true, true, true, true, 100.00));
        //        try {
//            DataProviderPostgres d = new DataProviderPostgres();
//            d.select();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

}