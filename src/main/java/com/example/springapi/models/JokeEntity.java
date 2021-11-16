/*
 * Copyright Avaya Inc., All Rights Reserved. THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Avaya
 * Inc. The copyright notice above does not evidence any actual or intended publication of such
 * source code. Some third-party source code components may have been modified from their original
 * versions by Avaya Inc. The modifications are Copyright Avaya Inc., All Rights Reserved. Avaya -
 * Confidential & Restricted. May not be distributed further without written permission of the Avaya
 * owner.
 */

package com.example.springapi.models;

import com.example.springapi.util.AppConst;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "jokes")
@AllArgsConstructor
@NoArgsConstructor
public class JokeEntity {
  @Id
  @Getter
  @Setter
  private int id;
  @Getter
  @Setter
  @Column(name="category")
  private String category;
  @Getter
  @Setter
  @Column(name="type")
  private String type;
  @Setter
  @Column(name="joke")
  private String joke;
  @Getter
  @Setter
  private String setup;
  @Getter
  @Setter
  private String delivery;

  public String getJoke() {
    switch (type) {
      case AppConst.SINGLE:
        return joke;
      case AppConst.TWOPART:
        return setup + " - " + delivery;
      default:
        return "";
    }
  }

  public String toString() {
    switch (type) {
      case AppConst.SINGLE:
        return "Joke: " + joke +
            "\nAdditions: { id: " + id + ", type: " + type +
            ", category: " + category + " }";
      case AppConst.TWOPART:
        return "Joke: " + setup + " - " + delivery +
            "\nAdditions: { id: " + id + ", type: " + type +
            ", category: " + category + " }";
      default:
        return "Joke is empty";
    }
  }
}
