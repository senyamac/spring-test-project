/*
 * Copyright Avaya Inc., All Rights Reserved. THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Avaya
 * Inc. The copyright notice above does not evidence any actual or intended publication of such
 * source code. Some third-party source code components may have been modified from their original
 * versions by Avaya Inc. The modifications are Copyright Avaya Inc., All Rights Reserved. Avaya -
 * Confidential & Restricted. May not be distributed further without written permission of the Avaya
 * owner.
 */

package com.example.springapi;

import com.example.springapi.models.JokeEntity;
import com.example.springapi.repository.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
  @Autowired
  JokeService jokeService;

  @Scheduled(fixedDelay = 3000)
  public void jokeProcess() {
    JokeEntity joke = jokeService.getNewJoke();
    jokeService.saveJoke(joke);
    jokeService.getReversedJoke(joke);
    jokeService.getJokeLength(joke);
  }
}
