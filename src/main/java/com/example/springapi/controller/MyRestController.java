package com.example.springapi.controller;

import com.example.springapi.models.JokeEntity;
import com.example.springapi.repository.JokeService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyRestController {
  @Autowired
  JokeService jokeService;

  @RequestMapping("/jokebase/typed/{subpath}")
  public ResponseEntity<List<JokeEntity>> getJokesByType(@PathVariable("subpath") String subpath) {
    List<JokeEntity> jokeEntities = new ArrayList<>(jokeService.findJokeByType(subpath));
    if (jokeEntities.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(jokeEntities, HttpStatus.OK);
  }

  @RequestMapping("/jokebase")
  public ResponseEntity<List<JokeEntity>> getAllJokes() {
    List<JokeEntity> jokeEntities = new ArrayList<>(jokeService.getAllJokes());
    if (jokeEntities.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(jokeEntities, HttpStatus.OK);
  }

  @RequestMapping("/jokebase/{subpath}")
  public ResponseEntity<JokeEntity> getJokeById(@PathVariable("subpath") String subpath) {
    int id = 0;
    try {
      id = Integer.parseInt(subpath);
    } catch (NumberFormatException e) {
      log.error(e.getMessage(), e);
    }
    JokeEntity jokeEntity = jokeService.findJokeById(id);
    if (jokeEntity == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(jokeEntity, HttpStatus.OK);
    }
  }

  @RequestMapping("/jokebase/length/{subpath}")
  public ResponseEntity<String> getJokeLengthById(@PathVariable("subpath") String subpath) {
    int id = 0;
    try {
      id = Integer.parseInt(subpath);
    } catch (NumberFormatException e) {
      log.error(e.getMessage(), e);
    }
    JokeEntity jokeEntity = jokeService.findJokeById(id);
    if (jokeEntity == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(jokeService.getJokeLength(jokeEntity), HttpStatus.OK);
    }
  }

  @RequestMapping("/jokebase/reverse/{subpath}")
  public ResponseEntity<String> getReversedJokeById(@PathVariable("subpath") String subpath) {
    int id = 0;
    try {
      id = Integer.parseInt(subpath);
    } catch (NumberFormatException e) {
      log.error(e.getMessage(), e);
    }
    JokeEntity jokeEntity = jokeService.findJokeById(id);
    if (jokeEntity == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(jokeService.getReversedJoke(jokeEntity), HttpStatus.OK);
    }
  }
}
