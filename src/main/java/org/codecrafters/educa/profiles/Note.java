package org.codecrafters.educa.profiles;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * class for Note object
 */
public class Note {
    private int studentId;
    private int creatorId;
    private String dateCreated;
    private String contents;

    /**
     * Constructor for new Note object
     * @param studentId the ID number of the student the note is assigned to
     * @param creatorId the ID number of the teacher assigning the note
     * @param contents the contents of the note
     */
    public Note(int studentId, int creatorId, String contents){
        this.studentId = studentId;
        this.creatorId = creatorId;
        this.dateCreated = LocalDate.now().toString();
        this.contents = contents;
    }

    /**
     * getter method for student ID
     * @return ID of the student the note is assigned to
     */
    public int getStudentId(){return studentId;}

    /**
     * setter method for student ID
     * @param studentId new Student ID
     */
    public void setStudentId(int studentId){this.studentId = studentId;}

    /**
     * getter method for teacher ID
     * @return ID of the teacher writing the note
     */
    public int getCreatorId(){return creatorId;}

    /**
     * setter method for teacher ID
     * @param creatorId new teacher ID
     */
    public void setCreatorId(int creatorId){this.creatorId = creatorId;}

    /**
     * getter method for date created
     * @return the date that the note was written
     */
    public String getDateCreated(){return dateCreated;}

    /**
     * setter method for date created
     * @param dateCreated new date created
     */
    public void setDateCreated(String dateCreated){this.dateCreated = dateCreated;}

    /**
     * getter method for contents
     * @return contents of the written note
     */
    public String getContents(){return dateCreated;}

    /**
     * setter method for contents
     * @param contents new note contents
     */
    public void setContents(String contents){this.contents = contents;}

    public String toString(){
        return "Student ID: " + studentId +
                ", Teacher ID: " + creatorId +
                ", Date Created: " + dateCreated +
                "\n Contents: " + contents;
    }
}
