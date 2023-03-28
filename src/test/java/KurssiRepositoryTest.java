
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.application.Application;

import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.User;
import com.example.application.data.service.KurssiRepository;
import com.example.application.data.service.KurssiService;
import com.example.application.data.service.UserService;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = Application.class)

@AutoConfigureTestDatabase
public class KurssiRepositoryTest {

    @Autowired
    private KurssiRepository kurssiRepository;

    @Autowired
    private KurssiService kurssiService;

    @Autowired
    private UserService userService;

    private Kurssi kurssi1;
    private Kurssi kurssi2;
    private User user;

    @Before
    public void setUp() {
        User user1 = new User("testaaja", "tauvo", "test", "salasana", null);
        user = userService.update(user1);

        kurssi1 = new Kurssi("Ohjelmointi 1", "OP1", Date.valueOf(LocalDate.of(2022, 1, 1)),
                Date.valueOf(LocalDate.of(2022, 12, 31)), "123", Time.valueOf("09:00:00"), Time.valueOf("16:00:00"),
                user);

        kurssiService.saveKurssi(kurssi1);

        kurssi2 = new Kurssi("Ohjelmointi 2", "OP2", Date.valueOf(LocalDate.of(2022, 1, 1)),
                Date.valueOf(LocalDate.of(2022, 12, 31)), "234", Time.valueOf("09:00:00"), Time.valueOf("16:00:00"),
                user);

        kurssiService.saveKurssi(kurssi2);
    }

    @After
    public void tearDown() {
        kurssiRepository.deleteAll();
    }

    @Test
    public void testFindKurssiById() {
        Kurssi kurssi = kurssiService.findKurssi(kurssi1.getId());
        assertNotNull(kurssi);
        assertEquals(kurssi1.getNimi(), kurssi.getNimi());
    }

    @Test
    public void testFindKurssiByUserId() {
        List<Kurssi> kurssit = kurssiService.findUserKurssit(kurssi1.getUser().getId());
        assertNotNull(kurssit);
        assertEquals(2, kurssit.size());
        assertEquals(kurssi1.getNimi(), kurssit.get(0).getNimi());

    }

    @Test
    public void testUpdateKurssi() {
        kurssi1.setNimi("Ohjelmointi 3");
        kurssiRepository.updateKurssi(kurssi1.getNimi(), kurssi1.getKoodi(),
                kurssi1.getAanestysAlkaa(),
                kurssi1.getAanestysLoppuu(),
                kurssi1.getAloitusPvm(), kurssi1.getLopetusPvm(),
                kurssi1.getAanestyspaivakoodi(), kurssi1.getId());
        Kurssi kurssi = kurssiRepository.findKurssiById(kurssi1.getId());
        assertEquals("Ohjelmointi 3", kurssi.getNimi());

    }

}
