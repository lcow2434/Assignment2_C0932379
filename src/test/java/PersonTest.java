import com.archit.assignment2.Person;  // Adjust the package name if needed

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testValidPerson() {
        Person person = Person.builder()
                .id("1")
                .firstName("John")
                .lastName("Doe")
                .age(25)
                .gender("Male")
                .build();

        assertNotNull(person);
    }

    @Test
    void testInvalidPerson() {
        assertThrows(IllegalArgumentException.class, () -> Person.builder()
                .id(null)
                .firstName("John")
                .lastName("Doe")
                .age(25)
                .gender("Male")
                .build());
    }
}
