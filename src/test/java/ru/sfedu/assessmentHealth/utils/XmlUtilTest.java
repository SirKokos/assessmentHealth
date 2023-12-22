package ru.sfedu.assessmentHealth.utils;

import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.CalcReport;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.XmlWrapper;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sfedu.assessmentHealth.utils.TestUtil.createCalcReport;

class XmlUtilTest {
    final private String doctorPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_DOCTOR).concat(CONST.XML_FILE_TYPE);

    @Test
    void getXmlWrapper() {
        XmlWrapper<Doctor> actual = XmlUtil.getXmlWrapper(doctorPath);
        System.out.println(actual.getListObjXml().get(0).getName());
    }

    @Test
    void getObjectFkIdPerson() {
        CalcReport calcReport = createCalcReport();
        calcReport.setFkToPatient(-1);
        calcReport.setFkToDoctor(-1);
        System.out.println(calcReport.getFkToDoctor());
        System.out.println(XmlUtil.getObjectFkIdPerson(doctorPath, calcReport.getFkToDoctor()));
    }
}
