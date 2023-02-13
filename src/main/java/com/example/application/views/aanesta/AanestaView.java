package com.example.application.views.aanesta;

import java.time.LocalDate;
import javax.annotation.security.RolesAllowed;

import com.example.application.data.entity.Palaute;
import com.example.application.data.service.PalauteService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Äänestä")
@Route(value = "aanesta", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@CssImport("./themes/myapp/views/roundBtn.css")
public class AanestaView extends VerticalLayout {
	private final RoundButtons greenBtn;
	private final RoundButtons yellowBtn;
	private final RoundButtons redBtn;
	PalauteService service;
	private Palaute palaute;
	 //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	 LocalDate date = LocalDate.now();  

	public AanestaView(PalauteService service) {
		this.service = service;
		addClassName("aanesta-view");
		setHeightFull();
		//setHeight("500px");

		HorizontalLayout hlButtons = new HorizontalLayout();
		hlButtons.addClassName("buttons");

		Image greenBtnImg = new Image("images/greenBtn.png", "green button");
		Image redBtnImg = new Image("images/redBtn.png", "red button");
		Image yellowBtnImg = new Image("images/yellowBtn.png", "yellow button");

		greenBtn = new RoundButtons(greenBtnImg);
		yellowBtn = new RoundButtons(yellowBtnImg);
		redBtn = new RoundButtons(redBtnImg);

		greenBtn.addClickListener(clickEvent -> {
			System.out.println("GREEN");
			greenBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
			Palaute p = new Palaute(1, date); 
			savePalaute(p);
		});

		yellowBtn.addClickListener(clickEvent -> {
			System.out.println("YELLOW");
			Palaute p = new Palaute(2, date); 
			savePalaute(p);
			yellowBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
		});

		redBtn.addClickListener(clickEvent -> {
			System.out.println("RED");
			Palaute p = new Palaute(3, date); 
			savePalaute(p);
			redBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
		});
	
		hlButtons.add(greenBtn);
		hlButtons.add(yellowBtn);
		hlButtons.add(redBtn);

		hlButtons.setWidthFull();
		hlButtons.setSpacing(true);
		hlButtons.setHeight("300px");
		hlButtons.setJustifyContentMode(JustifyContentMode.CENTER);
		hlButtons.setAlignItems(Alignment.CENTER);
		
		
		Div otsikko = new Div(new H2("Mitä pidit oppitunnista? 🤗"));
		otsikko.setClassName("otsikko");
		Div buttons = new Div(greenBtn, yellowBtn, redBtn);
		add(otsikko, buttons);
		getStyle().set("text-align", "center");
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//
//		com.vaadin.flow.component.page.Page page = UI.getCurrent().getPage();
//		page.addBrowserWindowResizeListener(event -> {
//			if (event.getWidth() < 600) {
//				add(greenBtn, yellowBtn, redBtn);
//				//Notification.show("Window width=" + event.getWidth() + ", height=" + event.getHeight());
//			}
//			else {
//				add(hlButtons);
//				}
//		});
	}
	
	public void setPalaute(Palaute palaute) {
		this.palaute = palaute;
	}
	
	private void savePalaute(Palaute palaute) {
		 service.savePalaute(palaute);
}
	
}