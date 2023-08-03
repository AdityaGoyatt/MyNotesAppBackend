package com.mynotesapp.server.mynotesappserver.services;

import com.mynotesapp.server.mynotesappserver.Entities.Part;
import com.mynotesapp.server.mynotesappserver.Entities.Topic;
import com.mynotesapp.server.mynotesappserver.Repositories.TopicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicService {
    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic findById(String slug){
        var result =topicRepository.findById(slug);
        if(!result.isPresent()) throw new RuntimeException("Topic doesnt Exists");
        return result.get();
    }

    public List<Topic> findByPart(Part part){
        return topicRepository.findByPart(part);
    }

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

}
