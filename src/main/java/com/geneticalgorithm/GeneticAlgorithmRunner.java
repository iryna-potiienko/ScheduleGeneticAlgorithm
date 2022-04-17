package com.geneticalgorithm;

import static com.geneticalgorithm.utils.TimetableUtils.initializeTimetable;

import com.geneticalgorithm.algorithm.GeneticAlgorithm;
import com.geneticalgorithm.algorithm.Timetable;
import com.geneticalgorithm.model.Class;
import com.geneticalgorithm.model.Population;


public class GeneticAlgorithmRunner {

  public static void main(String[] args) {
    Timetable timetable = initializeTimetable();

    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

    Population population = geneticAlgorithm.initPopulation(timetable);

    geneticAlgorithm.evalPopulation(population, timetable);

    int generation = 1;

    int maxGenerations = 1000;
    while (!geneticAlgorithm.isTerminationConditionMet(generation, maxGenerations) && !geneticAlgorithm.isTerminationConditionMet(population)) {

      System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());

      population = geneticAlgorithm.crossoverPopulation(population);

      population = geneticAlgorithm.mutatePopulation(population, timetable);

      geneticAlgorithm.evalPopulation(population, timetable);

      generation++;
    }

    timetable.createClasses(population.getFittest(0));

    System.out.println();
    System.out.println("Solution found in " + generation + " generations");
    System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
    System.out.println("Clashes: " + timetable.calculateClashes());

    System.out.println();
    Class[] classes = timetable.getClasses();
    int classIndex = 1;

    for (Class bestClass : classes) {
      System.out.println("Class " + classIndex + ":");
      System.out.println("Module: " + timetable.getModule(bestClass.getModuleId()).getModuleName());
      System.out.println("Group: " + timetable.getGroup(bestClass.getGroupId()).getGroupId());
      System.out.println("Room: " + timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
      System.out.println("Professor: " + timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
      System.out.println("Time: " + timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
      System.out.println("-----");
      classIndex++;
    }
  }
}
