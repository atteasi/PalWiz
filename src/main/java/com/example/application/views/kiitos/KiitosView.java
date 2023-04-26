package com.example.application.views.kiitos;

import java.util.Locale;
import javax.annotation.security.RolesAllowed;
import java.util.ResourceBundle;
import com.example.application.data.service.PalauteService;
import com.example.application.views.MainLayout;
import com.example.application.views.TranslationUtils;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed(value = { "USER", "ADMIN" })
@AnonymousAllowed
@PageTitle("Kiitos")
@Route(value = "kiitos", layout = MainLayout.class)

public class KiitosView extends VerticalLayout {
	private ResourceBundle messages;
	PalauteService service;
	Locale currentLocale = TranslationUtils.getCurrentLocale();

	public KiitosView(PalauteService service) {
		messages = ResourceBundle.getBundle("messages", currentLocale);
		this.service = service;
		setSpacing(false);

		Image img = new Image("images/empty-plant.png", "");
		img.setAlt("placeholder plant");
		img.setWidth("200px");
		// configureGrid();
		add(img);
		Button palaa = new Button(messages.getString("goBack"));
		palaa.addClickListener(e -> palaa.getUI().ifPresent(ui -> ui.navigate("koodi")));

		add(new H2(messages.getString("thankYouForFeedback")), palaa);
		// Grid<Palaute> grid = new Grid<>(Palaute.class);
		// grid.setItems(service.findAllPalautteet());
		// add(grid);

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		getStyle().set("text-align", "center");
	}
}
