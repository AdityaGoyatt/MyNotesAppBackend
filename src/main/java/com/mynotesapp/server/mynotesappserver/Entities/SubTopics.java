package com.mynotesapp.server.mynotesappserver.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "subtopics")
public class SubTopics {
    @Id
    @Column(name = "slug")
    private String subtopicSlug;


    public String getSubtopicSlug() {
        return subtopicSlug;
    }

    public void setSubtopicSlug(String subtopicSlug) {
        this.subtopicSlug = subtopicSlug;
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


    @PrePersist
    private void generateSubTopicSlug() {
        this.subtopicSlug = SlugNameSetter.setSlugByName(this.subtopicName);
    }
}
