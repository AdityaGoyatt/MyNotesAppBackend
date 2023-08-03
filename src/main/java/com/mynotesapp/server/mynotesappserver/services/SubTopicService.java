package com.mynotesapp.server.mynotesappserver.services;

import com.mynotesapp.server.mynotesappserver.Entities.Course;
import com.mynotesapp.server.mynotesappserver.Entities.SubTopics;
import com.mynotesapp.server.mynotesappserver.Repositories.SubTopicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubTopicService {

    private SubTopicRepository subTopicRepository;

    public SubTopicService (SubTopicRepository subTopicRepository){
        this.subTopicRepository =subTopicRepository;
    }

    public List<SubTopics> findAll(){
        return subTopicRepository.findAll();
    }

    public SubTopics saveSubtopic(SubTopics subTopics){
        var dbSubtopic =  subTopicRepository.save(subTopics);
        return subTopics;
    }

    public List<SubTopics> findByCourse(Course course){
        var list = subTopicRepository.findByCourse(course);
        return list;
    }
    public SubTopics findById(String slug){
        var result = subTopicRepository.findById(slug);
        if(!result.isPresent()) throw new RuntimeException("Subtopic with id: " + slug + "doesnt exists");
        return result.get();
    }

}
