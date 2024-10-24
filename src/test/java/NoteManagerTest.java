import org.codecrafters.educa.db.MockNotesDAO;
import org.codecrafters.educa.profiles.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NoteManagerTest {
    private MockNotesDAO NotesDAO;

    Note note1 = new Note(1, 1, "01/01/2000", "Note 1");
    Note note2 = new Note(1, 1, "01/01/2001", "Note 2");
    Note note3 = new Note(2, 1, "02/02/2001", "Note 3");
    Note note3v2 = new Note(2, 1, "02/02/2001", "Note 3v2");
    Note note4 = new Note(2, 1, "01/01/2000", "Note 4");
    Note note5 = new Note(3, 1, "01/01/2000", "Note 5");

    @BeforeEach
    void setup(){
        NotesDAO = new MockNotesDAO();
        NotesDAO.addNote(note1);
        NotesDAO.addNote(note2);
        NotesDAO.addNote(note3);
    }

    @Test
    void addNoteTestSingle(){
        NotesDAO.addNote(note4);

        List<Note> expected = Arrays.asList(note1, note2, note3, note4);

        assertEquals(NotesDAO.getAll(), expected);
    }

    @Test
    void addNoteTestMultiple(){
        NotesDAO.addNote(note4);
        NotesDAO.addNote(note5);

        List<Note> expected = Arrays.asList(note1, note2, note3, note4, note5);

        assertEquals(NotesDAO.getAll(), expected);
    }

    @Test
    void updateNoteTest(){
        NotesDAO.updateNote(note3, note3v2);

        List<Note> expected = Arrays.asList(note1, note2, note3v2);

        assertEquals(NotesDAO.getAll(), expected);
    }

    @Test
    void deleteTest(){
        NotesDAO.deleteNote(note2);

        List<Note> expected = Arrays.asList(note1, note3);

        assertEquals(NotesDAO.getAll(), expected);
    }

    @Test
    void getByStudentIDTest(){
        List<Note> expected = Arrays.asList(note1, note2);

        assertEquals(NotesDAO.getNotesByStudentId(1), expected);
    }
}
