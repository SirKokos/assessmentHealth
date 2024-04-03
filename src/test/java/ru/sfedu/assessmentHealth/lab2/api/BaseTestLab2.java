package ru.sfedu.assessmentHealth.lab2.api;

import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.lab1.utils.HibernateUtil;
import ru.sfedu.assessmentHealth.lab2.model.Person;
import ru.sfedu.assessmentHealth.lab2.model.TestEntity;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.time.Period;
import java.util.Date;

public class BaseTestLab2 {
    HibernateDataProviderLab2 hibernateDataProviderLab2 = new HibernateDataProviderLab2();
    static {
        PropertyConfig.setConfigPath(Const.NAME_PROPERTY_FILE);
        HibernateUtil.setPathConfig(PropertyConfig.getPropertyValue(Const.LAB2_HBN_CFG,Const.NAME_PROPERTY_FILE));
    }

    public Person getPerson(){
        Person person = new Person();
        person.setAge(12);
        person.setName("Artem");

        return person;
    }
    public TestEntity getTestEntity(){

        TestEntity testEntity = new TestEntity();
        testEntity.setId(1L);
        testEntity.setName("Artem2");
        testEntity.setDescription("this test been");
        testEntity.setDateCreated(new Date());
        testEntity.setCheck(Boolean.TRUE);
        testEntity.setPerson(getPerson());

        return testEntity;
    }

    @Test
    void test(){
        String s = HibernateUtil.getPathConfig();
        System.out.print(s);
    }
}
