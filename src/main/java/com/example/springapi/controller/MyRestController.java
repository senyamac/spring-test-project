package com.example.springapi.controller;

import com.example.springapi.models.Joke;
import com.example.springapi.models.JokeLength;
import com.example.springapi.models.ReverseJoke;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MyRestController {
  private final String url = "https://v2.jokeapi.dev/joke/Any?format=json";
  private RestTemplate restTemplate = new RestTemplate();

  @RequestMapping("/joke")
  public String getJoke() {
    String message = this.restTemplate.getForObject(url, String.class);
    System.out.println(message);
    ObjectMapper mapper = new ObjectMapper();
    try {
      Joke joke = mapper.readValue(message, Joke.class);
      System.out.println(joke.getJokeString());
      System.out.println(joke.toString());

      JokeLength jokeLength = new JokeLength(joke);
      jokeLength.actionWithJoke();

      ReverseJoke reverseJoke = new ReverseJoke(joke);
      reverseJoke.actionWithJoke();
    } catch (Exception e) {
      System.out.println(e);
    }
    return message;
  }
}
