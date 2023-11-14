package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private static final Logger log = LogManager.getLogger(Client.class.getName());
    @Test
    void logBasicSystemInfoTest(){
        Client cl = new Client();
        log.info("Debug = "+ log.isDebugEnabled());
        log.info("Error = "+ log.isErrorEnabled());
        log.info("Info = "+ log.isInfoEnabled());
        log.error("dwq");
    }
}