package com.mynotesapp.server.mynotesappserver.services;

import com.mynotesapp.server.mynotesappserver.Entities.Part;
import com.mynotesapp.server.mynotesappserver.Entities.Topic;
import com.mynotesapp.server.mynotesappserver.Repositories.TopicRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Repository
public class TopicService {
    private TopicRepository topicRepository;
    private final String imageDirectory = "src/main/resources/static/images/";
    private final String imageBaseUrl = "http://localhost:8080/api";


    public String saveImage(MultipartFile imageFile, String imageType) throws IOException {
        String fileName = generateUniqueFileName(imageFile);
        String relativeImagePath = imageType + "/" + fileName;

        // Create the target directory if it doesn't exist
        File targetDirectory = new File(imageDirectory + imageType);
        targetDirectory.mkdirs();

        // Copy the uploaded image to the target directory
        Path targetPath = Path.of(imageDirectory, relativeImagePath);
        Files.copy(imageFile.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        URI imageUrl = URI.create(imageBaseUrl + "/" + relativeImagePath);
        return imageUrl.toString();
    }

    private String generateUniqueFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + fileExtension;
    }

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic findById(String slug){
        var result =topicRepository.findById(slug);
        if(!result.isPresent()) throw new RuntimeException("Topic doesnt Exists");
        return result.get();
    }

    public Topic save(Topic topic){
        return topicRepository.save(topic);
    }
    public List<Topic> findByPart(Part part){
        return topicRepository.findByPart(part);
    }

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

}
