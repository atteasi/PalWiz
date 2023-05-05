

import com.example.application.data.entity.Kurssi;
import com.example.application.data.service.KurssiRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KurssiRepositoryTest {

    @Mock
    private KurssiRepository kurssiRepository;

    @Test
    public void testFindKurssiById() {
        // Arrange
        int id = 1;
        Kurssi mockKurssi = new Kurssi();
        when(kurssiRepository.findKurssiById(id)).thenReturn(mockKurssi);

        // Act
        Kurssi result = kurssiRepository.findKurssiById(id);

        // Assert
        verify(kurssiRepository, times(1)).findKurssiById(id);
        // Add other assertions as necessary
    }

    // Add other test methods as necessary
}
