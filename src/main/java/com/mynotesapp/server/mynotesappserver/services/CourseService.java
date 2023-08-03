package com.mynotesapp.server.mynotesappserver.services;

import com.mynotesapp.server.mynotesappserver.Entities.Course;
import com.mynotesapp.server.mynotesappserver.Entities.SubTopics;
import com.mynotesapp.server.mynotesappserver.Repositories.CourseRepository;
import com.mynotesapp.server.mynotesappserver.Repositories.SubTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    private SubTopicRepository subTopicRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll(){
        var result = courseRepository.findAll();
        return result;
    }

    public Course saveCourse(Course course){
        var dbCourse = courseRepository.save(course);
        return dbCourse;
    }

    public Course findById(int id){
        var result =courseRepository.findById(id);
        if(!result.isPresent()) throw new RuntimeException("Course doesnt exist");
        return result.get();
    }


    public List<SubTopics> findByCourse(Course course){
        var list = subTopicRepository.findByCourse(course);
        return list;
    }

    public SubTopics saveSubtopic(SubTopics subTopics){
       var dbSubtopic =  subTopicRepository.save(subTopics);
       return subTopics;
    }

}
