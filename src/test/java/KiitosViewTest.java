import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.example.application.data.service.PalauteService;
import com.example.application.views.kiitos.KiitosView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class KiitosViewTest {

    @Test
    @DisplayName("Test that KiitosView contains expected components")
    public void testKiitosViewComponents() {
        PalauteService mockPalauteService = Mockito.mock(PalauteService.class);
        KiitosView kiitosView = new KiitosView(mockPalauteService);

        Image image = null;
        H2 h2 = null;
        Button button = null;

        for (int i = 0; i < kiitosView.getComponentCount(); i++) {
            if (kiitosView.getComponentAt(i) instanceof Image) {
                image = (Image) kiitosView.getComponentAt(i);
            } else if (kiitosView.getComponentAt(i) instanceof H2) {
                h2 = (H2) kiitosView.getComponentAt(i);
            } else if (kiitosView.getComponentAt(i) instanceof Button) {
                button = (Button) kiitosView.getComponentAt(i);
            }
        }

        assertNotNull(image, "Image component not found");
        assertNotNull(h2, "H2 component not found");
        assertNotNull(button, "Button component not found");

        assertEquals("200px", image.getWidth(), "Image width is incorrect");
        assertEquals("placeholder plant", image.getAlt(), "Image alt text is incorrect");
    }
}
