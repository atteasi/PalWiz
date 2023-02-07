package com.example.application.views.koodi;

import java.security.Provider.Service;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import com.example.application.data.service.KoodiService;
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
import com.example.application.data.entity.Koodi;

@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Koodi")
@Route(value = "koodi", layout = MainLayout.class)

public class KoodiView extends VerticalLayout{
	KoodiService ks;
	public KoodiView(KoodiService s) {
		ks = s;
        setSpacing(false);

        add(new H2("Oisko siulla miulle koodia? :3"));
        TextField tf = new TextField();
        tf.setHelperText("Testatkaa koodia 'aanesta', 'kakka' tai 'moi");
       
        Button go = new Button("Äänestämään!");
        go.addClickListener(clickEvent -> {
        	List<Koodi> koodit = ks.findKoodit();
        	for(Koodi k : koodit) {
        		if(tf.getValue().matches(k.getKoodi())) {
        			go.getUI().ifPresent(ui -> ui.navigate("aanesta"));
        		}
        	}
        });
        add(go, tf);
        
        Button pohjusta = new Button ("Pohjustus koodeille koska oon huono devaaja");
        	pohjusta.addClickListener(clickEvent ->{ 
        		pohjustaKoodit();
        });
        add(pohjusta);
        
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
	
	private void pohjustaKoodit() {
		ks.pohjustaKoodit();
		System.out.println("done");
	}

}
