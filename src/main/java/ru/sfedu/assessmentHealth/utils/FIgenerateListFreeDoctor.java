package ru.sfedu.assessmentHealth.utils;


import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.Schedule;
import ru.sfedu.assessmentHealth.model.StatusSchedule;

import java.util.List;

/**
 * Тестовая штука
 * возвращает List<Schedule>
 */
@FunctionalInterface
public interface FIgenerateListFreeDoctor {
    void generateList(Doctor doctors);

    default List<Schedule> generateListDefult(Doctor doctors){
        return doctors.getLinkSchedule()
                .stream()
                .filter(j -> j.getStatSchedule().equals(StatusSchedule.FREE))
                .toList();
    }
}
