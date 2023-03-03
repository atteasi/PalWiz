package com.example.application.views.kurssit;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.application.data.service.KurssiService;
import com.example.application.data.service.UserService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.User;

@RolesAllowed(value = { "ADMIN" })
@PageTitle("Kurssilistaus")
@Route(value = "kurssit", layout = MainLayout.class)

public class KurssitView extends VerticalLayout {
	KurssiService service;
	private User user;

	public KurssitView(KurssiService service, UserService userService) {
		this.service = service;


		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		user = userService.getByUsername(authentication.getName());
		}




		setSpacing(false);

		Image img = new Image("images/empty-plant.png", "placeholder plant");
		img.setWidth("200px");
		// configureGrid();
		add(img);

		add(new H2("Kurssit"));
		Grid<Kurssi> grid = new Grid<>(Kurssi.class);
		grid.setItems(service.findUserKurssit(user.getId()));

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		getStyle().set("text-align", "center");

		grid.addSelectionListener(selection -> {
			Optional<Kurssi> optionalKurssi = selection.getFirstSelectedItem();
			if (optionalKurssi.isPresent()) {
				Notification.show(optionalKurssi.get().getNimi() + " valittu, " + optionalKurssi.get().getId());
				// Component c = UI.getCurrent() ;
				// String key = "kurssi" ;
				// Object value = optionalKurssi;//optionalKurssi.get().getId() ;
				ComponentUtil.setData(UI.getCurrent(), "kurssi", optionalKurssi.get().getId());

				getUI().ifPresent(ui -> ui.navigate("palaute"));
			}
		});

		add(grid);

	}

}
