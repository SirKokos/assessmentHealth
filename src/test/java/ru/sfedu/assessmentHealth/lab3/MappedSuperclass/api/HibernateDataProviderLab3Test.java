package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.api;

import lombok.extern.slf4j.Slf4j;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model.Doctor;


@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab3Test extends BaseTestLab3{
    @Test
    @Order(1)
    void saveRecord() {
        log.debug("saveRecord [1]: process load obj BD");
        hibernateDataProviderLab3.saveRecord(getDoctor());
        log.debug("saveRecord [2]: end load obj BD");
    }

    @Test
    @Order(2)
    void deleteRecord() {
        log.debug("deleteRecord [1]: process delete");
//        hibernateDataProviderLab3.deleteRecord(getDoctor());
        log.debug("deleteRecord [2]: end process delete");
    }

    @Test
    @Order(3)
    void updateRecord() {
        log.debug("updateRecord [1]: process update");
        Doctor doctor = getDoctor();
        doctor.setFio("Bob SSSSOOOSS");
        hibernateDataProviderLab3.updateRecord(doctor);
        log.debug("updateRecord [2]: end process update");
    }

    @Test
    @Order(4)
    void getRecord() {
        log.debug("getRecord [1]: process get obj");
        Object obj = hibernateDataProviderLab3.getRecord(Doctor.class,1);
        log.debug("getRecord [2]: end process get obj = {}",obj);
    }
}