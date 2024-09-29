package org.codecrafters.educa.db;

import org.codecrafters.educa.profiles.Student;

import java.util.List;

public interface IStudentDAO {
    void insert(Student student);
    void update(Student student);
    void delete(int id);
    List<Student> getAll();
    Student getById(int id);
}
