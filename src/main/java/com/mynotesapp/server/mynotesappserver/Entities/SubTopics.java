package com.mynotesapp.server.mynotesappserver.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "subtopics")
public class SubTopics {
    @Id
    @Column(name = "subtopic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subtopicId;

    public int getSubtopicId() {
        return subtopicId;
    }

    public void setSubtopicId(int subtopicId) {
        this.subtopicId = subtopicId;
    }

    public String getSubtopicName() {
        return subtopicName;
    }

    public void setSubtopicName(String subtopicName) {
        this.subtopicName = subtopicName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(name = "subtopic_name")
    private String subtopicName;

    @ManyToOne
    @JoinColumn(name = "id")
    private Course course;

    // Getters and setters, constructors, etc.
}
