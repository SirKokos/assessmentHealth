package ru.sfedu.assessmentHealth;


import ru.sfedu.assessmentHealth.api.Cat;
import ru.sfedu.assessmentHealth.api.DataProviderMongo;

import ru.sfedu.assessmentHealth.api.DataProviderYML;
import ru.sfedu.assessmentHealth.model.HistoryContent;
import ru.sfedu.assessmentHealth.model.StatusHistory;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        HistoryContent history = new HistoryContent();
        history.setId();
        history.setClassName("Artem");
        history.setCreatedDate(new Date());
        history.setMethodName("Main");
        history.setActor(CONST.NAME_ACTOR_HISTORY);
        history.setStatus(StatusHistory.SUCCESS);
        DataProviderMongo db = new DataProviderMongo();

        HistoryContent history1 = new HistoryContent();
        System.out.println("========"+db.jsonArrayToObjectList(List.of(history.setObject()), history1.getClass()).get(0));

    }
}