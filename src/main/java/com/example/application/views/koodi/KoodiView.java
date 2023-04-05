package com.example.application.views.koodi;

import java.util.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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

	Locale currentLocale = TranslationUtils.getCurrentLocale();
	ResourceBundle messages;

	public KoodiView(KurssiService s) {
		messages = ResourceBundle.getBundle("messages", currentLocale);
		ks = s;
		setSpacing(false);
		Calendar kalenteri = Calendar.getInstance();
		Date tanaan = new Date();

		kalenteri.setTime(tanaan);
		add(new H2(messages.getString("koodiViewH2")));
		TextField tf = new TextField();
		tf.setPlaceholder(messages.getString("writeCode"));

		Button go = new Button(messages.getString("toVote"));
		go.addClickListener(clickEvent -> {
			List<Kurssi> kurssit = ks.findKurssit();
			if (!kurssit.contains(tf.getValue())) {
				Notification.show("Kyseistä koodia ei ole olemassa! Tarkasta koodi!");
			}
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
								Notification.show(messages.getString("notOpenYet"));
							}
						} else {
							Notification.show(messages.getString("todayNoVote"));
						}
					} else {
						Notification.show(messages.getString("itsEndOrComing"));
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
