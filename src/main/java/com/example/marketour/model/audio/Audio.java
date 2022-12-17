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
    private Long audioId;

    @Column(name = "description")
    private String description;

    @Lob
    private byte[] data;
}
