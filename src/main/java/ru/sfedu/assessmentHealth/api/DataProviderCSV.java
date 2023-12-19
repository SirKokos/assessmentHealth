package ru.sfedu.assessmentHealth.api;
import com.opencsv.CSVReader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.*;

import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.sfedu.assessmentHealth.utils.CsvUtil.getObjectFkId;
import static ru.sfedu.assessmentHealth.utils.CsvUtil.getObjectLastIdCsv;
import static ru.sfedu.assessmentHealth.utils.FileUtil.createFileIfNotExists;

public class DataProviderCSV {
    private static final Logger log = LogManager.getLogger(DataProviderCSV.class.getName());

    final private String calcReportPath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_CALC_REPORT).concat(CONST.CSV_FILE_TYPE);
    final private String doctorPath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_DOCTOR).concat(CONST.CSV_FILE_TYPE);
    final private String patientPath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_PATIENT).concat(CONST.CSV_FILE_TYPE);
    final private String preparationPath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_PREPARATION).concat(CONST.CSV_FILE_TYPE);
    final private String schedulePath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_SCHEDULE).concat(CONST.CSV_FILE_TYPE);


    DataProviderCSV() throws IOException {
        createFileIfNotExists(calcReportPath);
        createFileIfNotExists(doctorPath);
        createFileIfNotExists(patientPath);
        createFileIfNotExists(preparationPath);
        createFileIfNotExists(schedulePath);
    }



    public void test(){

    }

    public StatusMethod InsertDataDoctor(Doctor obj) throws FileNotFoundException {
        log.debug("InsertDataDoctor [1]: - Start method working insert csv");
        StatefulBeanToCsv beanToCsv;
        Writer writer;
        StatusMethod result = StatusMethod.ERROR;
        int report_id;
        try {
            writer = new FileWriter(doctorPath,true);
            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            report_id = getObjectLastIdCsv(doctorPath,CONST.POSITION_PERSON_ID) + 1;
            obj.setPerson_ID(report_id);
            beanToCsv.write(obj);
            writer.close();
            result = StatusMethod.OK;
        } catch (IOException |CsvRequiredFieldEmptyException|CsvDataTypeMismatchException e) {
            log.debug("InsertDataDoctor [2]: - ERROR result = {}, error = {}",result,e.getMessage());
        }
        log.debug("InsertDataDoctor [3]: - Start method working END");
        return result;
    }


    public StatusMethod InsertDataPatient(Patient obj) {
        log.debug("InsertDataPatient [1]: - Start method working insert csv");
        StatefulBeanToCsv beanToCsv = null;
        Writer writer;
        StatusMethod result = StatusMethod.ERROR;
        int report_id;
        try {
            writer = new FileWriter(patientPath,true);
            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            report_id = getObjectLastIdCsv(patientPath,CONST.POSITION_PATIENT_ID) + 1;
            obj.setPerson_ID(report_id);
            beanToCsv.write(obj);
            writer.close();
            result = StatusMethod.OK;
        } catch (IOException |CsvRequiredFieldEmptyException|CsvDataTypeMismatchException e) {
            log.debug("InsertDataPatient [2]: - ERROR result = {}, error = {}",result,e.getMessage());
        }
        log.debug("InsertDataPatient [3]: - Start method working END");

        return result;
    }


    public StatusMethod InsertDataPreparation(Preparation obj, Integer FkPreparationToDoctor, String dateEnd) {
        log.debug("InsertDataPreparation [1]: - Start method working insert csv");
        StatefulBeanToCsv beanToCsv;
        Writer writer;
        StatusMethod result = StatusMethod.ERROR;
        int preparation_Id;
        int fk_doctor = getObjectFkId(doctorPath,FkPreparationToDoctor);
        try {
            if(fk_doctor > 0){
                log.debug("InsertDataPreparation [2]: - FK succeeded search");
                obj.setFkPreparationToDoctor(FkPreparationToDoctor);
            }else {
                log.warn("InsertDataPreparation [3]: - FK ERROR search");
                return result;
            }
            obj.setDateEnd(Date.valueOf(dateEnd));
            writer = new FileWriter(preparationPath,true);
            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            preparation_Id = getObjectLastIdCsv(preparationPath,CONST.POSITION_PREPARATION_ID) + 1;
            obj.setPreparation_ID(preparation_Id);
            beanToCsv.write(obj);
            writer.close();
            result = StatusMethod.OK;
        } catch (IOException |CsvRequiredFieldEmptyException|CsvDataTypeMismatchException e) {
            log.debug("InsertDataPreparation [2]: - ERROR result = {}, error = {}",result,e.getMessage());
        }
        log.debug("InsertDataPreparation [3]: - Start method working END");
        return result;
    }


    public StatusMethod InsertDataSchedule(Schedule obj, Integer FkScheduleToDoctor,String dateSchedule, String timeBegin, String timeEnd) {
        log.debug("InsertDataSchedule [1]: - Start method working insert csv");
        StatefulBeanToCsv beanToCsv;
        Writer writer;
        StatusMethod result = StatusMethod.ERROR;
        int schedule_id;
        int fk_doctor = getObjectFkId(doctorPath,FkScheduleToDoctor);
        try {
            if(fk_doctor > 0){
                log.debug("InsertDataSchedule [2]: - FK succeeded search");
                obj.setFkToDoctor(FkScheduleToDoctor);
            }else {
                log.warn("InsertDataSchedule [3]: - FK ERROR search");
                return result;
            }
            obj.setDateSchedule(Date.valueOf(dateSchedule));
            obj.setTimeBegin(Time.valueOf(timeBegin));
            obj.setTimeEnd(Time.valueOf(timeEnd));
            writer = new FileWriter(schedulePath,true);
            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            schedule_id = getObjectLastIdCsv(schedulePath,CONST.POSITION_SCHEDULE_ID) + 1;
            obj.setScheduleID(schedule_id);
            beanToCsv.write(obj);
            writer.close();
            result = StatusMethod.OK;
        } catch (IOException |CsvRequiredFieldEmptyException|CsvDataTypeMismatchException e) {
            log.debug("InsertDataSchedule [4]: - ERROR result = {}, error = {}",result,e.getMessage());
        }
        log.debug("InsertDataSchedule [5]: - Start method working END");

        return result;
    }


    public StatusMethod InsertDataCalcReport(CalcReport obj) {
        log.debug("InsertDataCalcReport [1]: - Start method working insert csv");
        StatefulBeanToCsv beanToCsv = null;
        Writer writer;
        StatusMethod result = StatusMethod.ERROR;
        int report_id;
        try {
            writer = new FileWriter(calcReportPath,true);
            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            report_id = getObjectLastIdCsv(calcReportPath,CONST.POSITION_REPORT_ID) + 1;
            obj.setReport_ID(report_id);
            beanToCsv.write(obj);
            writer.close();
            result = StatusMethod.OK;
        } catch (IOException |CsvRequiredFieldEmptyException|CsvDataTypeMismatchException e) {
            log.debug("InsertDataCalcReport [2]: - ERROR result = {}, error = {}",result,e.getMessage());
        }
        log.debug("InsertDataCalcReport [3]: - Start method working END");
        return result;
    }


    public Map<String, Object> selectDoctorId(Integer Doctor_Id) {
        log.debug("selectDoctorId [1]: start working method");
        List<List<String>> getListDoctor = generateListFromCsv(doctorPath);
        Map<String,Object> result = new HashMap<>();
        try {
            List<String> filterDoctorList = getListDoctor
                    .stream()
                    .filter(iter -> Integer.parseInt(iter.get(0)) == Doctor_Id)
                    .sorted()
                    .toList().get(0);

            result.put(CONST.KEY_PERSON, Integer.parseInt(filterDoctorList.get(0)));
            result.put(CONST.NAME_FIELD_NAME, filterDoctorList.get(1));
            result.put(CONST.NAME_FIELD_SURNAME, filterDoctorList.get(2));
            result.put(CONST.NAME_FIELD_SECONDNAME, filterDoctorList.get(3));
            result.put(CONST.NAME_FIELD_AGE, Integer.parseInt(filterDoctorList.get(4)));
            result.put(CONST.NAME_FIELD_GENDER, String.valueOf(filterDoctorList.get(5)));
            result.put(CONST.NAME_FIELD_STATUS, filterDoctorList.get(6));
            result.put(CONST.NAME_FIELD_DOCTOR_EXPERIENCE,Integer.parseInt(filterDoctorList.get(7)));
            result.put(CONST.NAME_FIELD_DOCTOR_AVGPATIENT, Double.parseDouble(filterDoctorList.get(8)));
            result.put(CONST.NAME_FIELD_DOCTOR_QUALIFICATION, (filterDoctorList.get(9)));
            result.put(CONST.NAME_FIELD_DOCTOR_SPECIALIZATION, (filterDoctorList.get(10)));
        }catch (Exception e){
            log.debug("selectDoctorId [2]: ERROR {}",e.getMessage());
        }
        log.debug("selectDoctorId [3]: end working ");

        return result;
    }


    public Map<String, Object> selectPatientId(Integer Patient_Id) {

        log.debug("selectPatientId [1]: start working method");
        List<List<String>> getListDoctor = generateListFromCsv(patientPath);
        Map<String,Object> result = new HashMap<>();
        try {
            List<String> filterDoctorList = getListDoctor
                    .stream()
                    .filter(iter -> Integer.parseInt(iter.get(0)) == Patient_Id)
                    .sorted()
                    .toList().get(0);

            result.put(CONST.KEY_PERSON, Integer.parseInt(filterDoctorList.get(0)));
            result.put(CONST.NAME_FIELD_NAME, filterDoctorList.get(1));
            result.put(CONST.NAME_FIELD_SURNAME, filterDoctorList.get(2));
            result.put(CONST.NAME_FIELD_SECONDNAME, filterDoctorList.get(3));
            result.put(CONST.NAME_FIELD_AGE, Integer.parseInt(filterDoctorList.get(4)));
            result.put(CONST.NAME_FIELD_GENDER, String.valueOf(filterDoctorList.get(5)));
            result.put(CONST.NAME_FIELD_STATUS, filterDoctorList.get(6));

            result.put(CONST.NAME_FIELD_PATIENT_CELLS_BLOOD,Double.parseDouble(filterDoctorList.get(7)));
            result.put(CONST.NAME_FIELD_PATIENT_HEMOGLOBIN, Double.parseDouble(filterDoctorList.get(8)));
            result.put(CONST.NAME_FIELD_PATIENT_PLATELETS, Double.parseDouble(filterDoctorList.get(9)));
            result.put(CONST.NAME_FIELD_PATIENT_TESTOSTERONE,Double.parseDouble(filterDoctorList.get(10)));
            result.put(CONST.NAME_FIELD_PATIENT_GLUCOSE,Double.parseDouble(filterDoctorList.get(11)));
            result.put(CONST.NAME_FIELD_PATIENT_CHOLESTEROL,Double.parseDouble(filterDoctorList.get(12)));
            result.put(CONST.NAME_FIELD_PATIENT_ARTERIAL_PRESS,Short.parseShort(filterDoctorList.get(13)));
            result.put(CONST.NAME_FIELD_PATIENT_STATUS_VISIT,(filterDoctorList.get(14)));


        }catch (Exception e){
            log.debug("selectPatientId [2]: ERROR {}",e.getMessage());
        }
        log.debug("selectPatientId [3]: end working ");

        return result;

    }


    public Map<String, Object> selectPreparationId(Integer Preparation_Id) {
        log.debug("selectPreparationId [1]: start working method");
        List<List<String>> getListDoctor = generateListFromCsv(preparationPath);
        Map<String,Object> result = new HashMap<>();
        try {
            List<String> filterDoctorList = getListDoctor
                    .stream()
                    .filter(iter -> Integer.parseInt(iter.get(0)) == Preparation_Id)
                    .sorted()
                    .toList().get(0);

            result.put(CONST.NAME_FIELD_PREPARATION_ID, Integer.parseInt(filterDoctorList.get(0)));
            result.put(CONST.NAME_FIELD_FK_PREPARATION_TO_DOCTOR, Integer.parseInt(filterDoctorList.get(1)));
            result.put(CONST.NAME_FIELD_PREPARATION_NAME_PREP, filterDoctorList.get(2));
            result.put(CONST.NAME_FIELD_PREPARATION_DAE_END,Date.valueOf(filterDoctorList.get(3)));
            result.put(CONST.NAME_FIELD_PREPARATION_DOSAGE, Double.parseDouble(filterDoctorList.get(4)));
            result.put(CONST.NAME_FIELD_PREPARATION_STATUS_VISIT_PREPARATION, (filterDoctorList.get(5)));
            result.put(CONST.NAME_FIELD_PREPARATION_ABOUT_PREP, filterDoctorList.get(6));
            result.put(CONST.NAME_FIELD_PREPARATION_COUNT_PREP, Integer.parseInt(filterDoctorList.get(7)));

        }catch (Exception e){
            log.debug("selectPreparationId [2]: ERROR {}",e.getMessage());
        }
        log.debug("selectPreparationId [3]: end working ");

        return result;
    }


    public Map<String, Object> selectScheduleId(Integer Schedule_Id) {
        log.debug("selectScheduleId [1]: start working method");
        List<List<String>> getListDoctor = generateListFromCsv(schedulePath);
        Map<String,Object> result = new HashMap<>();
        try {
            List<String> filterDoctorList = getListDoctor
                    .stream()
                    .filter(iter -> Integer.parseInt(iter.get(0)) == Schedule_Id)
                    .sorted()
                    .toList().get(0);

            result.put(CONST.NAME_FIELD_SCHEDULE_ID, Integer.parseInt(filterDoctorList.get(0)));
            result.put(CONST.NAME_FIELD_SCHEDULE_FK_TO_DOCTOR, Integer.parseInt(filterDoctorList.get(1)));
            result.put(CONST.NAME_FIELD_SCHEDULE_DATE_WEEK, filterDoctorList.get(2));
            result.put(CONST.NAME_FIELD_SCHEDULE_DATE_SCHEDULE,Date.valueOf(filterDoctorList.get(3)));
            result.put(CONST.NAME_FIELD_SCHEDULE_TIME_BEGIN, Time.valueOf(filterDoctorList.get(4)));
            result.put(CONST.NAME_FIELD_SCHEDULE_TIME_END, Time.valueOf(filterDoctorList.get(5)));
           result.put(CONST.NAME_FIELD_SCHEDULE_STATUS_SCHEDULE,(filterDoctorList.get(6)));

        }catch (Exception e){
            log.debug("selectScheduleId [2]: ERROR {}",e.getMessage());
        }
        log.debug("selectScheduleId [3]: end working ");

        return result;
    }


    public Map<String, Object> selectCalcReportId(Integer CalcReport_Id) {
        log.debug("selectCalcReportId [1]: start working method");
        List<List<String>> getListDoctor = generateListFromCsv(calcReportPath);
        Map<String,Object> result = new HashMap<>();
        try {
            List<String> filterDoctorList = getListDoctor
                    .stream()
                    .filter(iter -> Integer.parseInt(iter.get(0)) == CalcReport_Id)
                    .sorted()
                    .toList().get(0);

            result.put(CONST.NAME_FIELD_REPORT_ID, Integer.parseInt(filterDoctorList.get(0)));
            result.put(CONST.NAME_FIELD_REPORT_FK_TO_DOCTOR, Integer.parseInt(filterDoctorList.get(1)));
            result.put(CONST.NAME_FIELD_REPORT_FK_TO_PATIENT, Integer.parseInt(filterDoctorList.get(2)));
            result.put(CONST.NAME_FIELD_REPORT_NAME_PATIENT,(filterDoctorList.get(3)));
            result.put(CONST.NAME_FIELD_REPORT_NAME_DOCTOR, (filterDoctorList.get(4)));
            result.put(CONST.NAME_FIELD_REPORT_BLOOD_ANALYSE, Boolean.valueOf(filterDoctorList.get(5)));
            result.put(CONST.NAME_FIELD_REPORT_GLUCOSE_ANALYSE,Boolean.valueOf(filterDoctorList.get(6)));
            result.put(CONST.NAME_FIELD_REPORT_HORMONE_ANALYSE,Boolean.valueOf(filterDoctorList.get(7)));
            result.put(CONST.NAME_FIELD_REPORT_ARTERIAL_ANALYSE,Boolean.valueOf(filterDoctorList.get(8)));
            result.put(CONST.NAME_FIELD_REPORT_PRICE,Double.parseDouble(filterDoctorList.get(9)));

        }catch (Exception e){
            log.debug("selectCalcReportId [2]: ERROR {}",e.getMessage());
        }
        log.debug("selectCalcReportId [3]: end working ");

        return result;
    }


    public void deleteDataDoctor(Integer Doctor_ID) {

    }


    public void deleteDataPatient(Integer Patient_ID) {

    }


    public void deleteDataPreparation(Integer Preparation_ID) {

    }


    public void deleteDataSchedule(Integer Schedule_ID) {

    }


    public void deleteDataCalcReport(Integer CalcReport_ID) {

    }


