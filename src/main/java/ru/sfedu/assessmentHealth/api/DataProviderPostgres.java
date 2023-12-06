package ru.sfedu.assessmentHealth.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataProviderPostgres implements DataCommandCrud{
    private final Logger log = LogManager.getLogger(DataProviderPostgres.class);
    String url = CONST.BD_POSTGRES_HOST.concat(CONST.BD_POSTGRES_NAME);
    Connection connection;



    public DataProviderPostgres() throws SQLException {
        try {
            connection = DriverManager.getConnection(url,CONST.BD_POSTGRES_USER, CONST.BD_POSTGRES_PASSWORD);
            createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    protected void createTables(){
        log.debug("createTables [1] - start working method ");
        List<String> nameTab = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CONST.SQL_TABLE_CREATE_DOCTOR);
            statement.executeUpdate(CONST.SQL_TABLE_CREATE_PATIENT);
            statement.executeUpdate(CONST.SQL_TABLE_CREATE_PREPARATION);
            statement.executeUpdate(CONST.SQL_TABLE_CREATE_SCHEDULE);
            statement.executeUpdate(CONST.SQL_TABLE_CREATE_CALC_REPORT);
            ResultSet resultSet = connection.getMetaData().getTables(null,null,"%", new String[]{"TABLE"});
            while (resultSet.next()) {
                nameTab.add(resultSet.getString("TABLE_NAME"));
            }
            statement.close();
//            connection.close();
        } catch (SQLException e) {
            log.error("DataProviderPostgres [2] - initialization error {}", e.getMessage());
        }
        log.debug("createTables [3]: - successfully {}" , nameTab);
    }


    @Override
    public  void InsertDataDoctor(Doctor obj){
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(obj);
        Map<String,Object> mapObj = wrap.getObject();
        int rowsInserted = 0;
        log.debug("InsertDataDoctor [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.INSERT_DOCTOR);
            statement.setInt(1, (Integer) mapObj.get("Person_ID"));
            statement.setString(2, (String) mapObj.get("Name"));
            statement.setString(3, (String) mapObj.get("Surname"));
            statement.setString(4, (String) mapObj.get("SecondName"));
            statement.setInt(5, (Integer) mapObj.get("Age"));
            statement.setString(6, String.valueOf(mapObj.get("Gender")));
            statement.setString(7, mapObj.get("Status").toString());
            statement.setInt(8, (Integer) mapObj.get("Experience"));
            statement.setDouble(9, (Double) mapObj.get("AvgPatient"));
            statement.setString(10, (String) mapObj.get("Qualification"));
            statement.setString(11, (String) mapObj.get("Specialization"));
            rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            log.error("InsertDataDoctor [2]: - error {}" ,e.getMessage());
        }
        if (rowsInserted > 0) {
            log.debug("InsertDataDoctor [3]: insert Data successfully {}",mapObj);
        }
    }

    @Override
    public void InsertDataPatient(Patient obj){
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(obj);
        Map<String,Object> mapObj = wrap.getObject();
        int rowsInserted = 0;
        log.debug("InsertDataPatient [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.INSERT_PATIENT);
            statement.setInt(1, (Integer) mapObj.get("Person_ID"));
            statement.setString(2, (String) mapObj.get("Name"));
            statement.setString(3, (String) mapObj.get("Surname"));
            statement.setString(4, (String) mapObj.get("SecondName"));
            statement.setInt(5, (Integer) mapObj.get("Age"));
            statement.setString(6, String.valueOf(mapObj.get("Gender")));
            statement.setString(7, mapObj.get("Status").toString());
            statement.setDouble(8, (Double) mapObj.get("CellsBlood"));
            statement.setDouble(9, (Double) mapObj.get("Hemoglobin"));
            statement.setDouble(10, (Double) mapObj.get("Platelets"));
            statement.setDouble(11, (Double) mapObj.get("Testosterone"));
            statement.setDouble(12, (Double) mapObj.get("Glucose"));
            statement.setDouble(13, (Double) mapObj.get("Cholesterol"));
            statement.setInt(14, (short) mapObj.get("ArterialPress"));
            statement.setString(15, mapObj.get("statusVisit").toString());

            rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            log.error("InsertDataPatient  [2]: - error {}" ,e.getMessage());
        }
        if (rowsInserted > 0){
            log.debug("InsertDataPatient [3]: insert Data successfully {}",mapObj);
        }
    }
    @Override
    public void InsertDataPreparation(Preparation obj, Integer FkPreparationToDoctor, String dateEnd){
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(obj);
        Map<String,Object> mapObj = wrap.getObject();
        mapObj.put("DateEnd",Date.valueOf(dateEnd));
        log.debug("InsertDataPreparation [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.INSERT_PREPARATION);
            statement.setInt(1, (Integer) mapObj.get("Preparation_ID"));
            statement.setInt(2, FkPreparationToDoctor);
            statement.setString(3, (String) mapObj.get("NamePrep"));
            statement.setDate(4, (Date) mapObj.get("DateEnd"));
            statement.setDouble(5,(Double) mapObj.get("Dosage"));
            statement.setString(6,mapObj.get("statusVisitPreparation").toString());
            statement.setString(7,mapObj.get("AboutPrep").toString());
            statement.setInt(8,(Integer) mapObj.get("CountPrep"));

            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("InsertDataPreparation  [2]: - error {}" ,e.getMessage());
        }
            log.debug("InsertDataPreparation [3]: insert Data successfully {}",mapObj);
    }

    @Override
    public void InsertDataSchedule(Schedule obj, String dateSchedule, String timeBegin, String timeEnd){
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(obj);
        Map<String,Object> mapObj = wrap.getObject();
        mapObj.put("DateSchedule",Date.valueOf(dateSchedule));
        mapObj.put("TimeBegin", Time.valueOf(timeBegin));
        mapObj.put("TimeEnd",Time.valueOf(timeEnd));

        log.debug("InsertDataSchedule [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.INSERT_SCHEDULE);
            statement.setInt(1, (Integer) mapObj.get("Schedule_ID"));
            statement.setInt(2, (Integer) mapObj.get("FkToDoctor"));
            statement.setString(3, mapObj.get("dateWeek").toString());
            statement.setDate(4, (Date) mapObj.get("DateSchedule"));
            statement.setTime(5, (Time) mapObj.get("TimeBegin"));
            statement.setTime(6, (Time) mapObj.get("TimeEnd"));
            statement.setString(7,mapObj.get("statusSchedule").toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("InsertDataSchedule [2]: - error {}" ,e.getMessage());
        }
        log.debug("InsertDataSchedule [3]: insert Data successfully {}",mapObj);
    }

    @Override
    public void InsertDataCalcReport(CalcReport obj){
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(obj);
        Map<String,Object> mapObj = wrap.getObject();
        log.debug("InsertDataCalcReport [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.INSERT_CALC_REPORT);
            statement.setInt(1, (Integer) mapObj.get("Report_ID"));
            statement.setInt(2, (Integer) mapObj.get("FkToDoctor"));
            statement.setInt(3, (Integer) mapObj.get("FkToPatient"));
            statement.setString(4, (String) mapObj.get("PatientName"));
            statement.setString(5, (String) mapObj.get("NameDoctor"));
            statement.setBoolean(6,(Boolean) mapObj.get("BloodAnalyse"));
            statement.setBoolean(7,(Boolean) mapObj.get("GlucoseAnalyse"));
            statement.setBoolean(8,(Boolean) mapObj.get("HormoneAnalyse"));
            statement.setBoolean(9,(Boolean) mapObj.get("ArterialAnalyse"));
            statement.setDouble(10,(Double) mapObj.get("price"));
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("InsertDataCalcReport [2]: - error {}" ,e.getMessage());
        }
        log.debug("InsertDataCalcReport [3]: insert Data successfully {}",mapObj);
    }

    public Map<String,Object> createResultSetToObject(ResultSet result) throws SQLException {
        log.debug("createResultSetToObject [1]: - start working method");
        Map<String,Object> resultSet = new HashMap<>();
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();
        try {
            while(result.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = result.getObject(i);
                    resultSet.put(columnName, columnValue);
                }
            }
        }catch (Exception e){
            log.error("createResultSetToObject [2]: - ERROR {}" ,e.getMessage());
        }
        log.debug("createResultSetToObject [3]: - successfully end work");
        return resultSet;
    }
    @Override
    public Map<String,Object> selectDoctorId(Integer Doctor_Id){
        log.debug("selectDoctorId [1]: - start working method");
        Map<String,Object> resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.SELECT_DOCTOR_TO_ID);
            statement.setInt(1,Doctor_Id);
            ResultSet result = statement.executeQuery();

            resultSet = createResultSetToObject(result);

        } catch (SQLException e) {
            log.error("selectDoctorId [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("selectDoctorId [3]: - SELECT DATA DOCTOR successfully");


        return resultSet;
    }

    @Override
    public Map<String,Object> selectPatientId(Integer Patient_Id){
        log.debug("selectPatientId [1]: - start working method");
        Map<String,Object> resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.SELECT_PATIENT_TO_ID);
            statement.setInt(1,Patient_Id);
            ResultSet result = statement.executeQuery();

            resultSet = createResultSetToObject(result);

        } catch (SQLException e) {
            log.error("selectPatientId [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("selectPatientId [3]: - SELECT DATA Patient successfully");
        return resultSet;
    }
    @Override
    public Map<String,Object> selectPreparationId(Integer Preparation_Id){
        log.debug("selectPreparationId [1]: - start working method");
        Map<String,Object> resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.SELECT_PREPARATION_TO_ID);
            statement.setInt(1,Preparation_Id);
            ResultSet result = statement.executeQuery();

            resultSet = createResultSetToObject(result);

        } catch (SQLException e) {
            log.error("selectPreparationId [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("selectPreparationId[3]: - SELECT DATA Preparation successfully");
        return resultSet;
    }
    @Override
    public Map<String,Object> selectScheduleId(Integer Schedule_Id){
        log.debug("selectPreparationId [1]: - start working method");
        Map<String,Object> resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.SELECT_SCHEDULE_TO_ID);
            statement.setInt(1,Schedule_Id);
            ResultSet result = statement.executeQuery();

            resultSet = createResultSetToObject(result);

        } catch (SQLException e) {
            log.error("selectPreparationId [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("selectPreparationId[3]: - SELECT DATA Preparation successfully");
        return resultSet;
    }
    @Override
    public Map<String,Object> selectCalcReportId(Integer CalcReport_Id){
        log.debug("selectCalcReportId [1]: - start working method");
        Map<String,Object> resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.SELECT_CALC_REPORT_TO_ID);
            statement.setInt(1,CalcReport_Id);
            ResultSet result = statement.executeQuery();

            resultSet = createResultSetToObject(result);

        } catch (SQLException e) {
            log.error("selectCalcReportId [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("selectCalcReportId[3]: - SELECT DATA Preparation successfully");
        return resultSet;
    }
    @Override
    public void deleteDataDoctor(Integer Doctor_ID){
        log.debug("deleteDataDoctor [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.DELETE_DOCTOR_TO_ID);
            statement.setInt(1,Doctor_ID);
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("deleteDataDoctor [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("deleteDataDoctor [3]: - DELETE DATA Doctor successfully");
    }
    @Override
    public void deleteDataPatient(Integer Patient_ID){
        log.debug("deleteDataPatient [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.DELETE_PATIENT_TO_ID);
            statement.setInt(1,Patient_ID);
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("deleteDataPatient [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("deleteDataPatient [3]: - DELETE DATA Patient successfully");
    }
    @Override
    public void deleteDataPreparation(Integer Preparation_ID){
        log.debug("deleteDataPreparation [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.DELETE_PREPARATION_TO_ID);
            statement.setInt(1,Preparation_ID);
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("deleteDataPreparation [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("deleteDataPreparation [3]: - DELETE DATA Preparation successfully");
    }
    @Override
    public void deleteDataSchedule(Integer Schedule_ID){
        log.debug("deleteDataSchedule [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.DELETE_SCHEDULE_TO_ID);
            statement.setInt(1,Schedule_ID);
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("deleteDataSchedule [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("deleteDataSchedule [3]: - DELETE DATA Schedule successfully");
    }

    @Override
    public void deleteDataCalcReport(Integer CalcReport_ID){
        log.debug("deleteDataCalcReport [1]: - start working method");
        try {
            PreparedStatement statement = connection.prepareStatement(CONST.DELETE_CALC_REPORT_TO_ID);
            statement.setInt(1,CalcReport_ID);
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("deleteDataCalcReport [2]: - ERROR {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("deleteDataCalcReport [3]: - DELETE DATA CalcReport successfully");
    }


}
