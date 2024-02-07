package ru.sfedu.assessmentHealth.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.io.File;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import static ru.sfedu.assessmentHealth.utils.FileUtil.createFileIfNotExists;

public class DataProviderXml implements IDataProvider {

    private static final Logger log = LogManager.getLogger(DataProviderXml.class.getName());
//    final private String calcReportPath = PropertyConfig.getPropertyValue(Const.XML_NAME_CALCREPORT_KEY,Const.NAME_PROPERTY_FILE);
//    final private String doctorPath = PropertyConfig.getPropertyValue(Const.XML_NAME_DOCTOR_KEY,Const.NAME_PROPERTY_FILE);
//    final private String patientPath = PropertyConfig.getPropertyValue(Const.XML_NAME_PATIENT_KEY,Const.NAME_PROPERTY_FILE);
//    final private String preparationPath =PropertyConfig.getPropertyValue(Const.XML_NAME_PREPARATION_KEY,Const.NAME_PROPERTY_FILE);
//    final private String schedulePath = PropertyConfig.getPropertyValue(Const.XML_NAME_SCHEDULE_KEY,Const.NAME_PROPERTY_FILE);
    final private String calcReportPath = PropertyConfig.getPropertyValue(Const.XML_NAME_CALCREPORT_KEY,PropertyConfig.getConfigPath());
    final private String doctorPath = PropertyConfig.getPropertyValue(Const.XML_NAME_DOCTOR_KEY,PropertyConfig.getConfigPath());
    final private String patientPath = PropertyConfig.getPropertyValue(Const.XML_NAME_PATIENT_KEY,PropertyConfig.getConfigPath());
    final private String preparationPath =PropertyConfig.getPropertyValue(Const.XML_NAME_PREPARATION_KEY,PropertyConfig.getConfigPath());
    final private String schedulePath = PropertyConfig.getPropertyValue(Const.XML_NAME_SCHEDULE_KEY,PropertyConfig.getConfigPath());

    public DataProviderXml(){
        log.debug("DataProviderXml [1]: start constructor");
        try {
            createFileIfNotExists(calcReportPath);
            createFileIfNotExists(doctorPath);
            createFileIfNotExists(patientPath);
            createFileIfNotExists(preparationPath);
            createFileIfNotExists(schedulePath);
        } catch (IOException e) {
            log.error("DataProviderXml [2]: ERROR {}",e.getMessage());
        }
        log.debug("DataProviderXml [3]: end process");

    }


    /**
     * Этот метод считывает XML-файл и возвращает объект типа XmlWrapper
     * Обертку со всеми объектами
     * @param PathXmlFile
     * @param <T>
     * @return wrap
     */
    public static <T> XmlWrapper getXmlWrapper(String PathXmlFile){
        log.debug("getXmlWrapper [1]: start working method");
        Serializer serializer = new Persister();
        File file = new File(PathXmlFile);
        XmlWrapper<T> wrap = new XmlWrapper<>();
        try {
            wrap = serializer.read(XmlWrapper.class, file,false);
        } catch (Exception e) {
            log.error("getXmlWrapper [2]: ERROR {}",e.getMessage());
        }
        return wrap;
    }