//    protected Map<String,Object> getDoctorCSV(Integer doctorId){
//        log.debug("getDoctorCSV [1]: start working method");
//        List<List<String>> getListDoctor = generateListFromCsv(doctorPath);
//        Map<String,Object> result = new HashMap<>();
//        try {
//            List<String> filterDoctorList = getListDoctor
//                    .stream()
//                    .filter(iter -> Integer.parseInt(iter.get(0)) == doctorId)
//                    .sorted()
//                    .toList().get(0);
//
//            result.put(CONST.KEY_PERSON, Integer.parseInt(filterDoctorList.get(0)));
//            result.put(CONST.NAME_FIELD_NAME, filterDoctorList.get(1));
//            result.put(CONST.NAME_FIELD_SURNAME, filterDoctorList.get(2));
//            result.put(CONST.NAME_FIELD_SECONDNAME, filterDoctorList.get(3));
//            result.put(CONST.NAME_FIELD_AGE, Integer.parseInt(filterDoctorList.get(4)));
//            result.put(CONST.NAME_FIELD_GENDER, String.valueOf(filterDoctorList.get(5)));
//            result.put(CONST.NAME_FIELD_STATUS, filterDoctorList.get(6));
//            result.put(CONST.NAME_FIELD_DOCTOR_EXPERIENCE,Integer.parseInt(filterDoctorList.get(7)));
//            result.put(CONST.NAME_FIELD_DOCTOR_AVGPATIENT, Double.parseDouble(filterDoctorList.get(8)));
//            result.put(CONST.NAME_FIELD_DOCTOR_QUALIFICATION, (filterDoctorList.get(9)));
//            result.put(CONST.NAME_FIELD_DOCTOR_SPECIALIZATION, (filterDoctorList.get(10)));
//        }catch (Exception e){
//            log.debug("getDoctorCSV [2]: ERROR {}",e.getMessage());
//        }
//        log.debug("getDoctorCSV [3]: end working ");
//
//       return result;
//    }

    protected List<List<String>> generateListFromCsv(String filePath){
        log.debug("generateListFromCsv [1]: - process start ...");
        FileReader fileReader;
        CSVReader reader;
        List<List<String>> resultList = null;
        try {
            fileReader = new FileReader(filePath);
            reader = new CSVReader(fileReader);

            resultList = reader.readAll()
                    .stream()
                    .map(iter -> {
                        return Arrays.stream(iter).toList();
                    }).toList();
        } catch (IOException | CsvException e) {
            log.error("generateListFromCsv [2]: Error {}",e.getMessage());
        }
        log.debug("generateListFromCsv [3]: End working {}",resultList);
        return resultList;
    }

}
