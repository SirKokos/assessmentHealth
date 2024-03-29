package ru.sfedu.assessmentHealth.utils;

import org.apache.commons.collections.functors.PredicateTransformer;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.api.IDataProvider;
import ru.sfedu.assessmentHealth.api.Servis;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.Preparation;
import ru.sfedu.assessmentHealth.model.Schedule;
import ru.sfedu.assessmentHealth.model.StatusSchedule;

import java.util.*;
import java.util.function.BiPredicate;

public class ServisUtil {
    private static final Logger log = LogManager.getLogger(ServisUtil.class.getName());

    /**
     * Вспомогательный метод для determinationUrgency()
     * генерирует список врачей по 3 основным категориям (Гематолог, Эндокриолог ,Липидолог)
     * @see ru.sfedu.assessmentHealth.api.Servis
     * @param resultAnalyse - Название анализа из 3 (RESULT_CELLS_BLOOD , RESULT_HEMOGLOBIN,RESULT_GLUCOSE)
     * @param doctorType - Типы врачей которые могут выехать 3 основных (DOCTOR_TYPE_GEMOTOLOG,DOCTOR_TYPE_LIPIDOLOG,DOCTOR_TYPE_ENDOCRINOLOG)
     * @param dataProvider - параметр IDataProvider для работы с истониками данных
     * @param  mapAssessmentHealth - Словарь с данными анализов пациента valur ={0-отличе от нормы и 1 - норма} а ключи это название анализов
     * @return List<Doctor>
     */
    static public List<Doctor> generateListDoctor(IDataProvider dataProvider, Map<String,Integer> mapAssessmentHealth, String resultAnalyse , String doctorType){
        log.debug("generateListDoctor [1]: start working");
        List<Doctor> result = new ArrayList<>();
        try {
            switch (mapAssessmentHealth.get(resultAnalyse)){
                case 0 ->{
                    result = dataProvider.selectAllDoctor()
                            .get()
                            .stream()
                            .filter(i -> i.getSpecialization().equals(doctorType))
                            .toList();
                }
            }
        }catch (Exception e){
            log.error("generateListDoctor [2]: error {}",e.getMessage());
        }

        log.debug("generateListDoctor [3]: end working");
        return result;
    }

    /**
     * Интерфейс, который перебирает расписание врача и находит свободное
     * возвращает List<Schedule>
     * @param doctors Класс врача
     * @return List<Schedule> - со статусом FREE
     */
    public static List<Schedule> generateListScheduleFree(Doctor doctors){
        return doctors.getLinkSchedule()
                .stream()
                .filter(j -> j.getStatSchedule().equals(StatusSchedule.FREE))
                .toList();
    }


    /**
     * Метод для генерации списка расписаний
     * Для Servis
     *
     * @param iDataProvider - провайдер
     * @param listId - Строка разделенная пробелом id расписания
     * @return schedules
     */
    public static List<Schedule> getListSchedule(IDataProvider iDataProvider,String listId){
        log.debug("getListSchedule [1]: start working");
        List<Schedule> schedules = new ArrayList<>();
    try {
        List<Integer> listScheduleId = Arrays.stream(listId.split(" ")).map(i->Integer.parseInt(i)).toList();
        switch (listScheduleId.size()){
            case 0 -> log.info("Список id Schedule пуст");
            default -> {
                listScheduleId.forEach(i->schedules.add(iDataProvider.selectScheduleId(i).get()));
            }
        }
    }catch (Exception e){
        log.error("getListSchedule [2]: ERROR {}",e.getMessage());
    }
        log.debug("getListSchedule [1]: end working");
        return schedules;
    }


    /**
     * Метод для генерации списка препаратов
     * Для Servis
     *
     * @param iDataProvider - провайдер
     * @param listId - Строка разделенная пробелом id расписания
     * @return preparations
     */
    public static List<Preparation> getListPreparation(IDataProvider iDataProvider, String listId){
        log.debug("getListPreparation [1]: start working");
        List<Preparation> preparations = new ArrayList<>();
        try {
            List<Integer> listPreparationId = Arrays.stream(listId.split(" ")).map(i->Integer.parseInt(i)).toList();
            switch (listPreparationId.size()){
                case 0 -> log.info("Список id Preparation пуст");
                default -> {
                    listPreparationId.forEach(i->preparations.add(iDataProvider.selectPreparationId(i).get()));
                }
            }
        }catch (Exception e){
            log.error("getListPreparation [2]: error {}",e.getMessage());
        }

        log.debug("getListPreparation [3]: end working");
        return preparations;
    }


