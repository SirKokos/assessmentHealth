package ru.sfedu.assessmentHealth;

import java.security.PublicKey;
import java.util.Locale;

public class CONST {

    public static final String log4jPath = "src/main/resources/log4j2.properties";
    public static final String PATH_CONST_PROPERTIES = "src/main/resources/CONST.properties";
    public static final String PATH_CONST_XML = "src/main/resources/CONST.xml";
    public static final String PATH_CONST_YML = "src/main/resources/CONST.yml";


    public static final String NAME_ACTOR_HISTORY = "system";

    public static final  String EXAMPLE = "ru.sfedu.";

    public static final String BD_MONGO_HOST = "mongodb://localhost:27017";
    public static final String BD_MONGO_COLLECTION = "HistoryClass";
    public static final String BD_MONGO_NAME = "assessmentHealth";

    public static final String BD_POSTGRES_HOST = "jdbc:postgresql://localhost:5432/";
    public static final String BD_POSTGRES_NAME = "assessmentHealth";
    public static final String BD_POSTGRES_USER = "postgres";
    public static final String BD_POSTGRES_PASSWORD = "root";

    public static final char CSV_DEFAULT_SEPARATOR = ',';
    public static final String CSV_PATH_FOLDER = "csv/";
    public static final String CSV_FILE_TYPE = ".csv";
//-----------------------------------------------------------------------------------------

    public static final String XML_PATH_FOLDER = "xml/";
    public static final String XML_FILE_TYPE = ".xml";
//-----------------------------------------------------------------------------------------


    public static final String MONGO_FIELD_TIME = "time";
    public static final String MONGO_FIELD_COMMAND = "command";
    public static final String MONGO_FIELD_OBJECT = "item";
//-----------------------------------------------------------------------------------------
    public static final String NAME_FIELD_REPORT_ID = "Report_ID";
    public static final String NAME_FIELD_REPORT_FK_TO_DOCTOR = "FkToDoctor";
    public static final String NAME_FIELD_REPORT_FK_TO_PATIENT = "FkToPatient";
    public static final String NAME_FIELD_REPORT_NAME_DOCTOR = "NameDoctor";
    public static final String NAME_FIELD_REPORT_NAME_PATIENT = "PatientName";
    public static final String NAME_FIELD_REPORT_BLOOD_ANALYSE = "BloodAnalyse";
    public static final String NAME_FIELD_REPORT_GLUCOSE_ANALYSE = "GlucoseAnalyse";
    public static final String NAME_FIELD_REPORT_HORMONE_ANALYSE = "HormoneAnalyse";
    public static final String NAME_FIELD_REPORT_ARTERIAL_ANALYSE = "ArterialAnalyse";
    public static final String NAME_FIELD_REPORT_PRICE = "price";
    public static final int POSITION_REPORT_ID = 0;
//-----------------------------------------------------------------------------------------
    public static final String NAME_FIELD_NAME = "Name";
    public static final String NAME_FIELD_SURNAME = "Surname";
    public static final String NAME_FIELD_SECONDNAME = "SecondName";
    public static final String NAME_FIELD_AGE = "Age";
    public static final String NAME_FIELD_GENDER = "Gender";
    public static final String NAME_FIELD_STATUS = "Status";
    public static final String NAME_FIELD_PERSON_ID = "Person_ID";
    public static final int POSITION_PERSON_ID = 0;
//-----------------------------------------------------------------------------------------

