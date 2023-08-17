package com.mynotesapp.server.mynotesappserver.GetPostObjects;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class RecievedTopic {
    private String topicSlug;
    private String name;
    private MultipartFile syntaxImage;

    @Override
    public String toString() {
        return "RecievedTopic{" +
                "topicSlug='" + topicSlug + '\'' +
                ", name='" + name + '\'' +
                ", syntaxImage=" + syntaxImage +
                ", syntaxComment='" + syntaxComment + '\'' +
                ", resultImage=" + resultImage +
                ", resultComment='" + resultComment + '\'' +
                ", partSlug='" + partSlug + '\'' +
                '}';
    }

    private String syntaxComment;
    private MultipartFile resultImage;
    private String resultComment;

    private String partSlug;

    public String getPartSlug() {
        return partSlug;
    }

    public void setPartSlug(String partSlug) {
        this.partSlug = partSlug;
    }

    public String getTopicSlug() {
        return topicSlug;
    }

    public void setTopicSlug(String topicSlug) {
        this.topicSlug = topicSlug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getSyntaxImage() {
        return syntaxImage;
    }

    public void setSyntaxImage(MultipartFile syntaxImage) {
        this.syntaxImage = syntaxImage;
    }

    public String getSyntaxComment() {
        return syntaxComment;
    }

    public void setSyntaxComment(String syntaxComment) {
        this.syntaxComment = syntaxComment;
    }

    public MultipartFile getResultImage() {
        return resultImage;
    }

    public void setResultImage(MultipartFile resultImage) {
        this.resultImage = resultImage;
    }

    public String getResultComment() {
        return resultComment;
    }

    public void setResultComment(String resultComment) {
        this.resultComment = resultComment;
    }
}
