// import com.example.application.data.entity.Kurssi;
// import com.example.application.data.entity.User;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.sql.Date;
// import java.sql.Time;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// public class KurssiTest {

//     private Kurssi kurssi;
//     private User user;

//     @BeforeEach
//     public void setUp() {
//         user = new User();
//         user.setId(1L);
//         user.setUsername("testuser");

//         kurssi = new Kurssi("Test Course", "TEST123", Date.valueOf("2023-04-01"),
//                 Date.valueOf("2023-04-30"), "ABCDE", Time.valueOf("10:00:00"),
//                 Time.valueOf("18:00:00"), user);
//     }

//     @Test
//     public void testKurssiProperties() {
//         assertEquals("Test Course", kurssi.getNimi());
//         assertEquals("TEST123", kurssi.getKoodi());
//         assertEquals(Date.valueOf("2023-04-01"), kurssi.getAloitusPvm());
//         assertEquals(Date.valueOf("2023-04-30"), kurssi.getLopetusPvm());
//         assertEquals("ABCDE", kurssi.getAanestyspaivakoodi());
//         assertEquals(Time.valueOf("10:00:00"), kurssi.getAanestysAlkaa());
//         assertEquals(Time.valueOf("18:00:00"), kurssi.getAanestysLoppuu());
//         assertEquals(user, kurssi.getUser());
//     }

//     @Test
//     public void testKurssiSetters() {
//         kurssi.setNimi("New Course");
//         kurssi.setKoodi("NEW123");
//         kurssi.setAloitusPvm(Date.valueOf("2023-05-01"));
//         kurssi.setLopetusPvm(Date.valueOf("2023-05-31"));
//         kurssi.setAanestyspaivakoodi("XYZ");
//         kurssi.setAanestysAlkaa(Time.valueOf("09:00:00"));
//         kurssi.setAanestysLoppuu(Time.valueOf("17:00:00"));

//         User newUser = new User();
//         newUser.setId(2L);
//         newUser.setUsername("newuser");
//         kurssi.setUser(newUser);

//         assertEquals("New Course", kurssi.getNimi());
//         assertEquals("NEW123", kurssi.getKoodi());
//         assertEquals(Date.valueOf("2023-05-01"), kurssi.getAloitusPvm());
//         assertEquals(Date.valueOf("2023-05-31"), kurssi.getLopetusPvm());
//         assertEquals("XYZ", kurssi.getAanestyspaivakoodi());
//         assertEquals(Time.valueOf("09:00:00"), kurssi.getAanestysAlkaa());
//         assertEquals(Time.valueOf("17:00:00"), kurssi.getAanestysLoppuu());
//         assertEquals(newUser, kurssi.getUser());
//     }
// }

