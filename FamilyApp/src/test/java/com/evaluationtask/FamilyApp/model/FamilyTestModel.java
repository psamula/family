package com.evaluationtask.FamilyApp.model;

import org.springframework.data.annotation.Id;

public class FamilyTestModel {
    @Id
    private Long id;
    private String name;
    private int nrofinfants;
    private int nrofchildren;
    private int nrofadults;
}

