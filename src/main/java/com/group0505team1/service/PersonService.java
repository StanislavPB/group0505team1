package com.group0505team1.service;

import com.group0505team1.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    List<Person> personList = new ArrayList<>();

    int counter = 0;


    public List<Person> getPersonList() {
        return personList;
    }

}
