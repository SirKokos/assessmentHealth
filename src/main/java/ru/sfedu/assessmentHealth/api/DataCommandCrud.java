package ru.sfedu.assessmentHealth.api;

import ru.sfedu.assessmentHealth.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface DataCommandCrud {
    void InsertDataDoctor(Doctor obj);

    void InsertDataPatient(Patient obj);

    void InsertDataPreparation(Preparation obj, Integer FkPreparationToDoctor, String dateEnd);

    void InsertDataSchedule(Schedule obj, String dateSchedule, String timeBegin, String timeEnd);

    void InsertDataCalcReport(CalcReport obj);

    Map<String,Object> selectDoctorId(Integer Doctor_Id);

    Map<String,Object> selectPatientId(Integer Patient_Id);

    Map<String,Object> selectPreparationId(Integer Preparation_Id);

    Map<String,Object> selectScheduleId(Integer Schedule_Id);

    Map<String,Object> selectCalcReportId(Integer CalcReport_Id);

    void deleteDataDoctor(Integer Doctor_ID);

    void deleteDataPatient(Integer Patient_ID);

    void deleteDataPreparation(Integer Preparation_ID);

    void deleteDataSchedule(Integer Schedule_ID);

    void deleteDataCalcReport(Integer CalcReport_ID);
}
