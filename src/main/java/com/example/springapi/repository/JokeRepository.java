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
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JokeRepository  extends JpaRepository<JokeEntity, Integer> {
  List<JokeEntity> findByType(String type);
}
