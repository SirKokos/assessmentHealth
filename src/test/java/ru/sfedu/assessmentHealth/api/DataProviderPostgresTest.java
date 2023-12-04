package ru.sfedu.assessmentHealth.api;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderPostgresTest {
    
    
    @Test
    void conect(){
        DataProviderPostgres p = null;
        
            try {
                p = new DataProviderPostgres();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                System.out.println(p);
            }


            
    }


    @Test
    void select() {
    }
}