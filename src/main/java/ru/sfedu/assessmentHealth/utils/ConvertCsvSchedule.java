package ru.sfedu.assessmentHealth.utils;

import com.opencsv.bean.AbstractCsvConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.model.Preparation;
import ru.sfedu.assessmentHealth.model.Schedule;

import java.util.Arrays;
import java.util.Locale;

public class ConvertCsvSchedule extends AbstractCsvConverter {
    private static final Logger log = LogManager.getLogger(ConvertCsvSchedule.class.getName());
    @Override
    public Object convertToRead(String value) {
        Schedule schedule = new Schedule();
        try {
            log.debug("ConvertCsvSchedule.convertToRead [1]: start - {}",value);
            String[] split = value.split("\\$", 1);
            schedule.setId(Integer.valueOf(split[0]));
        }catch (Exception e){
            log.error("convertToRead [2]: {}",e.getMessage());
        }
        log.debug("convertToRead [3]: end {}",schedule);
        return schedule;

    }

    @Override
    public String convertToWrite(Object value) {
        Schedule t = new Schedule();
        try{
            log.debug("convertToWrite [1]: start {}",value);
            t = (Schedule) value;
        }catch (Exception e){
            log.error("convertToWrite [2]: {}",e.getMessage());
        }
        log.debug("convertToWrite [3]: end {}",value);
        return String.format(Locale.US,"%d", t.getId());


    }
}
