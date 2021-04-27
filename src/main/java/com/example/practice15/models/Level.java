package com.example.practice15.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "level")
public class Level {
    @Id
    @SequenceGenerator(name = "level_seq", sequenceName =
            "level_sequence", allocationSize = 1)
    @GeneratedValue(generator = "level_seq", strategy =
            GenerationType.SEQUENCE)
    private int id;
    @Column(name = "complexity")
    private String complexity;
    @Column (name = "level_name")
    private String levelName;
    @ManyToOne
    @JsonIgnore
    public Game game;
}
