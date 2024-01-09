package ru.sfedu.assessmentHealth;

public class Const {
    public static final String NAME_PROPERTY_FILE = "src/main/resources/BdConnection.properties";
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

}
