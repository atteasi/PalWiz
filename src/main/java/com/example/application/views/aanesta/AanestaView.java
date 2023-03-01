package com.example.application.views.aanesta;

import java.time.LocalDate;
import javax.annotation.security.RolesAllowed;

import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;
import com.example.application.data.service.PalauteService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Äänestä")
@Route(value = "aanesta", layout = MainLayout.class)
@CssImport("./themes/myapp/views/roundBtn.css")

public class AanestaView extends VerticalLayout {
	private RoundButton greenBtn;
	private RoundButton yellowBtn;
	private RoundButton redBtn;
	PalauteService service;

	LocalDate date = LocalDate.now();

	public AanestaView(PalauteService service) {
		this.service = service;
		addClassName("aanesta-view");

		add(luoOtsikko(), luoNappulat());
		getStyle().set("text-align", "center");
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);

		Kurssi kurssi = ComponentUtil.getData(UI.getCurrent(), Kurssi.class);
		
		greenBtn.addClickListener(clickEvent -> {
			System.out.println("GREEN");
			greenBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
			Palaute p = new Palaute(1, date,kurssi.getID());
			savePalaute(p);
		});

		yellowBtn.addClickListener(clickEvent -> {
			System.out.println("YELLOW");
			Palaute p = new Palaute(2, date,kurssi.getID());
			savePalaute(p);
			yellowBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
		});

		redBtn.addClickListener(clickEvent -> {
			System.out.println("RED");
			Palaute p = new Palaute(3, date,kurssi.getID());
			savePalaute(p);
			redBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
		});

	}

	private Div luoNappulat() {
		Image greenBtnImg = new Image("images/greenBtn.png", "green button");
		Image redBtnImg = new Image("images/redBtn.png", "red button");
		Image yellowBtnImg = new Image("images/yellowBtn.png", "yellow button");

		greenBtn = new RoundButton(greenBtnImg);
		yellowBtn = new RoundButton(yellowBtnImg);
		redBtn = new RoundButton(redBtnImg);
		Div nappulat = new Div(greenBtn, yellowBtn, redBtn);
		nappulat.setClassName("nappulat");
		return nappulat;
	}

	private Div luoOtsikko() {
		H2 o = new H2("Mitä pidit oppitunnista? 🤗");
		o.addClassName("aanestysOtsikko");
		Div otsikko = new Div(o);
		otsikko.setClassName("otsikko");
		return otsikko;

	}


	private void savePalaute(Palaute palaute) {
		service.savePalaute(palaute);
	}

}