package com.example.application.views.kurssi;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.annotation.security.RolesAllowed;

import com.example.application.data.entity.AanestysAjankohta;
import com.example.application.data.service.AanestysService;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.provider.DataProvider;
import org.hibernate.annotations.Check;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.User;
import com.example.application.data.service.KurssiService;
import com.example.application.data.service.UserService;
import com.example.application.views.MainLayout;
import com.example.application.views.TranslationUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed(value = {"ADMIN"})
@PageTitle("Kurssien Lisäys")
@Route(value = "kurssi", layout = MainLayout.class)
@Uses(Icon.class)


public class KurssiView extends Div {
    Locale currentLocale = TranslationUtils.getCurrentLocale();
    private ResourceBundle messages = ResourceBundle.getBundle("messages", currentLocale);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private User user;
    private String currentUserName;

    TimePicker[] pickers = new TimePicker[14];

    Checkbox[] items = new Checkbox[7];
    private TextField nimi = new TextField(messages.getString("courseName"));
    private DatePicker aloitusPvm = new DatePicker(messages.getString("courseStartDay"));
    private DatePicker lopetusPvm = new DatePicker(messages.getString("courseEndDay"));
    CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
    TimePicker palauteAlkaa = new TimePicker();
    TimePicker palauteLoppuu = new TimePicker();

    private Checkbox montaPaivaa = new Checkbox();

    private boolean muokataanko = false;
    private Button save = new Button(messages.getString("save"));

