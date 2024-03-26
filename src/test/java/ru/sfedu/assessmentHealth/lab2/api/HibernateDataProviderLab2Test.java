package ru.sfedu.assessmentHealth.lab2.api;

import com.mchange.v2.cfg.PropertiesConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.lab1.utils.HibernateUtil;
import ru.sfedu.assessmentHealth.lab2.model.StatusResponse;
import ru.sfedu.assessmentHealth.lab2.model.TestEntity;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HibernateDataProviderLab2Test extends BaseTestLab2 {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderLab2Test.class.getName());


    @Test
    void saveTestEntity(){
        log.debug("saveTestEntity [1]: process load obj BD");
        StatusResponse expected = hibernateDataProviderLab2.saveTestEntity(getTestEntity());
        assertEquals(expected,StatusResponse.OK);
        log.debug("saveTestEntity [2]: end working");
    }

    @Test
    void selectTestEntity() {
        log.debug("selectTestEntity [1]: process select TestEntity ");
        TestEntity expected = hibernateDataProviderLab2.selectTestEntity(1L);
        log.debug("selectTestEntity [2]: result  = {}",expected);
    }

    @Test
    void deleteTestEntity() {
        log.debug("deleteTestEntity [1]: process select TestEntity ");
        StatusResponse expected = hibernateDataProviderLab2.deleteTestEntity(2L);
        assertEquals(expected,StatusResponse.OK);
        log.debug("deleteTestEntity [2]: end working");
    }

    @Test
    void updateTestEntity() {
        log.debug("updateTestEntity [1]: process update TestEntity ");
        TestEntity obj = getTestEntity();
        obj.setCheck(false);
        TestEntity expected = hibernateDataProviderLab2.updateTestEntity(obj);
        log.debug("updateTestEntity [2]: end working {}",expected);
    }
}