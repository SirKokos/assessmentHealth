package ru.sfedu.assessmentHealth.utils;

import org.apache.commons.collections.functors.PredicateTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.api.IDataProvider;
import ru.sfedu.assessmentHealth.api.Servis;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.Schedule;
import ru.sfedu.assessmentHealth.model.StatusSchedule;

import java.util.ArrayList;
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


}
