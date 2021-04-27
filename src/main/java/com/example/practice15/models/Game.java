package com.example.practice15.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "game")
public class Game {
    @Id
    @SequenceGenerator(name = "game_seq", sequenceName =
            "game_sequence", allocationSize = 1)
    @GeneratedValue(generator = "game_seq", strategy =
            GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date creationDate;
    @OneToMany(mappedBy = "game")
    private List<Level> levels;
}
