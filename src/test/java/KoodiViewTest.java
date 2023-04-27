
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.application.data.service.AanestysService;
import com.example.application.data.service.KurssiService;
import com.example.application.views.koodi.KoodiView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class KoodiViewTest {

    @Test

    @DisplayName("Test that KoodiView contains expected components")
    public void testKoodiViewComponents() {
        KurssiService mockKurssiService = Mockito.mock(KurssiService.class);
        AanestysService mockAanestysService = Mockito.mock(AanestysService.class);
        KoodiView koodiView = new KoodiView(mockKurssiService, mockAanestysService);

        H2 h2 = null;
        TextField textField = null;
        Button button = null;

        for (int i = 0; i < koodiView.getComponentCount(); i++) {
            if (koodiView.getComponentAt(i) instanceof H2) {
                h2 = (H2) koodiView.getComponentAt(i);
            } else if (koodiView.getComponentAt(i) instanceof TextField) {
                textField = (TextField) koodiView.getComponentAt(i);
            } else if (koodiView.getComponentAt(i) instanceof Button) {
                button = (Button) koodiView.getComponentAt(i);
            }
        }

        assertNotNull(h2, "H2 component not found");
        assertNotNull(textField, "TextField component not found");
        assertNotNull(button, "Button component not found");

        // You can also test the TextField placeholder and Button text if necessary
        // assertEquals("Expected TextField placeholder", textField.getPlaceholder(),

        // "TextField placeholder is incorrect");
        // assertEquals("Expected Button text", button.getText(),
        // "Button text is incorrect");
    }
}
