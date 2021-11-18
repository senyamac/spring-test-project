/*
 * Copyright Avaya Inc., All Rights Reserved. THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Avaya
 * Inc. The copyright notice above does not evidence any actual or intended publication of such
 * source code. Some third-party source code components may have been modified from their original
 * versions by Avaya Inc. The modifications are Copyright Avaya Inc., All Rights Reserved. Avaya -
 * Confidential & Restricted. May not be distributed further without written permission of the Avaya
 * owner.
 */

package com.example.springapi.repository;

import com.example.springapi.models.JokeEntity;
import com.example.springapi.models.JokeLength;
import com.example.springapi.models.ReverseJoke;
import com.example.springapi.util.AppConst;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class JokeService {
  private final RestTemplate restTemplate;
  private final JokeRepository jokeRepository;
  private final JokeLength jokeLength;
  private final ReverseJoke reverseJoke;

  public JokeService(JokeRepository jokeRepository) {
    this.jokeRepository = jokeRepository;
    jokeLength = new JokeLength();
    reverseJoke = new ReverseJoke();
    restTemplate = new RestTemplate();
  }

  public String getReversedJoke(JokeEntity jokeEntity) {
    String result = reverseJoke.actionWithJoke(jokeEntity);
    log.debug(result);
    return result;
  }

  public String getJokeLength(JokeEntity jokeEntity) {
    String result = jokeLength.actionWithJoke(jokeEntity);
    log.debug(result);
    return result;
  }

  public JokeEntity getNewJoke() {
    String message = restTemplate.getForObject(AppConst.URL_API, String.class);
    log.debug(message);
    ObjectMapper mapper = new ObjectMapper();
    JokeEntity jokeEntity  = new JokeEntity();
    try {
      jokeEntity = mapper.readValue(message, JokeEntity.class);
      log.debug(jokeEntity.toString());
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
    }
    return jokeEntity;
  }

  public void saveJoke(@NonNull JokeEntity jokeEntity) {
    jokeRepository.save(jokeEntity);
  }

  public List<JokeEntity> getAllJokes() {
    return jokeRepository.findAll();
  }

  public List<JokeEntity> findJokeByType(String type) {
    return jokeRepository.findByType(type);
  }

  public JokeEntity findJokeById(int id) {
    return jokeRepository.findById(id).orElse(null);
  }
}
