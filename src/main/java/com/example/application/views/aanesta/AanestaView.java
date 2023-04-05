package com.example.application.views.aanesta;

import java.time.LocalDate;
import java.util.ResourceBundle;
import javax.annotation.security.RolesAllowed;
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;
import com.example.application.data.service.PalauteService;
import com.example.application.views.MainLayout;
import com.example.application.views.TranslationUtils;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import java.util.Locale;

@AnonymousAllowed
@RolesAllowed(value = { "USER", "ADMIN" })
@PageTitle("Äänestä")
@Route(value = "aanesta", layout = MainLayout.class)
@CssImport("./themes/myapp/views/roundBtn.css")

public class AanestaView extends VerticalLayout {
    Locale currentLocale = TranslationUtils.getCurrentLocale();
    private ResourceBundle messages;
    private RoundButton greenBtn;
    private RoundButton yellowBtn;
    private RoundButton redBtn;
    PalauteService service;
    LocalDate date = LocalDate.now();

    public AanestaView(PalauteService service) {
        messages = ResourceBundle.getBundle("messages", currentLocale);
        this.service = service;
        addClassName("aanesta-view");

        add(luoOtsikko(), luoNappulat());
        getStyle().set("text-align", "center");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        Kurssi kurssi = ComponentUtil.getData(UI.getCurrent(), Kurssi.class);

        greenBtn.addClickListener(clickEvent -> {
            greenBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
            Palaute p = new Palaute(1, date, kurssi);
            savePalaute(p);
        });

        yellowBtn.addClickListener(clickEvent -> {
            Palaute p = new Palaute(2, date, kurssi);
            savePalaute(p);
            yellowBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
        });

        redBtn.addClickListener(clickEvent -> {
            Palaute p = new Palaute(3, date, kurssi);
            savePalaute(p);
            redBtn.getUI().ifPresent(ui -> ui.navigate("kiitos"));
        });
    }

    private Div luoNappulat() {
        Image greenBtnImg = new Image("images/greenBtn.png", "green button");
        Image redBtnImg = new Image("images/redBtn.png", "red button");
        Image yellowBtnImg = new Image("images/yellowBtn.png", "yellow button");

        greenBtn = new RoundButton(greenBtnImg);
        yellowBtn = new RoundButton(yellowBtnImg);
        redBtn = new RoundButton(redBtnImg);
        Div nappulat = new Div(greenBtn, yellowBtn, redBtn);
        nappulat.setClassName("nappulat");
        return nappulat;
    }

    private Div luoOtsikko() {
        H2 o = new H2(messages.getString("classFeedback"));
        o.addClassName("aanestysOtsikko");
        Div otsikko = new Div(o);
        otsikko.setClassName("otsikko");
        return otsikko;
    }

    private void savePalaute(Palaute palaute) {
        service.savePalaute(palaute);
    }
}
