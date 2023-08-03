package com.mynotesapp.server.mynotesappserver;
import com.mynotesapp.server.mynotesappserver.Entities.*;
import com.mynotesapp.server.mynotesappserver.GetPostObjects.Directory;
import com.mynotesapp.server.mynotesappserver.services.CourseService;
import com.mynotesapp.server.mynotesappserver.services.PartService;
import com.mynotesapp.server.mynotesappserver.services.SubTopicService;
import com.mynotesapp.server.mynotesappserver.services.TopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/Courses")
public class DemoController {
    private CourseService courseService;
    private PartService partService;

    private TopicService topicService;
    private SubTopicService subTopicService;


    public DemoController (CourseService courseService, PartService partService, TopicService topicService){
        this.courseService = courseService;
        this.partService = partService;
        this.topicService = topicService;
    }


    @GetMapping("")
    public Directory<Course,SubTopics> getCourses(){
        Directory<Course,SubTopics> coursesAndSubtopics = new Directory<>();
        coursesAndSubtopics.setBaseDirectory(courseService.findAll());
        coursesAndSubtopics.setSubDirectory(subTopicService.findAll());
        return coursesAndSubtopics;
    }


    @PostMapping("")
    public PostCourseSubtopic addCourseSubtopic(@RequestBody PostCourseSubtopic postCourseSubtopic){
        System.out.println(postCourseSubtopic.toString());
        var course = postCourseSubtopic.getCourse();
        var dbCourse = courseService.saveCourse(course);
        var dbSubTopic = postCourseSubtopic.getSubTopics();
        dbSubTopic.setCourse(dbCourse);
        subTopicService.saveSubtopic(dbSubTopic);
        postCourseSubtopic.setSubTopics(dbSubTopic);
        postCourseSubtopic.setCourse(dbCourse);
        return postCourseSubtopic;
    }



    @PostMapping("/SubTopics")
    public SubTopics addSubTopic(@RequestBody SubTopics subTopics){
       var dbSubTopic =  courseService.saveSubtopic(subTopics);
       return dbSubTopic;
    }


    @PostMapping("/part")
    public Part savePart(@RequestBody Part part){
        return partService.save(part);
    }
    @GetMapping("/Subtopic/{subTopicSlug}")
    public List<Part> findPart(@PathVariable String subTopicSlug){
        var subTopic = subTopicService.findById(subTopicSlug);
        return partService.findBySubTopic(subTopic);
    }
    @GetMapping("/Part/{partSlug}")
    public List<Topic> getTopics(@PathVariable String partSlug){
        var part = partService.findById(partSlug);
        var topics = topicService.findByPart(part);
        return topics;
    }

    @GetMapping("/topic/{topicSlug}")
    public Topic findMainTopic(@PathVariable String topicSlug) {
        return topicService.findById(topicSlug);
    }
}

