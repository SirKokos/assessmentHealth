package ru.sfedu.assessmentHealth.lab5.ManyToMany.api;

import java.util.List;

public interface ISQL {
    List selectAllNativeSql();
    Object selectAllCriteria();
    Object selectAllHQL();

}
