package com.example.application.views.kurssit;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import com.example.application.data.service.KurssiService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.example.application.data.entity.Kurssi;

@RolesAllowed(value = { "ADMIN" })
@PageTitle("Kurssilistaus")
@Route(value = "kurssit", layout = MainLayout.class)


public class KurssitView extends VerticalLayout {
	KurssiService service;


	public KurssitView(KurssiService service) {
		this.service = service;
		setSpacing(false);

		Image img = new Image("images/empty-plant.png", "placeholder plant");
		img.setWidth("200px");
		//configureGrid();
		add(img);

		add(new H2("Kurssit"));
		Grid<Kurssi> grid = new Grid<>(Kurssi.class);
		grid.setItems(service.findKurssit());
		
		 

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		getStyle().set("text-align", "center");
		
		 grid.addSelectionListener(selection -> {
            Optional<Kurssi> optionalKurssi = selection.getFirstSelectedItem();
            if (optionalKurssi.isPresent()) {
							Notification.show( optionalKurssi.get().getNimi() + " valittu ");
							Notification.show( optionalKurssi.get().getId() + " valittu ");
							
            }
        });

				add(grid);
		
	}
	

}
