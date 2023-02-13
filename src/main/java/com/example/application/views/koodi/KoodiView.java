package com.example.application.views.koodi;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import com.example.application.data.service.KurssiService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.data.entity.Kurssi;

@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Koodi")
@Route(value = "koodi", layout = MainLayout.class)

public class KoodiView extends VerticalLayout{
	KurssiService ks;
	public KoodiView(KurssiService s) {
		ks = s;
        setSpacing(false);

        add(new H2("Oisko siulla miulle koodia? :3"));
        TextField tf = new TextField();
        tf.setHelperText("Testatkaa koodia 'aanesta', 'kakka' tai 'moi");
       
        Button go = new Button("Äänestämään!");
        go.addClickListener(clickEvent -> {
        	List<Kurssi> kurssit = ks.findKurssit();
        	for(Kurssi k : kurssit) {
        		if(tf.getValue().matches(k.getKoodi())) {
        			go.getUI().ifPresent(ui -> ui.navigate("aanesta"));
        		} else {
        			
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
		ks.pohjustaKurssit();
		System.out.println("done");
	}

}
