package ru.sfedu.assessmentHealth.api;

import ru.sfedu.assessmentHealth.model.*;

import java.util.Optional;

public interface IDataProvider {
    //-------------------------insert--------------------------------------------

    /**
     * @param obj - Экземпляр класса Doctor
     * @return StatusAnswer(OK/ERROR)
     * @see StatusAnswer
     */
    StatusAnswer insertDoctor(Doctor obj);

    /**
     * @param obj - Экземпляр класса Patient
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    StatusAnswer insertPatient(Patient obj);

    /**
     * @param obj - Экземпляр класса Preparation
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    StatusAnswer insertPreparation(Preparation obj);

    /**
     * @param obj - Экземпляр класса Schedule
     * @return StatusAnswer(OK / ERROR)
     * Дата в формате YYYY-MM-DD
     * время начала  в формате HH-MM-SS
     * время конца в формате HH-MM-SS
     * @see StatusAnswer
     */
    StatusAnswer insertSchedule(Schedule obj);

    /**
     * @param obj - Экземпляр класса CalcReport
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    StatusAnswer insertCalcReport(CalcReport obj);

//-------------------------insert end--------------------------------------------

//-------------------------select--------------------------------------------

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Doctor>
     */
    Optional<Doctor> selectDoctorId(Integer id);

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Patient>
     */
    Optional<Patient> selectPatientId(Integer id);

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Schedule>
     */
    Optional<Schedule> selectScheduleId(Integer id);

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Preparation>
     */
    Optional<Preparation> selectPreparationId(Integer id);

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<CalcReport>
     */
    Optional<CalcReport> selectCalcReport(Integer id);

//-------------------------select end--------------------------------------------


//-------------------------delete-------------------------------------------
    /**
     * @param id который нужно удалить Врача
     * @return StatusAnswer
     */
    StatusAnswer deleteDoctor(Integer id);

//-------------------------delete end--------------------------------------------



}
