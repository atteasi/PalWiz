package com.example.application.views.kurssit;

import javax.annotation.security.RolesAllowed;

import com.example.application.data.service.KurssiService;
import com.example.application.data.service.PalauteService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;

@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Kurssilistaus")
@Route(value = "kiitos", layout = MainLayout.class)

public class KurssitView extends VerticalLayout {
	KurssiService service;


	public KurssitView(KurssiService service) {
		this.service = service;
		setSpacing(false);

		Image img = new Image("images/empty-plant.png", "placeholder plant");
		img.setWidth("200px");
		//configureGrid();
		add(img);

		add(new H2("Kiitos palautteesta!"));
		Grid<Kurssi> grid = new Grid<>(Kurssi.class);
		grid.setItems(service.findKurssit());
		add(grid);
		 

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		getStyle().set("text-align", "center");
		
		
	}
	

}
