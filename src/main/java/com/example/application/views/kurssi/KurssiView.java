package com.example.application.views.kurssi;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import com.example.application.data.entity.Kurssi;
import com.example.application.data.service.KurssiService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@RolesAllowed(value = { "ADMIN" })
@PageTitle("Kurssien Lisäys")
@Route(value = "kurssi", layout = MainLayout.class)
@Uses(Icon.class)
public class KurssiView extends Div {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private TextField nimi = new TextField("Kurssin nimi");
	private DatePicker aloitusPvm = new DatePicker("Aloitus päivämäärä");
	private DatePicker lopetusPvm = new DatePicker("Lopetus päivämäärä");

	private Button save = new Button("Save");

	public KurssiView(KurssiService ks) {
		DatePicker.DatePickerI18n suomiI18n = luoI18n();

		LocalDate now = LocalDate.now(ZoneId.systemDefault());
		
		aloitusPvm.setI18n(suomiI18n);
		aloitusPvm.setMin(now);
		lopetusPvm.setMin(now);
		lopetusPvm.setI18n(suomiI18n);

		aloitusPvm.addValueChangeListener(e -> lopetusPvm.setMin(e.getValue()));
		lopetusPvm.addValueChangeListener(e -> aloitusPvm.setMax(e.getValue()));
		
		addClassName("kurssi-view");

		add(createTitle());
		add(createFormLayout());
		add(createButtonLayout());

		save.addClickListener(e -> {
			int vuosi = Calendar.getInstance().get(Calendar.YEAR) % 100;
			String koodi = nimi.getValue().substring(0, 4) + String.valueOf(vuosi);
			List<Kurssi> kurssit = ks.findKurssit();
			boolean onEnnestaan = false;
			for(Kurssi k : kurssit) {
				if(k.getKoodi().matches(koodi)) {
					onEnnestaan = true;
				}
			}
			if(onEnnestaan) { 
				int vanhojaKoodeja = 0;
				for(Kurssi k : kurssit) {
					String testattavaKoodi = k.getKoodi().substring(0,6);
					if(testattavaKoodi.matches(koodi)) {
						vanhojaKoodeja++;
					}
				}
				koodi += "(" + vanhojaKoodeja + ")";
			}
			// ks = koodiService
			ks.saveKurssi(new Kurssi(nimi.getValue(), koodi, Date.valueOf(aloitusPvm.getValue().format(formatter)),
					Date.valueOf(lopetusPvm.getValue().format(formatter))));
					Notification.show("Uusi kurssi nimeltä " + nimi.getValue() + " luotu");
					
					save.getUI().ifPresent(ui ->
           ui.navigate("kurssit"));
		});
	}

	private Component createTitle() {
		return new H3("Kurssin tiedot");
	}

	private Component createFormLayout() {
		FormLayout formLayout = new FormLayout();
		formLayout.add(nimi, aloitusPvm, lopetusPvm);
		formLayout.setColspan(nimi, 3);
		return formLayout;
	}

	private Component createButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.addClassName("button-layout");
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		buttonLayout.add(save);
		return buttonLayout;
	}

	private DatePicker.DatePickerI18n luoI18n() {
		DatePicker.DatePickerI18n suomiI18n = new DatePicker.DatePickerI18n();
		suomiI18n.setMonthNames(List.of("Tammikuu", "Helmikuu", "Maaliskuu", "Huhtikuu", "Toukokuu", "Kesäkuu",
				"Heinäkuu", "Elokuu", "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu"));
		suomiI18n.setWeekdays(
				List.of("Sunnuntai", "Maanantai", "Tiistai", "Keskiviikko", "Torstai", "Perjantai", "Lauantai"));
		suomiI18n.setWeekdaysShort(List.of("Su", "Ma", "Ti", "Ke", "To", "Pe", "La"));
		suomiI18n.setWeek("Viikko");
		suomiI18n.setToday("Tänään");
		suomiI18n.setCancel("Peru");
		suomiI18n.setFirstDayOfWeek(1);
		return suomiI18n;
	}

}
