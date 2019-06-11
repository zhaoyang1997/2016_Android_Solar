package net.solar.server.solar.entity;

public class Day {
    private String name;
    private String day;

    public Day() {
    }

    public Day(String name, String day) {
        this.name = name;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
