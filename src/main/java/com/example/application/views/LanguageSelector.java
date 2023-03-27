package com.example.application.views;

import com.vaadin.flow.component.select.Select;

import java.util.Locale;

public class LanguageSelector extends Select<String> {

    public LanguageSelector() {
        setLabel("Language");
        setItems("English", "Finnish");
        addValueChangeListener(event -> updateLanguage(event.getValue()));
        setValue(TranslationUtils.getCurrentLocale().getDisplayName());
    }

   /*  private void updateLanguage(String language) {
        switch (language) {
            case "Finnish":
                TranslationUtils.setCurrentLocale(new Locale("fi", "FI"));
                break;
            default:
                TranslationUtils.setCurrentLocale(Locale.ENGLISH);
        }

        // Trigger a UI refresh to apply the new language
        getUI().ifPresent(ui -> ui.getPage().reload());
    } */

    private void updateLanguage(String language) {
        switch (language) {
            case "English":
                TranslationUtils.setCurrentLocale(new Locale("en", "EN"));
                break;
                case "Finnish":
                TranslationUtils.setCurrentLocale(new Locale("fi", "FI"));
        }

        // Trigger a UI refresh to apply the new language
        getUI().ifPresent(ui -> ui.getPage().reload());
    }

}

