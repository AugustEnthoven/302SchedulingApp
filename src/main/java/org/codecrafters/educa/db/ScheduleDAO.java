package org.codecrafters.educa.db;

import org.codecrafters.educa.profiles.Schedule;
import org.codecrafters.educa.profiles.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating a Schedule Database Access Object
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
     * Function for creating a schedules table in the database
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS schedules ("
                    + "studentID INTEGER PRIMARY KEY, "
                    + "className VARCHAR NOT NULL, "
                    + "time INTEGER NOT NULL, "
                    + "preference INTEGER NOT NULL, "
                    + ")"
            );
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * A function for dropping the schedules table from the database
     */
    public void dropTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute("DROP TABLE schedules");
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * A function for parsing a Schedule object into the database
     * @param schedule a Schedule object to be inserted into the database
     */
    public void insert(Schedule schedule){
        try {
            PreparedStatement insertAccount = connection.prepareStatement(
                    "INSERT INTO schedules (studentID, className, time, preference)" +
                            "VALUES (?, ?, ?, ?)"
            );
            insertAccount.setInt(1, schedule.GetStudentID());
            insertAccount.setString(2, schedule.GetClassName());
            insertAccount.setInt(3, schedule.GetTime());
            insertAccount.setInt(4, schedule.GetPreference());
            insertAccount.execute();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * Function for updating schedule information in the database
     * @param schedule Schedule object to be updated
     */
    public void update( Schedule schedule){
        try{
            PreparedStatement updateAccount = connection.prepareStatement(
                    "UPDATE schedules SET studentID = ?, className = ?, " +
                            "time = ?, preference = ?"
            );
            updateAccount.setInt(1, schedule.GetStudentID());
            updateAccount.setString(2, schedule.GetClassName());
            updateAccount.setInt(3, schedule.GetTime());
            updateAccount.setInt(4, schedule.GetPreference());
            updateAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Deletes a Schedule from the database with a student and ID and the time of the class
     * @param studentID the ID of the student the schedule entry is attached to
     * @param time the time of the schedule entry to be deleted
     */
    public void delete(int studentID, int time){
        try{
            PreparedStatement deleteAccount = connection.prepareStatement(
                    "DELETE FROM schedules WHERE studentID = ? AND time = ?"
            );
            deleteAccount.setInt(1, studentID);
            deleteAccount.setInt(2, time);
            deleteAccount.execute();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * Deletes a Schedule from the database with a student and ID and the name of the class
     * @param studentID the ID of the student the schedule entry is attached to
     * @param className the name of the class to be removed
     */
    public void delete(int studentID, String className){
        try{
            PreparedStatement deleteAccount = connection.prepareStatement(
                    "DELETE FROM schedules WHERE studentID = ? AND className = ?"
            );
            deleteAccount.setInt(1, studentID);
            deleteAccount.setString(2, className);
            deleteAccount.execute();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * A function for returning a list of all schedule entries within the database
     * @return List object containing all schedule entries in the database
     */
    public List<Schedule> getAll() {
        List<Schedule> schedules = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM schedules");
            while (rs.next()){
                schedules.add(
                        new Schedule(
                                rs.getInt("studentID"),
                                rs.getString("className"),
                                rs.getInt("time"),
                                rs.getInt("preference")
                        )
                );
            }
        } catch (SQLException ex){
            System.err.println(ex);
        }
        return schedules;
    }

    /**
     * Finds schedule in the database with the given student ID and time
     * @param studentID The id of the student the schedule entry is associated with
     * @param time The time the class takes place in
     * @return Schedule object associated with a given student and time
     */
    public Schedule getByTime(int studentID, int time){
        try {
            PreparedStatement getAccount = connection.prepareStatement(
                    "SELECT * FROM schedules WHERE studentID = ? AND time = ?"
            );
            getAccount.setInt(1, studentID);
            getAccount.setInt(1, time);
            ResultSet rs = getAccount.executeQuery();
            if (rs.next()){
                new Schedule(
                        rs.getInt("studentID"),
                        rs.getString("className"),
                        rs.getInt("time"),
                        rs.getInt("preference")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Finds schedule in the database with the given student ID and class name
     * @param studentID The id of the student the schedule entry is associated with
     * @param className The name of the class
     * @return Schedule object associated with a given student and time
     */
    public Schedule getByName(int studentID, String className){
        try {
            PreparedStatement getAccount = connection.prepareStatement(
                    "SELECT * FROM schedules WHERE studentID = ? AND className = ?"
            );
            getAccount.setInt(1, studentID);
            getAccount.setString(1, className);
            ResultSet rs = getAccount.executeQuery();
            if (rs.next()){
                new Schedule(
                        rs.getInt("studentID"),
                        rs.getString("className"),
                        rs.getInt("time"),
                        rs.getInt("preference")
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
