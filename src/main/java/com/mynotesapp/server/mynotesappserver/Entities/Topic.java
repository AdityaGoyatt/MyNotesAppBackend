package com.mynotesapp.server.mynotesappserver.Entities;
import jakarta.persistence.*;


@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @Column(name = "topic_slug", length = 50)
    private String topicSlug;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "syntax_image", length = 255)
    private String syntaxImage;

    @Column(name = "syntax_comment", columnDefinition = "TEXT")
    private String syntaxComment;

    @Column(name = "result_image", length = 255)
    private String resultImage;

    @Column(name = "result_comment", columnDefinition = "TEXT")
    private String resultComment;

    @ManyToOne
    @JoinColumn(name = "part_slug")
    private Part part;

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

    public String getSyntaxImage() {
        return syntaxImage;
    }

    public void setSyntaxImage(String syntaxImage) {
        this.syntaxImage = syntaxImage;
    }

    public String getSyntaxComment() {
        return syntaxComment;
    }

    public void setSyntaxComment(String syntaxComment) {
        this.syntaxComment = syntaxComment;
    }

    public String getResultImage() {
        return resultImage;
    }

    public void setResultImage(String resultImage) {
        this.resultImage = resultImage;
    }

    public String getResultComment() {
        return resultComment;
    }

    public void setResultComment(String resultComment) {
        this.resultComment = resultComment;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    @PrePersist
    public void generateSlugByName(){
        this.topicSlug = SlugNameSetter.setSlugByName(this.name);
    }
}
