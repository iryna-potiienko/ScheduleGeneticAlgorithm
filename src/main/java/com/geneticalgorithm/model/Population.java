package com.geneticalgorithm.model;

import com.geneticalgorithm.algorithm.Individual;
import com.geneticalgorithm.algorithm.Timetable;
import java.util.Arrays;
import java.util.Random;


public class Population {

  private final Individual[] population;

  private double populationFitness = -1;

  public Population(int populationSize) {
    this.population = new Individual[populationSize];
  }

  public Population(int populationSize, Timetable timetable) {
    this.population = new Individual[populationSize];

    for (int individualCount = 0; individualCount < populationSize; individualCount++) {
      Individual individual = new Individual(timetable);
      this.population[individualCount] = individual;
    }
  }

  public Individual[] getIndividuals() {
    return this.population;
  }

  public Individual getFittest(int offset) {
    Arrays.sort(this.population, (first, second) -> {
      if (first.getFitness() > second.getFitness()) {
        return -1;
      } else if (first.getFitness() < second.getFitness()) {
        return 1;
      }
      return 0;
    });

    return this.population[offset];
  }

  public void setPopulationFitness(double fitness) {
    this.populationFitness = fitness;
  }

  public double getPopulationFitness() {
    return this.populationFitness;
  }

  public int size() {
    return this.population.length;
  }

  public void setIndividual(int offset, Individual individual) {
    population[offset] = individual;
  }

  public Individual getIndividual(int offset) {
    return population[offset];
  }

  public void shuffle() {
    Random rnd = new Random();
    for (int i = population.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);
      Individual a = population[index];
      population[index] = population[i];
      population[i] = a;
    }
  }
}