    public static final String NAME_FIELD_DOCTOR_EXPERIENCE = "Experience";
    public static final String NAME_FIELD_DOCTOR_AVGPATIENT = "AvgPatient";
    public static final String NAME_FIELD_DOCTOR_QUALIFICATION = "Qualification";
    public static final String NAME_FIELD_DOCTOR_SPECIALIZATION = "Specialization";
//-----------------------------------------------------------------------------------------
    public static final String NAME_FIELD_PATIENT_ID = "patient_id";
    public static final String NAME_FIELD_PATIENT_CELLS_BLOOD = "CellsBlood";
    public static final String NAME_FIELD_PATIENT_HEMOGLOBIN = "Hemoglobin";
    public static final String NAME_FIELD_PATIENT_PLATELETS = "Platelets";
    public static final String NAME_FIELD_PATIENT_TESTOSTERONE = "Testosterone";
    public static final String NAME_FIELD_PATIENT_GLUCOSE = "Glucose";
    public static final String NAME_FIELD_PATIENT_CHOLESTEROL = "Cholesterol";
    public static final String NAME_FIELD_PATIENT_ARTERIAL_PRESS = "ArterialPress";
    public static final String NAME_FIELD_PATIENT_STATUS_VISIT = "statusVisit";
    public static final int POSITION_PATIENT_ID = 0;
//-----------------------------------------------------------------------------------------
    public static final String NAME_FIELD_PREPARATION_ID = "Preparation_ID";
    public static final String NAME_FIELD_FK_PREPARATION_TO_DOCTOR = "FkPreparationToDoctor";
    public static final String NAME_FIELD_PREPARATION_NAME_PREP = "NamePrep";
    public static final String NAME_FIELD_PREPARATION_DAE_END = "DateEnd";
    public static final String NAME_FIELD_PREPARATION_DOSAGE = "Dosage";
    public static final String NAME_FIELD_PREPARATION_STATUS_VISIT_PREPARATION = "statusVisitPreparation";
    public static final String NAME_FIELD_PREPARATION_ABOUT_PREP = "AboutPrep";
    public static final String NAME_FIELD_PREPARATION_COUNT_PREP = "CountPrep";
    public static final int POSITION_PREPARATION_ID = 0;
//-----------------------------------------------------------------------------------------
    public static final String NAME_FIELD_SCHEDULE_ID = "Schedule_ID";
    public static final String NAME_FIELD_SCHEDULE_FK_TO_DOCTOR = "FkToDoctor";
    public static final String NAME_FIELD_SCHEDULE_DATE_WEEK = "dateWeek";
    public static final String NAME_FIELD_SCHEDULE_DATE_SCHEDULE = "DateSchedule";
    public static final String NAME_FIELD_SCHEDULE_TIME_BEGIN = "TimeBegin";
    public static final String NAME_FIELD_SCHEDULE_TIME_END = "TimeEnd";
    public static final String NAME_FIELD_SCHEDULE_STATUS_SCHEDULE = "statusSchedule";
    public static final int POSITION_SCHEDULE_ID = 0;

//-----------------------------------------------------------------------------------------

    public static final String KEY_PERSON = "Person_ID";
    public static final String KEY_DOCTOR = "Doctor_ID";
    public static String KEY_PATIENT = "Patient_ID";
    public static String KEY_PREPARATION = "Preparation_ID";
    public static String FK_PREPARATION_TO_DOCTOR = "FkPreparationToDoctor";
    public static String KEY_SCHEDULE = "Schedule_ID";
    public static String KEY_CALC_REPORT = "Report_ID";
    public static String FK_CALC_REPORT_TO_DOC = "FkToDoctor";
    public static String FK_SCHEDULE_TO_DOC = "FkToDoctor";
    public static String FK_CALC_REPORT_TO_PAT = "FkToPatient";
//-----------------------------------------------------------------------------------------


