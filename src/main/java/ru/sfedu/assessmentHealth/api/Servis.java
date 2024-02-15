package ru.sfedu.assessmentHealth.api;


import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.*;

import ru.sfedu.assessmentHealth.utils.ServisUtil;


import java.io.*;

import java.util.*;
import java.util.function.BiPredicate;

import static ru.sfedu.assessmentHealth.utils.ServisUtil.accumRangeCheckerValuesAssessmentHealth;
import static ru.sfedu.assessmentHealth.utils.ServisUtil.rangeCheckerValuesAssessmentHealth;

public class Servis {

    private static final Logger log = LogManager.getLogger(Servis.class.getName());

    /**
     * Метод формирует текстовый файл visitDoctor в котором и находится результат работы
     * @param patient
     * @return StatusAnswer
     */
    public StatusAnswer visitDoctor(Patient patient){
        log.debug("visitDoctor [1]: start working");
        StatusAnswer result;
        Map<String, Integer> mapResult = assessmentHealth(patient);
        List<String>  recomList =  heallingRecom(mapResult);

        File file = new File(Const.FILE_NAME_VISIT_DOCTOR);
        try (FileOutputStream fos = new FileOutputStream(file, true);
             OutputStreamWriter osw = new OutputStreamWriter(fos,Const.UNICODE_RUS);
             BufferedWriter writer = new BufferedWriter(osw)) {
            writer.write(patient+"\n");
            for (String value : recomList) {
                writer.write(value+"\n");
            }
            writer.write(Const.FILE_DELIMITER_VISIT_DOCTOR);
            result = StatusAnswer.OK;
        } catch (IOException e) {
            result = StatusAnswer.ERROR;
            log.debug("visitDoctor [2]: {} error {}",result,e.getMessage());
        }
        return result;
    }

    /**
     * Вызывается для оценки здоровья пациента в процентной шкале (0% – 100%).
     * Обрабатывает поля с его симптомами, личной информацией(пол, возраст), анализы.
     * @param patient Объект пациента
     * @return Map<String,Integer> с оценкой здоровья и параметрами для последующего анализа
     */
    protected Map<String,Integer> assessmentHealth(Patient patient){
        log.debug("assessmentHealth [1]: start working");
//        Integer health = 0;
        Map<String,Integer> result = new HashMap<>();
        Integer age = patient.getAge();
        Double redBloodCellsCount = patient.getCellsBlood();
        Double hemoglobinLevel = patient.getHemoglobin();
        Double plateletCount = patient.getPlatelets();
        Double glucoseLevel = patient.getGlucose();
        Double cholesterolLevel = patient.getCholesterol();
        String gender = patient.getGender();
//        Pair<Map<String, Integer>, Integer> pairRedBloodCellsCount;
//        Pair<Map<String, Integer>, Integer> pairHemoglobin;
//        Pair<Map<String, Integer>, Integer> pairPlatelets;
//        Pair<Map<String, Integer>, Integer> pairGlucose;
//        Pair<Map<String, Integer>, Integer> pairCholesterol;
        try {
//            if (age < Const.LIMIT_AGE_SERVIS) {health += Const.POINT_AGE_SERVIS;}

            result = accumRangeCheckerValuesAssessmentHealth(
                    age,
                    redBloodCellsCount,
                    hemoglobinLevel,
                    plateletCount,
                    glucoseLevel,
                    cholesterolLevel,
                    gender);
//            pairRedBloodCellsCount = rangeCheckerValuesAssessmentHealth(
//                    result,
//                    Const.VALID_CELLS_BLOOD,
//                    redBloodCellsCount,
//                    Const.POINT_BLOOD_HEALTH,
//                    Const.RESULT_CELLS_BLOOD);
//            health += pairRedBloodCellsCount.getRight();
//
//            if(gender.equals(Const.GENDER_SERVIS_M)){
//                pairHemoglobin = rangeCheckerValuesAssessmentHealth(
//                        pairRedBloodCellsCount.getLeft(),
//                        Const.VALID_HEMOGLOBIN_M,
//                        hemoglobinLevel,
//                        Const.POINT_HEMOGLOBIN_HEALTH,
//                        Const.RESULT_HEMOGLOBIN
//                        );
//                health += pairHemoglobin.getRight();
//            }else {
//                pairHemoglobin = rangeCheckerValuesAssessmentHealth(
//                        pairRedBloodCellsCount.getLeft(),
//                        Const.VALID_HEMOGLOBIN_G,
//                        hemoglobinLevel,
//                        Const.POINT_HEMOGLOBIN_HEALTH,
//                        Const.RESULT_HEMOGLOBIN
//                );
//                health += pairHemoglobin.getRight();
//            }
//            pairPlatelets = rangeCheckerValuesAssessmentHealth(
//                    pairHemoglobin.getLeft(),
//                    Const.VALID_PLATELETS,
//                    plateletCount,
//                    Const.POINT_PLATELETS_HEALTH,
//                    Const.RESULT_PLATELETS
//            );
//            health += pairPlatelets.getRight();
//
//            pairGlucose = rangeCheckerValuesAssessmentHealth(
//                    pairPlatelets.getLeft(),
//                    Const.VALID_GLUCOSE,
//                    glucoseLevel,
//                    Const.POINT_GLUCOSE_HEALTH,
//                    Const.RESULT_GLUCOSE
//                    );
//            health += pairGlucose.getRight();
//
//            pairCholesterol = rangeCheckerValuesAssessmentHealth(
//                    pairGlucose.getLeft(),
//                    Const.VALID_CHOLESTEROL,
//                    cholesterolLevel,
//                    Const.POINT_CHOLESTEROL_HEALTH,
//                    Const.RESULT_CHOLESTEROL
//            );
//            health += pairCholesterol.getRight();
//            result = pairCholesterol.getLeft();
//            result.put(Const.RESULT_HEALTH,health);
        }catch (Exception e){
            log.error("assessmentHealth [2]: error {}",e.getMessage());
        }
        log.debug("assessmentHealth [3]: end working {}",result);
        return result;
    }

