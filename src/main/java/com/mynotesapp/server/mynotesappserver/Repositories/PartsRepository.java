package com.mynotesapp.server.mynotesappserver.Repositories;

import com.mynotesapp.server.mynotesappserver.Entities.Part;
import com.mynotesapp.server.mynotesappserver.Entities.SubTopics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PartsRepository extends JpaRepository<Part, String> {

    List<Part> findBySubTopic(SubTopics subTopic);
}

