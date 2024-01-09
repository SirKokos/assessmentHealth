package ru.sfedu.assessmentHealth.utils;

import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.model.BaseId;
import ru.sfedu.assessmentHealth.model.Preparation;

public class ConverterCsv extends AbstractCsvConverter {
    private static final Logger log = LogManager.getLogger(ConverterCsv.class.getName());

    /**
     * @param value Строка которая отвечает за те данные которые мы будем считывать с файла
     * @return BaseId  объект Id
     */
    @Override
    public Object convertToRead(String value) {
        BaseId baseID = new BaseId();

        try {
            log.debug("ConverterCsv.convertToRead [1]: start {}",value);
            baseID.setId(Integer.valueOf(value));
        }catch (Exception e){
            log.error("convertToRead [2]: {}",e.getMessage());

        }

        log.debug("ConverterCsv.convertToRead [3]: end {}",baseID);
        return baseID;
    }

    @Override
    public String convertToWrite(Object value) {
        log.debug("ConverterCsv.convertToRead [1]: start {}",value);
        BaseId t = (BaseId) value;
        log.debug("ConverterCsv.convertToRead [1]: end ");
        return String.format("%d", t.getId());


    }
}
