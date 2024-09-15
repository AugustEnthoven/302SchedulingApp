package org.codecrafters.educa.profiles;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Class for creating Schedule entry object
 */
public class Schedule {
    private int studentID;
    private String className;
    private int time;
    private int preference;
    private String DOB;
    private String conditions;

    /**
     * Constructor for a schedule entry object.
     * @param studentID The student id that the schedule entry belongs to
     * @param className The name of the class the student is attending
     * @param time The time the class takes place at
     * @param preference The preference of how much this student can be taken out of this class
     */
    public Schedule (int studentID, String className, int time, int preference)
    {
        this.studentID = studentID;
        this.className = className;
        this.time = time;
        this.preference = preference;
    }

    /**
     * Constructor for a schedule entry object without a preference.
     * The preference will be set to the default of 0, meaning they shouldn't be taken out of this class.
     * @param studentID The student id that the schedule entry belongs to
     * @param className The name of the class the student is attending
     * @param time The time the class takes place at
     */
    public Schedule (int studentID, String className, int time)
    {
        this.studentID = studentID;
        this.className = className;
        this.time = time;
        this.preference = 0;
    }

    /**
     * Gets the student's ID for this schedule entry
     * @return returns the student id number
     */
    public int GetStudentID()
    {
        return studentID;
    }

    /**
     * Gets the name of the class
     * @return returns the name of the class
     */
    public String GetClassName()
    {
        return className;
    }

    /**
     * Gets the time of the class
     * @return returns the time of the class as an int
     */
    public int GetTime()
    {
        return time;
    }

    /**
     * Gets the preference of the parent/teacher of if the student should be taken out of this class
     * @return returns the preference as an int
     */
    public int GetPreference()
    {
        return preference;
    }

    /**
     * Updates the time the class takes place
     * @param time The input value is the new time the class will take place
     */
    public void SetTime(int time)
    {
        this.time = time;
    }

    /**
     * Sets the preference for the class as an integer.
     * If the preference is below 0 or above 5 it will be set to 0 or 5.
     * @param newPreference The new preference for if the student should be taken out of this class.
     */
    public void SetPreference(int newPreference)
    {
        if(newPreference > 5)
        {
            newPreference = 5;
        }
        else if (newPreference < 0)
		{
			newPreference = 0;
		}

		this.preference = newPreference;
    }

    /**
     * Sets the new class name
     * @param className The new class name as a string.
     */
    public void SetClass(String className)
    {
        this.className = className;
    }

    /**
     * Returns the schedule entry as a string.
     * @return Returns all information about this schedule entry (student, class name, time and preference)
     */
    public String toString() {
        return "Student ID: " + studentID +
                ", Class name: " + className +
                ", Time of class: " + time +
                ", Preference: " + preference;
    }
}
