package com.example.application.views.koodi;

import java.util.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.security.RolesAllowed;

import com.example.application.data.entity.AanestysAjankohta;
import com.example.application.data.service.AanestysService;
import com.example.application.data.service.KurssiService;
import com.example.application.views.MainLayout;
import com.example.application.views.TranslationUtils;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
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

    public KoodiView(KurssiService s, AanestysService as) {
        messages = ResourceBundle.getBundle("messages", currentLocale);
        ks = s;
        setSpacing(false);
        Calendar kalenteri = Calendar.getInstance();
        Date tanaan = new Date();

        kalenteri.setTime(tanaan);
        add(new H2(messages.getString("koodiViewH2")));
        TextField tf = new TextField();
        tf.setPlaceholder(messages.getString("writeCode"));
        tf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        Button go = new Button(messages.getString("toVote"));
        go.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_CONTRAST);
        go.addClickListener(clickEvent -> {

            List<Kurssi> kurssit = ks.findKurssit();
            for (Kurssi k : kurssit) {
                if (tf.getValue().equals(k.getKoodi())) {
                    List<AanestysAjankohta> aanestysAjankohdat = as.findByKurssi(k);
                    if (tanaan.after(k.getAloitusPvm()) && tanaan.before(k.getLopetusPvm())) {
                        String viikonpaiva = Integer.toString(kalenteri.get(Calendar.DAY_OF_WEEK));
                        boolean jatkuu = false;
                        System.out.println(viikonpaiva);
                        AanestysAjankohta aanestysAjankohta = null;
                        for (AanestysAjankohta aa : aanestysAjankohdat) {
                            if (viikonpaiva.equals(Integer.toString(aa.getPaiva()))) {
                                jatkuu = true;
                                aanestysAjankohta = aa;
                            }
                        }
                        if (jatkuu) {
                            LocalTime nyt = LocalTime.now();
                            if ((nyt.compareTo(aanestysAjankohta.getAanestysAlkaa().toLocalTime()) > 0
                                    && (nyt.compareTo(aanestysAjankohta.getAanestysLoppuu().toLocalTime()) < 0))) {
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
        setClassName("koodi-view");
    }

}