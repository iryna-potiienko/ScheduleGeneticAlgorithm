package com.geneticalgorithm.algorithm;

import com.geneticalgorithm.model.Class;
import com.geneticalgorithm.model.Group;
import com.geneticalgorithm.model.Module;
import com.geneticalgorithm.model.Professor;
import com.geneticalgorithm.model.Room;
import com.geneticalgorithm.model.Timeslot;
import java.util.HashMap;
import java.util.Map;


public class Timetable {

  private final Map<Integer, Room> rooms;

  private final Map<Integer, Professor> professors;

  private final Map<Integer, Module> modules;

  private final Map<Integer, Group> groups;

  private final Map<Integer, Timeslot> timeslots;

  private Class[] classes;

  private int numClasses = 0;


  public Timetable() {
    this.rooms = new HashMap<>();
    this.professors = new HashMap<>();
    this.modules = new HashMap<>();
    this.groups = new HashMap<>();
    this.timeslots = new HashMap<>();
  }

  @SuppressWarnings("CopyConstructorMissesField")
  public Timetable(Timetable cloneable) {
    this.rooms = cloneable.getRooms();
    this.professors = cloneable.getProfessors();
    this.modules = cloneable.getModules();
    this.groups = cloneable.getGroups();
    this.timeslots = cloneable.getTimeslots();
  }

  private Map<Integer, Group> getGroups() {
    return this.groups;
  }

  private Map<Integer, Timeslot> getTimeslots() {
    return this.timeslots;
  }

  private Map<Integer, Module> getModules() {
    return this.modules;
  }

  private Map<Integer, Professor> getProfessors() {
    return this.professors;
  }

  public void addRoom(int roomId, String roomName, int capacity) {
    this.rooms.put(roomId, new Room(roomId, roomName, capacity));
  }

  public void addProfessor(int professorId, String professorName) {
    this.professors.put(professorId, new Professor(professorId, professorName));
  }

  public void addModule(int moduleId, String moduleCode, String module, int[] professorIds) {
    this.modules.put(moduleId, new Module(moduleId, moduleCode, module, professorIds));
  }

  public void addGroup(int groupId, int groupSize, int[] moduleIds) {
    this.groups.put(groupId, new Group(groupId, groupSize, moduleIds));
    this.numClasses = 0;
  }

  public void addTimeslot(int timeslotId, String timeslot) {
    this.timeslots.put(timeslotId, new Timeslot(timeslotId, timeslot));
  }

  public void createClasses(Individual individual) {
    Class[] classes = new Class[this.getNumClasses()];

    int[] chromosome = individual.getChromosome();
    int chromosomePos = 0;
    int classIndex = 0;

    for (Group group : this.getGroupsAsArray()) {
      int[] moduleIds = group.getModuleIds();
      for (int moduleId : moduleIds) {
        classes[classIndex] = new Class(classIndex, group.getGroupId(), moduleId);

        classes[classIndex].addTimeslot(chromosome[chromosomePos]);
        chromosomePos++;

        classes[classIndex].setRoomId(chromosome[chromosomePos]);
        chromosomePos++;

        classes[classIndex].addProfessor(chromosome[chromosomePos]);
        chromosomePos++;

        classIndex++;
      }
    }

    this.classes = classes;
  }

  public Room getRoom(int roomId) {
    if (!this.rooms.containsKey(roomId)) {
      System.out.println("Rooms doesn't contain key " + roomId);
    }
    return this.rooms.get(roomId);
  }

  public Map<Integer, Room> getRooms() {
    return this.rooms;
  }

  public Room getRandomRoom() {
    Object[] roomsArray = this.rooms.values().toArray();
    return (Room) roomsArray[(int) (roomsArray.length * Math.random())];
  }

  public Professor getProfessor(int professorId) {
    return this.professors.get(professorId);
  }

  public Module getModule(int moduleId) {
    return this.modules.get(moduleId);
  }

  public Group getGroup(int groupId) {
    return this.groups.get(groupId);
  }

  public Group[] getGroupsAsArray() {
    return this.groups.values().toArray(new Group[0]);
  }

  public Timeslot getTimeslot(int timeslotId) {
    return this.timeslots.get(timeslotId);
  }

  public Timeslot getRandomTimeslot() {
    Object[] timeslotArray = this.timeslots.values().toArray();
    return (Timeslot) timeslotArray[(int) (timeslotArray.length * Math.random())];
  }

  public Class[] getClasses() {
    return this.classes;
  }

  public int getNumClasses() {
    if (this.numClasses > 0) {
      return this.numClasses;
    }

    int numClasses = 0;
    Group[] groups = this.groups.values().toArray(new Group[0]);
    for (Group group : groups) {
      numClasses += group.getModuleIds().length;
    }
    this.numClasses = numClasses;

    return this.numClasses;
  }

  public int calculateClashes() {
    int clashes = 0;

    for (Class classA : this.classes) {
      int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
      int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();

      if (roomCapacity < groupSize) {
        clashes++;
      }

      for (Class classB : this.classes) {
        if (classA.getRoomId() == classB.getRoomId() && classA.getTimeslotId() == classB.getTimeslotId()
            && classA.getClassId() != classB.getClassId()) {
          clashes++;
          break;
        }
      }

      for (Class classB : this.classes) {
        if (classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId()
            && classA.getClassId() != classB.getClassId()) {
          clashes++;
          break;
        }
      }
    }

    return clashes;
  }
}