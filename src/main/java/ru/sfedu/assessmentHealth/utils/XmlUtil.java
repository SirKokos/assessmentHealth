package ru.sfedu.assessmentHealth.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.assessmentHealth.api.DataProviderXML;
import ru.sfedu.assessmentHealth.model.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XmlUtil{
    private static final Logger log = LogManager.getLogger(XmlUtil.class.getName());

    public static <T> XmlWrapper getXmlWrapper(String PathXmlFile){
        log.debug("getXmlWrapper [1]: start working method");
        Serializer serializer = new Persister();
        File file = new File(PathXmlFile);
        XmlWrapper<T> wrap = new XmlWrapper<>();
        try {
            wrap = serializer.read(XmlWrapper.class, file,false);
        } catch (Exception e) {
            log.error("getXmlWrapper [2]: ERROR {}",e.getMessage());
        }
        return wrap;
    }

    /**
     * @param nameFileXml
     * @param fk_id_to_Object
     * @return
     */
    public static int getObjectFkIdPerson(String nameFileXml,int fk_id_to_Object) {
        log.debug("getObjectFkIdDoctor [1]: - Процесс проверки на ограничение внешнего ключа ");
        List<Person> ListPerson;
        List<Person> id;
        int fk_id = 0;
        try {
            ListPerson = getXmlWrapper(nameFileXml).getListObjXml();
            id = ListPerson.stream()
                    .filter(iter -> iter.getPersonID() == fk_id_to_Object)
                    .toList();

           switch (id.size()){
                case 0 -> log.debug("getObjectFkIdDoctor [2] - Такого ключа в файле нет.Попробуйте перепроверить");
               default ->  fk_id = id.get(0).getPersonID();
           }
        }catch (Exception e){
            log.error("getObjectFkIdDoctor [3] - Такого ключа в файле нет.Попробуйте перепроверить {}", e.getMessage());
        }
        log.debug("getObjectFkIdDoctor [4] - Метод завершил свою работу");
        return fk_id;
    }

}
