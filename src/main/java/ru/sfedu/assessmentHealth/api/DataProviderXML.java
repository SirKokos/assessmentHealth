package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.XmlUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

import static ru.sfedu.assessmentHealth.utils.FileUtil.createFileIfNotExists;
import static ru.sfedu.assessmentHealth.utils.XmlUtil.getXmlWrapper;

public class DataProviderXML implements DataCommandCrud{
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
    @Override
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

    @Override
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

    @Override
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
                        obj.setFkPreparationToDoctor(FkPreparationToDoctor);
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
    @Override
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

    @Override
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

    @Override
    public Map<String, Object> selectDoctorId(Integer Doctor_Id) {
        log.debug("selectDoctorId [1]: - Start method working select xml");
        Map<String,Object> result = new HashMap<>();
        List<Doctor> ListDoctor;
        HistoryContent historyContent = new HistoryContent();
        int index = -1;
        File xmlFile = new File(doctorPath);

        if(xmlFile.length() != 0) {
        try {
            ListDoctor = getXmlWrapper(doctorPath).getListObjXml();
            List<Doctor> ListDoctorFilterId = ListDoctor.stream()
                    .filter(iter-> iter.getPersonID().equals(Doctor_Id))
                    .toList();

            for (Doctor i : ListDoctor){
                index += 1;
                if( i.getPersonID().equals(ListDoctorFilterId.get(0).getPersonID()) ){
                    break;
                }
            }
            switch (index){
                case -1 -> {return null;}
                default -> {
                    historyContent.setObject(ListDoctorFilterId.get(0));
                    result = historyContent.getObject();
                    log.debug("selectDoctorId [2]: - success method work");
                }
            }
        }catch (Exception e){
            log.error("selectDoctorId [3]: - ERROR скоре всего нет такого ключа{}",e.getMessage());
        }


        }
        log.debug("selectDoctorId [4]: - END method working select xml");


        return result;
    }

    @Override
    public Map<String, Object> selectPatientId(Integer Patient_Id) {
        log.debug("selectPatientId [1]: - Start method working select xml");
        Map<String,Object> result = new HashMap<>();
        List<Patient> ListPatient;
        HistoryContent historyContent = new HistoryContent();
        int index = -1;
        File xmlFile = new File(patientPath);

        if(xmlFile.length() != 0) {
            try {
                ListPatient = getXmlWrapper(patientPath).getListObjXml();
                List<Patient> ListPatientFilterId = ListPatient.stream()
                        .filter(iter-> iter.getPersonID().equals(Patient_Id))
                        .toList();

                for (Patient i : ListPatient){
                    index += 1;
                    if( i.getPersonID().equals(ListPatientFilterId.get(0).getPersonID()) ){
                        break;
                    }
                }
                switch (index){
                    case -1 -> {return null;}
                    default -> {
                        historyContent.setObject(ListPatientFilterId.get(0));
                        result = historyContent.getObject();
                        log.debug("selectPatientId [2]: - success method work");
                    }
                }
            }catch (Exception e){
                log.error("selectPatientId [3]: - ERROR скоре всего нет такого ключа{}",e.getMessage());
            }
        }
        log.debug("selectPatientId [4]: - END method working select xml");


        return result;
    }

    @Override
    public Map<String, Object> selectPreparationId(Integer Preparation_Id) {
        log.debug("selectPreparationId [1]: - Start method working select xml");
        Map<String,Object> result = new HashMap<>();
        List<Preparation> ListPreparation;
        HistoryContent historyContent = new HistoryContent();
        int index = -1;
        File xmlFile = new File(preparationPath);

        if(xmlFile.length() != 0) {
            try {
                ListPreparation = getXmlWrapper(preparationPath).getListObjXml();
                List<Preparation> ListPatientFilterId = ListPreparation.stream()
                        .filter(iter-> iter.getPreparation_ID().equals(Preparation_Id))
                        .toList();

                for (Preparation i : ListPreparation){
                    index += 1;
                    if( i.getPreparation_ID().equals(ListPatientFilterId.get(0).getPreparation_ID()) ){
                        break;
                    }
                }
                switch (index){
                    case -1 -> {return null;}
                    default -> {
                        historyContent.setObject(ListPatientFilterId.get(0));
                        result = historyContent.getObject();
                        log.debug("selectPreparationId [2]: - success method work");
                    }
                }
            }catch (Exception e){
                log.error("selectPreparationId [3]: - ERROR скоре всего нет такого ключа{}",e.getMessage());
            }
        }
        log.debug("selectPreparationId [4]: - END method working select xml");


        return result;
    }

