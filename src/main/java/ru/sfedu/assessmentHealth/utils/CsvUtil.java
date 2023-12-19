package ru.sfedu.assessmentHealth.utils;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.CONST;

import java.io.*;
import java.util.Arrays;

public class CsvUtil {
    private static final Logger log = LogManager.getLogger(CsvUtil.class.getName());


//    public static boolean checkObjectIdFromCsv(String nameFileCsv,String nameFieldCsv, int objectId) throws FileNotFoundException {
//        log.debug("checkObjectIdFromCsv [1]: - Процесс проверки существующего ID");
//        CSVReader reader = new CSVReader(new FileReader(nameFileCsv));
//        String [] nextLine;
//        int columnPosition;
//        boolean status = false;
//
//        try {
//            nextLine = reader.readNext();
//            columnPosition = getHeaderLocation(nextLine, nameFieldCsv);
//            while ((nextLine = reader.readNext()) != null && columnPosition > -1) {
//                if(objectId != Integer.parseInt(nextLine[columnPosition])){
//                    log.debug("checkObjectIdFromCsv [2]: - Id не найден status true");
//                    status = true;
//                }else {
//                    log.debug("checkObjectIdFromCsv [3]: - Id найден вы не можете создать такой же ID status false");
//                }
//            }
//        }catch (Exception e){
//            log.error("checkObjectIdFromCsv [4]: - Возникла ошибка {}", e.getMessage());
//        }
//
//        return status;
//    }
    public static int getObjectFkId(String nameFileCsv,int fk_id_to_Object) {
        log.debug("getObjectFkId [1]: - Процесс проверки на ограничение внешнего ключа ");
        Reader fileReader;
        CSVReader reader;
        String [] nextLine;
        int fk_id = 0;
        try {
            fileReader = new FileReader(nameFileCsv);
            reader = new CSVReaderBuilder(fileReader).build();
            File file = new File(nameFileCsv);
            if(file.length() == 0){
                log.warn("getObjectFkId [2] - WARNING Пустой файл");
            }else{
                while ((nextLine = reader.readNext()) != null){
                    if(Integer.parseInt(nextLine[0]) == fk_id_to_Object){
                        log.debug("getObjectFkId [3] - Метод Нашел нужный fk");
                        fk_id = fk_id_to_Object;
                    }
                    if(fk_id == 0){log.debug("getObjectFkId [4] - Такого ключа в файле нет.Попробуйте перепроверить");}
                }
            }
        }catch (IOException | CsvValidationException e){
            log.error("getObjectFkId [5] - Возникла ошибка {}",e.getMessage());
        }
        log.debug("getObjectFkId [6] - Метод завершил работу");
        return fk_id;
    }

    public static int getObjectLastIdCsv(String nameFileCsv,int position_id) throws FileNotFoundException {
        log.debug("getObjectLastIdCsv [1]: - Процесс получения последнего ID");
        Reader fileReader = new FileReader(nameFileCsv);
        CSVReader reader = new CSVReaderBuilder(fileReader).build();
        String [] nextLine;
        int lastId = 0;
        try {
            while ((nextLine = reader.readNext()) != null) {
                lastId = Integer.parseInt(nextLine[position_id]);
            }
            if (lastId > 0){
                log.debug("getObjectLastIdCsv [2]: - Процесс получения последнего ID успешно получен");
            }else {
                log.error("getObjectLastIdCsv [3]: - Процесс получения последнего ID ОШИБКА");
            }
        }catch (Exception e){
            log.error("getObjectLastIdCsv [4]: - Процесс получения последнего ID ОШИБКА {}",e.getMessage());
        }
        return lastId;
    }
//    private static int getHeaderLocation(String[] headers, String columnName) {
//        if(headers == null){
//
//        }
//        return Arrays.asList(headers).indexOf(columnName);
//    }
}
