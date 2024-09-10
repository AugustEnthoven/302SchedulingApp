package org.codecrafters.educa.profiles;

import java.time.LocalDate;
import java.time.Period;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String DOB;
    private String conditions;

    public Student(int id, String firstName, String lastName, int age, String DOB, String conditions){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.age = Period.between(LocalDate.parse(DOB), LocalDate.now()).getYears();
        this.conditions = conditions;
    }

    public Student(String firstName, String lastName, int age, String DOB, String conditions){
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.age = Period.between(LocalDate.parse(DOB), LocalDate.now()).getYears();
        this.conditions = conditions;
    }

    public int getId() { return id;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getDOB() {return DOB;}

    public int getAge() {return age;}

    public String getConditions() {return conditions;}

    public void setFirstName(String firstName){ this.firstName = firstName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public void setDOB(String DOB){
        this.DOB = DOB;
        this.age = Period.between(LocalDate.parse(DOB), LocalDate.now()).getYears();
    }

    public void setConditions(String conditions){this.conditions = conditions;};

    public String toString() {
        return "ID: " + id +
                ", Name: " + firstName + " " + lastName +
                ", Date of Birth" + DOB + ", Age: " + age +
                ", Conditions: " + conditions;
    }
}
