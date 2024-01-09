package ru.sfedu.assessmentHealth.utils;

import com.opencsv.bean.AbstractCsvConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.Patient;
import ru.sfedu.assessmentHealth.model.Preparation;

import java.util.Locale;

public class ConvertCsvPatient extends AbstractCsvConverter {
    private static final Logger log = LogManager.getLogger(ConvertCsvPatient.class.getName());
    @Override
    public Object convertToRead(String value) {
        String[] split ;
        Patient patient = new Patient();
        try{
            log.debug("ConvertCsvDoctor.convertToRead [1]: start - {}",value);
            split = value.split("\\$", 1);
            patient.setId(Integer.valueOf(split[0]));
        }catch (Exception e){
            log.error("convertToRead [2]: {}",e.getMessage());
        }
        log.debug("convertToRead [3]: end {}",patient);
        return patient;

    }

    @Override
    public String convertToWrite(Object value) {
        Patient t = new Patient();
        try {
            log.debug("convertToWrite [1]: start {}",value);
            t = (Patient) value;
        }catch (Exception e){
            log.error("convertToWrite [2]: {}",e.getMessage());
        }
        log.debug("convertToWrite [3]: end {}",value);
        return String.format(Locale.US,"%d", t.getId());
    }
}