    public static final String SQL_TABLE_NAME_DOCTOR = "DOCTOR";
    public static final String SQL_TABLE_NAME_PATIENT = "PATIENT";
    public static final String SQL_TABLE_NAME_CALC_REPORT = "CALCREPORT";
    public static final String SQL_TABLE_NAME_PREPARATION = "PREPARATION";
    public static final String SQL_TABLE_NAME_SCHEDULE = "SCHEDULE";

//-----------------------------------------------------------------------------------------


//-----------------------------------------------------------------------------------------
    public static final String SQL_TABLE_CREATE_DOCTOR= "CREATE TABLE IF NOT EXISTS ".concat(SQL_TABLE_NAME_DOCTOR).concat("(").concat(KEY_DOCTOR).concat(" SERIAL PRIMARY KEY,").concat("Name VARCHAR(30)," +
            "Surname VARCHAR(30)," +
            "SecondName VARCHAR(30)," +
            "Age INT,Gender CHAR(1)," +
            "Status VARCHAR(20)," +
            "Experience INTEGER," +
            "AvgPatient DECIMAL," +
            "Qualification VARCHAR(60)," +
            "Specialization VARCHAR(60)").concat(");");
    public static final String SQL_TABLE_CREATE_PATIENT= "CREATE TABLE IF NOT EXISTS ".concat(SQL_TABLE_NAME_PATIENT).concat("(").concat(KEY_PATIENT).concat(" SERIAL PRIMARY KEY,\n" +
            "Name VARCHAR(30),\n" +
            "Surname VARCHAR(30),\n" +
            "SecondName VARCHAR(30),\n" +
            "Age INT,\n" +
            "Gender CHAR(1),\n" +
            "Status VARCHAR(20),\n" +
            "CellsBlood DECIMAL,\n" +
            "Hemoglobin DECIMAL,\n" +
            "Platelets DECIMAL,\n" +
            "Testosterone DECIMAL,\n" +
            "Glucose DECIMAL,\n" +
            "Cholesterol DECIMAL,\n" +
            "ArterialPress SMALLINT,\n" +
            "statusVisit VARCHAR(20)\n").concat(");");
    public static final String SQL_TABLE_CREATE_CALC_REPORT= "CREATE TABLE IF NOT EXISTS ".concat(SQL_TABLE_NAME_CALC_REPORT).concat("(").concat(KEY_CALC_REPORT).concat(" SERIAL PRIMARY KEY,\n" +
            "FkToDoctor INTEGER, \n" +
            "FkToPatient INTEGER,\n" +
            "PatientName VARCHAR(30),\n" +
            "NameDoctor VARCHAR(30),\n" +
            "BloodAnalyse BOOLEAN,\n" +
            "GlucoseAnalyse BOOLEAN,\n" +
            "HormoneAnalyse BOOLEAN,\n" +
            "ArterialAnalyse BOOLEAN,\n" +
            "price DECIMAL,\n" +
            "".concat(" CONSTRAINT ").concat(FK_CALC_REPORT_TO_DOC).concat(" FOREIGN KEY ").concat(" (").concat(FK_CALC_REPORT_TO_DOC).concat(") ").concat(" REFERENCES ").concat(SQL_TABLE_NAME_DOCTOR).concat("(").concat(KEY_DOCTOR).concat("),") +"\n"+
            "".concat(" CONSTRAINT ").concat(FK_CALC_REPORT_TO_PAT).concat(" FOREIGN KEY ").concat(" (").concat(FK_CALC_REPORT_TO_PAT).concat(") ").concat(" REFERENCES ").concat(SQL_TABLE_NAME_PATIENT).concat("(").concat(KEY_PATIENT).concat(")")+ "" +
            "".concat(");")
    );
    public static final String SQL_TABLE_CREATE_PREPARATION= "CREATE TABLE IF NOT EXISTS ".concat(SQL_TABLE_NAME_PREPARATION).concat("(").concat(KEY_PREPARATION).concat(" SERIAL PRIMARY KEY,\n" +
            "FkPreparationToDoctor INTEGER,\n" +
            "NamePrep VARCHAR(100),\n" +
            "DateEnd\tDATE,\n" +
            "Dosage DECIMAL,\n" +
            "statusVisitPreparation VARCHAR(50),\n" +
            "AboutPrep TEXT,\n" +
            "CountPrep INT ".concat(");"));
//            "".concat("CONSTRAINT ").concat(FK_PREPARATION_TO_DOCTOR).concat(" FOREIGN KEY ").concat(" (").concat(FK_PREPARATION_TO_DOCTOR).concat(") ").concat(" REFERENCES ").concat(SQL_TABLE_NAME_DOCTOR).concat("(").concat(KEY_DOCTOR).concat(")").concat(");"));
    public static final String SQL_TABLE_CREATE_SCHEDULE= "CREATE TABLE IF NOT EXISTS ".concat(SQL_TABLE_NAME_SCHEDULE).concat(" (").concat(KEY_SCHEDULE).concat(" SERIAL PRIMARY KEY,\n" +
            "FkToDoctor INT,\n" +
            "dateWeek VARCHAR(50),\n" +
            "DateSchedule DATE,\n" +
            "TimeBegin TIME,\n" +
            "TimeEnd TIME,\n" +
            "statusSchedule VARCHAR(20),\n" +
            "CONSTRAINT ".concat(FK_SCHEDULE_TO_DOC).concat(" FOREIGN KEY").concat(" (").concat(FK_SCHEDULE_TO_DOC).concat(" )").concat("  REFERENCES ").concat(SQL_TABLE_NAME_DOCTOR).concat("(").concat(KEY_DOCTOR).concat(")").concat(" ON DELETE CASCADE").concat(");"));
//-----------------------------------------------------------------------------------------
    public static final String SELECT_DOCTOR_TO_ID = String.format("SELECT * FROM %s WHERE %s",SQL_TABLE_NAME_DOCTOR,KEY_DOCTOR).concat(" = ?");
    public static final String SELECT_PATIENT_TO_ID = String.format("SELECT * FROM %s WHERE %s",SQL_TABLE_NAME_PATIENT,KEY_PATIENT).concat(" = ?");
    public static final String SELECT_PREPARATION_TO_ID = String.format("SELECT * FROM %s WHERE %s",SQL_TABLE_NAME_PREPARATION,KEY_PREPARATION).concat(" = ?");
    public static final String SELECT_SCHEDULE_TO_ID = String.format("SELECT * FROM %s WHERE %s",SQL_TABLE_NAME_SCHEDULE,KEY_SCHEDULE).concat(" = ?");
    public static final String SELECT_CALC_REPORT_TO_ID = String.format("SELECT * FROM %s WHERE %s",SQL_TABLE_NAME_CALC_REPORT,KEY_CALC_REPORT).concat(" = ?");
    public static final String SELECT_CALC_REPORT_TO_ID_DOCTOR = String.format("SELECT * FROM %s WHERE %s",SQL_TABLE_NAME_CALC_REPORT,FK_CALC_REPORT_TO_DOC).concat(" = ?");


//    public static final String INSERT_DOCTOR = String.format("INSERT INTO %s (Doctor_ID, Name, Surname, SecondName, Age, Gender, Status, Experience, AvgPatient, Qualification, Specialization) ",SQL_TABLE_NAME_DOCTOR).concat("\nVALUES (%d, '%s', '%s', '%s', %d, '%c', '%s', %d, %.2f, '%s', '%s')");
    public static final String INSERT_DOCTOR = String.format("INSERT INTO %s (Doctor_ID, Name, Surname, SecondName, Age, Gender, Status, Experience, AvgPatient, Qualification, Specialization) ",SQL_TABLE_NAME_DOCTOR).concat("\nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

//    public static final String INSERT_PATIENT = String.format("INSERT INTO %s (Patient_ID, Name, Surname, SecondName, Age, Gender, Status, CellsBlood, Hemoglobin, Platelets, Testosterone, Glucose, Cholesterol, ArterialPress, statusVisit) ",SQL_TABLE_NAME_PATIENT).concat("\nVALUES (%d, '%s', '%s', '%s', %d, '%c', '%s', %.2f, %.2f, %.2f, %.2f, %.2f,%.2f,%d,'%s')");
    public static final String INSERT_PATIENT = String.format("INSERT INTO %s (Patient_ID, Name, Surname, SecondName, Age, Gender, Status, CellsBlood, Hemoglobin, Platelets, Testosterone, Glucose, Cholesterol, ArterialPress, statusVisit) ",SQL_TABLE_NAME_PATIENT).concat("\nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");

//    public static final String INSERT_PREPARATION = String.format("INSERT INTO %s (Preparation_ID,FkPreparationToDoctor,NamePrep,DateEnd,Dosage,statusVisitPreparation,AboutPrep,countprep) ",SQL_TABLE_NAME_PREPARATION).concat("\nVALUES (%d, %d, '%s', '%s', %.2f, '%s', '%s', %d)");
    public static final String INSERT_PREPARATION = String.format("INSERT INTO %s (Preparation_ID,FkPreparationToDoctor,NamePrep,DateEnd,Dosage,statusVisitPreparation,AboutPrep,countprep) ",SQL_TABLE_NAME_PREPARATION).concat("\nVALUES (?, ?, ?, ?, ?, ?, ?, ?)");

//    public static final String INSERT_SCHEDULE = String.format("INSERT INTO %s (schedule_id,FkToDoctor,dateweek,dateschedule,timebegin,timeend,statusschedule)",SQL_TABLE_NAME_SCHEDULE).concat("\nVALUES(%d, %d,'%s', '%s', '%s', '%s', '%s')");
    public static final String INSERT_SCHEDULE = String.format("INSERT INTO %s (schedule_id,FkToDoctor,dateweek,dateschedule,timebegin,timeend,statusschedule)",SQL_TABLE_NAME_SCHEDULE).concat("\nVALUES(?, ?,?, ?, ?, ?, ?)");

//    public static final String INSERT_CALC_REPORT = String.format("INSERT INTO %s (Report_ID, FkToDoctor, FkToPatient, PatientName, NameDoctor, BloodAnalyse, GlucoseAnalyse, HormoneAnalyse, ArterialAnalyse, price) ",SQL_TABLE_NAME_CALC_REPORT).concat("\nVALUES (%d, %d, %d, '%s', '%s', %b, %b, %b, %b, %.2f)");
    public static final String INSERT_CALC_REPORT = String.format("INSERT INTO %s (Report_ID, FkToDoctor, FkToPatient, PatientName, NameDoctor, BloodAnalyse, GlucoseAnalyse, HormoneAnalyse, ArterialAnalyse, price) ",SQL_TABLE_NAME_CALC_REPORT).concat("\nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

//-----------------------------------------------------------------------------------------
    public static final String DELETE_DOCTOR_TO_ID = String.format("DELETE FROM %s WHERE %s",SQL_TABLE_NAME_DOCTOR,KEY_DOCTOR).concat(" = ?");
    public static final String DELETE_PATIENT_TO_ID = String.format("DELETE FROM %s WHERE %s",SQL_TABLE_NAME_PATIENT,KEY_PATIENT).concat(" = ?");
    public static final String DELETE_PREPARATION_TO_ID = String.format("DELETE FROM %s WHERE %s",SQL_TABLE_NAME_PREPARATION,KEY_PREPARATION).concat(" = ?");
    public static final String DELETE_SCHEDULE_TO_ID = String.format("DELETE FROM %s WHERE %s",SQL_TABLE_NAME_SCHEDULE,KEY_SCHEDULE).concat(" = ?");
    public static final String DELETE_CALC_REPORT_TO_ID = String.format("DELETE FROM %s WHERE %s",SQL_TABLE_NAME_CALC_REPORT,KEY_CALC_REPORT).concat(" = ?");


}