    @Override
    public Map<String, Object> selectScheduleId(Integer Schedule_Id) {
        log.debug("selectScheduleId [1]: - Start method working select xml");
        Map<String,Object> result = new HashMap<>();
        List<Schedule> ListSchedule;
        HistoryContent historyContent = new HistoryContent();
        int index = -1;
        File xmlFile = new File(schedulePath);

        if(xmlFile.length() != 0) {
            try {
                ListSchedule = getXmlWrapper(schedulePath).getListObjXml();
                List<Schedule> ListPatientFilterId = ListSchedule.stream()
                        .filter(iter-> iter.getScheduleID().equals(Schedule_Id))
                        .toList();

                for (Schedule i : ListSchedule){
                    index += 1;
                    if( i.getScheduleID().equals(ListPatientFilterId.get(0).getScheduleID()) ){
                        break;
                    }
                }
                switch (index){
                    case -1 -> {return null;}
                    default -> {
                        historyContent.setObject(ListPatientFilterId.get(0));
                        result = historyContent.getObject();
                        log.debug("selectScheduleId [2]: - success method work");
                    }
                }
            }catch (Exception e){
                log.error("selectScheduleId [3]: - ERROR скоре всего нет такого ключа{}",e.getMessage());
            }
        }
        log.debug("selectScheduleId [4]: - END method working select xml");
        return result;
    }

    @Override
    public Map<String, Object> selectCalcReportId(Integer CalcReport_Id) {
        log.debug("selectCalcReportId [1]: - Start method working select xml");
        Map<String,Object> result = new HashMap<>();
        List<CalcReport> ListCalcReport;
        HistoryContent historyContent = new HistoryContent();
        int index = -1;
        File xmlFile = new File(calcReportPath);

        if(xmlFile.length() != 0) {
            try {
                ListCalcReport = getXmlWrapper(calcReportPath).getListObjXml();
                List<CalcReport> ListPatientFilterId = ListCalcReport.stream()
                        .filter(iter-> iter.getReport_ID().equals(CalcReport_Id))
                        .toList();

                for (CalcReport i : ListCalcReport){
                    index += 1;
                    if( i.getReport_ID().equals(ListPatientFilterId.get(0).getReport_ID()) ){
                        break;
                    }
                }
                switch (index){
                    case -1 -> {return null;}
                    default -> {
                        historyContent.setObject(ListPatientFilterId.get(0));
                        result = historyContent.getObject();
                        log.debug("selectCalcReportId [2]: - success method work");
                    }
                }
            }catch (Exception e){
                log.error("selectCalcReportId [3]: - ERROR скоре всего нет такого ключа{}",e.getMessage());
            }
        }
        log.debug("selectCalcReportId [4]: - END method working select xml");
        return result;
    }