    /**
     * @param obj - Экземпляр класса Doctor
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertDoctor(Doctor obj) {
        log.debug("InsertDataDoctor [1]: - Start method working insert xml");
        StatusAnswer result = StatusAnswer.ERROR;

        List<Doctor> ListDoctor;
        XmlWrapper<Doctor> dataContainer =  new XmlWrapper<>();
        File xmlFile = new File(doctorPath);


        Serializer serializer = new Persister();
        Integer id;
        obj.setId(1);
        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    dataContainer.setListObject(List.of(obj));
                    serializer.write(dataContainer, xmlFile);

                    return StatusAnswer.OK;
                }
                default -> {
                    ListDoctor = getXmlWrapper(doctorPath).getListObjectXml();

                    id = BaseId.getObjectLastIdXml(doctorPath);
                    obj.setId(id);

                    ListDoctor.add(obj);
                    dataContainer.setListObject(ListDoctor);

                    serializer.write(dataContainer, xmlFile);


                    return StatusAnswer.OK;
                }
            }
        }catch (Exception e){
            log.error("insertDataDoctor [2] - error {}",e.getMessage());
        }
        log.debug("InsertDataDoctor [3]: - END method working insert xml");

        return result;
    }

    /**
     * @param obj - Экземпляр класса Patient
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertPatient(Patient obj) {
        log.debug("insertPatient [1]: - Start method working insert xml");
        StatusAnswer result = StatusAnswer.ERROR;

        List<Patient> ListPatient;
        XmlWrapper<Patient> dataContainer =  new XmlWrapper<>();
        File xmlFile = new File(patientPath);


        Serializer serializer = new Persister();
        Integer id;
        obj.setId(1);
        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    dataContainer.setListObject(List.of(obj));
                    serializer.write(dataContainer, xmlFile);

                    return StatusAnswer.OK;
                }
                default -> {
                    ListPatient = getXmlWrapper(patientPath).getListObjectXml();

                    id = BaseId.getObjectLastIdXml(patientPath);
                    obj.setId(id);

                    ListPatient.add(obj);
                    dataContainer.setListObject(ListPatient);

                    serializer.write(dataContainer, xmlFile);


                    return StatusAnswer.OK;
                }
            }
        }catch (Exception e){
            log.error("insertPatient [2] - error {}",e.getMessage());
        }
        log.debug("insertPatient [3]: - END method working insert xml");

        return result;
    }

    /**
     * @param obj - Экземпляр класса Preparation
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertPreparation(Preparation obj) {
        log.debug("insertPreparation [1]: - Start method working insert xml");
        StatusAnswer result = StatusAnswer.ERROR;

        List<Preparation> ListPreparation;
        XmlWrapper<Preparation> dataContainer =  new XmlWrapper<>();
        File xmlFile = new File(preparationPath);

        Serializer serializer = new Persister();
        Integer id;
        obj.setId(1);
        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    dataContainer.setListObject(List.of(obj));
                    serializer.write(dataContainer, xmlFile);

                    return StatusAnswer.OK;
                }
                default -> {
                    ListPreparation = getXmlWrapper(preparationPath).getListObjectXml();

                    id = BaseId.getObjectLastIdXml(preparationPath);
                    obj.setId(id);

                    ListPreparation.add(obj);
                    dataContainer.setListObject(ListPreparation);

                    serializer.write(dataContainer, xmlFile);


                    return StatusAnswer.OK;
                }
            }
        }catch (Exception e){
            log.error("insertPreparation [2] - error {}",e.getMessage());
        }
        log.debug("insertPreparation [3]: - END method working insert xml");

        return result;
    }

    /**
     * @param obj - Экземпляр класса Schedule
     * @return StatusAnswer(OK / ERROR)
     * Дата в формате YYYY-MM-DD
     * время начала  в формате HH-MM-SS
     * время конца в формате HH-MM-SS
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertSchedule(Schedule obj) {
        log.debug("insertSchedule [1]: - Start method working insert xml");
        StatusAnswer result = StatusAnswer.ERROR;

        List<Schedule> ListSchedule;
        XmlWrapper<Schedule> dataContainer =  new XmlWrapper<>();
        File xmlFile = new File(schedulePath);

        Serializer serializer = new Persister();
        Integer id;
        obj.setId(1);
        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    dataContainer.setListObject(List.of(obj));
                    serializer.write(dataContainer, xmlFile);

                    return StatusAnswer.OK;
                }
                default -> {
                    ListSchedule = getXmlWrapper(schedulePath).getListObjectXml();

                    id = BaseId.getObjectLastIdXml(schedulePath);
                    obj.setId(id);

                    ListSchedule.add(obj);
                    dataContainer.setListObject(ListSchedule);

                    serializer.write(dataContainer, xmlFile);


                    return StatusAnswer.OK;
                }
            }
        }catch (Exception e){
            log.error("insertSchedule [2] - error {}",e.getMessage());
        }
        log.debug("insertSchedule [3]: - END method working insert xml");

        return result;
    }

    /**
     * @param obj - Экземпляр класса CalcReport
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertCalcReport(CalcReport obj) {
        log.debug("insertCalcReport [1]: - Start method working insert xml");
        StatusAnswer result = StatusAnswer.ERROR;

        List<CalcReport> ListCalc;
        XmlWrapper<CalcReport> dataContainer =  new XmlWrapper<>();
        File xmlFile = new File(calcReportPath);

        Serializer serializer = new Persister();
        Integer id;
        obj.setId(1);
        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    dataContainer.setListObject(List.of(obj));
                    serializer.write(dataContainer, xmlFile);

                    return StatusAnswer.OK;
                }
                default -> {
                    ListCalc = getXmlWrapper(calcReportPath).getListObjectXml();

                    id = BaseId.getObjectLastIdXml(calcReportPath);
                    obj.setId(id);

                    ListCalc.add(obj);
                    dataContainer.setListObject(ListCalc);

                    serializer.write(dataContainer, xmlFile);


                    return StatusAnswer.OK;
                }
            }
        }catch (Exception e){
            log.error("insertCalcReport [2] - error {}",e.getMessage());
        }
        log.debug("insertCalcReport [3]: - END method working insert xml");

        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Doctor>
     */
    @Override
    public Optional<Doctor> selectDoctorId(Integer id) {
        log.debug("selectDoctorId [1]: - Start method working select xml");
        List<Doctor> ListDoctor;
        Optional<Doctor> result = Optional.empty();
        File xmlFile = new File(doctorPath);

        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    return result;
                }
                default -> {
                    ListDoctor = getXmlWrapper(doctorPath).getListObjectXml();
                    List<Doctor> ListDoctorFilterId = ListDoctor.stream()
                            .filter(iter-> iter.getId().equals(id))
                            .toList();
                    result = Optional.of(ListDoctorFilterId.get(0));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            log.error("selectDoctorId [2]: - error Такого ключа нет {}",e.getMessage());
        }
        log.debug("selectDoctorId [3]: - End working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Patient>
     */
    @Override
    public Optional<Patient> selectPatientId(Integer id) {
        log.debug("selectPatientId [1]: - Start method working select xml");
        List<Patient> ListPatient;
        Optional<Patient> result = Optional.empty();
        File xmlFile = new File(patientPath);

        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    return result;
                }
                default -> {
                    ListPatient = getXmlWrapper(patientPath).getListObjectXml();
                    List<Patient> ListListPatientFilterId = ListPatient.stream()
                            .filter(iter-> iter.getId().equals(id))
                            .toList();
                    result = Optional.of(ListListPatientFilterId.get(0));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            log.error("selectPatientId [2]: - error Такого ключа нет {}",e.getMessage());
        }
        log.debug("selectPatientId [3]: - End working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Schedule>
     */
    @Override
    public Optional<Schedule> selectScheduleId(Integer id) {
        log.debug("selectScheduleId [1]: - Start method working select xml");
        List<Schedule> ListSchedule;
        Optional<Schedule> result = Optional.empty();
        File xmlFile = new File(schedulePath);

        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    return result;
                }
                default -> {
                    ListSchedule = getXmlWrapper(schedulePath).getListObjectXml();
                    List<Schedule> ListListScheduleFilterId = ListSchedule.stream()
                            .filter(iter-> iter.getId().equals(id))
                            .toList();
                    result = Optional.of(ListListScheduleFilterId.get(0));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            log.error("selectScheduleId [2]: - error Такого ключа нет {}",e.getMessage());
        }
        log.debug("selectScheduleId [3]: - End working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Preparation>
     */
    @Override
    public Optional<Preparation> selectPreparationId(Integer id) {
        log.debug("selectPreparationId [1]: - Start method working select xml");
        List<Preparation> ListPreparation;
        Optional<Preparation> result = Optional.empty();
        File xmlFile = new File(preparationPath);

        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    return result;
                }
                default -> {
                    ListPreparation = getXmlWrapper(preparationPath).getListObjectXml();
                    List<Preparation> ListListPreparationFilterId = ListPreparation.stream()
                            .filter(iter-> iter.getId().equals(id))
                            .toList();
                    result = Optional.of(ListListPreparationFilterId.get(0));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            log.error("selectPreparationId [2]: - error Такого ключа нет {}",e.getMessage());
        }
        log.debug("selectPreparationId [3]: - End working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<CalcReport>
     */
    @Override
    public Optional<CalcReport> selectCalcReport(Integer id) {
        log.debug("selectCalcReport [1]: - Start method working select xml");
        List<CalcReport> ListCalcReport;
        Optional<CalcReport> result = Optional.empty();
        File xmlFile = new File(calcReportPath);

        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    return result;
                }
                default -> {
                    ListCalcReport = getXmlWrapper(calcReportPath).getListObjectXml();
                    List<CalcReport> ListListCalcReportFilterId = ListCalcReport.stream()
                            .filter(iter-> iter.getId().equals(id))
                            .toList();
                    result = Optional.of(ListListCalcReportFilterId.get(0));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            log.error("selectCalcReport [2]: - error Такого ключа нет {}",e.getMessage());
        }
        log.debug("selectCalcReport [3]: - End working");
        return result;
    }

    /**
     * Метод получает список всех врачей
     *
     * @return Optional<List < Doctor>>
     */
    @Override
    public Optional<List<Doctor>> selectAllDoctor() {
        log.debug("selectAllDoctor [1]: - Start method working select xml");
        List<Doctor> result = null;

        File xmlFile = new File(doctorPath);

        try {
            switch ((int) xmlFile.length()){
                case 0 -> {
                    return Optional.empty();
                }
                default -> {
                    result = getXmlWrapper(doctorPath).getListObjectXml();
                }
            }
        }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            log.error("selectAllDoctor [2]: - error Такого ключа нет {}",e.getMessage());

        }

        log.debug("selectAllDoctor [3]: - End working");
        return Optional.of(result);
    }

    /**
     * @param id который нужно удалить Врача
     * @return StatusAnswer
     */
    @Override
    public StatusAnswer deleteDoctor(Integer id) {
        log.debug("deleteDoctor [1]: - Start method working insert xml");
        StatusAnswer result = StatusAnswer.ERROR;

        List<Doctor> ListDoctor;
        XmlWrapper<Doctor> dataContainer =  new XmlWrapper<>();;
        File xmlFile = new File(doctorPath);
        Serializer serializer = new Persister();


        if(xmlFile.length() != 0 && !selectDoctorId(id).isEmpty()){
            try {

                ListDoctor = getXmlWrapper(doctorPath).getListObjectXml();

                switch (ListDoctor.size()){
                    case 1 -> serializer.write(null, xmlFile);
                    default -> {
                        ListDoctor = ListDoctor.stream()
                                .filter(iter-> !(iter.getId().equals(id)))
                                .toList();
                        dataContainer.setListObject(ListDoctor);
                        serializer.write(dataContainer, xmlFile);
                        result = StatusAnswer.OK;
                    }
                }
            }catch (Exception e){
                result = StatusAnswer.ERROR;
                log.debug("deleteDoctor [2] - error {}",e.getMessage());
            }
        }
        log.debug("deleteDoctor [3]: - END method working insert xml");
        return result;
    }
}
