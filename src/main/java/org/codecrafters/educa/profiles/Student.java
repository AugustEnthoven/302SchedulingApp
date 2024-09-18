package org.codecrafters.educa.profiles;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Class for creating Student object
 */
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String DOB;
    private String conditions;

    /**
     * Constructor to build Student Object for  the given ID, first name, last name, age,
     * date of birth and conditions.
     * @param id The Student's ID number
     * @param firstName The Student's First Name
     * @param lastName The Student's Surname
     * @param DOB The Student's Date of Birth in dd/mm/yyyy format
     * @param conditions The Student's listed conditions
     */
    public Student(int id, String firstName, String lastName, String DOB, String conditions){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.age = Period.between(LocalDate.parse(DOB, formatter), LocalDate.now()).getYears();
        this.conditions = conditions;
    }

    /**
     * Constructor for Student object with Auto-generated ID and age built from the given
     * first name, last name, date of birth and conditions
     * @param firstName The Student's first name
     * @param lastName The Student's surname
     * @param DOB the Student's date of birth in dd/mm/yyyy format
     * @param conditions The Student's listed Conditions
     */
    public Student(String firstName, String lastName, String DOB, String conditions){
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.age = Period.between(LocalDate.parse(DOB, formatter), LocalDate.now()).getYears();
        this.conditions = conditions;
    }

    /**
     * returns ID value of given Student object
     * @return student's ID number
     */
    public int getId() { return id;}

    /**
     * returns first name of given Student object
     * @return Student's first name
     */
    public String getFirstName() {return firstName;}

    /**
     * returns last name of given Student object
     * @return Student's last name
     */
    public String getLastName() {return lastName;}

    /**
     * returns DOB of given Student object
     * @return student's Date of birth in dd/mm/yyyy format
     */
    public String getDOB() {return DOB;}

    /**
     * returns age of given Student object
     * @return student's age
     */
    public int getAge() {return age;}

    /**
     * returns conditions of given Student object
     * @return the Student's listed conditions
     */
    public String getConditions() {return conditions;}

    /**
     * sets the first name of the given Student object
     * @param firstName the new first Name for the Student object
     */
    public void setFirstName(String firstName){ this.firstName = firstName;}

    /**
     * sets the last name of the given Student object
     * @param lastName the new last name for the Student object
     */
    public void setLastName(String lastName){this.lastName = lastName;}

    /**
     * sets the DOB of the given Student object
     * @param DOB the new DOB for the Student object
     */
    public void setDOB(String DOB){
        this.DOB = DOB;
        this.age = Period.between(LocalDate.parse(DOB), LocalDate.now()).getYears();
    }

    /**
     * sets the conditions of the given Student
     * @param conditions the new conditions for the Student object
     */
    public void setConditions(String conditions){this.conditions = conditions;};

    /**
     * Parses the information contained in the Student object to a single string
     * @return String containing all the information about the Student
     */
    public String toString() {
        return "ID: " + id +
                ", Name: " + firstName + " " + lastName +
                ", Date of Birth: " + DOB + ", Age: " + age +
                ", Conditions: " + conditions;
    }
}
