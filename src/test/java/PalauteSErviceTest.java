

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;
import com.example.application.data.service.PalauteRepository;
import com.example.application.data.service.PalauteService;

public class PalauteSErviceTest {

    private PalauteService palauteService;
    private PalauteRepository mockPalauteRepository;
    private Kurssi mockKurssi;
    private LocalDate mockLocalDate;

    @BeforeEach
    public void setup() {
        mockPalauteRepository = mock(PalauteRepository.class);
        palauteService = new PalauteService(mockPalauteRepository);
        mockKurssi = mock(Kurssi.class);
        mockLocalDate = LocalDate.now();
    }

    @Test
    public void testFindAllPalautteet() {
        List<Palaute> mockPalauteList = new ArrayList<>();
        mockPalauteList.add(new Palaute());
        when(mockPalauteRepository.findAll()).thenReturn(mockPalauteList);

        List<Palaute> result = palauteService.findAllPalautteet();
        assertEquals(1, result.size());
    }

    @Test
    public void testFindAllGoodByIDAndDate() {
        List<Palaute> mockPalauteList = new ArrayList<>();
        mockPalauteList.add(new Palaute());
        when(mockPalauteRepository.findPalauteByValueAndKurssiAndDate(1, mockKurssi, mockLocalDate)).thenReturn(mockPalauteList);

        List<Palaute> result = palauteService.findAllGoodByIDAndDate(mockKurssi, mockLocalDate);
        assertEquals(1, result.size());
    }

    // Add more test methods as needed for other public methods in PalauteService
}
