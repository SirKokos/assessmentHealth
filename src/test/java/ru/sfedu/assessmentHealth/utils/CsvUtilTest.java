package ru.sfedu.assessmentHealth.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.CONST;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CsvUtilTest {

    @Test
    void checkObjectIdFromCsv() {
    }

    @Test
    void getObjectLastIdCsv() throws IOException, CsvValidationException {

        CSVReader reader = new CSVReaderBuilder(new FileReader(CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_DOCTOR).concat(CONST.CSV_FILE_TYPE))).build();
        String [] nextLine;
        if(reader.readNext() == null){
            System.out.println("ffefefefe");
        }
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line

            System.out.println(nextLine[0] + nextLine[1] + "etc...");
        }

    }
}