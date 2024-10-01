package org.codecrafters.educa.db;

import org.codecrafters.educa.profiles.Student;

import java.util.ArrayList;
import java.util.List;

public class MockStudentDAO implements IStudentDAO{
    private List<Student> Students = new ArrayList<>();

    @Override
    public void insert(Student student) {
        Students.add(student);
    }

    @Override
    public void update(Student student) {
        int index = -1;
        for (Student s : Students){
            if (s.getId() == student.getId()){
                index = Students.indexOf(s);
            }
        }
        Students.set(index, student);
    }

    @Override
    public void delete(int id) {
        int index = -1;
        for (Student s : Students){
            if (s.getId() == id){
                index = Students.indexOf(s);
            }
        }

        Students.remove(index);
    }

    @Override
    public List<Student> getAll() {
        return Students;
    }

    @Override
    public Student getById(int id) {
        int index = -1;
        for (Student s : Students){
            if (s.getId() == id){
                index = Students.indexOf(s);
            }
        }
        return Students.get(index);
    }
}
