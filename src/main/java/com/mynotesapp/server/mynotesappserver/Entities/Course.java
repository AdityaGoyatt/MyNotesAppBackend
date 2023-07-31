package com.mynotesapp.server.mynotesappserver.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name = "course")
    private String course;

    @Column(name = "subjects")
    private String subjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
