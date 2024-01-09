package ru.sfedu.assessmentHealth.utils;

import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.Preparation;

import java.util.Locale;

public class ConvertCsvDoctor extends AbstractCsvConverter {
    private static final Logger log = LogManager.getLogger(ConvertCsvDoctor.class.getName());
    @Override
    public Object convertToRead(String value) {
        Doctor doctor = new Doctor();;
        try{
            log.debug("ConvertCsvDoctor.convertToRead [1]: start - {}",value);
            String[] split = value.split("\\$", 1);
            doctor.setId(Integer.valueOf(split[0]));
        }catch (Exception e){
            log.error("convertToRead [2]: Error {}",e.getMessage());
        }
        log.debug("convertToRead [3]: end {}",doctor);
        return doctor;

    }

    @Override
    public String convertToWrite(Object value) {
        Doctor t = new Doctor();
        try{
            log.debug("convertToWrite [1]: start {}",value);
            t = (Doctor) value;
        }catch (Exception e){
            log.error("convertToWrite [2]: {}",e.getMessage());
        }
        log.debug("convertToWrite [3]: end {}",value);
        return String.format(Locale.US,"%d", t.getId());
    }
}
