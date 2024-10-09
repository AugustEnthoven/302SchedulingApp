import org.codecrafters.educa.db.IStudentDAO;
import org.codecrafters.educa.db.MockStudentDAO;
import org.codecrafters.educa.db.StudentDAO;
import org.codecrafters.educa.profiles.Student;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {
    private MockStudentDAO studentDAO;

    Student student_1 = new Student(1, "John", "Jonas", "01/01/2000", "Too old for school");
    Student student_2 = new Student(2, "Doug", "Douglas", "03/02/2008", "The middle child");
    Student student_2_2 = new Student(2, "Doug", "Douglass", "03/02/2008", "The middle child updated");
    Student student_3 = new Student(3, "Steve", "Stevenson", "25/12/2000", "Christmas child");

    @BeforeEach
    void setup(){
        studentDAO = new MockStudentDAO();
    }

    @Test
    void insertTest(){
        studentDAO.insert(student_1);

        Student expected = student_1;

        assertEquals(studentDAO.getById(1), expected);
    }

    @Test
    void updateTest(){
        studentDAO.insert(student_2);
        studentDAO.update(student_2_2);

        Student expected = student_2_2;

        assertEquals(studentDAO.getById(2), expected);
    }

    @Test
    void deleteTest(){
        studentDAO.insert(student_1);
        studentDAO.insert(student_2);
        studentDAO.insert(student_3);

        studentDAO.delete(2);

        List<Student> expected = Arrays.asList(student_1, student_3);

        assertEquals(studentDAO.getAll(), expected);
    }

}
