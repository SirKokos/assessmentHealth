package ru.sfedu.assessmentHealth.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.model.*;

public class CreateObj {
    private static final Logger log = LogManager.getLogger(CreateObj.class.getName());


    /**
     *
     * Метод для создания объекта. Нужен для перевода string параметров из main в нужные типы данных
     *
     * @param dateWeek День недели
     * @param dateSchedule - Дата
     * @param timeBegin - начало работы
     * @param timeEnd - конец работы
     * @param statSchedule - Статус расписания
     * @return schedule - это и есть наш объект
     */
    public static Schedule createSchedule(String dateWeek,String dateSchedule,String timeBegin,String timeEnd,String statSchedule){
       log.debug("createSchedule [1]: процесс создания расписания");
        Schedule schedule = new Schedule();
        try {
            schedule.setWeek(DateWeek.valueOf(dateWeek))
                    .setDateSchedule(dateSchedule)
                    .setTimeBegin(timeBegin)
                    .setTimeEnd(timeEnd)
                    .setStatSchedule(StatusSchedule.valueOf(statSchedule));
        }catch (Exception e) {
            log.error("createSchedule [2]: при создании произошла ошибка {}", e.getMessage());
        }
            log.debug("createSchedule [3]: END {}",schedule);
        return schedule;
    }


    /**
     * Метод для создания объекта. Нужен для перевода string параметров из main в нужные типы данных
     * @param namePrep Название препарата
     * @param dateEnd Конец годности товара
     * @param dosage Доза
     * @param statusPrep Статус препарата
     * @param aboutPrep Краткая информация
     * @return preparation - это и есть наш объект
     */
    public static Preparation createPreparation(String namePrep,String dateEnd,String dosage,String statusPrep,String aboutPrep){
        log.debug("createPreparation [1]: процесс создания расписания");
        Preparation preparation = new Preparation();
        try {
            preparation.setNamePrep(namePrep)
                    .setDateEnd(dateEnd)
                    .setDosage(Double.parseDouble(dosage))
                    .setStatusVisitPreparation(StatusPreparation.valueOf(statusPrep))
                    .setAboutPrep(aboutPrep);
        }catch (Exception e) {
            log.error("createPreparation [2]: при создании произошла ошибка {}", e.getMessage());
        }
        log.debug("createPreparation [3]: END  {}",preparation);
        return preparation;
    }

    /**
     *
     * Метод для создания объекта. Нужен для перевода string параметров из main в нужные типы данных
     *
     * @param fio Полное Имя фамилия отчество
     * @param age Возраст
     * @param gender пол
     * @param statusPerson Статус человека
     * @param cellsBlood  эритроциты
     * @param hemoglobin гемоглобин
     * @param platelets тромбоциты
     * @param glucose глюкоза
     * @param cholesterol холестерин
     * @param statusVisit - статус визита
     * @return patient это и есть наш объект
     */
    public static Patient createPatient(String fio,String age,String gender,String statusPerson,String cellsBlood,String hemoglobin,String platelets,String glucose,String cholesterol,String statusVisit){
        log.debug("createPatient [1]: процесс создания расписания");
        Patient patient = new Patient();
        try {
            patient.setFio(fio)
                    .setAge(Integer.parseInt(age))
                    .setGender(gender)
                    .setStatusPerson(StatusPat.valueOf(statusPerson));

            patient.setCellsBlood(Double.parseDouble(cellsBlood))
                    .setHemoglobin(Double.parseDouble(hemoglobin))
                    .setPlatelets(Double.parseDouble(platelets))
                    .setGlucose(Double.parseDouble(glucose))
                    .setCholesterol(Double.parseDouble(cholesterol))
                    .setStatusVisit(StatusVisitEn.valueOf(statusVisit));
        }catch (Exception e) {
            log.error("createPatient [2]: при создании произошла ошибка {}", e.getMessage());
        }
        log.debug("createPatient [3]: END {}",patient);
        return patient;
    }

    /**
     * Метод который формирует из командной строки объект
     * Поля с расписанием и Препаратами будут вынесены в ServisUtil
     * @param fio - Имя
     * @param age - Возраст
     * @param gender - Пол
     * @param statusPerson - Статус
     * @param exp - Опыт
     * @param avgPatient - Среднее количество пациентов
     * @param qualification - Квалификация
     * @param special - Специализация
     * @return Doctor это и есть наш объект
     */
    public static Doctor createDoctor(String fio,String age,String gender,String statusPerson,String exp,String avgPatient,String qualification,String special){
        log.debug("createPatient [1]: процесс создания расписания");
        Doctor doctor = new Doctor();
        try {
            doctor.setFio(fio)
                    .setAge(Integer.parseInt(age))
                    .setGender(gender)
                    .setStatusPerson(StatusPat.valueOf(statusPerson));

            doctor.setExperience(Integer.parseInt(exp))
                    .setAvgPatient(Double.parseDouble(avgPatient))
                    .setQualification(qualification)
                    .setSpecialization(special);

        }catch (Exception e) {
            log.error("createPatient [2]: при создании произошла ошибка {}", e.getMessage());
        }
        log.debug("createPatient [3]: END {}",doctor);
        return doctor;
    }

    /**
     * Метод для создания объекта. Нужен для перевода string параметров из main в нужные типы данных
     *
     * @param fioPatient фио пациента
     * @param fioDoctor фио доктора
     * @param blood кровь
     * @param glucose глюкоза
     * @param arterial артерии
     * @return calcReport это и есть наш объект
     */
    public static CalcReport createCalcReport(String fioPatient,String fioDoctor, String blood, String glucose,String arterial){
        log.debug("createCalcReport [1]: процесс создания расписания");
        CalcReport calcReport = new CalcReport();

        try {
            calcReport.setFioPatient(fioPatient)
                    .setFioDoctor(fioDoctor)
                    .setBloodAnalysis(Boolean.valueOf(blood))
                    .setGlucoseAnalysis(Boolean.valueOf(glucose))
                    .setArterialAnalysis(Boolean.valueOf(arterial));

        }catch (Exception e) {
            log.error("createCalcReport [2]: при создании произошла ошибка {}", e.getMessage());
        }
        return calcReport;

    }


}
