package ru.sfedu.assessmentHealth.api;

import ru.sfedu.assessmentHealth.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface DataCommandCrud {
    StatusMethod InsertDataDoctor(Doctor obj);

    StatusMethod InsertDataPatient(Patient obj);

    StatusMethod InsertDataPreparation(Preparation obj, Integer FkPreparationToDoctor, String dateEnd);

    StatusMethod InsertDataSchedule(Schedule obj, Integer FkScheduleToDoctor,String dateSchedule, String timeBegin, String timeEnd);

    StatusMethod InsertDataCalcReport(CalcReport obj);

    Map<String,Object> selectDoctorId(Integer Doctor_Id);

    Map<String,Object> selectPatientId(Integer Patient_Id);

    Map<String,Object> selectPreparationId(Integer Preparation_Id);

    Map<String,Object> selectScheduleId(Integer Schedule_Id);

    Map<String,Object> selectCalcReportId(Integer CalcReport_Id);

    StatusMethod deleteDataDoctor(Integer Doctor_ID);

    StatusMethod deleteDataPatient(Integer Patient_ID);

    StatusMethod deleteDataPreparation(Integer Preparation_ID);

    StatusMethod deleteDataSchedule(Integer Schedule_ID);

    StatusMethod deleteDataCalcReport(Integer CalcReport_ID);
}
