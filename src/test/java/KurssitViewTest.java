
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.User;
import com.example.application.data.service.KurssiService;
import com.example.application.data.service.PalauteService;
import com.example.application.data.service.UserService;
import com.example.application.views.kurssit.KurssitView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KurssitViewTest {

    private KurssiService kurssiService;
    private UserService userService;
    private PalauteService palauteService;
    private User user;

    @BeforeEach
    public void setUp() {
        kurssiService = mock(KurssiService.class);
        userService = mock(UserService.class);
        palauteService = mock(PalauteService.class);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(authentication.getPrincipal()).thenReturn("testuser");
        when(authentication.isAuthenticated()).thenReturn(true);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(userService.getByUsername("testuser")).thenReturn(user);
    }

    @Test
    public void testCreateKurssitView() {
        KurssitView view = new KurssitView(kurssiService, userService,
                palauteService);
        assertNotNull(view);
    }

    @Test
    public void testGridDisplayCourses() {
        List<Kurssi> kurssiList = new ArrayList<>();
        Kurssi kurssi1 = new Kurssi();
        kurssi1.setId(1);
        kurssi1.setNimi("Course 1");
        kurssiList.add(kurssi1);

        Kurssi kurssi2 = new Kurssi();
        kurssi2.setId(2);
        kurssi2.setNimi("Course 2");
        kurssiList.add(kurssi2);

        when(kurssiService.findUserKurssit(user.getId())).thenReturn(kurssiList);

        KurssitView view = new KurssitView(kurssiService, userService,
                palauteService);
        Grid<Kurssi> grid = view.getGrid(); // Change this line
        assertTrue(grid instanceof Grid);

        assertEquals(2, grid.getDataProvider().size(new com.vaadin.flow.data.provider.Query<>()));

        Div hint = view.getHint(); // Change this line
        assertFalse(hint.isVisible());
    }

    @Test
    public void testRemoveCourse() {
        List<Kurssi> kurssiList = new ArrayList<>();
        Kurssi kurssi1 = new Kurssi();
        kurssi1.setId(1);
        kurssi1.setNimi("Course 1");
        kurssiList.add(kurssi1);

        when(kurssiService.findUserKurssit(user.getId())).thenReturn(kurssiList);

        KurssitView view = new KurssitView(kurssiService, userService,
                palauteService);

        view.poistaKurssi(kurssi1);

        verify(palauteService, times(1)).poistaPalauteet(kurssi1);
        verify(kurssiService, times(1)).poistaKurssi(kurssi1);

        Grid<Kurssi> grid = view.getGrid();
        assertTrue(grid instanceof Grid);
        assertEquals(0, grid.getDataProvider().size(new com.vaadin.flow.data.provider.Query<>()));

        Div hint = view.getHint();
        assertTrue(hint.isVisible());
    }

}
