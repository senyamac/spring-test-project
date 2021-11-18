/*
 * Copyright Avaya Inc., All Rights Reserved. THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Avaya
 * Inc. The copyright notice above does not evidence any actual or intended publication of such
 * source code. Some third-party source code components may have been modified from their original
 * versions by Avaya Inc. The modifications are Copyright Avaya Inc., All Rights Reserved. Avaya -
 * Confidential & Restricted. May not be distributed further without written permission of the Avaya
 * owner.
 */

package com.example.springapi.repository;

import com.example.springapi.models.Joke;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JokeService {
  private JokeRepository jokeRepository;

  public JokeService(JokeRepository jokeRepository) {
    this.jokeRepository = jokeRepository;
  }

  public void saveJoke(Joke joke) {
    jokeRepository.save(joke);
  }

  public List<Joke> findAll() {
    return jokeRepository.findAll();
  }

  public Joke findById(int id) {
    return jokeRepository.findById(id).orElse(null);
  }
}
