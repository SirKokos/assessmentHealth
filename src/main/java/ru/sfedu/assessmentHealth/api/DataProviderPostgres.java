package ru.sfedu.assessmentHealth.api;

import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.CommandType;
import ru.sfedu.assessmentHealth.model.Preparation;

import java.sql.*;

public class DataProviderPostgres {

    String url = CONST.BD_POSTGRES_HOST.concat(CONST.BD_POSTGRES_NAME);

    Connection connection;

    public DataProviderPostgres() throws SQLException {
        try {
            connection = DriverManager.getConnection(url,CONST.BD_POSTGRES_USER, CONST.BD_POSTGRES_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void select(){
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from doctor;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name")+" " +rs.getInt("age"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
