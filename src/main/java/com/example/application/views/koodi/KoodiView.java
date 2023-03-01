package com.example.application.views.koodi;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import com.example.application.data.service.KurssiService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.example.application.data.entity.Kurssi;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Koodi")
@Route(value = "koodi", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class KoodiView extends VerticalLayout{
	KurssiService ks;
	public KoodiView(KurssiService s) {
		ks = s;
        setSpacing(false);

        add(new H2("Tähän voit syöttää koodin, jolla pääset antamaan tunnista palautetta!"));
        TextField tf = new TextField();
       
        Button go = new Button("Äänestämään!");
        go.addClickListener(clickEvent -> {
        	List<Kurssi> kurssit = ks.findKurssit();
        	for(Kurssi k : kurssit) {
        		if(tf.getValue().matches(k.getKoodi())) {
        			ComponentUtil.setData(UI.getCurrent(), Kurssi.class, k);
        			go.getUI().ifPresent(ui -> ui.navigate("aanesta"));
        		} else {
        		}
        	}
        });
        add(go, tf);
        
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }


}
