package org.codecrafters.educa.db;

public class NoteManager {
    private NotesDAO notesDAO;
    public NoteManager(NotesDAO notesDAO){
        this.notesDAO = notesDAO;
    }
}
