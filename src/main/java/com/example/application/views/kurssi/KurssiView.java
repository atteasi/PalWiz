package com.example.application.views.kurssi;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
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
        addClassName("kurssi-view");
        
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        
        save.addClickListener(e -> {
        	
            ks.saveKurssi(new Kurssi(nimi.getValue(), "faux",
            		Date.valueOf(aloitusPvm.getValue().format(formatter)), Date.valueOf(lopetusPvm.getValue().format(formatter))));
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

}
