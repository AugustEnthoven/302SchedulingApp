package org.codecrafters.educa.db;

import org.codecrafters.educa.profiles.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class studentDAO {
    private Connection connection;

    public studentDAO(){
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS students ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "firstName VARCHAR NOT NULL, "
                    + "lastName VARCHAR NOT NULL, "
                    + "age INTEGER NOT NULL, "
                    + "dob VARCHAR NOT NULL, "
                    + "conditions VARCHAR NOT NULL"
                    + ")"
            );
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void insert(Student student){
        try {
            PreparedStatement insertAccount = connection.prepareStatement(
                    "INSERT INTO students (firstName, lastName, age, dob, conditions)" +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            insertAccount.setString(1, student.getFirstName());
            insertAccount.setString(2, student.getLastName());
            insertAccount.setInt(3, student.getAge());
            insertAccount.setString(4, student.getDOB());
            insertAccount.setString(5, student.getConditions());
            insertAccount.execute();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void update( Student student){
        try{
            PreparedStatement updateAccount = connection.prepareStatement(
                    "UPDATE students SET firstName = ?, lastName = ?, " +
                            "age = ?, dob = ?, conditions = ?"
            );
            updateAccount.setString(1, student.getFirstName());
            updateAccount.setString(2, student.getLastName());
            updateAccount.setInt(3, student.getAge());
            updateAccount.setString(4, student.getDOB());
            updateAccount.setString(5, student.getConditions());
            updateAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int id){
        try{
            PreparedStatement deleteAccount = connection.prepareStatement(
                    "DELETE FROM students WHERE id = ?"
            );
            deleteAccount.setInt(1, id);
            deleteAccount.execute();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM students");
            while (rs.next()){
                students.add(
                        new Student(
                                rs.getInt("id"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getInt("age"),
                                rs.getString("dob"),
                                rs.getString("conditions")
                        )
                );
            }
        } catch (SQLException ex){
            System.err.println(ex);
        }
        return students;
    }

    public Student getById(int id){
        try {
            PreparedStatement getAccount = connection.prepareStatement(
                    "SELECT * FROM students WHERE id = ?"
            );
            getAccount.setInt(1, id);
            ResultSet rs = getAccount.executeQuery();
            if (rs.next()){
                return new Student(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("dob"),
                        rs.getString("conditions")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }
}
