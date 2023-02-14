package com.example.application.views.kiitos;

import javax.annotation.security.RolesAllowed;

import com.example.application.data.service.PalauteService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.data.entity.Palaute;


@RolesAllowed(value = { "USER", "ADMIN" })
@AnonymousAllowed
@PageTitle("Kiitos")
@Route(value = "kiitos", layout = MainLayout.class)	

public class KiitosView extends VerticalLayout {
	PalauteService service;


	public KiitosView(PalauteService service) {
		this.service = service;
		setSpacing(false);

		Image img = new Image("images/empty-plant.png", "placeholder plant");
		img.setWidth("200px");
		//configureGrid();
		add(img);

		add(new H2("Kiitos palautteesta!"));
		Grid<Palaute> grid = new Grid<>(Palaute.class);
		grid.setItems(service.findAllPalautteet());
		add(grid);
		 

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		getStyle().set("text-align", "center");
		
		
	}
	

}