    /**
     * Метод нужен для поддержки метода assessmentHealth
     * @param result Это словарь, который будет передавать значения здоровья
     * @param validValuesRange Это из файла Const диапазоны допустимых значений
     * @param patientResult Это параметры пациента, которые нужно проверить
     * @param pointHealth  Это сколько балов стоит этот пункт
     * @param nameParameter Это имя парамера, который проверяли
     * @return Кортеж с на первом месте которого находится словарь а на 2 значение здоровья
     */
    public static Pair<Map<String,Integer>,Integer> rangeCheckerValuesAssessmentHealth(
                                                                         Map<String,Integer> result,
                                                                         Pair<Double,Double> validValuesRange,
                                                                         Double patientResult,
                                                                         Integer pointHealth,
                                                                         String nameParameter)
    {
        log.debug("rangeCheckerValuesAssessmentHealth [1]: start working");
        Map<String,Integer> resultMap = result;
        Integer health = 0;
        BiPredicate<Pair<Double,Double>,Double> check = (pair, analyse ) ->
                (pair.getLeft() < analyse && pair.getRight() > analyse);
        Pair<Map<String,Integer>,Integer> resultContainer = Pair.of(result,0);
        try {
            if(check.test(validValuesRange,patientResult)){
                health += pointHealth;
                resultMap.put(nameParameter,1);
            }else {resultMap.put(nameParameter,0);}
            resultContainer = Pair.of(resultMap,health);
        }catch (Exception e){
            log.error("rangeCheckerValuesAssessmentHealth [2]: ERROR {}",e.getMessage());
        }
        log.debug("rangeCheckerValuesAssessmentHealth [3]: END working {}",resultContainer);
        return resultContainer;
    }


    /**
     * Метод нужен для вынесения хардкода из основного метода
     * Вызывает метод rangeCheckerValuesAssessmentHealth
     * все входные параметры это данные пациента
     * @param age Возраст
     * @param redBloodCellsCount Эритроциты
     * @param hemoglobinLevel Гемоглобин
     * @param plateletCount тромбоциты
     * @param glucoseLevel глюкоза
     * @param cholesterolLevel холестерин
     * @param gender пол
     * @return Кортеж с Map парметрами и очками здоровья
     */
    public static Map<String, Integer> accumRangeCheckerValuesAssessmentHealth(
            Integer age,
            Double redBloodCellsCount,
            Double hemoglobinLevel,
            Double plateletCount ,
            Double glucoseLevel,
            Double cholesterolLevel,
            String gender){
        log.debug("accumRangeCheckerValuesAssessmentHealth [1]: start working");
        Integer health = 0;
        Map<String,Integer> result = new HashMap<>();
        Pair<Map<String, Integer>, Integer> pairRedBloodCellsCount;
        Pair<Map<String, Integer>, Integer> pairHemoglobin;
        Pair<Map<String, Integer>, Integer> pairPlatelets;
        Pair<Map<String, Integer>, Integer> pairGlucose;
        Pair<Map<String, Integer>, Integer> pairCholesterol = Pair.of(result,0);
        Map<String, Integer> data = new HashMap<>();
        try{
            if (age < Const.LIMIT_AGE_SERVIS) {health += Const.POINT_AGE_SERVIS;}
            pairRedBloodCellsCount = rangeCheckerValuesAssessmentHealth(
                    result,
                    Const.VALID_CELLS_BLOOD,
                    redBloodCellsCount,
                    Const.POINT_BLOOD_HEALTH,
                    Const.RESULT_CELLS_BLOOD);
            health += pairRedBloodCellsCount.getRight();

            if(gender.equals(Const.GENDER_SERVIS_M)){
                pairHemoglobin = rangeCheckerValuesAssessmentHealth(
                        pairRedBloodCellsCount.getLeft(),
                        Const.VALID_HEMOGLOBIN_M,
                        hemoglobinLevel,
                        Const.POINT_HEMOGLOBIN_HEALTH,
                        Const.RESULT_HEMOGLOBIN
                );
                health += pairHemoglobin.getRight();
            }else {
                pairHemoglobin = rangeCheckerValuesAssessmentHealth(
                        pairRedBloodCellsCount.getLeft(),
                        Const.VALID_HEMOGLOBIN_G,
                        hemoglobinLevel,
                        Const.POINT_HEMOGLOBIN_HEALTH,
                        Const.RESULT_HEMOGLOBIN
                );
                health += pairHemoglobin.getRight();
            }
            pairPlatelets = rangeCheckerValuesAssessmentHealth(
                    pairHemoglobin.getLeft(),
                    Const.VALID_PLATELETS,
                    plateletCount,
                    Const.POINT_PLATELETS_HEALTH,
                    Const.RESULT_PLATELETS
            );
            health += pairPlatelets.getRight();

            pairGlucose = rangeCheckerValuesAssessmentHealth(
                    pairPlatelets.getLeft(),
                    Const.VALID_GLUCOSE,
                    glucoseLevel,
                    Const.POINT_GLUCOSE_HEALTH,
                    Const.RESULT_GLUCOSE
            );
            health += pairGlucose.getRight();

            pairCholesterol = rangeCheckerValuesAssessmentHealth(
                    pairGlucose.getLeft(),
                    Const.VALID_CHOLESTEROL,
                    cholesterolLevel,
                    Const.POINT_CHOLESTEROL_HEALTH,
                    Const.RESULT_CHOLESTEROL
            );
            health += pairCholesterol.getRight();
            pairCholesterol.getLeft().put(Const.RESULT_HEALTH, health);
            data = pairCholesterol.getLeft();
        }catch (Exception e){
            log.error("accumRangeCheckerValuesAssessmentHealth [2]: ERROR working {}",e.getMessage());
        }
        log.debug("accumRangeCheckerValuesAssessmentHealth [3]: end working {}",pairCholesterol);
        return data;
    }





}
