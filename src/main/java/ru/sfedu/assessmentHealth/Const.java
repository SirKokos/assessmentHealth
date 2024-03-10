package ru.sfedu.assessmentHealth;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Const {


    public static final String SEPARATOR_ESCAPE = "\n";
    public static final String SEPARATOR_ARROW = "===>";
    public static final String NAME_PROPERTY_FILE = "src/main/resources/environment.properties";
    public static final String NAME_MONGO_COLLECTION = "Mongo.Collection";
    public static final String NAME_MONGO_HOST = "dataBase.Mongo";
    public static final String NAME_MONGO_NAME = "Mongo.Name";
    public static final String NAME_ACTOR_HISTORY = "system";


    public static final String CSV_PATH_FOLDER_KEY = "ru.sfedu.csv.folder";
    public static final String CSV_NAME_CALCREPORT_KEY = "ru.sfedu.csv.Name.CalcReport";
    public static final String CSV_NAME_DOCTOR_KEY = "ru.sfedu.csv.Name.Doctor";
    public static final String CSV_NAME_PATIENT_KEY = "ru.sfedu.csv.Name.Patient";
    public static final String CSV_NAME_PREPARATION_KEY = "ru.sfedu.csv.Name.Preparation";
    public static final String CSV_NAME_SCHEDULE_KEY = "ru.sfedu.csv.Name.Schedule";
    public static final String CSV_NAME_TYPE_KEY = "ru.sfedu.csv.type";


    public static final String XML_PATH_FOLDER_KEY = "ru.sfedu.xml.folder";
    public static final String XML_NAME_CALCREPORT_KEY = "ru.sfedu.xml.Name.CalcReport";
    public static final String XML_NAME_DOCTOR_KEY = "ru.sfedu.xml.Name.Doctor";
    public static final String XML_NAME_PATIENT_KEY = "ru.sfedu.xml.Name.Patient";
    public static final String XML_NAME_PREPARATION_KEY = "ru.sfedu.xml.Name.Preparation";
    public static final String XML_NAME_SCHEDULE_KEY = "ru.sfedu.xml.Name.Schedule";
    public static final String XML_NAME_TYPE_KEY = "ru.sfedu.xml.type";



    public static final String BD_POSTGRES_HOST = "dataBase.Postgres";
    public static final String BD_POSTGRES_NAME = "Postgres.Name";
    public static final String BD_POSTGRES_USER = "Postgres.User";
    public static final String BD_POSTGRES_PASSWORD = "Postgres.Password";


    public static final String TABLE_NAME_DOCTOR = "doctor";
    public static final String TABLE_NAME_PATIENT = "patient";
    public static final String TABLE_NAME_CALC_REPORT = "calcReport";
    public static final String TABLE_NAME_PREPARATION = "preparation";
    public static final String TABLE_NAME_SCHEDULE = "schedule";


    public static final String SQL_TABLE_CREATE_DOCTOR= "CREATE TABLE IF NOT EXISTS ".concat(TABLE_NAME_DOCTOR).concat("(").concat("id").concat(" SERIAL PRIMARY KEY,")
            .concat("FIO VARCHAR(100)," +
                    "Age INT,"+
                    "Gender CHAR(1)," +
                    "Status VARCHAR(20)," +
                    "Experience INTEGER," +
                    "AvgPatient DECIMAL," +
                    "Qualification VARCHAR(60)," +
                    "Specialization VARCHAR(60),"+
                    "LinkPreparation TEXT,"+
                    "LinkSchedule TEXT"
            ).concat(");");
    public static final String SQL_TABLE_CREATE_PATIENT= "CREATE TABLE IF NOT EXISTS ".concat(TABLE_NAME_PATIENT).concat("(").concat("id").concat(" SERIAL PRIMARY KEY,\n" +
            "FIO VARCHAR(30),\n" +
            "Age INT,\n" +
            "Gender CHAR(1),\n" +
            "Status VARCHAR(20),\n" +
            "CellsBlood DECIMAL,\n" +
            "Hemoglobin DECIMAL,\n" +
            "Platelets DECIMAL,\n" +
            "Glucose DECIMAL,\n" +
            "Cholesterol DECIMAL,\n" +
            "StatusVisit VARCHAR(20)\n").concat(");");
    public static final String SQL_TABLE_CREATE_CALC_REPORT= "CREATE TABLE IF NOT EXISTS ".concat(TABLE_NAME_CALC_REPORT)
            .concat("(").concat("id").concat("" +
                            " SERIAL PRIMARY KEY,\n" +
                            "PatientName VARCHAR(30),\n" +
                            "DoctorName VARCHAR(30),\n" +
                            "BloodAnalyse BOOLEAN,\n" +
                            "GlucoseAnalyse BOOLEAN,\n" +
                            "ArterialAnalyse BOOLEAN,\n" +
                            "Doctor TEXT, \n" +
                            "Patient TEXT,\n" +
                            "price DECIMAL);\n"
            );
    public static final String SQL_TABLE_CREATE_PREPARATION= "CREATE TABLE IF NOT EXISTS ".concat(TABLE_NAME_PREPARATION)
            .concat("(").concat("id").concat(" SERIAL PRIMARY KEY,\n" +
                    "NamePrep VARCHAR(100),\n" +
                    "DateEnd\tDATE,\n" +
                    "Dosage DECIMAL,\n" +
                    "statusVisitPreparation VARCHAR(50),\n" +
                    "AboutPrep TEXT\n" .concat(");"));
    public static final String SQL_TABLE_CREATE_SCHEDULE= "CREATE TABLE IF NOT EXISTS ".concat(TABLE_NAME_SCHEDULE).concat(" (").concat("id").concat(" SERIAL PRIMARY KEY,\n" +
            "dateWeek VARCHAR(50),\n" +
            "DateSchedule DATE,\n" +
            "TimeBegin TIME,\n" +
            "TimeEnd TIME,\n" +
            "statusSchedule VARCHAR(20));\n");


    public static final String SELECT_DOCTOR_TO_ID = String.format("SELECT * FROM %s WHERE %s",TABLE_NAME_DOCTOR,"id").concat(" = ?");
    public static final String SELECT_ALL_DOCTOR = String.format("SELECT ID FROM %s",TABLE_NAME_DOCTOR);
    public static final String SELECT_PATIENT_TO_ID = String.format("SELECT * FROM %s WHERE %s",TABLE_NAME_PATIENT,"id").concat(" = ?");
    public static final String SELECT_PREPARATION_TO_ID = String.format("SELECT * FROM %s WHERE %s",TABLE_NAME_PREPARATION,"id").concat(" = ?");
    public static final String SELECT_SCHEDULE_TO_ID = String.format("SELECT * FROM %s WHERE %s",TABLE_NAME_SCHEDULE,"id").concat(" = ?");
    public static final String SELECT_CALC_REPORT_TO_ID = String.format("SELECT * FROM %s WHERE %s",TABLE_NAME_CALC_REPORT,"id").concat(" = ?");

    public static final String SELECT_CALC_REPORT_TO_ID_DOCTOR = String.format("SELECT * FROM %s WHERE %s",TABLE_NAME_CALC_REPORT,"id").concat(" = ?");
    public static final String INSERT_DOCTOR = String.format("INSERT INTO %s ( FIO, Age, Gender, Status, Experience, AvgPatient, Qualification, Specialization,LinkPreparation,LinkSchedule) ",TABLE_NAME_DOCTOR).concat("\nVALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?,?);");

    public static final String INSERT_PATIENT = String.format("INSERT INTO %s ( FIO, Age, Gender, Status, CellsBlood, Hemoglobin, Platelets, Glucose ,Cholesterol, statusVisit) ",TABLE_NAME_PATIENT).concat("\nVALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

    public static final String INSERT_PREPARATION = String.format("INSERT INTO %s (NamePrep,DateEnd,Dosage,statusVisitPreparation,AboutPrep) ",TABLE_NAME_PREPARATION).concat("\nVALUES ( ?, ?, ?, ?, ?)");

    public static final String INSERT_SCHEDULE = String.format("INSERT INTO %s (dateWeek,dateSchedule,timeBegin,timeEnd,statusSchedule)",TABLE_NAME_SCHEDULE).concat("\nVALUES(?,?, ?, ?, ?)");

    public static final String INSERT_CALC_REPORT = String.format("INSERT INTO %s ( PatientName, DoctorName, BloodAnalyse, GlucoseAnalyse, ArterialAnalyse,Doctor, Patient, price) ",TABLE_NAME_CALC_REPORT).concat("\nVALUES ( ?, ?, ?, ?, ?, ?, ?, ?)");


    public static final String DELETE_DOCTOR_ID = String.format("DELETE FROM %s WHERE %s",TABLE_NAME_DOCTOR,"id").concat(" = ?");


    public static final String TEST_INSERT_DOCTOR = "INSERT INTO doctor " +
            "(id ,FIO, Age, Gender, Status, Experience, AvgPatient, Qualification, Specialization,LinkPreparation,LinkSchedule)\n" +
            "VALUES (1,'Sim Artem Evgen',29,'M','DOCTOR',12,12.3,'Med','Genikolog','1 1','1 1');";


    public static final String RESULT_CELLS_BLOOD = "CellsBlood";
    public static final String RESULT_HEMOGLOBIN = "Hemoglobin";
    public static final String RESULT_PLATELETS = "Platelets";
    public static final String RESULT_GLUCOSE = "Glucose";
    public static final String RESULT_CHOLESTEROL = "Cholesterol";
    public static final String RESULT_HEALTH = "Health";


    public static final Pair<Double,Double> VALID_CELLS_BLOOD =  Pair.of(4.0,5.1);
    public static final Pair<Double,Double> VALID_HEMOGLOBIN_M =  Pair.of(130.0,160.0);
    public static final Pair<Double,Double> VALID_HEMOGLOBIN_G =  Pair.of(120.0,140.0);
    public static final Pair<Double,Double> VALID_PLATELETS =  Pair.of(180.0,320.0);
    public static final Pair<Double,Double> VALID_GLUCOSE =  Pair.of(59.0,99.0);
    public static final Pair<Double,Double> VALID_CHOLESTEROL =  Pair.of(2.0,6.2);


    public static final  String DISEASE_CELLS_BLOOD_1 = "Эритремия";
    public static final  String DISEASE_CELLS_BLOOD_2 = "Хронические болезни легких и сердца";
    public static final  String DISEASE_CELLS_BLOOD_3 = "Гормональные болезни";
    public static final  String DISEASE_CELLS_BLOOD_4 = "Анемия";
    public static final  String DISEASE_CELLS_BLOOD_5 = "Хронические инфекционно-воспалительные болезни";

    public static final  String RESULT_SYSTEM_CELLS_BLOOD = String.format("Возможные причины отклонения от нормы эритроцитов:\n")
            .concat(DISEASE_CELLS_BLOOD_1)
            .concat("\n")
            .concat(DISEASE_CELLS_BLOOD_2)
            .concat("\n")
            .concat(DISEASE_CELLS_BLOOD_3)
            .concat("\n")
            .concat(DISEASE_CELLS_BLOOD_4)
            .concat("\n")
            .concat(DISEASE_CELLS_BLOOD_5).concat(";");

    public static final  String DISEASE_HEMOGLOBIN_1 = "Эритроцитозы";
    public static final  String DISEASE_HEMOGLOBIN_2 = "Обезвоживание";
    public static final  String DISEASE_HEMOGLOBIN_3 = "Хроническое кровотечение";

    public static final  String RESULT_SYSTEM_HEMOGLOBIN = String.format("Возможные причины отклонения от нормы Гемоглобина:\n")
            .concat(DISEASE_HEMOGLOBIN_1)
            .concat("\n")
            .concat(DISEASE_HEMOGLOBIN_2)
            .concat("\n")
            .concat(DISEASE_HEMOGLOBIN_3).concat(";");

    public static final  String DISEASE_PLATELET_1 = "Ревматические болезни";
    public static final  String DISEASE_PLATELET_2 = "Онкологические болезни";
    public static final  String DISEASE_PLATELET_3 = "Хирургические вмешательства";

    public static final  String RESULT_SYSTEM_PLATELET = String.format("Возможные причины отклонения от нормы Тромбоцитов:\n")
            .concat(DISEASE_PLATELET_1)
            .concat("\n")
            .concat(DISEASE_PLATELET_2)
            .concat("\n")
            .concat(DISEASE_PLATELET_3).concat(";");

    public static final  String DISEASE_GLUCOSE_1 = "Гипергликемия";
    public static final  String DISEASE_GLUCOSE_2 = "Диабет";

    public static final  String RESULT_SYSTEM_GLUCOSE = String.format("Возможные причины отклонения от нормы Глюкозы:\n")
            .concat(DISEASE_GLUCOSE_1)
            .concat("\n")
            .concat(DISEASE_GLUCOSE_2).concat(";");


    public static final  String DISEASE_CHOLESTEROL_1 = "Атеросклероз";
    public static final  String DISEASE_CHOLESTEROL_2 = "Гиперлипидемия";

    public static final  String RESULT_SYSTEM_CHOLESTEROL = String.format("Возможные причины отклонения от нормы Холестерина:\n")
            .concat(DISEASE_CHOLESTEROL_1)
            .concat("\n")
            .concat(DISEASE_CHOLESTEROL_2).concat(";");


    public static final String RESULT_FULL_HEALTH = "Нет серьезных отклонений от нормы. Оценка здоровья выше 85";

    public static final List<String> TEST_HEALTH_RECOM  =  Arrays.asList(RESULT_SYSTEM_CELLS_BLOOD,RESULT_SYSTEM_HEMOGLOBIN);




    public static final String FILE_TYPE = "txt";
    public static final String UNICODE_RUS = "cp1251";
    public static final String FILE_NAME_VISIT_DOCTOR = String.format("visitDoctor").concat(".").concat(FILE_TYPE);
    public static final String FILE_NAME_ARIVIAL_DOCTOR = String.format("ArivialDoctor").concat(".").concat(FILE_TYPE);

    public static final String FILE_NAME_CALCULATE_PRICE = String.format("calculatePrice").concat(".").concat(FILE_TYPE);
    public static final String FILE_DELIMITER_ARIVIAL_DOCTOR_STATUS_OK = "\nСостояние не критическое: Выберите подходящее\n";
    public static final String FILE_DELIMITER_VISIT_DOCTOR = "\n---------------------------\n";


    public static final String DOCTOR_TYPE_GEMOTOLOG = "Gemotolog";
    public static final String DOCTOR_TYPE_ENDOCRINOLOG = "Endocriolog";
    public static final String DOCTOR_TYPE_LIPIDOLOG = "Lipidolog";



    public static final Double DOCTOR_COF_SALARY = 100.0;

    public static final List<String> DOCTOR_PRIORITY = Arrays.asList("Endocriolog","Lipidolog","Gemotolog");






    public static final String CLI_TYPE_BD = "dType";
    public static final String CLI_TYPE_BD_LONG = "dataType";


    public static final String CLI_ENVIRONMENT_PROPERTIES = "Dconfig";
    public static final String CLI_ENVIRONMENT_PROPERTIES_LONG = "Dconfig";


    public static final String CLI_LOGGER = "Dlog4j";
    public static final String CLI_LOGGER_LONG = "Dlog4j";

    public static final String ClI_NEW_DOCTOR="doctor";
    public static final String ClI_NEW_PATIENT="patient";
    public static final String ClI_NEW_PREPARATION="prep";
    public static final String ClI_NEW_SCHEDULE="sche";
    public static final String ClI_NEW_CALC_REPORT="report";
    public static final String ClI_ALL_DOCTOR="aD";

    public static final String ClI_NEW_DOCTOR_LONG="newDoctor";
    public static final String ClI_NEW_PATIENT_LONG="newPatient";
    public static final String ClI_NEW_PREPARATION_LONG="newPreparation";
    public static final String ClI_NEW_SCHEDULE_LONG="newSchedule";
    public static final String ClI_NEW_CALC_REPORT_LONG="newReport";
    public static final String ClI_ALL_DOCTOR_LONG="allDoctor";


    public static final String ClI_VISIT_DOCTOR="visitDoctor";
    public static final String ClI_CALCULATE_PRICE="calculatePrice";
    public static final String ClI_ARIVIAL_DOCTOR="arrivialDoctor";



    public static final String ClI_SELECT_DOCTOR="sd";
    public static final String ClI_SELECT_PATIENT="sp";
    public static final String ClI_SELECT_PREPARATION="spr";
    public static final String ClI_SELECT_SCHEDULE= "ss";
    public static final String ClI_SELECT_CALC_REPORT= "sc";


    public static final String CLI_DESCRIPTION_TYPE_BD = "Укажите тип провайдера";
    public static final String CLI_LOGGER_DESCRIPTION = "Активация консольного аппендера";
    public static final String CLI_ENVIRONMENT_PROPERTIES_DESCRIPTION = "Путь до настройки конфигурационного файла";
    public static final String ClI_DESCRIPTION_NEW_DOCTOR="Добавление нового Доктора";
    public static final String ClI_DESCRIPTION_NEW_PATIENT="Добавление нового Пациента";
    public static final String ClI_DESCRIPTION_NEW_PREPARATION="Добавление нового препарата";
    public static final String ClI_DESCRIPTION_NEW_SCHEDULE="Добавление нового расписания";
    public static final String ClI_DESCRIPTION_NEW_CALC_REPORT="Добавление нового отчета";
    public static final String ClI_DESCRIPTION_ALL_DOCTOR="Вывести всех врачей";

    public static final String ClI_DESCRIPTION_VISIT_DOCTOR="Получение результатов о состоянии.";
    public static final String ClI_DESCRIPTION_CALCULATE_PRICE="Формирование отчета и его сохранение. Генерация файла с данными.";
    public static final String ClI_DESCRIPTION_ARIVIAL_DOCTOR="Формирование подходящих дат для пациента.";


    public static final String ARIVIAL_DOCTOR_ANSWER_FOR_IN_PATIENT = "К сожалению ваш статус не позволяет вызвать врача на дом.\n" +
            "Однако вы можете выбрать Дату которая подойдет:";
    public static final String CLI_HELP = "help";
    public static final String CLI_DESCRIPTION_HELP = "Информация по использованию сервиса";

    public static final Integer TEST_DATA_ASSESSMENT_HEALTH = 55;
    public static final Double TEST_DATA_PRICE_CALCULATE_PRICE = 907.0;



    public static final String TEST_SCHEDULE_DATA_ID = "1";
    public static final String TEST_SCHEDULE_DATA_WEEK = "SATURDAY";
    public static final String TEST_SCHEDULE_DATA_DATE = "2024-04-23";
    public static final String TEST_SCHEDULE_DATA_BEGIN = "9:20:30";
    public static final String TEST_SCHEDULE_DATA_END = "18:23:20";
    public static final String TEST_SCHEDULE_DATA_STATUS = "FREE";


    public static final String TEST_PREPARATION_DATA_ID = "1";
    public static final String TEST_PREPARATION_DATA_NAME = "Ubeprofen";
    public static final String TEST_PREPARATION_DATA_DATE_END = "2024-12-20";
    public static final String TEST_PREPARATION_DATA_DOSAGE = "2.3";
    public static final String TEST_PREPARATION_DATA_VISIT = "LOW";
    public static final String TEST_PREPARATION_DATA_ABOUT = "this prep important";



    public static final String TEST_PATIENT_DATA_ID = "1";
    public static final String TEST_PATIENT_DATA_FIO = "Bob";
    public static final String TEST_PATIENT_DATA_AGE = "23";
    public static final String TEST_PATIENT_DATA_GENDER = "M";
    public static final String TEST_PATIENT_DATA_STATUS_PERSON = "PATIENT";
    public static final String TEST_PATIENT_DATA_CELLS_BLOOD = "6.6";
    public static final String TEST_PATIENT_DATA_HEMOGLOBIN = "160.0";
    public static final String TEST_PATIENT_DATA_PLATELETS = "200.";
    public static final String TEST_PATIENT_DATA_GLUCOSE = "80.3";
    public static final String TEST_PATIENT_DATA_CHOLESTEROL = "5.3";
    public static final String TEST_PATIENT_DATA_STATUS_PATIENT = "IN";


    public static final String TEST_DOCTOR_DATA_ID = "1";
    public static final String TEST_DOCTOR_DATA_FIO = "Sim Artem Evgen";
    public static final String TEST_DOCTOR_DATA_AGE = "29";
    public static final String TEST_DOCTOR_DATA_GENDER = "M";
    public static final String TEST_DOCTOR_DATA_STATUS_PERSON = "DOCTOR";
    public static final String TEST_DOCTOR_DATA_EXP = "12";
    public static final String TEST_DOCTOR_DATA_AVG_PATIENT = "12.3";
    public static final String TEST_DOCTOR_DATA_QUALIFICATION = "Med";
    public static final String TEST_DOCTOR_DATA_SPECIALIZATION = "Genikolog";



    public static final String TEST_CALC_REPORT_DATA_ID = "1";
    public static final String TEST_CALC_REPORT_DATA_FIO_PATIENT = "Bob";
    public static final String TEST_CALC_REPORT_DATA_FIO_DOCTOR = "Sim Artem Evgen";
    public static final String TEST_CALC_REPORT_DATA_BLOOD_ANALYSIS = "true";
    public static final String TEST_CALC_REPORT_DATA_GLUCOSE_ANALYSIS = "true";
    public static final String TEST_CALC_REPORT_DATA_ARTERIAL_ANALYSIS = "true";
    public static final String TEST_CALC_REPORT_DATA_PRICE = "100.0";


    public static final String GENDER_SERVIS_M = "M";

    public static final Integer LIMIT_AGE_SERVIS = 55;


    public static final Integer POINT_AGE_SERVIS = 5;
    public static final Integer POINT_BLOOD_HEALTH = 25;
    public static final Integer POINT_HEMOGLOBIN_HEALTH = 20;
    public static final Integer POINT_PLATELETS_HEALTH = 15;
    public static final Integer POINT_GLUCOSE_HEALTH = 25;
    public static final Integer POINT_CHOLESTEROL_HEALTH = 10;


    public static final Integer TRIGGER_POINT_HEALTH = 60;
    public static final Integer TRIGGER_POINT_HEALTH_POSITIVE = 85;
    public static final Integer AVG_DIV_COF = 2;

    public static final Double COF_LOW = 0.0;
    public static final Double COF_UP = 5.0;








}
