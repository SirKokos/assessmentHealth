package ru.sfedu.assessmentHealth.api;

import com.opencsv.bean.StatefulBeanToCsv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.XmlUtil;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static ru.sfedu.assessmentHealth.utils.FileUtil.createFileIfNotExists;
import static ru.sfedu.assessmentHealth.utils.XmlUtil.getXmlWrapper;

public class DataProviderXML {
    private static final Logger log = LogManager.getLogger(DataProviderXML.class.getName());

    final private String calcReportPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_CALC_REPORT).concat(CONST.XML_FILE_TYPE);
    final private String doctorPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_DOCTOR).concat(CONST.XML_FILE_TYPE);
    final private String patientPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_PATIENT).concat(CONST.XML_FILE_TYPE);
    final private String preparationPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_PREPARATION).concat(CONST.XML_FILE_TYPE);
    final private String schedulePath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_SCHEDULE).concat(CONST.XML_FILE_TYPE);



    DataProviderXML() throws IOException {
        createFileIfNotExists(calcReportPath);
        createFileIfNotExists(doctorPath);
        createFileIfNotExists(patientPath);
        createFileIfNotExists(preparationPath);
        createFileIfNotExists(schedulePath);
    }
    
    protected Integer getId(List<Integer> listId){
        return Collections.max(listId)+1;
    }

    public StatusMethod InsertDataDoctor(Doctor obj) {
        log.debug("InsertDataDoctor [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<Doctor> ListDoctor;
        XmlWrapper<Doctor> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(doctorPath);
        Serializer serializer = new Persister();
        List<Integer> id;
        obj.setPerson_ID(1);
        if(xmlFile.length() != 0){
            try {
                ListDoctor = getXmlWrapper(doctorPath).getListObjXml();

                id = ListDoctor.stream()
                        .map(iter-> iter.getPersonID())
                        .toList();
                obj.setPerson_ID(getId(id));
                ListDoctor.add(obj);
                dataContainer.setListObjXml(ListDoctor);

                serializer.write(dataContainer, xmlFile);
                result = StatusMethod.OK;
            }catch (Exception e){
                log.debug("insertDataDoctor [2] - error {}",e.getMessage());
            }
        }else {
            try {
                dataContainer.setListObjXml(List.of(obj));
                serializer.write(dataContainer, xmlFile);
                result = StatusMethod.OK;
            } catch (Exception e) {
                log.error("insertDataDoctor [3] - error {}",e.getMessage());
            }
        }
        log.debug("InsertDataDoctor [4]: - END method working insert xml");
        return result;
    }


    public StatusMethod InsertDataPatient(Patient obj) {
        log.debug("InsertDataPatient [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<Patient> ListPatient;
        XmlWrapper<Patient> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(patientPath);
        Serializer serializer = new Persister();
        List<Integer> id;
        obj.setPerson_ID(1);
        if(xmlFile.length() != 0){
            try {
                ListPatient = getXmlWrapper(patientPath).getListObjXml();

                id = ListPatient.stream()
                        .map(iter-> iter.getPersonID())
                        .toList();
                obj.setPerson_ID(getId(id));
                ListPatient.add(obj);
                dataContainer.setListObjXml(ListPatient);

                serializer.write(dataContainer, xmlFile);
                result = StatusMethod.OK;
            }catch (Exception e){
                log.debug("InsertDataPatient [2] - error {}",e.getMessage());
            }
        }else {
            try {
                dataContainer.setListObjXml(List.of(obj));
                serializer.write(dataContainer, xmlFile);
                result = StatusMethod.OK;
            } catch (Exception e) {
                log.error("InsertDataPatient [3] - error {}",e.getMessage());
            }
        }
        log.debug("InsertDataPatient [4]: - END method working insert xml");
        return result;
    }


    public StatusMethod InsertDataPreparation(Preparation obj, Integer FkPreparationToDoctor, String dateEnd) {
        log.debug("InsertDataPatient [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<Preparation> ListPreparation;
        XmlWrapper<Preparation> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(preparationPath);
        Serializer serializer = new Persister();
        List<Integer> id;

        obj.setPreparation_ID(1);
        obj.setDateEnd(Date.valueOf(dateEnd));

        if(xmlFile.length() != 0){
            try {
                ListPreparation = getXmlWrapper(preparationPath).getListObjXml();

                id = ListPreparation.stream()
                        .map(iter-> iter.getPreparation_ID())
                        .toList();
                switch (XmlUtil.getObjectFkIdPerson(doctorPath,FkPreparationToDoctor)){
                    case 0 -> {return result;}
                    default -> {
                        obj.setPreparation_ID(getId(id));
                        ListPreparation.add(obj);
                        dataContainer.setListObjXml(ListPreparation);
                        obj.setFkPreparationToDoctor(FkPreparationToDoctor);
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                }
            }catch (Exception e){
                log.debug("InsertDataPatient [2] - error {}",e.getMessage());
            }
        }else {
            try {
                switch (XmlUtil.getObjectFkIdPerson(doctorPath,FkPreparationToDoctor)){
                    case 0 -> {return result;}
                    default -> {
                        dataContainer.setListObjXml(List.of(obj));
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                }
            } catch (Exception e) {
                log.error("InsertDataPatient [3] - error {}",e.getMessage());
            }
        }
        log.debug("InsertDataPreparation [4]: - END method working insert xml");

        return result;

    }


    public StatusMethod InsertDataSchedule(Schedule obj, Integer FkScheduleToDoctor,String dateSchedule, String timeBegin, String timeEnd) {
        log.debug("InsertDataSchedule [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<Schedule> ListSchedule;
        XmlWrapper<Schedule> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(schedulePath);
        Serializer serializer = new Persister();
        List<Integer> id;

        obj.setScheduleID(1);
        obj.setDateSchedule(Date.valueOf(dateSchedule));
        obj.setTimeBegin(Time.valueOf(timeBegin));
        obj.setTimeEnd(Time.valueOf(timeEnd));

        if(xmlFile.length() != 0){
            try {
                ListSchedule = getXmlWrapper(schedulePath).getListObjXml();

                id = ListSchedule.stream()
                        .map(iter-> iter.getScheduleID())
                        .toList();
                switch (XmlUtil.getObjectFkIdPerson(doctorPath,FkScheduleToDoctor)){
                    case 0 -> {return result;}
                    default -> {
                        obj.setScheduleID(getId(id));
                        ListSchedule.add(obj);
                        dataContainer.setListObjXml(ListSchedule);
                        obj.setFkToDoctor(FkScheduleToDoctor);
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                }
            }catch (Exception e){
                log.debug("InsertDataSchedule [2] - error {}",e.getMessage());
            }
        }else {
            try {
                switch (XmlUtil.getObjectFkIdPerson(doctorPath,FkScheduleToDoctor)){
                    case 0 -> {return result;}
                    default -> {
                        dataContainer.setListObjXml(List.of(obj));
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                };
            } catch (Exception e) {
                log.error("InsertDataSchedule [3] - error {}",e.getMessage());
            }
        }
        log.debug("InsertDataSchedule [4]: - END method working insert xml");

        return result;

    }


    public StatusMethod InsertDataCalcReport(CalcReport obj) {
        log.debug("InsertDataCalcReport [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<CalcReport> ListCalcReport;
        XmlWrapper<CalcReport> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(calcReportPath);
        Serializer serializer = new Persister();
        List<Integer> id;

        obj.setReport_ID(1);

        if(xmlFile.length() != 0){
            try {
                ListCalcReport = getXmlWrapper(calcReportPath).getListObjXml();

                id = ListCalcReport.stream()
                        .map(iter-> iter.getReport_ID())
                        .toList();

                if(XmlUtil.getObjectFkIdPerson(doctorPath,obj.getFkToDoctor()) == 0
                        ||
                        XmlUtil.getObjectFkIdPerson(patientPath,obj.getFkToDoctor()) == 0){
                    return result;
                }else {
                    obj.setReport_ID(getId(id));
                    ListCalcReport.add(obj);
                    dataContainer.setListObjXml(ListCalcReport);
                    serializer.write(dataContainer, xmlFile);
                    result = StatusMethod.OK;
                }
            }catch (Exception e){
                log.debug("InsertDataCalcReport [2] - error {}",e.getMessage());
            }
        }else {
            try {
                if(XmlUtil.getObjectFkIdPerson(doctorPath,obj.getFkToDoctor()) == 0
                        ||
                        XmlUtil.getObjectFkIdPerson(patientPath,obj.getFkToDoctor()) == 0){
                    return result;
                }else {
                    dataContainer.setListObjXml(List.of(obj));
                    serializer.write(dataContainer, xmlFile);
                    result = StatusMethod.OK;
                }

            } catch (Exception e) {
                log.error("InsertDataCalcReport [3] - error {}",e.getMessage());
            }
        }
        log.debug("InsertDataCalcReport [4]: - END method working insert xml");

        return result;

    }


    public Map<String, Object> selectDoctorId(Integer Doctor_Id) {
        return null;
    }


    public Map<String, Object> selectPatientId(Integer Patient_Id) {
        return null;
    }


    public Map<String, Object> selectPreparationId(Integer Preparation_Id) {
        return null;
    }


    public Map<String, Object> selectScheduleId(Integer Schedule_Id) {
        return null;
    }


    public Map<String, Object> selectCalcReportId(Integer CalcReport_Id) {
        return null;
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
}