    /**
     * Метод, который вызывается если оценка здоровья пациента ниже 85%.
     * То происходит рекомендации от системы.
     * По которым смотрится в каких анализах или симптомах пациент нуждается.
     * Возвращает список c возможными причинами.
     * @param dictPatient
     * @return List<String>
     */
    protected List<String> heallingRecom(Map<String,Integer> dictPatient){
        log.debug("heallingRecom [1]: start working");
        List<String> result = new ArrayList<>();

        try{
            if(dictPatient.get(Const.RESULT_HEALTH)<85){
                for(String i : dictPatient.keySet()){
                    if(dictPatient.get(i) == 0){
                        switch (i){
                            case Const.RESULT_CELLS_BLOOD -> {result.add(Const.RESULT_SYSTEM_CELLS_BLOOD);}
                            case Const.RESULT_HEMOGLOBIN -> {result.add(Const.RESULT_SYSTEM_HEMOGLOBIN);}
                            case Const.RESULT_PLATELETS -> {result.add(Const.RESULT_SYSTEM_PLATELET);}
                            case Const.RESULT_GLUCOSE -> {result.add(Const.RESULT_SYSTEM_GLUCOSE);}
                            case Const.RESULT_CHOLESTEROL -> {result.add(Const.RESULT_SYSTEM_CHOLESTEROL);}
                        }
                    }
                }
            }else {result.add(Const.RESULT_FULL_HEALTH);}
        }catch (Exception e){
            log.error("heallingRecom [2]: Error {}",e.getMessage());
        }
        log.debug("heallingRecom [3]: end working");
        return result;
    }


    /**
     * Основной вариант использования, при котором происходит формирование даты приезда врача на дом.
     * Метод heallingRecom - extend;
     * assessmentHealth() - include
     * determinationUrgency() - include
     * @param patient Объект пациента
     * @param dataProvider Дата провайдер
     * @return StatusAnswer
     */
    public StatusAnswer arivialDoctor(Patient patient,IDataProvider dataProvider){
        log.debug("arivialDoctor [1]: start working");
        StatusAnswer result;
        File file = new File(Const.FILE_NAME_ARIVIAL_DOCTOR);

        Map<String,Integer> mapAssessmentHealth = assessmentHealth(patient);
        Map<Integer, List<Schedule>> mapDoctorIdAndSchedule = determinationUrgency(mapAssessmentHealth, dataProvider);

        try (FileOutputStream fos = new FileOutputStream(file, true);
             OutputStreamWriter osw = new OutputStreamWriter(fos,Const.UNICODE_RUS);
             BufferedWriter writer = new BufferedWriter(osw)){

            if(mapAssessmentHealth.get(Const.RESULT_HEALTH)<60 && patient.getStatusVisit().equals(StatusPatient.OUT)){

                for (var value : mapDoctorIdAndSchedule.entrySet() ) {
                    writer.write(value.getKey() + "===>" + value.getValue()+"\n");
                }

                for (String value :  heallingRecom(mapAssessmentHealth)) {
                    writer.write(value+"\n");
                }
                writer.write(Const.FILE_DELIMITER_VISIT_DOCTOR);

            }else {
                if(patient.getStatusVisit().equals(StatusPatient.IN)){
                    writer.write(Const.ARIVIAL_DOCTOR_ANSWER_FOR_IN_PATIENT);
                }
                writer.write(Const.FILE_DELIMITER_ARIVIAL_DOCTOR_STATUS_OK);
                for (var value : mapDoctorIdAndSchedule.entrySet() ) {
                    writer.write(value.getKey() +"===>"+ value.getValue()+"\n");
                }
                writer.write(Const.FILE_DELIMITER_VISIT_DOCTOR);
            }

            result = StatusAnswer.OK;
        }catch (Exception e){
            result = StatusAnswer.ERROR;
        }

        return result;

    }

