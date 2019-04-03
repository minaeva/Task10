package com.foxminded.domain;

import com.foxminded.domain.WorkDay;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;

public class Validator {

    private static long id = 1;

    public static long generateNewId(){
        return id++;
    }

     public static <T> void validateNameIsUnique(List<T> list,
                                                 Predicate<T> predicate,
                                                 String objectName,
                                                 String name) throws IllegalArgumentException{
        if (list.stream().anyMatch(predicate)){
            throw new IllegalArgumentException(objectName + " with name " + name + " already exists");
        }
    }

    public static <T> T findObjectByNumberIfExists(List<T> list,
                                                 Predicate<T> predicate,
                                                 String object,
                                                 int number) throws IllegalArgumentException{
        return list
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(object + " with number " + number + " doesn't exist"));
    }

    public static <T> T findObjectByNameIfExists(List<T> list,
                                                 Predicate<T> predicate,
                                                 String object,
                                                 String name) throws IllegalArgumentException{
        return list
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(object + " with name " + name + " doesn't exist"));
    }

    public static <T> T findObjectByTimeIfExists(List<T> list,
                                                 Predicate<T> predicate,
                                                 String object,
                                                 LocalTime time) throws IllegalArgumentException{
        return list
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(object + " with time " + time + " doesn't exist"));
    }

    public static <T> T findObjectByWorkdayIfExists(List<T> list,
                                           Predicate<T> predicate,
                                           String object,
                                           WorkDay day) throws IllegalArgumentException{
        return list
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(object + " with workday " + day + " doesn't exist"));
    }

}

