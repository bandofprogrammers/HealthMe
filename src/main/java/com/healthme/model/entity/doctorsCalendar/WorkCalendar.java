package com.healthme.model.entity.doctorsCalendar;

import javax.persistence.*;
import java.util.List;

@Entity
public class WorkCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<WorkDay> daysOfWork;

    public WorkCalendar() {
    }

    public WorkCalendar(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
