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

   
}
