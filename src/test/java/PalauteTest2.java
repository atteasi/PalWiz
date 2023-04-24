import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PalauteTest2 {

    private Palaute palaute;
    private Kurssi kurssi;

    @BeforeEach
    public void setUp() {
        kurssi = new Kurssi();
        kurssi.setNimi("Test Course");
        kurssi.setKoodi("TEST123");

        palaute = new Palaute(5, LocalDate.of(2023, 4, 1), kurssi);
    }

    @Test
    public void testPalauteProperties() {
        assertEquals(5, palaute.getAnnettuVastaus());
        assertEquals(LocalDate.of(2023, 4, 1), palaute.getPaivamaara());
        assertEquals(kurssi, palaute.getKurssi());
    }

    @Test
    public void testPalauteSetters() {
        palaute.setAnnettuVastaus(4);
        palaute.setPaivamaara(LocalDate.of(2023, 4, 2));
        palaute.setKokonaismaara(10);

        Kurssi newKurssi = new Kurssi();
        newKurssi.setNimi("New Course");
        newKurssi.setKoodi("NEW123");
        palaute.setKurssi(newKurssi);

        assertEquals(4, palaute.getAnnettuVastaus());
        assertEquals(LocalDate.of(2023, 4, 2), palaute.getPaivamaara());
        assertEquals(10, palaute.getKokonaismaara());
        assertEquals(newKurssi, palaute.getKurssi());
    }
}
