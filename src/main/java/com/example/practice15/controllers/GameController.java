package com.example.practice15.controllers;


import com.example.practice15.models.Game;
import com.example.practice15.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/addGame")
    public void addGame (@RequestBody Game game) {
        gameService.addGame(game);
    }

    @GetMapping("/game/{id}")
    public Game findGame (@PathVariable int id) {
        return gameService.findGame(id);
    }

    @GetMapping("/games")
    public List<Game> findAll () {
        return gameService.findAllGames();
    }

    @DeleteMapping("/del/{id}")
    public void delGame (@PathVariable int id) {
        gameService.deleteGame(id);
    }

    @GetMapping("/filterName")
    public List<Game> filterByGameName() {
        return gameService.filterGameName();
    }

    @GetMapping("/filterCreationDate")
    public List<Game> filterByCreationDate() {
        return gameService.filterByCreationDate();
    }

    @GetMapping("/filterGameId")
    public List<Game> filterById() {
        return gameService.filterById();
    }
}
