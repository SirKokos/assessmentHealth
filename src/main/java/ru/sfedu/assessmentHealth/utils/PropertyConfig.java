package ru.sfedu.assessmentHealth.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyConfig {
    private static final Logger log = LogManager.getLogger(PropertyConfig.class.getName());
    private static String configPath = "";

    public static String getConfigPath() {
        return configPath;
    }
    public static void setConfigPath(String configpath) {
        configPath = configpath;
    }

    /**
     * ����� ��� ������ � property �������.
     * @param propertyKey - String ���� �� �������� �������� �������� ��������
     * @return �������� ���� String
     */
    public static String getPropertyValue(String propertyKey,String pathProperty){
        log.debug("getPropertyValue [1]: ������ ������ ��������� ���������� property");
        String propertyValue = "";

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathProperty));
            propertyValue = properties.getProperty(propertyKey);
        } catch (IOException e) {
            log.error("getPropertyValue [2]: �������� ������ {}",e.getMessage());
        }
        log.debug("getPropertyValue [3]: ����� ��������� property");
        return propertyValue;
    }
}
