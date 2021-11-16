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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JokeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(JokeService.class);

  private final JokeRepository jokeRepository;
  private final JokeLength jokeLength;
  private final ReverseJoke reverseJoke;

  public JokeService(JokeRepository jokeRepository) {
    this.jokeRepository = jokeRepository;
    jokeLength = new JokeLength();
    reverseJoke = new ReverseJoke();
  }

  public String getReversedJoke(JokeEntity jokeEntity) {
    String result = reverseJoke.actionWithJoke(jokeEntity);
    LOGGER.debug(result);
    return result;
  }

  public String getJokeLength(JokeEntity jokeEntity) {
    String result = jokeLength.actionWithJoke(jokeEntity);
    LOGGER.debug(result);
    return result;
  }

  public String parseAndSaveJoke(String jokeMessage) {
    String resultJoke;
    ObjectMapper mapper = new ObjectMapper();
    try {
      JokeEntity jokeEntity = mapper.readValue(jokeMessage, JokeEntity.class);
      resultJoke = jokeEntity.toString();
      LOGGER.debug(resultJoke);
      jokeRepository.save(jokeEntity);
      return resultJoke;
    } catch (JsonProcessingException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
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
