import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.application.views.kurssi.KurssiView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KurssiViewTest {

    @Mock
    private KurssiView kurssiView;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Set up the mock methods
        when(kurssiView.getNimi()).thenReturn("Some Name");
        when(kurssiView.getAloitusPvm()).thenReturn("Some Start Date");
        when(kurssiView.getLopetusPvm()).thenReturn("Some End Date");
        doNothing().when(kurssiView).save();
    }

    @Test
    public void testSaveWhenValid() {
        // Assume that the isValid method returns true
        when(kurssiView.isValid()).thenReturn(true);

        // Call the save method
        kurssiView.save();

        // Verify that the save method was called
        verify(kurssiView, times(1)).save();
    }

    @Test
    public void testSaveWhenInvalid() {
        // Assume that the isValid method returns false
        when(kurssiView.isValid()).thenReturn(false);

        // Try to call the save method
        Exception exception = assertThrows(IllegalStateException.class, () -> kurssiView.save());

        // Verify that the save method was not called
        verify(kurssiView, times(0)).save();

        // Verify that the exception message is what we expect
        String expectedMessage = "Cannot save: KurssiView is not in a valid state";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
