package com.geneticalgorithm.utils;

import static com.geneticalgorithm.model.ModuleMapping.COMPUTER_SCIENCE;
import static com.geneticalgorithm.model.ModuleMapping.DRAMA;
import static com.geneticalgorithm.model.ModuleMapping.ENGLISH;
import static com.geneticalgorithm.model.ModuleMapping.HISTORY;
import static com.geneticalgorithm.model.ModuleMapping.MATH;
import static com.geneticalgorithm.model.ModuleMapping.PHYSIC;

import com.geneticalgorithm.algorithm.Timetable;

public class TimetableUtils {


  public static Timetable initializeTimetable() {
    Timetable timetable = new Timetable();

    // Set up rooms
    timetable.addRoom(1, "A1", 15);
    timetable.addRoom(2, "B1", 30);
    timetable.addRoom(4, "D1", 20);
    timetable.addRoom(5, "F1", 25);

    // Set up timeslots
    timetable.addTimeslot(1, "Mon 9:00 - 11:00");
    timetable.addTimeslot(2, "Mon 11:00 - 13:00");
    timetable.addTimeslot(3, "Mon 13:00 - 15:00");
    timetable.addTimeslot(4, "Tue 9:00 - 11:00");
    timetable.addTimeslot(5, "Tue 11:00 - 13:00");
    timetable.addTimeslot(6, "Tue 13:00 - 15:00");
    timetable.addTimeslot(7, "Wed 9:00 - 11:00");
    timetable.addTimeslot(8, "Wed 11:00 - 13:00");
    timetable.addTimeslot(9, "Wed 13:00 - 15:00");
    timetable.addTimeslot(10, "Thu 9:00 - 11:00");
    timetable.addTimeslot(11, "Thu 11:00 - 13:00");
    timetable.addTimeslot(12, "Thu 13:00 - 15:00");
    timetable.addTimeslot(13, "Fri 9:00 - 11:00");
    timetable.addTimeslot(14, "Fri 11:00 - 13:00");
    timetable.addTimeslot(15, "Fri 13:00 - 15:00");

    // Set up professors
    timetable.addProfessor(1, "Dr P Smith");
    timetable.addProfessor(2, "Mrs E Mitchell");
    timetable.addProfessor(3, "Dr R Williams");
    timetable.addProfessor(4, "Mr A Thompson");

    // Set up modules and define the professors that teach them
    timetable.addModule(1, "cs", "Computer Science", new int[]{1, 2});
    timetable.addModule(2, "en", "English", new int[]{1, 3});
    timetable.addModule(3, "ma", "Maths", new int[]{1, 2});
    timetable.addModule(4, "ph", "Physics", new int[]{3, 4});
    timetable.addModule(5, "hi", "History", new int[]{4});
    timetable.addModule(6, "dr", "Drama", new int[]{1, 4});

    // Set up student groups and the modules they take.
    timetable.addGroup(1, 10, new int[]{COMPUTER_SCIENCE.getValue(), MATH.getValue(), PHYSIC.getValue()});
    timetable.addGroup(2, 30, new int[]{ENGLISH.getValue(), MATH.getValue(), HISTORY.getValue(), DRAMA.getValue()});
    timetable.addGroup(3, 18, new int[]{MATH.getValue(), PHYSIC.getValue(), HISTORY.getValue()});
    timetable.addGroup(4, 25, new int[]{COMPUTER_SCIENCE.getValue(), PHYSIC.getValue()});
    timetable.addGroup(5, 20, new int[]{ENGLISH.getValue(), MATH.getValue(), HISTORY.getValue()});
    timetable.addGroup(6, 22, new int[]{COMPUTER_SCIENCE.getValue(), PHYSIC.getValue(), HISTORY.getValue()});
    timetable.addGroup(7, 16, new int[]{COMPUTER_SCIENCE.getValue(), MATH.getValue()});
    timetable.addGroup(8, 18, new int[]{ENGLISH.getValue(), DRAMA.getValue()});
    timetable.addGroup(9, 24, new int[]{COMPUTER_SCIENCE.getValue(), DRAMA.getValue()});
    timetable.addGroup(10, 25, new int[]{MATH.getValue(), PHYSIC.getValue()});

    return timetable;
  }
}
