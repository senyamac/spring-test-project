package com.example.springapi.controller;

import com.example.springapi.models.Joke;
import com.example.springapi.models.JokeLength;
import com.example.springapi.models.ReverseJoke;
import com.example.springapi.repository.JokeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
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

  private final RestTemplate restTemplate;
  private final JokeLength jokeLength;
  private final ReverseJoke reverseJoke;

  @Autowired
  JokeService jokeService;

  public MyRestController() {
    restTemplate = new RestTemplate();
    jokeLength = new JokeLength();
    reverseJoke = new ReverseJoke();
  }

  //TODO Add constructor initialization
  //TODO Remove internal joke object in classes
  //TODO Save joke to Postgres database
  //TODO Hibernate + Spring JPA
  //TODO NonNull lombok
  //TODO Sequence diagram
  // Optional: add logger slf4j

  @RequestMapping("/joke")
  public String getJoke() {
    String message = restTemplate.getForObject(URL_API, String.class);
    LOGGER.debug(message);
    String result = "";
    ObjectMapper mapper = new ObjectMapper();
    try {
      Joke joke = mapper.readValue(message, Joke.class);
      String jokeString = joke.toString();
      LOGGER.debug(jokeString);
      result = jokeString;
      String jokeLen = jokeLength.actionWithJoke(joke);
      LOGGER.debug(jokeLen);
      result += "\n" + jokeLen;
      String jokeRev = reverseJoke.actionWithJoke(joke);
      LOGGER.debug(jokeRev);
      result += "\n" + jokeRev;
      jokeService.saveJoke(joke);
    } catch (JsonProcessingException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return result;
  }

  @RequestMapping("/jokebase")
  public ResponseEntity<List<Joke>> getAllJokes() {
    List<Joke> jokes;
    jokes = new ArrayList<>(jokeService.findAll());

    if (jokes.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(jokes, HttpStatus.OK);
  }

  @RequestMapping("/jokebase/{subpath}")
  public Joke getJokeById(@PathVariable("subpath") String subpath) {
    int id = 0;
    try {
      id = Integer.parseInt(subpath);
    } catch (NumberFormatException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return jokeService.findById(id);
  }
}