    /**
     * Метод определяет свободные окна у врача.
     * Выделяет сразу самых основных если здоровье меньше 60%
     * Или дает на выбор всех свободных врачей если больше 60.
     *
     * @param mapAssessmentHealth -Словарь с данными анализов пациента valur ={0-отличе от нормы и 1 - норма} а ключи это название анализов
     * @param dataProvider параметр IDataProvider для работы с истониками данных
     * @return result
     */
    protected Map<Integer,List<Schedule>> determinationUrgency(Map<String,Integer> mapAssessmentHealth,IDataProvider dataProvider){

        log.debug("determinationUrgency [1]: start working");
        Map<Integer,List<Schedule>> result = new HashMap<>();
        List<Doctor> ListGemotologDoctor;
        List<Doctor> ListEndocriologDoctor;
        List<Doctor> ListLiptologDoctor;

        try {
            if(mapAssessmentHealth.get(Const.RESULT_HEALTH) < 60){
                ListGemotologDoctor = ServisUtil.generateListDoctor(dataProvider,mapAssessmentHealth,Const.RESULT_CELLS_BLOOD,Const.DOCTOR_TYPE_GEMOTOLOG);
                ListLiptologDoctor = ServisUtil.generateListDoctor(dataProvider,mapAssessmentHealth,Const.RESULT_HEMOGLOBIN,Const.DOCTOR_TYPE_LIPIDOLOG);
                ListEndocriologDoctor = ServisUtil.generateListDoctor(dataProvider,mapAssessmentHealth,Const.RESULT_GLUCOSE,Const.DOCTOR_TYPE_ENDOCRINOLOG);

                ListGemotologDoctor.forEach(
                        i-> result.put(i.getId(),ServisUtil.generateListScheduleFree(i)));
                ListLiptologDoctor.forEach(
                        i-> result.put(i.getId(),ServisUtil.generateListScheduleFree(i)));
                ListEndocriologDoctor.forEach(
                        i-> result.put(i.getId(),ServisUtil.generateListScheduleFree(i)));

            }
            else {
                List<Doctor> ListDoctor = dataProvider.selectAllDoctor().get();
                ListDoctor.forEach(
                        i-> result.put(i.getId(),ServisUtil.generateListScheduleFree(i)));
            }
        }catch (Exception e){
            log.error("determinationUrgency [2]: end {}",e.getMessage());
        }
        log.debug("determinationUrgency [3]: end working");
        return result;
    }


    /**
     * Метод для формирования коэффициента цены врача
     * @param doctor - Объект врача
     * @return Double
     */
    protected Double calcExpDoctor(Doctor doctor){
        log.debug("calcExpDoctor [1]: start working");
        Double cof = Const.DOCTOR_PRIORITY.contains(doctor.getSpecialization()) ? 5.0 :0.0 ;
        try {
            Integer experience = doctor.getExperience();
            Double avgPatient = doctor.getAvgPatient();
            cof += (experience + avgPatient/2)/2;
        }catch (Exception e){
            log.error("calcExpDoctor [2]: error {}",e.getMessage());
        }
        log.debug("calcExpDoctor [3]: end working");
        return cof;

    }

    /**
     * Метод который формирует объект CalcReport который и будет загружен в базу
     * @param doctor Врач
     * @param patient пациент
     * @return Optional<CalcReport>
     */
    protected Optional<CalcReport> TotalReport(Doctor doctor,Patient patient){
        log.debug("TotalReport [1]: start working");
        CalcReport calcReport = new CalcReport();
        try {
            calcReport.setFioPatient(patient.getFio());
            calcReport.setFioDoctor(doctor.getFio());

            calcReport.setGlucoseAnalysis(patient.getGlucose() != null);
            calcReport.setBloodAnalysis(patient.getCellsBlood() != null);
            calcReport.setArterialAnalysis(patient.getPlatelets() != null);

            calcReport.setPatient(List.of(patient));
            calcReport.setDoctor(List.of(doctor));
        }catch (Exception e){
            log.error("TotalReport [2]: error {}",e.getMessage());
        }
        log.debug("TotalReport [3]: end working");
        return Optional.of(calcReport);

    }


    /**
     * Метод формирует текстовый файл со всей информацией и цене
     *
     * @param patient - Пациент
     * @param doctor - врач
     * @param flag - Создавать файл или нет
     * @return Optional<CalcReport>
     */
    public Optional<CalcReport> calculatePrice(Patient patient,Doctor doctor,Boolean flag){
        log.debug("calculatePrice [1]: start working");
        Double price = Const.DOCTOR_COF_SALARY;
        Double cof = calcExpDoctor(doctor);
        CalcReport calcReport = null;
        File file = new File(Const.FILE_NAME_CALCULATE_PRICE);
        try( FileOutputStream fos = new FileOutputStream(file, true);
             OutputStreamWriter osw = new OutputStreamWriter(fos,Const.UNICODE_RUS);
             BufferedWriter writer = new BufferedWriter(osw)) {
            calcReport = TotalReport(doctor,patient).get();
            calcReport.setPrice(Math.floor(price*cof));
            if(flag){writer.write(calcReport+"\n");}

        }catch (Exception e){
            log.error("calculatePrice [2]: {}",e.getMessage());
        }
        log.debug("calculatePrice [3]: end working");
        return Optional.of(calcReport);
    }

}