    @Override
    public StatusMethod deleteDataDoctor(Integer Doctor_ID) {
        log.debug("deleteDataDoctor [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<Doctor> ListDoctor;
        XmlWrapper<Doctor> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(doctorPath);
        Serializer serializer = new Persister();


        if(xmlFile.length() != 0 && !selectDoctorId(Doctor_ID).isEmpty()){
            try {

                ListDoctor = getXmlWrapper(doctorPath).getListObjXml();

                switch (ListDoctor.size()){
                    case 1 -> serializer.write(null, xmlFile);
                    default -> {
                        ListDoctor = ListDoctor.stream()
                                .filter(iter-> !(iter.getPersonID().equals(Doctor_ID)))
                                .toList();
                        dataContainer.setListObjXml(ListDoctor);
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                }
            }catch (Exception e){
                result = StatusMethod.ERROR;
                log.debug("deleteDataDoctor [2] - error {}",e.getMessage());
            }
        }
        log.debug("deleteDataDoctor [3]: - END method working insert xml");
        return result;
    }

    @Override
    public StatusMethod deleteDataPatient(Integer Patient_ID) {
        log.debug("deleteDataPatient [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<Patient> ListPatient;
        XmlWrapper<Patient> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(patientPath);
        Serializer serializer = new Persister();


        if(xmlFile.length() != 0 && !selectPatientId(Patient_ID).isEmpty()){
            try {

                ListPatient = getXmlWrapper(patientPath).getListObjXml();

                switch (ListPatient.size()){
                    case 1 -> serializer.write(null, xmlFile);
                    default -> {
                        ListPatient = ListPatient.stream()
                                .filter(iter-> !(iter.getPersonID().equals(Patient_ID)))
                                .toList();
                        dataContainer.setListObjXml(ListPatient);
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                }
            }catch (Exception e){
                result = StatusMethod.ERROR;
                log.debug("deleteDataPatient [2] - error {}",e.getMessage());
            }
        }
        log.debug("deleteDataPatient [3]: - END method working insert xml");
        return result;
    }

    @Override
    public StatusMethod deleteDataPreparation(Integer Preparation_ID) {
        log.debug("deleteDataPreparation [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<Preparation> ListPreparation;
        XmlWrapper<Preparation> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(preparationPath);
        Serializer serializer = new Persister();


        if(xmlFile.length() != 0 && !selectPreparationId(Preparation_ID).isEmpty()){
            try {

                ListPreparation = getXmlWrapper(preparationPath).getListObjXml();

                switch (ListPreparation.size()){
                    case 1 -> serializer.write(null, xmlFile);
                    default -> {
                        ListPreparation = ListPreparation.stream()
                                .filter(iter-> !(iter.getPreparation_ID().equals(Preparation_ID)))
                                .toList();
                        dataContainer.setListObjXml(ListPreparation);
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                }
            }catch (Exception e){
                result = StatusMethod.ERROR;
                log.debug("deleteDataPreparation [2] - error {}",e.getMessage());
            }
        }
        log.debug("deleteDataPreparation [3]: - END method working insert xml");
        return result;
    }

    @Override
    public StatusMethod deleteDataSchedule(Integer Schedule_ID) {
        log.debug("deleteDataSchedule [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<Schedule> ListSchedule;
        XmlWrapper<Schedule> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(schedulePath);
        Serializer serializer = new Persister();


        if(xmlFile.length() != 0 && !selectScheduleId(Schedule_ID).isEmpty()){
            try {

                ListSchedule = getXmlWrapper(schedulePath).getListObjXml();

                switch (ListSchedule.size()){
                    case 1 -> serializer.write(null, xmlFile);
                    default -> {
                        ListSchedule = ListSchedule.stream()
                                .filter(iter-> !(iter.getScheduleID().equals(Schedule_ID)))
                                .toList();
                        dataContainer.setListObjXml(ListSchedule);
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                }
            }catch (Exception e){
                result = StatusMethod.ERROR;
                log.debug("deleteDataSchedule [2] - error {}",e.getMessage());
            }
        }
        log.debug("deleteDataSchedule [3]: - END method working insert xml");
        return result;
    }

    @Override
    public StatusMethod deleteDataCalcReport(Integer CalcReport_ID) {
        log.debug("deleteDataCalcReport [1]: - Start method working insert xml");
        StatusMethod result = StatusMethod.ERROR;

        List<CalcReport> ListCalcReport;
        XmlWrapper<CalcReport> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(calcReportPath);
        Serializer serializer = new Persister();


        if(xmlFile.length() != 0 && !selectCalcReportId(CalcReport_ID).isEmpty()){
            try {

                ListCalcReport = getXmlWrapper(calcReportPath).getListObjXml();

                switch (ListCalcReport.size()){
                    case 1 -> serializer.write(null, xmlFile);
                    default -> {
                        ListCalcReport = ListCalcReport.stream()
                                .filter(iter-> !(iter.getReport_ID().equals(CalcReport_ID)))
                                .toList();
                        dataContainer.setListObjXml(ListCalcReport);
                        serializer.write(dataContainer, xmlFile);
                        result = StatusMethod.OK;
                    }
                }
            }catch (Exception e){
                result = StatusMethod.ERROR;
                log.debug("deleteDataCalcReport [2] - error {}",e.getMessage());
            }
        }
        log.debug("deleteDataCalcReport [3]: - END method working insert xml");
        return result;
    }
}
