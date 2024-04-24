package ru.sfedu.assessmentHealth.lab4.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab4Test extends BaseTestLab4 {



    @Test
    @Order(1)
    void saveRecordPreparation() {
        log.debug("saveRecordPreparation [1]: save obj list set");
        hibernateDataProviderLab4.saveRecord(getPreparation());
        log.debug("saveRecordPreparation [2]: end save obj list set");
    }

    @Test
    @Order(2)
    void saveRecordSchedule() {
        log.debug("saveRecordSchedule [1]: save obj list set");
        hibernateDataProviderLab4.saveRecord(getSchedule());

        log.debug("saveRecordSchedule [2]: end save obj list set");
    }


    @Test
    @Order(3)
    void saveRecordListSet() {
    log.debug("saveRecordListSet [1]: save obj list set");
    hibernateDataProviderLab4.saveRecord(getDoctor());
    log.debug("saveRecordListSet [2]: end save obj list set");
    }

    @Test
    @Order(4)
    void saveRecordPatient() {
        log.debug("saveRecordPatient [1]: save obj list set");
        hibernateDataProviderLab4.saveRecord(getPatient());
        log.debug("saveRecordPatient [2]: end save obj list set");
    }
    @Test
    @Order(5)
    void saveRecordCalcReport() {
        log.debug("saveRecordCalcReport [1]: save obj list set");
        hibernateDataProviderLab4.saveRecord(getCalcReport());
        log.debug("saveRecordCalcReport [2]: end save obj list set");
    }



    @Test
    void deleteRecord() {
    }

    @Test
    void updateRecord() {
    }

    @Test
    void getRecord() {
    }




}