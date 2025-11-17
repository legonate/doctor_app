import java.util.ArrayList;
import services.UserService;

import edu.secourse.patientportal.models.User;

public class UserServiceTest {
    @Test
    @DisplayName("addDoctor should return correct user and add to list")
    void testAddDoctor_HappyPath() {
        // Arrange
        String username = "dr.house";
        String email = "house@example.com";
        String role = "doctor";
        String name = "Dr. Gregory House";
        String password = "vicodin123";

        Object userService;
        // Act
        User returnedDoctor = userService.addDoctor(username, email, role, name, password);

        // Assert (Check returned object)
        assertNotNull(returnedDoctor, "The returned user should not be null.");

        // Use assertAll to check all properties of the returned user
        assertAll("Returned Doctor Properties",
            () -> assertEquals(0, returnedDoctor.getId(), "ID should be 0 for the first user."),
            () -> assertEquals(username, returnedDoctor.getUsername()),
            () -> assertEquals(email, returnedDoctor.getEmail_address()),
            () -> assertEquals(role, returnedDoctor.getRole()),
            () -> assertEquals(name, returnedDoctor.getName()),
            () -> assertEquals(password, returnedDoctor.getPassword())
        );

        // Assert (Check service state)
        ArrayList<User> users = userService.getUsers();
        assertEquals(1, users.size(), "User list should have 1 user.");

        // Check that the user in the list is the same one that was returned
        assertSame(returnedDoctor, users.get(0), "User in the list should be the same instance that was returned.");
    }

    private void assertNotNull(User returnedDoctor, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertNotNull'");
    }
}
