import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.application.data.entity.AanestysAjankohta;
import com.example.application.data.entity.Kurssi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;

public class AanestysAjankohtaTest {

    @Test
    public void testGettersAndSetters() {
        Kurssi kurssi = new Kurssi();
        int paiva = 1;
        Time aanestysAlkaa = Time.valueOf("09:00:00");
        Time aanestysLoppuu = Time.valueOf("12:00:00");
        AanestysAjankohta aanestysAjankohta = new AanestysAjankohta(kurssi, paiva, aanestysAlkaa, aanestysLoppuu);
        
        // Test getters
        Assertions.assertEquals(kurssi, aanestysAjankohta.getKurssiId());
        Assertions.assertEquals(paiva, aanestysAjankohta.getPaiva());
        Assertions.assertEquals(aanestysAlkaa, aanestysAjankohta.getAanestysAlkaa());
        Assertions.assertEquals(aanestysLoppuu, aanestysAjankohta.getAanestysLoppuu());
        
        // Test setters
        Kurssi newKurssi = new Kurssi();
        int newPaiva = 2;
        Time newAanestysAlkaa = Time.valueOf("13:00:00");
        Time newAanestysLoppuu = Time.valueOf("16:00:00");
        aanestysAjankohta.setKurssiId(newKurssi);
        aanestysAjankohta.setPaiva(newPaiva);
        aanestysAjankohta.setAanestysAlkaa(newAanestysAlkaa);
        aanestysAjankohta.setAanestysLoppuu(newAanestysLoppuu);
        aanestysAjankohta.setId(1);
        
        // Test updated values
        assertEquals(newKurssi, aanestysAjankohta.getKurssiId());
        assertEquals(newPaiva, aanestysAjankohta.getPaiva());
        assertEquals(newAanestysAlkaa, aanestysAjankohta.getAanestysAlkaa());
       assertEquals(newAanestysLoppuu, aanestysAjankohta.getAanestysLoppuu());
        assertEquals(1, aanestysAjankohta.getId());
    }
}
