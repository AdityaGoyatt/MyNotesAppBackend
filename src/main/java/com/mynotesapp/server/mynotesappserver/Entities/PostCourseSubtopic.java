package com.mynotesapp.server.mynotesappserver.Entities;


import org.springframework.stereotype.Component;


public class PostCourseSubtopic {
    @Override
    public String toString() {
        return "PostCourseSubtopic{" +
                "course=" + course +
                ", subTopics=" + subTopics +
                '}';
    }

    public Course course;
    public SubTopics subTopics;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public SubTopics getSubTopics() {
        return subTopics;
    }

    public void setSubTopics(SubTopics subTopics) {
        this.subTopics = subTopics;
    }
}
