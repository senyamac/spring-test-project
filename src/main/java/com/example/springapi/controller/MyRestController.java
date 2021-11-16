package com.example.springapi.controller;

import com.example.springapi.models.JokeEntity;
import com.example.springapi.repository.JokeService;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyRestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(MyRestController.class);
  private static final String URL_API = "https://v2.jokeapi.dev/joke/Any?format=json";

  @NonNull private final RestTemplate restTemplate;

  @Autowired
  JokeService jokeService;

  public MyRestController() {
    this.restTemplate = new RestTemplate();
  }

  //TODO Add constructor initialization
  //TODO Remove internal joke object in classes
  //TODO Save joke to Postgres database
  //TODO Hibernate + Spring JPA
  //TODO NonNull lombok
  //TODO Sequence diagram
  // Optional: add logger slf4j

  @RequestMapping("/joke")
  public ResponseEntity<String> getNewJoke() {
    String message = restTemplate.getForObject(URL_API, String.class);
    LOGGER.debug(message);
    String result;
    result = jokeService.parseAndSaveJoke(message);
    if (result == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(result, HttpStatus.OK);
    }
  }

  @RequestMapping("/jokebase/typed/{subpath}")
  public ResponseEntity<List<JokeEntity>> getJokesByType(@PathVariable("subpath") String subpath) {
    List<JokeEntity> jokeEntities;
    jokeEntities = new ArrayList<>(jokeService.findJokeByType(subpath));
    if (jokeEntities.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(jokeEntities, HttpStatus.OK);
  }

  @RequestMapping("/jokebase")
  public ResponseEntity<List<JokeEntity>> getAllJokes() {
    List<JokeEntity> jokeEntities;
    jokeEntities = new ArrayList<>(jokeService.getAllJokes());
    if (jokeEntities.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(jokeEntities, HttpStatus.OK);
  }

  @RequestMapping("/jokebase/{subpath}")
  public ResponseEntity<JokeEntity>  getJokeById(@PathVariable("subpath") String subpath) {
    int id = 0;
    JokeEntity jokeEntity;
    try {
      id = Integer.parseInt(subpath);
    } catch (NumberFormatException e) {
      LOGGER.error(e.getMessage(), e);
    }
    jokeEntity = jokeService.findJokeById(id);
    if (jokeEntity == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(jokeEntity, HttpStatus.OK);
    }
  }

  @RequestMapping("/jokebase/length/{subpath}")
  public ResponseEntity<String>  getLengthJokeById(@PathVariable("subpath") String subpath) {
    int id = 0;
    JokeEntity jokeEntity;
    try {
      id = Integer.parseInt(subpath);
    } catch (NumberFormatException e) {
      LOGGER.error(e.getMessage(), e);
    }
    jokeEntity = jokeService.findJokeById(id);
    if (jokeEntity == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(jokeService.getJokeLength(jokeEntity), HttpStatus.OK);
    }
  }

  @RequestMapping("/jokebase/reverse/{subpath}")
  public ResponseEntity<String>  getReversedJokeById(@PathVariable("subpath") String subpath) {
    int id = 0;
    JokeEntity jokeEntity;
    try {
      id = Integer.parseInt(subpath);
    } catch (NumberFormatException e) {
      LOGGER.error(e.getMessage(), e);
    }
    jokeEntity = jokeService.findJokeById(id);
    if (jokeEntity == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(jokeService.getReversedJoke(jokeEntity), HttpStatus.OK);
    }
  }
}
