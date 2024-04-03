package ru.sfedu.assessmentHealth.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.api.DataProviderPost;
import ru.sfedu.assessmentHealth.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PostgresUtils {
    private static final Logger log = LogManager.getLogger(PostgresUtils.class);


    /**
     * Генерирует создание sql запросов
     * @param statement PreparedStatement объект в котором будет формироваться запрос
     * @param obj Наш объект
     * @return statement
     * @throws SQLException
     */
    public static PreparedStatement createDoctor(PreparedStatement statement, Doctor obj) throws SQLException {
        log.debug("createDoctor [1]: start working");
        String prepIdAcc = "";
        String scheIdAcc = "";

        statement.setString(1,obj.getFio());
        statement.setInt(   2, obj.getAge());
        statement.setString(3, String.valueOf(obj.getGender()));
        statement.setString(4, String.valueOf(obj.getStatusPerson()));
        statement.setInt(5, obj.getExperience());
        statement.setDouble(6,obj.getAvgPatient());
        statement.setString(7, String.valueOf(obj.getQualification()));
        statement.setString(8, String.valueOf(obj.getSpecialization()));

        for(Preparation i:obj.getLinkPreparation()){
            prepIdAcc += i.getId()+" ";
        }
        statement.setString(9, prepIdAcc);

        for(Schedule i:obj.getLinkSchedule()){
            scheIdAcc += i.getId()+" ";
        }
        statement.setString(10, scheIdAcc);
        log.debug("createDoctor [2]: end working");
        return statement;
    }
    public static PreparedStatement createPatient(PreparedStatement statement, Patient obj) throws SQLException {
        log.debug("createPatient [1]: start working");
        statement.setString(1,obj.getFio());
        statement.setInt(   2, obj.getAge());
        statement.setString(3, String.valueOf(obj.getGender()));
        statement.setString(4, String.valueOf(obj.getStatusPerson()));
        statement.setDouble(5, obj.getCellsBlood());
        statement.setDouble(6,obj.getHemoglobin());
        statement.setDouble(7,obj.getPlatelets());
        statement.setDouble(8,obj.getGlucose());
        statement.setDouble(9,obj.getCholesterol());
        statement.setString(10, String.valueOf(obj.getStatusVisit()));
        log.debug("createPatient [2]: end working");
        return statement;
    }

    public static PreparedStatement createPreparation(PreparedStatement statement, Preparation obj) throws SQLException {
        log.debug("createPreparation [1]: start working");
        statement.setString(1,obj.getNamePrep());
        statement.setDate(   2, obj.getDateEnd());
        statement.setDouble(3,  (obj.getDosage()));
        statement.setString(4, String.valueOf(obj.getStatusVisitPreparation()));
        statement.setString(5, obj.getAboutPrep());
        log.debug("createPreparation [2]: end working");
        return statement;
    }

    public static PreparedStatement createSchedule(PreparedStatement statement, Schedule obj) throws SQLException {
        log.debug("createSchedule [1]: start working");
        statement.setString(1, String.valueOf(obj.getWeek()));
        statement.setDate(   2, (obj.getDateSchedule()));
        statement.setTime(3, (obj.getTimeBegin()));
        statement.setTime(4, (obj.getTimeEnd()));
        statement.setString(5, String.valueOf(obj.getStatSchedule()));
        log.debug("createSchedule [2]: end working");
        return statement;
    }
    public static PreparedStatement createCalcReport(PreparedStatement statement, CalcReport obj) throws SQLException {
        log.debug("createCalcReport [1]: start working");
        statement.setString(1, obj.getFioPatient());
        statement.setString(2, obj.getFioDoctor());
        statement.setBoolean(3, obj.getBloodAnalysis());
        statement.setBoolean(4, obj.getGlucoseAnalysis());
        statement.setBoolean(5, obj.getArterialAnalysis());
        statement.setString(6, String.valueOf(obj.getDoctor().get(0).getId()));
        statement.setString(7, String.valueOf(obj.getPatient().get(0).getId()));
        statement.setDouble(8, obj.getPrice());
        log.debug("createCalcReport [2]: end working");
        return statement;
    }


    /**
     * Два поля поле linkPreparation linkSchedule будут заполненны в методе select з которого он вызывается
     * @param resultSet - Полученный результат после выполнения select
     * @return Doctor
     * @throws SQLException
     */
    public static Doctor getDoctor(ResultSet resultSet) throws SQLException {
        Doctor result = new Doctor();
        DataProviderPost dataProviderPost = new DataProviderPost();

        resultSet.next();
        List<Preparation> listPrep;
        List<Schedule> listSche;
        result.setId(resultSet.getInt(1));
        result.setFio(resultSet.getString(2));
        result.setAge(resultSet.getInt(3));
        result.setGender(resultSet.getString(4));
        result.setStatusPerson(StatusPat.valueOf(resultSet.getString(5)));
        result.setExperience(resultSet.getInt(6));
        result.setAvgPatient(Double.valueOf(resultSet.getDouble(7)));
        result.setQualification(resultSet.getString(8));
        result.setSpecialization(resultSet.getString(9));


       listPrep =  Arrays.stream(resultSet.getString(10).split(" "))
                .map(i->dataProviderPost.selectPreparationId(Integer.valueOf(i)).get())
               .toList();

       result.setLinkPreparation(listPrep);

        listSche =  Arrays.stream(resultSet.getString(11).split(" "))
                .map(i->dataProviderPost.selectScheduleId(Integer.valueOf(i)).get())
                .toList();

        result.setLinkSchedule(listSche);

        return result;
    }

    /**
     * Метод формирует объект после выполнения select запроса
     * @param resultSet - Полученный результат после выполнения select
     * @return Preparation
     * @throws SQLException
     */
    public static Preparation getPreparation(ResultSet resultSet) throws SQLException {
        Preparation result = new Preparation();
        resultSet.next();
        result.setId(resultSet.getInt(1));
        result.setNamePrep(resultSet.getString(2));
        result.setDateEnd(resultSet.getString(3));
        result.setDosage(resultSet.getDouble(4));
        result.setStatusVisitPreparation(StatusPreparation.valueOf(resultSet.getString(5)));
        result.setAboutPrep(resultSet.getString(6));

        return result;
    }

    /**
     * Метод формирует объект после выполнения select запроса
     * @param resultSet - Полученный результат после выполнения select
     * @throws SQLException
     */
    public static Schedule getSchedule(ResultSet resultSet) throws SQLException {
        Schedule result = new Schedule();
        resultSet.next();
        result.setId(resultSet.getInt(1));
        result.setWeek(DateWeek.valueOf(resultSet.getString(2)));
        result.setDateSchedule(String.valueOf(resultSet.getDate(3)));
        result.setTimeBegin(resultSet.getString(4));
        result.setTimeEnd(resultSet.getString(5));
        result.setStatSchedule(StatusSchedule.valueOf(resultSet.getString(6)));

        return result;
    }

    /**
     * Метод формирует объект после выполнения select запроса
     * @param resultSet - Полученный результат после выполнения select
     * @throws SQLException
     */
    public static Patient getPatient(ResultSet resultSet) throws SQLException {
        Patient result = new Patient();
        resultSet.next();
        result.setId(resultSet.getInt(1));
        result.setFio(resultSet.getString(2));
        result.setAge(resultSet.getInt(3));
        result.setGender(resultSet.getString(4));
        result.setStatusPerson(StatusPat.valueOf(resultSet.getString(5)));

        result.setCellsBlood(resultSet.getDouble(6));
        result.setHemoglobin(resultSet.getDouble(7));
        result.setPlatelets(resultSet.getDouble(8));
        result.setGlucose(resultSet.getDouble(9));
        result.setCholesterol(resultSet.getDouble(10));
        result.setStatusVisit(StatusVisitEn.valueOf((resultSet.getString(11))));

        return result;
    }

    /**
     * Метод формирует объект после выполнения select запроса
     * @param resultSet - Полученный результат после выполнения select
     * @throws SQLException
     */
    public static CalcReport getCalcReport(ResultSet resultSet) throws SQLException {
        CalcReport result = new CalcReport();
        DataProviderPost dataProviderPost = new DataProviderPost();

        resultSet.next();
        result.setId(Integer.valueOf(resultSet.getString(1)));
        result.setFioPatient(resultSet.getString(2));
        result.setFioDoctor(resultSet.getString(3));

        result.setBloodAnalysis(resultSet.getObject(4,Boolean.class));
        result.setGlucoseAnalysis(resultSet.getObject(5,Boolean.class));
        result.setArterialAnalysis(resultSet.getObject(6,Boolean.class));

        Doctor d = dataProviderPost.selectDoctorId(Integer.valueOf(resultSet.getString(7))).get();
        result.setDoctor(List.of(d));
        Patient p = dataProviderPost.selectPatientId(Integer.valueOf(resultSet.getString(8))).get();
        result.setPatient(List.of(p));

        result.setPrice(Double.valueOf(resultSet.getString(9)));


        return result;
    }



}
