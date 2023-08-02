package com.mynotesapp.server.mynotesappserver;


import com.mynotesapp.server.mynotesappserver.Entities.Course;
import com.mynotesapp.server.mynotesappserver.Entities.PostCourseSubtopic;
import com.mynotesapp.server.mynotesappserver.Entities.SubTopics;
import com.mynotesapp.server.mynotesappserver.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5175")
@RequestMapping("/api")
public class DemoController {
    private CourseService courseService;


    public DemoController (CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/message")
    public List<String> hello() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "goyat", "detroit", "san francisco");
        return list;
    }
    @GetMapping("/list")
    public List<Course> getCourses(){
        return courseService.getAll();
    }


    @PostMapping("/list")
    public Course addCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }


    @GetMapping("/subtopics/{courseId}")
    public List<SubTopics> subtopicList(@PathVariable int courseId){
        var course = courseService.findById(courseId);
        return courseService.findByCourse(course);
    }

    @PostMapping("/subtopics")
    public SubTopics addSubTopic(@RequestBody SubTopics subTopics){
       var dbSubTopic =  courseService.saveSubtopic(subTopics);
       return dbSubTopic;
    }


    @PostMapping("/Course")
    public PostCourseSubtopic addCourseSubtopic(@RequestBody PostCourseSubtopic postCourseSubtopic){
        System.out.println(postCourseSubtopic.toString());
        var course = postCourseSubtopic.getCourse();
        var dbCourse = courseService.saveCourse(course);
        var dbSubTopic = postCourseSubtopic.getSubTopics();
        dbSubTopic.setCourse(dbCourse);
        courseService.save(dbSubTopic);
        postCourseSubtopic.setSubTopics(dbSubTopic);
        postCourseSubtopic.setCourse(dbCourse);
    return postCourseSubtopic;
    }
}

