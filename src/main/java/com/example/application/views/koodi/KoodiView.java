package com.example.application.views.koodi;

import java.util.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;

import com.example.application.data.service.KurssiService;
import com.example.application.views.MainLayout;
import com.example.application.views.TranslationUtils;
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

public class KoodiView extends VerticalLayout {
	KurssiService ks;
	private ResourceBundle messages;

	@PostConstruct
    public void init() {
        messages = TranslationUtils.getMessages();
        
        add(new H2(messages.getString("codeViewH1")));
       
    }
	
	

	public KoodiView(KurssiService s) {
		
		ks = s;
		setSpacing(false);
		Calendar kalenteri = Calendar.getInstance();
		Date tanaan = new Date();

		kalenteri.setTime(tanaan);
		
		TextField tf = new TextField();
		tf.setPlaceholder("Kirjoita kurssin koodi");

		Button go = new Button("Äänestämään!");
		go.addClickListener(clickEvent -> {
			List<Kurssi> kurssit = ks.findKurssit();
			for (Kurssi k : kurssit) {
				if (tf.getValue().equals(k.getKoodi())) {
						if (tanaan.after(k.getAloitusPvm()) && tanaan.before(k.getLopetusPvm())) {
						String viikonpaiva = Integer.toString(kalenteri.get(Calendar.DAY_OF_WEEK));
						System.out.println(viikonpaiva);
						if (k.getAanestyspaivakoodi().contains(viikonpaiva)) {
							LocalTime nyt = LocalTime.now();
							if ((nyt.compareTo(k.getAanestysAlkaa().toLocalTime()) > 0
									&& (nyt.compareTo(k.getAanestysLoppuu().toLocalTime()) < 0))) {
								ComponentUtil.setData(UI.getCurrent(), Kurssi.class, k);
								go.getUI().ifPresent(ui -> ui.navigate("aanesta"));
							} else {
								Notification.show("Äänestys ei taida olla vielä auki!");
							}
						} else {
							Notification.show("Tänään ei voi antaa kyseiselle kursille palautetta!");
						}
					} else {
						Notification.show("Äänestys kyseiselle kurssille on joko loppunut tai ei ole vielä alkanut!");
					}
				}
			}
		});

		add(tf, go);

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		getStyle().set("text-align", "center");
	}

}
