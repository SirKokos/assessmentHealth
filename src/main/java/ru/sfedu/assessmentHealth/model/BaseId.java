package ru.sfedu.assessmentHealth.model;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvBindByPosition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ru.sfedu.assessmentHealth.api.DataProviderXml.getXmlWrapper;

@Root
public class BaseId {
    private static final Logger log = LogManager.getLogger(BaseId.class.getName());

    @Element
    @CsvBindByPosition(position = 0)
    protected Integer id;

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public BaseId(){

    }

    /**
     * @param nameFileCsv - прописываем путь до нашего файла в котором хотим поучить последний id
     * @return result_id - возвращает поcлеаний id
     *  Id должен находится в первом столбце
     */
    public static Integer getObjectLastIdCsv(String nameFileCsv)  {
        log.debug("getObjectLastIdCsv [1]: - Процесс получения последнего ID");
        Integer result_id = 0;
        String[] nextLine;
        try {
            CSVReader csvReader = new CSVReaderBuilder(
                    new FileReader(nameFileCsv)
            ).build();
            while ((nextLine = csvReader.readNext()) != null) {
                result_id = Integer.valueOf(nextLine[0]);
            }
            switch (result_id){
                case 0 -> {return 0;}
                default -> {return result_id+1;}
            }
        }
        catch (Exception e) {
            log.error("getObjectLastIdCsv [2]: ERROR {}",e.getMessage());
        }
        return result_id;
    }


    /**
     * Метод дляустановки ID xml
     * @param nameFileXml путь до файла xml в котром нужно получить ID
     * @return Новый ID
     */
    public static Integer getObjectLastIdXml(String nameFileXml)  {
        log.debug("getObjectLastIdXml [1]: - Процесс получения последнего ID");
        List<BaseId> ListObject;
        List<Integer> id;
        ListObject = getXmlWrapper(nameFileXml).getListObjectXml();
        id = ListObject.stream()
                .map(iter-> iter.getId())
                .toList();
        log.debug("getObjectLastIdXml [1]: -END  Процесс получения последнего ID");
        return Collections.max(id)+1;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseId baseId)) return false;
        return Objects.equals(id, baseId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                '}';
    }
}
