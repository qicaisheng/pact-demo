package com.qicaisheng.pact.consumer;

public class City {
    private String name;
    private int personCount;

    City(String name, int personCount) {
        this.name = name;
        this.personCount = personCount;
    }

    /**
     * for parse response
     */
    public City() {
    }

    public int getPersonCount() {
        return personCount;
    }

    public String getName() {
        return name;
    }
}
