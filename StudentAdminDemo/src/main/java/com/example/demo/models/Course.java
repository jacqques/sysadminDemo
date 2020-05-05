package com.example.demo.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Course {
    //make stuff pls
    public int id;
    public int ECTS;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date startDate;
    public String name;
    public String description;

    public Course(int id, int ECTS, Date startDate, String name, String description) {
        this.id = id;
        this.ECTS = ECTS;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", ECTS=" + ECTS +
                ", startDate=" + startDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