    public KurssiView(KurssiService ks, UserService userService, AanestysService aanestysService) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = userService.getByUsername(authentication.getName());
        }

        String[] viikonpaivat = {
                messages.getString("monday"),
                messages.getString("tuesday"),
                messages.getString("wednesday"),
                messages.getString("thursday"),
                messages.getString("friday"),
                messages.getString("saturday"),
                messages.getString("sunday")
        };

        DatePicker.DatePickerI18n suomiI18n = luoI18n();

        LocalDate now = LocalDate.now(ZoneId.systemDefault());

        pickers = createTimePickers(viikonpaivat);
        items = alustaCheckboxit(viikonpaivat, pickers);

        aloitusPvm.setI18n(suomiI18n);
        aloitusPvm.setMin(now);
        lopetusPvm.setMin(now);
        lopetusPvm.setI18n(suomiI18n);

        aloitusPvm.addValueChangeListener(e -> lopetusPvm.setMin(e.getValue()));
        lopetusPvm.addValueChangeListener(e -> aloitusPvm.setMax(e.getValue()));

        //checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        palauteAlkaa.setLabel(messages.getString("givingStart"));
        palauteAlkaa.setStep(Duration.ofMinutes(15));
        palauteLoppuu.setLabel(messages.getString("givingEnd"));
        palauteLoppuu.setStep(Duration.ofMinutes(15));
        muokkaaKurssia();
        if (muokataanko) {
            Kurssi kurssi = ComponentUtil.getData(UI.getCurrent(), Kurssi.class);
            nimi.setValue(kurssi.getNimi());
            aloitusPvm.setValue(kurssi.getAloitusPvm().toLocalDate());
            lopetusPvm.setValue(kurssi.getLopetusPvm().toLocalDate());
            ComponentUtil.setData(UI.getCurrent(), Kurssi.class, null);
        }
        addClassName("kurssi-view");


        add(createTitle());
        add(createFormLayout());
        add(createTimeLayout());
        add(createButtonLayout());

        save.addClickListener(e -> {
            List<Kurssi> kurssit = ks.findKurssit();
            String koodi = luoKoodi(nimi.getValue(), kurssit);

            Set<String> aanestyspaivat = checkboxGroup.getSelectedItems();


            // ks = koodiService

            if (muokataanko) {
                Kurssi kurssi = ks.findKurssi(ks.getNykyinenKurssiId());
                kurssi.setNimi(nimi.getValue());
                kurssi.setKoodi(koodi);
                kurssi.setAloitusPvm(Date.valueOf(aloitusPvm.getValue().format(formatter)));
                kurssi.setLopetusPvm(Date.valueOf(lopetusPvm.getValue().format(formatter)));
                ks.muokkaaKurssia(kurssi);
                Notification.show(messages.getString("courseA") + " " + nimi.getValue() + " " + messages.getString("edited"));
            } else {
                Kurssi kurssi = new Kurssi(nimi.getValue(), koodi, Date.valueOf(aloitusPvm.getValue().format(formatter)),
                        Date.valueOf(lopetusPvm.getValue().format(formatter)), user);
                ks.saveKurssi(kurssi);
                //Time.valueOf(palauteAlkaa.getValue()), Time.valueOf(palauteLoppuu.getValue())
                Notification.show(messages.getString("newCourseName") + " " + nimi.getValue() + " " + messages.getString("created"));
                int i = 0;
                int j = 2;
                for (Checkbox cb : items) {
                    if (cb.getValue()) {
                        aanestysService.update(new AanestysAjankohta(kurssi, j, Time.valueOf(pickers[i].getValue()), Time.valueOf(pickers[i + 1].getValue())));
                    }
                    i = i + 2;
                    j++;
                    if (j >= 7) {
                        j = 1;
                    }
                }
            }
            save.getUI().ifPresent(ui ->
                    ui.navigate("kurssit"));

        });
    }

    private Component createTitle() {
        return new H3(messages.getString("courseInfo"));
    }

    private TimePicker[] createTimePickers(String[] paivat) {
        TimePicker[] pickers = new TimePicker[14];
        int day = 0;
        for (int i = 0; i < pickers.length; i++) {
            TimePicker picker = new TimePicker();
            if ((i + 1) % 2 == 0) {
                picker.setLabel(messages.getString("feedbackEnd") + paivat[day] + messages.getString("na"));
                day++;
            } else {
                picker.setLabel(messages.getString("feedbackStart") + paivat[day] + messages.getString("na"));
            }
            picker.setVisible(false);
            pickers[i] = picker;

        }
        return pickers;
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(nimi, aloitusPvm, lopetusPvm);
        formLayout.setColspan(nimi, 3);
        return formLayout;
    }

    private Component createTimeLayout() {
        FormLayout timeLayout = new FormLayout();
        timeLayout.add(items);
        for (Checkbox cb : items) {
            timeLayout.setColspan(cb, 3);
        }
        timeLayout.add(pickers);
        return timeLayout;
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
        suomiI18n.setMonthNames(List.of(messages.getString("january"), messages.getString("february"), messages.getString("march"), messages.getString("april"), messages.getString("may"), messages.getString("june"),
        messages.getString("july"), messages.getString("august"), messages.getString("september"), messages.getString("october"), messages.getString("november"), messages.getString("december")));
        suomiI18n.setWeekdays(
                List.of(messages.getString("sunday"), messages.getString("monday"), messages.getString("tuesday"), messages.getString("wednesday"), messages.getString("thursday"), messages.getString("friday"), messages.getString("saturday")));
        suomiI18n.setWeekdaysShort(List.of(messages.getString("sun"), messages.getString("mon"), messages.getString("tue"), messages.getString("wed"), messages.getString("thu"), messages.getString("fri"), messages.getString("sat")));
        suomiI18n.setWeek(messages.getString("week"));
        suomiI18n.setToday(messages.getString("today"));
        suomiI18n.setCancel(messages.getString("cancel"));
        suomiI18n.setFirstDayOfWeek(1);
        return suomiI18n;
    }

    private String luoKoodi(String nimi, List<Kurssi> kurssit) {
        String vuosi = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) % 100);
        char[] nimiKirjaimet = new char[nimi.length()];
        int isotKirjaimetMaara = 0;
        for (int i = 0; i < nimiKirjaimet.length; i++) {
            nimiKirjaimet[i] = nimi.charAt(i);
        }
        for (char kirjain : nimiKirjaimet) {
            if (Character.isUpperCase(kirjain)) {
                isotKirjaimetMaara++;
            }
        }
        String koodi = "";
        if (isotKirjaimetMaara >= 2) {
            for (char kirjain : nimiKirjaimet) {
                if (Character.isUpperCase(kirjain)) {
                    koodi += kirjain;
                }
            }
        } else {
            if (nimi.contains(" ")) {
                koodi = nimi.substring(0, nimi.indexOf(" "));
            } else if (nimi.length() > 4) {
                koodi = nimi.substring(0, 4);
            } else {
                koodi = nimi;
            }
        }
        koodi += vuosi;
        return koodi;
    }

    private void muokkaaKurssia() {
        if (ComponentUtil.getData(UI.getCurrent(), Kurssi.class) == null) {
            return;
        }
        System.out.println(messages.getString("modifyCourse") );
        muokataanko = true;
        ComponentUtil.setData(UI.getCurrent(), String.class, null);
    }

    private Checkbox[] alustaCheckboxit(String[] viikonpaivat, TimePicker[] pickers) {
        Checkbox[] items = {
                new Checkbox(viikonpaivat[0]),
                new Checkbox(viikonpaivat[1]),
                new Checkbox(viikonpaivat[2]),
                new Checkbox(viikonpaivat[3]),
                new Checkbox(viikonpaivat[4]),
                new Checkbox(viikonpaivat[5]),
                new Checkbox(viikonpaivat[6])
        };

        items[0].addValueChangeListener(event -> {
            if (items[0].getValue()) {
                pickers[0].setVisible(true);
                pickers[1].setVisible(true);
            } else {
                pickers[0].setVisible(false);
                pickers[1].setVisible(false);
            }
        });
        items[1].addValueChangeListener(event -> {
            if (items[1].getValue()) {
                pickers[2].setVisible(true);
                pickers[3].setVisible(true);
            } else {
                pickers[2].setVisible(false);
                pickers[3].setVisible(false);
            }
        });
        items[2].addValueChangeListener(event -> {
            if (items[2].getValue()) {
                pickers[4].setVisible(true);
                pickers[5].setVisible(true);
            } else {
                pickers[4].setVisible(false);
                pickers[5].setVisible(false);
            }
        });
        items[3].addValueChangeListener(event -> {
            if (items[3].getValue()) {
                pickers[6].setVisible(true);
                pickers[7].setVisible(true);
            } else {
                pickers[6].setVisible(false);
                pickers[7].setVisible(false);
            }
        });
        items[4].addValueChangeListener(event -> {
            if (items[4].getValue()) {
                pickers[8].setVisible(true);
                pickers[9].setVisible(true);
            } else {
                pickers[8].setVisible(false);
                pickers[9].setVisible(false);
            }
        });
        items[5].addValueChangeListener(event -> {
            if (items[5].getValue()) {
                pickers[10].setVisible(true);
                pickers[11].setVisible(true);
            } else {
                pickers[10].setVisible(false);
                pickers[11].setVisible(false);
            }
        });
        items[6].addValueChangeListener(event -> {
            if (items[6].getValue()) {
                pickers[12].setVisible(true);
                pickers[13].setVisible(true);
            } else {
                pickers[12].setVisible(false);
                pickers[13].setVisible(false);
            }
        });
        return items;
    }

	public Object getNimi() {
		return null;
	}

    public Object getAloitusPvm() {
        return null;
    }

    public Object getLopetusPvm() {
        return null;
    }

    public void save() {
    }

    public Object isValid() {
        return null;
    }
}