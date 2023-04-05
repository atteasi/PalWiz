import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.example.application.data.service.PalauteService;
import com.example.application.views.aanesta.AanestaView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AanestaViewTest {

    @Test
    @DisplayName("Test that AanestaView contains expected components")
    public void testAanestaViewComponents() {
        PalauteService mockPalauteService = Mockito.mock(PalauteService.class);
        AanestaView aanestaView = new AanestaView(mockPalauteService);

        Div nappulatDiv = null;
        Div otsikkoDiv = null;
        H2 h2 = null;

        for (int i = 0; i < aanestaView.getComponentCount(); i++) {
            if (aanestaView.getComponentAt(i) instanceof Div) {
                Div div = (Div) aanestaView.getComponentAt(i);
                if ("nappulat".equals(div.getClassName())) {
                    nappulatDiv = div;
                } else if ("otsikko".equals(div.getClassName())) {
                    otsikkoDiv = div;
                }
            }
        }

        assertNotNull(nappulatDiv, "Nappulat Div not found");
        assertNotNull(otsikkoDiv, "Otsikko Div not found");

        for (int i = 0; i < otsikkoDiv.getComponentCount(); i++) {
            if (otsikkoDiv.getComponentAt(i) instanceof H2) {
                h2 = (H2) otsikkoDiv.getComponentAt(i);
            }
        }

        assertNotNull(h2, "H2 component not found");

        // You can also test the H2 text content if necessary
        // assertEquals("Expected H2 text", h2.getText(), "H2 text is incorrect");
    }
}
