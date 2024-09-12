import org.codecrafters.educa.profiles.Student;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student student_1;
    Student student_2;
    Student student_3;

    @BeforeEach
    void setup(){
        Student student_1 = new Student("Rick", "Astley", "22/10/2000", "Wicked Prankster");
        Student student_2 = new Student("Benjamin", "Button", "01/01/2024", "Very Young");
        Student student_3 = new Student("Mr", "Dr", "20/11/2025", "Doesn't exist yet");
    }

    @Test
    void testAge(){
        int actual_1 = student_1.getAge();
        int actual_2 = student_2.getAge();
        int actual_3 = student_3.getAge();

        int expected_1 = 24;
        int expected_2 = 0;
        int expected_3 = -1;

        assertEquals(expected_1, actual_1);
        assertEquals(expected_2, actual_2);
        assertEquals(expected_3, actual_3);
    }
}