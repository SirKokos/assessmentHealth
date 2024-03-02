package ru.sfedu.assessmentHealth.api;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.io.*;
import java.util.*;


import static ru.sfedu.assessmentHealth.utils.FileUtil.createFileIfNotExists;

public class DataProviderCsv implements IDataProvider {


    private static final Logger log = LogManager.getLogger(DataProviderCsv.class.getName());


    final private String calcReportPath = PropertyConfig.getPropertyValue(Const.CSV_NAME_CALCREPORT_KEY,PropertyConfig.getConfigPath());
    final private String doctorPath = PropertyConfig.getPropertyValue(Const.CSV_NAME_DOCTOR_KEY,PropertyConfig.getConfigPath());
    final private String patientPath = PropertyConfig.getPropertyValue(Const.CSV_NAME_PATIENT_KEY,PropertyConfig.getConfigPath());
    final private String preparationPath =PropertyConfig.getPropertyValue(Const.CSV_NAME_PREPARATION_KEY,PropertyConfig.getConfigPath());
    final private String schedulePath = PropertyConfig.getPropertyValue(Const.CSV_NAME_SCHEDULE_KEY,PropertyConfig.getConfigPath());
    public DataProviderCsv(){
        log.debug("DataProviderCsv [1]: start constructor");
        try {
            createFileIfNotExists(calcReportPath);
            createFileIfNotExists(doctorPath);
            createFileIfNotExists(patientPath);
            createFileIfNotExists(preparationPath);
            createFileIfNotExists(schedulePath);
        } catch (IOException e) {
            log.error("DataProviderCsv [2]: ERROR {}",e.getMessage());
        }
        log.debug("DataProviderCsv [3]: end process");

    }


    DataProviderMongo dataProviderMongo = new DataProviderMongo();

