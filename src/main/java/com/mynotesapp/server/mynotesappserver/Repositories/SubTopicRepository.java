package com.mynotesapp.server.mynotesappserver.Repositories;

import com.mynotesapp.server.mynotesappserver.Entities.Course;
import com.mynotesapp.server.mynotesappserver.Entities.SubTopics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTopicRepository extends JpaRepository<SubTopics, String> {
    List<SubTopics> findByCourse(Course course);
}

