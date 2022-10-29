package com.example.marketour.model.audio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "audio")
@Getter
@Setter
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "audio_id")
    private int audioId;

    @Column(name = "description")
    private String description;

    //in millis since epoch
    @Column(name = "create_time", nullable = false)
    private Long createTime;

    @Lob
    private byte[] data;
}
