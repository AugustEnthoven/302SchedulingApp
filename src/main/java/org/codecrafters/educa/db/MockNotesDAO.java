package org.codecrafters.educa.db;

import org.codecrafters.educa.profiles.Note;
import org.codecrafters.educa.profiles.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MockNotesDAO implements NotesDAO{
    private List<Note> notes = new ArrayList<>();

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public void deleteNote(Note note) {
        int index = -1;
        for (Note n : notes){
            if (Objects.equals(n.toString(), note.toString())){
                index = notes.indexOf(n);
            }
        }
        notes.remove(index);
    }

    @Override
    public void updateNote(Note note) {
        int index = -1;
        for (Note n : notes){
            if (Objects.equals(n.toString(), note.toString())){
                index = notes.indexOf(n);
            }
        }
        notes.set(index, note);
    }

    @Override
    public List<Note> getAll() {
        return notes;
    }

    @Override
    public List<Note> getNotesByStudentId(int studentId) {
        List<Note> matches = new ArrayList<>();

        for (Note n : notes){
            if (n.getStudentId() == studentId){
                matches.add(n);
            }
        }

        return matches;
    }
}
