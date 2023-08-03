package com.mynotesapp.server.mynotesappserver.Repositories;
import com.mynotesapp.server.mynotesappserver.Entities.Part;
import com.mynotesapp.server.mynotesappserver.Entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {
    List<Topic> findByPart(Part part);
}
