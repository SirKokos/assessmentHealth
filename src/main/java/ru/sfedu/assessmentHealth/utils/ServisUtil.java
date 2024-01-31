package ru.sfedu.assessmentHealth.utils;

import org.apache.commons.collections.functors.PredicateTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.api.IDataProvider;
import ru.sfedu.assessmentHealth.api.Servis;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.Preparation;
import ru.sfedu.assessmentHealth.model.Schedule;
import ru.sfedu.assessmentHealth.model.StatusSchedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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





}
