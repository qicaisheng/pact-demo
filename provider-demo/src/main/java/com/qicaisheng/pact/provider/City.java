package com.qicaisheng.pact.provider;

public class City {
    City(String name, int personCount) {
        this.name = name;
        this.personCount = personCount;
    }

    private String name;
    private int personCount;

    public String getName() {
        return name;
    }

    public int getPersonCount() {
        return personCount;
    }
}
