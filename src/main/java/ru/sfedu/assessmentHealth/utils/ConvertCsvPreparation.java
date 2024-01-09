package ru.sfedu.assessmentHealth.utils;

import com.opencsv.bean.AbstractCsvConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.model.Preparation;

import java.util.Arrays;
import java.util.Locale;

public class ConvertCsvPreparation extends AbstractCsvConverter {
    private static final Logger log = LogManager.getLogger(ConvertCsvPreparation.class.getName());
    @Override
    public Object convertToRead(String value) {
        Preparation preparation = new Preparation();
        try{
            log.debug("ConvertCsvPrep.convertToRead [1]: start - {}",value);
            String[] split = value.split("\\$", 1);
            preparation.setId(Integer.valueOf(split[0]));
        }catch (Exception e){
            log.error("convertToRead [2]: {}",e.getMessage());
        }
        log.debug("convertToRead [2]: end {}",preparation);
        return preparation;

    }

    @Override
    public String convertToWrite(Object value) {
        Preparation t = new Preparation();
        try {
            log.debug("convertToWrite [1]: start {}",value);
            t = (Preparation) value;
        }catch (Exception e){
            log.error("convertToWrite [2]: {}",e.getMessage());

        }
        log.debug("convertToWrite [3]: end {}",value);
        return String.format(Locale.US,"%d", t.getId());
    }
}
