package com.example.marketour.model.image;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "description")
    private String description;

    //in millis since epoch
    @Column(name = "create_time", nullable = false)
    private Long createTime;

    @Lob
    private byte[] data;
}
