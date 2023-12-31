package com.mynotesapp.server.mynotesappserver;
import com.mynotesapp.server.mynotesappserver.Entities.*;
import com.mynotesapp.server.mynotesappserver.GetPostObjects.Directory;
import com.mynotesapp.server.mynotesappserver.GetPostObjects.RecievedTopic;
import com.mynotesapp.server.mynotesappserver.services.CourseService;
import com.mynotesapp.server.mynotesappserver.services.PartService;
import com.mynotesapp.server.mynotesappserver.services.SubTopicService;
import com.mynotesapp.server.mynotesappserver.services.TopicService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class DemoController {
    private CourseService courseService;
    private PartService partService;

    private TopicService topicService;
    private SubTopicService subTopicService;


    public DemoController (CourseService courseService, PartService partService, TopicService topicService,SubTopicService subTopicService){
        this.courseService = courseService;
        this.partService = partService;
        this.topicService = topicService;
        this.subTopicService = subTopicService;
    }

    /////////////////////////////////////////////////////////////////////////////////Root Directory functions
    @GetMapping("/courses")
    public Directory<Course,SubTopics> getCourses(){
        Directory<Course,SubTopics> coursesAndSubtopics = new Directory<>();
        coursesAndSubtopics.setBaseDirectory(courseService.findAll());
        coursesAndSubtopics.setSubDirectory(subTopicService.findAll());
        return coursesAndSubtopics;
    }


    @PostMapping("/courses")
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
    /////////////////////////////////////////////////////////////////////////////////////SubTopic Functions

    @GetMapping("/courses/{subtopicSlug}")
    public SubTopics getSubTopicBySlug(@PathVariable String subtopicSlug){
        return subTopicService.findById(subtopicSlug);
    }


    @PostMapping("/SubTopics")
    public SubTopics addSubTopic(@RequestBody SubTopics subTopics){
       var dbSubTopic =  subTopicService.saveSubtopic(subTopics);
       return dbSubTopic;
    }


    //////////////////////////////////////////////////////////////////////////////////////Parts Functions

    @PostMapping("/part")
    public Part savePart(@RequestBody Part part){
        return partService.save(part);
    }
    @GetMapping("/Subtopic/{subTopicSlug}")
    public List<Part> findPart(@PathVariable String subTopicSlug){
        var subTopic = subTopicService.findById(subTopicSlug);
        return partService.findBySubTopic(subTopic);
    }

    @GetMapping("/{partSlug}")
    @CrossOrigin(origins = "http://localhost:8080/api/")
    public List<Topic> getTopics(@PathVariable String partSlug){
        var part = partService.findById(partSlug);
        var topics = topicService.findByPart(part);
        return topics;
    }


    //////////////////////////////////////////////////////////////////////////////// Main Topic Functions
    @GetMapping("/topic/{topicSlug}")
    public Topic findMainTopic(@PathVariable String topicSlug)
    {
        System.out.println(topicSlug);
        var result = topicService.findById(topicSlug);
        System.out.println(result.getTopicSlug());
        return result;
    }



    @PostMapping("/topic")
    public Topic saveTopic(@ModelAttribute RecievedTopic recievedTopic) throws IOException {
        System.out.println(recievedTopic);
        Topic toSaveTopic = new Topic();
        toSaveTopic.setName(recievedTopic.getName());
        Part part = partService.findById(recievedTopic.getPartSlug());
        toSaveTopic.setPart(part);
        toSaveTopic.setResultComment(recievedTopic.getResultComment());
        toSaveTopic.setSyntaxComment(recievedTopic.getSyntaxComment());

        String syntaxImagePath = topicService.saveImage(recievedTopic.getSyntaxImage(), "syntax");
        String resultImagePath = topicService.saveImage(recievedTopic.getResultImage(), "result");
        toSaveTopic.setSyntaxImage(syntaxImagePath);
        toSaveTopic.setResultImage(resultImagePath);
        return topicService.save(toSaveTopic);
    }

    //////////////////////////////////////////////////////////////////////////////////////Image Url Functions

    @GetMapping("/{imageType}/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageType, @PathVariable String fileName) throws IOException {
        String imagePath = "src/main/resources/static/images/" + imageType + "/" + fileName;
        Path imagePathObj = Path.of(imagePath);

        if (Files.exists(imagePathObj)) {
            Resource imageResource = new UrlResource(imagePathObj.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Change the content type if needed
                    .body(imageResource);
        } else {
            return ResponseEntity.notFound().build();}
}

}

