package ru.sfedu.assessmentHealth.api;

import ru.sfedu.assessmentHealth.model.*;

public interface DataCommandCrud {
    void InsertDataDoctor(Doctor obj);

    void InsertDataPatient(Patient obj);

    void InsertDataPreparation(Preparation obj, Integer FkPreparationToDoctor, String dateEnd);

    void InsertDataSchedule(Schedule obj, String dateSchedule, String timeBegin, String timeEnd);

    void InsertDataCalcReport(CalcReport obj);
}
