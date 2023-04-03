
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;

public class PalauteTest {

	@Test
	@DisplayName("Testaa tallentuuko palautteen päivä oikein")
	public void getPalautePaivamaara() {
		Kurssi kurssi = new Kurssi();
		Palaute p = new Palaute(1, LocalDate.now(), kurssi);
		assertEquals(LocalDate.now(), p.getPaivamaara(), "Päivämäärä on väärin");
	}

}
