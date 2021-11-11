package com.example.springapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MyRestController {
  private final String url = "https://v2.jokeapi.dev/joke/Any?format=json";
  private RestTemplate restTemplate = new RestTemplate();

  @RequestMapping("/joke")
  public String getJod() {
    String message = this.restTemplate.getForObject(url, String.class);
    System.out.println(message);
    ObjectMapper mapper = new ObjectMapper();
    try {
      Joke joke = mapper.readValue(message, Joke.class);
      System.out.println(joke.toStingOnlyJoke());
      System.out.println(joke.toString());
    } catch (Exception e) {
      System.out.println(e);
    }
    return message;
  }
}
