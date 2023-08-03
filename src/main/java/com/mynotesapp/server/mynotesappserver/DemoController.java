package com.mynotesapp.server.mynotesappserver;
import com.mynotesapp.server.mynotesappserver.Entities.*;
import com.mynotesapp.server.mynotesappserver.services.CourseService;
import com.mynotesapp.server.mynotesappserver.services.PartService;
import com.mynotesapp.server.mynotesappserver.services.TopicService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class DemoController {
    private CourseService courseService;
    private PartService partService;

    private TopicService topicService;


    public DemoController (CourseService courseService, PartService partService, TopicService topicService){
        this.courseService = courseService;
        this.partService = partService;
        this.topicService = topicService;
    }


    @GetMapping("/Courses")
    public List<Course> getCourses(){
        return courseService.getAll();
    }


    @PostMapping("/Courses")
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
    @GetMapping("/topic")
    public List<Topic> findAllTopics(){
        return topicService.findAll();
    }
}