    /**
     * @param obj - Экземпляр класса Doctor
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertDoctor(Doctor obj) {
        log.debug("InsertDataDoctor [1]: Start working");
        StatusAnswer response = StatusAnswer.ERROR;
        StatefulBeanToCsv beanToCsv;

        File csvFile = new File(doctorPath);
        try (Writer writer = new FileWriter(doctorPath,true)){

            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

            switch ((int) csvFile.length()){
                case 0-> {
                    obj.setId(1);
                    beanToCsv.write(obj);
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
                default -> {
                    obj.setId(BaseId.getObjectLastIdCsv(doctorPath));
                    beanToCsv.write(obj);
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
            }



        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.debug("InsertDataDoctor [2]: - ERROR result = {}, error = {}",response,e.getMessage());
        }
        log.debug("InsertDataDoctor [3]: - Start method working END");

        return response;
    }

    /**
     * @param obj - Экземпляр класса Patient
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertPatient(Patient obj) {
        log.debug("InsertDataPatient [1]: Start working");
        StatusAnswer response = StatusAnswer.ERROR;
        StatefulBeanToCsv beanToCsv;

        File csvFile = new File(patientPath);
        try (Writer writer = new FileWriter(patientPath,true)){

            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

            switch ((int) csvFile.length()){
                case 0-> {
                    obj.setId(1);
                    beanToCsv.write(obj);
                    writer.close();
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
                default -> {
                    obj.setId(BaseId.getObjectLastIdCsv(patientPath));
                    beanToCsv.write(obj);
                    writer.close();
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
            }

        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.debug("InsertDataPatient [2]: - ERROR result = {}, error = {}",response,e.getMessage());
        }
        log.debug("InsertDataPatient [3]: - Start method working END");

        return response;
    }

    /**
     * @param obj - Экземпляр класса Preparation
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertPreparation(Preparation obj) {
        log.debug("InsertDataPreparation [1]: Start working");
        StatusAnswer response = StatusAnswer.ERROR;
        StatefulBeanToCsv beanToCsv;

        File csvFile = new File(preparationPath);
        try (Writer writer= new FileWriter(preparationPath,true)){
            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

            switch ((int) csvFile.length()){
                case 0-> {
                    obj.setId(1);
                    beanToCsv.write(obj);
                    writer.close();
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
                default -> {
                    obj.setId(BaseId.getObjectLastIdCsv(preparationPath));
                    beanToCsv.write(obj);
                    writer.close();
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
            }

        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.debug("InsertDataPreparation [2]: - ERROR result = {}, error = {}",response,e.getMessage());
        }
        log.debug("InsertDataPreparation [3]: - Start method working END");

        return response;
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
        log.debug("InsertDataSchedule [1]: Start working");
        StatusAnswer response = StatusAnswer.ERROR;
        StatefulBeanToCsv beanToCsv;

        File csvFile = new File(schedulePath);
        try ( Writer writer = new FileWriter(schedulePath,true)){
            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

            switch ((int) csvFile.length()){
                case 0-> {
                    obj.setId(1);
                    beanToCsv.write(obj);
                    writer.close();
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
                default -> {
                    obj.setId(BaseId.getObjectLastIdCsv(schedulePath));
                    beanToCsv.write(obj);
                    writer.close();
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
            }

        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.debug("InsertDataSchedule [2]: - ERROR result = {}, error = {}",response,e.getMessage());
        }
        log.debug("InsertDataSchedule [3]: - Start method working END");

        return response;
    }

    /**
     * @param obj - Экземпляр класса CalcReport
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertCalcReport(CalcReport obj) {
        log.debug("InsertDataCalcReport [1]: Start working");
        StatusAnswer response = StatusAnswer.ERROR;
        StatefulBeanToCsv beanToCsv;

        File csvFile = new File(calcReportPath);
        try(Writer writer = new FileWriter(calcReportPath,true)) {

            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

            switch ((int) csvFile.length()){
                case 0-> {
                    obj.setId(1);
                    beanToCsv.write(obj);
                    writer.close();
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
                default -> {
                    obj.setId(BaseId.getObjectLastIdCsv(calcReportPath));
                    beanToCsv.write(obj);
                    writer.close();
                    dataProviderMongo.save(CommandType.UPDATED,obj);
                    return StatusAnswer.OK;}
            }

        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.debug("InsertDataCalcReport [2]: - ERROR result = {}, error = {}",response,e.getMessage());
        }
        log.debug("InsertDataCalcReport [3]: - Start method working END");
        return response;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Doctor>
     */
    @Override
    public Optional<Doctor> selectDoctorId(Integer id) {
        log.debug("selectDoctorId [1]: start working");
        Optional<Doctor> result = Optional.empty();
        List<Preparation> listPrep;
        List<Schedule> listSche;
        CsvToBean csvToBean;
        List<Doctor> Beans;
        try (CSVReader csvReader = new CSVReader(new FileReader(doctorPath))){
            csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(Doctor.class)
                    .build();

            Beans = csvToBean.parse();

            result = Optional.of(Beans.stream()
                    .filter(i -> i.getId().equals(id))
                    .toList().get(0));


            listSche =  result.get().getLinkSchedule()
                    .stream()
                    .map(i->selectScheduleId(i.getId()).get())
                    .toList();

            listPrep = result.get().getLinkSchedule()
                    .stream()
                    .map(i->selectPreparationId(i.getId()).get())
                    .toList();

            result.get().setLinkSchedule(listSche);
            result.get().setLinkPreparation(listPrep);

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            log.error("selectDoctorId [2]: Error {}",e.getMessage());
        }
        log.debug("selectDoctorId [3]: end working");
        return result;
    }

