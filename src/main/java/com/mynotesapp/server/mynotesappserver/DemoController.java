package com.mynotesapp.server.mynotesappserver;


import com.mynotesapp.server.mynotesappserver.Entities.Course;
import com.mynotesapp.server.mynotesappserver.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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

}
