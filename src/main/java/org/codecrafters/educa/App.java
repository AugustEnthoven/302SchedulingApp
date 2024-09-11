package org.codecrafters.educa;

import org.codecrafters.educa.db.*;
import org.codecrafters.educa.profiles.*;

import java.sql.Connection;
import java.util.List;

public class App {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.createTable();;

        studentDAO.insert(new Student("Jayden", "Wilson", "11/03/2000", "Kinda sucks at Java"));
        studentDAO.insert(new Student("Jayden", "But a little bit better", "11/03/2000", "Can make something work in Java"));

        List<Student> students = studentDAO.getAll();

        for (Student s : students){
            System.out.println(s);
        }

        studentDAO.close();
    }
}