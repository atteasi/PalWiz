import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.application.data.entity.Palaute;
import com.example.application.data.service.PalauteRepository;
import com.example.application.data.service.PalauteService;

public class PalauteTest {

	@Test
	@DisplayName("Testaa tallentuuko palautteen päivä oikein")
	public void getPalautePaivamaara() {
		Palaute p = new Palaute(1, LocalDate.now());
		assertEquals(LocalDate.now(), p.getPaivamaara(), "Päivämäärä on väärin");
	}
}
