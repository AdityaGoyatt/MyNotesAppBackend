package com.mynotesapp.server.mynotesappserver.services;

import com.mynotesapp.server.mynotesappserver.Entities.Part;
import com.mynotesapp.server.mynotesappserver.Entities.SubTopics;
import com.mynotesapp.server.mynotesappserver.Repositories.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartService {
    @Autowired
    private PartsRepository partsRepository;

    public List<Part> findBySubTopic(SubTopics subTopics){
        return partsRepository.findBySubTopic(subTopics);
    }

    public List<Part> find(){
        return partsRepository.findAll();
    }
    public Part save(Part part){
       var dbPart =  partsRepository.save(part);
       return dbPart;
    }


}
