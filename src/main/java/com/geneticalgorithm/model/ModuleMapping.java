package com.geneticalgorithm.model;

public enum ModuleMapping {
  COMPUTER_SCIENCE(1),
  ENGLISH(2),
  MATH(3),
  PHYSIC(4),
  HISTORY(5),
  DRAMA(6);


  private final int value;

  ModuleMapping(int value) {this.value = value;}

  public int getValue() {
    return value;
  }
}
