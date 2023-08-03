package com.mynotesapp.server.mynotesappserver.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "parts")
public class Part {

    @Id
    @Column(name = "part_slug")
    private String partSlug;

    @Column(name = "part_name")
    private String partName;

    @ManyToOne
    @JoinColumn(name = "subtopic_slug")
    private SubTopics subTopic;

    public String getPartSlug() {
        return partSlug;
    }


    public void setPartSlug(String partSlug) {
        this.partSlug = partSlug;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public SubTopics getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(SubTopics subTopic) {
        this.subTopic = subTopic;
    }

    @PrePersist
    private void generatePartSlugWithName(){
        this.partSlug = generateByName(this.partName);
    }

    private String generateByName(String partName) {
        return  partName.replace(" ", "").toLowerCase();
    }


}