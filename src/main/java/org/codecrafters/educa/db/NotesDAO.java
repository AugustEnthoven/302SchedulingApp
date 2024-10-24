package org.codecrafters.educa.db;

import org.codecrafters.educa.profiles.Note;

import java.util.List;

public interface NotesDAO {
    void addNote(Note note);
    void deleteNote(Note note);
    void updateNote(Note oldNote, Note newNote);
    List<Note> getAll();
    List<Note> getNotesByStudentId(int studentId);
}
