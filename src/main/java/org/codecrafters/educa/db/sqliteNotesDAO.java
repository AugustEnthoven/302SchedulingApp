package org.codecrafters.educa.db;

import org.codecrafters.educa.profiles.Note;
import org.codecrafters.educa.profiles.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sqliteNotesDAO implements NotesDAO{
    private Connection connection;

    /**
     * Constructor for StudentDAO object and its connection to the database
     */
    public sqliteNotesDAO(){
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Function for creating a notes table in the database if it doesn't exist already
     */
    public void createTable(){
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS Notes ("
                            + "StudentID INTEGER NOT NULL, "
                            + "CreatorID INTEGER NOT NULL, "
                            + "Date VARCHAR NOT NULL, "
                            + "Contents VARCHAR NOT NULL"
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
            Statement dropTable = connection.createStatement();
            dropTable.execute("DROP TABLE Notes");
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * method for inserting Note object into the database
     * @param note a Note object to be added to database
     */
    @Override
    public void addNote(Note note) {
        try {
            PreparedStatement insertNote = connection.prepareStatement(
                    "INSERT INTO Notes (StudentID, CreatorID, Date, Contents)" +
                            "VALUES (?, ?, ?, ?)"
            );
            insertNote.setInt(1, note.getStudentId());
            insertNote.setInt(2, note.getCreatorId());
            insertNote.setString(3, note.getDateCreated());
            insertNote.setString(4, note.getContents());
            insertNote.execute();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * method for removing Note object from database
     * @param note note to be removed
     */
    @Override
    public void deleteNote(Note note) {
        try{
            PreparedStatement deleteNote = connection.prepareStatement(
                    "DELETE FROM Notes WHERE " +
                            "StudentID = ? " +
                            "AND CreatorID = ? " +
                            "AND Date = ? " +
                            "AND Contents = ?"
            );
            deleteNote.setInt(1, note.getStudentId());
            deleteNote.setInt(2, note.getCreatorId());
            deleteNote.setString(3, note.getDateCreated());
            deleteNote.setString(4, note.getContents());
            deleteNote.execute();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * method for updating an existing note within the database
     * @param oldNote original note as it currently exists
     * @param newNote new note to replace old note
     */
    @Override
    public void updateNote(Note oldNote, Note newNote) {
        try{
            PreparedStatement updateNote = connection.prepareStatement(
                    "UPDATE Notes SET StudentID = ?, CreatorID = ?, " +
                            "Date = ?, Contents = ? WHERE " +
                            "StudentID = ? AND CreatorID = ? AND " +
                            "Date = ? AND Contents = ?"
            );
            updateNote.setInt(1, newNote.getStudentId());
            updateNote.setInt(2, newNote.getCreatorId());
            updateNote.setString(3, newNote.getDateCreated());
            updateNote.setString(4, newNote.getContents());
            updateNote.setInt(5, oldNote.getStudentId());
            updateNote.setInt(6, oldNote.getCreatorId());
            updateNote.setString(7, oldNote.getDateCreated());
            updateNote.setString(8, oldNote.getContents());
            updateNote.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * method for returning all notes within the database
     * @return List of all Note objects in database
     */
    @Override
    public List<Note> getAll() {
        List<Note> notes = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM Notes");
            while (rs.next()){
                notes.add(
                        new Note(
                                rs.getInt("StudentID"),
                                rs.getInt("CreatorID"),
                                rs.getString("Date"),
                                rs.getString("Contents")
                        )
                );
            }
        } catch (SQLException ex){
            System.err.println(ex);
        }
        return notes;
    }

    /**
     * method to return all notes assigned to a given student
     * @param studentId the ID number of the selected student
     * @return List of all notes about the given student
     */
    @Override
    public List<Note> getNotesByStudentId(int studentId) {
        List<Note> notes = new ArrayList<>();
        try {
            PreparedStatement getAccount = connection.prepareStatement(
                    "SELECT * FROM Notes WHERE StudentID = ?"
            );
            getAccount.setInt(1, studentId);
            ResultSet rs = getAccount.executeQuery();
            while (rs.next()){
                notes.add(
                        new Note(
                            rs.getInt("StudentID"),
                            rs.getInt("CreatorID"),
                            rs.getString("Date"),
                            rs.getString("Contents")
                    )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return notes;
    }
}
