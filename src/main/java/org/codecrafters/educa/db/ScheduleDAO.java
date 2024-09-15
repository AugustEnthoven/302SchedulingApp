package org.codecrafters.educa.db;

import org.codecrafters.educa.profiles.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating a Student Database Access Object
 */
public class ScheduleDAO {
    private Connection connection;

    /**
     * Constructor for StudentDAO object and its connection to the database
     */
    public ScheduleDAO(){
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Function for creating a students table in the database
     */
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

    /**
     * A function for dropping the students table from the database
     */
    public void dropTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute("DROP TABLE students");
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * A function for parsing a Student object into the database
     * @param student a Student object to be inserted into the database
     */
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

    /**
     * Function for updating student information in the database
     * @param student Student object to be updated
     */
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

    /**
     * Deletes a Student from the Database with the given ID
     * @param id the ID of the student to be deleted
     */
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

    /**
     * A function for returing a list of all students within the database
     * @return List object containing all Students in the database
     */
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

    /**
     * Finds student in the database with the given ID
     * @param id ID of the desired Student
     * @return Student object with the given ID
     */
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

    /**
     * disconnects from the database
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }
}
