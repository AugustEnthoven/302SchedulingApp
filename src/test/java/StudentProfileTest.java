import org.codecrafters.educa.profiles.Student;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student student_1 = new Student("Rick", "Astley", "22/08/2000", "Wicked Prankster");
    Student student_2 = new Student("Benjamin", "Button", "01/01/2024", "Very Young");
    Student student_3 = new Student("Mr", "Dr", "20/11/2025", "Doesn't exist yet");

    @Test
    void testAgeNormal(){
        int actual_1 = student_1.getAge();

        int expected_1 = 24;

        assertEquals(expected_1, actual_1);
    }

    @Test
    void testAge0(){
        int actual_2 = student_2.getAge();

        int expected_2 = 0;

        assertEquals(expected_2, actual_2);
    }

    @Test
    void testAgeNeg(){
        int actual_3 = student_3.getAge();

        int expected_3 = -1;

        assertEquals(expected_3, actual_3);
    }
}