    @Override
    public Optional<List<Doctor>> selectAllDoctor(){
        log.debug("selectAllDoctor [1]: start working");
        List<Doctor> result = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReaderBuilder(new FileReader(doctorPath)).build();
            List<String[]> myEntries = reader.readAll();
            List<Integer> listIdDoctor = myEntries.stream().map(
                    i->Integer.valueOf(
                            Arrays.stream(i).toList().get(0)))
                            .toList();

            for(Integer i:listIdDoctor){result.add(selectDoctorId(i).get());}
        } catch (IOException | CsvException | ArrayIndexOutOfBoundsException e) {
            log.error("selectAllDoctor [2]: error {}",e.getMessage());
        }
        log.debug("selectAllDoctor[3]: end working");
        return Optional.of(result);
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Patient>
     */
    @Override
    public Optional<Patient> selectPatientId(Integer id) {
        log.debug("selectPatientId [1]: start working");
        Optional<Patient> result = Optional.empty();

        CsvToBean csvToBean;
        List<Patient> Beans;
        try (CSVReader csvReader = new CSVReader(new FileReader(patientPath))){

            csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(Patient.class)
                    .build();

            Beans = csvToBean.parse();
            result = Optional.of(Beans.stream()
                                    .filter(i->i.getId().equals(id))
                                    .toList()
                                    .get(0)
                                    );

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            log.error("selectPatientId [2]: Error {}",e.getMessage());
        }
        log.debug("selectPatientId [3]: end working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Schedule>
     */
    @Override
    public Optional<Schedule> selectScheduleId(Integer id) {
        log.debug("selectScheduleId [1]: start working");
        Optional<Schedule> result = Optional.empty();
        CsvToBean csvToBean;
        List<Schedule> Beans;
        try(CSVReader csvReader = new CSVReader(new FileReader(schedulePath))) {
            csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(Schedule.class)
                    .build();

            Beans = csvToBean.parse();
            result = Optional.of(Beans.stream()
                    .filter(i->i.getId().equals(id))
                    .toList()
                    .get(0)
            );

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            log.error("selectScheduleId [2]: Error {}",e.getMessage());
        }
        log.debug("selectScheduleId [3]: end working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Preparation>
     */
    @Override
    public Optional<Preparation> selectPreparationId(Integer id) {
        log.debug("selectPreparationId [1]: start working");
        Optional<Preparation> result = Optional.empty();
        CsvToBean csvToBean;
        List<Preparation> Beans;
        try (CSVReader csvReader = new CSVReader(new FileReader(preparationPath))){
            csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(Preparation.class)
                    .build();

            Beans = csvToBean.parse();
            result = Optional.of(Beans.stream()
                    .filter(i->i.getId().equals(id))
                    .toList()
                    .get(0)
            );

        } catch (IOException| ArrayIndexOutOfBoundsException e) {
            log.error("selectPreparationId [2]: Error {}",e.getMessage());
        }
        log.debug("selectPreparationId [3]: end working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<CalcReport>
     */
    @Override
    public Optional<CalcReport> selectCalcReport(Integer id) {
        log.debug("selectCalcReport [1]: start working");
        Optional<CalcReport> result = Optional.empty();
        CsvToBean csvToBean;
        List<CalcReport> Beans;
        List<Doctor> doctorList;
        List<Patient> patientList;
        try(CSVReader csvReader = new CSVReader(new FileReader(calcReportPath));) {

            csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(CalcReport.class)
                    .build();

            Beans = csvToBean.parse();

            result = Optional.of(Beans.stream()
                    .filter(i->i.getId().equals(id))
                    .toList()
                    .get(0)
            );

            doctorList =  result.get().getDoctor()
                    .stream()
                    .map(i->selectDoctorId(i.getId()).get())
                    .toList();


            patientList =  result.get().getPatient()
                    .stream()
                    .map(i->selectPatientId(i.getId()).get())
                    .toList();


            result.get().setDoctor(doctorList);
            result.get().setPatient(patientList);

        } catch (IOException | ArrayIndexOutOfBoundsException | NoSuchElementException e) {
            log.error("selectCalcReport [2]: Error {}",e.getMessage());
        }
        log.debug("selectCalcReport [3]: end working");
        return result;
    }

    /**
     * @param id который нужно удалить Врача
     * @return StatusAnswer
     */
    @Override
    public StatusAnswer deleteDoctor(Integer id) {
        log.debug("deleteDoctor [1]: Start working");
        dataProviderMongo.save(CommandType.DELETED,selectDoctorId(id).get());
        StatusAnswer response = StatusAnswer.ERROR;
        CsvToBean csvToBean;
        List<Doctor> Beans;
        try(CSVReader csvReader = new CSVReader(new FileReader(doctorPath))) {
            csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(Doctor.class)
                    .build();
            Beans = csvToBean.parse();
            csvReader.close();

            File file = new File(doctorPath);
            file.delete();

            List<Doctor> listDoctor = Beans.stream().filter(i->!(i.getId().equals(id))).toList();


            listDoctor.forEach(i-> {
                try (Writer writer = new FileWriter(doctorPath,true)){

                    StatefulBeanToCsv  beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
                    beanToCsv.write(i);
                } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
            response = StatusAnswer.OK;

        } catch (IOException e) {
            log.error("deleteDoctor [2]: Error {}",e.getMessage());
        }
        log.debug("deleteDoctor [3]: end working");
        return response;
    }


}
