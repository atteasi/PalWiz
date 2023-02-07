package com.example.application.views.koodi;

import javax.annotation.security.RolesAllowed;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Koodi")
@Route(value = "koodi", layout = MainLayout.class)

public class KoodiView extends VerticalLayout{
	public KoodiView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        add(new H2("Oisko siulla miulle koodia? :3"));
        TextField tf = new TextField();
        tf.setHelperText("Testatkaa koodia 'aanesta'");
        add(tf);
        Button go = new Button("Äänestämään!");
        go.addClickListener(clickEvent -> {
        	if(tf.getValue().matches("aanesta")) {
    			go.getUI().ifPresent(ui -> ui.navigate("aanesta"));
        	}
        });
        add(go);
        
        
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
