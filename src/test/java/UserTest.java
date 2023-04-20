import com.example.application.data.Role;
import com.example.application.data.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private User user;
    private Set<Role> roles;

    @BeforeEach
    public void setUp() {
        roles = new HashSet<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);

        user = new User("John", "Doe", "johndoe", "password123", roles);
    }

    @Test
    public void testUserProperties() {
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getSurName());
        assertEquals("johndoe", user.getUsername());
        assertTrue(BCrypt.checkpw("password123", user.getHashedPassword()));
        assertEquals(roles, user.getRoles());
    }

    @Test
    public void testUserSetters() {
        user.setUsername("janedoe");
        user.setHashedPassword(BCrypt.hashpw("newpassword123", BCrypt.gensalt(10)));

        Set<Role> newRoles = new HashSet<>();
        newRoles.add(Role.USER);
        user.setRoles(newRoles);

        assertEquals("janedoe", user.getUsername());
        assertTrue(BCrypt.checkpw("newpassword123", user.getHashedPassword()));
        assertEquals(newRoles, user.getRoles());
    }
}

