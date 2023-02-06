package com.example.application.views.aanesta;

import javax.annotation.security.RolesAllowed;

import org.springframework.data.domain.Page;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Äänestä")
@Route(value = "aanesta", layout = MainLayout.class)
@CssImport("./themes/myapp/views/roundBtn.css")
public class AanestaView extends VerticalLayout {
	private final RoundButtons greenBtn;
	private final RoundButtons yellowBtn;
	private final RoundButtons redBtn;

	public AanestaView() {
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
		});

		yellowBtn.addClickListener(clickEvent -> {
			System.out.println("YELLOW");
			yellowBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
		});

		redBtn.addClickListener(clickEvent -> {
			System.out.println("RED");
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

}