package com.mynotesapp.server.mynotesappserver;


import com.mynotesapp.server.mynotesappserver.Entities.Course;
import com.mynotesapp.server.mynotesappserver.Entities.Part;
import com.mynotesapp.server.mynotesappserver.Entities.PostCourseSubtopic;
import com.mynotesapp.server.mynotesappserver.Entities.SubTopics;
import com.mynotesapp.server.mynotesappserver.services.CourseService;
import com.mynotesapp.server.mynotesappserver.services.PartService;
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
    public PartService partService;

    public DemoController (CourseService courseService, PartService partService){
        this.courseService = courseService;
        this.partService = partService;
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
        courseService.saveSubtopic(dbSubTopic);
        postCourseSubtopic.setSubTopics(dbSubTopic);
        postCourseSubtopic.setCourse(dbCourse);
    return postCourseSubtopic;
    }

    @PostMapping("/part")
    public Part savePart(@RequestBody Part part){
        return partService.save(part);
    }
    @GetMapping("/part")
    public List<Part> findPart(@RequestBody SubTopics subTopics){
        return partService.findBySubTopic(subTopics);
    }
    @GetMapping("/partall")
    public List<Part> findAllPart(){

            return partService.find();

    }
}

