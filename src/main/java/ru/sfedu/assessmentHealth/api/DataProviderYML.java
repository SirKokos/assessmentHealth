package ru.sfedu.assessmentHealth.api;

import org.yaml.snakeyaml.Yaml;
import ru.sfedu.assessmentHealth.CONST;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class DataProviderYML {



 public Map<Integer,String> getPeople() throws IOException {
     Yaml yaml = new Yaml();
     FileInputStream inputStream = new FileInputStream(CONST.PATH_CONST_YML);
     // Чтение содержимого файла yml и преобразование в объект Map
     Map<Integer, String> data = yaml.load(inputStream);

     inputStream.close();

     return  data;
 }
}
